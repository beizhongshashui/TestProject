package com.yuyoubang.activity.mine.order;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.activity.sort.CharacterParser;
import com.yuyoubang.activity.sort.PinyinComparator;
import com.yuyoubang.activity.sort.SideBar;
import com.yuyoubang.activity.sort.SortAdapter;
import com.yuyoubang.activity.sort.SortModel;
import com.yuyoubang.bean.City;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.CityApi;
import com.yuyoubang.utils.LocationUtil;
import com.yuyoubang.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hongchen on 16/12/23.
 */

public class EditorChooseCityActivity extends BaseActivity {

    @Bind(R.id.info_location)
    TextView infoLocation;
    @Bind(R.id.info_address)
    EditText infoAddress;
    @Bind(R.id.lv_all_city)
    ListView lvAllCity;
    @Bind(R.id.dialog)
    TextView dialog;
    @Bind(R.id.sidrbar)
    SideBar sidebar;
    @Bind(R.id.search_city)
    EditText searchCity;

    @Bind(R.id.gone_layout)
    LinearLayout gone_layout;
    private int state = 0;
    private CharacterParser characterParser;
    private PinyinComparator pinyinComparator;
    private SortAdapter adapter;
    private List<SortModel> SourceDateList;
    private List<SortModel> CityDateList;

    private String province = "";
    private String area = "";
    private List<SortModel> allCityList = new ArrayList<>();
    private List<SortModel> locationCityList = new ArrayList<>();
    private String areaCode;
    private String provinceCode;
    private List<String> marketFour = new ArrayList<>();

    private HeaderBuilder builder;

