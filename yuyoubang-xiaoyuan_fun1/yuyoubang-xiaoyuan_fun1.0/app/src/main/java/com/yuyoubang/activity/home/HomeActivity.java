package com.yuyoubang.activity.home;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.activity.login.LoginAccountActivity;
import com.yuyoubang.activity.push.PushActActivity;
import com.yuyoubang.activity.push.PushStateActivity;
import com.yuyoubang.app.AppManager;
import com.yuyoubang.app.YuYouBangApp;
import com.yuyoubang.bean.findfollowinfo.OtherHome;
import com.yuyoubang.bean.mine.UserSureName;
import com.yuyoubang.fragment.find.FindFragment;
import com.yuyoubang.fragment.home.HomeFragment;
import com.yuyoubang.fragment.message.Constant;
import com.yuyoubang.fragment.message.DemoHelper;
import com.yuyoubang.fragment.message.MessageFragment;
import com.yuyoubang.fragment.mine.MineFragment;
import com.yuyoubang.listener.OnLoginEaseListener;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.FindApi;
import com.yuyoubang.network.api.MineApi;
import com.yuyoubang.permission.MPermission;
import com.yuyoubang.permission.OnMPermissionDenied;
import com.yuyoubang.permission.OnMPermissionGranted;
import com.yuyoubang.utils.BuildConfig;
import com.yuyoubang.utils.ChatUtil;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.QLog;
import com.yuyoubang.view.PushPopwin;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 16/11/9.
 */
public class HomeActivity extends BaseActivity {
    private final int BASIC_PERMISSION_REQUEST_CODE = 110;

