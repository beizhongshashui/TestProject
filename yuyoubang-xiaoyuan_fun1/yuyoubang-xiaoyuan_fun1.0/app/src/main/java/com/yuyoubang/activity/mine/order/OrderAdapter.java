package com.yuyoubang.activity.mine.order;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.activity.home.InfoActivity;
import com.yuyoubang.activity.model.ApplyRefundPopupWindow;
import com.yuyoubang.activity.model.ComplaintPopupWindow;
import com.yuyoubang.bean.BaseBean;
import com.yuyoubang.bean.CancelOrder;
import com.yuyoubang.bean.Order;
import com.yuyoubang.bean.mine.NoCommend;
import com.yuyoubang.listener.ApplyRefundClickListener;
import com.yuyoubang.listener.ComplaintClickListener;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.MineApi;
import com.yuyoubang.utils.DateUtils;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.TimeUtil;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.base.MultiBaseAdapter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author xiaoyuan
 */

public class OrderAdapter extends MultiBaseAdapter<Order.ResultBean.ListBean> {
    private Context context;
    private List<String> strings = new ArrayList<>();
    private TextView cancelorder;
    private String startTime;
    private String endTime;
    private TextView statusAction;
    private TextView tvstatusAction;

    public OrderAdapter(Context context, List<Order.ResultBean.ListBean> listBean, boolean isOpenLoadMore) {
        super(context, listBean, isOpenLoadMore);
        this.context = context;

    }

    @Override
    protected int getViewType(int position, Order.ResultBean.ListBean data) {
        return 0;


    }


