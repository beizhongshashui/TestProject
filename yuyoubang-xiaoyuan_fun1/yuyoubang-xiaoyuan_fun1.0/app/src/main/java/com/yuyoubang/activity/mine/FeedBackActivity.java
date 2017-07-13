package com.yuyoubang.activity.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.bean.mine.Feedback;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.MineApi;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.utils.ValidateUtil;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hongchen on 16/11/30.
 */

public class FeedBackActivity extends BaseActivity {

    private TextView tvContent;
    private TextView tvNumber;
    private TextView tvCommit;
    private String content;
    private String number;
    private ScrollView feedBackScroll;
    private int flag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
    }

    private void initView() {
        flag = getIntent().getIntExtra("flag", -1);
        tvContent = getViewById(R.id.tv_feed_back_content);
        tvNumber = getViewById(R.id.tv_your_mobile_number);
        tvCommit = getViewById(R.id.tv_click_commit);
        feedBackScroll = getViewById(R.id.feed_back_scroll);
        if (flag == 1){
            tvNumber.setHint("手机号");
            tvContent.setHint("请输入举报类型和原因（虚假信息、色情、广告推销、诈骗、信息骚扰、冒充他人、其它）");
        }else {
            tvNumber.setHint("手机号");
            tvContent.setHint("给遇游邦提出宝贵建议");
        }
    }

    private void setListener() {
        if (tvContent.requestFocus()) {
            feedBackScroll.setFocusable(false);
        } else {
            feedBackScroll.setFocusable(true);
        }
        tvCommit.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                String content = tvContent.getText().toString().trim();
                String number = tvNumber.getText().toString().trim();
                if (TextUtils.isEmpty(content) || TextUtils.isEmpty(number)) {
                    if (flag == 1) {
                        ToastUtils.showShort("手机号和反馈内容不能为空哦！");
                    } else {
                        ToastUtils.showShort("手机号和反馈内容不能为空哦！");
                    }
                    return;
                }
                if (!ValidateUtil.isMobile(number)) {
                    ToastUtils.showShort("请输入正确的手机号哦！");
                    return;
                }
                //请求网络，提交反馈意见
                setFeedBack();
            }
        });
    }

    private void setFeedBack() {
        content = tvContent.getText().toString().trim();
        number = tvNumber.getText().toString().trim();
        String user_name = PreferenceUtils.getPrefString(this, "user_name", "");
        if (TextUtils.isEmpty(user_name)) {
            return;
        }
        FormBody formBody = new FormBody.Builder()
                .add("feedback", content)
                .add("feedback_type", "feedback")
                .add("phone_qq_number", number)
                .add("uid", PreferenceUtils.getsessionId(this))
                .add("user_name", user_name)
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<Feedback> feedback = mineApi.user_feedback(formBody);
        feedback.enqueue(new Callback<Feedback>() {
            @Override
            public void onResponse(Call<Feedback> call, Response<Feedback> response) {
                if (flag == 1) {
                    ToastUtils.showShort("举报成功");
                } else {
                    ToastUtils.showShort("感谢您的宝贵意见");
                }
                finish();
            }

            @Override
            public void onFailure(Call<Feedback> call, Throwable t) {
                ToastUtils.showShort("哎哟,网络好像不对哦");
            }
        });
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_feedback;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        flag = getIntent().getIntExtra("flag", -1);
        if (flag == 1) {
            builder.setTitle("用户举报");
        } else {
            builder.setTitle("意见反馈");
        }
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