    @Override
    protected void initTitleBar(final HeaderBuilder builder) {
        this.builder = builder;
        if (state == 1) {
            builder.setTitle(province);
        } else {
            builder.setTitle("选择城市");
        }

        builder.setRightTvText("保存", new OnClickListener() {
            @Override
            protected void clickOperate() {
                if (TextUtils.isEmpty(province)) {
                    ToastUtils.showLong("请选择具体地址");
                    return;
                }
                if (TextUtils.isEmpty(area)) {
                    ToastUtils.showLong("请选择详细地址");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("province", province);
                intent.putExtra("user_location", area);
                intent.putExtra("province_code", provinceCode);
                intent.putExtra("city_code", areaCode);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        builder.setLeftOperate(new OnClickListener() {
            @Override
            protected void clickOperate() {
                if (state == 1) {
                    builder.setTitle("选择城市");
                    adapter.updateListView(SourceDateList);
                    state--;
                } else {
                    finish();
                }
            }
        });
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

            if (TextUtils.isEmpty(province) && TextUtils.isEmpty(area)) {
                infoLocation.setText((String) msg.obj);
            } else {
                infoLocation.setText(province + area);
            }

        }
    };

    @Override
    protected int getContentResId() {
        return R.layout.act_set_location;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initView();

        loadDataAllCity();
    }

    private void loadDataAllCity() {
        onShowProgressDlg();
        FormBody formBody = new FormBody.Builder()
                .build();
        CityApi cityApi = RetrofitManager.getTestRetrofit().create(CityApi.class);
        Call<City> cityCall = cityApi.all_location(formBody);
        cityCall.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                cancelProgressDlg();
                if (response.body().getError_code() == 0) {
                    if (response.body().getResult() != null) {
                        if (response.body().getResult().getList() != null) {
                            if (response.body().getResult().getList().size() > 0) {
                                List<City.ResultBean.ListBean> allList = response.body().getResult().getList();
                                for (int i = 0; i < allList.size(); i++) {
                                    SortModel regionModel = new SortModel();
                                    regionModel.setId(Long.valueOf(allList.get(i).getCode()));
                                    regionModel.setName(allList.get(i).getName());
                                    allCityList.add(regionModel);
                                }
                                SourceDateList = filledData(allCityList);
                                Collections.sort(SourceDateList, pinyinComparator);
                                adapter = new SortAdapter(EditorChooseCityActivity.this, SourceDateList);
                                lvAllCity.setAdapter(adapter);
                            }
                        }
                    }
                } else {
                    ToastUtils.showShort("系统错误");
                    cancelProgressDlg();
                }
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (state == 1) {
            builder.setTitle("选择城市");
            adapter.updateListView(SourceDateList);
            state--;
        } else {
            finish();
        }
    }


    private void initView() {
        marketFour.add("北京市");
        marketFour.add("天津市");
        marketFour.add("上海市");
        marketFour.add("重庆市");
        infoAddress.setVisibility(View.GONE);
        gone_layout.setVisibility(View.GONE);
        if (TextUtils.isEmpty(province) && TextUtils.isEmpty(area)) {
            LocationUtil.startLocation(mLocationListener);
        } else {
            infoLocation.setText(province + area);
        }
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
                                    final int position, long id) {

                if (adapter.getList() != null && adapter.getList().size() > 0) {
                    area = "";
                    if (state == 0) {
                        if (adapter.getList().get(position).getName().equals(marketFour.get(0))) {
                            locationCityList = new ArrayList<SortModel>();
                            SortModel regionModel = new SortModel();
                            regionModel.setId(Long.valueOf("110100"));
                            regionModel.setName(marketFour.get(0));
                            locationCityList.add(regionModel);
                            CityDateList = filledData(locationCityList);
                            province = adapter.getList().get(position).getName();
                            provinceCode = String.valueOf(adapter.getList().get(position).getId());
                            Collections.sort(CityDateList, pinyinComparator);
                            adapter.updateListView(CityDateList);
                            state++;
                            infoLocation.setText(province);
                            builder.setTitle(province);
                            return;
                        }
                        if (adapter.getList().get(position).getName().equals(marketFour.get(1))) {
                            locationCityList = new ArrayList<SortModel>();
                            SortModel regionModel = new SortModel();
                            regionModel.setId(Long.valueOf("120100"));
                            regionModel.setName(marketFour.get(1));
                            locationCityList.add(regionModel);
                            CityDateList = filledData(locationCityList);
                            province = adapter.getList().get(position).getName();
                            provinceCode = String.valueOf(adapter.getList().get(position).getId());
                            Collections.sort(CityDateList, pinyinComparator);
                            adapter.updateListView(CityDateList);
                            state++;
                            infoLocation.setText(province);
                            builder.setTitle(province);
                            return;
                        }
                        if (adapter.getList().get(position).getName().equals(marketFour.get(2))) {
                            locationCityList = new ArrayList<SortModel>();
                            SortModel regionModel = new SortModel();
                            regionModel.setId(Long.valueOf("310100"));
                            regionModel.setName(marketFour.get(2));
                            locationCityList.add(regionModel);
                            CityDateList = filledData(locationCityList);
                            province = adapter.getList().get(position).getName();
                            provinceCode = String.valueOf(adapter.getList().get(position).getId());
                            Collections.sort(CityDateList, pinyinComparator);
                            adapter.updateListView(CityDateList);
                            state++;
                            infoLocation.setText(province);
                            builder.setTitle(province);
                            return;
                        }
                        if (adapter.getList().get(position).getName().equals(marketFour.get(3))) {
                            locationCityList = new ArrayList<SortModel>();
                            SortModel regionModel = new SortModel();
                            regionModel.setId(Long.valueOf("500100"));
                            regionModel.setName(marketFour.get(3));
                            locationCityList.add(regionModel);
                            CityDateList = filledData(locationCityList);
                            province = adapter.getList().get(position).getName();
                            provinceCode = String.valueOf(adapter.getList().get(position).getId());
                            Collections.sort(CityDateList, pinyinComparator);
                            adapter.updateListView(CityDateList);
                            state++;
                            infoLocation.setText(province);
                            builder.setTitle(province);
                            return;
                        }
                        onShowProgressDlg();
                        locationCityList = new ArrayList<SortModel>();
                        FormBody formBody = new FormBody.Builder()
                                .add("parent", String.valueOf(adapter.getList().get(position).getId()))//省份编码
                                .build();
                        CityApi cityApi = RetrofitManager.getTestRetrofit().create(CityApi.class);
                        Call<City> cityCall = cityApi.found_city(formBody);
                        cityCall.enqueue(new Callback<City>() {
                            @Override
                            public void onResponse(Call<City> call, Response<City> response) {
                                cancelProgressDlg();
                                if (response.body().getError_code() == 0) {
                                    if (response.body().getResult() != null) {
                                        if (response.body().getResult().getList() != null) {
                                            if (response.body().getResult().getList().size() > 0) {
                                                List<City.ResultBean.ListBean> locationList = response.body().getResult().getList();
                                                for (int i = 0; i < locationList.size(); i++) {
                                                    SortModel regionModel = new SortModel();
                                                    regionModel.setId(Long.valueOf(locationList.get(i).getCode()));
                                                    regionModel.setName(locationList.get(i).getName());
                                                    locationCityList.add(regionModel);
                                                }
                                                CityDateList = filledData(locationCityList);
                                                province = adapter.getList().get(position).getName();
                                                provinceCode = String.valueOf(adapter.getList().get(position).getId());
                                                Collections.sort(CityDateList, pinyinComparator);
                                                adapter.updateListView(CityDateList);
                                                state++;
                                                infoLocation.setText(province);
                                                builder.setTitle(province);
                                            }
                                        }
                                    }
                                } else {
                                    ToastUtils.showShort("系统错误");
                                    cancelProgressDlg();
                                }
                            }

                            @Override
                            public void onFailure(Call<City> call, Throwable t) {

                            }
                        });
                    } else {
                        for (int i = 0; i < adapter.getList().size(); i++) {
                            if (i == position){
                                if (adapter.getList().get(position).getIsChoose() == 0) {
                                    adapter.getList().get(position).setIsChoose(1);
                                    area = adapter.getList().get(position).getName();
                                    areaCode = String.valueOf(adapter.getList().get(position).getId());
                                } else {
                                    adapter.getList().get(position).setIsChoose(0);
                                    area = "";
                                }
                            }else {
                                adapter.getList().get(i).setIsChoose(0);
                            }
                        }
//                        area = adapter.getList().get(position).getName();
//                        areaCode = String.valueOf(adapter.getList().get(position).getId());
                        infoLocation.setText(province + area);
                        adapter.notifyDataSetChanged();
                    }
                }

            }
        });
    }

    private List<SortModel> filledData(List<SortModel> data) {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < data.size(); i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(data.get(i).getName());
            sortModel.setId(data.get(i).getId());
            String pinyin = characterParser.getSelling(data.get(i).getName());
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

        switch (state) {
            case 0:
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
                break;
            case 1:
                if (TextUtils.isEmpty(filterStr)) {
                    filterDateList = CityDateList;
                } else {
                    filterDateList.clear();
                    for (SortModel sortModel : CityDateList) {
                        String name = sortModel.getName();
                        if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())) {
                            filterDateList.add(sortModel);
                        }
                    }
                }
                break;
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
