package com.yuyoubang.fragment.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.squareup.otto.Subscribe;
import com.yuyoubang.R;
import com.yuyoubang.activity.home.ChooseCityActivity;
import com.yuyoubang.activity.home.HomeActivity;
import com.yuyoubang.activity.home.SearchActivity;
import com.yuyoubang.activity.home.SiftActivity;
import com.yuyoubang.adapter.PopAdapter;
import com.yuyoubang.bean.SystemTripBean;
import com.yuyoubang.fragment.base.BaseFragment;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.TripApi;
import com.yuyoubang.otto.AppBus;
import com.yuyoubang.otto.CityEvent;
import com.yuyoubang.otto.HomeEvent;
import com.yuyoubang.utils.LocationUtil;
import com.yuyoubang.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 16/11/6.
 */

public class HomeFragment extends BaseFragment {
    @Bind(R.id.tv_hot)
    TextView tvHot;
    @Bind(R.id.tv_city)
    TextView tvCity;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.tv_recent)
    TextView tvRecent;
    @Bind(R.id.frg_content)
    FrameLayout frgContent;
    @Bind(R.id.yellow_line1)
    View yellowLine1;
    @Bind(R.id.yellow_line2)
    View yellowLine2;
    @Bind(R.id.yellow_line3)
    View yellowLine3;
    @Bind(R.id.yellow_line4)
    View yellowLine4;
    @Bind(R.id.ll)
    LinearLayout ll;
    @Bind(R.id.home_arrow)
    ImageView homeArrow;
    @Bind(R.id.now_city)
    TextView nowCity;
    @Bind(R.id.type_layout)
    LinearLayout type_layout;
    private FragmentManager fragmentManager;


    private HotFragment hotFragment;
    private CityFragment cityFragment;
    private TypeFragment typeFragment;
    private RecentFragment recentFragment;
    private PopupWindow popupWindow;
    private ListView lv_group;
    private View view;

    private List<SystemTripBean.ResultBean.ListBean> listPop = new ArrayList<>();
    private int mPosition = 0;//记录已经选择的类型
    private int showFrag = 0;//记录选择的frag

    private HomeEvent homeEvent = HomeEvent.getInstance();
    private HomeActivity homeActivity;
    private boolean isLocation; //是否已经定位;


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        AppBus.getInstance().register(this);
        fragmentManager = getActivity().getSupportFragmentManager();
        setTabSelection(0);
        getTirpTrip();
        LocationUtil.startLocation(mLocationListener);
    }


    @Override
    protected int getContentResId() {
        return R.layout.frg_home;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        homeActivity = (HomeActivity) activity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        AppBus.getInstance().unregister(this);

    }

    @OnClick({R.id.tv_hot, R.id.tv_city, R.id.tv_type, R.id.tv_recent, R.id.now_city, R.id.sift, R.id.type_layout, R.id.search, R.id.home_arrow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_hot:
                setTabSelection(0);
                break;
            case R.id.tv_city:
                setTabSelection(1);
                break;
            case R.id.tv_type:
                setTabSelection(2);
                break;
            case R.id.type_layout:
                setTabSelection(2);
                break;
            case R.id.home_arrow:
                setTabSelection(2);
                break;
            case R.id.tv_recent:
                setTabSelection(3);
                break;

            case R.id.now_city:
                Intent intent = new Intent(getActivity(), ChooseCityActivity.class);
                getActivity().startActivityForResult(intent, 100);
                break;
            case R.id.sift:
                SiftActivity.start(getActivity());
                break;

            case R.id.search:
                SearchActivity.start(getActivity());
                break;

        }
    }

    private void setTabSelection(int index) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        hide();
        hideText();

        switch (index) {
            case 0:
                yellowLine1.setVisibility(View.VISIBLE);
                tvHot.setTextColor(getResources().getColor(R.color.color_ff9600));
                if (hotFragment == null) {
                    hotFragment = HotFragment.newInstance();
                    transaction.add(R.id.frg_content, hotFragment);
                } else {
                    transaction.show(hotFragment);
                }
                showFrag = index;
                break;
            case 1:
                yellowLine2.setVisibility(View.VISIBLE);
                tvCity.setTextColor(getResources().getColor(R.color.color_ff9600));
                if (cityFragment == null) {
                    cityFragment = CityFragment.newInstance();
                    transaction.add(R.id.frg_content, cityFragment);
                } else {
                    transaction.show(cityFragment);
                }
                showFrag = index;
                break;
            case 2:
                yellowLine3.setVisibility(View.VISIBLE);
                tvType.setTextColor(getResources().getColor(R.color.color_ff9600));
//                if (showFrag == 2) {
                showPopupWindow(ll, transaction);
//                    return;
//                }xxxxx
                if (typeFragment == null) {
                    typeFragment = TypeFragment.newInstance(listPop.get(mPosition).getData().getTrip_route_type());
                    transaction.add(R.id.frg_content, typeFragment);
//                    if (listPop.size() > 0) {
//                        if(listPop.get(mPosition).getData().getTrip_route_type().equals("全部路线类型")){
//                            typeFragment = TypeFragment.newInstance("全部");
//                        }else {
//
//                        }
////                        typeFragment = TypeFragment.newInstance(listPop.get(mPosition).getData().getTrip_route_type());
//                    }

//                    else {
//                        typeFragment = TypeFragment.newInstance("全部");
//                    }

                } else {
                    transaction.show(typeFragment);
                }
                showFrag = index;
                break;
            case 3:
                yellowLine4.setVisibility(View.VISIBLE);
                tvRecent.setTextColor(getResources().getColor(R.color.color_ff9600));
                if (recentFragment == null) {
                    recentFragment = RecentFragment.newInstance();
                    transaction.add(R.id.frg_content, recentFragment);
                } else {
                    transaction.show(recentFragment);
                }
                showFrag = index;
                break;

        }
        transaction.commitAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (hotFragment != null) {
            transaction.hide(hotFragment);
        }
        if (cityFragment != null) {
            transaction.hide(cityFragment);
        }
        if (typeFragment != null) {
            transaction.hide(typeFragment);
        }
        if (recentFragment != null) {
            transaction.hide(recentFragment);
        }
    }

    private void hide() {
        yellowLine1.setVisibility(View.INVISIBLE);
        yellowLine2.setVisibility(View.INVISIBLE);
        yellowLine3.setVisibility(View.INVISIBLE);
        yellowLine4.setVisibility(View.INVISIBLE);

    }

    private void hideText() {
        tvHot.setTextColor(getResources().getColor(R.color.color_898989));
        tvCity.setTextColor(getResources().getColor(R.color.color_898989));
        tvType.setTextColor(getResources().getColor(R.color.color_898989));
        tvRecent.setTextColor(getResources().getColor(R.color.color_898989));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            return;
        }

    }

    private void showPopupWindow(View parent, final FragmentTransaction transaction) {
        homeArrow.setBackgroundResource(R.mipmap.fragment_home_arrow_focus);
        for (int i = 0; i < listPop.size(); i++) {
            listPop.get(mPosition).getData().setSelector(0);
            if (i == mPosition) {
                listPop.get(mPosition).getData().setSelector(1);
            }
        }
        if (popupWindow == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.pop_layout, null);
            lv_group = (ListView) view.findViewById(R.id.lv_pop);

            PopAdapter adapter = new PopAdapter(getActivity(), listPop);
            lv_group.setAdapter(adapter);
            popupWindow = new PopupWindow(view, 600, ScreenUtil.getScreenHeight(getActivity()) / 2);
        }
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        WindowManager windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        int xPos = -popupWindow.getWidth() / 2
                + tvType.getWidth();
        popupWindow.showAsDropDown(parent, ScreenUtil.getScreenWidth(getActivity()) / 4, 0);

        lv_group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (popupWindow != null) {
                    popupWindow.dismiss();
                    mPosition = position;
//                    transaction.remove(typeFragment);
                    typeFragment.SetType(listPop.get(mPosition).getData().getTrip_route_type());
//                    typeFragment = TypeFragment.newInstance(listPop.get(mPosition).getData().getTrip_route_type());
//                    transaction.add(R.id.frg_content, typeFragment);
//                    transaction.commitAllowingStateLoss();
                }

            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                homeArrow.setBackgroundResource(R.mipmap.fragment_home_arrow);
            }
        });
    }


    private void getTirpTrip() {
        TripApi tripApi = RetrofitManager.getTestRetrofit().create(TripApi.class);
        FormBody body = new FormBody.Builder().build();
        Call<SystemTripBean> data = tripApi.system_trip(body);
        data.enqueue(new Callback<SystemTripBean>() {
            @Override
            public void onResponse(Call<SystemTripBean> call, Response<SystemTripBean> response) {
                listPop = response.body().getResult().getList();
                SystemTripBean.ResultBean.ListBean.DataBean dataBean = new SystemTripBean.ResultBean.ListBean.DataBean();
                SystemTripBean.ResultBean.ListBean listBean = new SystemTripBean.ResultBean.ListBean();
                dataBean.setTrip_route_type("全部路线类型");
                listBean.setData(dataBean);
                listPop.add(0, listBean);
            }

            @Override
            public void onFailure(Call<SystemTripBean> call, Throwable t) {

            }
        });


    }

    @Subscribe
    public void city(CityEvent cityEvent) {
        nowCity.setText(cityEvent.city + "");
        AppBus.getInstance().post(homeEvent);
        homeActivity.city = cityEvent.city + "";

    }

    AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (!TextUtils.isEmpty(aMapLocation.getCity())) {
                Message message = Message.obtain();
                message.obj = aMapLocation.getCity();
                message.what = 200;
                handler.sendMessage(message);
            }


        }
    };

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 200) {
                String city = (String) msg.obj;
                if (!isLocation) {
                    homeActivity.city = city.replace("市", "");
                    nowCity.setText(city.replace("市", ""));
                    isLocation = true;
                }

                LocationUtil.onStop();
            }

        }
    };


}
