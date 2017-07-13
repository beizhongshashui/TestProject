package com.yuyoubang.activity.login;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.app.AppManager;
import com.yuyoubang.utils.StatusBarUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xiaoyuan on 16/11/7.
 */
public class LoginActivity extends BaseActivity {

    @Bind(R.id.btn_rigster)
    TextView btnRigster;
    @Bind(R.id.btn_login)
    TextView btnLogin;

    @Override
    protected void initTitleBar(HeaderBuilder builder) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AppManager.getAppManager().finishAllActivity();
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_login;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        mHeaderBuilder.goneToolbar();
        btnRigster.getBackground().setAlpha(150);
        btnLogin.getBackground().setAlpha(150);
        StatusBarUtil.setColor(LoginActivity.this,getResources().getColor(R.color.color_f7f7f7));
    }

    @OnClick({R.id.btn_rigster, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_rigster:
                qStartActivity(RigisterActivity.class);
                break;
            case R.id.btn_login:
                qStartActivity(LoginAccountActivity.class);
                break;
        }
    }
}
