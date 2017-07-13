package com.yuyoubang.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.bean.SetPwd;
import com.yuyoubang.network.BaseCallback;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.LoginApi;
import com.yuyoubang.utils.QLog;
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
public class SetPwdActivity extends BaseActivity {


    @Bind(R.id.tv_pwd_rigster)
    TextView tvPwdRigster;
    @Bind(R.id.edit_pwd)
    EditText editPwd;
    @Bind(R.id.edit_repeat_pwd)
    EditText editRepeatPwd;
    @Bind(R.id.iv_pwd)
    ImageView ivPwd;
    @Bind(R.id.iv_repeat_pwd)
    ImageView ivRepeatPwd;

    private String captcha;
    private String phone;
    private boolean mbDisplayFlg;
    private boolean mbDisplayFlgRepeat;
    private String uid;

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("设置密码");
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_set_pwd;
    }


    public static void start(Context context, String captcha, String phone, String uid) {
        Intent intent = new Intent(context, SetPwdActivity.class);
        intent.putExtra("captcha", captcha);
        intent.putExtra("phone", phone);
        intent.putExtra("uid", uid);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {
        captcha = getIntent().getExtras().getString("captcha");
        phone = getIntent().getExtras().getString("phone");
        uid = getIntent().getExtras().getString("uid");
    }

    private void setPwd(String pwd, String captcha, final String phone) {
        onShowProgressDlg();
        LoginApi loginApi = RetrofitManager.getTestRetrofit().create(LoginApi.class);
        FormBody body = new FormBody.Builder().add("phone", phone).add("captcha", captcha).add("password", pwd).build();
        Call<SetPwd> login = loginApi.setPassWord(body);
        login.enqueue(new BaseCallback<SetPwd>() {
            @Override
            public void onResponse(Call<SetPwd> call, Response<SetPwd> response) {
                cancelProgressDlg();
                QLog.d("SetPwdActivity", response.body().toString() + "");
                if (response.body().isResult()) {
                    Intent intent = new Intent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setClass(SetPwdActivity.this, LoginInfoActivity.class);
                    intent.putExtra("phone", phone);
                    intent.putExtra("uid", uid);
                    startActivity(intent);
//                    qStartActivity(LoginInfoActivity.class, phone);
                } else {
//                    ToastUtils.showLong(response.body().error_msg);
                }

            }

            @Override
            public void onFailure(Call<SetPwd> call, Throwable t) {
                super.onFailure(call, t);
                cancelProgressDlg();
                ToastUtils.showLong(R.string.net_error);
            }
        });
    }


    private void showPwd(EditText edpwd) {
        if (!mbDisplayFlg) {
            edpwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            ivPwd.setBackgroundResource(R.mipmap.bg_close_pwd);
        } else {
            edpwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            ivPwd.setBackgroundResource(R.mipmap.bg_open_pwd);
        }
        mbDisplayFlg = !mbDisplayFlg;
        edpwd.postInvalidate();
    }

    private void showRepeatPwd(EditText edpwd) {
        if (!mbDisplayFlgRepeat) {
            edpwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            ivRepeatPwd.setBackgroundResource(R.mipmap.bg_close_pwd);
        } else {
            edpwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            ivRepeatPwd.setBackgroundResource(R.mipmap.bg_open_pwd);
        }
        mbDisplayFlgRepeat = !mbDisplayFlgRepeat;
        edpwd.postInvalidate();
    }


    @OnClick({R.id.iv_pwd, R.id.iv_repeat_pwd, R.id.tv_pwd_rigster})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_pwd:
                showPwd(editPwd);
                break;
            case R.id.iv_repeat_pwd:
                showRepeatPwd(editRepeatPwd);
                break;
            case R.id.tv_pwd_rigster:
                String pwd = editPwd.getText().toString().trim();
                String repeat = editRepeatPwd.getText().toString().trim();
                if (TextUtils.isEmpty(pwd)) {
                    ToastUtils.showLong("密码不能为空");
                    return;
                }

                if (!pwd.equals(repeat)) {
                    ToastUtils.showLong("两次密码输入的不一样");
                    return;
                }
                setPwd(pwd, captcha, phone);
                break;
        }
    }
}
