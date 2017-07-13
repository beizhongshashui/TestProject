package com.yuyoubang.fragment.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuyoubang.R;
import com.yuyoubang.adapter.ApplyLikeAdapter;
import com.yuyoubang.adapter.CheckUser;
import com.yuyoubang.adapter.TripLikeListAdapter;
import com.yuyoubang.bean.TripLikeListBean;
import com.yuyoubang.bean.TripLikeUser;
import com.yuyoubang.fragment.base.BaseNetFragment;
import com.yuyoubang.network.BaseCallback;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.FindApi;
import com.yuyoubang.network.api.HomeApi;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.QLog;
import com.yuyoubang.widget.adapter.interfaces.OnLoadMoreListener;
import com.yuyoubang.widget.recycleview.RecyclerListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 16/11/6.
 */

public class LikeUserFragment extends BaseNetFragment<TripLikeUser> {


    private ApplyLikeAdapter mAdapter;

    private List<CheckUser.ResultBean.ListBean> mData = new ArrayList<>();
    private SwipeRefreshLayout findFollowSp;
    private String trip_id;
    private String cursor;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public static LikeUserFragment newInstance(String trip_id) {
        LikeUserFragment fragment = new LikeUserFragment();
        Bundle bundle = new Bundle();
        bundle.putString("trip_id", trip_id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initViews() {
        trip_id = getArguments().getString("trip_id");
        findFollowSp = getViewById(R.id.find_follow_sp);
        RecyclerView findFollowRv = getViewById(R.id.find_follow_rv);
        findFollowSp.setColorSchemeColors(this.getResources().getColor(R.color.color_ff9600));
        findFollowSp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData("", trip_id);
            }
        });
        //初始化adapter
        mAdapter = new ApplyLikeAdapter(getActivity(), null, true);
        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                getData(mData.get(mData.size() - 1).getId() + "", trip_id);
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        findFollowRv.setLayoutManager(layoutManager);
        findFollowRv.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
        getData("", trip_id);
    }

    private void getData(final String cursor, String trip_id) {
        this.cursor = cursor;
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        FormBody body;
        if (TextUtils.isEmpty(cursor)) {
            body = new FormBody.Builder()
                    .add("trip_id", trip_id)
                    .build();
        } else {
            body = new FormBody.Builder()
                    .add("trip_id", trip_id)
                    .add("cursor", cursor)
                    .build();
        }
        QLog.d("trip_id", "trip_id=" + trip_id);
        Call<TripLikeUser> tripLikeUserCall = findApi.trip_like_list(body);
        tripLikeUserCall.enqueue(this);
        findFollowSp.setRefreshing(false);
        mAdapter.setLoadingView(R.layout.load_more_layout);
        /*likeBeanCall.enqueue(new BaseCallback<TripLikeListBean>() {
            @Override
            public void onResponse(Call<TripLikeListBean> call, Response<TripLikeListBean> response) {
                TripLikeListAdapter tripLikeAdapter = new TripLikeListAdapter(likeRl, getActivity(), response.body().getResult().getList());
                likeRl.setAdapter(tripLikeAdapter);
            }

        });*/
    }

    @Override
    protected void processData(TripLikeUser tripLikeUser) {
        if (TextUtils.isEmpty(cursor)) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < tripLikeUser.getResult().getList().size(); i++) {
                stringBuffer.append(tripLikeUser.getResult().getList().get(i).getUid()).append(",");
            }
            if (TextUtils.isEmpty(stringBuffer)) {
                goneLoading();
                return;
            }
            String sb = stringBuffer.substring(0, stringBuffer.length() - 1);
            requestUser(sb, 1);
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < tripLikeUser.getResult().getList().size(); i++) {
                stringBuffer.append(tripLikeUser.getResult().getList().get(i).getUid()).append(",");
            }
            if (TextUtils.isEmpty(stringBuffer)) {
                goneLoading();
                return;
            }
            String sb = stringBuffer.substring(0, stringBuffer.length() - 1);
            requestUser(sb, 2);
        }
    }

    private void requestUser(String sb, final int position) {
        FormBody formBody = new FormBody.Builder()
                .add("ids", sb)
                .add("uid", PreferenceUtils.getsessionId(getActivity()))
                .build();
        HomeApi homeApi = RetrofitManager.getTestRetrofit().create(HomeApi.class);
        Call<CheckUser> checkUserCall = homeApi.check_user(formBody);
        checkUserCall.enqueue(new Callback<CheckUser>() {
            @Override
            public void onResponse(Call<CheckUser> call, Response<CheckUser> response) {
                goneLoading();
                CheckUser checkUser = response.body();
                if (checkUser.getError_code() == 0) {
                    switch (position) {
                        case 1:
                            if (checkUser.getResult() == null || checkUser.getResult().getList().size() == 0) {
                                mAdapter.setLoadEndView(R.layout.load_end_layout);
                                return;
                            }
                            mData.clear();
                            mData.addAll(checkUser.getResult().getList());
                            mAdapter.setNewData(mData);
                            findFollowSp.setRefreshing(false);
                            mAdapter.setLoadingView(R.layout.load_more_layout);
                            if (checkUser.getResult().getList() == null || checkUser.getResult().getList().size() == 0 || checkUser.getResult().getList().size() < 10) {
                                mAdapter.setLoadEndView(R.layout.load_end_layout);
                                return;
                            }
                            break;
                        case 2:
                            if (checkUser.getResult().getList() == null || checkUser.getResult().getList().size() == 0) {
                                mAdapter.setLoadEndView(R.layout.load_end_layout);
                                return;
                            }
                            mData.addAll(checkUser.getResult().getList());
                            mAdapter.setLoadMoreData(mData);
                            break;
                    }

                }
            }

            @Override
            public void onFailure(Call<CheckUser> call, Throwable t) {
                goneLoading();
            }
        });
    }


    @Override
    protected int getContentResId() {
        return R.layout.frg_like_user;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
