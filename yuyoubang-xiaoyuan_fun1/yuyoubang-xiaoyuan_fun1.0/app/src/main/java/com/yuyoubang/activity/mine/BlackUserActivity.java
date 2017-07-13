package com.yuyoubang.activity.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseNetActivity;
import com.yuyoubang.adapter.FindMoreLikeUserAdapter;
import com.yuyoubang.bean.findfollowinfo.MoreLikeList;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.FindApi;
import com.yuyoubang.widget.adapter.interfaces.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;

/**
 * Created by hongchen on 16/11/30.
 */

public class BlackUserActivity extends BaseNetActivity<MoreLikeList> {
    private FindMoreLikeUserAdapter mAdapter;
    private String cursor;

    private List<MoreLikeList.ResultBean.ListBean> mData = new ArrayList<>();
    private SwipeRefreshLayout findFollowSp;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
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
        mAdapter = new FindMoreLikeUserAdapter(this, null, true, "1000");
        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                getData(mData.get(mData.size() - 1).getId() + "");
            }
        });
        /*mAdapter.setOnMultiItemClickListener(new OnMultiItemClickListeners<FindFollowBean.ResultBean.ListBean>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, FindFollowBean.ResultBean.ListBean data, int position, int viewType) {
                if (data != null) {
                    FindInfoActivity.start(this, data);
                }
            }
        });*/
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        findFollowRv.setLayoutManager(layoutManager);
        findFollowRv.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
        getData("");
    }

    @Override
    protected void processData(MoreLikeList moreLikeList) {
        goneLoading();
        if (TextUtils.isEmpty(cursor)) {
            if (moreLikeList.getResult() == null || moreLikeList.getResult().getList().size() == 0) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
            mData.clear();
            mData.addAll(moreLikeList.getResult().getList());
            mAdapter.setNewData(mData);
            findFollowSp.setRefreshing(false);
            mAdapter.setLoadingView(R.layout.load_more_layout);
            if (moreLikeList.getResult().getList() == null || moreLikeList.getResult().getList().size() == 0 || moreLikeList.getResult().getList().size() < 5) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
        } else {
            if (moreLikeList.getResult().getList() == null || moreLikeList.getResult().getList().size() == 0) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
            mData.addAll(moreLikeList.getResult().getList());
            mAdapter.setLoadMoreData(mData);

        }
    }

    private void getData(final String cursor) {
        uid = getIntent().getStringExtra("uid");
        this.cursor = cursor;
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        FormBody body;
        if (TextUtils.isEmpty(cursor)) {
            body = new FormBody.Builder().add("uid", uid).build();
        } else {
            body = new FormBody.Builder().add("cursor", cursor).add("uid", uid).build();
        }
        Call<MoreLikeList> find = findApi.my_follow_list(body);
        find.enqueue(this);

        findFollowSp.setRefreshing(false);
        mAdapter.setLoadingView(R.layout.load_more_layout);
    }


    @Override
    protected int getContentResId() {
        return R.layout.act_more_like_list;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setBackGround(Color.WHITE);
        builder.setTitle("黑名单");
        builder.setLeftOperate(R.mipmap.back_black, new OnClickListener() {
            @Override
            protected void clickOperate() {
                finish();
            }
        });
        builder.setTitleColor(Color.BLACK);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
