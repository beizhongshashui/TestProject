package com.yuyoubang.fragment.message;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.bean.GroupApplyList;
import com.yuyoubang.bean.MineVisit;
import com.yuyoubang.bean.Order;
import com.yuyoubang.bean.SysMessage;
import com.yuyoubang.fragment.base.BaseFragment;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.FindApi;
import com.yuyoubang.network.api.HomeApi;
import com.yuyoubang.network.api.MineApi;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.PreferenceUtils;

import java.util.List;

import butterknife.ButterKnife;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 16/11/6.
 */

public class NoticeFragment extends BaseFragment {


    private LinearLayout sys_layout;
    private LinearLayout order_layout;
    private LinearLayout visit_layout;
    private LinearLayout request_layout;
    private String cursor;
    private String orderCursor;
    private String visitCursor;
    private String teamsCursor;
    private TextView sys_content;
    private TextView sys_num;
    private TextView order_num;
    private TextView visit_num;
    private TextView visit_user;
    private TextView teams_num;
    private TextView teams_content;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public static NoticeFragment newInstance() {
        NoticeFragment fragment = new NoticeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        setListener();
    }

    private void setListener() {
        sys_layout.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//系统消息
                LaunchOperate.openSysMsgActivity(getActivity());
            }
        });

        order_layout.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//订单消息
                LaunchOperate.openOrderMsgActivity(getActivity());
            }
        });

        visit_layout.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//最近来访
                LaunchOperate.openVisitMsgActivity(getActivity());
            }
        });

        request_layout.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//小组请求
                LaunchOperate.openTeamsMsgActivity(getActivity());
            }
        });
    }

    private void initView() {
        sys_layout = getViewById(R.id.sys_layout);
        order_layout = getViewById(R.id.order_layout);
        visit_layout = getViewById(R.id.visit_layout);
        request_layout = getViewById(R.id.request_layout);
        sys_content = getViewById(R.id.sys_content);
        sys_num = getViewById(R.id.sys_num);
        order_num = getViewById(R.id.order_num);
        visit_user = getViewById(R.id.visit_user);
        visit_num = getViewById(R.id.visit_num);
        teams_content = getViewById(R.id.teams_content);
        teams_num = getViewById(R.id.teams_num);
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataSysMsg("");
        getDataOrderMsg("");
        getDataVisitMsg("");
        getDataTeamsMsg("");
    }

    private void getDataTeamsMsg(String teamsCursor) {
        final int msg_teams = PreferenceUtils.getPrefInt(getActivity(), "msg_teams", 0);
        this.teamsCursor = teamsCursor;
        HomeApi homeApi = RetrofitManager.getTestRetrofit().create(HomeApi.class);
        FormBody body;
        if (TextUtils.isEmpty(teamsCursor)) {
            body = new FormBody.Builder()
                    .add("uid", PreferenceUtils.getsessionId(getActivity()))
                    .build();
        } else {
            body = new FormBody.Builder()
                    .add("uid", PreferenceUtils.getsessionId(getActivity()))
                    .add("cursor", teamsCursor)
                    .build();
        }
        Call<GroupApplyList> groupApplyListCall = homeApi.group_apply(body);
        groupApplyListCall.enqueue(new Callback<GroupApplyList>() {
            @Override
            public void onResponse(Call<GroupApplyList> call, Response<GroupApplyList> response) {
                if (response.body().getError_code() == 0) {
                    List<GroupApplyList.ResultBean.ListBean> msgTeamsList = response.body().getResult().getList();
                    if (msgTeamsList.size() > 0) {
                        if (msgTeamsList.size() > msg_teams) {
                            teams_num.setVisibility(View.VISIBLE);
                            teams_num.setText((msgTeamsList.size() - msg_teams) + "");
                        } else {
                            teams_num.setVisibility(View.GONE);
                        }
                        if (msgTeamsList.get(0).getUser() != null) {
                            if (!TextUtils.isEmpty(msgTeamsList.get(0).getUser().getUser_data().getUser_name())) {
                                if (msgTeamsList.get(0).getOp_data().getType() == 1) {
                                    teams_content.setText(msgTeamsList.get(0).getUser().getUser_data().getUser_name() + "邀请您加入小组");
                                } else {
                                    teams_content.setText(msgTeamsList.get(0).getUser().getUser_data().getUser_name() + "申请加入小组");
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GroupApplyList> call, Throwable t) {

            }
        });
    }

    private void getDataVisitMsg(String visitCursor) {
        final int msg_visit = PreferenceUtils.getPrefInt(getActivity(), "msg_visit", 0);
        this.visitCursor = visitCursor;
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        FormBody body;
        if (TextUtils.isEmpty(visitCursor)) {
            body = new FormBody.Builder()
                    .add("uid", PreferenceUtils.getsessionId(getActivity()))
                    .build();
        } else {
            body = new FormBody.Builder()
                    .add("uid", PreferenceUtils.getsessionId(getActivity()))
                    .add("cursor", visitCursor)
                    .build();
        }
        Call<MineVisit> mineVisitCall = findApi.mine_visit(body);
        mineVisitCall.enqueue(new Callback<MineVisit>() {
            @Override
            public void onResponse(Call<MineVisit> call, Response<MineVisit> response) {
                if (response.body().getError_code() == 0) {
                    List<MineVisit.ResultBean.ListBean> msgVisitList = response.body().getResult().getList();
                    if (msgVisitList.size() > 0) {
                        if (msgVisitList.size() > msg_visit) {
                            visit_num.setVisibility(View.VISIBLE);
                            visit_num.setText((msgVisitList.size() - msg_visit) + "");
                        } else {
                            visit_num.setVisibility(View.GONE);
                        }
                        if (msgVisitList.get(0).getUser() != null) {
                            if (!TextUtils.isEmpty(msgVisitList.get(0).getUser().getUser_data().getUser_name())) {
                                visit_user.setText(msgVisitList.get(0).getUser().getUser_data().getUser_name() + "访问了你");
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<MineVisit> call, Throwable t) {

            }
        });
    }

    private void getDataOrderMsg(String orderCursor) {
        final int msg_order = PreferenceUtils.getPrefInt(getActivity(), "msg_order", 0);
        this.orderCursor = orderCursor;
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        FormBody body;
        if (TextUtils.isEmpty(orderCursor)) {
            body = new FormBody.Builder()
                    .add("uid", PreferenceUtils.getsessionId(getActivity()))
                    .build();
        } else {
            body = new FormBody.Builder()
                    .add("uid", PreferenceUtils.getsessionId(getActivity()))
                    .add("cursor", orderCursor).build();
        }
        Call<Order> order = mineApi.my_trip_participate_list_all(body);
        order.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.body().getError_code() == 0) {
                    List<Order.ResultBean.ListBean> msgOrderList = response.body().getResult().getList();
                    if (msgOrderList.size() > 0) {
                        if (msgOrderList.size() > msg_order) {
                            order_num.setVisibility(View.VISIBLE);
                            order_num.setText((msgOrderList.size() - msg_order) + "");
                        } else {
                            order_num.setVisibility(View.GONE);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {

            }
        });
    }

    private void getDataSysMsg(final String cursor) {
//        String msg_sys = PreferenceUtils.getPrefString(getActivity(), "msg_sys", "");
        final int msg_sys = PreferenceUtils.getPrefInt(getActivity(), "msg_sys", 0);
        this.cursor = cursor;
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        FormBody body;
        if (TextUtils.isEmpty(cursor)) {
            body = new FormBody.Builder()
                    .build();
        } else {
            body = new FormBody.Builder()
                    .add("cursor", cursor)
                    .build();
        }
        Call<SysMessage> sysMessageCall = findApi.sys_message(body);
        sysMessageCall.enqueue(new Callback<SysMessage>() {
            @Override
            public void onResponse(Call<SysMessage> call, Response<SysMessage> response) {
                if (response.body().getError_code() == 0) {
                    List<SysMessage.ResultBean.ListBean> sysMsgList = response.body().getResult().getList();
                    if (sysMsgList.size() > 0) {
                        if (sysMsgList.size() > msg_sys) {
                            sys_num.setVisibility(View.VISIBLE);
                            sys_num.setText((sysMsgList.size() - msg_sys) + "");
                        } else {
                            sys_num.setVisibility(View.GONE);
                        }
                        /*try {
//                            PreferenceUtils.setPrefString(getActivity(), "msg_sys", sysMsgList.size() + "");
                            PreferenceUtils.setPrefString(getActivity(), "msg_sys", PreferenceUtils.serialize(sysMsgList));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/
                        if (sysMsgList.get(0).getData() != null) {
                            if (!TextUtils.isEmpty(sysMsgList.get(0).getData().getMessage())) {
                                sys_content.setText(sysMsgList.get(0).getData().getMessage());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SysMessage> call, Throwable t) {

            }
        });
    }

    @Override
    protected int getContentResId() {
        return R.layout.frg_notice;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
}
