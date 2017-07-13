package com.yuyoubang.activity.mine;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.activity.login.LoginAccountActivity;
import com.yuyoubang.activity.login.RigisterActivity;
import com.yuyoubang.activity.login.SetNewPwdActivity;
import com.yuyoubang.activity.login.SetPwdActivity;
import com.yuyoubang.app.YuYouBangApp;
import com.yuyoubang.bean.Rigster;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.BaseCallback;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.LoginApi;
import com.yuyoubang.permission.MPermission;
import com.yuyoubang.permission.OnMPermissionDenied;
import com.yuyoubang.permission.OnMPermissionGranted;
import com.yuyoubang.utils.CommonUtis;
import com.yuyoubang.utils.LogUtils;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.ToastUtils;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hongchen on 16/12/1.
 */

public class SetPhoneTwoActivity extends BaseActivity {

    private final int BASIC_PERMISSION_REQUEST_CODE = 110;

    private TextView tv_company;
    private TextView tv_success;
    private EditText editRigsterPhone;
    private EditText editRigsterCode;
    private TextView tvGetcode;
    private String phone;

    private TimeCount time;
    private String code;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        time = new TimeCount(60000, 1000);
        initView();
        setListener();
        requestBasicPermission();
    }

    private void requestBasicPermission() {
        MPermission.with(SetPhoneTwoActivity.this)
                .addRequestCode(BASIC_PERMISSION_REQUEST_CODE)
                .permissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                )
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @OnMPermissionGranted(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionSuccess() {
        Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
    }

    @OnMPermissionDenied(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionFailed() {
        Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
    }


    private void initView() {
        tv_company = getViewById(R.id.tv_company);
        tv_success = getViewById(R.id.tv_success);
        editRigsterPhone = getViewById(R.id.edit_rigster_phone);
        editRigsterCode = getViewById(R.id.et_login_code);
        tvGetcode = getViewById(R.id.tv_getcode);
    }

    private void setListener() {
        tv_company.setText(PreferenceUtils.getPrefString(this, "phone", ""));

        tvGetcode.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                phone = editRigsterPhone.getText().toString();
                if (checkPhone()) {
                    return;
                }
                getCode();
            }
        });

        tv_success.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                phone = editRigsterPhone.getText().toString();
                code = editRigsterCode.getText().toString();
                if (checkPhone()) {
                    return;
                }
                if (TextUtils.isEmpty(code)) {
                    ToastUtils.showLong("验证码不能为空");
                    return;
                }
                verificationCode(code);
            }
        });
    }

    private void verificationCode(final String code) {
        onShowProgressDlg();
        LoginApi loginApi = RetrofitManager.getTestRetrofit().create(LoginApi.class);
        FormBody body = new FormBody.Builder()
                .add("phone", phone)
                .add("captcha", code)
                .build();
        Call<Rigster> login = loginApi.verify_code(body);
        login.enqueue(new BaseCallback<Rigster>() {
            @Override
            public void onResponse(Call<Rigster> call, Response<Rigster> response) {
                if (response.body().error_code == 0) {
                    verifyChangePhone(phone);
                } else {
                    cancelProgressDlg();
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

    private void verifyChangePhone(String phone) {
        LoginApi loginApi = RetrofitManager.getTestRetrofit().create(LoginApi.class);
        FormBody body = new FormBody.Builder()
                .add("phone", phone)
                .add("uid", PreferenceUtils.getsessionId(this))
                .build();
        Call<Rigster> login = loginApi.update_psw(body);
        login.enqueue(new Callback<Rigster>() {
            @Override
            public void onResponse(Call<Rigster> call, Response<Rigster> response) {
                if (response.body().error_code == 0) {
                    ToastUtils.showLong("修改手机号成功");
                    YuYouBangApp.logOut();
                    qStartActivity(LoginAccountActivity.class);
                    finish();
                } else {
                    ToastUtils.showLong(response.body().error_msg);
                }
            }

            @Override
            public void onFailure(Call<Rigster> call, Throwable t) {
                cancelProgressDlg();
                ToastUtils.showLong("网络连接失败");
                Log.e("RigisterActivity", "测试失败");
            }
        });
    }

    private void getCode() {
        onShowProgressDlg();
        LoginApi loginApi = RetrofitManager.getTestRetrofit().create(LoginApi.class);
        FormBody body = new FormBody.Builder().add("phone", phone).build();
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


    private boolean checkPhone() {
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showLong("手机号不能为空");
            return true;
        }

        if (!CommonUtis.checkPhoneNumber(phone)) {
            ToastUtils.showLong("手机号不正确");
            return true;
        }
        return false;
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tvGetcode.setClickable(false);
            tvGetcode.setText(millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            tvGetcode.setText("重新获取");
            tvGetcode.setClickable(true);

        }
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_set_phone_two;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("修改手机号");
        builder.setBackGround(Color.WHITE);
        builder.setTitleColor(Color.BLACK);
        builder.setLeftOperate(R.mipmap.back_black, new OnClickListener() {
            @Override
            protected void clickOperate() {
                finish();
            }
        });
    }
}
