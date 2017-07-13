package com.yuyoubang.fragment.home;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.squareup.otto.Subscribe;
import com.yuyoubang.R;
import com.yuyoubang.activity.home.HomeActivity;
import com.yuyoubang.adapter.HotAdapter;
import com.yuyoubang.bean.BannerPics;
import com.yuyoubang.bean.HotBean;
import com.yuyoubang.fragment.base.BaseNetFragment;
import com.yuyoubang.network.BaseCallback;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.HomeApi;
import com.yuyoubang.otto.AppBus;
import com.yuyoubang.otto.HomeEvent;
import com.yuyoubang.utils.LocationUtil;
import com.yuyoubang.utils.ParamsUtil;
import com.yuyoubang.utils.QLog;
import com.yuyoubang.widget.adapter.interfaces.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 16/11/6.
 */

public class CityFragment extends BaseNetFragment<HotBean> {
    @Bind(R.id.city_rv)
    RecyclerView cityRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private LinkedList<HotBean.ResultBean.ListBean> data = new LinkedList<>();
    private boolean loading;
    private HotAdapter mAdapter;
    private String city = "";
    private List<String> strings = new ArrayList<>();
    private boolean isLocation; //是否已经定位;
    private HomeActivity homeActivity;
    private FrameLayout null_comment;
    private TextView empty_text;


    public static CityFragment newInstance() {
        CityFragment fragment = new CityFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        homeActivity = (HomeActivity) activity;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        AppBus.getInstance().register(this);
        goneLoading();
        initView();
    }

    @Override
    protected void initViews() {


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppBus.getInstance().unregister(this);
    }


    private void initView() {
        empty_text = getViewById(R.id.empty_text);
        null_comment = getViewById(R.id.null_comment);
        mSwipeRefreshLayout.setColorSchemeColors(getActivity().getResources().getColor(R.color.color_ff9600));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                getCity("", city);
                if (!TextUtils.isEmpty(homeActivity.city)) {
                    getCity("", homeActivity.city);
                } else {
                    getCity("", city);
                }

            }
        });
        //初始化adapter
        mAdapter = new HotAdapter(getActivity(), null, true);
        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                if (!TextUtils.isEmpty(homeActivity.city)) {
                    getCity(data.get(data.size() - 1).getId() + "", homeActivity.city);
                } else {
                    getCity(data.get(data.size() - 1).getId() + "", city);
                }
//                getCity(data.get(data.size() - 1).getId() + "", city);
            }
        });
        /*mAdapter.setOnMultiItemClickListener(new OnMultiItemClickListeners<HotBean.ResultBean.ListBean>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, HotBean.ResultBean.ListBean data, int position, int viewType) {
                InfoActivity.start(getActivity(), data.getId() + "");
            }
        });*/
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cityRecyclerView.setLayoutManager(layoutManager);
        cityRecyclerView.setAdapter(mAdapter);
//        cityRecyclerView.addItemDecoration(new ItemDivider(getActivity(),R.drawable.divider_item));

    }

    @Override
    protected void loadData() {
        LocationUtil.startLocation(mLocationListener);


        getBanner();
    }


    AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (!TextUtils.isEmpty(aMapLocation.getCity())) {
                Message message = Message.obtain();
                message.obj = aMapLocation.getCity();
                handler.sendMessage(message);
            }


        }
    };

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            city = (String) msg.obj;
            city = city.replace("市", "");
            if (!isLocation) {
                if (!TextUtils.isEmpty(homeActivity.city)) {
                    getCity("", homeActivity.city);
                } else {
                    getCity("", city);
                }

                isLocation = true;
            }

            LocationUtil.onStop();


        }
    };

    private void getBanner() {
        FormBody formBody = new FormBody.Builder()
                .build();
        HomeApi homeApi = RetrofitManager.getTestRetrofit().create(HomeApi.class);
        Call<BannerPics> bannerPicsCall = homeApi.banner_pics(formBody);
        bannerPicsCall.enqueue(new Callback<BannerPics>() {
            @Override
            public void onResponse(Call<BannerPics> call, Response<BannerPics> response) {
                BannerPics body = response.body();
                List<BannerPics.ResultBean.ListBean> list = body.getResult().getList();
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        strings.add(list.get(i).getData().getBanner_pic_url());
                    }
                } else {
                    strings.add("http://img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg");
                    strings.add("http://static.risfond.com/images2/2016/2/232d68c5013245eea8c51bc01373145c.jpg");
                    strings.add("https://imgs.qunarzz.com/p/tts9/1606/15/a624dd83c1d13bf7.jpg_r_390x260x90_f190d4c6.jpg");
                    strings.add("http://static.risfond.com/images2/2016/2/232d68c5013245eea8c51bc01373145c.jpg");
                    strings.add("http://static.risfond.com/images2/2016/2/232d68c5013245eea8c51bc01373145c.jpg");
                }
                mAdapter.setHeadData(strings, list);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<BannerPics> call, Throwable t) {

            }
        });
    }


    //todo 没有加cursor
    private void getCity(final String cursor, String city) {
        QLog.d("city", "city=" + city);
        HomeApi homeApi = RetrofitManager.getTestRetrofit().create(HomeApi.class);
        FormBody body = null;
        Map<String, String> map = ParamsUtil.getParams();
        map.put("meeting_city", city);
        map.put("cursor", cursor);
        FormBody.Builder builder = ParamsUtil.filterParams(map, new FormBody.Builder());
        body = builder.build();
        Call<HotBean> login = homeApi.get_city_trip(body);
        login.enqueue(new BaseCallback<HotBean>() {
            @Override
            public void onResponse(Call<HotBean> call, Response<HotBean> response) {
                if (response.body().getError_code() == 10001) {
                    mSwipeRefreshLayout.setRefreshing(false);
                    null_comment.setVisibility(View.VISIBLE);
                    empty_text.setText("系统错误");
                    return;
                }
                if (TextUtils.isEmpty(cursor)) {
                    if (response.body().getResult().getList() == null || response.body().getResult().getList().size() == 0) {
                        null_comment.setVisibility(View.VISIBLE);
                    } else {
                        null_comment.setVisibility(View.GONE);
                    }
                    data.clear();
                    data.addAll(response.body().getResult().getList());
                    mAdapter.setNewData(data);
                    mSwipeRefreshLayout.setRefreshing(false);
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
                    data.addAll(response.body().getResult().getList());
                    mAdapter.setLoadMoreData(data);

                }


            }

        });
    }


    @Override
    protected void processData(HotBean o) {

    }

    @Override
    protected int getContentResId() {
        return R.layout.frg_city;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Subscribe
    public void home(HomeEvent homeEvent) {
        if (!city.equals(homeEvent.city)) {
            city = homeEvent.city;
            getCity("", city);

        } else {
            getCity("", city);
        }

    }

}