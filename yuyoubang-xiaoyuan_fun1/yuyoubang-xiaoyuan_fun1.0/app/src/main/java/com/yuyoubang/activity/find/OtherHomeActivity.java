package com.yuyoubang.activity.find;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseNetActivity;
import com.yuyoubang.adapter.FindOtherAdapter;
import com.yuyoubang.bean.RemoveUser;
import com.yuyoubang.bean.VisitUser;
import com.yuyoubang.bean.findfollowinfo.FollowUser;
import com.yuyoubang.bean.findfollowinfo.OtherHome;
import com.yuyoubang.fragment.message.ChatActivity;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.FindApi;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.LogUtils;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.interfaces.OnLoadMoreListener;
import com.yuyoubang.widget.adapter.interfaces.OnMultiItemClickListeners;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hongchen on 16/11/30.
 */

public class OtherHomeActivity extends BaseNetActivity<OtherHome> {

    private String uid;
    private SwipeRefreshLayout findFollowSp;
    private FindOtherAdapter mAdapter;
    private List<OtherHome.ResultBean.ListBean> mData = new ArrayList<>();
    private List<OtherHome.ResultBean.ListBean> followList;
    private TextView care;
    private int is_like_to_user;
    private OtherHome otherHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        findFollowSp = getViewById(R.id.find_follow_sp);
        RecyclerView findFollowRv = getViewById(R.id.find_follow_rv);
        care = getViewById(R.id.care);
        TextView send_mes = getViewById(R.id.send_mes);
        findFollowSp.setColorSchemeColors(this.getResources().getColor(R.color.color_ff9600));
        findFollowSp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        //初始化adapter
        mAdapter = new FindOtherAdapter(this, null, true);
        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                if (isReload) {
                    mAdapter.setLoadingView(R.layout.load_more_layout);
                } else {
                    mAdapter.setLoadEndView(R.layout.load_end_layout);
                }
            }
        });
        mAdapter.setOnMultiItemClickListener(new OnMultiItemClickListeners<OtherHome.ResultBean.ListBean>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, OtherHome.ResultBean.ListBean data, int position, int viewType) {
                if(data != null){
                    OtherInfoActivity.start(OtherHomeActivity.this, data);
                }
            }
        });
        /*mAdapter.setOnMultiItemClickListener(new OnMultiItemClickListeners<FindFollowBean.ResultBean.ListBean>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, FindFollowBean.ResultBean.ListBean data, int position, int viewType) {
                if (data != null) {
                    FindInfoActivity.start(this, data);
                }
            }
        });*/
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        findFollowRv.setLayoutManager(layoutManager);
        findFollowRv.setAdapter(mAdapter);

        send_mes.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                ChatActivity.start(OtherHomeActivity.this, uid, ChatActivity.SINGLE);
            }
        });

        care.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                if (is_like_to_user == 0) {
                    followUser();
                } else {
                    cancelCare();
                }
            }
        });

    }

    private void followUser() {
        FormBody formBody = new FormBody.Builder()
                .add("to_uid", uid)
                .add("uid", PreferenceUtils.getsessionId(this))
                .build();
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        Call<FollowUser> followUserCall = findApi.follow_user(formBody);
        followUserCall.enqueue(new Callback<FollowUser>() {
            @Override
            public void onResponse(Call<FollowUser> call, Response<FollowUser> response) {
                if (response.body().getError_code() == 0) {
                    is_like_to_user = 1;
//                    otherHome.getResult().getUser().get(0).getUser_data().setIs_like_to_user(1);
                    care.setText("取消关注");
                }
            }

            @Override
            public void onFailure(Call<FollowUser> call, Throwable t) {

            }
        });
    }

    private void cancelCare() {
        FormBody formBody = new FormBody.Builder()
                .add("to_uid", uid)
                .add("uid", PreferenceUtils.getsessionId(this))
                .build();
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        Call<RemoveUser> followUserCall = findApi.remove_follow_user(formBody);
        followUserCall.enqueue(new Callback<RemoveUser>() {
            @Override
            public void onResponse(Call<RemoveUser> call, Response<RemoveUser> response) {
                RemoveUser body = response.body();
                if (body.getError_code() == 0) {
                    is_like_to_user = 0;
//                    otherHome.getResult().getUser().get(0).getUser_data().setIs_like_to_user(0);
                    care.setText("+ 关注");
                }
            }

            @Override
            public void onFailure(Call<RemoveUser> call, Throwable t) {

            }
        });
    }


    @Override
    protected void loadData() {
        uid = getIntent().getStringExtra("uid");
        FormBody formBody = new FormBody.Builder()
                .add("uid", PreferenceUtils.getsessionId(this))
                .add("to_uid", uid)
                .build();
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        Call<OtherHome> otherHomeCall = findApi.user_info_show(formBody);
        otherHomeCall.enqueue(this);
    }

    @Override
    protected void processData(OtherHome otherHome) {
        this.otherHome = otherHome;
        findFollowSp.setRefreshing(false);
        goneLoading();
        if (otherHome != null) {
            if (otherHome.getResult() != null) {
                if (otherHome.getResult().getUser().size() > 0) {
                    is_like_to_user = otherHome.getResult().getUser().get(0).getIs_like_to_user();
                    if (is_like_to_user == 0) {//未关注
                        care.setText("+ 关注");
                    } else {
                        care.setText("取消关注");
                    }
                    if (!TextUtils.isEmpty(otherHome.getResult().getUser().get(0).getUser_data().getUser_name())) {
                        builder.setTitle(otherHome.getResult().getUser().get(0).getUser_data().getUser_name() + "的主页");
                    }
                    mAdapter.setHeadData(otherHome.getResult().getUser());
                    mAdapter.setNewData(mData);
                }
                if (otherHome.getResult().getList().size() > 0) {
                    followList = otherHome.getResult().getList();
                    if (followList == null || followList.size() == 0) {
                        mAdapter.setLoadEndView(R.layout.load_end_layout);
                        return;
                    }
                    mData.clear();
                    mData.addAll(followList);
                    mAdapter.setNewData(mData);
                }
                /*is_like_to_user = otherHome.getResult().getUser().get(0).getUser_data().getIs_like_to_user();
                if (is_like_to_user == 0) {//未关注
                    care.setText("+ 关注");
                } else {
                    care.setText("取消关注");
                }
                if (!TextUtils.isEmpty(otherHome.getResult().getUser().get(0).getUser_data().getUser_name())) {
                    builder.setTitle(otherHome.getResult().getUser().get(0).getUser_data().getUser_name() + "的主页");
                }
                mAdapter.setHeadData(otherHome.getResult().getUser());
                followList = otherHome.getResult().getList();
                if (followList == null || followList.size() == 0) {
                    mAdapter.setLoadEndView(R.layout.load_end_layout);
                    return;
                }
                mData.clear();
                mData.addAll(followList);
                mAdapter.setNewData(mData);*/
            }
        }

        requestVisitUser();
    }

    private void requestVisitUser() {
        FormBody formBody = new FormBody.Builder()
                .add("to_uid", uid)
                .add("uid", PreferenceUtils.getsessionId(this))
                .build();
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        Call<VisitUser> visitUserCall = findApi.visit_user(formBody);
        visitUserCall.enqueue(new Callback<VisitUser>() {
            @Override
            public void onResponse(Call<VisitUser> call, Response<VisitUser> response) {
                if (response.body().getError_code() == 0) {
                    LogUtils.e("访问成功");
                }
            }

            @Override
            public void onFailure(Call<VisitUser> call, Throwable t) {

            }
        });
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_other_home;
    }

    public HeaderBuilder builder;

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        this.builder = builder;
        builder.setBackGround(Color.WHITE);
        builder.setLeftOperate(R.mipmap.back_black, new OnClickListener() {
            @Override
            protected void clickOperate() {
                finish();
            }
        });
        builder.setTitleColor(Color.BLACK);
        builder.setRightImgOperate(R.mipmap.icon_repost, new OnClickListener() {
            @Override
            protected void clickOperate() {
                LaunchOperate.openFeedbackActivity(OtherHomeActivity.this, 1);
            }
        });
    }
}
