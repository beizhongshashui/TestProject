package com.yuyoubang.config;

/**
 * date: Created hongchen on 16/11/05.
 */
public class UrlConfig {

    public static String TestHostUrl;
    public static String TestShareUrl;

    public static String TempPic = "http://static.risfond.com/images2/2016/2/232d68c5013245eea8c51bc01373145c.jpg";

    // 0测试环境
    public static final int Test = 0;
    // 1线上环境
    public static final int Online = 1;

    static {
        switch (Online) {
            case Test:
                TestHostUrl = "http://api.yybme.com/";
                TestShareUrl = "http://h5.yybme.com/";
                break;
            case Online:
                TestHostUrl = "http://api.yybme.com/";
                TestShareUrl = "http://h5.yybme.com/";
                break;
        }
    }

    public final static String UTIL_FILE_UPLOAD = "util/file/upload.json";

    //手机号密码登录登录
    public final static String USER_LOGIN_BY_PHONE_PASSWORD = "user/login_by_phone_password.json";
    //获取手机验证码
    public final static String USER_CREATE_BY_PHONE = "user/create_user_by_phone.json";
    //获取验证码
    public final static String USER_GET_VERIFY_CODE = "user/get_verify_code.json";
    //用户密码
    public final static String USER_UPDATE_PASSWORD = "user/update_password.json";
    //用户信息
    public final static String USER_INFO_UPDATE = "user/info/update.json";
    //用户设置密码
    public final static String USER_SET_PASSWORD = "user/set/password.json";

    //首页热门活动
    public final static String TRIP_HOT_LIST = "trip/hot_list.json";
    //同城活动
    public final static String TRIP_CITY_LIST = "trip/same_city_list.json";
    //获取活动详情
    public final static String TRIP_SHOW = "trip/show.json";
    //根据类型获取活动
    public final static String TRIP_LIST_BY_ROUTE_TYPE = "trip/list_by_route_type.json";
    //最近出发
    public final static String TRIP_LIST_BY_RECENT_START_TIME = "trip/list_by_recent_start_time.json";

    //显示邦主全部的评价
    public final static String BIZ_USER_COMMENT_LIST_ALL = "biz_user/comment/list_all.json";
    //显示邦主满意的评价
    public final static String BIZ_USER_COMMENT_LIST_FINE = "biz_user/comment/list_fine.json";
    //显示邦主一般的评价
    public final static String BIZ_USER_COMMENT_LIST_NORMAL = "biz_user/comment/list_normal.json";
    //显示邦主不好的评价
    public final static String BIZ_USER_COMMENT_LIST_BAD = "biz_user/comment/list_bad.json";

    //发布一个新活动
    public final static String TRIP_NEW = "trip/new.json";
    //获取活动类型
    public final static String SYSTEM_TRIP_ROUTE_TYPE_LIST = "system/trip_route_type/list.json";

    //发现页推荐用户
    public final static String USER_RECOMMEND_LIST = "user/recommend/list.json";

    //发现页最新用户动态
    public final static String STATUS_TIMELINE = "status/timeline.json";
    //发现页热门动态
    public final static String STATUS_HOT_LIST = "status/hot_list.json";
    //同城用户动态列表
    public final static String STATUS_SAME_LOC_LIST = "status/same_loc_list.json";
    //用户动态详情
    public final static String STATUS_SHOW = "status/show.json";
    //显示动态评论
    public final static String STATUS_COMMENT_LIST = "status/comment/list.json";
    //发布一个状态
    public final static String STATUS_NEW = "status/new.json";

    //获取赞过这个活动的用户列表
    public final static String TRIP_LIKE_LIST = "trip/like_list.json";
    //获取用户关注用户列表
    public final static String MY_FOLLOW_LIST = "my/follow/list.json";
    //显示用户主页接口
    public final static String USER_INFO_SHOW = "user_info/show.json";


    public final static String STATUS_COMMENT = "status/comment.json";
    //赞动态
    public final static String STATUS_LIKE = "statu/like.json";
    //取消赞动态
    public final static String STATUS_LIKE_REMOVE = "status/like/remove.json";

    //身份认证接口
    public final static String USER_PERSON_VERIFICATION_APPLY = "user/person_verification/apply.json";

    //赞接口
    public final static String TRIP_LIKE = "trip/like.json";

