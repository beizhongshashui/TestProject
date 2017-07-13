package com.yuyoubang.activity.mine;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.squareup.otto.Subscribe;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseNetActivity;
import com.yuyoubang.activity.home.ChooseCityActivity;
import com.yuyoubang.activity.mine.order.EditorChooseCityActivity;
import com.yuyoubang.activity.picker.ClipImageActivity;
import com.yuyoubang.activity.push.SetDateActivity;
import com.yuyoubang.bean.NewTrip;
import com.yuyoubang.bean.SystemTripBean;
import com.yuyoubang.bean.UploadBean;
import com.yuyoubang.bean.findfollowinfo.OtherHome;
import com.yuyoubang.bean.mine.UpdateUserInfo;
import com.yuyoubang.config.UrlConfig;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.network.BaseCallback;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.FindApi;
import com.yuyoubang.network.api.MineApi;
import com.yuyoubang.network.api.UploadApi;
import com.yuyoubang.otto.AppBus;
import com.yuyoubang.otto.CityEvent;
import com.yuyoubang.otto.EditorCityEvent;
import com.yuyoubang.utils.FileUtil;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.LogUtils;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.TimeUtil;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.view.RoundImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hongchen on 16/12/1.
 */

public class EditorActivity extends BaseNetActivity<OtherHome> {

