package com.yuyoubang.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.bean.FindRecomendBean;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.view.CircleImageView;
import com.yuyoubang.widget.recycleview.BaseRecycleListViewAdapter;
import com.yuyoubang.widget.recycleview.RecycleViewHolder;
import com.yuyoubang.widget.recycleview.RecyclerListView;

import java.util.List;

/**
 * Created by xiaoyuan on 16/11/09.
 */

public class FindFollowMoreAdapter extends BaseRecycleListViewAdapter<FindRecomendBean.ResultBean.ListBean> {


    public FindFollowMoreAdapter(RecyclerListView listView, Context context) {
        super(listView, context);
    }

    public FindFollowMoreAdapter(RecyclerListView listView, Context context, List<FindRecomendBean.ResultBean.ListBean> list) {
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
        TextView userName = holder.getView(R.id.tv_user_name);
        if (mData.get(position).getUser() != null) {
            if (TextUtils.isEmpty(mData.get(position).getUser().getUser_data().getUser_name())) {
                return;
            }
            userName.setText(mData.get(position).getUser().getUser_data().getUser_name());
            ImageLoader.getInstance().displayImage(mData.get(position).getUser().getUser_data().getProfile_pic_url(), tripLikePic, ImageOption.defaultOptions);


            tripLikePic.setOnClickListener(new OnClickListener() {
                @Override
                protected void clickOperate() {
                    LaunchOperate.openOtherHome(mContext, String.valueOf(mData.get(position).getUser().getId()));
                }
            });
        }
    }
}
