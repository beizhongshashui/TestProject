package com.yuyoubang.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.activity.sort.SortModel;
import com.yuyoubang.bean.FindRecomendBean;
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

public class SetTeamsUserAvatarAdapter extends BaseRecycleListViewAdapter<SortModel> {

    public SetTeamsUserAvatarAdapter(RecyclerListView listView, Context context, List<SortModel> list) {
        super(listView, context, list);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_teams_user_item;
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    protected void onBindView(RecycleViewHolder holder, final int position) {
        CircleImageView tripLikePic = holder.getView(R.id.trip_like_pic);

        if (mData.get(position).getIsChoose() == 1) {
            tripLikePic.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage(mData.get(position).getAvatarUrl(), tripLikePic, ImageOption.defaultOptions);
        } else {
            tripLikePic.setVisibility(View.GONE);
        }


        /*tripLikePic.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                LaunchOperate.openOtherHome(mContext, String.valueOf(mData.get(position).getUser().getId()));
            }
        });*/
    }
}
