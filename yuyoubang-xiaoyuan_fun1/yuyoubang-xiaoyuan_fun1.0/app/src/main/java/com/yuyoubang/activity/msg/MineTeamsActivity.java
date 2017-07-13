package com.yuyoubang.activity.msg;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.adapter.JoinTeamsAdapter;
import com.yuyoubang.bean.TeamsMember;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.QLog;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.widget.recycleview.RecyclerListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongchen on 16/12/15.
 */

public class MineTeamsActivity extends BaseActivity {
    private RecyclerListView lessonListLine;
    private List<EMGroup> list;
    private TextView more_teams;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 100) {
                lessonListLine.setAdapter(new JoinTeamsAdapter(lessonListLine, MineTeamsActivity.this, list));
            }
            if (msg.what == 101) {
                ToastUtils.showShort("您还没有创建群组");
            }
            if (msg.what == 102) {
                ToastUtils.showShort("网络错误");
            }
        }
    };

    private void loadData() {
        onShowProgressDlg();
        list = new ArrayList<>();
        EMClient.getInstance().groupManager().asyncGetJoinedGroupsFromServer(new EMValueCallBack<List<EMGroup>>() {
            @Override
            public void onSuccess(List<EMGroup> emGroups) {
                cancelProgressDlg();
                if (emGroups.size() == 0 || emGroups == null) {
                    handler.sendEmptyMessage(101);
                } else {
                    list.addAll(emGroups);
                    handler.sendEmptyMessage(100);
                }
            }

            @Override
            public void onError(int i, String s) {
                handler.sendEmptyMessage(102);
                cancelProgressDlg();
                QLog.d("MineTeamsActivity","i="+i);
            }
        });

    }

    private void initView() {
        more_teams = getViewById(R.id.more_teams);
        lessonListLine = getViewById(R.id.lv_lesson_line);
    }

    private void setListener() {
        more_teams.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                LaunchOperate.openPostTeamActivity(MineTeamsActivity.this);
            }
        });
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_mine_team;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("我的小组");
        builder.setBackGround(Color.WHITE);
        builder.setTitleColor(Color.BLACK);
        builder.setLeftOperate(R.mipmap.back_black, new OnClickListener() {
            @Override
            protected void clickOperate() {
                finish();
            }
        });
        builder.setRightTvColor(R.color.color_ff9600);
        builder.setRightTvText("创建小组", new OnClickListener() {
            @Override
            protected void clickOperate() {
                LaunchOperate.openContactsActivity(MineTeamsActivity.this, "1001", new ArrayList<TeamsMember.ResultBean.ListBean>(), "1111");
            }
        });
    }
}
