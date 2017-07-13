package com.yuyoubang.activity.mine.business;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.TimeUtil;
import com.yuyoubang.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hongchen on 17/1/3.
 */

public class BusinessSerachActivity extends BaseActivity {


    private TextView sure_search;
    private EditText editTextMessage;
    private TextView start_time;
    private TextView end_time;
    private int year;
    private int month;
    private int day;
    private Calendar c;
    private String startTime;
    private String endTime;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        initView();
        setListener();
    }

    private void setListener() {
        sure_search.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//查询
                String orderNum = editTextMessage.getText().toString().trim();
                if(TextUtils.isEmpty(orderNum)){
                    ToastUtils.showLong("搜索内容不能为空");
                    return;
                }

                if (!TextUtils.isEmpty(startTime) && !TextUtils.isEmpty(endTime)) {
                    if (Long.valueOf(startTime) > Long.valueOf(endTime)) {
                        ToastUtils.showShort("开始时间不能大于结束时间");
                        return;
                    }
                }
                LaunchOperate.openSerachBusResultActivity(BusinessSerachActivity.this, orderNum, startTime, endTime);
            }
        });

        start_time.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                showDate(start_time, 1);
            }
        });

        end_time.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                showDate(end_time, 2);
            }
        });
    }

    private void showDate(final TextView tv, final int position) {
        DatePickerDialog dd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        try {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                            String dateInString = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
                            Date date = formatter.parse(dateInString);
                            switch (position) {
                                case 1:
                                    startTime = TimeUtil.getDateTime(formatter.format(date));
                                    break;
                                case 2:
                                    endTime = TimeUtil.getDateTime(formatter.format(date));
                                    break;
                            }
                            tv.setText(formatter.format(date));
                        } catch (Exception ex) {

                        }
                    }
                }, year, month, day);
        dd.show();
    }


    private void initView() {
        sure_search = getViewById(R.id.sure_search);
        editTextMessage = getViewById(R.id.editTextMessage);
        start_time = getViewById(R.id.start_time);
        end_time = getViewById(R.id.end_time);
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_business_serach;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("搜索");
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
