package com.yuyoubang.activity.mine.order;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.squareup.otto.Subscribe;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.yuyoubang.R;
import com.yuyoubang.activity.mine.MineOrderActivity;
import com.yuyoubang.app.GlobalParams;
import com.yuyoubang.bean.Order;
import com.yuyoubang.bean.OrderPayed;
import com.yuyoubang.fragment.base.BaseNetFragment;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.MineApi;
import com.yuyoubang.otto.AppBus;
import com.yuyoubang.otto.PayEvent;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.QLog;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.widget.adapter.interfaces.OnLoadMoreListener;
import com.yuyoubang.wxapi.Util;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 16/11/6.
 */

public class AllOrderFragment extends BaseNetFragment<Order> {


    @Bind(R.id.all_order_rv)
    RecyclerView allOrderRv;
    @Bind(R.id.all_order_sp)
    SwipeRefreshLayout allOrderSp;
    private OrderAdapter mAdapter;
    private List<Order.ResultBean.ListBean> data = new ArrayList<>();
    private String cursor;

    private PayReq req;

    public IWXAPI msgApi;
    private String orderID;
    private int mPosition;
    private FrameLayout null_comment;


    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        MineOrderActivity activity1 = (MineOrderActivity) activity;
        msgApi = activity1.msgApi;
    }

    public static AllOrderFragment newInstance() {
        AllOrderFragment fragment = new AllOrderFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        AppBus.getInstance().register(this);
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void loadData() {
//        TestApi testApi = RetrofitManager.getTestRetrofit().create(TestApi.class);
//        Call<Object> testCall = testApi.test();
//        testCall.enqueue(this);
    }


    private void initView() {
        null_comment = getViewById(R.id.null_comment);
        allOrderSp.setColorSchemeColors(getActivity().getResources().getColor(R.color.color_ff9600));
        allOrderSp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData("");

            }
        });
        //初始化adapter
        mAdapter = new OrderAdapter(getActivity(), null, true);
        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                getData(data.get(data.size() - 1).getId() + "");
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        allOrderRv.setLayoutManager(layoutManager);
        allOrderRv.setAdapter(mAdapter);
        getData("");
