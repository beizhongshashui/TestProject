package com.yuyoubang.network.api;

import com.yuyoubang.bean.BaseBean;
import com.yuyoubang.bean.CancelOrder;
import com.yuyoubang.bean.CommentDetail;
import com.yuyoubang.bean.Order;
import com.yuyoubang.bean.OrderPayed;
import com.yuyoubang.bean.PayWx;
import com.yuyoubang.bean.RemoveState;
import com.yuyoubang.bean.mine.ActionDetail;
import com.yuyoubang.bean.mine.ActionOffLine;
import com.yuyoubang.bean.mine.ApplyCommit;
import com.yuyoubang.bean.mine.ApplyCompany;
import com.yuyoubang.bean.mine.ApplyCompanyState;
import com.yuyoubang.bean.mine.Comment;
import com.yuyoubang.bean.mine.CommentNew;
import com.yuyoubang.bean.mine.DelAction;
import com.yuyoubang.bean.mine.Feedback;
import com.yuyoubang.bean.mine.FixPsw;
import com.yuyoubang.bean.mine.MineDoTan;
import com.yuyoubang.bean.mine.NoCommend;
import com.yuyoubang.bean.mine.UpdateUserInfo;
import com.yuyoubang.bean.mine.UserSureName;
import com.yuyoubang.config.UrlConfig;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by hongchen on 16/11/30.
 */

public interface MineApi {
    //获取推荐用户列表
    @POST(UrlConfig.USER_FEEDBACK)
    Call<Feedback> user_feedback(@Body FormBody body);

    //用户更新密码
    @POST(UrlConfig.USER_UPDATE_PSW)
    Call<FixPsw> user_fix_psw(@Body FormBody body);

    //APP用户主页，显示我的所有动态
    @POST(UrlConfig.MY_STATA_LIST)
    Call<MineDoTan> my_stats_list(@Body FormBody body);

    //更新用户信息
    @POST(UrlConfig.UPDATE_USER_INFO)
    Call<UpdateUserInfo> update_user_info(@Body FormBody body);

    //显示个人认证情况
    @POST(UrlConfig.PERSON_VERIFCATION)
    Call<UserSureName> person_sure_name(@Body FormBody body);

    //申请个人资质认证
    @POST(UrlConfig.APPLAY_VERIFICATION)
    Call<ApplyCommit> apply_verification(@Body FormBody body);

    //全部订单
    @POST(UrlConfig.MY_TRIP_PARTICIPATE_LIST_ALL)
    Call<Order> my_trip_participate_list_all(@Body FormBody body);


    //未付款
    @POST(UrlConfig.MY_TRIP_PARTICIPATE_LIST_NOT_PAYED)
    Call<Order> my_trip_participate_list_not_payed(@Body FormBody body);


    //已付款
    @POST(UrlConfig.MY_TRIP_PARTICIPATE_LIST_PAYED)
    Call<Order> my_trip_participate_list_payed(@Body FormBody body);


    //已完成
    @POST(UrlConfig.MY_TRIP_PARTICIPATE_LIST_FINISHED)
    Call<Order> my_trip_participate_list_finished(@Body FormBody body);


    //已取消
    @POST(UrlConfig.MY_TRIP_PARTICIPATE_LIST_CANCELLED)
    Call<Order> my_trip_participate_list_cancelled(@Body FormBody body);


    //通知商家支付成功
    @POST(UrlConfig.TRIP_PARTICIPATE_USER_PAYED)
    Call<OrderPayed> TRIP_PARTICIPATE_USER_PAYED(@Body FormBody body);

    //报名活动的用户申请退款（必须在活动开始之前）
    @POST(UrlConfig.USER_REFUND_APPLY)
    Call<BaseBean> user_refund_apply(@Body FormBody body);

    //待评价活动报名列表
    @POST(UrlConfig.NOT_COMMENT_LIST)
    Call<NoCommend> not_comment_list(@Body FormBody body);

    //已评价的活动报名
    @POST(UrlConfig.COMMENT_LIST)
    Call<Comment> comment_list(@Body FormBody body);

    //已评价的活动报名
    @POST(UrlConfig.COMMENT_NEW)
    Call<CommentNew> new_comment(@Body FormBody body);

    //申请商家验证
    @POST(UrlConfig.APPLY_BUSINESS)
    Call<ApplyCompany> apply_business(@Body FormBody body);

    //显示商家用户申请商家认证状态接口
    @POST(UrlConfig.APPLY_BUSINESS_STATE)
    Call<ApplyCompanyState> apply_business_state(@Body FormBody body);

    //商家管理后台，下线活动
    @POST(UrlConfig.ACTION_OFF_LINE)
    Call<ActionOffLine> action_off_line(@Body FormBody body);

    //删除一个活动（商家操作，只能删除自己创建的活动）
    @POST(UrlConfig.DEL_ACTION)
    Call<DelAction> del_action(@Body FormBody body);

    //用户投诉并且申请退款（必须在活动结束之后，且在投诉期内）
    @POST(UrlConfig.USER_REFUND_COMPLAINT)
    Call<BaseBean> user_refund_complaint(@Body FormBody body);

    //获取一个活动的所有（以及所有App所需状态）的报名信息列表
    @POST(UrlConfig.LIST_BY_TRIP_ID)
    Call<ActionDetail> list_by_trip_id(@Body FormBody body);

    //用户评价详情
    @POST(UrlConfig.COMMENT_DETAILS)
    Call<CommentDetail> comment_detail(@Body FormBody body);

    //删除一条动态
    @POST(UrlConfig.REMOVE_STATE)
    Call<RemoveState> remove_state(@Body FormBody body);

    //用户取消活动报名
    @POST(UrlConfig.USER_CANCEL_ORDER)
    Call<CancelOrder> user_cancel_order(@Body FormBody body);

    //获取微信预支付订单信息
    @POST(UrlConfig.PAY_WX)
    Call<PayWx> pay_wx(@Body FormBody body);


    //撤销退款
    @POST(UrlConfig.USER_REVOCATION_APPLY)
    Call<CancelOrder> user_revocation_apply(@Body FormBody body);


    //撤销投诉
    @POST(UrlConfig.USER_REVOCATION_COMPLAINT)
    Call<CancelOrder> user_revocation_complaint(@Body FormBody body);
}
