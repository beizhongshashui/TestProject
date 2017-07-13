package com.yuyoubang.network.api;

import com.yuyoubang.adapter.CheckUser;
import com.yuyoubang.app.TripIsOrder;
import com.yuyoubang.bean.ActionInfoBean;
import com.yuyoubang.bean.ApplyGroup;
import com.yuyoubang.bean.BannerPics;
import com.yuyoubang.bean.BaseBean;
import com.yuyoubang.bean.BizCommentValue;
import com.yuyoubang.bean.CommentBean;
import com.yuyoubang.bean.GroupApplyList;
import com.yuyoubang.bean.HotBean;
import com.yuyoubang.bean.Report;
import com.yuyoubang.bean.SearchTrip;
import com.yuyoubang.bean.SearchUser;
import com.yuyoubang.bean.SiftResultBean;
import com.yuyoubang.bean.TripOrderUser;
import com.yuyoubang.config.UrlConfig;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * date: Created xiaoyuan on 16/11/05.
 */
public interface HomeApi {
    //获取热门活动
//    @Headers(HttpClient.CACHE_CONTROL_AGE + HttpClient.CACHE_SHORT)
    @POST(UrlConfig.TRIP_HOT_LIST)
    Call<HotBean> get_hot_trip(@Body FormBody body);

    //获取同城活动
    @POST(UrlConfig.TRIP_CITY_LIST)
    Call<HotBean> get_city_trip(@Body FormBody body);

    //获取活动详情
    @POST(UrlConfig.TRIP_SHOW)
    Call<ActionInfoBean> trip_show(@Body FormBody body);


    //获取活动详情
    @POST(UrlConfig.TRIP_LIST_BY_ROUTE_TYPE)
    Call<HotBean> trip_trpe_show(@Body FormBody body);


    //最近出发
    @POST(UrlConfig.TRIP_LIST_BY_RECENT_START_TIME)
    Call<HotBean> trip_time_show(@Body FormBody body);


    //邦主全部评价
    @POST(UrlConfig.BIZ_USER_COMMENT_LIST_ALL)
    Call<CommentBean> comment_list_all(@Body FormBody body);

    //邦主满意评价
    @POST(UrlConfig.BIZ_USER_COMMENT_LIST_FINE)
    Call<CommentBean> comment_list_fine(@Body FormBody body);

    //邦主一般评价
    @POST(UrlConfig.BIZ_USER_COMMENT_LIST_NORMAL)
    Call<CommentBean> comment_list_normal(@Body FormBody body);

    //邦主不满意
    @POST(UrlConfig.BIZ_USER_COMMENT_LIST_BAD)
    Call<CommentBean> comment_list_bad(@Body FormBody body);

    //邦主满意数值
    @POST(UrlConfig.BIZ_USER_COMMENT_VALUE_SHOW)
    Call<BizCommentValue> biz_user_comment_value_show(@Body FormBody body);


    //邦主满意数值
    @POST(UrlConfig.BANNER_PICS)
    Call<BannerPics> banner_pics(@Body FormBody body);

    //SEARCH_TRIP
    @POST(UrlConfig.SEARCH_TRIP)
    Call<SearchTrip> search_trip(@Body FormBody body);

    //搜索活动
    @POST(UrlConfig.SEARCH_USER)
    Call<SearchUser> search_user(@Body FormBody body);

    //搜索活动
    @POST(UrlConfig.TRIP_FILTER)
    Call<SiftResultBean> trip_filter(@Body FormBody body);

    //用户创建举报
    @POST(UrlConfig.USER_REPORT_CREATE)
    Call<Report> user_report_create(@Body FormBody body);

    //显示活动报名用户
    @POST(UrlConfig.TRIP_ORDER_USER_LIST)
    Call<TripOrderUser> trip_order_user_list(@Body FormBody body);

    //成员查询
    @POST(UrlConfig.CHECK_USER)
    Call<CheckUser> check_user(@Body FormBody body);

    //用户申请入群或群主拉人
    @POST(UrlConfig.APPLY_TO_GROUP)
    Call<ApplyGroup> apply_to_group(@Body FormBody body);

    //群申请/邀请列表
    @POST(UrlConfig.GROUP_APPLY)
    Call<GroupApplyList> group_apply(@Body FormBody body);

    //同意入群/加群
    @POST(UrlConfig.AGREE_APPLY_TO_GROUP)
    Call<ApplyGroup> agree_to_group(@Body FormBody body);

    //拒绝入群/加群
    @POST(UrlConfig.REFUSE_APPLY_GROUP)
    Call<BaseBean> refuse_to_group(@Body FormBody body);

    //用户是否已报名
    @POST(UrlConfig.TRIP_ID_ORDER)
    Call<TripIsOrder> trip_id_order(@Body FormBody body);
}
