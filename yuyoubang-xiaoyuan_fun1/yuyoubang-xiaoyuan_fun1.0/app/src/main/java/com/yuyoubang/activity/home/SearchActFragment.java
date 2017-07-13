package com.yuyoubang.activity.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.yuyoubang.R;
import com.yuyoubang.adapter.MineNoCommentAdapter;
import com.yuyoubang.adapter.SearchTripAdapter;
import com.yuyoubang.bean.SearchTrip;
import com.yuyoubang.bean.mine.NoCommend;
import com.yuyoubang.fragment.base.BaseNetFragment;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.HomeApi;
import com.yuyoubang.network.api.MineApi;
import com.yuyoubang.otto.AppBus;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.interfaces.OnLoadMoreListener;
import com.yuyoubang.widget.adapter.interfaces.OnMultiItemClickListeners;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;

/**
 * Created by hongchen on 16/12/7.
 */

public class SearchActFragment extends BaseNetFragment<SearchTrip> {

    private SearchTripAdapter mAdapter;
    private String cursor;

    private List<SearchTrip.ResultBean.ListBean> mData = new ArrayList<>();
    private SwipeRefreshLayout findFollowSp;
    private FrameLayout null_comment;
    private String content;

    public static SearchActFragment newInstance(String content) {
        SearchActFragment fragment = new SearchActFragment();
        Bundle bundle = new Bundle();
        bundle.putString("content", content);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppBus.getInstance().register(this);
    }

    @Override
    protected void initViews() {
        findFollowSp = getViewById(R.id.cancel_order_sp);
        RecyclerView findFollowRv = getViewById(R.id.cancel_order_rv);
        null_comment = getViewById(R.id.null_comment);
        findFollowSp.setColorSchemeColors(getActivity().getResources().getColor(R.color.color_ff9600));
        findFollowSp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData("");

            }
        });
        //初始化adapter
        mAdapter = new SearchTripAdapter(getActivity(), null, true);
        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                getData(mData.get(mData.size() - 1).getId() + "");
            }
        });
        mAdapter.setOnMultiItemClickListener(new OnMultiItemClickListeners<SearchTrip.ResultBean.ListBean>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, SearchTrip.ResultBean.ListBean data, int position, int viewType) {
                InfoActivity.start(getActivity(), data.getId() + "");
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        findFollowRv.setLayoutManager(layoutManager);
        findFollowRv.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
        getData("");
    }

    private void getData(final String cursor) {
        content = getArguments().getString("content");
        this.cursor = cursor;
        HomeApi homeApi = RetrofitManager.getTestRetrofit().create(HomeApi.class);
        FormBody body;
        if (TextUtils.isEmpty(cursor)) {
            body = new FormBody.Builder()
                    .add("search_str", content)
                    .build();
        } else {
            body = new FormBody.Builder()
                    .add("search_str", content)
                    .add("cursor", cursor)
                    .build();
        }
        Call<SearchTrip> searchTripCall = homeApi.search_trip(body);
        searchTripCall.enqueue(this);

        findFollowSp.setRefreshing(false);
        mAdapter.setLoadingView(R.layout.load_more_layout);
    }


    @Override
    protected void processData(SearchTrip searchTrip) {
        goneLoading();
        if (TextUtils.isEmpty(cursor)) {
            if (searchTrip.getResult() == null || searchTrip.getResult().getList().size() == 0) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                null_comment.setVisibility(View.VISIBLE);
                return;
            }
            mData.clear();
            mData.addAll(searchTrip.getResult().getList());
            mAdapter.setNewData(mData);
            findFollowSp.setRefreshing(false);
            mAdapter.setLoadingView(R.layout.load_more_layout);
            if (searchTrip.getResult().getList() == null || searchTrip.getResult().getList().size() == 0 || searchTrip.getResult().getList().size() < 5) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
        } else {
            if (searchTrip.getResult().getList() == null || searchTrip.getResult().getList().size() == 0) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
            mData.addAll(searchTrip.getResult().getList());
            mAdapter.setLoadMoreData(mData);

        }
    }

    @Override
    protected int getContentResId() {
        return R.layout.frag_no_commend;
    }
}
