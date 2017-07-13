package com.yuyoubang.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.yuyoubang.R;
import com.yuyoubang.bean.ActionInfoBean;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.base.MultiBaseAdapter;

import java.util.List;

/**
 * @author xiaoyuan
 */

public class ActionInfoDescAdapter extends MultiBaseAdapter<ActionInfoBean.ResultBean.TripBean> {
    private Context context;

    public ActionInfoDescAdapter(Context context, List<ActionInfoBean.ResultBean.TripBean> listBean, boolean isOpenLoadMore) {
        super(context, listBean, isOpenLoadMore);
        this.context = context;

    }

    @Override
    protected int getViewType(int position, ActionInfoBean.ResultBean.TripBean data) {
        return 1;

    }


    @Override
    protected void convert(ViewHolder holder, ActionInfoBean.ResultBean.TripBean data, int viewType) {
        holder.setText(R.id.action_desc,data.getData().getTrip_intro());
        holder.setText(R.id.action_fit,data.getData().getTrip_equipment_intro());

        //zhangyushuai
        if(TextUtils.isEmpty(data.getData().getOther_desc())){
            holder.getView(R.id.other).setVisibility(View.GONE);
            holder.getView(R.id.action_other).setVisibility(View.GONE);
        }else {
            holder.getView(R.id.other).setVisibility(View.VISIBLE);
            holder.getView(R.id.action_other).setVisibility(View.VISIBLE);
            holder.setText(R.id.other,data.getData().getOther_desc());
        }

    }


    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.info_action_desc_item;
    }



}
