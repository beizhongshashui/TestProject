package com.yuyoubang.activity.push;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.bean.NewTrip;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.utils.DateUtils;
import com.yuyoubang.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xiaoyuan on 16/11/18.
 */
public class SetDateActivity extends BaseActivity {
    @Bind(R.id.rl_act_start)
    RelativeLayout rlActStart;
    @Bind(R.id.rl_act_end)
    RelativeLayout rlActEnd;
    @Bind(R.id.rl_act_apply_end)
    RelativeLayout rlActApplyEnd;
    @Bind(R.id.tv_act_start)
    TextView tvActStart;
    @Bind(R.id.tv_act_apply_end)
    TextView tvActApplyEnd;
    @Bind(R.id.tv_act_end)
    TextView tvActEnd;
    private Calendar c;
    private int year;
    private int month;
    private int day;
    private NewTrip newTrip;


    public static void start(Activity context, NewTrip newTrip, int requestCode) {
        Intent intent = new Intent(context, SetDateActivity.class);
        intent.putExtra("trip", newTrip);
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("日期设置");
        builder.setRightTvText("保存", new OnClickListener() {
            @Override
            protected void clickOperate() {
                if (TextUtils.isEmpty(newTrip.getStart_time())) {

                    ToastUtils.showLong("请选择活动开始时间");
                    return;
                }

                if (TextUtils.isEmpty(newTrip.getEnd_time())) {

                    ToastUtils.showLong("请选择活动结束时间");
                    return;
                }


                if (TextUtils.isEmpty(newTrip.getParticipate_end_time())) {
                    ToastUtils.showLong("请选择活动报名时间");
                    return;
                }

                if (Long.parseLong(newTrip.getStart_time()) > Long.parseLong(newTrip.getEnd_time())) {
                    ToastUtils.showLong("开始时间不能大于结束时间");
                    return;
                }

                if (Long.parseLong(newTrip.getParticipate_end_time()) > Long.parseLong(newTrip.getStart_time())) {
                    ToastUtils.showLong("报名截止时间不能大于开始时间");
                    return;
                }

                if (Long.parseLong(newTrip.getParticipate_end_time()) == Long.parseLong(newTrip.getStart_time())) {
                    ToastUtils.showLong("报名截止时间不能等于开始时间");
                    return;
                }

                if (Long.parseLong(newTrip.getParticipate_end_time()) > Long.parseLong(newTrip.getEnd_time())) {
                    ToastUtils.showLong("报名截止时间不能大于结束时间");
                    return;
                }
                if (Long.parseLong(newTrip.getParticipate_end_time()) > Long.parseLong(newTrip.getStart_time())) {
                    ToastUtils.showLong("报名截止时间不能大于开始时间");
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra("trip", newTrip);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_set_date;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        newTrip = (NewTrip) getIntent().getExtras().getSerializable("trip");
        initView(newTrip);
    }

    @OnClick({R.id.rl_act_start, R.id.rl_act_end, R.id.rl_act_apply_end})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_act_start:
                showDate(tvActStart);
                break;
            case R.id.rl_act_end:
                showDate(tvActEnd);
                break;
            case R.id.rl_act_apply_end:
                showDate(tvActApplyEnd);
                break;
        }
    }

    private void initView(NewTrip newTrip) {
        if (!TextUtils.isEmpty(newTrip.getStart_time())) {

            tvActStart.setText(DateUtils.getDateToString(Long.parseLong(newTrip.getStart_time())));
        }
        if (!TextUtils.isEmpty(newTrip.getEnd_time())) {

            tvActEnd.setText(DateUtils.getDateToString(Long.parseLong(newTrip.getEnd_time())));
        }

        if (!TextUtils.isEmpty(newTrip.getParticipate_end_time())) {

            tvActApplyEnd.setText(DateUtils.getDateToString(Long.parseLong(newTrip.getParticipate_end_time())));
        }

    }

    private void showDate(final TextView tv) {
        DatePickerDialog dd = new DatePickerDialog(SetDateActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        try {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                            String dateInString = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
                            Date date = formatter.parse(dateInString);
                            tv.setText(formatter.format(date).toString());
                            if (tv.getId() == R.id.tv_act_start) {
                                newTrip.setStart_time(date.getTime() + "");
                            } else if (tv.getId() == R.id.tv_act_end) {
                                newTrip.setEnd_time(date.getTime() + "");
                            } else if (tv.getId() == R.id.tv_act_apply_end) {
                                newTrip.setParticipate_end_time(date.getTime() + "");
                            }

                        } catch (Exception ex) {

                        }


                    }
                }, year, month, day);
        dd.show();
    }


}
