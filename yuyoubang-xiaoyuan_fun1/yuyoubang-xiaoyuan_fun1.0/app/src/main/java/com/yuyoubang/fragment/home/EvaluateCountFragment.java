package com.yuyoubang.fragment.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuyoubang.R;
import com.yuyoubang.activity.home.InfoActivity;
import com.yuyoubang.adapter.CommentAdapter;
import com.yuyoubang.bean.ActionInfoBean;
import com.yuyoubang.bean.CommentBean;
import com.yuyoubang.fragment.base.BaseNetFragment;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.HomeApi;
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

public class EvaluateCountFragment extends BaseNetFragment<CommentBean> {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    private InfoActivity infoActivity;
    private ActionInfoBean actionInfoBean;
    private CommentAdapter mAdapter;

    private String biz_uid;
    private String type;
    private String cursor;
    private List<CommentBean.ResultBean.ListBean> data = new ArrayList<>();


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.infoActivity = (InfoActivity) activity;
        this.actionInfoBean = infoActivity.actionInfoBean;
    }

    public static EvaluateCountFragment newInstance(String biz_uid, String type) {
        EvaluateCountFragment fragment = new EvaluateCountFragment();
        Bundle bundle = new Bundle();
        bundle.putString("biz_uid", biz_uid);
        bundle.putString("type", type);
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
        biz_uid = getArguments().getString("biz_uid");
        type = getArguments().getString("type");

        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new CommentAdapter(getActivity(), null, true);
        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                loadData(biz_uid,data.get(data.size() - 1).getId() + "");
            }
        });
        recyclerview.setAdapter(mAdapter);

    }




    @Override
    protected void loadData() {
        loadData(biz_uid,cursor);
    }


    private void loadData(String biz_uid, String cursor) {
        this.cursor = cursor;
        if (TextUtils.isEmpty(biz_uid)){
            return;
        }
        HomeApi homeApi = RetrofitManager.getTestRetrofit().create(HomeApi.class);
        FormBody body = null;
        if (TextUtils.isEmpty(cursor)) {
            body = new FormBody.Builder().add("biz_uid", biz_uid).build();
        } else {
            body = new FormBody.Builder().add("biz_uid", biz_uid).add("cursor", cursor).build();
        }

        if (type.equals("0")) {
            Call<CommentBean> beanCall = homeApi.comment_list_all(body);
            beanCall.enqueue(this);
        } else if (type.equals("1")) {
            Call<CommentBean> beanCall = homeApi.comment_list_fine(body);
            beanCall.enqueue(this);
        } else if (type.equals("2")) {
            Call<CommentBean> beanCall = homeApi.comment_list_normal(body);
            beanCall.enqueue(this);
        } else if (type.equals("3")) {
            Call<CommentBean> beanCall = homeApi.comment_list_bad(body);
            beanCall.enqueue(this);
        }

    }

    @Override
    protected void processData(CommentBean commentBean) {
        if (TextUtils.isEmpty(cursor)) {
            data.clear();
            data.addAll(commentBean.getResult().getList());
            mAdapter.setNewData(data);
            mAdapter.setLoadingView(R.layout.load_more_layout);
            if (commentBean.getResult().getList() == null || commentBean.getResult().getList().size() == 0 || commentBean.getResult().getList().size() < 5) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
        } else {
            if (commentBean.getResult().getList() == null || commentBean.getResult().getList().size() == 0) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
            data.addAll(commentBean.getResult().getList());
            mAdapter.setLoadMoreData(data);

        }

    }

    @Override
    protected int getContentResId() {
        return R.layout.frg_evaluate_count;
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
