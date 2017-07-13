package com.yuyoubang.utils;

import android.content.Context;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.yuyoubang.app.YuYouBangApp;

/**
 * Created by xiaoyuan on 16/11/22.
 */
public class LocationUtil {
    private static AMapLocationClient mLocationClient = null;
    //声明AMapLocationClient类对象
    private static AMapLocationClientOption mLocationOption = null;
    public static void init(Context context) {
        //初始化定位
        mLocationClient = new AMapLocationClient(context);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
//该方法默认为false。
        mLocationOption.setOnceLocation(true);
//获取最近3s内精度最高的一次定位结果：
//设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);

    }

    public static void startLocation(AMapLocationListener mLocationListener) {
        if (mLocationClient == null) {
            init(YuYouBangApp.getContext());
            //设置定位回调监听
            mLocationClient.setLocationListener(mLocationListener);
            //启动定位
            mLocationClient.startLocation();
        } else {
            //设置定位回调监听
            mLocationClient.setLocationListener(mLocationListener);
            //启动定位
            mLocationClient.startLocation();
        }

    }

    public static void onDestroy(){
        if(mLocationClient != null){
            mLocationClient = null;
        }
    }

    public static void onStop(){
        if(mLocationClient != null){
            mLocationClient.stopLocation();
        }
    }
}