//
//        mAdapter.setOnMultiItemClickListener(new OnMultiItemClickListeners<Order.ResultBean.ListBean>() {
//            @Override
//            public void onItemClick(ViewHolder viewHolder, Order.ResultBean.ListBean data, int position, int viewType) {
////                QLog.d("qingyuan","帮主ID"+data.getUid()+"");
////                QLog.d("qingyuan","活动ID"+data.getTrip().getId()+""+"");
////                QLog.d("qingyuan","订单ID"+ data.getId()+"");
////                QLog.d("qingyuan","我的ID"+PreferenceUtils.getsessionId(getActivity())+"");
//
////
//                orderID = data.getId() + "";
//                mPosition = position;
//                initPay();
//            }
//        });


    }


    private void initPay() {
        req = new PayReq();
        msgApi.registerApp(GlobalParams.WEIXIN_APP_KEY);


        cancelProgressDlg();
        req.appId = "wx93f2363ed1fc5411";
        req.partnerId = "1375291902";
        req.prepayId = "wx201612201631424e9be0aa120064763917";
        req.packageValue = "Sign=WXPay";
        req.nonceStr = "74ICK7WROBF9AJ677SUTRD8VB";
        req.timeStamp = "1482222702";
        req.sign = "81DD457CE912D530A320F76B8627502A";
        msgApi.sendReq(req);

//        GetPrepayIdTask getPrepayId = new GetPrepayIdTask();
//        getPrepayId.execute();
    }


    /**
     * 获取prepay_id
     */

    private class GetPrepayIdTask extends
            AsyncTask<Void, Void, Map<String, String>> {


        @Override
        protected void onPreExecute() {
            onShowProgressDlg();
        }

        @Override
        protected void onPostExecute(Map<String, String> result) {
            if(!msgApi.isWXAppInstalled()){
                ToastUtils.showShort("未安装微信");
            }
//            cancelProgressDlg();
//            req.appId = GlobalParams.WEIXIN_APP_KEY;
//            req.partnerId = GlobalParams.MCH_ID;
//            req.prepayId = result.get("prepay_id");
//            req.packageValue = "Sign=WXPay";
//            req.nonceStr = Util.genNonceStr();
//            req.timeStamp = String.valueOf(Util.genTimeStamp());
//            List<NameValuePair> signParams = new LinkedList<>();
//            signParams.add(new BasicNameValuePair("appid", req.appId));
//            signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
//            signParams.add(new BasicNameValuePair("package", req.packageValue));
//            signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
//            signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
//            signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));
//            req.sign = Util.genAppSign(signParams);


            cancelProgressDlg();
            req.appId = "wx93f2363ed1fc5411";
            req.partnerId = "1375291902";
            req.prepayId = "wx201612201631424e9be0aa120064763917";
            req.packageValue = "Sign=WXPay";
            req.nonceStr = "74ICK7WROBF9AJ677SUTRD8VB";
            req.timeStamp = "1482222702";
            req.sign = "81DD457CE912D530A320F76B8627502A";
            msgApi.sendReq(req);

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Map<String, String> doInBackground(Void... params) {
            String url = String
                    .format("https://api.mch.weixin.qq.com/pay/unifiedorder");
            String entity = CreateProductArgs();
            byte[] buf = Util.httpPost(url, entity);
            String content = new String(buf);
            Log.e("orion", content);
            Map<String, String> xml = Util.decodeXml(content);
            return xml;
        }
    }







    /**
     * 生成订单信息
     *
     * @return
     */
    private String CreateProductArgs() {
        StringBuffer xml = new StringBuffer();

        try {
            String nonceStr = Util.genNonceStr();

            xml.append("</xml>");
            List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
            packageParams.add(new BasicNameValuePair("appid", GlobalParams.WEIXIN_APP_KEY));
            packageParams.add(new BasicNameValuePair("body", "遇游邦"));
            packageParams.add(new BasicNameValuePair("detail",
                    "测试"));
            packageParams
                    .add(new BasicNameValuePair("mch_id", GlobalParams.MCH_ID));
            packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));
            packageParams.add(new BasicNameValuePair("notify_url",
                    "http://www.api.geekniu.com/pay/callback.json"));//回调地址
            packageParams.add(new BasicNameValuePair("out_trade_no",
                    orderID));
            packageParams.add(new BasicNameValuePair("spbill_create_ip",
                    "127.0.0.1"));
            packageParams.add(new BasicNameValuePair("total_fee", 1 + ""));
            packageParams.add(new BasicNameValuePair("trade_type", "APP"));

            String sign = Util.genPackageSign(packageParams);
            packageParams.add(new BasicNameValuePair("sign", sign));

            String xmlstring = Util.toXml(packageParams);

            return new String(xmlstring.toString().getBytes(), "ISO8859-1");

        } catch (Exception e) {
            return null;
        }


    }

    private void getData(final String cursor) {
        this.cursor = cursor;
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        FormBody body;
        if (TextUtils.isEmpty(cursor)) {
            body = new FormBody.Builder()
                    .add("uid", PreferenceUtils.getsessionId(getActivity()))
                    .build();
        } else {
            body = new FormBody.Builder()
                    .add("uid", PreferenceUtils.getsessionId(getActivity()))
                    .add("cursor", cursor).build();
        }
        Call<Order> order = mineApi.my_trip_participate_list_all(body);
        order.enqueue(this);
    }


    @Override
    protected void processData(Order order) {
        goneLoading();
        if (TextUtils.isEmpty(cursor)) {
            if (order.getResult().getList() == null || order.getResult().getList().size() == 0) {
                null_comment.setVisibility(View.VISIBLE);
            }else {
                null_comment.setVisibility(View.GONE);
            }
            data.clear();
            data.addAll(order.getResult().getList());
            mAdapter.setNewData(data);
            allOrderSp.setRefreshing(false);
            mAdapter.setLoadingView(R.layout.load_more_layout);
            if (order.getResult().getList() == null || order.getResult().getList().size() == 0 || order.getResult().getList().size() < 5) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
        } else {
            if (order.getResult().getList() == null || order.getResult().getList().size() == 0) {
                mAdapter.setLoadEndView(R.layout.load_end_layout);
                return;
            }
            data.clear();
            data.addAll(order.getResult().getList());
            mAdapter.setLoadMoreData(data);

        }

    }


    private void notifialBusiness() {
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        FormBody body = new FormBody.Builder()
                    .add("biz_uid", data.get(mPosition).getUid()+"")//邦主ID
                    .add("trip_id", data.get(mPosition).getTrip().getId()+"")//活动ID
                    .add("trip_participate_id", data.get(mPosition).getId()+"")//订单ID
                    .add("uid", PreferenceUtils.getsessionId(getActivity()))
                    .build();

        Call<OrderPayed> order = mineApi.TRIP_PARTICIPATE_USER_PAYED(body);
        order.enqueue(new Callback<OrderPayed>() {
            @Override
            public void onResponse(Call<OrderPayed> call, Response<OrderPayed> response) {

            }

            @Override
            public void onFailure(Call<OrderPayed> call, Throwable t) {

            }
        });
    }

    @Override
    protected int getContentResId() {
        return R.layout.frg_all_order;
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
        AppBus.getInstance().unregister(this);
    }

    //支付成功回调 // TODO: 16/12/1 支付结果要以服务器为基准
    @Subscribe
    public void pay(PayEvent payEvent) {

//        notifialBusiness();

        QLog.d("qingyuan","支付成");
    }

}
