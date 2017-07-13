package com.yuyoubang.activity.mine;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseNetActivity;
import com.yuyoubang.adapter.MineToTaiAdapter;
import com.yuyoubang.bean.mine.MineDoTan;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.MineApi;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.interfaces.OnLoadMoreListener;
import com.yuyoubang.widget.adapter.interfaces.OnMultiItemClickListeners;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;

/**
 * Created by hongchen on 16/12/1.
 */

public class MineDoTanActivity extends BaseNetActivity<MineDoTan> {

    private SwipeRefreshLayout findFollowSp;
    private MineToTaiAdapter mAdapter;
    private String cursor;
    private List<MineDoTan.ResultBean.ListBean> mData = new ArrayList<>();
    private FrameLayout null_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        null_comment = getViewById(R.id.null_comment);
        findFollowSp = getViewById(R.id.find_follow_sp);
        RecyclerView findFollowRv = getViewById(R.id.find_follow_rv);
        findFollowSp.setColorSchemeColors(this.getResources().getColor(R.color.color_ff9600));
        findFollowSp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData("");

            }
        });
        //初始化adapter
        mAdapter = new MineToTaiAdapter(this, null, true);
        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                getData(mData.get(mData.size() - 1).getId() + "");
            }
        });
        mAdapter.setOnMultiItemClickListener(new OnMultiItemClickListeners<MineDoTan.ResultBean.ListBean>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, MineDoTan.ResultBean.ListBean data, int position, int viewType) {
                if(data != null){
                    MineStatusActivity.start(MineDoTanActivity.this, data);
                }
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        findFollowRv.setLayoutManager(layoutManager);
        findFollowRv.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
        getData("");
    }

    private void getData(final String cursor) {
        this.cursor = cursor;
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        FormBody body;
        if (TextUtils.isEmpty(cursor)) {
            body = new FormBody.Builder().add("uid", PreferenceUtils.getsessionId(this)).build();
        } else {
            body = new FormBody.Builder().add("cursor", cursor).add("uid", PreferenceUtils.getsessionId(this)).build();
        }
        Call<MineDoTan> mineDoTanCall = mineApi.my_stats_list(body);
        mineDoTanCall.enqueue(this);

        findFollowSp.setRefreshing(false);
        mAdapter.setLoadingView(R.layout.load_more_layout);
    }

    @Override
    protected void processData(MineDoTan mineDoTan) {
        goneLoading();
        if (TextUtils.isEmpty(cursor)) {
            if (mineDoTan.getResult() == null || mineDoTan.getResult().getList().size() == 0) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                null_comment.setVisibility(View.VISIBLE);
                return;
            }
            mData.clear();
            mData.addAll(mineDoTan.getResult().getList());
            mAdapter.setNewData(mData);
            findFollowSp.setRefreshing(false);
            mAdapter.setLoadingView(R.layout.load_more_layout);
            if (mineDoTan.getResult().getList() == null || mineDoTan.getResult().getList().size() == 0 || mineDoTan.getResult().getList().size() < 5) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
        } else {
            if (mineDoTan.getResult().getList() == null || mineDoTan.getResult().getList().size() == 0) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
            mData.addAll(mineDoTan.getResult().getList());
            mAdapter.setLoadMoreData(mData);
        }
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_mine_dong_tai;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("我的动态");
    }
}