    //取消赞接口
    public final static String TRIP_UNLIKE = "trip/unlike.json";

    //获取邦主评论的评价数值
    public final static String BIZ_USER_COMMENT_VALUE_SHOW = "biz_user/comment/value_show.json";

    //根据状态获取活动列表
    public final static String MY_TRIP_LIST_BY_STATE = "my/trip/list_by_state.json";

    //用户报名活动
    public final static String TRIP_PARTICIPATE_BOOK = "trip/participate/book.json";

    //获取热门城市
    public final static String SYSTEM_HOT_CITIES_LIST = "system/hot_cities/list.json";

    //邦主页面
    public final static String BIZ_USER_HOME_PAGE_AND_STATISTICS_SHOW = "biz_user/home_page_and_statistics/show.json";

    //我所有的订单
    public final static String MY_TRIP_PARTICIPATE_LIST_ALL = "my/trip_participate/list_all.json";

    //我未付款的订单
    public final static String MY_TRIP_PARTICIPATE_LIST_NOT_PAYED = "my/trip_participate/list_not_payed.json";

    //已付款的订单
    public final static String MY_TRIP_PARTICIPATE_LIST_PAYED = "my/trip_participate/list_payed.json";
    //已完成
    public final static String MY_TRIP_PARTICIPATE_LIST_FINISHED = "my/trip_participate/list_finished.json";

    //以取消的订单
    public final static String MY_TRIP_PARTICIPATE_LIST_CANCELLED = "my/trip_participate/list_cancelled.json";
    //用户取消活动报名
    public final static String USER_CANCEL_ORDER = "trip/participate/user_cancel.json";

    //通知商家支付成功回调
    public final static String TRIP_PARTICIPATE_USER_PAYED = "trip/participate/user_payed.json";
    //关注一个用户
    public final static String FOLLOW_USER = "user/follow.json";
    //用户取消关注
    public final static String REMOVE_FOLLOW_USER = "user/follow/remove.json";

    //获取个人标签
    public final static String SYSTEM_HOT_USER_TAGS_LIST = "system/hot_user_tags/list.json";
    //报名活动的用户申请退款（用户申请退款，participate_state=refund_apply）
    public final static String USER_REFUND_APPLY = "trip/participate/user_refund_apply.json";
    //待评价活动报名列表
    public final static String NOT_COMMENT_LIST = "my/participate/not_comment_list.json";
    //已评价的活动报名
    public final static String COMMENT_LIST = "my/participate/has_comment_list.json";
    //用户对一次报名活动进行评价
    public final static String COMMENT_NEW = "my/participate/comment/new.json";
    //用户投诉并且申请退款（必须在活动结束之后，且在投诉期内）
    public final static String USER_REFUND_COMPLAINT = "trip/participate/user_refund_complaint.json";
    //显示活动报名用户
    public final static String TRIP_ORDER_USER_LIST = "trip/order/user_list.json";
    //用户评价详情
    public final static String COMMENT_DETAILS = "user/comment/show.json";
    //删除一条动态
    public final static String REMOVE_STATE = "status/remove.json";
    //首页筛选接口
    public final static String TRIP_FILTER = "trip/filter.json";
    //访问主页
    public final static String VISIT_USER = "user/visit/user.json";
    //我的访客
    public final static String MY_VISIT = "my/visit/list.json";
    //系统消息
    public final static String SYS_MESSAGE = "sys/message/find.json";
    //用户创建举报
    public final static String USER_REPORT_CREATE = "user/report/create.json";
    //成员查询
    public final static String CHECK_USER = "check/user/list.json";
    //用户申请入群或群主拉人
    public final static String APPLY_TO_GROUP = "user/apply/to_group.json";
    //群申请/邀请列表
    public final static String GROUP_APPLY = "group/apply/list.json";
    //同意入群/加群
    public final static String AGREE_APPLY_TO_GROUP = "agree/user/apply_to_group.json";
    //拒绝入群/加群
    public final static String REFUSE_APPLY_GROUP = "refuse/user/apply_to_group.json";
    //获取微信预支付订单信息
    public final static String PAY_WX = "pay/wxpay/prepay.json";
    //修改手机号
    public final static String UPDATE_PHONE = "update/user/phone.json";
    //校验验证码
    public final static String VERIFY_CODE = "user/verify_code/by_phone.json";
    //用户是否已报名
    public final static String TRIP_ID_ORDER = "user/trip/is_order.json";
    //首页分享
    public final static String HOME_SHARE = "portal/index/topic/";
    //动态分享
    public final static String STATUE_SHARE = "portal/index/dynamic/";

//http://101.200.205.189:8011/portal/index/topic/976663249747995/1035836457025557
    //http://101.200.205.189:8011/portal/index/dynamic/1035627782012934/1035836457025557












