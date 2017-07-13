package com.yuyoubang.activity.mine.business;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.ToastUtils;

/**
 * Created by hongchen on 17/1/3.
 */

public class ThemeSerachActivity extends BaseActivity {

    private EditText editTextMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        editTextMessage = getViewById(R.id.editTextMessage);
        TextView sure_search = getViewById(R.id.sure_search);

        sure_search.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {


                String content = editTextMessage.getText().toString().trim();
                if(TextUtils.isEmpty(content)){
                    ToastUtils.showLong("搜索内容不能为空");
                    return;
                }

                LaunchOperate.openSerachThemeResultActivity(ThemeSerachActivity.this, content);
            }
        });
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_business_serach_theme;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("主题搜索");
    }
}