    public ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 3,
            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
            new ThreadPoolExecutor.DiscardOldestPolicy());
    @Bind(R.id.act_content)
    FrameLayout actContent;
    @Bind(R.id.iv_home)
    ImageView ivHome;

    @Bind(R.id.tv_home)
    TextView tvHome;
    @Bind(R.id.ll_home)
    LinearLayout llHome;
    @Bind(R.id.iv_find)
    ImageView ivFind;
    @Bind(R.id.tv_find)
    TextView tvFind;
    @Bind(R.id.ll_find)
    LinearLayout llFind;
    @Bind(R.id.ll_push)
    LinearLayout llPush;
    @Bind(R.id.iv_msg)
    ImageView ivMsg;
    @Bind(R.id.tv_msg)
    TextView tvMsg;
    @Bind(R.id.ll_msg)
    LinearLayout llMsg;
    @Bind(R.id.iv_mine)
    ImageView ivMine;
    @Bind(R.id.tv_mine)
    TextView tvMine;
    @Bind(R.id.ll_mine)
    LinearLayout llMine;
    @Bind(R.id.unreadlabel)
    TextView unreadlabel;

    private HomeFragment homeFragment;
    private FindFragment findFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;
    private FragmentManager fragmentManager;
    private LocalBroadcastManager broadcastManager;
    private BroadcastReceiver broadcastReceiver;


    private int position;
    private PushPopwin pushPopwin;
    public String city = "";


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 100) {
                updateUnreadLabel();
            }

        }
    };

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        mHeaderBuilder.goneToolbar();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        QLog.d("TAX",PreferenceUtils.getsessionId(this)+"UID");
        fragmentManager = getSupportFragmentManager();

        requestBasicPermission();
        if (!DemoHelper.getInstance().isLoggedIn()) {
            ChatUtil.Rigister(new OnLoginEaseListener() {
                @Override
                public void success() {
                    registerBroadcastReceiver();
                    EMClient.getInstance().chatManager().addMessageListener(messageListener);
                    handler.sendEmptyMessage(100);
                }
            });
        } else {
            registerBroadcastReceiver();
            EMClient.getInstance().groupManager().loadAllGroups();
            EMClient.getInstance().chatManager().loadAllConversations();
        }
        getUserInfo();
    }


    @Override
    protected void onResume() {
        super.onResume();
        setTabSelection(BuildConfig.POSITION);
        updateUnreadLabel();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_home;
    }

    @OnClick({R.id.ll_home, R.id.ll_find, R.id.ll_push, R.id.ll_msg, R.id.ll_mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                setTabSelection(0);
                BuildConfig.POSITION = 0;
                break;
            case R.id.ll_find:
                setTabSelection(1);
                BuildConfig.POSITION = 1;
                break;
            case R.id.ll_push:
                showPushPop();
                break;
            case R.id.ll_msg:
                setTabSelection(2);
                BuildConfig.POSITION = 2;
                break;
            case R.id.ll_mine:
                setTabSelection(3);
                BuildConfig.POSITION = 3;
                break;
        }
    }


    private void requestBasicPermission() {
        MPermission.with(HomeActivity.this)
                .addRequestCode(BASIC_PERMISSION_REQUEST_CODE)
                .permissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE
                )
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @OnMPermissionGranted(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionSuccess() {
    }

    @OnMPermissionDenied(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionFailed() {
        Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
    }


    private void showPushPop() {
        pushPopwin = new PushPopwin(this, clickListener);
        //showAtLocation(View parent, int gravity, int x, int y)
        pushPopwin.showAtLocation(findViewById(R.id.ll_push), Gravity.CENTER, 0, 0);
        pushPopwin.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.push_state:
//                    showAuthentication();
//                    return;
                    PushStateActivity.start(HomeActivity.this);
                    if (pushPopwin != null) {
                        pushPopwin.dismiss();
                    }
                    break;
                case R.id.push_act:
//                    showAuthentication();
//                    return;

                    lookUserApplyState();
                    if (pushPopwin != null) {
                        pushPopwin.dismiss();
                    }
                    break;
            }
        }
    };

    private void lookUserApplyState() {
        FormBody formBody = new FormBody.Builder()
                .add("uid", PreferenceUtils.getsessionId(this))
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<UserSureName> userSureNameCall = mineApi.person_sure_name(formBody);
        userSureNameCall.enqueue(new Callback<UserSureName>() {
            @Override
            public void onResponse(Call<UserSureName> call, Response<UserSureName> response) {
                UserSureName body = response.body();
                if (body != null) {
                    if (body.getResult() != null) {
                        if (body.getResult().getList().size() > 0) {
                            if (body.getResult().getList().get(0).getData().getApply_state() == 0) {
                                showAuthentication();
                            }
                            if (body.getResult().getList().get(0).getData().getApply_state() == 1) {
                                PushActActivity.start(HomeActivity.this);
                            }
                            if (body.getResult().getList().get(0).getData().getApply_state() == 2) {
                                PushActActivity.start(HomeActivity.this);
                            }
                        } else {
                            showAuthentication();
                        }
                    } else {
                        showAuthentication();
                    }
                } else {
                    showAuthentication();
                }
            }

            @Override
            public void onFailure(Call<UserSureName> call, Throwable t) {

            }
        });
    }


    public void showAuthentication() {
        new AlertDialog.Builder(this).setTitle("温馨提示").setMessage("在发布活动之前请先进行实名认证哦").setPositiveButton("立即认证", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LaunchOperate.openUserInfoStatsActivity(HomeActivity.this);

            }
        }).setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }

    private void setTabSelection(int index) {
        this.position = index;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        clearIvSelection();
        clearTvSelection();
        switch (index) {
            case 0:
                ivHome.setImageResource(R.mipmap.main_home_focus);
                tvHome.setTextColor(getResources().getColor(R.color.color_ff9600));


                if (homeFragment == null) {
                    homeFragment = HomeFragment.newInstance();
                    transaction.add(R.id.act_content, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }

                break;
            case 1:
                ivFind.setImageResource(R.mipmap.main_find_focus);
                tvFind.setTextColor(getResources().getColor(R.color.color_ff9600));


                if (findFragment == null) {
                    findFragment = FindFragment.newInstance();
                    transaction.add(R.id.act_content, findFragment);
                } else {
                    transaction.show(findFragment);
                }

                break;
            case 2:
                ivMsg.setImageResource(R.mipmap.main_message_focus);
                tvMsg.setTextColor(getResources().getColor(R.color.color_ff9600));

                if (messageFragment == null) {
                    messageFragment = MessageFragment.newInstance();
                    transaction.add(R.id.act_content, messageFragment);
                } else {
                    transaction.show(messageFragment);
                }
                break;
            case 3:
                ivMine.setImageResource(R.mipmap.main_my_focus);
                tvMine.setTextColor(getResources().getColor(R.color.color_ff9600));
                if (mineFragment == null) {
                    mineFragment = MineFragment.newInstance();
                    transaction.add(R.id.act_content, mineFragment);
                } else {
                    transaction.show(mineFragment);
                }
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (findFragment != null) {
            transaction.hide(findFragment);
        }
        if (messageFragment != null) {
            transaction.hide(messageFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }

    private void clearIvSelection() {
        ivHome.setImageResource(R.mipmap.main_home);
        ivFind.setImageResource(R.mipmap.main_find);
        ivMsg.setImageResource(R.mipmap.main_message);
        ivMine.setImageResource(R.mipmap.main_my);
    }

    private void clearTvSelection() {
        tvHome.setTextColor(getResources().getColor(R.color.color_a8a8a8));
        tvFind.setTextColor(getResources().getColor(R.color.color_a8a8a8));
        tvMsg.setTextColor(getResources().getColor(R.color.color_a8a8a8));
        tvMine.setTextColor(getResources().getColor(R.color.color_a8a8a8));

    }

    @Override
    public void onBackPressed() {
        exitBy2Click();
    }

    private static Boolean isExit = false;

    /**
     * 双击退出函数
     */
    private void exitBy2Click() {
        Timer tExit;
        if (!isExit) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
//            finish();// 调用双击退出函数,此方法不严谨
            AppManager.getAppManager().finishAllActivity();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EMClient.getInstance().chatManager().removeMessageListener(messageListener);
//        ChatUtil.loginOut();//退出环信
    }


    private void registerBroadcastReceiver() {
        broadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION_CONTACT_CHANAGED);
        intentFilter.addAction(Constant.ACTION_GROUP_CHANAGED);
        broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                updateUnreadLabel();
                if (position == 2) {
                    // refresh conversation list
                    if (messageFragment != null) {
                        messageFragment.refresh();
                    }
                }

            }
        };
        broadcastManager.registerReceiver(broadcastReceiver, intentFilter);
    }

    /**
     * update unread message count
     */
    public void updateUnreadLabel() {
        int count = getUnreadMsgCountTotal();
        if (count > 0) {
            unreadlabel.setText(String.valueOf(count));
            unreadlabel.setVisibility(View.VISIBLE);
        } else {
            unreadlabel.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * get unread message count
     *
     * @return
     */
    public int getUnreadMsgCountTotal() {
        int unreadMsgCountTotal = 0;
        int chatroomUnreadMsgCount = 0;
        unreadMsgCountTotal = EMClient.getInstance().chatManager().getUnreadMsgsCount();
        for (EMConversation conversation : EMClient.getInstance().chatManager().getAllConversations().values()) {
            if (conversation.getType() == EMConversation.EMConversationType.ChatRoom)
                chatroomUnreadMsgCount = chatroomUnreadMsgCount + conversation.getUnreadMsgCount();
        }
        return unreadMsgCountTotal - chatroomUnreadMsgCount;
    }


    EMMessageListener messageListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            // notify new message
            for (EMMessage message : messages) {
                DemoHelper.getInstance().getNotifier().onNewMsg(message);
            }
            refreshUIWithMessage();
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {

        }

        @Override
        public void onMessageReadAckReceived(List<EMMessage> messages) {
        }

        @Override
        public void onMessageDeliveryAckReceived(List<EMMessage> message) {
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
        }
    };

    private void refreshUIWithMessage() {
        runOnUiThread(new Runnable() {
            public void run() {
                // refresh unread count
                updateUnreadLabel();
                if (position == 2) {
                    // refresh conversation list
                    if (messageFragment != null) {
                        messageFragment.refresh();
                    }
                }
            }
        });
    }

    protected void loadData() {
        FormBody formBody = new FormBody.Builder()
                .add("uid", PreferenceUtils.getsessionId(this))
                .build();
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        final Call<OtherHome> otherHomeCall = findApi.user_info_show(formBody);
        otherHomeCall.enqueue(new Callback<OtherHome>() {
            @Override
            public void onResponse(Call<OtherHome> call, Response<OtherHome> response) {

                if(response.body().getError_code() == 0){
                    QLog.d("TAG","轮询");
                    if(response.body().getResult().getUser().get(0).getUser_data().getState() != 0){
                        ChatUtil.loginOut();//退出环信
                        YuYouBangApp.logOut();
                        AppManager.getAppManager().finishAllActivity();
                        qStartActivity(LoginAccountActivity.class);
                    }
                }

            }

            @Override
            public void onFailure(Call<OtherHome> call, Throwable t) {

            }
        });
    }

    public void getUserInfo() {
        threadPool.execute(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        try {
                            Thread.sleep(10 * 1000);
                        } catch (InterruptedException e) {
                        }
                        if (TextUtils.isEmpty(PreferenceUtils.getsessionId(HomeActivity.this))) {
                            return;
                        }

                        loadData();
                    } catch (Exception e) {
                    }




                }
            }
        });
    }
}
