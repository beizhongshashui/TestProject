package com.yuyoubang.activity.mine.business;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.activity.home.InfoActivity;
import com.yuyoubang.activity.model.ActionOnlineWindow;
import com.yuyoubang.activity.model.DelActionWindow;
import com.yuyoubang.bean.MyTripBean;
import com.yuyoubang.bean.mine.ActionOffLine;
import com.yuyoubang.bean.mine.DelAction;
import com.yuyoubang.listener.IsCloseClickListener;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.MineApi;
import com.yuyoubang.utils.DateUtils;
import com.yuyoubang.utils.LaunchOperate;
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

public class ActionMangerAdapter extends MultiBaseAdapter<MyTripBean.ResultBean.ListBean> {
    private Context context;
    private List<String> strings = new ArrayList<>();
    private TextView sure_off_line;
    private TextView del;

    public ActionMangerAdapter(Context context, List<MyTripBean.ResultBean.ListBean> listBean, boolean isOpenLoadMore) {
        super(context, listBean, isOpenLoadMore);
        this.context = context;

    }

    @Override
    protected int getViewType(int position, MyTripBean.ResultBean.ListBean data) {
        return 0;


    }


    @Override
    protected void convert(ViewHolder holder, final MyTripBean.ResultBean.ListBean data, int viewType) {
        ImageView imageView = holder.getView(R.id.iv_pic);
        sure_off_line = holder.getView(R.id.sure_off_line);
        TextView status = holder.getView(R.id.status);
        TextView look_apply_info = holder.getView(R.id.look_apply_info);
        del = holder.getView(R.id.del);
        TextView tv_company = holder.getView(R.id.tv_company);
        RelativeLayout info_activity = holder.getView(R.id.info_activity);
        ImageLoader.getInstance().displayImage(data.getData().getTrip_cover_pic(), imageView, ImageOption.default_trip_options);
        holder.setText(R.id.tv_title, data.getData().getTrip_name());
        holder.setText(R.id.tv_time, DateUtils.getDateToMonth(Long.parseLong(data.getData().getStart_time())) + "一" + DateUtils.getDateToMonth(Long.parseLong(data.getData().getEnd_time())));
        holder.setText(R.id.apply_time, "报名截止日期:" + DateUtils.getDateToMonth(Long.parseLong(data.getData().getParticipate_end_time())));
        holder.setText(R.id.tv_price, data.getData().getTrip_price() + "");
        holder.setText(R.id.apply_count, (data.getData().getA_trip_female_particpate_total_count() + data.getData().getA_trip_male_particpate_total_count()) + "");
        holder.setText(R.id.sure_apply_count, (data.getData().getA_trip_female_particpate_and_payed_total_count() + data.getData().getA_trip_male_particpate_and_payed_total_count()) + "");

        if (data.getData().getTrip_state() == 0) {//待上线
            status.setText("待审核");
            sure_off_line.setVisibility(View.GONE);
        }
        if (data.getData().getTrip_state() == 1) {//已上线
            del.setVisibility(View.GONE);
            tv_company.setVisibility(View.GONE);
            status.setText("已上线");
            sure_off_line.setText("确认下线");
        }
        if (data.getData().getTrip_state() == 2) {//已下线
            status.setText("已下线");
            sure_off_line.setVisibility(View.GONE);
        }
        if (data.getData().getTrip_state() == -1) {//已删除
            del.setVisibility(View.GONE);
            tv_company.setVisibility(View.GONE);
            status.setText("已删除");
            sure_off_line.setVisibility(View.GONE);
        }

        sure_off_line.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//确认下线
                showPopuWindow(data.getId());
            }
        });

        del.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//删除
                showDelPopuWindow(data.getId(), data.getUid());
            }
        });

        look_apply_info.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//查看报名详情
                LaunchOperate.openActionDetailsActivity(mContext, String.valueOf(data.getId()));
            }
        });

        info_activity.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                InfoActivity.start(mContext, data.getId() + "");
            }
        });

    }

    private void showDelPopuWindow(final long id, final long uid) {
        DelActionWindow msgPopupWindow = new DelActionWindow(mContext, new IsCloseClickListener() {
            @Override
            public void sure() {
                delAction(id, uid);
            }

            @Override
            public void notSure() {
            }
        });
        msgPopupWindow.showAtLocation(
                del, Gravity.BOTTOM
                        | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void delAction(final long id, long uid) {
        FormBody formBody = new FormBody.Builder()
                .add("biz_uid", uid + "")
                .add("trip_id", id + "")
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<DelAction> delActionCall = mineApi.del_action(formBody);
        delActionCall.enqueue(new Callback<DelAction>() {
            @Override
            public void onResponse(Call<DelAction> call, Response<DelAction> response) {
                if (response.body().getError_code() == 0) {
                    for (int i = 0; i < mDatas.size(); i++) {
                        if (mDatas.get(i).getId() == id) {
                            ToastUtils.showShort("删除成功");
                            remove(i);
                        }
                    }
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<DelAction> call, Throwable t) {
                ToastUtils.showShort("删除失败");
            }
        });
    }

    private void showPopuWindow(final long id) {
        ActionOnlineWindow msgPopupWindow = new ActionOnlineWindow(mContext, new IsCloseClickListener() {
            @Override
            public void sure() {
                actionOffLine(id);
            }

            @Override
            public void notSure() {
            }
        });
        msgPopupWindow.showAtLocation(
                sure_off_line, Gravity.BOTTOM
                        | Gravity.CENTER_HORIZONTAL, 0, 0);
    }


    private void actionOffLine(final long id) {
        FormBody formBody = new FormBody.Builder()
                .add("trip_id", id + "")
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<ActionOffLine> actionOffLineCall = mineApi.action_off_line(formBody);
        actionOffLineCall.enqueue(new Callback<ActionOffLine>() {
            @Override
            public void onResponse(Call<ActionOffLine> call, Response<ActionOffLine> response) {
                if (response.body().getError_code() == 0) {
                    for (int i = 0; i < mDatas.size(); i++) {
                        if (mDatas.get(i).getId() == id) {
                            ToastUtils.showShort("该活动下线成功");
                            remove(i);
                        }
                    }
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ActionOffLine> call, Throwable t) {
                ToastUtils.showShort("该活动下线失败");
            }
        });
    }


    @Override
    protected int getItemLayoutId(int viewType) {

        return R.layout.action_manger_item;
    }


}
