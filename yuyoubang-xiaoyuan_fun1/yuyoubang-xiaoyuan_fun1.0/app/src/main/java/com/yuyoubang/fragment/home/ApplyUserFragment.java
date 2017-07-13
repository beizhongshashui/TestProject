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
import com.yuyoubang.bean.TripOrderUser;
import com.yuyoubang.fragment.base.BaseNetFragment;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.HomeApi;
import com.yuyoubang.utils.ParamsUtil;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.widget.adapter.interfaces.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 16/11/6.
 */

public class ApplyUserFragment extends BaseNetFragment<TripOrderUser> {

    private ApplyLikeAdapter mAdapter;

    private List<CheckUser.ResultBean.ListBean> mData = new ArrayList<>();
    private SwipeRefreshLayout findFollowSp;
    private String trip_id;
    private String cursor;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public static ApplyUserFragment newInstance(String trip_id) {
        ApplyUserFragment fragment = new ApplyUserFragment();
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
//        TestApi testApi = RetrofitManager.getTestRetrofit().create(TestApi.class);
//        Call<Object> testCall = testApi.test();
//        testCall.enqueue(this);
        getData("", trip_id);
    }

    @Override
    protected void processData(TripOrderUser tripOrderUser) {
        if (TextUtils.isEmpty(cursor)) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < tripOrderUser.getResult().getList().size(); i++) {
                stringBuffer.append(tripOrderUser.getResult().getList().get(i).getUid()).append(",");
            }
            if (TextUtils.isEmpty(stringBuffer)) {
                goneLoading();
                return;
            }
            String sb = stringBuffer.substring(0, stringBuffer.length() - 1);
            requestUser(sb, 1);
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < tripOrderUser.getResult().getList().size(); i++) {
                stringBuffer.append(tripOrderUser.getResult().getList().get(i).getUid()).append(",");
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

    private void getData(final String cursor, String trip_id) {
        this.cursor = cursor;
        HomeApi homeApi = RetrofitManager.getTestRetrofit().create(HomeApi.class);
        FormBody body;
        Map<String, String> map = ParamsUtil.getParams();
        map.put("cursor", cursor);
        map.put("trip_id", trip_id);
        map.put("uid", PreferenceUtils.getsessionId(getActivity()));
        FormBody.Builder builder = ParamsUtil.filterParams(map, new FormBody.Builder());
        body = builder.build();
        Call<TripOrderUser> tripOrderUserCall = homeApi.trip_order_user_list(body);
        tripOrderUserCall.enqueue(this);
        findFollowSp.setRefreshing(false);
        mAdapter.setLoadingView(R.layout.load_more_layout);
        /*likeBeanCall.enqueue(new BaseCallback<ActionDetail>() {
            @Override
            public void onResponse(Call<ActionDetail> call, Response<ActionDetail> response) {
                TripApplyUserListAdapter tripLikeAdapter = new TripApplyUserListAdapter(applyRl, getActivity(), response.body().getResult().getList());
                applyRl.setAdapter(tripLikeAdapter);
            }

        });*/
    }

    @Override
    protected int getContentResId() {
        return R.layout.frg_apply_user;
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
