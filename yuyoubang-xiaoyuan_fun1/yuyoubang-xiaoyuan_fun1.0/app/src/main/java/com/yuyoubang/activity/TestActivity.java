package com.yuyoubang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseNetActivity;
import com.yuyoubang.app.GlobalParams;
import com.yuyoubang.utils.LogUtils;
import com.yuyoubang.utils.StatusBarUtil;

/**
 * 测试activity
 * Created by hongchen on 16/11/6.
 */

public class TestActivity extends BaseNetActivity<Object> {

    private ImageView iv;
    private Button pay;

    private PayReq req;

    public IWXAPI msgApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goneLoading();
//        setContentView(R.layout.activity_main);
//        TestModel.test();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }

//        StatusBarUtil.setColor(TestActivity.this,getResources().getColor(R.color.transparent),0);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }
        iv = (ImageView) findViewById(R.id.iv_iv);
        pay = (Button) findViewById(R.id.yuanyuan_pay);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPay();
            }
        });
//        StatusBarUtil.setTranslucentForImageView(TestActivity.this,iv);
        StatusBarUtil.setTranslucentForImageViewInFragment(TestActivity.this,iv);
    }

    private void initPay() {
        req = new PayReq();
        IWXAPI msgApi = WXAPIFactory.createWXAPI(this, null);
        msgApi.registerApp(GlobalParams.WEIXIN_APP_KEY);


        cancelProgressDlg();
        req.appId = "wx93f2363ed1fc5411";
        req.partnerId = "1375291902";
        req.prepayId = "wx2016122017012159020824790484286314";
        req.packageValue = "Sign=WXPay";
        req.nonceStr = "IZB1OHN0RLT2RC0G3GKSS05PN";
        req.timeStamp = "1482224481";
        req.sign = "DF1E256021AE04AC03094DFC453DC15E";
        msgApi.sendReq(req);

//        GetPrepayIdTask getPrepayId = new GetPrepayIdTask();
//        getPrepayId.execute();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void loadData() {
//        TestApi testApi = RetrofitManager.getTestRetrofit().create(TestApi.class);
//        Call<Object> testCall = testApi.test();
//        testCall.enqueue(this);
    }

    @Override
    protected void processData(Object o) {
        LogUtils.w(o.toString());
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        mHeaderBuilder.goneToolbar();

    }

    @Override
    protected int getContentResId() {
        return R.layout.test;
    }


    public static void start(Context context) {
        Intent intent = new Intent(context, TestActivity.class);
        context.startActivity(intent);
    }
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        }
//    }
}
