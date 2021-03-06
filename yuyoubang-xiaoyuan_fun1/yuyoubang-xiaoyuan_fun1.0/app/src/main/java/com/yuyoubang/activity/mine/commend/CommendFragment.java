package com.yuyoubang.activity.mine.commend;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.adapter.MineCommentAdapter;
import com.yuyoubang.adapter.MineNoCommentAdapter;
import com.yuyoubang.bean.mine.Comment;
import com.yuyoubang.bean.mine.NoCommend;
import com.yuyoubang.fragment.base.BaseNetFragment;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.MineApi;
import com.yuyoubang.otto.AppBus;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.widget.adapter.interfaces.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;

/**
 * Created by hongchen on 16/12/7.
 */

public class CommendFragment extends BaseNetFragment<Comment> {

    private MineCommentAdapter mAdapter;
    private String cursor;

    private List<Comment.ResultBean.ListBean> mData = new ArrayList<>();
    private SwipeRefreshLayout findFollowSp;
    private FrameLayout null_comment;
    private TextView empty_text;

    public static CommendFragment newInstance() {
        CommendFragment fragment = new CommendFragment();
        Bundle bundle = new Bundle();
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
        empty_text = getViewById(R.id.empty_text);
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
        mAdapter = new MineCommentAdapter(getActivity(), null, true);
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
        this.cursor = cursor;
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        FormBody body;
        if (TextUtils.isEmpty(cursor)) {
            body = new FormBody.Builder()
                    .add("uid", PreferenceUtils.getsessionId(getActivity()))
                    .build();
        } else {
            body = new FormBody.Builder()
                    .add("uid", PreferenceUtils.getsessionId(getActivity()))
                    .add("cursor", cursor)
                    .build();
        }
        Call<Comment> noCommendCall = mineApi.comment_list(body);
        noCommendCall.enqueue(this);

        findFollowSp.setRefreshing(false);
        mAdapter.setLoadingView(R.layout.load_more_layout);
    }


    @Override
    protected void processData(Comment noCommend) {
        goneLoading();
        if (TextUtils.isEmpty(cursor)) {
            if (noCommend.getResult() == null || noCommend.getResult().getList().size() == 0) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                null_comment.setVisibility(View.VISIBLE);
                empty_text.setText("暂无已评价订单");
                return;
            }
            mData.clear();
            mData.addAll(noCommend.getResult().getList());
            mAdapter.setNewData(mData);
            findFollowSp.setRefreshing(false);
            mAdapter.setLoadingView(R.layout.load_more_layout);
            if (noCommend.getResult().getList() == null || noCommend.getResult().getList().size() == 0 || noCommend.getResult().getList().size() < 5) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
        } else {
            if (noCommend.getResult().getList() == null || noCommend.getResult().getList().size() == 0) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
            mData.addAll(noCommend.getResult().getList());
            mAdapter.setLoadMoreData(mData);

        }
    }

    @Override
    protected int getContentResId() {
        return R.layout.frag_commend;
    }
}
