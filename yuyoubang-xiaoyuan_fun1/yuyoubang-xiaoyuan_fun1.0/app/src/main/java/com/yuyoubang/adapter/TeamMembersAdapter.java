package com.yuyoubang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.bean.TeamsMember;
import com.yuyoubang.config.UrlConfig;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by xiaoyuan on 16/10/18.
 */
public class TeamMembersAdapter extends BaseAdapter {
    private ViewHolder viewHolder;
    private List<TeamsMember.ResultBean.ListBean> data = new ArrayList<>();
    private Context mContext;
    private String mDdvice;


    public TeamMembersAdapter(Context context, String advice) {
        this.mContext = context;
        this.mDdvice = advice;
    }

    public void setData(List<TeamsMember.ResultBean.ListBean> data) {
        if (data != null) {
            this.data = data;
        }
    }

    public List<TeamsMember.ResultBean.ListBean> getData() {
        return data;
    }

    public void delData(int position) {
        this.data.remove(position);
        notifyDataSetChanged();
    }

    public void addData(TeamsMember.ResultBean.ListBean data) {
        this.data.add(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mDdvice.equals("flag")) {
            return data.size() + 1;
        } else {
            return data.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.act_team_member_item, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.lable_name);
            viewHolder.labelLayout = (FrameLayout) convertView.findViewById(R.id.lable_layout);
            viewHolder.addLable = (ImageView) convertView.findViewById(R.id.add_label_add);
            viewHolder.trip_like_pic = (CircleImageView) convertView.findViewById(R.id.trip_like_pic);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (mDdvice.equals("flag")) {
            viewHolder.addLable.setVisibility(View.VISIBLE);
        } else {
            viewHolder.addLable.setVisibility(View.GONE);
        }
        if (position == data.size()) {
            viewHolder.addLable.setVisibility(View.VISIBLE);
            viewHolder.name.setVisibility(View.GONE);
            viewHolder.trip_like_pic.setVisibility(View.GONE);
        } else {
            viewHolder.addLable.setVisibility(View.GONE);
            viewHolder.name.setVisibility(View.VISIBLE);
            viewHolder.name.setText(data.get(position).getUser_data().getUser_name());
            ImageLoader.getInstance().displayImage(data.get(position).getUser_data().getProfile_pic_url(), viewHolder.trip_like_pic, ImageOption.defaultOptions);

        }
        return convertView;
    }

    private class ViewHolder {
        TextView name;
        ImageView addLable;
        CircleImageView trip_like_pic;
        FrameLayout labelLayout;
    }
}
