package com.yuyoubang.activity.msg;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.adapter.TeamMembersAdapter;
import com.yuyoubang.bean.TeamsMember;
import com.yuyoubang.fragment.message.ChatActivity;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.FindApi;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.LogUtils;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hongchen on 16/12/16.
 */

public class TeamsDetailsActivity extends BaseActivity {

    private String group_id;
    private LinearLayout change_name;
    private TextView teams_name;
    private EditText et;
    private EMGroup group;
    public static TeamsDetailsActivity instance;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 100) {
                ToastUtils.showShort("修改成功");
                teams_name.setText(et.getText().toString().trim());
            }

            if (msg.what == 101) {
                ToastUtils.showShort("退群成功");
                finish();
                ChatActivity.instance.finish();
            }

            if (msg.what == 102) {
                ToastUtils.showShort("解散群成功");
                finish();
                ChatActivity.instance.finish();
            }
            if (msg.what == 103) {
                ToastUtils.showShort("删除成功");
                if (!TextUtils.isEmpty(delMember)) {
                    for (int i = 0; i < adapter.getData().size(); i++) {
                        if (adapter.getData().get(i).getId() == (Long.valueOf(delMember))) {
                            adapter.getData().remove(i);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        }
    };
    private TextView del_and_quit;
    private GridView teamInfoGridView;
    private List<String> members = new ArrayList<>();
    private TeamMembersAdapter adapter;
    private String flag;
    private List<TeamsMember.ResultBean.ListBean> list;
    private String delMember;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        initView();
        setListener();
        loadData();
    }

    private void loadData() {
    }

    private void initView() {
        group_id = getIntent().getStringExtra("group_id");
        flag = getIntent().getStringExtra("flag");
        change_name = getViewById(R.id.change_name);
        teams_name = getViewById(R.id.teams_name);
        del_and_quit = getViewById(R.id.del_and_quit);
        teamInfoGridView = getViewById(R.id.team_member_grid);
    }

    private void setListener() {
        onShowProgressDlg();
        new Thread(new Runnable() {
            public void run() {
                try {
                    group = EMClient.getInstance().groupManager().getGroupFromServer(group_id);

                    runOnUiThread(new Runnable() {
                        public void run() {
                            members.addAll(group.getMembers());
                            StringBuffer sb = new StringBuffer();
                            for (int i = 0; i < members.size(); i++) {
                                sb.append(members.get(i)).append(",");
                            }
                            if (TextUtils.isEmpty(sb)) {
                                return;
                            }
                            String memIds = sb.substring(0, sb.length() - 1);
                            //请求接口拿到头像和名字
                            requestMemberMsg(memIds);
                            teams_name.setText(group.getGroupName());

                            if (flag.equals("flag")) {
                                teamInfoGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        if (position == members.size()) {
                                            LaunchOperate.openContactsActivity(TeamsDetailsActivity.this, "1002", list, group_id);
                                        }
                                    }
                                });

                                teamInfoGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                    @Override
                                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                                        if (position != members.size()) {//删除该成员
                                            String owner = group.getOwner();
                                            if (owner.equals(PreferenceUtils.getsessionId(TeamsDetailsActivity.this))) {
                                                delMember = String.valueOf(list.get(position).getId());
                                                if (!delMember.equals(PreferenceUtils.getsessionId(TeamsDetailsActivity.this))) {
                                                    showDelMemberDialog(members.get(position));
                                                } else {
                                                    ToastUtils.showShort("您不能删除自己");
                                                }
                                            }
                                        }
                                        return false;
                                    }
                                });
                            }

                            change_name.setOnClickListener(new OnClickListener() {
                                @Override
                                protected void clickOperate() {//修改名字
                                    String owner = group.getOwner();
                                    if (TextUtils.isEmpty(owner)) {
                                        return;
                                    }
                                    if (owner.equals(PreferenceUtils.getsessionId(TeamsDetailsActivity.this))) {
                                        showDialog();
                                    } else {
                                        ToastUtils.showShort("您没有权限修改群名称");
                                    }
                                }
                            });

                            del_and_quit.setOnClickListener(new OnClickListener() {
                                @Override
                                protected void clickOperate() {//删除讨论组
                                    String owner = group.getOwner();
                                    if (TextUtils.isEmpty(owner)) {
                                        return;
                                    }
                                    if (owner.equals(PreferenceUtils.getsessionId(TeamsDetailsActivity.this))) {
                                        dismissTeam();
                                    } else {
                                        quitTeam();
                                    }
                                }
                            });
                            cancelProgressDlg();
                        }
                    });

                } catch (Exception e) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                        }
                    });
                }
            }
        }).start();
        /*group = EMClient.getInstance().groupManager().getGroup(group_id);
        if (group == null) {
            EMClient.getInstance().groupManager().asyncGetGroupFromServer(group_id, new EMValueCallBack<EMGroup>() {
                @Override
                public void onSuccess(EMGroup emGroup) {
                    members.addAll(emGroup.getMembers());
                }

                @Override
                public void onError(int i, String s) {

                }
            });
        } else {
            members = group.getMembers();
        }*/

