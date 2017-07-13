package com.yuyoubang.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.bean.BusinessSearch;
import com.yuyoubang.bean.OrderManager;
import com.yuyoubang.fragment.message.ChatActivity;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.BusinessApi;
import com.yuyoubang.utils.DateUtils;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.TimeUtil;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.base.MultiBaseAdapter;

import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author xiaoyuan
 */

public class OrderSerachAdapter extends MultiBaseAdapter<BusinessSearch.ResultBean.ListBean> {
    private Context context;
    private TextView sure_name;
    private TextView order_state;
    private TextView del;

    public OrderSerachAdapter(Context context, List<BusinessSearch.ResultBean.ListBean> listBean, boolean isOpenLoadMore) {
        super(context, listBean, isOpenLoadMore);
        this.context = context;
    }

    @Override
    protected int getViewType(int position, BusinessSearch.ResultBean.ListBean data) {
        return 1;
    }

    @Override
    protected void convert(ViewHolder holder, final BusinessSearch.ResultBean.ListBean data, int viewType) {
        TextView tv_send_msg = holder.getView(R.id.tv_send_msg);
        TextView look_apply_info = holder.getView(R.id.look_apply_info);
        TextView trip_name = holder.getView(R.id.trip_name);
        TextView tv_title = holder.getView(R.id.tv_title);
        TextView buy_user = holder.getView(R.id.buy_user);
        TextView buy_time = holder.getView(R.id.buy_time);
        TextView order_num = holder.getView(R.id.order_num);
        TextView persons = holder.getView(R.id.persons);
        order_state = holder.getView(R.id.order_state);
        del = holder.getView(R.id.del);
        sure_name = holder.getView(R.id.sure_name);

        tv_send_msg.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tv_send_msg.getPaint().setAntiAlias(true);//抗锯齿

        trip_name.setText("活动主题: " + data.getOp_data().getTrip_name());

        long start_time = data.getTrip().getData().getStart_time();
        long end_time = data.getTrip().getData().getEnd_time();
        String time = TimeUtil.getLongString(data.getCreated_at());

        tv_title.setText("活动日期: " + DateUtils.getDateToMonth(start_time) + "一" + DateUtils.getDateToMonth(end_time));
        buy_user.setText("下单用户: " + data.getOp_data().getContact_user_name() + "(" + data.getOp_data().getContact_phone() + ")");
        buy_time.setText("下单时间: " + time);
        order_num.setText("订单编号: " + data.getId());
        int male_count = data.getOp_data().getMale_count();
        int female_count = data.getOp_data().getFemale_count();
        int totalCount = male_count + female_count;
        persons.setText("报名人数: " + totalCount + ", 男" + male_count + "人, 女" + female_count + "人");
        if (data.getOp_data().getParticipate_state().equals("book")) {
            order_state.setText("未付款(未确认)");
            del.setText("拒绝报名");
            sure_name.setText("同意报名");
            del.setVisibility(View.VISIBLE);
            sure_name.setVisibility(View.VISIBLE);
            del.setOnClickListener(new OnClickListener() {
                @Override
                protected void clickOperate() {//取消订单(拒绝报名)
                    refuseUserJoinTrip(String.valueOf(data.getData_id()), String.valueOf(data.getId()));
                }
            });

            sure_name.setOnClickListener(new OnClickListener() {
                @Override
                protected void clickOperate() {//确认报名
                    agreeUserJoinTrip(String.valueOf(data.getData_id()), String.valueOf(data.getId()));
                }
            });
        }
        if (data.getOp_data().getParticipate_state().equals("cancel")) {
            order_state.setText("已取消");
        }
        if (data.getOp_data().getParticipate_state().equals("confirmed")) {
            order_state.setText("未付款(已确认)");
        }
        if (data.getOp_data().getParticipate_state().equals("refund_complaint")) {
            order_state.setText("被投诉");
        }
        if (data.getOp_data().getParticipate_state().equals("finished")) {
            order_state.setText("已完成");
        }
        if (data.getOp_data().getParticipate_state().equals("payed")) {
            order_state.setText("已付款");
        }
        if (data.getOp_data().getParticipate_state().equals("refund_processing")) {
            order_state.setText("退款处理中");
            del.setText("拒绝退款");
            sure_name.setText("同意退款");
            del.setVisibility(View.VISIBLE);
            sure_name.setVisibility(View.VISIBLE);
            del.setOnClickListener(new OnClickListener() {
                @Override
                protected void clickOperate() {//拒绝退款
                    disAgreeTripApply(String.valueOf(data.getId()));
                }
            });

            sure_name.setOnClickListener(new OnClickListener() {
                @Override
                protected void clickOperate() {//同意退款
                    agreeTripApply(String.valueOf(data.getId()));
                }
            });
        }

        look_apply_info.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//查看详情
                LaunchOperate.openOrderDetailsActivity(mContext, String.valueOf(data.getId()));
            }
        });

        tv_send_msg.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//立即沟通
                ChatActivity.start(mContext, String.valueOf(data.getUid()), ChatActivity.SINGLE);
            }
        });
    }

    private void disAgreeTripApply(String trip_participate_id) {
        FormBody body = new FormBody.Builder()
                .add("trip_participate_id", trip_participate_id)
                .add("refund_biz_comment", "商家拒绝用户退款的注释")
                .build();
        BusinessApi businessApi = RetrofitManager.getTestRetrofit().create(BusinessApi.class);
        Call<OrderManager> objectCall = businessApi.business_disagree_refund_apply(body);
        objectCall.enqueue(new Callback<OrderManager>() {
            @Override
            public void onResponse(Call<OrderManager> call, Response<OrderManager> response) {
                if (response.body().getError_code() == 0) {
                    ToastUtils.showShort("拒绝成功");
                    del.setVisibility(View.GONE);
                    order_state.setText("已拒绝");
                }
            }

            @Override
            public void onFailure(Call<OrderManager> call, Throwable t) {

            }
        });
    }

    private void agreeTripApply(String trip_participate_id) {
        FormBody body = new FormBody.Builder()
                .add("trip_participate_id", trip_participate_id)
                .add("refund_biz_comment", "商家拒绝用户退款的注释")
                .build();
        BusinessApi businessApi = RetrofitManager.getTestRetrofit().create(BusinessApi.class);
        Call<OrderManager> objectCall = businessApi.business_agree_refund_apply(body);
        objectCall.enqueue(new Callback<OrderManager>() {
            @Override
            public void onResponse(Call<OrderManager> call, Response<OrderManager> response) {
                if (response.body().getError_code() == 0) {
                    ToastUtils.showShort("同意退款成功");
                    sure_name.setVisibility(View.GONE);
                    order_state.setText("已退款");
                }
            }

            @Override
            public void onFailure(Call<OrderManager> call, Throwable t) {

            }
        });
    }

    private void refuseUserJoinTrip(String trip_id, String trip_participate_id) {
        FormBody body = new FormBody.Builder()
                .add("trip_id", trip_id)
                .add("trip_participate_id", trip_participate_id)
                .build();
        BusinessApi businessApi = RetrofitManager.getTestRetrofit().create(BusinessApi.class);
        Call<OrderManager> objectCall = businessApi.business_refuse_book(body);
        objectCall.enqueue(new Callback<OrderManager>() {
            @Override
            public void onResponse(Call<OrderManager> call, Response<OrderManager> response) {
                if (response.body().getError_code() == 0) {
                    ToastUtils.showShort("拒绝成功");
                    del.setVisibility(View.GONE);
                    order_state.setText("未付款(已拒绝)");
                }
            }

            @Override
            public void onFailure(Call<OrderManager> call, Throwable t) {

            }
        });
    }

    private void agreeUserJoinTrip(String trip_id, String trip_participate_id) {
        FormBody body = new FormBody.Builder()
                .add("trip_id", trip_id)
                .add("trip_participate_id", trip_participate_id)
                .build();
        BusinessApi businessApi = RetrofitManager.getTestRetrofit().create(BusinessApi.class);
        Call<OrderManager> objectCall = businessApi.business_confirm_book(body);
        objectCall.enqueue(new Callback<OrderManager>() {
            @Override
            public void onResponse(Call<OrderManager> call, Response<OrderManager> response) {
                if (response.body().getError_code() == 0) {
                    ToastUtils.showShort("确认成功");
                    sure_name.setVisibility(View.GONE);
                    order_state.setText("未付款(已确认)");
                }
            }

            @Override
            public void onFailure(Call<OrderManager> call, Throwable t) {

            }
        });
    }


    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_type_state_item;
    }


}
