package com.yuyoubang.activity.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseNetActivity;
import com.yuyoubang.bean.CommentDetail;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.MineApi;
import com.yuyoubang.utils.DateUtils;
import com.yuyoubang.utils.PreferenceUtils;

import okhttp3.FormBody;
import retrofit2.Call;

/**
 * Created by hongchen on 16/12/22.
 */

public class CommentDetailsActivity extends BaseNetActivity<CommentDetail> {

    private LinearLayout bizApply;
    private TextView comment_name;
    private TextView comment_time;
    private TextView comment_content;
    private TextView biz_content;
    private TextView biz_time;
    private RatingBar comment_rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        bizApply = getViewById(R.id.biz_apply);
        comment_name = getViewById(R.id.comment_name);
        comment_time = getViewById(R.id.comment_time);
        comment_content = getViewById(R.id.comment_content);
        biz_content = getViewById(R.id.biz_content);
        biz_time = getViewById(R.id.biz_time);
        comment_rb = getViewById(R.id.comment_rb);
    }

    @Override
    protected void loadData() {
        String biz_uid = getIntent().getStringExtra("biz_uid");
        String trip_id = getIntent().getStringExtra("trip_id");
        String trip_participate_id = getIntent().getStringExtra("trip_participate_id");
        FormBody formBody = new FormBody.Builder()
                .add("biz_uid", biz_uid)
                .add("trip_id", trip_id)
                .add("trip_participate_id", trip_participate_id)
                .add("uid", PreferenceUtils.getsessionId(this))
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<CommentDetail> commentDetailCall = mineApi.comment_detail(formBody);
        commentDetailCall.enqueue(this);
    }

    @Override
    protected void processData(CommentDetail commentDetail) {
        goneLoading();
        if (commentDetail.getError_code() == 0) {
            comment_rb.setRating(commentDetail.getResult().getList().get(0).getOp_data().getValue());
            comment_name.setText(commentDetail.getResult().getList().get(0).getOp_data().getUser_name());
            comment_time.setText(DateUtils.getDateToString(commentDetail.getResult().getList().get(0).getCreated_at()));
            comment_content.setText(commentDetail.getResult().getList().get(0).getOp_data().getComment());
            if (commentDetail.getResult().getList().get(0).getOp_data().getBiz_user_reply() != null) {
                bizApply.setVisibility(View.VISIBLE);
                biz_content.setText(commentDetail.getResult().getList().get(0).getOp_data().getBiz_user_reply());
                biz_time.setText(DateUtils.getDateToString(commentDetail.getResult().getList().get(0).getUpdated_at()));
            } else {
                bizApply.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_comment_detail;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("评价详情");
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
