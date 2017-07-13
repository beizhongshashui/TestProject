package com.yuyoubang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.bean.SystemTripBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyuan on 16/11/10.
 */
public class PopTypeAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private Context mContext;
    private List<String> popBeen = new ArrayList<>();

    public PopTypeAdapter(Context mContext, List<String> list) {
        mInflater = LayoutInflater.from(mContext);
        this.popBeen = list;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return popBeen.size();
    }

    @Override
    public String getItem(int position) {
        return popBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private ViewHolder viewHolder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.pop_list_item,
                    parent, false);
            viewHolder.title = (TextView) convertView
                    .findViewById(R.id.tv_pop);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(popBeen.get(position));
        return convertView;
    }

    private class ViewHolder {
        TextView title;
    }

}




