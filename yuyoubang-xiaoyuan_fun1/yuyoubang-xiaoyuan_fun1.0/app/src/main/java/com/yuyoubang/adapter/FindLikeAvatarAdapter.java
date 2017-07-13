package com.yuyoubang.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.bean.FindFollowInfoBean;
import com.yuyoubang.bean.FindRecomendBean;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.widget.recycleview.BaseRecycleListViewAdapter;
import com.yuyoubang.widget.recycleview.RecycleViewHolder;
import com.yuyoubang.widget.recycleview.RecyclerListView;

import java.util.List;

/**
 * Created by xiaoyuan on 16/11/09.
 */

public class FindLikeAvatarAdapter extends BaseRecycleListViewAdapter<String> {


    public FindLikeAvatarAdapter(RecyclerListView listView, Context context, List<String> list) {
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
        ImageView tripLikePic = holder.getView(R.id.trip_like_pic);
        TextView userName = holder.getView(R.id.tv_user_name);
        userName.setVisibility(View.GONE);
        /*if (TextUtils.isEmpty(mData.get(position).getUser().getUser_data().getUser_name())) {
            return;
        }
        userName.setText(mData.get(position).getUser().getUser_data().getUser_name());*/
        if (position == 0){
            ImageLoader.getInstance().displayImage(mData.get(position), tripLikePic, ImageOption.default_like);
        }else {
            ImageLoader.getInstance().displayImage(mData.get(position), tripLikePic, ImageOption.defaultOptions);
        }
    }
}
