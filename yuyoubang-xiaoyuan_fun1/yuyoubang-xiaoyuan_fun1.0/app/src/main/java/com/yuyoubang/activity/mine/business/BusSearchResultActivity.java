package com.yuyoubang.activity.mine.business;

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
import com.yuyoubang.adapter.OrderSerachAdapter;
import com.yuyoubang.bean.BusinessSearch;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.BusinessApi;
import com.yuyoubang.utils.ParamsUtil;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.widget.adapter.interfaces.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import retrofit2.Call;

/**
 * Created by hongchen on 16/12/8.
 */

public class BusSearchResultActivity extends BaseNetActivity<BusinessSearch> {


    private OrderSerachAdapter mAdapter;
    private String cursor;

    private List<BusinessSearch.ResultBean.ListBean> mData = new ArrayList<>();
    private SwipeRefreshLayout findFollowSp;
    private FrameLayout null_comment;
    private String order_id;
    private String trip_time_start;
    private String trip_time_end;
    private TextView empty_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        order_id = getIntent().getStringExtra("order_id");
        trip_time_start = getIntent().getStringExtra("trip_time_start");
        trip_time_end = getIntent().getStringExtra("trip_time_end");
        null_comment = getViewById(R.id.null_comment);
        empty_text = getViewById(R.id.empty_text);
        findFollowSp = getViewById(R.id.find_follow_sp);
        RecyclerView findFollowRv = getViewById(R.id.find_follow_rv);
        findFollowSp.setColorSchemeColors(this.getResources().getColor(R.color.color_ff9600));
        findFollowSp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData("");

            }
        });
        //初始化adapter
        mAdapter = new OrderSerachAdapter(this, null, true);
        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                getData(mData.get(mData.size() - 1).getId() + "");
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
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
        map.put("uid", PreferenceUtils.getsessionId(this));
        if (!TextUtils.isEmpty(order_id)) {
            map.put("order_id", order_id);
        }
        if (!TextUtils.isEmpty(trip_time_start)) {
            map.put("trip_time_start", trip_time_start);
        }
        if (!TextUtils.isEmpty(trip_time_end)) {
            map.put("trip_time_end", trip_time_end);
        }
        if (!TextUtils.isEmpty(cursor)) {
            map.put("cursor", cursor);
        }
        FormBody.Builder builder = ParamsUtil.filterParams(map, new FormBody.Builder());
        body = builder.build();
        BusinessApi businessApi = RetrofitManager.getTestRetrofit().create(BusinessApi.class);
        Call<BusinessSearch> businessSearchCall = businessApi.choose_order(body);
        businessSearchCall.enqueue(this);

        findFollowSp.setRefreshing(false);
        mAdapter.setLoadingView(R.layout.load_more_layout);
    }


    @Override
    protected void processData(BusinessSearch businessSearch) {
        goneLoading();
        if (TextUtils.isEmpty(cursor)) {
            if (businessSearch.getResult() == null || businessSearch.getResult().getList().size() == 0) {
                null_comment.setVisibility(View.VISIBLE);
                empty_text.setText("暂无搜索订单");
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
            mData.clear();
            mData.addAll(businessSearch.getResult().getList());
            mAdapter.setNewData(mData);
            findFollowSp.setRefreshing(false);
            mAdapter.setLoadingView(R.layout.load_more_layout);
            if (businessSearch.getResult().getList() == null || businessSearch.getResult().getList().size() == 0 || businessSearch.getResult().getList().size() < 10) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
        } else {
            if (businessSearch.getResult().getList() == null || businessSearch.getResult().getList().size() == 0) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
            mData.addAll(businessSearch.getResult().getList());
            mAdapter.setLoadMoreData(mData);

        }
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_more_like_list;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("搜索");
    }
}
