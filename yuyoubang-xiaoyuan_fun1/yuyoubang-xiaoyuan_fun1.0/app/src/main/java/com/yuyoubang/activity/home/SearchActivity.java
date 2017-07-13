package com.yuyoubang.activity.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.adapter.SearchHistoryAdapter;
import com.yuyoubang.bean.HotCityBean;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.CityApi;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.widget.recycleview.RecyclerListView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 16/12/13.
 */
public class SearchActivity extends BaseActivity {


    private ImageView left_iv;
    private TextView sift;
    private EditText et_content;
    private TagFlowLayout gvHotCity;
    private List<String> list = new ArrayList<>();
    private List<String> historyList = new ArrayList<>();
    private TagAdapter<String> tagAdapter;
    private RecyclerListView recyclerListView;
    private SearchHistoryAdapter searchHistoryAdapter;
    private TextView tv_del;
    private List<HotCityBean.ResultBean.ListBean> cityList;

    public static void start(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
    }

    private void setListener() {
        left_iv.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                finish();
            }
        });
        sift.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//搜索结果
                String content = et_content.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    ToastUtils.showShort("请输入关键字");
                    return;
                }

                if (historyList.size() > 0) {
                    if (!historyList.contains(content)) {
                        historyList.add(content);
                    }
                } else {
                    historyList.add(content);
                }
                PreferenceUtils.saveList(SearchActivity.this, historyList);
                LaunchOperate.openSearchResultActivity(SearchActivity.this, content);
            }
        });

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
                String s = list.get(position);
                et_content.setText(s);
                return false;
            }
        });
        searchHistoryAdapter = new SearchHistoryAdapter(recyclerListView, this, historyList);
        recyclerListView.setAdapter(searchHistoryAdapter);

        tv_del.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                historyList = new ArrayList<>();
                PreferenceUtils.saveList(SearchActivity.this, historyList);
                searchHistoryAdapter.replaceAll(historyList);
                searchHistoryAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        PreferenceUtils.loadList(this, historyList);
        if (historyList.size() > 0) {
            Collections.reverse(historyList);
            searchHistoryAdapter.replaceAll(historyList);
            searchHistoryAdapter.notifyDataSetChanged();
        }
    }

    private void initView() {
        CityApi cityApi = RetrofitManager.getTestRetrofit().create(CityApi.class);
        FormBody body = new FormBody.Builder().build();
        Call<HotCityBean> city = cityApi.SYSTEM_HOT_CITIES_LIST(body);
        city.enqueue(new Callback<HotCityBean>() {
            @Override
            public void onResponse(Call<HotCityBean> call, Response<HotCityBean> response) {
                if (response.body().getError_code() == 0) {
                    if (response.body().getResult() != null){
                        if (response.body().getResult().getList() != null){
                            if (response.body().getResult().getList().size() > 0){
                                cityList = response.body().getResult().getList();
                                for (int i = 0; i < cityList.size(); i++) {
                                    list.add(cityList.get(i).getData().getCity_name());
                                }
                                tagAdapter.notifyDataChanged();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<HotCityBean> call, Throwable t) {

            }
        });
        /*list.add("乌鲁木齐");
        list.add("郑州");
        list.add("西安");
        list.add("扬州");
        list.add("西藏");
        list.add("花果山");
        list.add("丽江");
        list.add("乌鲁木统");
        list.add("稻城亚丁");*/
        left_iv = getViewById(R.id.left_iv);
        sift = getViewById(R.id.sift);
        et_content = getViewById(R.id.et_content);
        gvHotCity = getViewById(R.id.gv_hot_city);
        recyclerListView = getViewById(R.id.more_follow);
        tv_del = getViewById(R.id.tv_del);
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.goneToolbar();
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_search;
    }
}
