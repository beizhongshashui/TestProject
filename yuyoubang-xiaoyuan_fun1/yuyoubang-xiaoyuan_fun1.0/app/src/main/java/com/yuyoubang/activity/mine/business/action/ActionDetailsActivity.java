package com.yuyoubang.activity.mine.business.action;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseNetActivity;
import com.yuyoubang.adapter.ComDetailsAdapter;
import com.yuyoubang.bean.mine.ActionDetail;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.MineApi;
import com.yuyoubang.utils.DateUtils;
import com.yuyoubang.widget.recycleview.RecyclerListView;

import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;

/**
 * Created by hongchen on 16/12/9.
 */

public class ActionDetailsActivity extends BaseNetActivity<ActionDetail> {

    private String trip_id;
    private TextView action_name;
    private TextView action_start_time;
    private TextView action_sign_time;
    private TextView persons;
    private RecyclerListView lessonListLine;
    private List<ActionDetail.ResultBean.ListBean> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        action_name = getViewById(R.id.action_name);
        action_start_time = getViewById(R.id.action_start_time);
        action_sign_time = getViewById(R.id.action_sign_time);
        persons = getViewById(R.id.persons);
        lessonListLine = getViewById(R.id.lv_lesson_line);
    }

    @Override
    protected void loadData() {
        trip_id = getIntent().getStringExtra("trip_id");
        FormBody formBody = new FormBody.Builder()
                .add("trip_id", trip_id)
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<ActionDetail> actionDetailCall = mineApi.list_by_trip_id(formBody);
        actionDetailCall.enqueue(this);
    }

    @Override
    protected void processData(ActionDetail actionDetail) {
        goneLoading();
        if (actionDetail.getError_code() == 0) {
            userList = actionDetail.getResult().getList();
            action_name.setText(actionDetail.getResult().getTrip().get(0).getData().getTrip_name());
            long start_time = actionDetail.getResult().getTrip().get(0).getData().getStart_time();
            long end_time = actionDetail.getResult().getTrip().get(0).getData().getEnd_time();
            long participate_end_time = actionDetail.getResult().getTrip().get(0).getData().getParticipate_end_time();
            action_start_time.setText("活动日期: " + DateUtils.getDateToMonth(start_time) + "一" + DateUtils.getDateToMonth(end_time));
            action_sign_time.setText("报名截止日期: " + DateUtils.getDateToMonth(participate_end_time));
            int male_count = actionDetail.getResult().getTrip().get(0).getData().getA_trip_male_confirmed_total_count();
            int female_count = actionDetail.getResult().getTrip().get(0).getData().getA_trip_female_confirmed_total_count();
            int male_pay_count = actionDetail.getResult().getTrip().get(0).getData().getA_trip_male_particpate_and_payed_total_count();
            int female_pay_count = actionDetail.getResult().getTrip().get(0).getData().getA_trip_female_particpate_and_payed_total_count();
            persons.setText("已确认: " + (male_count + female_count) + "人, 其中男" + male_count + "人, 女" + female_count + "人。已付款: " + (male_pay_count + female_pay_count) + "人");
            lessonListLine.setAdapter(new ComDetailsAdapter(lessonListLine, this, userList));
        }
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_action_details;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("活动报名详情");
        builder.setBackGround(Color.WHITE);
        builder.setTitleColor(Color.BLACK);
        builder.setLeftOperate(R.mipmap.back_black, new OnClickListener() {
            @Override
            protected void clickOperate() {
                finish();
            }
        });
    }
}