    @Override
    protected void convert(ViewHolder holder, final Order.ResultBean.ListBean data, int viewType) {
        ImageView imageView = holder.getView(R.id.iv_pic);
        tvstatusAction = holder.getView(R.id.tv_status_action);
        TextView business_name = holder.getView(R.id.business_name);
        ImageLoader.getInstance().displayImage(data.getTrip().getData().getTrip_cover_pic(), imageView, ImageOption.default_trip_options);
        holder.setText(R.id.tv_title, data.getOp_data().getTrip_name());
        if (data.getTrip() != null) {
            if (data.getTrip().getData() != null) {
                if (data.getTrip().getData().getTrip_price() != null) {
                    holder.setText(R.id.tv_price, data.getTrip().getData().getTrip_price() + "");
                }
            }
        }
        holder.setText(R.id.all_price, data.getOp_data().getTotal_price() + "");
        holder.setText(R.id.order_info, "下单人数：" + (Integer.parseInt(data.getOp_data().getMale_count()) + Integer.parseInt(data.getOp_data().getFemale_count())) + " 其中男" + data.getOp_data().getMale_count() + "人," + "女" + data.getOp_data().getFemale_count() + "人");
        statusAction = holder.getView(R.id.status_action);
        cancelorder = holder.getView(R.id.cancel_order);
        TextView order_id = holder.getView(R.id.order_id);
        TextView order_time = holder.getView(R.id.order_time);
        TextView orde_time = holder.getView(R.id.orde_time);
        RelativeLayout info_activity = holder.getView(R.id.info_activity);

        order_id.setText("订单号 " + data.getId());
        order_time.setText("下单时间: " + TimeUtil.getTimeShowString(data.getCreated_at(), false));
        business_name.setText("商家：" + data.getOp_data().getTrip_biz_name());
        if (data.getTrip() != null) {
            if (data.getTrip().getData() != null) {
                if (data.getTrip().getData().getStart_time() != null) {
                    startTime = DateUtils.getDateToMonth(Long.valueOf(data.getTrip().getData().getStart_time()));
                }
                if (data.getTrip().getData().getEnd_time() != null) {
                    endTime = DateUtils.getDateToMonth(Long.valueOf(data.getTrip().getData().getEnd_time()));
                }
                orde_time.setText(startTime + "一" + endTime);
            }
        }

        if (data.getOp_data().getParticipate_state().equals("book")) {
            tvstatusAction.setText("报名中,等待商家确认");
            statusAction.setText("取消订单");
            cancelorder.setText("取消订单");
            statusAction.setVisibility(View.GONE);
            cancelorder.setVisibility(View.VISIBLE);
            cancelorder.setOnClickListener(new OnClickListener() {
                @Override
                protected void clickOperate() {
                    showDelDialog(String.valueOf(data.getData_id()), String.valueOf(data.getId()));
                }
            });
            statusAction.setOnClickListener(new OnClickListener() {
                @Override
                protected void clickOperate() {
                    ToastUtils.showShort("等待商家确认");
                }
            });
        } else if (data.getOp_data().getParticipate_state().equals("cancel")) {
            tvstatusAction.setText("已取消");
            statusAction.setVisibility(View.GONE);
            cancelorder.setVisibility(View.GONE);
        } else if (data.getOp_data().getParticipate_state().equals("confirmed")) {
            tvstatusAction.setText("报名已确认,请支付");
            statusAction.setText("立即支付");
            cancelorder.setText("取消订单");
            statusAction.setVisibility(View.VISIBLE);
            cancelorder.setVisibility(View.VISIBLE);
            cancelorder.setOnClickListener(new OnClickListener() {
                @Override
                protected void clickOperate() {
                    showDelDialog(String.valueOf(data.getData_id()), String.valueOf(data.getId()));
                }
            });
            statusAction.setOnClickListener(new OnClickListener() {
                @Override
                protected void clickOperate() {
                    LaunchOperate.openOrderPayActivity(mContext, String.valueOf(data.getUid()),
                            String.valueOf(data.getTrip().getId()), String.valueOf(data.getId())
                            , data.getOp_data().getTrip_name(), data.getOp_data().getTotal_price());
                }
            });
        } else if (data.getOp_data().getParticipate_state().equals("refused")) {
            tvstatusAction.setText("商家拒绝");
            cancelorder.setVisibility(View.GONE);
            statusAction.setVisibility(View.GONE);
        } else if (data.getOp_data().getParticipate_state().equals("payed")) {
            tvstatusAction.setText("已付款");
            statusAction.setVisibility(View.GONE);
            statusAction.setText("申请退款");

            if (TimeUtil.getDateSecond() > Long.valueOf(data.getTrip().getData().getStart_time())) {
                cancelorder.setVisibility(View.GONE);
                if (TimeUtil.getDateSecond() > (Long.valueOf(data.getTrip().getData().getEnd_time()) + (3 * 24 * 60 * 60 * 1000))) {
                    cancelorder.setVisibility(View.GONE);
                } else {
                    statusAction.setText("评价");
                    cancelorder.setText("投诉");
                    statusAction.setVisibility(View.VISIBLE);
                    cancelorder.setVisibility(View.VISIBLE);
                    cancelorder.setOnClickListener(new OnClickListener() {
                        @Override
                        protected void clickOperate() {
                            if (data.getTrip() != null) {
//                                showPopuWindow(String.valueOf(data.getTrip().getId()), String.valueOf(data.getId()), data.getOp_data().getTotal_price());
                                showComplaintPopuWindow(String.valueOf(data.getTrip().getId()), String.valueOf(data.getId()), data.getOp_data().getTotal_price());
                            }
                        }
                    });
                }
            } else {
                cancelorder.setText("申请退款");
                cancelorder.setVisibility(View.VISIBLE);
                cancelorder.setOnClickListener(new OnClickListener() {
                    @Override
                    protected void clickOperate() {
                        if (data.getTrip() != null) {
                            showPopuWindow(String.valueOf(data.getTrip().getId()), String.valueOf(data.getId()), data.getOp_data().getTotal_price());
                        }
                    }
                });

            }


            if (data.getOp_data().getTrip_comment_state() != null) {
                if (Long.valueOf(data.getOp_data().getTrip_comment_state()) == 1) {//已经评价
                    statusAction.setText("已评价");
                    statusAction.setEnabled(false);
                }else {
                    statusAction.setText("评价");
                    statusAction.setEnabled(true);
                }
            }else {
                /*statusAction.setText("评价");
                statusAction.setClickable(true);*/
                statusAction.setText("已评价");
                statusAction.setEnabled(false);
            }
            statusAction.setOnClickListener(new OnClickListener() {
                @Override
                protected void clickOperate() {
                    NoCommend.ResultBean.ListBean listBean = new NoCommend.ResultBean.ListBean();
                    NoCommend.ResultBean.ListBean.OpDataBean opDataBean = new NoCommend.ResultBean.ListBean.OpDataBean();
                    NoCommend.ResultBean.ListBean.TirpDataBean tirpDataBean = new NoCommend.ResultBean.ListBean.TirpDataBean();
                    NoCommend.ResultBean.ListBean.TirpDataBean.DataBean pic = new NoCommend.ResultBean.ListBean.TirpDataBean.DataBean();
                    listBean.setUid(data.getUid());
                    listBean.setId(data.getId());
                    opDataBean.setTrip_name(data.getOp_data().getTrip_name());
                    opDataBean.setTotal_price(Integer.valueOf(data.getOp_data().getTotal_price()));
                    opDataBean.setTrip_biz_uid(Long.valueOf(data.getOp_data().getTrip_biz_uid()));
                    opDataBean.setTrip_id(Long.valueOf(data.getOp_data().getTrip_id()));
                    pic.setTrip_cover_pic(data.getTrip().getData().getTrip_cover_pic());
                    tirpDataBean.setData(pic);
                    listBean.setOp_data(opDataBean);
                    listBean.setTrip(tirpDataBean);
                    LaunchOperate.openCommentActivity(mContext, listBean);
                }
            });

            /**
             * by xiaoyuan
             */
//            statusAction.setVisibility(View.VISIBLE);
            /*if(TimeUtil.getDateSecond() > Long.valueOf(data.getTrip().getData().getEnd_time())){
                if (TimeUtil.getDateSecond() > (Long.valueOf(data.getTrip().getData().getEnd_time()) + (3 * 24 * 60 * 60 * 1000))) {
                    cancelorder.setVisibility(View.GONE);
                } else {
                    cancelorder.setText("申请退款");
                    cancelorder.setVisibility(View.VISIBLE);
                    cancelorder.setOnClickListener(new OnClickListener() {
                        @Override
                        protected void clickOperate() {
                            if (data.getTrip() != null) {
//                                showPopuWindow(String.valueOf(data.getTrip().getId()), String.valueOf(data.getId()), data.getOp_data().getTotal_price());
                                showComplaintPopuWindow(String.valueOf(data.getTrip().getId()), String.valueOf(data.getId()), data.getOp_data().getTotal_price());
                            }
                        }
                    });
                }
            }*/

            /**
             * by xiaoyuan
             */

//            cancelorder.setOnClickListener(new OnClickListener() {
//                @Override
//                protected void clickOperate() {
//                    showPopuWindow(String.valueOf(data.getTrip().getId()), String.valueOf(data.getId()), data.getOp_data().getTotal_price());
//                }
//            });
        } else if (data.getOp_data().getParticipate_state().equals("refund_apply")) {
            cancelorder.setText("撤销退款");
            tvstatusAction.setText("申请退款中");
            statusAction.setVisibility(View.GONE);
            cancelorder.setVisibility(View.VISIBLE);
            cancelorder.setOnClickListener(new OnClickListener() {
                @Override
                protected void clickOperate() {
//                    ToastUtils.showShort("申请退款中");
                    cancelApplyRefund(data.getId() + "");
//                    showPopuWindow(String.valueOf(data.getTrip().getId()), String.valueOf(data.getId()), data.getOp_data().getTotal_price());
                }
            });
        } else if (data.getOp_data().getParticipate_state().equals("refund_complaint")) {
            statusAction.setVisibility(View.GONE);
            tvstatusAction.setText("投诉退款中");
            cancelorder.setVisibility(View.VISIBLE);
            cancelorder.setText("撤销投诉");
            cancelorder.setOnClickListener(new OnClickListener() {
                @Override
                protected void clickOperate() {
//                    ToastUtils.showShort("申请退款中");
//                    showPopuWindow(String.valueOf(data.getTrip().getId()), String.valueOf(data.getId()), data.getOp_data().getTotal_price());
                    cancelComplaint(data.getId() + "");
                }
            });

        } else if (data.getOp_data().getParticipate_state().equals("sys_cancel")) {
            statusAction.setVisibility(View.GONE);
            tvstatusAction.setText("系统撤单");
            cancelorder.setText("系统撤单");
        } else if (data.getOp_data().getParticipate_state().equals("refund_processing")) {
            statusAction.setVisibility(View.GONE);
            tvstatusAction.setText("投诉处理中");
            cancelorder.setText("撤销投诉");
            cancelorder.setVisibility(View.GONE);//TODO 暂时gone,木有接口
            cancelorder.setOnClickListener(new OnClickListener() {
                @Override
                protected void clickOperate() {//撤销投诉

                }
            });

        } else if (data.getOp_data().getParticipate_state().equals("finished")) {
            tvstatusAction.setText("已完成");
            statusAction.setText("评价");
            cancelorder.setVisibility(View.GONE);
//            cancelorder.setText("投诉");
            /**
             * by xiaoyuan
             */
//            cancelorder.setVisibility(View.GONE);
            /*if (TimeUtil.getDateSecond() > (Long.valueOf(data.getTrip().getData().getEnd_time()) + (3 * 24 * 60 * 60 * 1000))) {
                cancelorder.setVisibility(View.GONE);
            } else {
                cancelorder.setText("投诉");
                cancelorder.setVisibility(View.VISIBLE);
                cancelorder.setOnClickListener(new OnClickListener() {
                    @Override
                    protected void clickOperate() {
                        if (data.getTrip() != null) {
//                                showPopuWindow(String.valueOf(data.getTrip().getId()), String.valueOf(data.getId()), data.getOp_data().getTotal_price());
                            showComplaintPopuWindow(String.valueOf(data.getTrip().getId()), String.valueOf(data.getId()), data.getOp_data().getTotal_price());
                        }
                    }
                });
            }*/
            /*if(TimeUtil.getDateSecond() > Long.valueOf(data.getTrip().getData().getEnd_time())){
                if (TimeUtil.getDateSecond() > (Long.valueOf(data.getTrip().getData().getEnd_time()) + (3 * 24 * 60 * 60 * 1000))) {
                    cancelorder.setVisibility(View.GONE);
                } else {
                    cancelorder.setText("投诉");
                    cancelorder.setVisibility(View.VISIBLE);
                    cancelorder.setOnClickListener(new OnClickListener() {
                        @Override
                        protected void clickOperate() {
                            if (data.getTrip() != null) {
//                                showPopuWindow(String.valueOf(data.getTrip().getId()), String.valueOf(data.getId()), data.getOp_data().getTotal_price());
                                showComplaintPopuWindow(String.valueOf(data.getTrip().getId()), String.valueOf(data.getId()), data.getOp_data().getTotal_price());
                            }
                        }
                    });
                }
            }*/

            if (data.getOp_data().getTrip_comment_state() != null) {
                if (Long.valueOf(data.getOp_data().getTrip_comment_state()) == 1) {//已经评价
                    statusAction.setText("已评价");
                    statusAction.setEnabled(false);
                }else {
                    statusAction.setText("评价");
                    statusAction.setEnabled(true);
                }
            }else {
                /*statusAction.setText("评价");
                statusAction.setClickable(true);*/
                statusAction.setText("已评价");
                statusAction.setEnabled(false);
            }

            statusAction.setOnClickListener(new OnClickListener() {
                @Override
                protected void clickOperate() {
                    NoCommend.ResultBean.ListBean listBean = new NoCommend.ResultBean.ListBean();
                    NoCommend.ResultBean.ListBean.OpDataBean opDataBean = new NoCommend.ResultBean.ListBean.OpDataBean();
                    NoCommend.ResultBean.ListBean.TirpDataBean tirpDataBean = new NoCommend.ResultBean.ListBean.TirpDataBean();
                    NoCommend.ResultBean.ListBean.TirpDataBean.DataBean pic = new NoCommend.ResultBean.ListBean.TirpDataBean.DataBean();
                    listBean.setUid(data.getUid());
                    listBean.setId(data.getId());
                    opDataBean.setTrip_name(data.getOp_data().getTrip_name());
                    opDataBean.setTotal_price(Integer.valueOf(data.getOp_data().getTotal_price()));
                    opDataBean.setTrip_biz_uid(Long.valueOf(data.getOp_data().getTrip_biz_uid()));
                    opDataBean.setTrip_id(Long.valueOf(data.getOp_data().getTrip_id()));
                    pic.setTrip_cover_pic(data.getTrip().getData().getTrip_cover_pic());
                    tirpDataBean.setData(pic);
                    listBean.setOp_data(opDataBean);
                    listBean.setTrip(tirpDataBean);
                    LaunchOperate.openCommentActivity(mContext, listBean);
                }
            });
        }
        info_activity.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                InfoActivity.start(mContext, data.getTrip().getId() + "");
            }
        });
    }

    private void applyOutMoney(String type, String num, String because,
                               String trip_id, String trip_participate_id, String refund_value) {//报名活动的用户申请退款（必须在活动开始之前用户申请退款，participate_state=refund_apply）
        FormBody formBody = new FormBody.Builder()
                .add("refund_account", num)//用户退款账号
                .add("refund_account_type", type)//用户退款账号类型（银行卡，支付宝，微信等等。用户手填）
                .add("refund_reason", because)//用户申请退款理由
                .add("refund_value", refund_value)//退款金额，这里传入订单总金额
                .add("trip_id", trip_id)//报名这次活动的id
                .add("trip_participate_id", trip_participate_id)//报名id
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<BaseBean> objectCall = mineApi.user_refund_apply(formBody);
        objectCall.enqueue(new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                if (response.body().error_code == 0) {
                    ToastUtils.showShort("申请退款成功,等待商家处理");
                } else {
                    ToastUtils.showShort(response.body().error_msg);
                }
            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {
                ToastUtils.showShort("退款失败");
            }
        });
    }

    private void applyComplaintMoney(String type, String num, String because,
                                     String trip_id, String trip_participate_id, String refund_value) {//报名活动的用户申请退款（必须在活动开始之前用户申请退款，participate_state=refund_apply）
        FormBody formBody = new FormBody.Builder()
                .add("refund_account", num)//用户退款账号
                .add("refund_account_type", type)//用户退款账号类型（银行卡，支付宝，微信等等。用户手填）
                .add("refund_reason", because)//用户申请退款理由
                .add("refund_value", refund_value)//退款金额，这里传入订单总金额
                .add("trip_id", trip_id)//报名这次活动的id
                .add("trip_participate_id", trip_participate_id)//报名id
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<BaseBean> objectCall = mineApi.user_refund_complaint(formBody);
        objectCall.enqueue(new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                if (response.body().error_code == 0) {
                    ToastUtils.showShort("投诉成功, 等待商家处理");
                } else {
                    ToastUtils.showShort(response.body().error_msg);
                }
            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {
                ToastUtils.showShort("退款失败");
            }
        });
    }


    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.order_item;
    }

    private void showPopuWindow(final String trip_id, final String trip_participate_id, final String refund_value) {
        ApplyRefundPopupWindow msgPopupWindow = new ApplyRefundPopupWindow(mContext, new ApplyRefundClickListener() {
            @Override
            public void sure(String type, String num, String because) {
                if (TextUtils.isEmpty(type)) {
                    ToastUtils.showShort("请输入退款方式");
                    return;
                }
                if (TextUtils.isEmpty(num)) {
                    ToastUtils.showShort("请输入退款账号");
                    return;
                }
                if (TextUtils.isEmpty(because)) {
                    ToastUtils.showShort("请输入退款原因");
                    return;
                }
                applyOutMoney(type, num, because, trip_id, trip_participate_id, refund_value);
            }

            @Override
            public void notSure() {
            }
        });
        msgPopupWindow.showAtLocation(
                cancelorder, Gravity.CENTER
                        | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void showComplaintPopuWindow(final String trip_id, final String trip_participate_id, final String refund_value) {
        ComplaintPopupWindow msgPopupWindow = new ComplaintPopupWindow(mContext, new ComplaintClickListener() {
            @Override
            public void sure(String type, String num, String because, String price) {
                if (TextUtils.isEmpty(type)) {
                    ToastUtils.showShort("请输入退款方式");
                    return;
                }
                if (TextUtils.isEmpty(num)) {
                    ToastUtils.showShort("请输入退款账号");
                    return;
                }
                if (TextUtils.isEmpty(because)) {
                    ToastUtils.showShort("请输入退款原因");
                    return;
                }
                if (TextUtils.isEmpty(price)) {
                    ToastUtils.showShort("请输入退款原因");
                    return;
                }
                applyComplaintMoney(type, num, because, trip_id, trip_participate_id, price);
            }

            @Override
            public void notSure() {
            }
        });
        msgPopupWindow.showAtLocation(
                cancelorder, Gravity.CENTER
                        | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void showDelDialog(final String trip_id, final String trip_participate_id) {
        new AlertDialog.Builder(mContext).setCancelable(true)
                .setTitle("确定取消该订单？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        cancelOrder(trip_id, trip_participate_id);
                    }
                }).show();
    }

    private void cancelOrder(final String trip_id, String trip_participate_id) {
        FormBody formBody = new FormBody.Builder()
                .add("trip_id", trip_id)
                .add("trip_participate_id", trip_participate_id)
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<CancelOrder> cancelOrderCall = mineApi.user_cancel_order(formBody);
        cancelOrderCall.enqueue(new Callback<CancelOrder>() {
            @Override
            public void onResponse(Call<CancelOrder> call, Response<CancelOrder> response) {
                if (response.body().getError_code() == 0) {
                    for (int i = 0; i < mDatas.size(); i++) {
                        if (mDatas.get(i).getData_id() == Long.valueOf(trip_id)) {
                            mDatas.get(i).getOp_data().setParticipate_state("cancle");
                            ToastUtils.showShort("取消成功");
                        }
                    }
                    notifyDataSetChanged();
                } else {
                    ToastUtils.showShort("取消失败");
                }
            }

            @Override
            public void onFailure(Call<CancelOrder> call, Throwable t) {
                ToastUtils.showShort("取消失败");
            }
        });
    }

    /**
     * 撤销退款  by  xiaoyuan
     *
     * @param trip_participate_id
     */
    private void cancelApplyRefund(String trip_participate_id) {
        FormBody formBody = new FormBody.Builder()
                .add("participate_id", trip_participate_id)
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<CancelOrder> cancelOrderCall = mineApi.user_revocation_apply(formBody);
        cancelOrderCall.enqueue(new Callback<CancelOrder>() {
            @Override
            public void onResponse(Call<CancelOrder> call, Response<CancelOrder> response) {
                if (response.body().getError_code() == 0) {
                    ToastUtils.showShort("撤销成功");
                } else {
                    ToastUtils.showShort("投诉已处理, 无法撤回申请");
                }
            }

            @Override
            public void onFailure(Call<CancelOrder> call, Throwable t) {
                ToastUtils.showShort("撤销失败");
            }
        });
    }

    /**
     * 撤销撤销  by  xiaoyuan
     *
     * @param trip_participate_id
     */
    private void cancelComplaint(String trip_participate_id) {
        FormBody formBody = new FormBody.Builder()
                .add("participate_id", trip_participate_id)
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<CancelOrder> cancelOrderCall = mineApi.user_revocation_complaint(formBody);
        cancelOrderCall.enqueue(new Callback<CancelOrder>() {
            @Override
            public void onResponse(Call<CancelOrder> call, Response<CancelOrder> response) {
                if (response.body().getError_code() == 0) {
                    ToastUtils.showShort("撤销成功");
                } else {
                    ToastUtils.showShort("投诉已处理, 无法撤回申请");
                }
            }

            @Override
            public void onFailure(Call<CancelOrder> call, Throwable t) {
                ToastUtils.showShort("撤销失败");
            }
        });
    }
}
