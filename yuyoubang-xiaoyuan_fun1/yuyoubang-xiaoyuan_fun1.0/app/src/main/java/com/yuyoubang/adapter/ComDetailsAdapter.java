package com.yuyoubang.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.exceptions.HyphenateException;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.bean.mine.ActionDetail;
import com.yuyoubang.fragment.message.ChatActivity;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.utils.DateUtils;
import com.yuyoubang.utils.TimeUtil;
import com.yuyoubang.widget.recycleview.BaseRecycleListViewAdapter;
import com.yuyoubang.widget.recycleview.RecycleViewHolder;
import com.yuyoubang.widget.recycleview.RecyclerListView;

import java.util.List;

/**
 * Created by hongchen on 16/10/25.
 */

public class ComDetailsAdapter extends BaseRecycleListViewAdapter<ActionDetail.ResultBean.ListBean> {

    public ComDetailsAdapter(RecyclerListView listView, Context context, List<ActionDetail.ResultBean.ListBean> data) {
        super(listView, context, data);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_details_team;
    }

    @Override
    protected void onBindView(RecycleViewHolder holder, final int position) {
        LinearLayout rootLayout = holder.getView(R.id.root_layout);
        TextView action_name = holder.getView(R.id.action_name);
        TextView tv_send_msg = holder.getView(R.id.tv_send_msg);
        TextView action_time = holder.getView(R.id.action_time);
        TextView buy_user = holder.getView(R.id.buy_user);
        TextView buy_time = holder.getView(R.id.buy_time);
        TextView order_id = holder.getView(R.id.order_id);
        TextView persons = holder.getView(R.id.persons);
        TextView order_state = holder.getView(R.id.order_state);

        action_name.setText("活动主题: " + mData.get(position).getOp_data().getTrip_name());
        long start_time = mData.get(position).getTrip().getData().getStart_time();
        long end_time = mData.get(position).getTrip().getData().getEnd_time();
        action_time.setText("活动日期: " + DateUtils.getDateToMonth(start_time) + "一" + DateUtils.getDateToMonth(end_time));

        buy_user.setText("下单用户: " + mData.get(position).getOp_data().getUser_name() + "(" + mData.get(position).getOp_data().getContact_phone() + ")");
        String time = TimeUtil.getTimeShowString(mData.get(position).getCreated_at(), false);
        buy_time.setText("下单时间: " + time);
        order_id.setText("订单编号: " + mData.get(position).getId());
        String male_count = mData.get(position).getOp_data().getMale_count();
        String female_count = mData.get(position).getOp_data().getFemale_count();
        int totalCount = Integer.valueOf(male_count) + Integer.valueOf(female_count);
        persons.setText("报名人数: " + totalCount + ", 男" + male_count + "人, 女" + female_count + "人");

        if (mData.get(position).getOp_data().getParticipate_state().equals("book")) {
            order_state.setText("未付款(未确认)");
        }
        if (mData.get(position).getOp_data().getParticipate_state().equals("cancel")) {
            order_state.setText("已取消");
        }
        if (mData.get(position).getOp_data().getParticipate_state().equals("confirmed")) {
            order_state.setText("未付款(已确认)");
        }
        if (mData.get(position).getOp_data().getParticipate_state().equals("refund_complaint")) {
            order_state.setText("被投诉");
        }
        if (mData.get(position).getOp_data().getParticipate_state().equals("finished")) {
            order_state.setText("已完成");
        }
        if (mData.get(position).getOp_data().getParticipate_state().equals("payed")) {
            order_state.setText("已付款");
        }
        tv_send_msg.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//立即沟通
                ChatActivity.start(mContext, String.valueOf(mData.get(position).getUid()), ChatActivity.SINGLE);
            }
        });
    }
}
