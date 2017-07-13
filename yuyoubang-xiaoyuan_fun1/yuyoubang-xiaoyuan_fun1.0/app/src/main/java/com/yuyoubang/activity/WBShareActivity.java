package com.yuyoubang.activity;

import android.os.Bundle;

import com.umeng.socialize.media.WBShareCallBackActivity;

/**
 * 新浪微博分享回调
 */
public class WBShareActivity extends WBShareCallBackActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
        }catch (Exception e){
            finish();
        }
    }
}
