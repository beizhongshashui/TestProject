package com.yuyoubang.activity.mine;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseNetActivity;
import com.yuyoubang.bean.UploadBean;
import com.yuyoubang.bean.mine.ApplyCommit;
import com.yuyoubang.bean.mine.UserSureName;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.network.BaseCallback;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.MineApi;
import com.yuyoubang.network.api.UploadApi;
import com.yuyoubang.utils.BitmapUtil;
import com.yuyoubang.utils.FileUtil;
import com.yuyoubang.utils.LogUtils;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.ToastUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hongchen on 16/12/2.
 */

public class UserInfoStateActivity extends BaseNetActivity<UserSureName> {

    //请求相机
    private static final int REQUEST_CAPTURE = 102;
    //请求相册
    private static final int REQUEST_PICK = 103;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 104;

    private static final int ONE = 0;//正面
    private static final int TWO = 1;//反面

    private View top_line;
    private LinearLayout top_layout;
    private LinearLayout display_layout;
    private TextView tv_commit;
    private TextView tv_load_time;
    private LinearLayout success_layout;
    private ImageView iv_msg;
    private TextView tv_because;
    private TextView tv_1;
    private TextView tv_2;
    private ImageView click_one;
    private ImageView click_two;

    private File tempFile;
    private float angle;
    private String pic_1;
    private String pic_2;
    private ImageView iv_pic_1;
    private ImageView iv_pic_2;
    private TextView tv_3;
    private TextView tv_4;
    private EditText et_name;
    private EditText et_num;

