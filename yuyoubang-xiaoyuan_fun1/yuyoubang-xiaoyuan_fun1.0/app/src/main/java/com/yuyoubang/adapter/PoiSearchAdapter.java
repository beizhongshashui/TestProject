package com.yuyoubang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.yuyoubang.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongchen on 16/12/29.
 */

public class PoiSearchAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    private Context mContext;
    private List<PoiItem> popBeen = new ArrayList<>();

    public PoiSearchAdapter(Context mContext, List<PoiItem> list) {
        mInflater = LayoutInflater.from(mContext);
        this.popBeen = list;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return popBeen.size();
    }

    @Override
    public PoiItem getItem(int position) {
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
            convertView = mInflater.inflate(R.layout.adapter_poi_item, parent, false);
            viewHolder.tv_pop = (TextView) convertView.findViewById(R.id.tv_pop);
            viewHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_pop.setText(popBeen.get(position).getTitle());
//        viewHolder.tv_content.setText(popBeen.get(position).getCityName() + popBeen.get(position).getAdName() + popBeen.get(position).getTitle());
        viewHolder.tv_content.setText(popBeen.get(position).getSnippet());

        return convertView;
    }

    private class ViewHolder {
        TextView tv_pop;
        TextView tv_content;
    }

}
