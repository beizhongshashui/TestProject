package com.yuyoubang.activity.msg;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseNetActivity;
import com.yuyoubang.adapter.VisitMsgAdapter;
import com.yuyoubang.bean.MineVisit;
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

public class VisitMessageActivity extends BaseNetActivity<MineVisit> {

    private VisitMsgAdapter mAdapter;
    private String cursor;

    private List<MineVisit.ResultBean.ListBean> mData = new ArrayList<>();
    private SwipeRefreshLayout findFollowSp;
    private FrameLayout null_comment;
    private TextView empty_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        empty_text = getViewById(R.id.empty_text);
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
        mAdapter = new VisitMsgAdapter(this, null, true);
        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                getData(mData.get(mData.size() - 1).getId() + "");
            }
        });
        /*mAdapter.setOnMultiItemClickListener(new OnMultiItemClickListeners<MineVisit.ResultBean.ListBean>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, MineVisit.ResultBean.ListBean data, int position, int viewType) {
                LaunchOperate.openOtherHome(VisitMessageActivity.this, uid);

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
    protected void processData(MineVisit mineVisit) {
        goneLoading();
        if (mineVisit.getResult().getList().size() > 0) {
            PreferenceUtils.setPrefInt(this, "msg_visit", mineVisit.getResult().getList().size());
        }
        if (TextUtils.isEmpty(cursor)) {
            if (mineVisit.getResult() == null || mineVisit.getResult().getList().size() == 0) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                null_comment.setVisibility(View.VISIBLE);
                empty_text.setText("暂无访客");
                return;
            }
            mData.clear();
            mData.addAll(mineVisit.getResult().getList());
            mAdapter.setNewData(mData);
            findFollowSp.setRefreshing(false);
            mAdapter.setLoadingView(R.layout.load_more_layout);
            if (mineVisit.getResult().getList() == null || mineVisit.getResult().getList().size() == 0 || mineVisit.getResult().getList().size() < 10) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
        } else {
            if (mineVisit.getResult().getList() == null || mineVisit.getResult().getList().size() == 0) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
            mData.addAll(mineVisit.getResult().getList());
            mAdapter.setLoadMoreData(mData);

        }
    }

    private void getData(final String cursor) {
        this.cursor = cursor;
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        FormBody body;
        if (TextUtils.isEmpty(cursor)) {
            body = new FormBody.Builder()
                    .add("uid", PreferenceUtils.getsessionId(this))
                    .build();
        } else {
            body = new FormBody.Builder()
                    .add("uid", PreferenceUtils.getsessionId(this))
                    .add("cursor", cursor)
                    .build();
        }
        Call<MineVisit> mineVisitCall = findApi.mine_visit(body);
        mineVisitCall.enqueue(this);

        findFollowSp.setRefreshing(false);
        mAdapter.setLoadingView(R.layout.load_more_layout);
    }


    @Override
    protected int getContentResId() {
        return R.layout.act_sys_msg;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("最近来访");
    }
}
