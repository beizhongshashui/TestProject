package com.yuyoubang.activity.mine.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.squareup.otto.Subscribe;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.app.GlobalParams;
import com.yuyoubang.bean.OrderPayed;
import com.yuyoubang.bean.PayWx;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.MineApi;
import com.yuyoubang.otto.AppBus;
import com.yuyoubang.otto.PayEvent;
import com.yuyoubang.utils.CallUtil;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.QLog;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.utils.WeixinPayUtils;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 订单支付
 * Created by chenhong on 16/12/13.
 */
public class OrderPayActivity extends BaseActivity {

    private TextView tvCallMobile;
    private TextView tvProduceName;
    private TextView tvPrice;
    private TextView tvPayPrice;
    private TextView tvSubmit;
    private CheckBox cbWx;
    private CheckBox cbZfb;
    private String produceName;
    private String producePrice;

    private IWXAPI msgApi;
    private String orderId;
    private String biz_uid;
    private String trip_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppBus.getInstance().register(this);
        initWeiXin();
        initView();
        setListener();
    }

    private void initWeiXin() {
        msgApi = WXAPIFactory.createWXAPI(this, null);
        msgApi.registerApp(GlobalParams.WEIXIN_APP_KEY);
    }

    private void setListener() {
        produceName = getIntent().getStringExtra("produceName");
        producePrice = getIntent().getStringExtra("producePrice");
        orderId = getIntent().getStringExtra("trip_participate_id");
        biz_uid = getIntent().getStringExtra("biz_uid");
        trip_id = getIntent().getStringExtra("trip_id");
        tvProduceName.setText(produceName);
        tvPrice.setText("￥" + producePrice);
        tvPayPrice.setText("￥" + producePrice);
        tvCallMobile.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                CallUtil.call(OrderPayActivity.this, "0755-84552622");
            }
        });
        cbWx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cbZfb.setChecked(!isChecked);
            }
        });

        cbZfb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cbWx.setChecked(!isChecked);
            }
        });
        tvSubmit.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//立即支付
                submitOrder();
            }
        });
    }

    private void submitOrder() {
        String backType = cbZfb.isChecked() ? GlobalParams.ZhiFuBao : GlobalParams.WeiXin;
        if (TextUtils.equals(backType, GlobalParams.ZhiFuBao)) {//支付宝
        } else {//微信
            requestPaywx();
        }
    }

    private void requestPaywx() {
        FormBody formBody = new FormBody.Builder()
                .add("order_id", orderId)
                .add("subject", produceName)
                .add("total_fee", producePrice)
                .add("uid", PreferenceUtils.getsessionId(this))
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<PayWx> payWxCall = mineApi.pay_wx(formBody);
        payWxCall.enqueue(new Callback<PayWx>() {
            @Override
            public void onResponse(Call<PayWx> call, Response<PayWx> response) {
                if (response.body().getError_code() == 0) {
                    WeixinPayUtils weixinPayUtils = new WeixinPayUtils();
                    weixinPayUtils.initPay(OrderPayActivity.this, orderId, msgApi, response.body());
                }
            }

            @Override
            public void onFailure(Call<PayWx> call, Throwable t) {
                ToastUtils.showShort("支付失败");
            }
        });
    }

    private void initView() {
        tvCallMobile = getViewById(R.id.tv_call_num);
        tvProduceName = getViewById(R.id.produce_name);
        tvPrice = getViewById(R.id.tv_price);
        tvPayPrice = getViewById(R.id.price);
        tvSubmit = getViewById(R.id.submit);
        cbWx = getViewById(R.id.wx_way_cb);
        cbZfb = getViewById(R.id.zfb_way_cb);
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("订单支付");
       /* builder.setBackGround(Color.WHITE);
        builder.setTitleColor(Color.BLACK);
        builder.setLeftOperate(R.mipmap.back_black, new OnClickListener() {
            @Override
            protected void clickOperate() {
                finish();
            }
        });*/
    }

    @Override
    protected int getContentResId() {
        return R.layout.activity_order_pay;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppBus.getInstance().unregister(this);
    }

    //支付成功回调 // TODO: 16/12/1 支付结果要以服务器为基准
    @Subscribe
    public void pay(PayEvent payEvent) {

//        notifialBusiness();
//        finish();
        QLog.d("qingyuan", "支付成功");
        LaunchOperate.openMineOrderActivity(OrderPayActivity.this);
        finish();
    }

    private void notifialBusiness() {
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        FormBody body = new FormBody.Builder()
                .add("biz_uid", biz_uid)//邦主ID
                .add("trip_id", trip_id)//活动ID
                .add("trip_participate_id", orderId)//订单ID
                .add("uid", PreferenceUtils.getsessionId(this))
                .build();

        Call<OrderPayed> order = mineApi.TRIP_PARTICIPATE_USER_PAYED(body);
        order.enqueue(new Callback<OrderPayed>() {
            @Override
            public void onResponse(Call<OrderPayed> call, Response<OrderPayed> response) {
                finish();
            }

            @Override
            public void onFailure(Call<OrderPayed> call, Throwable t) {

            }
        });
    }
}
