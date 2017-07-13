package com.yuyoubang.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.yuyoubang.R;
import com.yuyoubang.activity.msg.TeamsMessageActivity;
import com.yuyoubang.bean.ApplyGroup;
import com.yuyoubang.bean.BaseBean;
import com.yuyoubang.bean.GroupApplyList;
import com.yuyoubang.fragment.message.ChatActivity;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.HomeApi;
import com.yuyoubang.utils.LogUtils;
import com.yuyoubang.utils.TimeUtil;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.base.MultiBaseAdapter;

import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author xiaoyuan
 */

public class TeamsMsgAdapter extends MultiBaseAdapter<GroupApplyList.ResultBean.ListBean> {
    private Context context;

    public TeamsMsgAdapter(Context context, List<GroupApplyList.ResultBean.ListBean> listBean, boolean isOpenLoadMore) {
        super(context, listBean, isOpenLoadMore);
        this.context = context;
    }

    @Override
    protected int getViewType(int position, GroupApplyList.ResultBean.ListBean data) {
        return 1;
    }

    @Override
    protected void convert(ViewHolder holder, final GroupApplyList.ResultBean.ListBean data, int viewType) {
        TextView rmb = holder.getView(R.id.rmb);
        TextView tv_send_msg = holder.getView(R.id.tv_send_msg);
        TextView sure = holder.getView(R.id.sure);
        TextView not_sure = holder.getView(R.id.not_sure);

        if (data.getUser() != null) {
            if (data.getOp_data().getType() == 1){
                rmb.setText(data.getUser().getUser_data().getUser_name() + "邀请您加入【" + data.getOp_data().getGroup_name() + "】讨论组, 是否同意?");
            }else {
                rmb.setText(data.getUser().getUser_data().getUser_name() + "申请加入【" + data.getOp_data().getGroup_name() + "】讨论组, 是否同意?");
            }
        }
        tv_send_msg.setText(TimeUtil.getLongString(data.getCreated_at()));

        sure.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//同意
                /*String[] allmebers = {data.getTo_uid() + ""};
                EMClient.getInstance().groupManager().asyncAddUsersToGroup(data.getOp_data().getGroup_id() + "", allmebers, new EMCallBack() {
                    @Override
                    public void onSuccess() {
//                        handler.sendEmptyMessage(100);
                        joinApplyTeams(String.valueOf(data.getId()), String.valueOf(data.getOp_data().getGroup_id()));
                    }

                    @Override
                    public void onError(int i, String s) {
                        LogUtils.e(s + i);
                    }

                    @Override
                    public void onProgress(int i, String s) {
                        LogUtils.e(s + i);
                    }
                });*/

                EMClient.getInstance().groupManager().asyncJoinGroup(data.getOp_data().getGroup_id() + "", new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        joinApplyTeams(String.valueOf(data.getId()), String.valueOf(data.getOp_data().getGroup_id()));
                    }

                    @Override
                    public void onError(int i, String s) {
                        if (i == 601) {//已经加入过群组
                            handler.sendEmptyMessage(100);
                            ChatActivity.start(mContext, String.valueOf(data.getOp_data().getGroup_id()), ChatActivity.Group);
                            TeamsMessageActivity.instance.finish();
                        }
                        LogUtils.e(s + i);
                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        });

        not_sure.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//拒绝
                refuseApplyTeams(String.valueOf(data.getId()));
            }
        });
    }


    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_teams_msg_item;
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                ToastUtils.showShort("加群成功");
            }
        }
    };

    private void joinApplyTeams(String apply_id, final String group_id) {
        FormBody formBody = new FormBody.Builder()
                .add("apply_id", apply_id)
                .build();
        HomeApi homeApi = RetrofitManager.getTestRetrofit().create(HomeApi.class);
        Call<ApplyGroup> applyGroupCall = homeApi.agree_to_group(formBody);
        applyGroupCall.enqueue(new Callback<ApplyGroup>() {
            @Override
            public void onResponse(Call<ApplyGroup> call, Response<ApplyGroup> response) {
                if (response.body().getError_code() == 0) {
                    if (response.body().getResult().getOp_data().getState() == 1) {//同意(2:拒绝)
                        ToastUtils.showShort("加群成功");
                        ChatActivity.start(mContext, group_id, ChatActivity.Group);
                        TeamsMessageActivity.instance.finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApplyGroup> call, Throwable t) {

            }
        });
    }

    private void refuseApplyTeams(final String apply_id) {
        FormBody formBody = new FormBody.Builder()
                .add("apply_id", apply_id)
                .build();
        HomeApi homeApi = RetrofitManager.getTestRetrofit().create(HomeApi.class);
        Call<BaseBean> applyGroupCall = homeApi.refuse_to_group(formBody);
        applyGroupCall.enqueue(new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                if (response.body().error_code == 0) {
                    ToastUtils.showShort("拒绝成功");
                    for (int i = 0; i < mDatas.size(); i++) {
                        if (mDatas.get(i).getId() == Long.valueOf(apply_id)) {
                            remove(i);
                        }
                    }
                    notifyDataSetChanged();
                    /*if (response.body().getResult().getOp_data().getState() == 2) {//同意(2:拒绝)
                        ToastUtils.showShort("已拒绝");
                        for (int i = 0; i < mDatas.size(); i++) {
                            if (mDatas.get(i).getId() == Long.valueOf(apply_id)) {
                                remove(i);
                            }
                        }
                        notifyDataSetChanged();
                    }*/
                }else {
                    ToastUtils.showShort("网络错误");
                }
            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {
                ToastUtils.showShort("网络错误");
            }
        });
    }

}
