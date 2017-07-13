package com.yuyoubang.activity.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.utils.LaunchOperate;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hongchen on 16/11/22.
 */

public class ChooseContentActivity extends BaseActivity {

    @Bind(R.id.hot_city)
    TagFlowLayout hotCity;
    @Bind(R.id.ages)
    TagFlowLayout ages;
    @Bind(R.id.sex)
    TagFlowLayout sex;
    @Bind(R.id.reset)
    TextView reset;
    @Bind(R.id.sure)
    TextView sure;

    private List<String> sexList = new ArrayList<>();
    private List<String> ageList = new ArrayList<>();
    private List<String> hotCityList = new ArrayList<>();

    private TagAdapter<String> sexAdapter;
    private TagAdapter<String> ageAdapter;
    private TagAdapter<String> hotAdapter;

    private String city = "";
    private String gender = "";
    private String age_start = "";
    private String age_end = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        setListener();
    }

    private void setListener() {

        sexAdapter = new TagAdapter<String>(sexList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) getLayoutInflater().inflate(R.layout.choose_content_adapter,
                        sex, false);
                tv.setText(s);
                return tv;
            }
        };
        sex.setAdapter(sexAdapter);
        sex.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                return false;
            }
        });

        sex.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                if (selectPosSet.size() > 0) {
                    List<Integer> list = new ArrayList<>(selectPosSet);
                    gender = sexList.get(list.get(0));
                    if (gender.equals(sexList.get(0))) {
                        gender = "";
                    } else if (gender.equals(sexList.get(1))) {
                        gender = 0 + "";
                    } else if (gender.equals(sexList.get(2))) {
                        gender = 1 + "";
                    }
                } else {
                    gender = "";
                }
            }
        });

        ageAdapter = new TagAdapter<String>(ageList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) getLayoutInflater().inflate(R.layout.choose_content_adapter,
                        ages, false);
                tv.setText(s);
                return tv;
            }
        };
        ages.setAdapter(ageAdapter);
        ages.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                return false;
            }
        });

        ages.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                if (selectPosSet.size() > 0) {
                    List<Integer> list = new ArrayList<Integer>(selectPosSet);
                    if (ageList.get(list.get(0)).equals(ageList.get(0))) {
                        age_start = "";
                        age_end = "";
                    } else if (ageList.get(list.get(0)).equals(ageList.get(1))) {
                        age_start = 18 + "";
                        age_end = 22 + "";
                    } else if (ageList.get(list.get(0)).equals(ageList.get(2))) {
                        age_start = 23 + "";
                        age_end = 26 + "";
                    } else if (ageList.get(list.get(0)).equals(ageList.get(3))) {
                        age_start = 27 + "";
                        age_end = 35 + "";
                    } else if (ageList.get(list.get(0)).equals(ageList.get(4))) {
                        age_start = 36 + "";
                        age_end = 200 + "";
                    }

                } else {
                    age_start = "";
                    age_end = "";
                }

            }
        });


        hotAdapter = new TagAdapter<String>(hotCityList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) getLayoutInflater().inflate(R.layout.choose_content_adapter,
                        hotCity, false);
                tv.setText(s);
                return tv;
            }
        };
        hotCity.setAdapter(hotAdapter);
        hotCity.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                return false;
            }
        });

        hotCity.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                if (selectPosSet.size() > 0) {
                    List<Integer> list = new ArrayList<>(selectPosSet);
                    city = hotCityList.get(list.get(0));
                    if (city.equals(hotCityList.get(0))) {
                        city = "";
                    }
                } else {
                    city = "";
                }
            }
        });

        reset.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                Set<Integer> set = new HashSet<>();
                sexAdapter.setSelectedList(set);
                ageAdapter.setSelectedList(set);
                hotAdapter.setSelectedList(set);
                city = "";
                gender = "";
                age_start = "";
                age_end = "";
            }
        });

        sure.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                LaunchOperate.openSerachStatusActivity(ChooseContentActivity.this, age_end, age_start, gender, city);
            }
        });
    }

    private void initView() {

        sexList.add("全部");
        sexList.add("男");
        sexList.add("女");

        ageList.add("全部");
        ageList.add("18-22");
        ageList.add("23-26");
        ageList.add("27-35");
        ageList.add("36岁以上");

        hotCityList.add("不限");
        hotCityList.add("北京");
        hotCityList.add("上海");
        hotCityList.add("广州");
        hotCityList.add("深圳");
        hotCityList.add("杭州");
        hotCityList.add("成都");
        hotCityList.add("重庆");
        hotCityList.add("天津");
        hotCityList.add("南京");
        hotCityList.add("武汉");
        hotCityList.add("沈阳");
        hotCityList.add("长沙");
        hotCityList.add("大连");
        hotCityList.add("厦门");
        hotCityList.add("无锡");
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("筛选");
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_choose_content;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
