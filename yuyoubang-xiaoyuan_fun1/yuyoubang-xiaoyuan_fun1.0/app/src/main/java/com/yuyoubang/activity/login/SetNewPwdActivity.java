package com.yuyoubang.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.activity.home.HomeActivity;
import com.yuyoubang.app.AppManager;
import com.yuyoubang.bean.SetPwd;
import com.yuyoubang.network.BaseCallback;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.LoginApi;
import com.yuyoubang.utils.PreferenceUtils;
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
public class SetNewPwdActivity extends BaseActivity {


    @Bind(R.id.edit_new_pwd)
    EditText editNewPwd;
    @Bind(R.id.login_layout)
    LinearLayout loginLayout;
    @Bind(R.id.edit_repeat_pwd)
    EditText editRepeatPwd;
    @Bind(R.id.tv_sure)
    TextView tvSure;
    private String captcha;
    private String phone;

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("找回密码");
    }

    public static void start(Context context, String captcha, String phone) {
        Intent intent = new Intent(context, SetNewPwdActivity.class);
        intent.putExtra("captcha", captcha);
        intent.putExtra("phone", phone);
        context.startActivity(intent);
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_set_new_pwd;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initView();
        mHeaderBuilder.setTitle("找回密码");
    }

    private void initView() {
        captcha = getIntent().getExtras().getString("captcha");
        phone = getIntent().getExtras().getString("phone");
    }

    private void setPwd(String pwd, String captcha, String phone) {
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
                    ToastUtils.showShort("修改成功,请重新登录");
                    qStartActivity(LoginAccountActivity.class);
                    AppManager.getAppManager().finishAllActivity();
                } else {
                    ToastUtils.showShort("修改失败,请重试");
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


    @OnClick(R.id.tv_sure)
    public void onClick() {
        String pwd = editNewPwd.getText().toString().trim();
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
    }
}
