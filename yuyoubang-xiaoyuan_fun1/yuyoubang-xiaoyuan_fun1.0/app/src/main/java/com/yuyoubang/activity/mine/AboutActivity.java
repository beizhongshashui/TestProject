package com.yuyoubang.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.app.GlobalParams;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.utils.CallUtil;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.VersionUtils;

/**
 * Created by hongchen on 16/11/30.
 */

public class AboutActivity extends BaseActivity {

    private TextView tv_feedback;
    private TextView tv_serve;
    private TextView tv_version;
    private TextView tv_customer;
    private TextView tv_mark;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
    }

    private void initView() {
        tv_feedback = getViewById(R.id.tv_feedback);
        tv_serve = getViewById(R.id.tv_serve);
        tv_version = getViewById(R.id.tv_version);
        tv_customer = getViewById(R.id.tv_customer);
        tv_mark = getViewById(R.id.tv_mark);
    }

    private void setListener() {
        tv_version.setText("遇游邦 V" + VersionUtils.getVersionName(this));
        tv_feedback.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                LaunchOperate.openFeedbackActivity(AboutActivity.this, 2);
            }
        });

        tv_serve.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                LaunchOperate.openRuleActivity(AboutActivity.this, GlobalParams.USER, 3, "temp");
            }
        });

        tv_customer.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                CallUtil.call(AboutActivity.this, "0755-84552622");
            }
        });

        tv_mark.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                start(AboutActivity.this, "market://details?id=com.yuyoubang");
            }
        });
    }

    //直接跳转不判断是否存在市场应用
    public void start(Context paramContext, String paramString) {
        Uri localUri = Uri.parse(paramString);
        Intent localIntent = new Intent("android.intent.action.VIEW", localUri);
        localIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        paramContext.startActivity(localIntent);
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_about;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("关于我们");
    }
}
