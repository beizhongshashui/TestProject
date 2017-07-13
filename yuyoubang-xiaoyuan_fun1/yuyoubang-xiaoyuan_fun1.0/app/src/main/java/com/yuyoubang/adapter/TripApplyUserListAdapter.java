package com.yuyoubang.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.yuyoubang.R;
import com.yuyoubang.bean.mine.ActionDetail;
import com.yuyoubang.widget.recycleview.BaseRecycleListViewAdapter;
import com.yuyoubang.widget.recycleview.RecycleViewHolder;
import com.yuyoubang.widget.recycleview.RecyclerListView;

import java.util.List;

/**
 * Created by xiaoyuan on 16/11/09.
 */

public class TripApplyUserListAdapter extends BaseRecycleListViewAdapter<ActionDetail.ResultBean.ListBean> {


    public TripApplyUserListAdapter(RecyclerListView listView, Context context) {
        super(listView, context);
    }
    public TripApplyUserListAdapter(RecyclerListView listView, Context context, List<ActionDetail.ResultBean.ListBean> list) {
        super(listView, context,list);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.trip_like_item;
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    protected void onBindView(RecycleViewHolder holder, final int position) {
        ImageView tripLikePic = holder.getView(R.id.trip_like_pic);
//        ImageLoader.getInstance().displayImage(mData.get(position).getUser().getUser_data().getProfile_pic_url(),tripLikePic, ImageOption.defaultOptions);

    }
}
