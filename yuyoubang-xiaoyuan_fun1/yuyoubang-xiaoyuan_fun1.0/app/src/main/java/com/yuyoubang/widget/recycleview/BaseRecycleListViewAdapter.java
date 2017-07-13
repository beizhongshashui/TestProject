package com.yuyoubang.widget.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * date: Created by hongchen on 16/10/25.
 */
public abstract class BaseRecycleListViewAdapter<T> extends RecyclerView.Adapter<RecycleViewHolder>
        implements View.OnClickListener, View.OnLongClickListener {
    protected Context mContext;
    protected List<T> mData = new ArrayList<>();
    private RecyclerListView mListView;

    private OnItemClickListener mItemClickListener;
    private OnItemLongClickListener mItemLongClickListener;

    private BaseRecycleListViewAdapter() {
    }

    public BaseRecycleListViewAdapter(RecyclerListView listView, Context context) {
        this.mListView = listView;
        this.mContext = context;
        mData = new ArrayList<>();
    }

    public BaseRecycleListViewAdapter(RecyclerListView listView, Context context, List<T> data) {
        this.mListView = listView;
        this.mContext = context;
        if (data == null || data.size() == 0) {
            this.mData = new ArrayList<>();
        } else {
            this.mData = data;
        }
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(getItemLayoutResId(viewType), parent, false);
        itemView.setOnClickListener(this);
        RecycleViewHolder holder = new RecycleViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        onBindView(holder, position);
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addALl(List<T> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void addALl(int index, List<T> data) {
        mData.addAll(index, data);
        notifyDataSetChanged();
    }

    public void add(T t) {
        mData.add(t);
        notifyDataSetChanged();
    }

    public void add(int index, T t) {
        mData.add(index, t);
        notifyDataSetChanged();
    }

    public void replaceAll(List<T> data) {
        mData.clear();
        addALl(data);
    }


    public void setmData(List<T> t){
        mData = t;
        notifyDataSetChanged();
    }
    public List<T> getData() {
        return mData;
    }


    public T getItem(int position) {
        return mData.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mItemLongClickListener = mOnItemLongClickListener;
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener == null) {
            return;
        }
        int pos = mListView.getChildAdapterPosition(v);
        mItemClickListener.onItemClick(v, pos);
    }

    @Override
    public boolean onLongClick(View v) {
        if (mItemLongClickListener == null) {
            return false;
        }
        int pos = mListView.getChildAdapterPosition(v);
        mItemLongClickListener.onItemLongClick(v, pos);
        return true;
    }

    protected abstract int getItemLayoutResId(int viewType);

    protected abstract void onBindView(RecycleViewHolder holder, int position);
}
