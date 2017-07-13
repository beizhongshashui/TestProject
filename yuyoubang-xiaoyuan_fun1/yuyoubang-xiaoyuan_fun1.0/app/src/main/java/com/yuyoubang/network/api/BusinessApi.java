package com.yuyoubang.network.api;

import com.yuyoubang.bean.BizBean;
import com.yuyoubang.bean.BizMoney;
import com.yuyoubang.bean.BizOrder;
import com.yuyoubang.bean.BusinessSearch;
import com.yuyoubang.bean.MyTripBean;
import com.yuyoubang.bean.OrderManager;
import com.yuyoubang.bean.OrderShow;
import com.yuyoubang.config.UrlConfig;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by xiaoyuan on 16/11/21.
 */
public interface BusinessApi {
    //根据状态显示活动状态
    @POST(UrlConfig.MY_TRIP_LIST_BY_STATE)
    Call<MyTripBean> my_trip_list_by_state(@Body FormBody body);

    //邦主页面
    @POST(UrlConfig.BIZ_USER_HOME_PAGE_AND_STATISTICS_SHOW)
    Call<BizBean> BIZ_USER_HOME_PAGE_AND_STATISTICS_SHOW(@Body FormBody body);//邦主页面

    //APP端订单列表
    @POST(UrlConfig.BIZ_ORDER)
    Call<BizOrder> biz_order(@Body FormBody body);

    //移动端订单详情
    @POST(UrlConfig.ORDER_SHOW)
    Call<OrderShow> order_show(@Body FormBody body);

    //商家确认用户的预订
    @POST(UrlConfig.BUSSINESS_CONFIRM_BOOK)
    Call<OrderManager> business_confirm_book(@Body FormBody body);

    //商家拒绝用户的报名（取消订单）
    @POST(UrlConfig.BUSSINESS_REFUSE_BOOK)
    Call<OrderManager> business_refuse_book(@Body FormBody body);

    //商家同意退款
    @POST(UrlConfig.BUSSINESS_REFUSE_APPLY)
    Call<OrderManager> business_agree_refund_apply(@Body FormBody body);

    //商家拒绝用户的退款请求
    @POST(UrlConfig.BUSSINESS_DISARGEE_REFUSE_APPLY)
    Call<OrderManager> business_disagree_refund_apply(@Body FormBody body);

    //筛选订单
    @POST(UrlConfig.CHOOSE_ORDER)
    Call<BusinessSearch> choose_order(@Body FormBody body);

    //搜索订单
    @POST(UrlConfig.SEARCH_ORDER)
    Call<BusinessSearch> search_order(@Body FormBody body);

    //商家提现页面详情
    @POST(UrlConfig.BIZ_MINEY)
    Call<BizMoney> biz_money(@Body FormBody body);
}
