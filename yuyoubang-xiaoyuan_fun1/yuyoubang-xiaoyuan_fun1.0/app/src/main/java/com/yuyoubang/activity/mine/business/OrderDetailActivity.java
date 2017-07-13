package com.yuyoubang.activity.mine.business;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseNetActivity;
import com.yuyoubang.bean.OrderShow;
import com.yuyoubang.config.UrlConfig;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.BusinessApi;
import com.yuyoubang.utils.DateUtils;
import com.yuyoubang.utils.TimeUtil;
import com.yuyoubang.view.CircleImageView;

import okhttp3.FormBody;
import retrofit2.Call;

/**
 * Created by hongchen on 16/12/13.
 */

public class OrderDetailActivity extends BaseNetActivity<OrderShow> {

    private String trip_id;
    private CircleImageView img_avatar;
    private TextView user_name;
    private TextView buy_state;
    private TextView buy_time;
    private TextView buy_num;
    private TextView trip_name;
    private TextView trip_start_time;
    private TextView last_time;
    private TextView trip_num;
    private TextView contacts;
    private TextView phone;
    private TextView persons;
    private TextView single_price;
    private TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        trip_id = getIntent().getStringExtra("trip_id");
        img_avatar = getViewById(R.id.img_avatar);
        user_name = getViewById(R.id.user_name);
        buy_state = getViewById(R.id.buy_state);
        buy_time = getViewById(R.id.buy_time);
        buy_num = getViewById(R.id.buy_num);
        trip_name = getViewById(R.id.trip_name);
        trip_start_time = getViewById(R.id.trip_start_time);
        last_time = getViewById(R.id.last_time);
        trip_num = getViewById(R.id.trip_num);
        contacts = getViewById(R.id.contacts);
        phone = getViewById(R.id.phone);
        persons = getViewById(R.id.persons);
        single_price = getViewById(R.id.single_price);
        total = getViewById(R.id.total);
    }

    @Override
    protected void loadData() {
        FormBody formBody = new FormBody.Builder()
                .add("order_id", trip_id)
                .build();
        BusinessApi businessApi = RetrofitManager.getTestRetrofit().create(BusinessApi.class);
        Call<OrderShow> orderShowCall = businessApi.order_show(formBody);
        orderShowCall.enqueue(this);
    }

    @Override
    protected void processData(OrderShow orderShow) {
        goneLoading();
        if (orderShow.getError_code() == 0) {
            if (orderShow.getResult().getList().get(0).getUser() != null){
                ImageLoader.getInstance().displayImage(orderShow.getResult().getList().get(0).getUser().getUser_data().getProfile_pic_url(), img_avatar, ImageOption.defaultOptions);
                user_name.setText(orderShow.getResult().getList().get(0).getUser().getUser_data().getUser_name() + "的报名信息");
            }
            if (orderShow.getResult().getList().get(0).getOp_data().getParticipate_state().equals("book")) {
                buy_state.setText("未付款(未确认)");
            }
            if (orderShow.getResult().getList().get(0).getOp_data().getParticipate_state().equals("cancel")) {
                buy_state.setText("已取消");
            }
            if (orderShow.getResult().getList().get(0).getOp_data().getParticipate_state().equals("confirmed")) {
                buy_state.setText("未付款(已确认)");
            }
            if (orderShow.getResult().getList().get(0).getOp_data().getParticipate_state().equals("refund_complaint")) {
                buy_state.setText("被投诉");
            }
            if (orderShow.getResult().getList().get(0).getOp_data().getParticipate_state().equals("finished")) {
                buy_state.setText("已完成");
            }
            if (orderShow.getResult().getList().get(0).getOp_data().getParticipate_state().equals("payed")) {
                buy_state.setText("已付款");
            }
            String time = TimeUtil.getLongString(orderShow.getResult().getList().get(0).getCreated_at());
            buy_time.setText(time);
            buy_num.setText(orderShow.getResult().getList().get(0).getId() + "");
            trip_name.setText(orderShow.getResult().getList().get(0).getOp_data().getTrip_name());
            long start_time = orderShow.getResult().getList().get(0).getTrip().getData().getStart_time();
            long end_time = orderShow.getResult().getList().get(0).getTrip().getData().getEnd_time();
            trip_start_time.setText(DateUtils.getDateToMonth(start_time) + "一" + DateUtils.getDateToMonth(end_time));
            long participate_end_time = orderShow.getResult().getList().get(0).getTrip().getData().getParticipate_end_time();
            last_time.setText(TimeUtil.getLongString(participate_end_time));
            trip_num.setText(orderShow.getResult().getList().get(0).getOp_data().getTrip_id() + "");
            contacts.setText(orderShow.getResult().getList().get(0).getOp_data().getContact_user_name());
            phone.setText(orderShow.getResult().getList().get(0).getOp_data().getContact_phone());
            int male_count = orderShow.getResult().getList().get(0).getOp_data().getMale_count();
            int female_count = orderShow.getResult().getList().get(0).getOp_data().getFemale_count();
            int totalCount = male_count + female_count;
            persons.setText(totalCount + "人, 男" + male_count + "人, 女" + female_count + "人");
            int total_price = orderShow.getResult().getList().get(0).getOp_data().getTotal_price();
            single_price.setText(orderShow.getResult().getList().get(0).getTrip().getData().getTrip_price() + "/人");
            total.setText(total_price + "元");
        }
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_order_detail;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("订单详情");
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
