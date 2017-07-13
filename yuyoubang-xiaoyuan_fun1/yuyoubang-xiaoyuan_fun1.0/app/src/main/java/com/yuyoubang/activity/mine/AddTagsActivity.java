package com.yuyoubang.activity.mine;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
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
import java.util.List;
import java.util.Set;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by hongchen on 16/12/12.
 */

public class AddTagsActivity extends BaseActivity {

    private TagFlowLayout tripLabel;
    private TagFlowLayout tags;

    private List<String> tripLabelList = new ArrayList<>();
    private TagAdapter<String> tripLabelAdapter;
    private TagAdapter<String> tagsAdapter;
    private TextView add_tags;
    private List<String> userTags;
    final Set<Integer> set = new HashSet<>();
    final Set<Integer> setUserTags = new HashSet<>();
    private EditText et;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
        getTripLabel();
    }

    private void initView() {
        userTags = (List<String>) getIntent().getSerializableExtra("userTags");
        tripLabel = getViewById(R.id.trip_label);
        tags = getViewById(R.id.tags);
        add_tags = getViewById(R.id.add_tags);
    }

    private void setListener() {
        /*userTags.add("旅行");
        userTags.add("个性");*/

        tagsAdapter = new TagAdapter<String>(userTags) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) getLayoutInflater().inflate(R.layout.choose_eat_adapter,
                        tags, false);
                tv.setText(s + " X");
                return tv;
            }
        };

        tags.setAdapter(tagsAdapter);

        tags.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                userTags.remove(position);
                tagsAdapter.notifyDataChanged();
                Set<Integer> list = new HashSet<>();
                for (int i = 0; i < tripLabelList.size(); i++) {
                    for (int j = 0; j < userTags.size(); j++) {
                        if (tripLabelList.get(i).equals(userTags.get(j))) {
                            list.add(i);
                        }
                    }
                }
                tripLabelAdapter.setSelectedList(list);
                tripLabelAdapter.notifyDataChanged();
                return false;
            }
        });

        tags.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                set.clear();
                set.addAll(selectPosSet);
            }
        });

        add_tags.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                showDialog();
            }
        });

        tripLabelAdapter = new TagAdapter<String>(tripLabelList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) getLayoutInflater().inflate(R.layout.choose_content_adapter,
                        tripLabel, false);
                tv.setText(s);
                return tv;
            }
        };

        tripLabel.setAdapter(tripLabelAdapter);

        tripLabel.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                if (userTags.contains(tripLabelList.get(position))) {
                    for (int i = 0; i < userTags.size(); i++) {
                        if (tripLabelList.get(position).equals(userTags.get(i))) {
                            userTags.remove(i);
                        }
                    }
                } else {
                    if (userTags.size() > 2) {
                        ToastUtils.showLong("标签数限选3项");
                    } else {
                        userTags.add(tripLabelList.get(position));
                    }
                    for (int i = 0; i < userTags.size(); i++) {
                        tagsAdapter.setSelectedList(i);
                    }
                }
                tagsAdapter.notifyDataChanged();
                return false;
            }
        });

        tripLabel.setOnSelectListener(new TagFlowLayout.OnSelectListener() {

            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                set.clear();
                set.addAll(selectPosSet);
            }
        });
    }

    private void getTripLabel() {
        onShowProgressDlg();
        TripApi tripApi = RetrofitManager.getTestRetrofit().create(TripApi.class);
        FormBody body = new FormBody.Builder().build();
        Call<TripLabel> trip = tripApi.system_hot_user_tags_list(body);
        trip.enqueue(new BaseCallback<TripLabel>() {
            @Override
            public void onResponse(Call<TripLabel> call, Response<TripLabel> response) {
                cancelProgressDlg();
                for (int i = 0; i < response.body().getResult().getList().size(); i++) {
                    tripLabelList.add(response.body().getResult().getList().get(i).getData().getTag_name());
                }

                for (int i = 0; i < tripLabelList.size(); i++) {
                    for (int j = 0; j < userTags.size(); j++) {
                        if (tripLabelList.get(i).equals(
                                userTags.get(j))) {
                            tripLabelAdapter.setSelectedList(i);
                        }
                        tagsAdapter.setSelectedList(j);
                    }
                }
                tagsAdapter.notifyDataChanged();
                tripLabelAdapter.notifyDataChanged();

            }

            @Override
            public void onFailure(Call<TripLabel> call, Throwable t) {
                super.onFailure(call, t);
                cancelProgressDlg();
                ToastUtils.showLong(R.string.net_error);
            }
        });
    }


    private void showDialog() {
        et = new EditText(this);
        et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)}); //最大输入长度
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
                            ToastUtils.showLong("标签名不能大于4个汉字");
                            return;
                        }

                        if (userTags.size() > 2) {
                            ToastUtils.showLong("标签数限选3项");
                            return;
                        }

                        for (int i = 0; i < tripLabelList.size(); i++) {
                            if (et.getText().toString().trim().equals(tripLabelList.get(i))) {
                                ToastUtils.showShort("标签不能重复添加");
                                return;
                            }
                        }

                        userTags.add(et.getText().toString());
                        tagsAdapter.notifyDataChanged();
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
        return R.layout.act_add_tags;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("添加标签");
        builder.setBackGround(Color.WHITE);
        builder.setTitleColor(Color.BLACK);
        builder.setLeftOperate(R.mipmap.back_black, new OnClickListener() {
            @Override
            protected void clickOperate() {
                finish();
            }
        });
        builder.setRightTvColor(R.color.color_ff9600);
        builder.setRightTvText("保存", new OnClickListener() {
            @Override
            protected void clickOperate() {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < userTags.size(); i++) {
                    sb.append(userTags.get(i)).append(",");
                }
                if (TextUtils.isEmpty(sb)) {
                    ToastUtils.showShort("请选择标签");
                    return;
                }
                String tags = sb.substring(0, sb.length() - 1);
                Intent intent = new Intent();
                intent.putExtra("tags", tags);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
