package com.yuyoubang.fragment.find;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.squareup.otto.Subscribe;
import com.yuyoubang.R;
import com.yuyoubang.activity.find.FindInfoActivity;
import com.yuyoubang.activity.home.HomeActivity;
import com.yuyoubang.adapter.FindCityAdapter;
import com.yuyoubang.bean.FindFollowBean;
import com.yuyoubang.fragment.base.BaseNetFragment;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.FindApi;
import com.yuyoubang.otto.HomeEvent;
import com.yuyoubang.utils.LocationUtil;
import com.yuyoubang.utils.QLog;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.interfaces.OnLoadMoreListener;
import com.yuyoubang.widget.adapter.interfaces.OnMultiItemClickListeners;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import retrofit2.Call;

/**
 * Created by xiaoyuan on 16/11/6.
 */

public class FindCityFragment extends BaseNetFragment<FindFollowBean> {


    @Bind(R.id.find_city_rv)
    RecyclerView findCityRv;
    @Bind(R.id.find_city_sp)
    SwipeRefreshLayout findCitySp;
    private FindCityAdapter mAdapter;
    private String cursor;
    private List<FindFollowBean.ResultBean.ListBean> mData = new ArrayList<>();
    private HomeActivity homeActivity;
    private String city = "";
    private boolean isLocation; //是否已经定位;
    private FrameLayout null_comment;
    private TextView empty_text;

    public static FindCityFragment newInstance() {
        FindCityFragment fragment = new FindCityFragment();
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
        goneLoading();
    }

    @Override
    protected void initViews() {
        empty_text = getViewById(R.id.empty_text);
        null_comment = getViewById(R.id.null_comment);
        findCitySp.setColorSchemeColors(getActivity().getResources().getColor(R.color.color_ff9600));
        findCitySp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData("", homeActivity.city);

            }
        });
        //初始化adapter
        mAdapter = new FindCityAdapter(getActivity(), null, true);
        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                getData(mData.get(mData.size() - 1).getId() + "", homeActivity.city);
            }
        });

        mAdapter.setOnMultiItemClickListener(new OnMultiItemClickListeners<FindFollowBean.ResultBean.ListBean>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, FindFollowBean.ResultBean.ListBean data, int position, int viewType) {
                FindInfoActivity.start(getActivity(), data);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        findCityRv.setLayoutManager(layoutManager);
        findCityRv.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
        LocationUtil.startLocation(mLocationListener);
//        TestApi testApi = RetrofitManager.getTestRetrofit().create(TestApi.class);
//        Call<Object> testCall = testApi.test();
//        testCall.enqueue(this);
        getData("", homeActivity.city);
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
                    getData("", homeActivity.city);
                } else {
                    getData("", city);
                }

                isLocation = true;
            }

            LocationUtil.onStop();


        }
    };

    @Subscribe
    public void home(HomeEvent homeEvent) {
        if (!city.equals(homeEvent.city)) {
            city = homeEvent.city;
            getData("", city);

        }

    }

    private void getData(final String cursor, String city) {
        this.cursor = cursor;
        QLog.d("city", "city=" + city);
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        FormBody body;
        if (TextUtils.isEmpty(cursor)) {
            body = new FormBody.Builder()
//                    .add("uid", PreferenceUtils.getsessionId(getActivity()))
                    .add("location", city).build();
        } else {
            body = new FormBody.Builder()
//                    .add("uid", PreferenceUtils.getsessionId(getActivity()))
                    .add("cursor", cursor)
                    .add("location", city).build();
        }
        Call<FindFollowBean> find = findApi.status_same_loc_list(body);
        find.enqueue(this);

        if (findCitySp != null) {
            findCitySp.setRefreshing(false);
        }
        mAdapter.setLoadingView(R.layout.load_more_layout);
    }

    @Override
    protected void processData(FindFollowBean data) {
        if (data.getError_code() == 10001) {
            null_comment.setVisibility(View.VISIBLE);
            if (findCitySp != null) {
                findCitySp.setRefreshing(false);
            }
            empty_text.setText("系统错误");
            return;
        }
        if (TextUtils.isEmpty(cursor)) {
            if (data.getResult() == null || data.getResult().getList().size() == 0) {
                mData.clear();
                mData.addAll(new ArrayList<FindFollowBean.ResultBean.ListBean>());
                mAdapter.setNewData(mData);
                null_comment.setVisibility(View.VISIBLE);
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }else {
                null_comment.setVisibility(View.GONE);
            }
            mData.clear();
            mData.addAll(data.getResult().getList());
            mAdapter.setNewData(mData);
            if (findCitySp != null) {
                findCitySp.setRefreshing(false);
            }
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
        return R.layout.frg_find_city;
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
