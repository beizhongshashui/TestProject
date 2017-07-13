package com.yuyoubang.activity.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.activity.login.LoginAccountActivity;
import com.yuyoubang.app.YuYouBangApp;
import com.yuyoubang.bean.mine.FixPsw;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.MineApi;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.ToastUtils;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hongchen on 16/12/1.
 */

public class FixPasswordActivity extends BaseActivity {

    private EditText et_old_psw;
    private EditText et_new_psw;
    private EditText et_again_psw;
    private TextView tv_sure;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
    }

    private void initView() {
        et_old_psw = getViewById(R.id.et_old_psw);
        et_new_psw = getViewById(R.id.et_new_psw);
        et_again_psw = getViewById(R.id.et_again_psw);
        tv_sure = getViewById(R.id.tv_sure);
    }

    private void setListener() {
        tv_sure.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//更换密码
                String old_psw = et_old_psw.getText().toString().trim();
                String new_psw = et_new_psw.getText().toString().trim();
                String again_psw = et_again_psw.getText().toString().trim();
                if (TextUtils.isEmpty(old_psw)) {
                    ToastUtils.showShort("旧密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(new_psw)) {
                    ToastUtils.showShort("新密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(again_psw)) {
                    ToastUtils.showShort("新密码不能为空");
                    return;
                }
                if (!new_psw.equals(again_psw)) {
                    ToastUtils.showShort("新密码2次输入不一致");
                    return;
                }
                changePsw(old_psw, new_psw, again_psw);
            }
        });
    }

    private void changePsw(String old_psw, String new_psw, String again_psw) {
        if (TextUtils.isEmpty(PreferenceUtils.getsessionId(this))) {
            ToastUtils.showShort("网络好像不对哦");
            return;
        }
        FormBody formBody = new FormBody.Builder()
                .add("old_password", old_psw)
                .add("password", new_psw)
                .add("confirm_pwd", again_psw)
                .add("uid", PreferenceUtils.getsessionId(this))
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<FixPsw> fixPswCall = mineApi.user_fix_psw(formBody);
        fixPswCall.enqueue(new Callback<FixPsw>() {
            @Override
            public void onResponse(Call<FixPsw> call, Response<FixPsw> response) {
                FixPsw fixPsw = response.body();
                if (fixPsw.getError_code() == 0) {
                    ToastUtils.showShort("更新密码成功,快去登录吧");
                    YuYouBangApp.logOut();
                    qStartActivity(LoginAccountActivity.class);
                    finish();
                } else {
                    ToastUtils.showShort("请正确输入您的旧密码");
                }
            }

            @Override
            public void onFailure(Call<FixPsw> call, Throwable t) {
                ToastUtils.showShort("网络好像不对哦");
            }
        });
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_fix_password;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("修改密码");
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
