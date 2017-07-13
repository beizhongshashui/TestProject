package com.yuyoubang.activity.mine.business;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseNetActivity;
import com.yuyoubang.adapter.OrderTypeStateAdapter;
import com.yuyoubang.adapter.PopTypeAdapter;
import com.yuyoubang.bean.BizOrder;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.BusinessApi;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.ScreenUtil;
import com.yuyoubang.widget.adapter.interfaces.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;

/**
 * Created by hongchen on 16/12/8.
 */

public class OrderTypeStateActivity extends BaseNetActivity<BizOrder> {

    private PopupWindow popupWindow;
    private View view;
    private ImageView homeArrow;

    private List<String> listPop = new ArrayList<>();
    private List<String> listType = new ArrayList<>();
    private ListView lv_group;
    private int mPosition;
    private int mPosition1;//pop的索引
    private LinearLayout type_layout;
    private TextView tv_type;
    //    private int position;
    private SwipeRefreshLayout findFollowSp;

    private OrderTypeStateAdapter mAdapter;
    private String cursor;

    private List<BizOrder.ResultBean.ListBean> mData = new ArrayList<>();
    private TextView ed_search;
    private String trip_biz_uid;
    private ImageView iv_sel;
    private FrameLayout search_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goneLoading();
        mHeaderBuilder.goneToolbar();
    }

    @Override
    protected void initViews() {
        mPosition = getIntent().getIntExtra("position", -1);
        ImageView left_iv = getViewById(R.id.left_iv);
        homeArrow = getViewById(R.id.home_arrow);
        type_layout = getViewById(R.id.type_layout);
        tv_type = getViewById(R.id.tv_type);
        ed_search = getViewById(R.id.ed_search);
        iv_sel = getViewById(R.id.iv_sel);
        search_layout = getViewById(R.id.search_layout);

        findFollowSp = getViewById(R.id.find_follow_sp);
        RecyclerView findFollowRv = getViewById(R.id.find_follow_rv);


        if (mPosition == 0 || mPosition == 1 || mPosition == 2 || mPosition == 3) {
            homeArrow.setVisibility(View.GONE);
        } else {
            homeArrow.setVisibility(View.VISIBLE);
        }
        switch (mPosition) {
            case 0:
                listPop.add("已报名未确认");
                break;
            case 1:
                listPop.add("已确认未付款");

                break;
            case 2:
                listPop.add("已付款");

                break;
            case 3:
                listPop.add("已完成");

                break;
            case 4:
                listPop.add("投诉处理中");
                listPop.add("申请处理中");
                listPop.add("退款中");
                break;
            case 5:
                listPop.add("已取消");
                listPop.add("商家拒绝");
                break;
            case 6:
                break;
            case 7:
                break;


        }


        tv_type.setText(listPop.get(0));
        left_iv.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                finish();
            }
        });

        type_layout.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                if (mPosition == 0 || mPosition == 1 || mPosition == 2 || mPosition == 3) {
                    return;
                }
                showPopupWindow(type_layout);
            }
        });

        iv_sel.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//搜索
                LaunchOperate.openBusinessSearchActivity(OrderTypeStateActivity.this);
            }
        });

        search_layout.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//主题搜索
                LaunchOperate.openThemeSearchActivity(OrderTypeStateActivity.this);
            }
        });

        findFollowSp.setColorSchemeColors(this.getResources().getColor(R.color.color_ff9600));
        findFollowSp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                switch (mPosition) {
                    case 0:
                        getData("", listType.get(0));
                        break;
                    case 1:
                        getData("", listType.get(1));
                        break;
                    case 2:
                        getData("", listType.get(2));
                        break;
                    case 3:
                        getData("", listType.get(3));
                        break;
                    /*case 4:
                        getData("", listType.get(4));
                        break;
                    case 5:
                        getData("", listType.get(5));
                        break;*/
                    case 4:
                        if(mPosition1==0){
                            getData("", listType.get(4));
                        }else if(mPosition1==1){
                            getData("", listType.get(5));
                        }else if(mPosition1==2){
                            getData("", listType.get(6));
                        }

                        break;
                    case 5:
                        if(mPosition1==0){
                            getData("", listType.get(7));
                        }else if(mPosition1==1){
                            getData("", listType.get(8));
                        }

                        break;
                    case 6:
                        getData("", "");
                        break;
                }

