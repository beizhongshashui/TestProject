package com.yuyoubang.activity.push;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.activity.login.LoginAccountActivity;
import com.yuyoubang.app.YuYouBangApp;
import com.yuyoubang.bean.LoginUserBean;
import com.yuyoubang.bean.NewStatus;
import com.yuyoubang.bean.UploadBean;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.BaseCallback;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.TripApi;
import com.yuyoubang.network.api.UploadApi;
import com.yuyoubang.permission.MPermission;
import com.yuyoubang.permission.OnMPermissionDenied;
import com.yuyoubang.permission.OnMPermissionGranted;
import com.yuyoubang.utils.BitmapUtil;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.LocationUtil;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.view.FlowLayout_AddPicture;
import com.yuyoubang.widget.photo.PhotoPickerActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 16/11/18.
 */
public class PushStateActivity extends BaseActivity {
    private static final int PICK_PHOTO = 1;
    private final int BASIC_PERMISSION_REQUEST_CODE = 110;

    @Bind(R.id.add_pic)
    FlowLayout_AddPicture addPic;
    @Bind(R.id.et_content)
    EditText etContent;
    @Bind(R.id.sign_location)
    TextView signLocation;
    @Bind(R.id.rl_sign_location)
    RelativeLayout rlSignLocation;

    private List<String> picUrls = new ArrayList<>();
    private int position;
    private String content;
    private File fileTemp;

    LoginUserBean.ResultBean.UserDataBean user;
    private FormBody body;
    private String location;
    private String curLocation = "";

    public static void start(Context context) {
        Intent intent = new Intent(context, PushStateActivity.class);
        context.startActivity(intent);
    }

    AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (!TextUtils.isEmpty(aMapLocation.getCity())) {
                Message message = Message.obtain();
                message.obj = aMapLocation.getCity();
                handler.sendMessage(message);
            }


        }
    };

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            curLocation = (String) msg.obj;
        }
    };


    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("发布动态");
        builder.setRightTvText("发布", new OnClickListener() {
            @Override
            protected void clickOperate() {
                content = etContent.getText().toString().trim();
                location = signLocation.getText().toString();
                /*if (TextUtils.isEmpty(content)) {
                    ToastUtils.showLong("内容不能为空");
                    return;
                }
                location = signLocation.getText().toString();
                if (location.equals("标记这是哪里")) {
//            location = "";
                    ToastUtils.showShort("请选择动态地址");
                    cancelProgressDlg();
                    return;
                }*/

                onShowProgressDlg();
                if (addPic.getSelectedPictures().size() == 0) {
                    pushStatus(content);
                } else {
                    upLoad();
                }
            }
        });


    }


    private void requestBasicPermission() {
        MPermission.with(PushStateActivity.this)
                .addRequestCode(BASIC_PERMISSION_REQUEST_CODE)
                .permissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                )
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @OnMPermissionGranted(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionSuccess() {
//        Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
    }

    @OnMPermissionDenied(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionFailed() {
//        Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> result = data.getStringArrayListExtra(PhotoPickerActivity.KEY_RESULT);
                for (int i = 0; i < result.size(); i++) {
                    addPic.addPicture(result.get(i));
                }
            }
        }

        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
                if (data.getExtras().getString("location") != null) {
                    signLocation.setText(data.getExtras().getString("location"));
                }
            }
        }


    }

    @Override
    protected int getContentResId() {
        return R.layout.act_push_state;
    }

    private void upLoad() {
        if (addPic.getSelectedPictures().size() == 0) {
            ToastUtils.showShort("请上传图片");
            return;
        }
        File file = new File(addPic.getSelectedPictures().get(position));
        Bitmap getimage = BitmapUtil.getImage(file.getPath());

        File cacheDir = StorageUtils.getOwnCacheDirectory(this,
                "yuyoubang/Cache");
        String fileName = cacheDir.getPath() + "/"
                + "pic" + System.currentTimeMillis();
        FileOutputStream mFileOutputStream = null;

        try {
            mFileOutputStream = new FileOutputStream(fileName);
            getimage.compress(Bitmap.CompressFormat.JPEG, 100,
                    mFileOutputStream);// 把数据写入文件
            fileTemp = new File(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("pic", fileTemp.getName() + ".jpg", RequestBody.create(MediaType.parse("image/*"), fileTemp));
        UploadApi uploadApi = RetrofitManager.getTestRetrofit().create(UploadApi.class);
        Call<UploadBean> upload = uploadApi.upload(filePart);
        upload.enqueue(new BaseCallback<UploadBean>() {
            @Override
            public void onResponse(Call<UploadBean> call, Response<UploadBean> response) {
                if (response.body().getError_code() == 0) {
                    String profile_pic_url = response.body().getResult().getOriginal_pic();
                    picUrls.add(profile_pic_url);
                    if (position != addPic.getSelectedPictures().size() - 1) {
                        position++;
                        upLoad();
                    } else {
                        pushStatus(content);
                    }
                } else {

                }
//                cancelProgressDlg();

            }

            @Override
            public void onFailure(Call<UploadBean> call, Throwable t) {
                super.onFailure(call, t);
                /*if (position != addPic.getSelectedPictures().size() - 1) {
                    position++;
                    upLoad();
                }*/
                cancelProgressDlg();
                ToastUtils.showLong("网络连接失败");
            }
        });
    }

    private void pushStatus(String content) {

        StringBuffer imgfileUrl = null;
        String imgUrlPath = null;
        if (addPic.getSelectedPictures().size() != 0) {
            imgfileUrl = new StringBuffer();
            for (String filePath : picUrls) {
                imgfileUrl.append(filePath).append(",");
            /*int endIndex = filePath.indexOf(".com");
            if (endIndex >= 0) {
                String fileUrlPath = filePath.substring(endIndex + 4);
                imgfileUrl.append(fileUrlPath).append(",");
            }*/
            }

            imgUrlPath = imgfileUrl.substring(0, imgfileUrl.length() - 1);
        }


        try {
            user = PreferenceUtils.deSerialization(PreferenceUtils.getPrefString(PushStateActivity.this, "user", ""));
            if (user == null) {
                ToastUtils.showShort("账号异常");
                YuYouBangApp.logOut();
                qStartActivity(LoginAccountActivity.class);
                finish();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        TripApi tripApi = RetrofitManager.getTestRetrofit().create(TripApi.class);
        if (TextUtils.isEmpty(content) && TextUtils.isEmpty(imgUrlPath)) {
            ToastUtils.showShort("内容和图片不能同时为空");
            cancelProgressDlg();
            return;
        }
        if (TextUtils.isEmpty(content) && (!TextUtils.isEmpty(imgUrlPath))) {
            if (location.equals("标记这是哪里")) {
                body = new FormBody.Builder()
                        .add("uid", PreferenceUtils.getsessionId(PushStateActivity.this))
                        .add("pics", imgUrlPath)
                        .add("location", curLocation)
//                        .add("user_location", user.getUser_location())
                        .add("user_location", curLocation)
                        .build();
            } else {
                body = new FormBody.Builder()
                        .add("uid", PreferenceUtils.getsessionId(PushStateActivity.this))
                        .add("pics", imgUrlPath)
                        .add("location", location)
//                        .add("user_location", user.getUser_location())
                        .add("user_location", curLocation)
                        .build();
            }
        } else if (TextUtils.isEmpty(imgUrlPath) && (!TextUtils.isEmpty(content))) {
            if (location.equals("标记这是哪里")) {
                body = new FormBody.Builder()
                        .add("uid", PreferenceUtils.getsessionId(PushStateActivity.this))
                        .add("content", content)
                        .add("location", curLocation)
//                        .add("user_location", user.getUser_location())
                        .add("user_location", curLocation)
                        .build();
            } else {
                body = new FormBody.Builder()
                        .add("uid", PreferenceUtils.getsessionId(PushStateActivity.this))
                        .add("content", content)
                        .add("location", location)
//                        .add("user_location", user.getUser_location())
                        .add("user_location", curLocation)
                        .build();

            }
        } else if (!(TextUtils.isEmpty(imgUrlPath)) && (!TextUtils.isEmpty(content))) {
            if (location.equals("标记这是哪里")) {
                body = new FormBody.Builder()
                        .add("uid", PreferenceUtils.getsessionId(PushStateActivity.this))
                        .add("content", content)
                        .add("pics", imgUrlPath)
                        .add("location", curLocation)
//                        .add("user_location", user.getUser_location())
                        .add("user_location", curLocation)
                        .build();

            } else {
                body = new FormBody.Builder()
                        .add("uid", PreferenceUtils.getsessionId(PushStateActivity.this))
                        .add("content", content)
                        .add("location", location)
                        .add("pics", imgUrlPath)
//                        .add("user_location", user.getUser_location())
                        .add("user_location", curLocation)
                        .build();
            }
        } else {
            body = new FormBody.Builder()
                    .add("uid", PreferenceUtils.getsessionId(PushStateActivity.this))
                    .add("content", content)
                    .add("location", location)
                    .add("pics", imgUrlPath)
//                    .add("user_location", user.getUser_location())
                    .add("user_location", curLocation)
                    .build();
        }
        Call<NewStatus> login = tripApi.status_new(body);
        login.enqueue(new BaseCallback<NewStatus>() {
            @Override
            public void onResponse(Call<NewStatus> call, Response<NewStatus> response) {
                Log.e("PushActActivity", response.body() + "=" + response.message());
                if (response.body().getError_code() == 0) {
                    ToastUtils.showLong("发布成功");
                    finish();
                } else {

                }
                cancelProgressDlg();

            }

            @Override
            public void onFailure(Call<NewStatus> call, Throwable t) {
                super.onFailure(call, t);
                cancelProgressDlg();
                ToastUtils.showLong(R.string.net_error);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        LocationUtil.startLocation(mLocationListener);
        initView();
        requestBasicPermission();
    }


    private void initView() {
        addPic.setMaxAllowedCount(9);
        addPic.setOnClickAddPictureListener(new FlowLayout_AddPicture.ClickAddPictureListener() {
            @Override
            public void addPicture() {

                Intent intent = new Intent(PushStateActivity.this, PhotoPickerActivity.class);
                intent.putExtra(PhotoPickerActivity.EXTRA_SHOW_CAMERA, PhotoPickerActivity.EXTRA_SHOW_CAMERA);
                intent.putExtra(PhotoPickerActivity.EXTRA_SELECT_MODE, PhotoPickerActivity.MODE_MULTI);
                intent.putExtra(PhotoPickerActivity.EXTRA_MAX_MUN, addPic.mMaxAllowedCount - addPic.getSelectedPictureCount());
                startActivityForResult(intent, PICK_PHOTO);
            }
        });


    }

    @OnClick(R.id.rl_sign_location)
    public void onClick() {
        LaunchOperate.openPoiMapActivity(this, 100);
        /*Intent intent = new Intent(this, SignLocationActivity.class);
        startActivityForResult(intent, 100);*/
    }


}
