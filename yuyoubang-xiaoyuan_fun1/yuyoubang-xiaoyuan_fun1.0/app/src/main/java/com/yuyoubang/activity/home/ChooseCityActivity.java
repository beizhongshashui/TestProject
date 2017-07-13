package com.yuyoubang.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseNetActivity;
import com.yuyoubang.activity.sort.CharacterParser;
import com.yuyoubang.activity.sort.PinyinComparator;
import com.yuyoubang.activity.sort.SideBar;
import com.yuyoubang.activity.sort.SortAdapter;
import com.yuyoubang.activity.sort.SortModel;
import com.yuyoubang.bean.HotCityBean;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.CityApi;
import com.yuyoubang.otto.AppBus;
import com.yuyoubang.otto.CityEvent;
import com.yuyoubang.otto.HomeEvent;
import com.yuyoubang.utils.LocationUtil;
import com.yuyoubang.utils.ToastUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import retrofit2.Call;

/**
 * Created by xiaoyuan on 16/11/14.
 */
public class ChooseCityActivity extends BaseNetActivity<HotCityBean> {
    TagFlowLayout gvHotCity;
    TextView tvNowLocation;
    @Bind(R.id.lv_all_city)
    ListView lvAllCity;
    @Bind(R.id.dialog)
    TextView dialog;
    @Bind(R.id.sidrbar)
    SideBar sidebar;
    @Bind(R.id.search_city)
    EditText searchCity;

    private List<String> list = new ArrayList<>();
    private CharacterParser characterParser;
    private PinyinComparator pinyinComparator;
    private SortAdapter adapter;
    private List<SortModel> SourceDateList;
    private TagAdapter<String> tagAdapter;
    private Set<Integer> set = new HashSet<>();

    private HomeEvent homeEvent = HomeEvent.getInstance();
    private LinearLayout ll_cur_city;

    public static void start(Context context) {
        Intent intent = new Intent(context, ChooseCityActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("选择城市");

    }

    @Override
    protected int getContentResId() {
        return R.layout.act_choose_city;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        initView();
        LocationUtil.startLocation(mLocationListener);
        searchCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void loadData() {
        CityApi cityApi = RetrofitManager.getTestRetrofit().create(CityApi.class);
        FormBody body = new FormBody.Builder().build();
        Call<HotCityBean> city = cityApi.SYSTEM_HOT_CITIES_LIST(body);
        city.enqueue(this);
    }

    @Override
    protected void processData(HotCityBean hotCityBean) {
        goneLoading();
        if (hotCityBean != null) {
            if (hotCityBean.getResult() != null) {
                if (hotCityBean.getResult().getList() != null) {
                    for (int i = 0; i < hotCityBean.getResult().getList().size(); i++) {
                        list.add(hotCityBean.getResult().getList().get(i).getData().getCity_name());
                    }
                    tagAdapter.notifyDataChanged();
                }
            }
        }


    }


    @Override
    protected void onResume() {
        super.onResume();

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

            tvNowLocation.setText((String) msg.obj);

        }
    };

    private void initView() {
        View view = LayoutInflater.from(this).inflate(R.layout.choose_city_head, null);
        gvHotCity = (TagFlowLayout) view.findViewById(R.id.gv_hot_city);
        tvNowLocation = (TextView) view.findViewById(R.id.tv_now_location);
        ll_cur_city = (LinearLayout) view.findViewById(R.id.ll_cur_city);
        lvAllCity.addHeaderView(view);
        tagAdapter = new TagAdapter<String>(list) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) getLayoutInflater().inflate(R.layout.hot_city_item,
                        gvHotCity, false);
                tv.setText(s);
                return tv;
            }
        };
        gvHotCity.setAdapter(tagAdapter);
        gvHotCity.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                HomeEvent.getInstance().city = list.get(position);
                AppBus.getInstance().post(homeEvent);
                AppBus.getInstance().post(new CityEvent(list.get(position)));
                LocationUtil.onDestroy();
                finish();

                return false;
            }
        });

        ll_cur_city.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                String locationCity = tvNowLocation.getText().toString();
                if (TextUtils.isEmpty(locationCity)){
                    ToastUtils.showShort("定位失败");
                    return;
                }
                String substring = locationCity.substring(0, locationCity.length() - 1);
                HomeEvent.getInstance().city = substring;
                AppBus.getInstance().post(homeEvent);
                AppBus.getInstance().post(new CityEvent(substring));
                LocationUtil.onDestroy();
                finish();
            }
        });

        characterParser = CharacterParser.getInstance();


        pinyinComparator = new PinyinComparator();

        sidebar.setTextView(dialog);

        sidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    lvAllCity.setSelection(position);
                }

            }
        });

        lvAllCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    return;
                }
                if (SourceDateList != null && SourceDateList.size() > 0) {
                    HomeEvent.getInstance().city = adapter.getList().get(position - 1).getName();
                    AppBus.getInstance().post(homeEvent);
                    AppBus.getInstance().post(new CityEvent(adapter.getList().get(position - 1).getName()));
                    LocationUtil.onDestroy();
                    finish();
                }

            }
        });

        SourceDateList = filledData(getResources().getStringArray(R.array.date));

        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new SortAdapter(this, SourceDateList);
        lvAllCity.setAdapter(adapter);

    }


    private List<SortModel> filledData(String[] date) {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < date.length; i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date[i]);
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
        } else {
            filterDateList.clear();
            for (SortModel sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // ���a-z��������
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocationUtil.onDestroy();

    }


}
