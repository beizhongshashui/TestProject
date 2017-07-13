package com.yuyoubang.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.bean.mine.NoCommend;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.utils.DateUtils;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.base.MultiBaseAdapter;

import java.util.List;

/**
 * Created by hongchen on 16/12/7.
 */

public class MineNoCommentAdapter extends MultiBaseAdapter<NoCommend.ResultBean.ListBean> {
    private Context context;
    private TextView comment;

    public MineNoCommentAdapter(Context context, List<NoCommend.ResultBean.ListBean> listBean, boolean isOpenLoadMore) {
        super(context, listBean, isOpenLoadMore);
        this.context = context;

    }

    @Override
    protected int getViewType(int position, NoCommend.ResultBean.ListBean data) {
        return 1;
    }

    @Override
    protected void convert(ViewHolder holder, final NoCommend.ResultBean.ListBean data, int viewType) {
        ImageView imageView = holder.getView(R.id.iv_pic);
        TextView business_name = holder.getView(R.id.business_name);
        TextView orde_time = holder.getView(R.id.orde_time);
        ImageLoader.getInstance().displayImage(data.getTrip().getData().getTrip_cover_pic(), imageView, ImageOption.default_trip_options);
        holder.setText(R.id.tv_title, data.getOp_data().getTrip_name());
        holder.setText(R.id.tv_price, data.getOp_data().getTotal_price() + "");
        comment = holder.getView(R.id.comment);
        business_name.setText(data.getOp_data().getTrip_biz_name());
        long start_time = Long.valueOf(data.getTrip().getData().getStart_time());
        long end_time = Long.valueOf(data.getTrip().getData().getEnd_time());
        orde_time.setText(DateUtils.getDateToMonth(start_time) + "一" + DateUtils.getDateToMonth(end_time));
        comment.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                comment.setText("已评价");
                LaunchOperate.openCommentActivity(mContext, data);
            }
        });
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.mine_no_comment_item;
    }
}
