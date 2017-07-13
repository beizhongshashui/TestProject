package com.yuyoubang.utils;

import android.util.Log;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.EMMessageListener;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMGroupManager;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;
import com.yuyoubang.app.YuYouBangApp;
import com.yuyoubang.fragment.message.DemoHelper;
import com.yuyoubang.listener.OnLoginEaseListener;

import java.util.List;

/**
 * Created by xiaoyuan on 16/11/23.
 */
public class ChatUtil {


    /**
     * 注册环信
     */
    public static void Rigister(final OnLoginEaseListener onLoginEaseListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(PreferenceUtils.getsessionId(YuYouBangApp.getContext()), PreferenceUtils.getsessionId(YuYouBangApp.getContext()));
//                    login();
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    Log.d("main", "注册！" + e.getErrorCode());
                    int errorCode = e.getErrorCode();
                    if (errorCode == EMError.USER_ALREADY_EXIST) {
                        login(onLoginEaseListener);
                    } else if (errorCode == EMError.USER_REG_FAILED) {
                        Rigister(onLoginEaseListener);
                        //注册失败
                    } else if (errorCode == EMError.EM_NO_ERROR) {

                        //无错误
                        login(onLoginEaseListener);
                    } else {
                        Rigister(onLoginEaseListener);
                    }
                }
            }
        }).start();


    }

    /**
     * 登录环信
     */
    public static void login(final OnLoginEaseListener onLoginEaseListener) {
        EMClient.getInstance().login(PreferenceUtils.getsessionId(YuYouBangApp.getContext()),PreferenceUtils.getsessionId(YuYouBangApp.getContext()), new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                Log.d("main", "登录聊天服务器成功！");
                // ** manually load all local groups and conversation
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                if(onLoginEaseListener != null){
                    onLoginEaseListener.success();
                }

//
            }

            @Override
            public void onProgress(int progress, String status) {
            }

            @Override
            public void onError(int code, String message) {
                Log.d("main", "登录聊天服务器失败！" + code);
                if (code == EMError.USER_ALREADY_LOGIN) {
                } else {
                    login(onLoginEaseListener);
                }
            }
        });
    }

    private  void CreateGroup(String groupName, String desc, String[] allMembers, String reason) {
        /**
         * 创建群组
         * @param groupName 群组名称
         * @param desc 群组简介
         * @param allMembers 群组初始成员，如果只有自己传空数组即可
         * @param reason 邀请成员加入的reason
         * @param option 群组类型选项，可以设置群组最大用户数(默认200)及群组类型@see {@link EMGroupStyle}
         * @return 创建好的group
         * @throws HyphenateException
         */
        EMGroupManager.EMGroupOptions option = new EMGroupManager.EMGroupOptions();
        option.maxUsers = 200;
        option.style = EMGroupManager.EMGroupStyle.EMGroupStylePrivateMemberCanInvite;
        EMClient.getInstance().groupManager().asyncCreateGroup(groupName, desc, allMembers, reason, option, new EMValueCallBack<EMGroup>() {
            @Override
            public void onSuccess(EMGroup emGroup) {
                Log.d("main", "创建成功" + "onSuccess" + emGroup.getGroupId());
                //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                EMMessage message = EMMessage.createTxtSendMessage("我的群", emGroup.getGroupId());
//                //如果是群聊，设置chattype，默认是单聊
                message.setChatType(EMMessage.ChatType.GroupChat);
//                //发送消息
                EMClient.getInstance().chatManager().sendMessage(message);
            }

            @Override
            public void onError(int i, String s) {
                Log.d("main", "创建失败" + "onError");

            }
        });
    }

    public static   void CreateGroup(String groupName, String desc, String[] allMembers, String reason,EMValueCallBack emValueCallBack) {
        /**
         * 创建群组
         * @param groupName 群组名称
         * @param desc 群组简介
         * @param allMembers 群组初始成员，如果只有自己传空数组即可
         * @param reason 邀请成员加入的reason
         * @param option 群组类型选项，可以设置群组最大用户数(默认200)及群组类型@see {@link EMGroupStyle}
         * @return 创建好的group
         * @throws HyphenateException
         */
        EMGroupManager.EMGroupOptions option = new EMGroupManager.EMGroupOptions();
        option.maxUsers = 200;
        option.style = EMGroupManager.EMGroupStyle.EMGroupStylePublicOpenJoin;//公开群
        EMClient.getInstance().groupManager().asyncCreateGroup(groupName, desc, allMembers, reason, option, emValueCallBack);
    }


    public static void loginOut() {
        EMClient.getInstance().logout(true);
    }



}
