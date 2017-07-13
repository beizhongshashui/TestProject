package com.yuyoubang.activity.mine.business.action;

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
import android.widget.FrameLayout;

import com.yuyoubang.R;
import com.yuyoubang.activity.mine.business.ActionMangerAdapter;
import com.yuyoubang.bean.MyTripBean;
import com.yuyoubang.fragment.base.BaseNetFragment;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.BusinessApi;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.widget.adapter.interfaces.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import retrofit2.Call;

/**
 * Created by xiaoyuan on 16/11/6.
 */

public class AllActionFragment extends BaseNetFragment<MyTripBean> {


    @Bind(R.id.all_rv)
    RecyclerView allRv;
    @Bind(R.id.all_sp)
    SwipeRefreshLayout allSp;
    private ActionMangerAdapter mAdapter;
    private List<MyTripBean.ResultBean.ListBean> data = new ArrayList<>();
    private String cursor;
    private FrameLayout null_comment;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public static AllActionFragment newInstance() {
        AllActionFragment fragment = new AllActionFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        goneLoading();
        initView();
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void loadData() {
//        TestApi testApi = RetrofitManager.getTestRetrofit().create(TestApi.class);
//        Call<Object> testCall = testApi.test();
//        testCall.enqueue(this);
    }



    private void initView() {
        null_comment = getViewById(R.id.null_comment);
        allSp.setColorSchemeColors(getActivity().getResources().getColor(R.color.color_ff9600));
        allSp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData("");

            }
        });
        //初始化adapter
        mAdapter = new ActionMangerAdapter(getActivity(), null, true);
        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                getData(data.get(data.size()-1).getId()+"");
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        allRv.setLayoutManager(layoutManager);
        allRv.setAdapter(mAdapter);
        getData("");

    }


    private void getData(final String cursor) {
        this.cursor = cursor;
        BusinessApi businessApi = RetrofitManager.getTestRetrofit().create(BusinessApi.class);
        FormBody body;
        if (TextUtils.isEmpty(cursor)) {
            body = new FormBody.Builder()
                    .add("biz_uid", PreferenceUtils.getsessionId(getActivity()))
                    .build();
        } else {
            body = new FormBody.Builder()
                    .add("biz_uid", PreferenceUtils.getsessionId(getActivity()))
                    .add("cursor", cursor).build();
        }
        Call<MyTripBean> login = businessApi.my_trip_list_by_state(body);
        login.enqueue(this);
    }


    @Override
    protected void processData(MyTripBean myTripBean) {
            if (TextUtils.isEmpty(cursor)) {
                if (myTripBean.getResult().getList() == null || myTripBean.getResult().getList().size() == 0) {
                    null_comment.setVisibility(View.VISIBLE);
                }else {
                    null_comment.setVisibility(View.GONE);
                }
                data.clear();
                data.addAll(myTripBean.getResult().getList());
                mAdapter.setNewData(data);
                allSp.setRefreshing(false);
                mAdapter.setLoadingView(R.layout.load_more_layout);
                if (myTripBean.getResult().getList() == null || myTripBean.getResult().getList().size() == 0 ||myTripBean.getResult().getList().size() < 5) {
                    mAdapter.setLoadEndView(R.layout.load_end_layout);
                    return;
                }
            } else {
                if (myTripBean.getResult().getList() == null || myTripBean.getResult().getList().size() == 0) {
                    mAdapter.setLoadEndView(R.layout.load_end_layout);
                    return;
                }
                data.addAll(myTripBean.getResult().getList());
                mAdapter.setLoadMoreData(data);

            }

    }

    @Override
    protected int getContentResId() {
        return R.layout.frg_all_action;
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
