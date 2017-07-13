package com.yuyoubang.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.activity.home.SearchActivity;
import com.yuyoubang.bean.FindRecomendBean;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.view.CircleImageView;
import com.yuyoubang.widget.recycleview.BaseRecycleListViewAdapter;
import com.yuyoubang.widget.recycleview.RecycleViewHolder;
import com.yuyoubang.widget.recycleview.RecyclerListView;

import java.util.List;

/**
 * Created by xiaoyuan on 16/11/09.
 */

public class SearchHistoryAdapter extends BaseRecycleListViewAdapter<String> {


    public SearchHistoryAdapter(RecyclerListView listView, Context context, List<String> list) {
        super(listView, context, list);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_search_history;
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    protected void onBindView(RecycleViewHolder holder, final int position) {
        TextView userName = holder.getView(R.id.tv_user_name);
        ImageView del = holder.getView(R.id.del);
        userName.setText(mData.get(position));

        userName.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                if (TextUtils.isEmpty(mData.get(position))) {
                    return;
                }
                LaunchOperate.openSearchResultActivity(mContext, mData.get(position));
            }
        });

        del.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                for (int i = 0; i < mData.size(); i++) {
                    if (mData.get(i).equals(mData.get(position))) {
                        mData.remove(i);
                    }
                }
                PreferenceUtils.saveList(mContext, mData);
                notifyDataSetChanged();
            }
        });
    }
}
