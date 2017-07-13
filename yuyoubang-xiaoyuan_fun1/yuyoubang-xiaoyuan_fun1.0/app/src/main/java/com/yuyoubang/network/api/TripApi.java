package com.yuyoubang.network.api;

import com.yuyoubang.bean.BaseBean;
import com.yuyoubang.bean.NewStatus;
import com.yuyoubang.bean.SystemTripBean;
import com.yuyoubang.bean.Trip;
import com.yuyoubang.bean.TripLabel;
import com.yuyoubang.bean.TripLikeBean;
import com.yuyoubang.bean.TripUnLikeBean;
import com.yuyoubang.config.UrlConfig;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by xiaoyuan on 16/11/21.
 */
public interface TripApi {

    //发布一个新状态
    @POST(UrlConfig.STATUS_NEW)
    Call<NewStatus> status_new(@Body FormBody body);

    //发布一个新活动
    @POST(UrlConfig.TRIP_NEW)
    Call<Trip> trip_new(@Body FormBody body);
    //获取系统推荐活动路线
    @POST(UrlConfig.SYSTEM_TRIP_ROUTE_TYPE_LIST)
    Call<SystemTripBean> system_trip(@Body FormBody body);

    //赞这个活动
    @POST(UrlConfig.TRIP_LIKE)
    Call<TripLikeBean> trip_like(@Body FormBody body);

    //取消赞这个活动
    @POST(UrlConfig.TRIP_UNLIKE)
    Call<TripUnLikeBean> trip_unlike(@Body FormBody body);

    //用户报名这个活动
    @POST(UrlConfig.TRIP_PARTICIPATE_BOOK)
    Call<BaseBean> trip_participate_book(@Body FormBody body);

    //用户报名这个活动
    @POST(UrlConfig.TRIP_PARTICIPATE_BOOK)
    Call<Object> trip_participate_book1(@Body FormBody body);


    //获取标签
    @POST(UrlConfig.SYSTEM_HOT_USER_TAGS_LIST)
    Call<TripLabel> system_hot_user_tags_list(@Body FormBody body);

    //获取热门活动标签
    @POST(UrlConfig.HOT_TRIP_TAGS)
    Call<TripLabel> hot_trip_tags(@Body FormBody body);

}
