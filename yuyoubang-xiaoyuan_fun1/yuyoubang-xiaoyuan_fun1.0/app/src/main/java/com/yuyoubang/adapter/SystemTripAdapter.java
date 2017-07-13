package com.yuyoubang.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.bean.SystemTripBean;
import com.yuyoubang.widget.recycleview.BaseRecycleListViewAdapter;
import com.yuyoubang.widget.recycleview.RecycleViewHolder;
import com.yuyoubang.widget.recycleview.RecyclerListView;

import java.util.List;

/**
 * Created by xiaoyuan on 16/11/09.
 */

public class SystemTripAdapter extends BaseRecycleListViewAdapter<SystemTripBean.ResultBean.ListBean> {


    public SystemTripAdapter(RecyclerListView listView, Context context) {
        super(listView, context);
    }

    public SystemTripAdapter(RecyclerListView listView, Context context, List<SystemTripBean.ResultBean.ListBean> list) {
        super(listView, context, list);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.system_trip_item;
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    protected void onBindView(RecycleViewHolder holder, final int position) {
        TextView systemTrip = holder.getView(R.id.system_trip_name);
        ImageView cb = holder.getView(R.id.cb);
        systemTrip.setText(mData.get(position).getData().getTrip_route_type());
        cb.setVisibility(View.GONE);
        if (mData.get(position).getIsChoose() == 1) {
            cb.setVisibility(View.VISIBLE);
        } else {
            cb.setVisibility(View.GONE);
        }
    }
}
