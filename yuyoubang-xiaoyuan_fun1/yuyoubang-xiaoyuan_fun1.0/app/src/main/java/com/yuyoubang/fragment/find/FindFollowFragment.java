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
import com.yuyoubang.adapter.FindFollowAdapter;
import com.yuyoubang.bean.FindFollowBean;
import com.yuyoubang.bean.FindRecomendBean;
import com.yuyoubang.fragment.base.BaseNetFragment;
import com.yuyoubang.network.BaseCallback;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.FindApi;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.interfaces.OnLoadMoreListener;
import com.yuyoubang.widget.adapter.interfaces.OnMultiItemClickListeners;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 16/11/6.
 */

public class FindFollowFragment extends BaseNetFragment<FindFollowBean> {


//    @Bind(R.id.find_follow_rv)
//    RecyclerView findFollowRv;
//    @Bind(R.id.find_follow_sp)
//    SwipeRefreshLayout findFollowSp;
    private FindFollowAdapter mAdapter;
    private String cursor;
    private List<FindFollowBean.ResultBean.ListBean> mData = new ArrayList<>();
    private FrameLayout null_comment;
    private SwipeRefreshLayout findFollowSp;
    private RecyclerView findFollowRv;
    private TextView empty_text;

    public static FindFollowFragment newInstance() {
        FindFollowFragment fragment = new FindFollowFragment();
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
        findFollowRv = getViewById(R.id.find_follow_rv);
        findFollowSp = getViewById(R.id.find_follow_sp);
        findFollowSp.setColorSchemeColors(getActivity().getResources().getColor(R.color.color_ff9600));
        findFollowSp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData("");

            }
        });
        //初始化adapter
        mAdapter = new FindFollowAdapter(getActivity(), null, true);
        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                getData(mData.get(mData.size() - 1).getId() + "");
            }
        });
        mAdapter.setOnMultiItemClickListener(new OnMultiItemClickListeners<FindFollowBean.ResultBean.ListBean>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, FindFollowBean.ResultBean.ListBean data, int position, int viewType) {
               if(data != null){
                   FindInfoActivity.start(getActivity(), data);
               }
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        findFollowRv.setLayoutManager(layoutManager);
        findFollowRv.setAdapter(mAdapter);
//        findFollowRv.addItemDecoration(new ItemDivider(getActivity(), R.drawable.divider_item));

    }


    @Override
    protected void loadData() {
        getData("");

    }


    private void getUserRecommed() {
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        FormBody body = new FormBody.Builder()
                .add("uid",PreferenceUtils.getsessionId(getActivity())).build();
        Call<FindRecomendBean> login = findApi.recommend_list(body);
        login.enqueue(new BaseCallback<FindRecomendBean>() {
            @Override
            public void onResponse(Call<FindRecomendBean> call, Response<FindRecomendBean> response) {
                if (response.body().getError_code() == 0) {
                    mAdapter.setHeadData(response.body().getResult().getList());
                } else {

                }

            }

            @Override
            public void onFailure(Call<FindRecomendBean> call, Throwable t) {
                super.onFailure(call, t);
                ToastUtils.showLong(R.string.net_error);
            }
        });
    }

    private void getData(final String cursor) {
        this.cursor = cursor;
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        FormBody body;
        if (TextUtils.isEmpty(cursor)) {
            body = new FormBody.Builder()
                    .add("uid", PreferenceUtils.getsessionId(getActivity()))
                    .build();
        } else {
            body = new FormBody.Builder().add("uid", PreferenceUtils.getsessionId(getActivity())).add("cursor", cursor).build();
        }
        Call<FindFollowBean> find = findApi.status_timeline(body);
        find.enqueue(this);

        findFollowSp.setRefreshing(false);
        mAdapter.setLoadingView(R.layout.load_more_layout);
    }

    @Override
    protected void processData(FindFollowBean data) {
        if (data.getError_code() == 10001) {
            null_comment.setVisibility(View.VISIBLE);
            findFollowSp.setRefreshing(false);
            empty_text.setText("系统错误");
            return;
        }
        if (TextUtils.isEmpty(cursor)) {
            getUserRecommed();
            if (data.getResult() == null || data.getResult().getList().size() == 0) {
                null_comment.setVisibility(View.VISIBLE);
                mData.clear();
                mData.addAll(data.getResult().getList());
                mAdapter.setNewData(mData);
                findFollowSp.setRefreshing(false);
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }else {
                null_comment.setVisibility(View.GONE);
            }
            mData.clear();
            mData.addAll(data.getResult().getList());
            mAdapter.setNewData(mData);
            findFollowSp.setRefreshing(false);
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
        return R.layout.frg_find_follow;
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
        ButterKnife.unbind(this);
    }
}
