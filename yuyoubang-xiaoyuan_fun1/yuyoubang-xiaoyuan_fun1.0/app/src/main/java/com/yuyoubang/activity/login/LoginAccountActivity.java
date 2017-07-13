package com.yuyoubang.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.activity.home.HomeActivity;
import com.yuyoubang.app.AppManager;
import com.yuyoubang.bean.LoginUserBean;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.BaseCallback;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.LoginApi;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.ToastUtils;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 16/11/7.
 */
public class LoginAccountActivity extends BaseActivity {

    @Bind(R.id.edit_account)
    EditText editAccount;
    @Bind(R.id.edit_pwd)
    EditText editPwd;
    @Bind(R.id.tv_login)
    TextView tvLogin;
    @Bind(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @Bind(R.id.iv_login_pwd)
    ImageView ivPwd;
    private boolean mbDisplayFlgRepeat = false;

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setLeftOperate(R.mipmap.white_back_icon, new OnClickListener() {
            @Override
            protected void clickOperate() {
                LaunchOperate.openLoginActivity(LoginAccountActivity.this);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LaunchOperate.openLoginActivity(LoginAccountActivity.this);
        AppManager.getAppManager().finishAllActivity();
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_account;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mHeaderBuilder.setTitle("登录");
        mHeaderBuilder.setRightTvText("注册", new OnClickListener() {
            @Override
            protected void clickOperate() {
                qStartActivity(RigisterActivity.class);
                finish();
            }
        });
        mHeaderBuilder.setRightTvColor(R.color.color_ff9600);

    }


    protected void loadData(final String phone, String pwd) {
        onShowProgressDlg();
        LoginApi loginApi = RetrofitManager.getTestRetrofit().create(LoginApi.class);
        FormBody body = new FormBody.Builder().add("phone", phone).add("password", pwd).build();
        Call<LoginUserBean> login = loginApi.login(body);
        Log.e("LoginAccountActivity", body.toString() + "");
        login.enqueue(new BaseCallback<LoginUserBean>() {
            @Override
            public void onResponse(Call<LoginUserBean> call, Response<LoginUserBean> response) {
                Log.e("LoginAccountActivity", response.body() + "");
                cancelProgressDlg();
                if (response.body().error_code == 0) {
                    ToastUtils.showShort("登录成功");
                    Log.e("LoginAccountActivity", response.body().getResult().getId() + "");
                    PreferenceUtils.setPrefString(LoginAccountActivity.this, "phone", phone);
                    PreferenceUtils.setPrefString(LoginAccountActivity.this, "user_name", response.body().getResult().getUser_data().getUser_name());
                    PreferenceUtils.putsessionId(response.body().getResult().getId() + "");
                    try {
                        if (response.body().getResult().getUser_data() != null) {
                            PreferenceUtils.setPrefString(LoginAccountActivity.this, "user", PreferenceUtils.serialize(response.body().getResult().getUser_data()));
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
                    ToastUtils.showShort(response.body().error_msg);
                }
            }

            @Override
            public void onFailure(Call<LoginUserBean> call, Throwable t) {
                super.onFailure(call, t);
                cancelProgressDlg();
                ToastUtils.showLong(R.string.net_error);
            }
        });
    }


    @OnClick({R.id.tv_login, R.id.tv_forget_pwd, R.id.iv_login_pwd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                String accout = editAccount.getText().toString().trim();
                String pwd = editPwd.getText().toString().trim();
                if (checkAccount(accout, pwd)) return;
                loadData(accout, pwd);
                break;
            case R.id.tv_forget_pwd:
                qStartActivity(FindPwdActivity.class);
                break;
            case R.id.iv_login_pwd:
                showPwd(editPwd);
                break;
        }
    }

    private void showPwd(EditText edpwd) {
        if (!mbDisplayFlgRepeat) {
            edpwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            ivPwd.setBackgroundResource(R.mipmap.bg_close_pwd);
        } else {
            edpwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            ivPwd.setBackgroundResource(R.mipmap.bg_open_pwd);
        }
        mbDisplayFlgRepeat = !mbDisplayFlgRepeat;
        edpwd.postInvalidate();
    }

    private boolean checkAccount(String accout, String pwd) {
        if (TextUtils.isEmpty(accout)) {
            ToastUtils.showLong("账号不能为空");
            return true;
        }
        if (TextUtils.isEmpty(pwd)) {
            ToastUtils.showLong("密码不能为空");
            return true;
        }
        return false;
    }
}
