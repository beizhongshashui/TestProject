package com.yuyoubang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.animation.Animation;

import com.yuyoubang.R;
import com.yuyoubang.activity.home.HomeActivity;
import com.yuyoubang.activity.login.LoginActivity;
import com.yuyoubang.utils.PreferenceUtils;

/**
 * Created by xiaoyuan on 16/6/12.
 */
public class SplashActivity extends Activity {

    private Animation alphaAnimation;


    public void initUiAndListener() {
//        ButterKnife.bind(this);
//// 生成一个状态栏大小的矩形
//        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
//        int statusBarHeight = getResources().getDimensionPixelSize(resourceId);
//// 绘制一个和状态栏一样高的矩形
//        View statusView = new View(this);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
//        statusView.setLayoutParams(params);
//        statusView.setBackgroundColor(Color.TRANSPARENT);
//// 添加 statusView 到布局中
//        ViewGroup rootView = (ViewGroup) ((ViewGroup) findViewById(R.id.splash_rootview));
//        rootView.addView(statusView, 0);// addView(ViewGroup view, index);
//        rootView.setFitsSystemWindows(true);
//        rootView.setClipToPadding(true);
//
//
//        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.welcome_alpha);
//        alphaAnimation.setDuration(3000);
//        alphaAnimation.setFillEnabled(true); //启动Fill保持
//        alphaAnimation.setFillAfter(true);  //设置动画的最后一帧是保持在View上面
//        rootView.setAnimation(alphaAnimation);
//        alphaAnimation.setAnimationListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        if (!isTaskRoot()) {
            finish();
            return;
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(PreferenceUtils.getsessionId(SplashActivity.this))) {
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 3000);
//        ButterKnife.bind(this);
//        initUiAndListener();
    }

//    @Override
//    public void onAnimationStart(Animation animation) {
//
//    }
//
//    @Override
//    public void onAnimationEnd(Animation animation) {
//        Log.e("SplashActivity",PreferenceUtils.getsessionId(SplashActivity.this)+"");
//
//    }
//
//    @Override
//    public void onAnimationRepeat(Animation animation) {
//
//    }


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