    //请求相机
    private static final int REQUEST_CAPTURE = 102;
    //请求相册
    private static final int REQUEST_PICK = 103;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 104;
    private String profile_pic_url = "";
    private RoundImageView iv_find_info_avtor;
    private File tempFile;
    private LinearLayout sign;
    private TextView user_name;
    private TextView tv_city;
    private TextView user_sex;
    private TextView user_tags;
    private TextView user_sign;
    private TextView user_birth;
    private Calendar c;
    private int year;
    private int month;
    private int day;
    private int gender;
    private String birthday;
    private String user_location = "";
    private String user_desc = "";
    private List<String> userTags = new ArrayList<>();
    private String tags = "";
    private String province_code = "";
    private String city_code = "";
    private String province = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createHeadImageTempFile(savedInstanceState);
        AppBus.getInstance().register(this);
        c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initViews() {
        iv_find_info_avtor = getViewById(R.id.iv_find_info_avtor);
        user_name = getViewById(R.id.user_name);
        tv_city = getViewById(R.id.tv_city);
        user_sex = getViewById(R.id.user_sex);
        user_tags = getViewById(R.id.user_tags);
        user_sign = getViewById(R.id.user_sign);
        user_birth = getViewById(R.id.user_birth);
        sign = getViewById(R.id.sign);

        ImageLoader.getInstance().displayImage(UrlConfig.TempPic, iv_find_info_avtor, ImageOption.defaultOptions);

        iv_find_info_avtor.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//更改头像
                showPhotoDialog();
            }
        });

        sign.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//个人签名
                LaunchOperate.openMineSignActivity(EditorActivity.this, 1001);
            }
        });

        user_birth.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//生日
                showDate(user_birth);
            }
        });

        tv_city.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//城市
                Intent intent = new Intent(EditorActivity.this, EditorChooseCityActivity.class);
                startActivityForResult(intent, 100);
            }
        });

        user_tags.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//标签
                LaunchOperate.openAddTagsActivity(EditorActivity.this, userTags, 1002);
            }
        });
    }

    private void showDate(final TextView tv) {
        DatePickerDialog dd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        try {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
                            String dateInString = year + "." + (monthOfYear + 1) + "." + dayOfMonth;
                            Date date = formatter.parse(dateInString);
                            birthday = TimeUtil.getTime(formatter.format(date));
                            tv.setText(formatter.format(date));
                        } catch (Exception ex) {

                        }
                    }
                }, year, month, day);
        dd.show();
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


    @Override
    protected void loadData() {
        FormBody formBody = new FormBody.Builder()
                .add("uid", PreferenceUtils.getsessionId(this))
                .add("to_uid", PreferenceUtils.getsessionId(this))
                .build();
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        Call<OtherHome> otherHomeCall = findApi.user_info_show(formBody);
        otherHomeCall.enqueue(this);
    }

    @Override
    protected void processData(OtherHome otherHome) {
        goneLoading();
        if (otherHome != null) {
            if (otherHome.getResult() != null) {
                if (otherHome.getResult().getUser().get(0).getUser_data().getProfile_pic_url() != null) {
                    profile_pic_url = otherHome.getResult().getUser().get(0).getUser_data().getProfile_pic_url();
                    ImageLoader.getInstance().displayImage(profile_pic_url, iv_find_info_avtor, ImageOption.defaultOptions);
                }
                user_name.setText(otherHome.getResult().getUser().get(0).getUser_data().getUser_name());
                if (!TextUtils.isEmpty(otherHome.getResult().getUser().get(0).getUser_data().getGender())) {
                    gender = Integer.valueOf(otherHome.getResult().getUser().get(0).getUser_data().getGender());
                    if (Integer.valueOf(otherHome.getResult().getUser().get(0).getUser_data().getGender()) == 0) {
                        user_sex.setText("男");
                    } else {
                        user_sex.setText("女");
                    }
                }
                birthday = String.valueOf(otherHome.getResult().getUser().get(0).getUser_data().getBirthday());
                if (otherHome.getResult().getUser().get(0).getUser_data().getUser_location() != null) {
                    user_location = otherHome.getResult().getUser().get(0).getUser_data().getUser_location();
                    tv_city.setText(otherHome.getResult().getUser().get(0).getUser_data().getUser_location());
                }
                if (otherHome.getResult().getUser().get(0).getUser_data().getUser_desc() != null) {
                    user_desc = otherHome.getResult().getUser().get(0).getUser_data().getUser_desc();
                    user_sign.setText(user_desc);
                }
                if (otherHome.getResult().getUser().get(0).getUser_data().getUser_tags() != null) {
                    userTags = otherHome.getResult().getUser().get(0).getUser_data().getUser_tags();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < userTags.size(); i++) {
                        stringBuffer.append(userTags.get(i)).append(",");
                    }
                    if (TextUtils.isEmpty(stringBuffer)) {
                        tags = "";
                    } else {
                        tags = stringBuffer.substring(0, stringBuffer.length() - 1);
                    }
                    user_tags.setText(tags);
                }
                String birth = TimeUtil.getDateString(Long.valueOf(birthday));
                user_birth.setText(birth);
            }
        }
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_editor;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("编辑个人页面");
        builder.setBackGround(Color.WHITE);
        builder.setTitleColor(Color.BLACK);
        builder.setLeftOperate(R.mipmap.back_black, new OnClickListener() {
            @Override
            protected void clickOperate() {
                finish();
            }
        });
        builder.setRightTvColor(R.color.color_ff9600);
        builder.setRightTvText("保存", new OnClickListener() {
            @Override
            protected void clickOperate() {
                updateUserInfo();//更新用户信息接口
            }
        });
    }

    private void updateUserInfo() {
        /*if (TextUtils.isEmpty(profile_pic_url)) {
            ToastUtils.showShort("头像不能为空");
            return;
        }*/
        FormBody formBody = null;
        if (!TextUtils.isEmpty(province_code) && !TextUtils.isEmpty(city_code) && !TextUtils.isEmpty(province)) {
            formBody = new FormBody.Builder()
                    .add("uid", PreferenceUtils.getsessionId(this))
                    .add("profile_pic_url", profile_pic_url)
                    .add("gender", gender + "")//性别
                    .add("birthday", birthday)//生日
                    .add("user_location", user_location)//用户所在地
                    .add("user_desc", user_desc)//用户描述
                    .add("user_tags", tags)//用户标签，用半角英文,分割的标签名字字符串
                    .add("province_id", province_code)//省id
                    .add("location_id", city_code)//市id
                    .add("province", province)//用户所在省
                    .build();
        } else {
            formBody = new FormBody.Builder()
                    .add("uid", PreferenceUtils.getsessionId(this))
                    .add("profile_pic_url", profile_pic_url)
                    .add("gender", gender + "")//性别
                    .add("birthday", birthday)//生日
                    .add("user_location", user_location)//用户所在地
                    .add("user_desc", user_desc)//用户描述
                    .add("user_tags", tags)//用户标签，用半角英文,分割的标签名字字符串
                    .build();
        }

        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<UpdateUserInfo> updateUserInfoCall = mineApi.update_user_info(formBody);
        updateUserInfoCall.enqueue(new Callback<UpdateUserInfo>() {
            @Override
            public void onResponse(Call<UpdateUserInfo> call, Response<UpdateUserInfo> response) {
                LogUtils.e("UpdateUserInfo" + response.body());
                ToastUtils.showShort("更新资料成功");
                finish();
            }

            @Override
            public void onFailure(Call<UpdateUserInfo> call, Throwable t) {
                ToastUtils.showShort("更新资料失败");
            }
        });
    }

    private void createHeadImageTempFile(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("tempFile")) {
            tempFile = (File) savedInstanceState.getSerializable("tempFile");
        } else {
            tempFile = new File(FileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"),
                    System.currentTimeMillis() + ".jpg");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == 100) {
            province_code = data.getStringExtra("province_code");
            city_code = data.getStringExtra("city_code");
            user_location = data.getStringExtra("user_location");
            province = data.getStringExtra("province");
            tv_city.setText(user_location);
        }
        if (requestCode == 1001) {
            user_desc = data.getStringExtra("mine_sign");
            if (!TextUtils.isEmpty(user_desc)) {
                user_sign.setText(user_desc);
            }
        }
        if (requestCode == 1002) {
            tags = data.getStringExtra("tags");
            if (!TextUtils.isEmpty(tags)) {
                user_tags.setText(tags);
            }
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
                    iv_find_info_avtor.setImageBitmap(bitMap);

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppBus.getInstance().unregister(this);
    }
}
