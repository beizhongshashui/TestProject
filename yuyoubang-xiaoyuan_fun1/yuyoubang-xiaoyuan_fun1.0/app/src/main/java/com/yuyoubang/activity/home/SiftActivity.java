package com.yuyoubang.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.otto.HomeEvent;
import com.yuyoubang.view.ATDragView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xiaoyuan on 16/11/14.
 */
public class SiftActivity extends BaseActivity {


    @Bind(R.id.go_time)
    TagFlowLayout goTime;
    @Bind(R.id.go_days)
    TagFlowLayout goDays;
    @Bind(R.id.at_dragView)
    ATDragView atDragView;
    private List<String> list = new ArrayList<>();
    private List<String> list1 = new ArrayList<>();
    List<String> data = new ArrayList<>();
    private TagAdapter<String> tagAdapter;
    private TagAdapter<String> tagAdapter1;
    private HomeEvent homeEvent = HomeEvent.getInstance();

    public static void start(Context context) {
        Intent intent = new Intent(context, SiftActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("筛选");
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_sift;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initView();
        Set<Integer> set = new HashSet<>();
        tagAdapter.setSelectedList(set);
        tagAdapter1.setSelectedList(set);
        atDragView.setReset();
        homeEvent.day_max = "";
        homeEvent.day_min = "";
        homeEvent.start_timestamp = "";
        homeEvent.min_price = "";
        homeEvent.max_price = "";
    }


    private void initView() {
        list.add("1月");
        list.add("2月");
        list.add("3月");
        list.add("4月");
        list.add("5月");
        list.add("6月");
        list.add("7月");
        list.add("8月");
        list.add("9月");
        list.add("10月");
        list.add("11月");
        list.add("12月");
        tagAdapter = new TagAdapter<String>(list) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) getLayoutInflater().inflate(R.layout.flag_adapter,
                        goTime, false);
                tv.setText(s);
                return tv;
            }
        };
        goTime.setAdapter(tagAdapter);

        if (homeEvent.start_timestamp != null) {
            for (int i = 0; i < list.size(); i++) {
                if (homeEvent.start_timestamp.equals(list.get(i))) {
                    tagAdapter.setSelectedList(i);
                    tagAdapter.notifyDataChanged();
                }
            }

        }

        list1.add("1天");
        list1.add("2天");
        list1.add("3天");
        list1.add("3-7天");
        list1.add("7天以上");
        tagAdapter1 = new TagAdapter<String>(list1) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) getLayoutInflater().inflate(R.layout.flag_adapter,
                        goDays, false);
                tv.setText(s);
                return tv;
            }
        };

        goDays.setAdapter(tagAdapter1);
        if (homeEvent.day_max != null) {
            for (int i = 0; i < list1.size(); i++) {
                String temp = list1.get(i).replace("天", "");
                if (homeEvent.day_max.equals(temp)) {
                    tagAdapter1.setSelectedList(i);
                    tagAdapter1.notifyDataChanged();
                }
            }

        }

        goTime.setOnSelectListener(new TagFlowLayout.OnSelectListener() {

            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                if (selectPosSet.size() > 0) {
                    List<Integer> arrayList = new ArrayList<Integer>(selectPosSet);
                    homeEvent.start_timestamp = getTime(list.get(arrayList.get(0))) + "000";
                } else {
                    homeEvent.start_timestamp = "";
                }

            }
        });

        goDays.setOnSelectListener(new TagFlowLayout.OnSelectListener() {

            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                if (selectPosSet.size() > 0) {
                    List<Integer> list = new ArrayList<Integer>(selectPosSet);
                    if (list1.get(list.get(0)).equals("1天")) {
                        homeEvent.day_max = 1 + "";
                        homeEvent.day_min = 0 + "";
                    } else if (list1.get(list.get(0)).equals("2天")) {
                        homeEvent.day_max = 2 + "";
                        homeEvent.day_min = 1 + "";
                    } else if (list1.get(list.get(0)).equals("3天")) {
                        homeEvent.day_max = 3 + "";
                        homeEvent.day_min = 2 + "";
                    } else if (list1.get(list.get(0)).equals("3-7天")) {
                        homeEvent.day_max = 3 + "";
                        homeEvent.day_min = 7 + "";
                    } else if (list1.get(list.get(0)).equals("7天以上")) {
                        homeEvent.day_max = "";
                        homeEvent.day_min = "";
                    }

                } else {
                    homeEvent.day_max = "";
                    homeEvent.day_min = "";
                }

            }
        });


        data.add("0");
        data.add("200");
        data.add("500");
        data.add("1000");
        data.add("2500");
        data.add("5000");
        data.add("不限");

        atDragView.setData(data, new ATDragView.OnDragFinishedListener() {
            @Override
            public void dragFinished(int leftPostion, int rightPostion) {
                homeEvent.max_price = data.get(rightPostion);
                homeEvent.min_price = data.get(leftPostion);
                if (data.get(rightPostion).equals("不限")) {
                    homeEvent.max_price = "";
//                    homeEvent.min_price = "";
                }
            }
        });

        if (homeEvent.max_price != null) {
            for (int i = 0; i < data.size(); i++) {
                if (homeEvent.max_price.equals(data.get(i))) {
                    atDragView.setRight(i);
                }
            }
        }


        if (homeEvent.min_price != null) {
            for (int i = 0; i < data.size(); i++) {
                if (homeEvent.min_price.equals(data.get(i))) {
                    atDragView.setLeft(i);
                }
            }


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.reset, R.id.sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reset:
                Set<Integer> set = new HashSet<>();
                tagAdapter.setSelectedList(set);
                tagAdapter1.setSelectedList(set);
                atDragView.setReset();
                homeEvent.day_max = "";
                homeEvent.day_min = "";
                homeEvent.start_timestamp = "";
                homeEvent.min_price = "";
                homeEvent.max_price = "";
                break;
            case R.id.sure:
                SiftResultActiivty.start(this, homeEvent);
                finish();
                break;
        }
    }


    /**
     * 月份转时间戳
     *
     * @param month
     * @return
     */
    public static String getTime(String month) {
        String substring = "";
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int curMonth = c.get(Calendar.MONTH);
        if (!TextUtils.isEmpty(month)) {
            if (month.length() > 2) {
                substring = month.substring(0, 2);
            } else {
                substring = month.substring(0, 1);
            }
            if (curMonth > Integer.parseInt(substring)) {
                year = year + 1;
            }
        }
        String time = year + "年" + month + "08日11时17分00秒";
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        Date d;
        try {
            d = sdf.parse(time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return re_time;
    }
}
