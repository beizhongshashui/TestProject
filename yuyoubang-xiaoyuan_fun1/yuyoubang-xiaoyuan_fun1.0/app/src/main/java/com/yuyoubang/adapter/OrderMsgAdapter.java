package com.yuyoubang.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.bean.FindRecomendBean;
import com.yuyoubang.bean.Order;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.utils.TimeUtil;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.base.MultiBaseAdapter;

import java.util.List;

/**
 * @author xiaoyuan
 */

public class OrderMsgAdapter extends MultiBaseAdapter<Order.ResultBean.ListBean> {
    private Context context;

    public OrderMsgAdapter(Context context, List<Order.ResultBean.ListBean> listBean, boolean isOpenLoadMore) {
        super(context, listBean, isOpenLoadMore);
        this.context = context;
    }

    @Override
    protected int getViewType(int position, Order.ResultBean.ListBean data) {
        return 1;
    }

    @Override
    protected void convert(ViewHolder holder, Order.ResultBean.ListBean data, int viewType) {
        TextView rmb = holder.getView(R.id.rmb);
        TextView tv_send_msg = holder.getView(R.id.tv_send_msg);
        TextView tv_content = holder.getView(R.id.tv_content);


        if (data.getOp_data().getTrip_name().contains("【")) {
            rmb.setText(data.getOp_data().getTrip_name());
        } else {
            rmb.setText("【" + data.getOp_data().getTrip_name() + "】");
        }
        tv_send_msg.setText(TimeUtil.getLongString(data.getCreated_at()));

        if (data.getOp_data().getParticipate_state().equals("book")) {
            //您的订单已被商家确认, 请到我的订单页面进行付款(点击查看详情)
            tv_content.setText("您的订单未确认, 请等待商家确认(点击查看详情)");
        }
        if (data.getOp_data().getParticipate_state().equals("cancel")) {
            tv_content.setText("您的订单已取消(点击查看详情)");
        }
        if (data.getOp_data().getParticipate_state().equals("confirmed")) {
            tv_content.setText("您的订单已被商家确认, 请到我的订单页面进行付款(点击查看详情)");
        }
        if (data.getOp_data().getParticipate_state().equals("refund_complaint")) {
            tv_content.setText("您的订单投诉成功(点击查看详情)");
        }
        if (data.getOp_data().getParticipate_state().equals("finished")) {
            tv_content.setText("您的订单已完成(点击查看详情)");
        }
        if (data.getOp_data().getParticipate_state().equals("payed")) {
            tv_content.setText("您的订单已付款(点击查看详情)");
        }
        if (data.getOp_data().getParticipate_state().equals("refund_processing")) {
            tv_content.setText("您的订单退款处理中(点击查看详情)");
        }
    }


    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_order_msg_item;
    }


}
