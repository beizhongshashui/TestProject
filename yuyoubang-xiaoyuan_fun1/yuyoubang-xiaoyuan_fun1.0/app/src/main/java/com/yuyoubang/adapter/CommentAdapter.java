package com.yuyoubang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.yuyoubang.R;
import com.yuyoubang.bean.CommentBean;
import com.yuyoubang.utils.DateUtils;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.base.MultiBaseAdapter;

import java.util.List;

/**
 * @author xiaoyuan
 */

public class CommentAdapter extends MultiBaseAdapter<CommentBean.ResultBean.ListBean> {
    private Context context;

    public CommentAdapter(Context context, List<CommentBean.ResultBean.ListBean> listBean, boolean isOpenLoadMore) {
        super(context, listBean, isOpenLoadMore);
        this.context = context;

    }

    @Override
    protected int getViewType(int position, CommentBean.ResultBean.ListBean data) {
        return 1;

    }


    @Override
    protected void convert(ViewHolder holder, CommentBean.ResultBean.ListBean data, int viewType) {
        RatingBar ratingBar = holder.getView(R.id.comment_rb);
        LinearLayout bizApply = holder.getView(R.id.biz_apply);
        ratingBar.setRating(data.getOp_data().getValue());
        holder.setText(R.id.comment_name, data.getOp_data().getUser_name());
        holder.setText(R.id.comment_time, DateUtils.getDateToString(data.getCreated_at()));
        holder.setText(R.id.comment_content, data.getOp_data().getComment());
        LayerDrawable layerDrawable = (LayerDrawable) ratingBar.getProgressDrawable();
        layerDrawable.getDrawable(2).setColorFilter(Color.parseColor("#ff9600"), PorterDuff.Mode.SRC_ATOP);

        if (TextUtils.isEmpty(data.getOp_data().getBiz_user_reply())) {
            bizApply.setVisibility(View.GONE);
        } else {
            bizApply.setVisibility(View.VISIBLE);
            holder.setText(R.id.biz_content, data.getOp_data().getBiz_user_reply());
            holder.setText(R.id.biz_time, DateUtils.getDateToString(data.getUpdated_at()));
        }

    }


    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.comment_item;
    }


}
