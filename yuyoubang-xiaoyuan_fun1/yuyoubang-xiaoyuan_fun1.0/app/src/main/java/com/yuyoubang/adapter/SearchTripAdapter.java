package com.yuyoubang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.bean.SearchTrip;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.utils.DateUtils;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.base.MultiBaseAdapter;

import java.util.List;

/**
 * Created by hongchen on 16/12/7.
 */

public class SearchTripAdapter extends MultiBaseAdapter<SearchTrip.ResultBean.ListBean> {
    private Context context;

    public SearchTripAdapter(Context context, List<SearchTrip.ResultBean.ListBean> listBean, boolean isOpenLoadMore) {
        super(context, listBean, isOpenLoadMore);
        this.context = context;
    }

    @Override
    protected int getViewType(int position, SearchTrip.ResultBean.ListBean data) {
        return 1;
    }

    @Override
    protected void convert(ViewHolder holder, final SearchTrip.ResultBean.ListBean data, int viewType) {
        ImageView imageView = holder.getView(R.id.iv_pic);
        TextView tv_company = holder.getView(R.id.tv_company);

        ImageLoader.getInstance().displayImage(data.getData().getTrip_cover_pic(), imageView, ImageOption.default_trip_options);
        holder.setText(R.id.tv_title, data.getData().getTrip_name());
        holder.setText(R.id.tv_time, DateUtils.getDateToMonth(data.getData().getStart_time()) + "一" + DateUtils.getDateToMonth(data.getData().getEnd_time()));
        holder.setText(R.id.tv_price, data.getData().getTrip_price() + "");
        holder.setText(R.id.tv_apply, data.getData().getParticipants_female_count() + data.getData().getParticipants_male_count() + "人报名");
        if (data.getUser_result() != null) {
            holder.setText(R.id.tv_biz_user_name, data.getUser_result().getUser_data().getUser_name());
            holder.setText(R.id.tv_days, "畅游" + data.getData().getTrip_days() + "天");
        }
        if (data.getUser_result() != null) {
            if (data.getUser_result().getUser_data() != null) {
                if (data.getUser_result().getUser_data().getVerification_type() != null) {
                    if (Integer.parseInt(data.getUser_result().getUser_data().getVerification_type()) == 2) {
                        tv_company.setVisibility(View.VISIBLE);
                    } else {
                        tv_company.setVisibility(View.GONE);
                    }
                } else {
                    tv_company.setVisibility(View.GONE);
                }
            }
        }
        LinearLayout llLabel = holder.getView(R.id.ll_label);
        llLabel.removeAllViews();
        for (int i = 0; i < data.getData().getTrip_tags().size(); i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 0, 0, 0);
            TextView textView = new TextView(context);
            textView.setText(data.getData().getTrip_tags().get(i));
            textView.setTextColor(context.getResources().getColor(R.color.color_ff9600));
            textView.setBackgroundResource(R.drawable.yellow_circle);
            llLabel.addView(textView, params);
        }

    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_search_trip_item;
    }
}
