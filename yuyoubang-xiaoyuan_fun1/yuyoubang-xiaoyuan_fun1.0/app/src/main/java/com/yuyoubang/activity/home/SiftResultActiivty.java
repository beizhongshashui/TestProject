package com.yuyoubang.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseNetActivity;
import com.yuyoubang.adapter.SiftResultAdapter;
import com.yuyoubang.bean.SiftResultBean;
import com.yuyoubang.network.BaseCallback;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.HomeApi;
import com.yuyoubang.otto.HomeEvent;
import com.yuyoubang.utils.ParamsUtil;
import com.yuyoubang.utils.QLog;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.interfaces.OnLoadMoreListener;
import com.yuyoubang.widget.adapter.interfaces.OnMultiItemClickListeners;

import java.util.LinkedList;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 16/12/22.
 */
public class SiftResultActiivty extends BaseNetActivity<SiftResultBean> {
    @Bind(R.id.sift_rv)
    RecyclerView siftRv;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @Bind(R.id.no_result)
    LinearLayout noResult;
    private SiftResultAdapter mAdapter;
    private LinkedList<SiftResultBean.ResultBean.ListBean> data = new LinkedList<>();
    private String start_timestamp;
    private String day_min;
    private String day_max;
    private String min_price;
    private String max_price;

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("筛选");
    }

    public static void start(Context context, HomeEvent homeEvent) {
        Intent intent = new Intent(context, SiftResultActiivty.class);
        intent.putExtra("event", homeEvent);
        context.startActivity(intent);
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_sift_result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initViews() {

    }

    private void initView() {
        swiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.color_ff9600));
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData("", start_timestamp, day_min, day_max, min_price, max_price);

            }
        });
        //初始化adapter
        mAdapter = new SiftResultAdapter(this, null, true);
        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                String cursor = data.get(data.size() - 1).getId() + "";
                getData(cursor, start_timestamp, day_min, day_max, min_price, max_price);

            }
        });
        mAdapter.setOnMultiItemClickListener(new OnMultiItemClickListeners<SiftResultBean.ResultBean.ListBean>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, SiftResultBean.ResultBean.ListBean data, int position, int viewType) {
                InfoActivity.start(SiftResultActiivty.this, data.getId() + "");

            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        siftRv.setLayoutManager(layoutManager);
        siftRv.setAdapter(mAdapter);

    }

    @Override
    protected void loadData() {
        HomeEvent homeEvent = (HomeEvent) getIntent().getExtras().getSerializable("event");
        start_timestamp = homeEvent.start_timestamp;
        day_min = homeEvent.day_min;
        day_max = homeEvent.day_max;
        min_price = homeEvent.min_price;
        max_price = homeEvent.max_price;

        getData("", start_timestamp, day_min, day_max, min_price, max_price);
    }

    @Override
    protected void processData(SiftResultBean siftResultBean) {

    }


    private void getData(final String cursor, String start_timestamp, String day_min, String day_max, String price_min, String price_max) {
        QLog.d("xiaoyuan", "cursor=" + cursor);
        HomeApi homeApi = RetrofitManager.getTestRetrofit().create(HomeApi.class);
        FormBody body;
        Map<String, String> map = ParamsUtil.getParams();
        if (!TextUtils.isEmpty(start_timestamp)){
            map.put("start_timestamp", start_timestamp);
        }
        if (!TextUtils.isEmpty(day_min)){
            map.put("day_min", day_min);
        }
        if (!TextUtils.isEmpty(day_max)){
            map.put("day_max", day_max);
        }
        if (!TextUtils.isEmpty(price_min)){
            map.put("price_min", price_min);
        }
        if (!TextUtils.isEmpty(price_max)) {
            map.put("price_max", price_max);
        }
        if (!TextUtils.isEmpty(cursor)){
            map.put("cursor", cursor);
        }
        /*map.put("start_timestamp", start_timestamp);
        map.put("day_min", day_min);
        map.put("day_max", day_max);
        map.put("price_min", price_min);
        map.put("price_max", price_max);
        map.put("cursor", cursor);*/
        FormBody.Builder builder = ParamsUtil.filterParams(map, new FormBody.Builder());
        body = builder.build();
        Call<SiftResultBean> home = homeApi.trip_filter(body);
        home.enqueue(new BaseCallback<SiftResultBean>() {
            @Override
            public void onResponse(Call<SiftResultBean> call, Response<SiftResultBean> response) {
                goneLoading();
                if (TextUtils.isEmpty(cursor)) {
                    if (response.body().getResult().getList() != null && response.body().getResult().getList().size() == 0) {
                        noResult.setVisibility(View.VISIBLE);
                        swiperefreshlayout.setVisibility(View.INVISIBLE);
                        return;
                    } else {
                        noResult.setVisibility(View.GONE);
                        swiperefreshlayout.setVisibility(View.VISIBLE);
                    }
                    data.clear();
                    data.addAll(response.body().getResult().getList());
                    mAdapter.setNewData(data);
                    swiperefreshlayout.setRefreshing(false);
                    mAdapter.setLoadingView(R.layout.load_more_layout);
                    if (response.body().getResult().getList() == null || response.body().getResult().getList().size() == 0 || response.body().getResult().getList().size() < 5) {
                        mAdapter.setLoadEndView(R.layout.load_end_layout);
                        return;
                    }
                } else {
                    if (response.body().getResult().getList() == null || response.body().getResult().getList().size() == 0) {
                        mAdapter.setLoadEndView(R.layout.load_end_layout);
                        return;
                    }
                    data.clear();
                    data.addAll(response.body().getResult().getList());
                    mAdapter.setLoadMoreData(data);

                }
            }

        });
    }


}
