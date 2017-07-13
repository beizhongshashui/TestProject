package com.yuyoubang.activity.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.activity.home.HomeActivity;
import com.yuyoubang.activity.mine.order.EditorChooseCityActivity;
import com.yuyoubang.activity.picker.ClipImageActivity;
import com.yuyoubang.app.AppManager;
import com.yuyoubang.bean.LoginUserBean;
import com.yuyoubang.bean.RigsterUserBean;
import com.yuyoubang.bean.UploadBean;
import com.yuyoubang.network.BaseCallback;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.LoginApi;
import com.yuyoubang.network.api.UploadApi;
import com.yuyoubang.otto.AppBus;
import com.yuyoubang.utils.FileUtil;
import com.yuyoubang.utils.LocationUtil;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.view.CircleImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
 * Created by xiaoyuan on 16/11/7.
 */
public class LoginInfoActivity extends BaseActivity {
    //请求相机
    private static final int REQUEST_CAPTURE = 102;
    //请求相册
    private static final int REQUEST_PICK = 103;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 104;
    @Bind(R.id.iv_head)
    CircleImageView ivHead;
    @Bind(R.id.ed_nn)
    EditText edNn;
    @Bind(R.id.tv_man)
    TextView tvMan;
    @Bind(R.id.tv_woman)
    TextView tvWoman;
    @Bind(R.id.tv_birth)
    TextView tvBirth;
    @Bind(R.id.tv_location)
    TextView tvLocation;
    @Bind(R.id.tv_start)
    TextView tvStart;

    private String profile_pic_url;
    private String user_location = "";
    private String birthday;
    private String user_name;


    private File tempFile;
    private int gender = -1;
    private Calendar c;
    private int year;
    private int month;
    private int day;

    private boolean isLocation;
    private String phone;
    private String uid;
    private String province_code = "";
    private String city_code = "";
    private String province = "";

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("完善资料");
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_login_info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        phone = getIntent().getStringExtra("phone");
        uid = getIntent().getStringExtra("uid");
        createHeadImageTempFile(savedInstanceState);
        c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
//        GradientDrawable myGrad = (GradientDrawable) tvMan.getBackground();
//        myGrad.setColor(Color.argb(255, 249, 122, 3));
        tvMan.setBackgroundResource(R.drawable.grey_circle);
        tvWoman.setBackgroundResource(R.drawable.grey_circle);
        LocationUtil.startLocation(mLocationListener);
        AppBus.getInstance().register(this);

    }

    AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (!TextUtils.isEmpty(aMapLocation.getCity())) {
                Message message = Message.obtain();
                message.obj = aMapLocation;
                handler.sendMessage(message);
            }


        }
    };


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            AMapLocation aMapLocation = (AMapLocation) msg.obj;
            if (!isLocation) {
                tvLocation.setText(aMapLocation.getCity());
                isLocation = true;
            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppBus.getInstance().unregister(this);
    }

    @OnClick({R.id.iv_head, R.id.tv_man, R.id.tv_woman, R.id.tv_birth, R.id.tv_location, R.id.tv_start})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                showPhotoDialog();
                break;
            case R.id.tv_man:
                tvWoman.setBackgroundResource(R.drawable.grey_circle);
                tvWoman.setTextColor(getResources().getColor(R.color.color_000000));
                tvMan.setTextColor(getResources().getColor(R.color.color_ffffff));
                tvMan.setBackgroundResource(R.drawable.bulue_circle);
                gender = 0;
                break;
            case R.id.tv_woman:
                tvMan.setBackgroundResource(R.drawable.grey_circle);
                tvMan.setTextColor(getResources().getColor(R.color.color_000000));
                tvWoman.setTextColor(getResources().getColor(R.color.color_ffffff));
                tvWoman.setBackgroundResource(R.drawable.bulue_circle);
                gender = 1;
                break;
            case R.id.tv_birth:
                showDate();
                break;
            case R.id.tv_location:
                /*Intent intent = new Intent(this, ChooseCityActivity.class);
                startActivity(intent);*/
                Intent intent = new Intent(LoginInfoActivity.this, EditorChooseCityActivity.class);
                startActivityForResult(intent, 200);
                break;
            case R.id.tv_start:
                user_name = edNn.getText().toString();
                if (TextUtils.isEmpty(user_name)) {
                    ToastUtils.showLong("请输入名字");
                    return;
                }
                if (TextUtils.isEmpty(profile_pic_url)) {
                    ToastUtils.showLong("请选择头像");
                    return;
                }

                if (gender == -1) {
                    ToastUtils.showLong("请选择性别");
                    return;
                }