    /*  -----我的----    */

    //提交用户反馈意见
    public final static String USER_FEEDBACK = "user/feedback/new.json";
    //用户更新密码
    public final static String USER_UPDATE_PSW = "user/update_password.json";
    //APP用户主页，显示我的所有动态
    public final static String MY_STATA_LIST = "my/status_list.json";
    //更新用户信息
    public final static String UPDATE_USER_INFO = "user/info/update.json";
    //显示个人认证情况
    public final static String PERSON_VERIFCATION = "user/person_verification/state.json";
    //申请个人资质认证
    public final static String APPLAY_VERIFICATION = "user/person_verification/apply.json";
    //商家管理后台，下线活动
    public final static String TRIP_OFF_LINE = "biz_admin/trip/off_line.json";
    //申请商家验证
    public final static String APPLY_BUSINESS = "business/verification/apply.json";
    //显示商家用户申请商家认证状态接口
    public final static String APPLY_BUSINESS_STATE = "business/verification/state.json";
    //商家管理后台，下线活动
    public final static String ACTION_OFF_LINE = "biz_admin/trip/off_line.json";
    //删除一个活动（商家操作，只能删除自己创建的活动）
    public final static String DEL_ACTION = "trip/remove.json";
    //获取一个活动的所有（以及所有App所需状态）的报名信息列表
    public final static String LIST_BY_TRIP_ID = "participate/list_by_trip_id.json";
    //获取运营管理后台app顶导轮播图列表
    public final static String BANNER_PICS = "sys_admin/banner/list.json";
    //搜索活动
    public final static String SEARCH_TRIP = "trip/search.json";
    //搜索用户
    public final static String SEARCH_USER = "user/search.json";
    //粉丝列表
    public final static String MY_FANS_LISt = "my/fans/list.json";
    //群成员查询
    public final static String GROUP_LIST = "group/user/list.json";
    //APP端订单列表
    public final static String BIZ_ORDER = "app/biz/order/list.json";
    //移动端订单详情
    public final static String ORDER_SHOW = "app/biz/order/show.json";
    //商家确认用户的预订
    public final static String BUSSINESS_CONFIRM_BOOK = "trip/participate/business_confirm_book.json";
    //商家拒绝用户的报名（取消订单）
    public final static String BUSSINESS_REFUSE_BOOK = "trip/participate/business_refuse_book.json";
    //商家同意退款
    public final static String BUSSINESS_REFUSE_APPLY = "trip/participate/business_agree_refund_apply.json";
    //商家拒绝用户的退款请求
    public final static String BUSSINESS_DISARGEE_REFUSE_APPLY = "trip/participate/business_disagree_refund_apply.json";
    //筛选订单
    public final static String CHOOSE_ORDER = "chose/order/list.json";
    //搜索订单
    public final static String SEARCH_ORDER = "search/order/list.json";
    //筛选动态
    public final static String SERACH_STATUS = "app/search/status.json";
    //商家提现页面详情
    public final static String BIZ_MINEY = "biz_admin/finance/take_cash/show.json";
    //赞动态列表
    public final static String STATUS_LIKE_LIST = "status/like/list.json";
    //推荐活动列表
    public final static String POST_TEAMS = "post/trip/list.json";
    //所有省份列表
    public final static String ALL_LOCATION = "all/location/get.json";
    //通过省份获得城市列表
    public final static String FOUND_CITY = "found/city/list.json";
    //获取热门活动标签
    public final static String HOT_TRIP_TAGS = "system/hot_trip_tags/list.json";

    //撤销退款
    public final static String USER_REVOCATION_APPLY = "user/revocation_apply.json";
    //撤销投诉退款
    public final static String USER_REVOCATION_COMPLAINT = "user/revocation_complaint.json";






}