    private int type;
    private FrameLayout one_layout;
    private FrameLayout two_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createHeadImageTempFile(savedInstanceState);
    }

    @Override
    protected void initViews() {
        top_line = getViewById(R.id.top_line);
        top_layout = getViewById(R.id.top_layout);
        display_layout = getViewById(R.id.display_layout);
        success_layout = getViewById(R.id.success_layout);

        one_layout = getViewById(R.id.one_layout);
        two_layout = getViewById(R.id.two_layout);

        tv_because = getViewById(R.id.tv_because);

        tv_1 = getViewById(R.id.tv_1);
        tv_2 = getViewById(R.id.tv_2);
        tv_3 = getViewById(R.id.tv_3);
        tv_4 = getViewById(R.id.tv_4);

        iv_msg = getViewById(R.id.iv_msg);

        click_one = getViewById(R.id.click_one);
        click_two = getViewById(R.id.click_two);
        iv_pic_1 = getViewById(R.id.iv_pic_1);
        iv_pic_2 = getViewById(R.id.iv_pic_2);

        et_name = getViewById(R.id.et_name);
        et_num = getViewById(R.id.et_num);

        tv_commit = getViewById(R.id.tv_commit);
        tv_load_time = getViewById(R.id.tv_load_time);


        et_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    String temp = s.toString();
                    String tem = temp.substring(temp.length() - 1, temp.length());
                    char[] temC = tem.toCharArray();
                    int mid = temC[0];
                    if (mid >= 48 && mid <= 57) {//数字
                        return;
                    }
                    if (mid >= 65 && mid <= 90) {//大写字母
                        return;
                    }
                    if (mid > 97 && mid <= 122) {//小写字母
                        return;
                    }
                    s.delete(temp.length() - 1, temp.length());
                } catch (Exception e) {
                }
            }
        });


        one_layout.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                type = ONE;
                showPhotoDialog();
            }
        });

        two_layout.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                type = TWO;
                showPhotoDialog();
            }
        });

        tv_commit.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//提交审核
                String name = et_name.getText().toString().trim();
                String num = et_num.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    ToastUtils.showShort("真实姓名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(num)) {
                    ToastUtils.showShort("身份证号码不能为空");
                    return;
                }
                if (num.length() < 18) {
                    ToastUtils.showShort("请正确输入身份证号码");
                    return;
                }
                if (TextUtils.isEmpty(pic_1)) {
                    ToastUtils.showShort("请上传身份证照片");
                    return;
                }
                if (TextUtils.isEmpty(pic_2)) {
                    ToastUtils.showShort("请上传身份证照片");
                    return;
                }
                commitUserInfoTService(name, num);
            }
        });
    }

    private void commitUserInfoTService(String name, String num) {
        //// TODO: 16/11/28  缺如下几个参数
        //("phone",pic2,pic3,name,"uid","user_name",card);
        String phone = PreferenceUtils.getPrefString(this, "phone", "");
        if (TextUtils.isEmpty(phone)){
            return;
        }
        String user_name = PreferenceUtils.getPrefString(this, "user_name", "");
        if (TextUtils.isEmpty(user_name)) {
            return;
        }
        FormBody body = new FormBody.Builder()
                .add("phone", phone)
                .add("pic2", pic_1)
                .add("pic3", pic_2)
                .add("real_name", name)
                .add("uid", PreferenceUtils.getsessionId(this))
                .add("user_name", user_name)
                .add("vip_card_number", num)
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<ApplyCommit> userSureNameCall = mineApi.apply_verification(body);
        userSureNameCall.enqueue(new Callback<ApplyCommit>() {
            @Override
            public void onResponse(Call<ApplyCommit> call, Response<ApplyCommit> response) {
                LogUtils.e("UserSureName" + response.body());
                ToastUtils.showShort("提交审核成功");
                finish();
            }

            @Override
            public void onFailure(Call<ApplyCommit> call, Throwable t) {
                LogUtils.e("UserSureName", t);
                ToastUtils.showShort("网络错误");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    Bitmap getimage = BitmapUtil.compressImage(tempFile.getPath());

                    File cacheDir = StorageUtils.getOwnCacheDirectory(this,
                            "yuyoubang/Cache");
                    String fileName = cacheDir.getPath() + "/"
                            + "pic" + System.currentTimeMillis();
                    FileOutputStream mFileOutputStream = null;

                    try {
                        mFileOutputStream = new FileOutputStream(fileName);
                        getimage.compress(Bitmap.CompressFormat.JPEG, 100,
                                mFileOutputStream);// 把数据写入文件
                        File file = new File(fileName);
                        upLoad(file, type);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String path = FileUtil.getRealFilePathFromUri(getApplicationContext(), uri);


                    Bitmap getimage = BitmapUtil.getImage(path);

                    File cacheDir = StorageUtils.getOwnCacheDirectory(this,
                            "yuyoubang/Cache");
                    String fileName = cacheDir.getPath() + "/"
                            + "pic" + System.currentTimeMillis();
                    FileOutputStream mFileOutputStream = null;

                    try {
                        mFileOutputStream = new FileOutputStream(fileName);
                        getimage.compress(Bitmap.CompressFormat.JPEG, 100,
                                mFileOutputStream);// 把数据写入文件
                        File file = new File(fileName);
                        upLoad(file, type);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


                }
                break;
        }
    }

    private void showPhotoDialog() {
        final AlertDialog dlg = new AlertDialog.Builder(this).create();
        dlg.show();
        Window window = dlg.getWindow();
        window.setContentView(R.layout.alert_dialog);
        TextView tv_paizhao = (TextView) window.findViewById(R.id.tv_content1);
        tv_paizhao.setText("拍照");
        tv_paizhao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getCamera(dlg);
            }
        });
        TextView tv_xiangce = (TextView) window.findViewById(R.id.tv_content2);
        tv_xiangce.setText("相册");
        tv_xiangce.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getPhoto(dlg);
            }
        });

    }

    private void getCamera(AlertDialog dlg) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        startActivityForResult(intent, REQUEST_CAPTURE);
        dlg.cancel();
    }

    private void getPhoto(AlertDialog dlg) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
        dlg.cancel();
    }

    private void createHeadImageTempFile(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("tempFile")) {
            tempFile = (File) savedInstanceState.getSerializable("tempFile");
        } else {
            tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"),
                    System.currentTimeMillis() + ".jpg");
        }
    }

    /**
     * 检查文件是否存在
     */
    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    private void upLoad(File file, final int type) {
        onShowProgressDlg();
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("pic", file.getName() + ".jpg", RequestBody.create(MediaType.parse("image/*"), file));
        UploadApi uploadApi = RetrofitManager.getTestRetrofit().create(UploadApi.class);
        Call<UploadBean> upload = uploadApi.upload(filePart);
        upload.enqueue(new BaseCallback<UploadBean>() {
            @Override
            public void onResponse(Call<UploadBean> call, Response<UploadBean> response) {
                Log.e("LoginInfoActivity", "response=" + response.body().getResult().getBmiddle_pic());
                if (response.body().getError_code() == 0) {
                    if (type == ONE) {
                        pic_1 = response.body().getResult().getOriginal_pic();
                        click_one.setVisibility(View.GONE);
                        tv_3.setVisibility(View.GONE);
                        ImageLoader.getInstance().displayImage(pic_1, iv_pic_1, ImageOption.default_trip_options);
                    }
                    if (type == TWO) {
                        pic_2 = response.body().getResult().getOriginal_pic();
                        click_two.setVisibility(View.GONE);
                        tv_4.setVisibility(View.GONE);
                        ImageLoader.getInstance().displayImage(pic_2, iv_pic_2, ImageOption.default_trip_options);
                    }
                } else {
                }
                cancelProgressDlg();
            }

            @Override
            public void onFailure(Call<UploadBean> call, Throwable t) {
                super.onFailure(call, t);
                cancelProgressDlg();
                ToastUtils.showLong("网络连接失败");
            }
        });
    }


    @Override
    protected void loadData() {
        FormBody formBody = new FormBody.Builder()
                .add("uid", PreferenceUtils.getsessionId(this))
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<UserSureName> userSureNameCall = mineApi.person_sure_name(formBody);
        userSureNameCall.enqueue(this);
    }

    @Override
    protected void processData(UserSureName userSureName) {
        goneLoading();
        if (userSureName != null) {
            if (userSureName.getResult() != null) {
                if (userSureName.getResult().getList().size() == 0) {//说明之前没有提交过认证信息

                }
                if (userSureName.getResult().getList().size() > 0) {//apply_state: 0 待处理，1通过，2未通过
                    if (userSureName.getResult().getList().get(0).getData().getApply_state() == 0) {
                        top_line.setVisibility(View.VISIBLE);
                        top_layout.setVisibility(View.GONE);
                        display_layout.setVisibility(View.GONE);
                        tv_commit.setBackgroundColor(Color.parseColor("#e5e5e5"));
                        tv_commit.setTextColor(Color.parseColor("#999999"));
                        tv_commit.setText("审核中");
                        et_name.setText(userSureName.getResult().getList().get(0).getData().getReal_name());
                        et_num.setText(userSureName.getResult().getList().get(0).getData().getVip_card_number() + "");
                        ImageLoader.getInstance().displayImage(userSureName.getResult().getList().get(0).getData().getPic2(), iv_pic_1, ImageOption.default_trip_options);
                        ImageLoader.getInstance().displayImage(userSureName.getResult().getList().get(0).getData().getPic3(), iv_pic_2, ImageOption.default_trip_options);
                        one_layout.setClickable(false);
                        two_layout.setClickable(false);
                        tv_commit.setClickable(false);
                        click_one.setVisibility(View.GONE);
                        click_two.setVisibility(View.GONE);
                        tv_3.setVisibility(View.GONE);
                        tv_4.setVisibility(View.GONE);
                        et_name.setFocusable(false);
                        et_num.setFocusable(false);
                    }

                    if (userSureName.getResult().getList().get(0).getData().getApply_state() == 1) {
                        top_line.setVisibility(View.VISIBLE);
                        top_layout.setVisibility(View.GONE);
                        display_layout.setVisibility(View.GONE);
                        tv_commit.setVisibility(View.GONE);
                        tv_load_time.setVisibility(View.GONE);
                        success_layout.setVisibility(View.VISIBLE);
                        et_name.setText(userSureName.getResult().getList().get(0).getData().getReal_name());
                        et_num.setText(userSureName.getResult().getList().get(0).getData().getVip_card_number() + "");
                        ImageLoader.getInstance().displayImage(userSureName.getResult().getList().get(0).getData().getPic2(), iv_pic_1, ImageOption.default_trip_options);
                        ImageLoader.getInstance().displayImage(userSureName.getResult().getList().get(0).getData().getPic3(), iv_pic_2, ImageOption.default_trip_options);
                        one_layout.setClickable(false);
                        two_layout.setClickable(false);
                        tv_commit.setClickable(false);
                        click_one.setVisibility(View.GONE);
                        click_two.setVisibility(View.GONE);
                        tv_3.setVisibility(View.GONE);
                        tv_4.setVisibility(View.GONE);
                        et_name.setFocusable(false);
                        et_num.setFocusable(false);
                    }

                    if (userSureName.getResult().getList().get(0).getData().getApply_state() == 2) {
                        iv_msg.setImageResource(R.mipmap.icon_not_success);
                        tv_because.setText("XXX原因审核失败, 请重新修改提交审核");
                        tv_1.setVisibility(View.GONE);
                        tv_2.setVisibility(View.GONE);
                    }
                }
            }
        }
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_user_info_state;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("身份认证");
    }
}
