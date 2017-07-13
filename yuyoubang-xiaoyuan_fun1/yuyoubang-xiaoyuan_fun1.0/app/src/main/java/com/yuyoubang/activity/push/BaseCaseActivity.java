package com.yuyoubang.activity.push;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.bean.NewTrip;
import com.yuyoubang.bean.SystemTripBean;
import com.yuyoubang.bean.TripLabel;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.BaseCallback;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.TripApi;
import com.yuyoubang.utils.ToastUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 16/11/18.
 */
public class BaseCaseActivity extends BaseActivity {
    @Bind(R.id.act_title)
    EditText actTitle;
    @Bind(R.id.act_join_count)
    EditText actJoinCount;
    @Bind(R.id.act_price)
    EditText actPrice;
    @Bind(R.id.rl_trip_type)
    TextView rlTripType;
    @Bind(R.id.trip_label)
    TagFlowLayout tripLabel;


    private SystemTripBean.ResultBean.ListBean listBean;
    private NewTrip newTrip;

    private List<String> tripLabelList = new ArrayList<>();
    private TagAdapter<String> tripLabelAdapter;
    private String label = "";
    private StringBuilder sb;

    private StringBuilder sb1;//自用


    public static void start(Activity context, NewTrip newTrip, int requestCode) {
        Intent intent = new Intent(context, BaseCaseActivity.class);
        intent.putExtra("trip", newTrip);
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("基本情况");
        builder.setRightTvText("保存", new OnClickListener() {
            @Override
            protected void clickOperate() {
                String title = actTitle.getText().toString();
                String count = actJoinCount.getText().toString();
                String actprice = actPrice.getText().toString();
                if (TextUtils.isEmpty(title)) {
                    ToastUtils.showLong("请输入活动标题");
                    return;
                }
                if (rlTripType.getText().equals("未选择")) {
                    ToastUtils.showLong("请选择活动路线");
                    return;
                }

                if (TextUtils.isEmpty(count)) {
                    ToastUtils.showLong("请输入活动参与人数");
                    return;
                }
                if (Integer.parseInt(count) > 200) {
                    ToastUtils.showLong("活动参与人数不能大于200");
                    return;
                }
                if (TextUtils.isEmpty(actprice)) {
                    ToastUtils.showLong("请输入活动价格");
                    return;
                }
                if (Integer.parseInt(actprice) > 99999) {
                    ToastUtils.showLong("活动价格不能大于99999");
                    return;
                }
                if (TextUtils.isEmpty(label)) {
                    ToastUtils.showLong("请选择活动标签");
                    return;
                }


                if (newTrip != null) {
                    newTrip.setTrip_price(actprice);
                    newTrip.setTrip_name(title);
                    newTrip.setParticipants_limit_count(count);
                    newTrip.setTrip_tags(label);
                    newTrip.setTrip_route_type(rlTripType.getText().toString());
                    Intent intent = new Intent();
                    intent.putExtra("trip", newTrip);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }

            }
        });


    }


    private void showDialog() {
        final EditText et = new EditText(this);
        new AlertDialog.Builder(this).setTitle("添加标签")
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (TextUtils.isEmpty(et.getText().toString())) {
                            ToastUtils.showLong("标签名不能为空");
                            return;
                        }

                        if (et.getText().toString().length() > 4) {
                            ToastUtils.showLong("标签名不能大于4个字");
                            return;
                        }
                        if (!tripLabelList.contains(et.getText().toString())) {
                            sb1.append(et.getText().toString());
                            sb1.append(",");
//                            String tvlabel = sb1.substring(0, sb1.length());
                            newTrip.setTvlabel(sb1.toString());

                            tripLabelList.add(et.getText().toString());
                            tripLabelAdapter.notifyDataChanged();
                        } else {
                            ToastUtils.showLong("标签已存在");
                        }

                        if (newTrip.getLabel() != null && newTrip.getLabel().size() > 0) {
                            tripLabelAdapter.setSelectedList(new HashSet<Integer>(newTrip.getLabel()));
                            tripLabelAdapter.notifyDataChanged();
                        }

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_base_case;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        newTrip = (NewTrip) getIntent().getExtras().getSerializable("trip");
        initView(newTrip);
        getTripLabel();

    }

