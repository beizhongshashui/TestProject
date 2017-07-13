package com.yuyoubang.fragment.message;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMGroupManager;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;
import com.yuyoubang.R;
import com.yuyoubang.bean.TeamsMember;
import com.yuyoubang.fragment.base.BaseFragment;
import com.yuyoubang.fragment.message.chat.EaseConversationListFragment;
import com.yuyoubang.utils.LaunchOperate;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hongchen on 16/11/6.
 */

public class MessageFragment extends BaseFragment {


    @Bind(R.id.msg_tablayout)
    TabLayout msgTablayout;
    @Bind(R.id.msg_vp)
    ViewPager msgVp;
    @Bind(R.id.create_group)
    ImageView createGroup;
    @Bind(R.id.add)
    ImageView add;
    private FragmentPagerAdapter adapter;
    private static String[] allmebers = new String[0];

    private EaseConversationListFragment easeConversationListFragmen = EaseConversationListFragment.newInstance();

    public static MessageFragment newInstance() {
        MessageFragment fragment = new MessageFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

    }


    @Override
    protected int getContentResId() {
        return R.layout.frg_message;
    }

    private void initView() {
        adapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return getFragments().get(position);
            }

            @Override
            public int getCount() {
                return getFragments().size();
            }
        };
        msgVp.setAdapter(adapter);
        msgTablayout.setTabMode(TabLayout.MODE_FIXED);
        initTabLine();
    }

    private void initTabLine() {
        msgTablayout.setupWithViewPager(msgVp);
        msgTablayout.getTabAt(0).setText("消息");
        msgTablayout.getTabAt(1).setText("通知");
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(easeConversationListFragmen);
        fragments.add(NoticeFragment.newInstance());
        return fragments;
    }

    /**
     * refresh ui
     */
    public void refresh() {
        if (easeConversationListFragmen != null) {
            easeConversationListFragmen.refresh();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.create_group, R.id.add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.create_group:
                LaunchOperate.openContactsActivity(getActivity(), "1000", new ArrayList<TeamsMember.ResultBean.ListBean>(), "1111");
                break;
            case R.id.add:
                LaunchOperate.openMineTeamsActivity(getActivity());
//                CreateGroup("这是一个测试群","这是一个测试群",allmebers,null);
                break;
        }
    }

    private void CreateGroup(String groupName, String desc, String[] allMembers, String reason) {
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
        option.style = EMGroupManager.EMGroupStyle.EMGroupStylePublicOpenJoin;
        EMClient.getInstance().groupManager().asyncCreateGroup(groupName, desc, allMembers, reason, option, new EMValueCallBack<EMGroup>() {
            @Override
            public void onSuccess(EMGroup emGroup) {
                Log.d("main", "创建成功" + "onSuccess" + emGroup.getGroupId());
                //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                EMMessage message = EMMessage.createTxtSendMessage("这是一条测试消息", emGroup.getGroupId());
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
}
