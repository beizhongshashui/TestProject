package com.yuyoubang.activity.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.yuyoubang.R;
import com.yuyoubang.adapter.MineCommentAdapter;
import com.yuyoubang.adapter.SerachUserAdapter;
import com.yuyoubang.bean.SearchUser;
import com.yuyoubang.bean.mine.NoCommend;
import com.yuyoubang.fragment.base.BaseNetFragment;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.HomeApi;
import com.yuyoubang.network.api.MineApi;
import com.yuyoubang.otto.AppBus;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.widget.adapter.interfaces.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;

/**
 * Created by hongchen on 16/12/7.
 */

public class SearchUserFragment extends BaseNetFragment<SearchUser> {

    private SerachUserAdapter mAdapter;
    private String cursor;

    private List<SearchUser.ResultBean.ListBean> mData = new ArrayList<>();
    private SwipeRefreshLayout findFollowSp;
    private FrameLayout null_comment;
    private String content;

    public static SearchUserFragment newInstance(String content) {
        SearchUserFragment fragment = new SearchUserFragment();
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
        mAdapter = new SerachUserAdapter(getActivity(), null, true);
        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                getData(mData.get(mData.size() - 1).getId() + "");
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
                    .add("uid", PreferenceUtils.getsessionId(getActivity()))
                    .build();
        } else {
            body = new FormBody.Builder()
                    .add("search_str", content)
                    .add("uid", PreferenceUtils.getsessionId(getActivity()))
                    .add("cursor", cursor)
                    .build();
        }
        Call<SearchUser> searchUserCall = homeApi.search_user(body);
        searchUserCall.enqueue(this);

        findFollowSp.setRefreshing(false);
        mAdapter.setLoadingView(R.layout.load_more_layout);
    }


    @Override
    protected void processData(SearchUser searchUser) {
        goneLoading();
        if (TextUtils.isEmpty(cursor)) {
            if (searchUser.getResult() == null || searchUser.getResult().getList().size() == 0) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                null_comment.setVisibility(View.VISIBLE);
                return;
            }
            mData.clear();
            mData.addAll(searchUser.getResult().getList());
            mAdapter.setNewData(mData);
            findFollowSp.setRefreshing(false);
            mAdapter.setLoadingView(R.layout.load_more_layout);
            if (searchUser.getResult().getList() == null || searchUser.getResult().getList().size() == 0 || searchUser.getResult().getList().size() < 10) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
        } else {
            if (searchUser.getResult().getList() == null || searchUser.getResult().getList().size() == 0) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
            mData.addAll(searchUser.getResult().getList());
            mAdapter.setLoadMoreData(mData);

        }
    }

    @Override
    protected int getContentResId() {
        return R.layout.frag_commend;
    }
}
