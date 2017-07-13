package com.yuyoubang.activity.mine.business;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.bean.Rigster;
import com.yuyoubang.bean.UploadBean;
import com.yuyoubang.bean.mine.ApplyCompany;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.network.BaseCallback;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.LoginApi;
import com.yuyoubang.network.api.MineApi;
import com.yuyoubang.network.api.UploadApi;
import com.yuyoubang.utils.FileUtil;
import com.yuyoubang.utils.LogUtils;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.ToastUtils;

import java.io.File;

import butterknife.ButterKnife;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 16/12/1.
 */
public class CompanyActivity extends BaseActivity {

    //请求相机
    private static final int REQUEST_CAPTURE = 102;
    //请求相册
    private static final int REQUEST_PICK = 103;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 104;

    private FrameLayout click_upload_pic;
    private File tempFile;
    private String pic_1;
    private ImageView iv_pic;
    private ImageView iv_icon;
    private TextView tv_other;
    private TextView sure_commit;
    private EditText act_join_count;
    private EditText act_price;
    private EditText company_user_name;
    private EditText phone;
    private EditText editText_code;
    private TextView get_code;
    private TimeCount time;

    public static void start(Context context) {
        Intent intent = new Intent(context, CompanyActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("企业用户认证");
    }


    @Override
    protected int getContentResId() {
        return R.layout.act_company;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        createHeadImageTempFile(savedInstanceState);
        initView();

        setListener();

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

    private void setListener() {
        click_upload_pic.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//上传正面
                showPhotoDialog();
            }
        });

        sure_commit.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//确认提交
                String comName = act_join_count.getText().toString().trim();
                String comAddress = act_price.getText().toString().trim();
                String comUserName = company_user_name.getText().toString().trim();
                String moblie = phone.getText().toString().trim();
                String etCode = editText_code.getText().toString().trim();
                if (TextUtils.isEmpty(comName)) {
                    ToastUtils.showShort("请输入公司名字");
                    return;
                }
                if (TextUtils.isEmpty(comAddress)) {
                    ToastUtils.showShort("请输入公司地址");
                    return;
                }
                if (TextUtils.isEmpty(comUserName)) {
                    ToastUtils.showShort("请输入公司法人姓名");
                    return;
                }
                if (TextUtils.isEmpty(pic_1)) {
                    ToastUtils.showShort("请上传公司营业执照");
                    return;
                }
                if (TextUtils.isEmpty(moblie)) {
                    ToastUtils.showShort("请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(etCode)) {
                    ToastUtils.showShort("验证码不能为空");
                    return;
                }
                verificationCode(etCode, moblie, comAddress, comName, comUserName);
            }
        });

        get_code.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//获取验证码
                getCode();
            }
        });
    }

    private void verificationCode(final String code, final String phone,
                                  final String comAddress,
                                  final String comName,
                                  final String comUserName) {
        onShowProgressDlg();
        LoginApi loginApi = RetrofitManager.getTestRetrofit().create(LoginApi.class);
        FormBody body = new FormBody.Builder().add("phone", phone).add("captcha", code).build();
        Call<Rigster> login = loginApi.verificationCode(body);
        login.enqueue(new BaseCallback<Rigster>() {
            @Override
            public void onResponse(Call<Rigster> call, Response<Rigster> response) {
                cancelProgressDlg();
                if (response.body().error_code == 0) {
                    applyCompany(comAddress, comName, phone, comUserName);//申请验证接口
                } else {
                    ToastUtils.showLong(response.body().error_msg);
                }
            }

            @Override
            public void onFailure(Call<Rigster> call, Throwable t) {
                super.onFailure(call, t);
                cancelProgressDlg();
                ToastUtils.showLong("网络连接失败");
                Log.e("RigisterActivity", "测试失败");
            }
        });
    }

    private void applyCompany(String comAddress, String comName, String phone
            , String comUserName) {
        FormBody formBody = new FormBody.Builder()
                .add("business_license_pic", pic_1)
                .add("company_address", comAddress)
                .add("company_name", comName)
                .add("contact_phone", phone)
                .add("contact_user_name", comUserName)
                .add("legal_person", comUserName)
                .add("uid", PreferenceUtils.getsessionId(this))
                .add("user_name", comUserName)
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<ApplyCompany> applyCompanyCall = mineApi.apply_business(formBody);
        applyCompanyCall.enqueue(new Callback<ApplyCompany>() {
            @Override
            public void onResponse(Call<ApplyCompany> call, Response<ApplyCompany> response) {
                ToastUtils.showShort("提交申请完成,等待申请结果");
                finish();
            }

            @Override
            public void onFailure(Call<ApplyCompany> call, Throwable t) {
                ToastUtils.showShort("申请失败");
            }
        });
    }

    private void getCode() {
        String moblie = phone.getText().toString().trim();
        onShowProgressDlg();
        LoginApi loginApi = RetrofitManager.getTestRetrofit().create(LoginApi.class);
        FormBody body = new FormBody.Builder().add("phone", moblie).build();
        Call<Object> login = loginApi.get_code(body);
        login.enqueue(new BaseCallback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                LogUtils.w(response.toString());
                time.start();
                cancelProgressDlg();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                super.onFailure(call, t);
                cancelProgressDlg();
            }
        });
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

    private void initView() {
        act_join_count = getViewById(R.id.act_join_count);
        act_price = getViewById(R.id.act_price);
        company_user_name = getViewById(R.id.company_user_name);
        click_upload_pic = getViewById(R.id.click_upload_pic);
        iv_pic = getViewById(R.id.iv_pic);
        iv_icon = getViewById(R.id.iv_icon);
        tv_other = getViewById(R.id.tv_other);
        phone = getViewById(R.id.phone);
        get_code = getViewById(R.id.get_code);
        editText_code = getViewById(R.id.editText_code);
        sure_commit = getViewById(R.id.sure_commit);
        time = new TimeCount(60000, 1000);
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
                    upLoad(tempFile);
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String path = FileUtil.getRealFilePathFromUri(getApplicationContext(), uri);
                    upLoad(new File(path));
                }
                break;
        }
    }

    private void upLoad(File file) {
        onShowProgressDlg();
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("pic", file.getName() + ".jpg", RequestBody.create(MediaType.parse("image/*"), file));
        UploadApi uploadApi = RetrofitManager.getTestRetrofit().create(UploadApi.class);
        Call<UploadBean> upload = uploadApi.upload(filePart);
        upload.enqueue(new BaseCallback<UploadBean>() {
            @Override
            public void onResponse(Call<UploadBean> call, Response<UploadBean> response) {
                Log.e("LoginInfoActivity", "response=" + response.body().getResult().getBmiddle_pic());
                if (response.body().getError_code() == 0) {
                    pic_1 = response.body().getResult().getBmiddle_pic();
                    tv_other.setVisibility(View.GONE);
                    iv_icon.setVisibility(View.GONE);
                    ImageLoader.getInstance().displayImage(pic_1, iv_pic, ImageOption.default_trip_options);
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

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            get_code.setClickable(false);
            get_code.setText(millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            get_code.setText("重新获取");
            get_code.setClickable(true);

        }
    }
}
