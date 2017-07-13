package com.yuyoubang.activity.push;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.utils.LocationUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xiaoyuan on 16/12/15.
 */
public class SignLocationActivity extends BaseActivity {
    @Bind(R.id.tv_location1)
    TextView tvLocation1;
    @Bind(R.id.tv_location2)
    TextView tvLocation2;

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("所在位置");
        LocationUtil.startLocation(mLocationListener);
    }


    @Override
    protected int getContentResId() {
        return R.layout.act_sign_location;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_location1, R.id.tv_location2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_location1:
                Intent intent = new Intent();
                intent.putExtra("location",tvLocation1.getText());
                setResult(Activity.RESULT_OK,intent);
                LocationUtil.onDestroy();
                finish();
                break;
            case R.id.tv_location2:
                Intent intent1 = new Intent();
                intent1.putExtra("location",tvLocation2.getText());
                setResult(Activity.RESULT_OK,intent1);
                LocationUtil.onDestroy();
                finish();

                break;
        }
    }

    AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (!TextUtils.isEmpty(aMapLocation.getCity())) {
                Message message = Message.obtain();
                message.obj = aMapLocation;
                handler.sendMessage(message);
            }


        }
    };

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            AMapLocation aMapLocation = (AMapLocation) msg.obj;
            tvLocation1.setText(aMapLocation.getCity());
            tvLocation2.setText(aMapLocation.getAddress());
        }
    };
}
