package com.yuyoubang.fragment.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuyoubang.R;
import com.yuyoubang.activity.home.InfoActivity;
import com.yuyoubang.adapter.ActionInfoDescAdapter;
import com.yuyoubang.bean.ActionInfoBean;
import com.yuyoubang.fragment.base.BaseNetFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiaoyuan on 16/11/6.
 */

public class ActionInfoFragment extends BaseNetFragment<Object> {


//    @Bind(R.id.action_desc)
//    TextView actionDesc;
//    @Bind(R.id.action_fit)
//    TextView actionFit;
//    @Bind(R.id.other)
//    TextView other;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    private InfoActivity infoActivity;
    private ActionInfoBean actionInfoBean;
    private ActionInfoDescAdapter mAdapter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.infoActivity = (InfoActivity) activity;
        this.actionInfoBean = infoActivity.actionInfoBean;
    }

    public static ActionInfoFragment newInstance() {
        ActionInfoFragment fragment = new ActionInfoFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        goneLoading();
    }

    @Override
    protected void initViews() {
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new ActionInfoDescAdapter(getActivity(), null, true);
        recyclerview.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
//        TestApi testApi = RetrofitManager.getTestRetrofit().create(TestApi.class);
//        Call<Object> testCall = testApi.test();
//        testCall.enqueue(this);
    }

    @Override
    protected void processData(Object o) {

    }

    @Override
    protected int getContentResId() {
        return R.layout.frg_action_info;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<ActionInfoBean.ResultBean.TripBean> tripBeen = new ArrayList<>();
        tripBeen.add(actionInfoBean.getResult().getTrip().get(0));
        mAdapter.setNewData(tripBeen);
//        actionDesc.setText(actionInfoBean.getResult().getTrip().get(0).getData().getTrip_intro());
//        actionFit.setText(actionInfoBean.getResult().getTrip().get(0).getData().getTrip_equipment_intro());
//        actionDesc.setText(actionInfoBean.getResult().getTrip().get(0).getData().getOther_desc());

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
