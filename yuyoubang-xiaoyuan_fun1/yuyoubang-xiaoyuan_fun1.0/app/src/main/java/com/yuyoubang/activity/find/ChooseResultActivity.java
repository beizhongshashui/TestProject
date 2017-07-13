package com.yuyoubang.activity.find;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseNetActivity;
import com.yuyoubang.adapter.FindSerachAdapter;
import com.yuyoubang.bean.FindFollowBean;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.FindApi;
import com.yuyoubang.utils.ParamsUtil;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.interfaces.OnLoadMoreListener;
import com.yuyoubang.widget.adapter.interfaces.OnMultiItemClickListeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import retrofit2.Call;

/**
 * Created by hongchen on 17/1/3.
 *
 *
 */

public class ChooseResultActivity extends BaseNetActivity<FindFollowBean> {

    /*@Bind(R.id.find_follow_rv)
    RecyclerView findFollowRv;
    @Bind(R.id.find_follow_sp)
    SwipeRefreshLayout findFollowSp;*/
    private FindSerachAdapter mAdapter;
    private String cursor;
    private List<FindFollowBean.ResultBean.ListBean> mData = new ArrayList<>();
    private FrameLayout null_comment;
    private String experience_end;
    private String experience_start;
    private String gender;
    private String user_location;
    private TextView empty_text;
    private SwipeRefreshLayout findFollowSp;
    private RecyclerView findFollowRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void initViews() {
        experience_end = getIntent().getStringExtra("experience_end");
        experience_start = getIntent().getStringExtra("experience_start");
        gender = getIntent().getStringExtra("gender");
        user_location = getIntent().getStringExtra("user_location");
        null_comment = getViewById(R.id.null_comment);
        findFollowSp = getViewById(R.id.find_follow_sp);
        findFollowRv = getViewById(R.id.find_follow_rv);
        empty_text = getViewById(R.id.empty_text);
        findFollowSp.setColorSchemeColors(ChooseResultActivity.this.getResources().getColor(R.color.color_ff9600));
        findFollowSp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData("");

            }
        });
        //初始化adapter
        mAdapter = new FindSerachAdapter(ChooseResultActivity.this, null, true);
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
                if (data != null) {
                    FindInfoActivity.start(ChooseResultActivity.this, data);
                }
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(ChooseResultActivity.this);
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
        FormBody body;
        Map<String, String> map = ParamsUtil.getParams();
        if (!TextUtils.isEmpty(experience_end)) {
            map.put("experience_end", experience_end);
        }
        if (!TextUtils.isEmpty(experience_start)) {
            map.put("experience_start", experience_start);
        }
        if (!TextUtils.isEmpty(gender)) {
            map.put("gender", gender);
        }
        if (!TextUtils.isEmpty(user_location)) {
            map.put("user_location", user_location);
        }
        if (!TextUtils.isEmpty(cursor)) {
            map.put("cursor", cursor);
        }
        FormBody.Builder builder = ParamsUtil.filterParams(map, new FormBody.Builder());
        body = builder.build();
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        Call<FindFollowBean> chooseStatusResultCall = findApi.search_status(body);
        chooseStatusResultCall.enqueue(this);

        findFollowSp.setRefreshing(false);
        mAdapter.setLoadingView(R.layout.load_more_layout);
    }


    @Override
    protected void processData(FindFollowBean chooseStatusResult) {
        goneLoading();
        if (TextUtils.isEmpty(cursor)) {
            if (chooseStatusResult.getResult() == null || chooseStatusResult.getResult().getList().size() == 0) {
                null_comment.setVisibility(View.VISIBLE);
                empty_text.setText("暂无搜索动态");
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
            mData.clear();
            mData.addAll(chooseStatusResult.getResult().getList());
            mAdapter.setNewData(mData);
            findFollowSp.setRefreshing(false);
            mAdapter.setLoadingView(R.layout.load_more_layout);
            if (chooseStatusResult.getResult().getList() == null || chooseStatusResult.getResult().getList().size() == 0 || chooseStatusResult.getResult().getList().size() < 5) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
        } else {
            if (chooseStatusResult.getResult().getList() == null || chooseStatusResult.getResult().getList().size() == 0) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
            mData.clear();
            mData.addAll(chooseStatusResult.getResult().getList());
            mAdapter.setLoadMoreData(mData);

        }
    }

    @Override
    protected int getContentResId() {
        return R.layout.frg_find_follow;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("筛选");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
