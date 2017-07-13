package com.yuyoubang.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.utils.ToastUtils;

/**
 * Created by hongchen on 16/12/1.
 */

public class MineSignActivity extends BaseActivity {

    private EditText tv_feed_back_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
    }

    private void initView() {
        tv_feed_back_content = getViewById(R.id.tv_feed_back_content);
    }

    private void setListener() {

    }

    @Override
    protected int getContentResId() {
        return R.layout.act_mine_sign;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("个人签名");
        builder.setBackGround(Color.WHITE);
        builder.setTitleColor(Color.BLACK);
        builder.setLeftOperate(R.mipmap.back_black, new OnClickListener() {
            @Override
            protected void clickOperate() {
                finish();
            }
        });
        builder.setRightTvColor(R.color.color_ff9600);
        builder.setRightTvText("保存", new OnClickListener() {
            @Override
            protected void clickOperate() {
                String mine_sign = tv_feed_back_content.getText().toString().trim();
                if (TextUtils.isEmpty(mine_sign)) {
                    ToastUtils.showShort("个性签名不能为空");
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra("mine_sign", mine_sign);
                setResult(Activity.RESULT_OK, intent);
                finish();

            }
        });
    }
}