//        try {
//            group =EMClient.getInstance().groupManager().getGroupFromServer(group_id);
//            members = group.getMembers();
//        } catch (HyphenateException e) {
//            e.printStackTrace();
//        }

    }

    private void requestMemberMsg(String memIds) {
        onShowProgressDlg();
        FormBody formBody = new FormBody.Builder()
                .add("uid", memIds)
                .build();
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        Call<TeamsMember> teamsMemberCall = findApi.group_list(formBody);
        teamsMemberCall.enqueue(new Callback<TeamsMember>() {
            @Override
            public void onResponse(Call<TeamsMember> call, Response<TeamsMember> response) {
                cancelProgressDlg();
                TeamsMember body = response.body();
                list = body.getResult().getList();
                adapter = new TeamMembersAdapter(TeamsDetailsActivity.this, flag);
                adapter.setData(list);
                teamInfoGridView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<TeamsMember> call, Throwable t) {
                cancelProgressDlg();
            }
        });
    }

    private void showDelMemberDialog(final String member) {
        new AlertDialog.Builder(TeamsDetailsActivity.this).setCancelable(true)
                .setTitle("确定删除该成员？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        EMClient.getInstance().groupManager().asyncRemoveUserFromGroup(group_id, member, new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                handler.sendEmptyMessage(103);
                            }

                            @Override
                            public void onError(int i, String s) {

                            }

                            @Override
                            public void onProgress(int i, String s) {

                            }
                        });
                    }
                }).show();
    }

    /**
     * 非群主退出群
     */
    private void quitTeam() {

        new AlertDialog.Builder(TeamsDetailsActivity.this).setCancelable(true)
                .setTitle("删除并退出？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        EMClient.getInstance().groupManager().asyncLeaveGroup(group_id, new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                handler.sendEmptyMessage(101);
                            }

                            @Override
                            public void onError(int i, String s) {
                                LogUtils.e(i+s);
                            }

                            @Override
                            public void onProgress(int i, String s) {

                            }
                        });
                    }
                }).show();

    }

    /**
     * 群主解散群(直接退出)
     */
    private void dismissTeam() {
        new AlertDialog.Builder(TeamsDetailsActivity.this).setCancelable(true)
                .setTitle("解散该群？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        EMClient.getInstance().groupManager().asyncDestroyGroup(group_id, new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                handler.sendEmptyMessage(102);
                            }

                            @Override
                            public void onError(int i, String s) {

                            }

                            @Override
                            public void onProgress(int i, String s) {

                            }
                        });
                    }
                }).show();


    }

    private void showDialog() {
        et = new EditText(this);
        new AlertDialog.Builder(this).setTitle("修改群组名字")
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = et.getText().toString().trim();
                        if (TextUtils.isEmpty(name)) {
                            ToastUtils.showLong("群组名字不能为空");
                            return;
                        }

                        EMClient.getInstance().groupManager().asyncChangeGroupName(group_id, name, new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                handler.sendEmptyMessage(100);
                            }

                            @Override
                            public void onError(int i, String s) {
                            }

                            @Override
                            public void onProgress(int i, String s) {

                            }
                        });
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_teams_details;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("讨论组资料");
    }
}
