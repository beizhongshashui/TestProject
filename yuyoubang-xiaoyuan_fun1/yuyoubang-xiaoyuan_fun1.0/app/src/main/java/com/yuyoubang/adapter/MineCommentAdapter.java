package com.yuyoubang.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.bean.mine.Comment;
import com.yuyoubang.bean.mine.NoCommend;
import com.yuyoubang.config.UrlConfig;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.utils.DateUtils;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.base.MultiBaseAdapter;

import java.util.List;

/**
 * Created by hongchen on 16/12/7.
 */

public class MineCommentAdapter extends MultiBaseAdapter<Comment.ResultBean.ListBean> {
    private Context context;

    public MineCommentAdapter(Context context, List<Comment.ResultBean.ListBean> listBean, boolean isOpenLoadMore) {
        super(context, listBean, isOpenLoadMore);
        this.context = context;
    }

    @Override
    protected int getViewType(int position, Comment.ResultBean.ListBean data) {
        return 1;
    }

    @Override
    protected void convert(ViewHolder holder, final Comment.ResultBean.ListBean data, int viewType) {
        ImageView imageView = holder.getView(R.id.iv_pic);
        TextView business_name = holder.getView(R.id.business_name);
        ImageLoader.getInstance().displayImage(data.getTrip().getData().getTrip_cover_pic(), imageView, ImageOption.default_trip_options);
        holder.setText(R.id.tv_title, data.getOp_data().getTrip_name());
        holder.setText(R.id.tv_price, data.getOp_data().getTotal_price() + "");
        TextView comment = holder.getView(R.id.comment);
        TextView orde_time = holder.getView(R.id.orde_time);
        business_name.setText(data.getOp_data().getTrip_biz_name());

        if(data.getTrip() != null){
            if (data.getTrip().getData() != null){
                long start_time = data.getTrip().getData().getStart_time();
                long end_time = data.getTrip().getData().getEnd_time();
                orde_time.setText(DateUtils.getDateToMonth(start_time) + "一" + DateUtils.getDateToMonth(end_time));
            }

        }
        comment.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                LaunchOperate.openCommentDetailActivity(mContext, String.valueOf(data.getOp_data().getTrip_biz_uid()),
                        String.valueOf(data.getOp_data().getTrip_id()), String.valueOf(data.getId()));
            }
        });
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.mine_comment_item;
    }
}
