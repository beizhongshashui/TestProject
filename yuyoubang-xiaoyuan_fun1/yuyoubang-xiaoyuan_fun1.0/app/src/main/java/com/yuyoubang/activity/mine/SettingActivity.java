package com.yuyoubang.activity.mine;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.activity.login.LoginAccountActivity;
import com.yuyoubang.activity.model.MsgPopupWindow;
import com.yuyoubang.app.YuYouBangApp;
import com.yuyoubang.fragment.message.DemoModel;
import com.yuyoubang.listener.IsCloseClickListener;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.utils.ChatUtil;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.utils.VersionUtils;
import com.yuyoubang.view.DataCleanManager;

/**
 * Created by hongchen on 16/12/1.
 */

public class SettingActivity extends BaseActivity {

    private TextView logout;
    private TextView fix_psw;
    private LinearLayout phone;
    private CheckBox tb_setting_push;
    public static final String SETTING_msg = "setting_msg";
    boolean settingMsgInfo;
    private TextView black_user;
    private DemoModel demoModel;
    private TextView tv_size;
    private LinearLayout del_storage;
    private TextView tv_version;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
    }

    private void initView() {
        logout = getViewById(R.id.logout);
        fix_psw = getViewById(R.id.fix_psw);
        phone = getViewById(R.id.phone);
        black_user = getViewById(R.id.black_user);
        tb_setting_push = getViewById(R.id.tb_setting_push);
        tv_size = getViewById(R.id.tv_size);
        del_storage = getViewById(R.id.del_storage);
        tv_version = getViewById(R.id.tv_version);
        settingMsgInfo = PreferenceUtils.getPrefBoolean(this, SETTING_msg, true);
        tb_setting_push.setChecked(settingMsgInfo);
    }

    private void setListener() {
        demoModel = new DemoModel(this);
        logout.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//退出登录
                ChatUtil.loginOut();//退出环信
                YuYouBangApp.logOut();
                qStartActivity(LoginAccountActivity.class);
                finish();
            }
        });

        fix_psw.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//修改密码
                LaunchOperate.openFixPswActivity(SettingActivity.this);
            }
        });

        phone.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//修改手机号
                LaunchOperate.openSetPhoneActivity(SettingActivity.this);
            }
        });

        black_user.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//黑名单
                LaunchOperate.openBlackUserList(SettingActivity.this, PreferenceUtils.getsessionId(SettingActivity.this));
            }
        });

        tb_setting_push.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                PreferenceUtils.setPrefBoolean(SettingActivity.this, SETTING_msg, b);
                if (b) {
                    //消息推送打开
                    demoModel.setSettingMsgNotification(true);
                    demoModel.setSettingMsgSound(true);
                    demoModel.setSettingMsgVibrate(true);
//                    ToastUtils.showShort("消息推送已打开");
                } else {
                    //消息推送关闭
                    isOpenMsg();
//                    ToastUtils.showShort("消息推送已关闭");
                }
            }
        });

        try {
            tv_size.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }

        tv_version.setText(VersionUtils.getVersionName(this));

        del_storage.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//清楚缓存
                showDialogDelStorage();
            }
        });
    }

    private void showDialogDelStorage() {
        new AlertDialog.Builder(this).setCancelable(true)
                .setTitle("确定清除缓存？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        DataCleanManager.clearAllCache(SettingActivity.this);
                        try {
                            tv_size.setText(DataCleanManager.getTotalCacheSize(SettingActivity.this));
                            ToastUtils.showShort("清除成功");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).show();
    }

    public void isOpenMsg() {
        MsgPopupWindow msgPopupWindow = new MsgPopupWindow(this, new IsCloseClickListener() {
            @Override
            public void sure() {
                demoModel.setSettingMsgNotification(false);
                demoModel.setSettingMsgSound(false);
                demoModel.setSettingMsgVibrate(false);
            }

            @Override
            public void notSure() {
                tb_setting_push.setChecked(true);
            }
        });
        msgPopupWindow.showAtLocation(
                tb_setting_push, Gravity.BOTTOM
                        | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_settint;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("设置");
    }
}