    private void initView(NewTrip newTrip) {
        actTitle.setText(newTrip.getTrip_name());
        if (TextUtils.isEmpty(newTrip.getTrip_route_type())) {
            rlTripType.setText("未选择");
        } else {
            rlTripType.setText(newTrip.getTrip_route_type());
        }

        actJoinCount.setText(newTrip.getParticipants_limit_count());
        actPrice.setText(newTrip.getTrip_price());
        label = newTrip.getTrip_tags();
//
        if (!TextUtils.isEmpty(newTrip.getTvlabel())) {

            sb1 = new StringBuilder(newTrip.getTvlabel());
        } else {
            sb1 = new StringBuilder();
        }


    }


    private void getTripLabel() {
        TripApi tripApi = RetrofitManager.getTestRetrofit().create(TripApi.class);
        FormBody body = new FormBody.Builder().build();
        Call<TripLabel> trip = tripApi.hot_trip_tags(body);
        trip.enqueue(new BaseCallback<TripLabel>() {
            @Override
            public void onResponse(Call<TripLabel> call, Response<TripLabel> response) {
                cancelProgressDlg();
                for (int i = 0; i < response.body().getResult().getList().size(); i++) {
                    tripLabelList.add(response.body().getResult().getList().get(i).getData().getTag_name());
                }


                tripLabelAdapter = new TagAdapter<String>(tripLabelList) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        TextView tv = (TextView) getLayoutInflater().inflate(R.layout.choose_eat_adapter,
                                tripLabel, false);
                        tv.setText(s);
                        return tv;
                    }
                };

                tripLabel.setAdapter(tripLabelAdapter);

                if (!TextUtils.isEmpty(newTrip.getTvlabel())) {
                    //做标签出来
                    String[] arrayStr = new String[]{};// 字符数组
                    List<String> list = new ArrayList<String>();// list
                    arrayStr = newTrip.getTvlabel().split(",");// 字符串转字符数组
                    list = java.util.Arrays.asList(arrayStr);// 字符数组转list
                    for (int i = 0; i < list.size(); i++) {
                        if (!tripLabelList.contains(list.get(i))) {
                            tripLabelList.add(list.get(i));
                            tripLabelAdapter.notifyDataChanged();
                        }
                    }

                }


                if (newTrip.getLabel() != null && newTrip.getLabel().size() > 0) {
                    tripLabelAdapter.setSelectedList(new HashSet<Integer>(newTrip.getLabel()));
                }
                tripLabel.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        return false;
                    }
                });

                tripLabel.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                    @Override
                    public void onSelected(Set<Integer> selectPosSet) {
                        if (selectPosSet.size() == 0) {
                            return;
                        }
                        newTrip.setLabel(new ArrayList<Integer>(selectPosSet));
                        sb = new StringBuilder();
                        Iterator<Integer> it = selectPosSet.iterator();
                        while (it.hasNext()) {
                            sb.append(tripLabelList.get(it.next()));
                            sb.append(",");
                        }

                        label = sb.substring(0, sb.length() - 1);
                    }
                });

            }

            @Override
            public void onFailure(Call<TripLabel> call, Throwable t) {
                super.onFailure(call, t);
                cancelProgressDlg();
                ToastUtils.showLong(R.string.net_error);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == 100) {
            listBean = (SystemTripBean.ResultBean.ListBean) data.getExtras().getSerializable("system_trip");
            if (listBean != null) {
                rlTripType.setText(listBean.getData().getTrip_route_type());
            }
        }
    }

    @OnClick({R.id.rl_date_set})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_date_set:
                SystemTripListActivity.start(BaseCaseActivity.this, 100);
                break;
        }
    }

    @OnClick(R.id.add_label)
    public void onClick() {
        showDialog();
    }
}
