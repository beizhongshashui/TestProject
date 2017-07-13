package com.yuyoubang.fragment.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.exceptions.HyphenateException;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.bean.EaseUser;
import com.yuyoubang.bean.TeamsMember;
import com.yuyoubang.bean.findfollowinfo.OtherHome;
import com.yuyoubang.fragment.message.chat.EaseChatFragment;
import com.yuyoubang.fragment.message.chat.EaseConstant;
import com.yuyoubang.fragment.message.chat.EaseUserUtils;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.FindApi;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.ToastUtils;

import java.util.ArrayList;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * chat activity，EaseChatFragment was used {@link #}
 */
public class ChatActivity extends BaseActivity {
    public static final int SINGLE = 1;
    public static final int Group = 2;
    public static ChatActivity instance;

    private int chatType;
    private EMGroup group;
    private String groupId;
    private String toUserName;

    public static void start(Context context, String uses_id, int chattype) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra("userId", uses_id);
        intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, chattype);
        context.startActivity(intent);
    }

    public static ChatActivity activityInstance;
    private EaseChatFragment chatFragment;
    String toChatUsername;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        instance = this;
    }

    @Override
    protected int getContentResId() {
        return R.layout.em_activity_chat;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        chatType = getIntent().getIntExtra(EaseConstant.EXTRA_CHAT_TYPE, -1);
//        builder.setTitle("聊天");
        activityInstance = this;
        //get user id or group id
        toChatUsername = getIntent().getExtras().getString("userId");
        if (chatType == EaseConstant.CHATTYPE_SINGLE) {
            getUserForInfo(toChatUsername, builder);//获取聊天对象的信息
            // set title
//            builder.setTitle(toUserName);
            /*if (EaseUserUtils.getUserInfo(toChatUsername) != null) {
                EaseUser user = EaseUserUtils.getUserInfo(toChatUsername);
                if (user != null) {
                    ToastUtils.showShort(user.getUsername()+user.getNick()+user.getNickname());
                    builder.setTitle(user.getNick());
                }
            }*/
        }
        if (chatType == EaseConstant.CHATTYPE_GROUP) {
            //group chat
            group = EMClient.getInstance().groupManager().getGroup(toChatUsername);
            if (group == null) {
                try {
                    group = EMClient.getInstance().groupManager().getGroupFromServer(toChatUsername);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
            groupId = group.getGroupId();
            builder.setTitle(group.getGroupName());
        }
        //use EaseChatFratFragment
        chatFragment = new ChatFragment();
        //pass parameters to chat fragment
        chatFragment.setArguments(getIntent().getExtras());

        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();

        builder.setRightImgOperate(R.mipmap.msg_create_group, new OnClickListener() {
            @Override
            protected void clickOperate() {
                if (chatType == EaseConstant.CHATTYPE_SINGLE) {//他人主页
                    // set title
                    LaunchOperate.openContactsActivity(ChatActivity.this, "1000", new ArrayList<TeamsMember.ResultBean.ListBean>(), "1111");
//                    LaunchOperate.openOtherHome(ChatActivity.this, toChatUsername);
                }

                if (chatType == EaseConstant.CHATTYPE_GROUP) {
                    //group chat
                    LaunchOperate.openTeamsDetailsActivity(ChatActivity.this, groupId, "flag");
                }

            }
        });
    }

    private void getUserForInfo(String toChatUsername, final HeaderBuilder builder) {
        onShowProgressDlg();
        FormBody formBody = new FormBody.Builder()
                .add("uid", toChatUsername)
                .add("to_uid", toChatUsername)
                .build();
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        Call<OtherHome> otherHomeCall = findApi.user_info_show(formBody);
        otherHomeCall.enqueue(new Callback<OtherHome>() {
            @Override
            public void onResponse(Call<OtherHome> call, Response<OtherHome> response) {
                cancelProgressDlg();
                if (response.body().getError_code() == 0) {
                    if (response.body().getResult().getUser() != null) {
                        if (response.body().getResult().getUser().size() > 0) {
                            toUserName = response.body().getResult().getUser().get(0).getUser_data().getUser_name();
                            builder.setTitle(toUserName);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<OtherHome> call, Throwable t) {
                cancelProgressDlg();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInstance = null;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // make sure only one chat activity is opened
        String username = intent.getStringExtra("userId");
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
//        chatFragment.onBackPressed();
//        if (EasyUtils.isSingleActivity(this)) {
//            Intent intent = new Intent(this, HomeActivity.class);
//            startActivity(intent);
//        }
        finish();
    }

    public String getToChatUsername() {
        return toChatUsername;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
    }
}
