package com.yuyoubang.activity.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.activity.home.HomeActivity;
import com.yuyoubang.activity.login.LoginAccountActivity;
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

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by hongchen on 16/12/1.
 */

public class SetPhoneActivity extends BaseActivity {


    private TextView tv_next;
    private EditText et_old_psw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
    }

    private void initView() {
        tv_next = getViewById(R.id.tv_next);
        et_old_psw = getViewById(R.id.et_old_psw);
    }

    private void setListener() {
        tv_next.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                String phone = PreferenceUtils.getPrefString(SetPhoneActivity.this, "phone", "");
                if (TextUtils.isEmpty(phone)) {
                    return;
                }
                String psw = et_old_psw.getText().toString().trim();
                if (TextUtils.isEmpty(psw)) {
                    ToastUtils.showShort("密码不能为空");
                    return;
                }
                requestNext(phone, psw);
            }
        });
    }

    private void requestNext(String phone, String psw) {
        onShowProgressDlg();
        LoginApi loginApi = RetrofitManager.getTestRetrofit().create(LoginApi.class);
        FormBody body = new FormBody.Builder()
                .add("phone", phone)
                .add("password", psw)
                .build();
        Call<LoginUserBean> login = loginApi.login(body);
        Log.e("LoginAccountActivity", body.toString() + "");
        login.enqueue(new BaseCallback<LoginUserBean>() {
            @Override
            public void onResponse(Call<LoginUserBean> call, Response<LoginUserBean> response) {
                Log.e("LoginAccountActivity", response.body() + "");
                cancelProgressDlg();
                if (response.body().error_code == 0) {
                    LaunchOperate.openSetPhoneTwoActivity(SetPhoneActivity.this);
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

    @Override
    protected int getContentResId() {
        return R.layout.act_set_phone;
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
