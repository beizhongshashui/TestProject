package com.yuyoubang.activity.login;

import android.Manifest;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.bean.Rigster;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 16/11/7.
 */
public class FindPwdActivity extends BaseActivity {

    private final int BASIC_PERMISSION_REQUEST_CODE = 110;

    @Bind(R.id.edit_find_phone)
    EditText editFindPhone;
    @Bind(R.id.tv_getcode)
    TextView tvGetcode;
    @Bind(R.id.edit_find_code)
    EditText editFindCode;
    @Bind(R.id.tv_next)
    TextView tvNext;

    private String phone;
    private String code;
    private FindPwdActivity.TimeCount time;

    @Override
    protected void initTitleBar(HeaderBuilder builder) {

    }

    @Override
    protected int getContentResId() {
        return R.layout.act_find_pwd;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initViews();
        requestBasicPermission();


    }

    private void requestBasicPermission() {
        MPermission.with(FindPwdActivity.this)
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

    private void initViews() {
        mHeaderBuilder.setTitle("找回密码");
        time = new FindPwdActivity.TimeCount(60000, 1000);
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

    private void verificationCode(final String code) {
        onShowProgressDlg();
        LoginApi loginApi = RetrofitManager.getTestRetrofit().create(LoginApi.class);
        FormBody body = new FormBody.Builder().add("phone", phone).add("captcha", code).build();
        Call<Rigster> login = loginApi.verificationCode(body);
        login.enqueue(new BaseCallback<Rigster>() {
            @Override
            public void onResponse(Call<Rigster> call, Response<Rigster> response) {

                cancelProgressDlg();
                if (response.body().error_code == 0) {
                    PreferenceUtils.putsessionId(response.body().getUser().getId() + "");
//                    qStartActivity(SetNewPwdActivity.class);
                    SetNewPwdActivity.start(FindPwdActivity.this, code,phone);
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


    @OnClick({R.id.tv_getcode, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_getcode:
                phone = editFindPhone.getText().toString();
                if (checkPhone()) {
                    return;
                }

                getCode();
                break;
            case R.id.tv_next:
                phone = editFindPhone.getText().toString();
                code = editFindCode.getText().toString();
                if (checkPhone()) {
                    return;
                }
                if (TextUtils.isEmpty(code)) {
                    ToastUtils.showLong("验证码不能为空");
                    return;
                }
                verificationCode(code);
                break;
        }
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
}