//                if (TextUtils.isEmpty(user_location)) {
//                    ToastUtils.showLong("请选择地点");
//                    return;
//                }
                if (TextUtils.isEmpty(birthday)) {
                    ToastUtils.showLong("请选择出生日期");
                    return;
                }

                if (TextUtils.isEmpty(province_code) || TextUtils.isEmpty(city_code)) {
                    ToastUtils.showShort("请选择城市");
                    return;
                }
                showDialog();

                break;
        }
    }

    private void setUserInfo(String uid, String profile_pic_url, String gender, String user_location, String birthday, String user_name) {
        onShowProgressDlg();
        Log.e("LoginInfoActivity", uid + "=" + profile_pic_url + "=" + gender + "=" + user_location + "=" + birthday + "=" + user_name);
        LoginApi loginApi = RetrofitManager.getTestRetrofit().create(LoginApi.class);
        FormBody body = new FormBody.Builder()
                .add("uid", uid)
                .add("profile_pic_url", profile_pic_url)
                .add("gender", gender)
                .add("user_location", user_location)
                .add("province", province)
                .add("birthday", birthday)
                .add("user_name", user_name)
                .add("province_id", province_code)//省id
                .add("location_id", city_code)//市id
                .build();
        Call<RigsterUserBean> login = loginApi.setUserInfo(body);
        login.enqueue(new BaseCallback<RigsterUserBean>() {
            @Override
            public void onResponse(Call<RigsterUserBean> call, Response<RigsterUserBean> response) {
                Log.e("LoginInfoActivity", response.body().getError_code() + "");
                cancelProgressDlg();
                if (response.body().getError_code() == 0) {
                    ToastUtils.showShort("登录成功");
                    Log.e("LoginAccountActivity", response.body().getResult().get(0).getId() + "");
                    PreferenceUtils.setPrefString(LoginInfoActivity.this, "phone", phone);
                    PreferenceUtils.setPrefString(LoginInfoActivity.this, "user_name", response.body().getResult().get(0).getUser_data().getUser_name());
                    PreferenceUtils.putsessionId(response.body().getResult().get(0).getId() + "");
                    try {
                        if (response.body().getResult().get(0).getUser_data() != null) {
                            LoginUserBean.ResultBean.UserDataBean userDataBean = new LoginUserBean.ResultBean.UserDataBean();
                            userDataBean.setBirthday(Long.valueOf(response.body().getResult().get(0).getUser_data().getBirthday()));
                            userDataBean.setGender(Integer.parseInt(response.body().getResult().get(0).getUser_data().getGender()));
                            userDataBean.setIs_verification(response.body().getResult().get(0).getUser_data().getIs_verification());
                            userDataBean.setProfile_pic_url(response.body().getResult().get(0).getUser_data().getProfile_pic_url());
                            userDataBean.setState(response.body().getResult().get(0).getUser_data().getState());
                            userDataBean.setUser_group(response.body().getResult().get(0).getUser_data().getUser_group());
                            userDataBean.setUser_location(response.body().getResult().get(0).getUser_data().getUser_location());
                            userDataBean.setUser_name(response.body().getResult().get(0).getUser_data().getUser_name());

                            if (userDataBean != null) {
                                PreferenceUtils.setPrefString(LoginInfoActivity.this, "user", PreferenceUtils.serialize(userDataBean));
                            }
                        } else {
                            ToastUtils.showShort("账号异常");
                            return;
                        }
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                    AppManager.getAppManager().finishAllActivity();
                    qStartActivity(HomeActivity.class);
                } else {
                    ToastUtils.showLong(response.body().getError_code());
                }

            }

            @Override
            public void onFailure(Call<RigsterUserBean> call, Throwable t) {
                super.onFailure(call, t);
                cancelProgressDlg();
                ToastUtils.showLong(R.string.net_error);
            }
        });
    }


    private void showDate() {
        DatePickerDialog dd = new DatePickerDialog(LoginInfoActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        try {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                            String dateInString = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
                            Date date = formatter.parse(dateInString);
                            tvBirth.setText(formatter.format(date).toString());
                            birthday = date.getTime() + "";

                        } catch (Exception ex) {

                        }


                    }
                }, year, month, day);
        dd.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == 200) {
            province_code = data.getStringExtra("province_code");
            city_code = data.getStringExtra("city_code");
            user_location = data.getStringExtra("user_location");
            province = data.getStringExtra("province");
            tvLocation.setText(user_location);
        }
        switch (requestCode) {

            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = data.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = FileUtil.getRealFilePathFromUri(getApplicationContext(), uri);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                    File cacheDir = StorageUtils.getOwnCacheDirectory(this,
                            "yuyoubang/Cache");
                    String fileName = cacheDir.getPath() + "/"
                            + "pic" + System.currentTimeMillis();
                    FileOutputStream mFileOutputStream = null;
                    ivHead.setImageBitmap(bitMap);

                    try {
                        mFileOutputStream = new FileOutputStream(fileName);
                        bitMap.compress(Bitmap.CompressFormat.JPEG, 100,
                                mFileOutputStream);// 把数据写入文件
                        File file = new File(fileName);
                        upLoad(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            mFileOutputStream.flush();
                            mFileOutputStream.close();
//                                    bitmap.recycle();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                }
        }
    }


    private void upLoad(File file) {
        Log.e("LoginInfoActivity", "file.name=" + file.getName());
        onShowProgressDlg();
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("pic", file.getName() + ".jpg", RequestBody.create(MediaType.parse("image/*"), file));
        UploadApi uploadApi = RetrofitManager.getTestRetrofit().create(UploadApi.class);
        Call<UploadBean> upload = uploadApi.upload(filePart);
        upload.enqueue(new BaseCallback<UploadBean>() {
            @Override
            public void onResponse(Call<UploadBean> call, Response<UploadBean> response) {
                Log.e("LoginInfoActivity", "response=" + response.body().getResult().getBmiddle_pic());
                if (response.body().getError_code() == 0) {
                    profile_pic_url = response.body().getResult().getBmiddle_pic();
                } else {
//                    ToastUtils.showLong(response.body().error_msg);
                }
                cancelProgressDlg();

            }

            @Override
            public void onFailure(Call<UploadBean> call, Throwable t) {
                super.onFailure(call, t);
                cancelProgressDlg();
                ToastUtils.showLong("网络连接失败");
                Log.e("RigisterActivity", "测试失败");
            }
        });
    }


    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.putExtra("type", 1);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
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


    private void getPhoto(AlertDialog dlg) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
        dlg.cancel();
    }

    private void getCamera(AlertDialog dlg) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        startActivityForResult(intent, REQUEST_CAPTURE);
        dlg.cancel();
    }

    private void createHeadImageTempFile(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("tempFile")) {
            tempFile = (File) savedInstanceState.getSerializable("tempFile");
        } else {
            tempFile = new File(FileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"),
                    System.currentTimeMillis() + ".jpg");
        }
    }


    private void showDialog() {

        new android.support.v7.app.AlertDialog.Builder(LoginInfoActivity.this).setCancelable(true)
                .setTitle("昵称、性别、注册确认后无法更改, 是否确认? ")
//                .setView(editText)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        setUserInfo(uid, profile_pic_url, gender + "", "北京", birthday, user_name);
                    }
                }).show();
    }

    /*@Subscribe
    public void city(CityEvent cityEvent) {
        tvLocation.setText(cityEvent.city + "");
    }*/

}