//                getData("");

            }
        });
        //初始化adapter
        mAdapter = new OrderTypeStateAdapter(this, null, true);
        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                switch (mPosition) {
                    case 0:
                        getData(mData.get(mData.size() - 1).getId() + "", listType.get(0));
                        break;
                    case 1:
                        getData(mData.get(mData.size() - 1).getId() + "", listType.get(0));
                        break;
                    case 2:
                        getData(mData.get(mData.size() - 1).getId() + "", listType.get(0));
                        break;
                    case 3:
                        getData(mData.get(mData.size() - 1).getId() + "", listType.get(0));
                        break;
                    case 4://by  xiaoyuan
                        if(mPosition1==0){
                            getData(mData.get(mData.size() - 1).getId() + "", listType.get(4));
                        }else if(mPosition1==1){
                            getData(mData.get(mData.size() - 1).getId() + "", listType.get(5));
                        }else if(mPosition1==2){
                            getData(mData.get(mData.size() - 1).getId() + "", listType.get(6));
                        }

                        break;
                    case 5://by  xiaoyuan
                        if(mPosition1==0){
                            getData(mData.get(mData.size() - 1).getId() + "", listType.get(7));
                        }else if(mPosition1==1){
                            getData(mData.get(mData.size() - 1).getId() + "", listType.get(8));
                        }
                        break;
                    case 6:
                        getData(mData.get(mData.size() - 1).getId() + "", "");
                        break;
                }

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        findFollowRv.setLayoutManager(layoutManager);
        findFollowRv.setAdapter(mAdapter);
    }

    private void showPopupWindow(View parent) {
        homeArrow.setBackgroundResource(R.mipmap.fragment_home_arrow_focus);
        if (popupWindow == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.pop_layout, null);
            lv_group = (ListView) view.findViewById(R.id.lv_pop);

            PopTypeAdapter adapter = new PopTypeAdapter(this, listPop);
            lv_group.setAdapter(adapter);
            
            popupWindow = new PopupWindow(view);
            popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            popupWindow.setWidth(600);

        }
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        popupWindow.showAsDropDown(parent, ScreenUtil.getScreenWidth(this) / 4, 0);

        lv_group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (popupWindow != null) {
                    popupWindow.dismiss();
                    mPosition1 = position;
                    tv_type.setText(listPop.get(position));
                    switch (mPosition) {
//                        case 0:
//                            getData("", listType.get(0));
//                            break;
//                        case 1:
//                            getData("", listType.get(0));
//                            break;
//                        case 2:
//                            getData("", listType.get(0));
//                            break;
//                        case 3:
//                            getData("", listType.get(0));
//                            break;
                        case 4:
                            if(mPosition1==0){
                                getData("", listType.get(4));
                            }else if(mPosition1==1){
                                getData("", listType.get(5));
                            }else if(mPosition1==2){
                                getData("", listType.get(6));
                            }
                            break;
                        case 5:
                            if(mPosition1==0){
                                getData("", listType.get(7));
                            }else if(mPosition1==1){
                                getData("", listType.get(8));
                            }
                            break;
                        case 6:
                            getData("", "");
                            break;
                    }
                }
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                homeArrow.setBackgroundResource(R.mipmap.fragment_home_arrow);
            }
        });
    }

    //by  xiaoyuan
    @Override
    protected void loadData() {
        listType.add("book");
        listType.add("confirmed");
        listType.add("payed");
        listType.add("finished");

        listType.add("refund_complaint");
        listType.add("refund_apply");
        listType.add("refund_processing");

        listType.add("cancel");
        listType.add("refused");
        switch (mPosition) {
            case 0:
                getData("", listType.get(0));
                break;
            case 1:
                getData("", listType.get(1));
                break;
            case 2:
                getData("", listType.get(2));
                break;
            case 3:
                getData("", listType.get(3));
                break;
            case 4:
                if(mPosition1==0){
                    getData("", listType.get(4));
                }else if(mPosition1==1){
                    getData("", listType.get(5));
                }else if(mPosition1==2){
                    getData("", listType.get(6));
                }

                break;
            case 5:
                if(mPosition1==0){
                    getData("", listType.get(7));
                }else if(mPosition1==1){
                    getData("", listType.get(8));
                }

                break;
            case 6:
                getData("", "");
                break;
        }
    }

    private void getData(final String cursor, String participate_state) {
        trip_biz_uid = getIntent().getStringExtra("trip_biz_uid");
        this.cursor = cursor;
        BusinessApi businessApi = RetrofitManager.getTestRetrofit().create(BusinessApi.class);
        FormBody body;
        if (!TextUtils.isEmpty(participate_state)) {
            if (TextUtils.isEmpty(cursor)) {
                body = new FormBody.Builder()
                        .add("trip_biz_uid", trip_biz_uid)
                        .add("participate_state", participate_state)
                        .build();
            } else {
                body = new FormBody.Builder()
                        .add("trip_biz_uid", trip_biz_uid)
                        .add("participate_state", participate_state)
                        .add("cursor", cursor)
                        .build();
            }
        } else {
            if (TextUtils.isEmpty(cursor)) {
                body = new FormBody.Builder()
                        .add("trip_biz_uid", trip_biz_uid)
                        .build();
            } else {
                body = new FormBody.Builder()
                        .add("trip_biz_uid", trip_biz_uid)
                        .add("cursor", cursor)
                        .build();
            }
        }
        /*if (TextUtils.isEmpty(cursor)) {
            body = new FormBody.Builder()
                    .add("trip_biz_uid", trip_biz_uid)
                    .add("participate_state", participate_state)
                    .build();
        } else if (!TextUtils.isEmpty(participate_state)) {
            body = new FormBody.Builder()
                    .add("trip_biz_uid", trip_biz_uid)
                    .add("participate_state", participate_state)
                    .add("cursor", cursor)
                    .build();
        } else {
            body = new FormBody.Builder()
                    .add("trip_biz_uid", trip_biz_uid)
                    .add("cursor", cursor)
                    .build();
        }*/
        Call<BizOrder> bizOrderCall = businessApi.biz_order(body);
        bizOrderCall.enqueue(this);

        findFollowSp.setRefreshing(false);
        mAdapter.setLoadingView(R.layout.load_more_layout);
    }

    @Override
    protected void processData(BizOrder bizOrder) {
        goneLoading();
        if (TextUtils.isEmpty(cursor)) {
            /*if (bizOrder.getResult() == null || bizOrder.getResult().getList().size() == 0) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                mAdapter.notifyDataSetChanged();
                return;
            }*/
            mData.clear();
            mData.addAll(bizOrder.getResult().getList());
            mAdapter.setNewData(mData);
            findFollowSp.setRefreshing(false);
            mAdapter.setLoadingView(R.layout.load_more_layout);
            if (bizOrder.getResult().getList() == null || bizOrder.getResult().getList().size() == 0 || bizOrder.getResult().getList().size() < 10) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                mAdapter.notifyDataSetChanged();
                return;
            }
        } else {
            if (bizOrder.getResult().getList() == null || bizOrder.getResult().getList().size() == 0) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                mAdapter.notifyDataSetChanged();
                return;
            }
            mData.addAll(bizOrder.getResult().getList());
            mAdapter.setLoadMoreData(mData);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_order_type_state;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
    }
}
