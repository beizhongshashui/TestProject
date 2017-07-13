package com.yuyoubang.widget.recycleview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * date: Created by hongchen on 16/10/25.
 */
public class RecycleViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views = new SparseArray<View>();

    public RecycleViewHolder(View itemView) {
        super(itemView);
    }

    public <T extends View> T getView(int resId) {
        View v = views.get(resId);
        if (null == v) {
            v = itemView.findViewById(resId);
            views.put(resId, v);
        }
        return (T) v;
    }

}
