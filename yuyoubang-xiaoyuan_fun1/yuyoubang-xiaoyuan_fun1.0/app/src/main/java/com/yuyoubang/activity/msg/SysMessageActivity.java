package com.yuyoubang.activity.msg;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseNetActivity;
import com.yuyoubang.adapter.SysMsgAdapter;
import com.yuyoubang.bean.FindRecomendBean;
import com.yuyoubang.bean.SysMessage;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.FindApi;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.widget.adapter.interfaces.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;

/**
 * Created by hongchen on 16/12/15.
 */

public class SysMessageActivity extends BaseNetActivity<SysMessage> {

    private SysMsgAdapter mAdapter;
    private String cursor;

    private List<SysMessage.ResultBean.ListBean> mData = new ArrayList<>();
    private SwipeRefreshLayout findFollowSp;
    private FrameLayout null_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        null_comment = getViewById(R.id.null_comment);
        findFollowSp = getViewById(R.id.find_follow_sp);
        RecyclerView findFollowRv = getViewById(R.id.find_follow_rv);
        findFollowSp.setColorSchemeColors(this.getResources().getColor(R.color.color_ff9600));
        findFollowSp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData("");

            }
        });
        //初始化adapter
        mAdapter = new SysMsgAdapter(this, null, true);
        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                getData(mData.get(mData.size() - 1).getId() + "");
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
    }

    @Override
    protected void loadData() {
        getData("");
    }

    @Override
    protected void processData(SysMessage sysMessage) {
        goneLoading();
        if (sysMessage.getResult().getList().size() > 0) {
            PreferenceUtils.setPrefInt(this, "msg_sys", sysMessage.getResult().getList().size());
        }
        if (TextUtils.isEmpty(cursor)) {
            if (sysMessage.getResult() == null || sysMessage.getResult().getList().size() == 0) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                null_comment.setVisibility(View.VISIBLE);
                return;
            }
            mData.clear();
            mData.addAll(sysMessage.getResult().getList());
            mAdapter.setNewData(mData);
            findFollowSp.setRefreshing(false);
            mAdapter.setLoadingView(R.layout.load_more_layout);
            if (sysMessage.getResult().getList() == null || sysMessage.getResult().getList().size() == 0 || sysMessage.getResult().getList().size() < 10) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
        } else {
            if (sysMessage.getResult().getList() == null || sysMessage.getResult().getList().size() == 0) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
            mData.addAll(sysMessage.getResult().getList());
            mAdapter.setLoadMoreData(mData);

        }
    }

    private void getData(final String cursor) {
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
        sysMessageCall.enqueue(this);

        findFollowSp.setRefreshing(false);
        mAdapter.setLoadingView(R.layout.load_more_layout);
    }


    @Override
    protected int getContentResId() {
        return R.layout.act_sys_msg;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("系统消息");
    }
}
