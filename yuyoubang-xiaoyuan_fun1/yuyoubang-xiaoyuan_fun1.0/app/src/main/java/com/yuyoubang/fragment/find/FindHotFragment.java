package com.yuyoubang.fragment.find;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.find.FindInfoActivity;
import com.yuyoubang.adapter.FindHotAdapter;
import com.yuyoubang.bean.FindFollowBean;
import com.yuyoubang.fragment.base.BaseNetFragment;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.FindApi;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.interfaces.OnLoadMoreListener;
import com.yuyoubang.widget.adapter.interfaces.OnMultiItemClickListeners;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import retrofit2.Call;

/**
 * Created by xiaoyuan on 16/11/6.
 */

public class FindHotFragment extends BaseNetFragment<FindFollowBean> {


    @Bind(R.id.find_hot_rv)
    RecyclerView findHotRv;
    @Bind(R.id.find_hot_sp)
    SwipeRefreshLayout findHotSp;
    private FindHotAdapter mAdapter;
    private FrameLayout null_comment;
    private int page = 1;
    private List<FindFollowBean.ResultBean.ListBean> mData = new ArrayList<>();
    private TextView empty_text;

    public static FindHotFragment newInstance() {
        FindHotFragment fragment = new FindHotFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        goneLoading();
    }

    @Override
    protected void initViews() {
        empty_text = getViewById(R.id.empty_text);
        null_comment = getViewById(R.id.null_comment);
        findHotSp.setColorSchemeColors(getActivity().getResources().getColor(R.color.color_ff9600));
        findHotSp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getData(page);

            }
        });
        //初始化adapter
        mAdapter = new FindHotAdapter(getActivity(), null, true);
        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                page++;
                getData(page);
            }
        });
        mAdapter.setOnMultiItemClickListener(new OnMultiItemClickListeners<FindFollowBean.ResultBean.ListBean>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, FindFollowBean.ResultBean.ListBean data, int position, int viewType) {
                FindInfoActivity.start(getActivity(), data);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        findHotRv.setLayoutManager(layoutManager);
        findHotRv.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
        getData(page);
    }


    private void getData(final int page) {
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        FormBody body;
        if (page == 1) {
            body = new FormBody.Builder()
                    .add("uid", PreferenceUtils.getsessionId(getActivity()))
                    .add("page", page + "")
                    .build();
        } else {
            body = new FormBody.Builder()
                    .add("uid", PreferenceUtils.getsessionId(getActivity()))
                    .add("page", page + "")
                    .build();
        }
        Call<FindFollowBean> find = findApi.status_hot_list(body);
        find.enqueue(this);

        findHotSp.setRefreshing(false);
        mAdapter.setLoadingView(R.layout.load_more_layout);
    }

    @Override
    protected void processData(FindFollowBean data) {
        if (data.getError_code() == 10001) {
            null_comment.setVisibility(View.VISIBLE);
            if (findHotSp != null) {
                findHotSp.setRefreshing(false);
            }
            empty_text.setText("系统错误");
            return;
        }
        if (page == 1) {
            if (data.getResult() == null || data.getResult().getList().size() == 0) {
                null_comment.setVisibility(View.VISIBLE);
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
            mData.clear();
            mData.addAll(data.getResult().getList());
            mAdapter.setNewData(mData);
            findHotSp.setRefreshing(false);
            mAdapter.setLoadingView(R.layout.load_more_layout);
            if (data.getResult().getList() == null || data.getResult().getList().size() == 0 || data.getResult().getList().size() < 5) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
        } else {
            if (data.getResult().getList() == null || data.getResult().getList().size() == 0) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
            mData.clear();
            mData.addAll(data.getResult().getList());
            mAdapter.setLoadMoreData(mData);

        }
    }

    @Override
    protected int getContentResId() {
        return R.layout.frg_find_hot;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        page = 1;
        ButterKnife.unbind(this);
    }
}
