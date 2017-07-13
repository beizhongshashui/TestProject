package com.yuyoubang.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.exceptions.HyphenateException;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.fragment.message.ChatActivity;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.view.RoundImageView;
import com.yuyoubang.widget.recycleview.BaseRecycleListViewAdapter;
import com.yuyoubang.widget.recycleview.RecycleViewHolder;
import com.yuyoubang.widget.recycleview.RecyclerListView;

import java.util.List;

/**
 * Created by hongchen on 16/10/25.
 */

public class JoinTeamsAdapter extends BaseRecycleListViewAdapter<EMGroup> {

    EMGroup group = null;

    public JoinTeamsAdapter(RecyclerListView listView, Context context, List<EMGroup> data) {
        super(listView, context, data);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_join_team;
    }

    @Override
    protected void onBindView(RecycleViewHolder holder, final int position) {
        LinearLayout rootLayout = holder.getView(R.id.root_layout);
        final TextView tvNum = holder.getView(R.id.tv_team_name);
        RoundImageView teamAvatar = holder.getView(R.id.iv_avatar);
        View line = holder.getView(R.id.line_right_arrow_blue);

        try {
//            group = EMClient.getInstance().groupManager().getGroup(mData.get(position).getGroupId());
//            if (group == null) {
                new Thread(new Runnable() {

                    public void run() {
                        //get detail from server
                        try {
                            group = EMClient.getInstance().groupManager().getGroupFromServer(mData.get(position).getGroupId());
                            ((Activity)mContext).runOnUiThread(new Runnable() {
                                public void run() {
                                    tvNum.setText(mData.get(position).getGroupName() + "(" + group.getMemberCount() + ")");
                                }
                            });
                        } catch (final HyphenateException e) {
                            e.printStackTrace();
                            ((Activity)mContext).runOnUiThread(new Runnable() {
                                public void run() {
                                }
                            });
                        }

                    }
                }).start();
            /*}else {
                tvNum.setText(mData.get(position).getGroupName() + "(" + group.getMemberCount() + ")");
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mData.get(position).getDescription().equals("")) {
            teamAvatar.setImageResource(R.mipmap.icon_teams);
        } else {
            ImageLoader.getInstance().displayImage(mData.get(position).getDescription(), teamAvatar, ImageOption.default_group_avatar);
        }

        if (position == mData.size() - 1) {
            line.setVisibility(View.GONE);
        } else {
            line.setVisibility(View.VISIBLE);
        }
        rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatActivity.start(mContext, mData.get(position).getGroupId(), ChatActivity.Group);
            }
        });
    }
}
