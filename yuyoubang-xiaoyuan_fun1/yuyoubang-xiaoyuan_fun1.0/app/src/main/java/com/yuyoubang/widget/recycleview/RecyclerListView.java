package com.yuyoubang.widget.recycleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.yuyoubang.R;


/**
 * date: Created by hongchen on 16/10/25.
 */
public class RecyclerListView extends RecyclerView {
    private BaseRecycleListViewAdapter mAdapter;

    private OnItemClickListener mItemClickListener;
    private OnItemLongClickListener mItemLongClickListener;

    private LinearLayoutManager mLayoutManager;

    private ScrollListener mScrollListener;

    public RecyclerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    public RecyclerListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs){
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RecyclerListView);
        int orientation = ta.getInt(R.styleable.RecyclerListView_orientation, 1);
        switch (orientation){
            case 0:
                mLayoutManager = new LinearLayoutManager(context, HORIZONTAL, false);
                break;
            case 1:
                mLayoutManager = new LinearLayoutManager(context, VERTICAL, false);
                break;
        }
        setLayoutManager(mLayoutManager);
    }

    public void setScrollListener(ScrollListener listener){
        this.mScrollListener = listener;
        setListener();
    }

    private void setListener(){
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (mLayoutManager.findLastCompletelyVisibleItemPosition()
                        == recyclerView.getAdapter().getItemCount() - 1) {
                    mScrollListener.scrollToBottom();
                }
            }
        });
    }

    public interface ScrollListener{
        void scrollToBottom();
    }

    @Override
    public void setAdapter(Adapter adapter) {
        mAdapter = (BaseRecycleListViewAdapter) adapter;
        if (mItemClickListener != null){
            mAdapter.setOnItemClickListener(mItemClickListener);
        }
        if (mItemLongClickListener != null){
            mAdapter.setOnItemLongClickListener(mItemLongClickListener);
        }
        super.setAdapter(adapter);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        try {
            mAdapter = (BaseRecycleListViewAdapter) getAdapter();
            if (null != mAdapter) {
                mAdapter.setOnItemClickListener(listener);
            } else {
                this.mItemClickListener = listener;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        try {
            mAdapter = (BaseRecycleListViewAdapter) getAdapter();
            if (null != mAdapter) {
                mAdapter.setOnItemLongClickListener(listener);
            } else {
                this.mItemLongClickListener = listener;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void scrollTo(int pos, int offset){
        mLayoutManager.scrollToPositionWithOffset(pos, offset);
    }

}
