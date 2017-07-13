package com.yuyoubang.activity.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.bean.Report;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.HomeApi;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.ToastUtils;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hongchen on 16/11/30.
 */

public class ReportActivity extends BaseActivity {

    private TextView tvContent;
    private TextView tvNumber;
    private TextView tvCommit;
    private String content;
    private String number;
    private ScrollView feedBackScroll;
    private String trip_id;
    private String biz_uid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
    }

    private void initView() {
        tvContent = getViewById(R.id.tv_feed_back_content);
        tvNumber = getViewById(R.id.tv_your_mobile_number);
        tvCommit = getViewById(R.id.tv_click_commit);
        feedBackScroll = getViewById(R.id.feed_back_scroll);
    }

    private void setListener() {
        trip_id = getIntent().getStringExtra("trip_id");
        biz_uid = getIntent().getStringExtra("biz_uid");
        if (tvContent.requestFocus()) {
            feedBackScroll.setFocusable(false);
        } else {
            feedBackScroll.setFocusable(true);
        }
        tvCommit.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                content = tvContent.getText().toString().trim();
                number = tvNumber.getText().toString().trim();
                if (TextUtils.isEmpty(content) || TextUtils.isEmpty(number)) {
                    ToastUtils.showShort("手机号和反馈内容不能为空哦！");
                    return;
                }
                if (TextUtils.isEmpty(biz_uid)) {
                    ToastUtils.showShort("邦主不存在");
                    return;
                }
                //请求网络，提交反馈意见
                reportTrip();
            }
        });
    }

    private void reportTrip() {
        onShowProgressDlg();
        String user_name = PreferenceUtils.getPrefString(this, "user_name", "");
        if (TextUtils.isEmpty(user_name)) {
            cancelProgressDlg();
            return;
        }
        FormBody formBody = new FormBody.Builder()
                .add("user_name", user_name)
                .add("data_id", trip_id)//举报数据id
                .add("text", content)//举报内容
                .add("to_uid", biz_uid)//与本条数据相关联的用户 id，必须是已存在的用户，否则本条数据创建会失败
                .add("type", "0")//类别（0=活动，1=动态）
                .add("uid", PreferenceUtils.getsessionId(this))//与本条数据相关联的用户 id，必须是已存在的用户，否则本条数据创建会失败
                .build();
        HomeApi homeApi = RetrofitManager.getTestRetrofit().create(HomeApi.class);
        Call<Report> reportCall = homeApi.user_report_create(formBody);
        reportCall.enqueue(new Callback<Report>() {
            @Override
            public void onResponse(Call<Report> call, Response<Report> response) {
                cancelProgressDlg();
                if (response.body().getError_code() == 0) {
                    ToastUtils.showLong("举报成功");
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Report> call, Throwable t) {
                cancelProgressDlg();
                ToastUtils.showLong("举报失败");
            }
        });
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_report;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("用户举报");
    }
}
