package com.yuyoubang.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.bean.ActionInfoBean;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.view.CircleImageView;
import com.yuyoubang.widget.recycleview.BaseRecycleListViewAdapter;
import com.yuyoubang.widget.recycleview.RecycleViewHolder;
import com.yuyoubang.widget.recycleview.RecyclerListView;

import java.util.List;

/**
 * Created by xiaoyuan on 16/11/09.
 */

public class TripLikeAdapter extends BaseRecycleListViewAdapter<ActionInfoBean.ResultBean.LikeUserListBean> {


    public TripLikeAdapter(RecyclerListView listView, Context context) {
        super(listView, context);
    }

    public TripLikeAdapter(RecyclerListView listView, Context context, List<ActionInfoBean.ResultBean.LikeUserListBean> list) {
        super(listView, context, list);
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
        CircleImageView tripLikePic = holder.getView(R.id.trip_like_pic);
        ImageLoader.getInstance().displayImage(mData.get(position).getUser().getUser_data().getProfile_pic_url(), tripLikePic, ImageOption.defaultOptions);

        tripLikePic.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                LaunchOperate.openOtherHome(mContext, String.valueOf(mData.get(position).getUid()));

            }
        });
    }
}
