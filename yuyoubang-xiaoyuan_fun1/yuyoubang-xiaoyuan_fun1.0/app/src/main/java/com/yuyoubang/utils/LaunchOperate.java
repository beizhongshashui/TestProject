package com.yuyoubang.utils;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.yuyoubang.activity.PostTeamsActivity;
import com.yuyoubang.activity.ScanImgsActivity;
import com.yuyoubang.activity.find.ChooseContentActivity;
import com.yuyoubang.activity.find.ChooseResultActivity;
import com.yuyoubang.activity.find.FindStatusLikeActivity;
import com.yuyoubang.activity.find.MoreLikeUserActivity;
import com.yuyoubang.activity.find.MoreRecommendUserActivity;
import com.yuyoubang.activity.find.OtherHomeActivity;
import com.yuyoubang.activity.home.ReportActivity;
import com.yuyoubang.activity.home.SearchResultActivity;
import com.yuyoubang.activity.login.LoginAccountActivity;
import com.yuyoubang.activity.login.LoginActivity;
import com.yuyoubang.activity.mine.AboutActivity;
import com.yuyoubang.activity.mine.AddTagsActivity;
import com.yuyoubang.activity.mine.BlackUserActivity;
import com.yuyoubang.activity.mine.CareUserActivity;
import com.yuyoubang.activity.mine.CommentActivity;
import com.yuyoubang.activity.mine.CommentDetailsActivity;
import com.yuyoubang.activity.mine.EditorActivity;
import com.yuyoubang.activity.mine.FeedBackActivity;
import com.yuyoubang.activity.mine.FixPasswordActivity;
import com.yuyoubang.activity.mine.MineCommendActivity;
import com.yuyoubang.activity.mine.MineDoTanActivity;
import com.yuyoubang.activity.mine.MineOrderActivity;
import com.yuyoubang.activity.mine.MineSignActivity;
import com.yuyoubang.activity.mine.SetPhoneActivity;
import com.yuyoubang.activity.mine.SetPhoneTwoActivity;
import com.yuyoubang.activity.mine.SettingActivity;
import com.yuyoubang.activity.mine.UserInfoStateActivity;
import com.yuyoubang.activity.mine.YuYouBangRuleActivity;
import com.yuyoubang.activity.mine.business.BusSearchResultActivity;
import com.yuyoubang.activity.mine.business.BusinessSerachActivity;
import com.yuyoubang.activity.mine.business.OrderDetailActivity;
import com.yuyoubang.activity.mine.business.OrderTypeStateActivity;
import com.yuyoubang.activity.mine.business.ThemeSearchResultActivity;
import com.yuyoubang.activity.mine.business.ThemeSerachActivity;
import com.yuyoubang.activity.mine.business.action.ActionDetailsActivity;
import com.yuyoubang.activity.mine.order.OrderPayActivity;
import com.yuyoubang.activity.msg.ContactsActivity;
import com.yuyoubang.activity.msg.MineTeamsActivity;
import com.yuyoubang.activity.msg.OrderMessageActivity;
import com.yuyoubang.activity.msg.SysMessageActivity;
import com.yuyoubang.activity.msg.TeamsDetailsActivity;
import com.yuyoubang.activity.msg.TeamsMessageActivity;
import com.yuyoubang.activity.msg.VisitMessageActivity;
import com.yuyoubang.activity.push.NewLocalActivity;
import com.yuyoubang.bean.TeamsMember;
import com.yuyoubang.bean.mine.NoCommend;

import java.io.Serializable;
import java.util.List;

/**
 * date: Created hongchen on 16/11/05.
 */
public class LaunchOperate {

    /**
     * 跳转登录界面
     *
     * @param context 跳转类
     */
    public static void openLoginActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    /**
     * 跳转登录界面
     *
     * @param context 跳转类
     */
    public static void openLoginAccActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LoginAccountActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    /**
     * 跳转筛选界面
     *
     * @param context
     */
    public static void openChooseContentActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ChooseContentActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转图片浏览页
     *
     * @param imgUrl
     */
    public static void openImgScan(Context context, List<String> imgUrl, int position) {
        Intent intent = new Intent(context, ScanImgsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putSerializable("imgurl", (Serializable) imgUrl);
        intent.putExtra("pos", position);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 跳转粉丝列表页
     */
    public static void openLikeList(Context context, String uid, String flag) {
        Intent intent = new Intent();
        intent.putExtra("uid", uid);
        intent.putExtra("flag", flag);
        intent.setClass(context, MoreLikeUserActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    /**
     * 跳转粉丝列表页
     */
    public static void openRecommendUserList(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MoreRecommendUserActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    /**
     * 跳转他人主页
     */
    public static void openOtherHome(Context context, String uid) {
        Intent intent = new Intent();
        intent.putExtra("uid", uid);
        intent.setClass(context, OtherHomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    /**
     * 关于我们界面
     *
     * @param context
     */
    public static void openAboutActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, AboutActivity.class);
        context.startActivity(intent);
    }

    /**
     * 意见反馈界面
     *
     * @param context
     */
    public static void openFeedbackActivity(Context context, int flag) {
        Intent intent = new Intent();
        intent.setClass(context, FeedBackActivity.class);
        intent.putExtra("flag", flag);
        context.startActivity(intent);
    }

    /**
     * 设置界面
     *
     * @param context
     */
    public static void openSettingActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SettingActivity.class);
        context.startActivity(intent);
    }

    /**
     * 修改密码界面
     *
     * @param context
     */
    public static void openFixPswActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, FixPasswordActivity.class);
        context.startActivity(intent);
    }

    /**
     * 修改手机号一级界面
     *
     * @param context
     */
    public static void openSetPhoneActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SetPhoneActivity.class);
        context.startActivity(intent);
    }

    /**
     * 修改手机号二级界面
     *
     * @param context
     */
    public static void openSetPhoneTwoActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SetPhoneTwoActivity.class);
        context.startActivity(intent);
    }

    /**
     * 编辑个人资料界面
     *
     * @param context
     */
    public static void openEditorActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, EditorActivity.class);
        context.startActivity(intent);
    }

    /**
     * 个人签名界面
     *
     * @param context
     */
    public static void openMineSignActivity(Context context, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(context, MineSignActivity.class);
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    /**
     * 跳转关注列表页
     */
    public static void openCareUserList(Context context, String uid, String flag) {
        Intent intent = new Intent();
        intent.putExtra("uid", uid);
        intent.putExtra("flag", flag);
        intent.setClass(context, CareUserActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    /**
     * 跳转黑名单列表页
     */
    public static void openBlackUserList(Context context, String uid) {
        Intent intent = new Intent();
        intent.putExtra("uid", uid);
        intent.setClass(context, BlackUserActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    /**
     * 我的动态界面
     *
     * @param context
     */
    public static void openMineDoTaiActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MineDoTanActivity.class);
        context.startActivity(intent);
    }

    /**
     * 个人认证界面
     *
     * @param context
     */
    public static void openUserInfoStatsActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, UserInfoStateActivity.class);
        context.startActivity(intent);
    }

    /**
     * 我的订单 界面
     *
     * @param context
     */
    public static void openMineOrderActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MineOrderActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    /**
     * 我的评价 界面
     *
     * @param context
     */
    public static void openMineCommendActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MineCommendActivity.class);
        context.startActivity(intent);
    }

    /**
     * 评价 界面
     *
     * @param context
     */
    public static void openCommentActivity(Context context, NoCommend.ResultBean.ListBean data) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        intent.setClass(context, CommentActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 全部订单 界面
     *
     * @param context
     */
    public static void openOrderTypeActivity(Context context, int position, String trip_biz_uid) {
        Intent intent = new Intent();
        intent.putExtra("position", position);
        intent.putExtra("trip_biz_uid", trip_biz_uid);
        intent.setClass(context, OrderTypeStateActivity.class);
        context.startActivity(intent);
    }

    /**
     * 活动报名详情 界面
     *
     * @param context
     */
    public static void openActionDetailsActivity(Context context, String trip_id) {
        Intent intent = new Intent();
        intent.setClass(context, ActionDetailsActivity.class);
        intent.putExtra("trip_id", trip_id);
        context.startActivity(intent);
    }

    /**
     * 修改标签 界面
     *
     * @param context
     */
    public static void openAddTagsActivity(Context context, List<String> userTags, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(context, AddTagsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("userTags", (Serializable) userTags);
        intent.putExtras(bundle);
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    /**
     * 立即支付 界面
     *
     * @param context
     */
    public static void openOrderPayActivity(Context context, String biz_uid, String trip_id,
                                            String trip_participate_id,
                                            String produceName, String producePrice) {
        Intent intent = new Intent();
        intent.setClass(context, OrderPayActivity.class);
        intent.putExtra("biz_uid", biz_uid);
        intent.putExtra("trip_id", trip_id);
        intent.putExtra("trip_participate_id", trip_participate_id);
        intent.putExtra("produceName", produceName);
        intent.putExtra("producePrice", producePrice);
        context.startActivity(intent);
    }

    /**
     * 订单详情 界面
     *
     * @param context
     */
    public static void openOrderDetailsActivity(Context context, String trip_id) {
        Intent intent = new Intent();
        intent.setClass(context, OrderDetailActivity.class);
        intent.putExtra("trip_id", trip_id);
        context.startActivity(intent);
    }

    /**
     * 系统消息 界面
     *
     * @param context
     */
    public static void openSysMsgActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SysMessageActivity.class);
        context.startActivity(intent);
    }

    /**
     * 订单 消息 界面
     *
     * @param context
     */
    public static void openOrderMsgActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, OrderMessageActivity.class);
        context.startActivity(intent);
    }

    /**
     * 访客 消息 界面
     *
     * @param context
     */
    public static void openVisitMsgActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, VisitMessageActivity.class);
        context.startActivity(intent);
    }

    /**
     * 小组请求 消息 界面
     *
     * @param context
     */
    public static void openTeamsMsgActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TeamsMessageActivity.class);
        context.startActivity(intent);
    }

    /**
     * 我的群组 消息 界面
     *
     * @param context
     */
    public static void openMineTeamsActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MineTeamsActivity.class);
        context.startActivity(intent);
    }

    /**
     * 好友列表 界面
     *
     * @param context
     */
    public static void openContactsActivity(Context context, String flag, List<TeamsMember.ResultBean.ListBean> memList, String group_id) {
        Intent intent = new Intent(context, ContactsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("memList", (Serializable) memList);
        intent.putExtra("flag", flag);
        intent.putExtra("group_id", group_id);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 讨论组资料 界面
     *
     * @param context
     */
    public static void openTeamsDetailsActivity(Context context, String group_id, String flag) {
        Intent intent = new Intent();
        intent.setClass(context, TeamsDetailsActivity.class);
        intent.putExtra("group_id", group_id);
        intent.putExtra("flag", flag);
        context.startActivity(intent);
    }

    /**
     * 搜索结果 界面
     *
     * @param context
     */
    public static void openSearchResultActivity(Context context, String content) {
        Intent intent = new Intent();
        intent.setClass(context, SearchResultActivity.class);
        intent.putExtra("content", content);
        context.startActivity(intent);
    }

    /**
     * 评价详情 界面
     *
     * @param context
     */
    public static void openCommentDetailActivity(Context context, String biz_uid, String trip_id, String trip_participate_id) {
        Intent intent = new Intent();
        intent.setClass(context, CommentDetailsActivity.class);
        intent.putExtra("biz_uid", biz_uid);
        intent.putExtra("trip_id", trip_id);
        intent.putExtra("trip_participate_id", trip_participate_id);
        context.startActivity(intent);
    }

    /**
     * 跳转遇游邦规则协议
     *
     * @param context
     */
    public static void openRuleActivity(Context context, String path, int position, String title) {
        Intent intent = new Intent();
        intent.putExtra("title", "使用条款");
        intent.putExtra("url", path);
        intent.putExtra("position", position);
        intent.putExtra("title", title);
        intent.setClass(context, YuYouBangRuleActivity.class);
        context.startActivity(intent);
    }

    /**
     * 个人签名界面
     *
     * @param context
     */
    public static void openPoiMapActivity(Context context, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(context, NewLocalActivity.class);
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    /**
     * 搜索订单 界面
     *
     * @param context
     */
    public static void openBusinessSearchActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, BusinessSerachActivity.class);
        context.startActivity(intent);
    }

    /**
     * 搜索订单
     *
     * @param context
     */
    public static void openSerachBusResultActivity(Context context, String order_id, String trip_time_start, String trip_time_end) {
        Intent intent = new Intent();
        intent.putExtra("order_id", order_id);
        intent.putExtra("trip_time_start", trip_time_start);
        intent.putExtra("trip_time_end", trip_time_end);
        intent.setClass(context, BusSearchResultActivity.class);
        context.startActivity(intent);
    }

    /**
     * 搜索主题订单 界面
     *
     * @param context
     */
    public static void openThemeSearchActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ThemeSerachActivity.class);
        context.startActivity(intent);
    }

    /**
     * 搜索主题订单
     *
     * @param context
     */
    public static void openSerachThemeResultActivity(Context context, String trip_name) {
        Intent intent = new Intent();
        intent.putExtra("trip_name", trip_name);
        intent.setClass(context, ThemeSearchResultActivity.class);
        context.startActivity(intent);
    }

    /**
     * 搜索动态
     *
     * @param context
     */
    public static void openSerachStatusActivity(Context context, String experience_end, String experience_start, String gender, String user_location) {
        Intent intent = new Intent();
        intent.putExtra("experience_end", experience_end);
        intent.putExtra("experience_start", experience_start);
        intent.putExtra("gender", gender);
        intent.putExtra("user_location", user_location);
        intent.setClass(context, ChooseResultActivity.class);
        context.startActivity(intent);
    }

    /**
     * 举报 界面
     *
     * @param context
     */
    public static void openReportActivity(Context context, String trip_id, String biz_uid) {
        Intent intent = new Intent();
        intent.putExtra("trip_id", trip_id);
        intent.putExtra("biz_uid", biz_uid);
        intent.setClass(context, ReportActivity.class);
        context.startActivity(intent);
    }

    /**
     * 点赞列表 界面
     *
     * @param context
     */
    public static void openStatusLikeActivity(Context context, String status_id) {
        Intent intent = new Intent();
        intent.setClass(context, FindStatusLikeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("status_id", status_id);
        context.startActivity(intent);
    }

    /**
     * 推荐小组 界面
     *
     * @param context
     */
    public static void openPostTeamActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, PostTeamsActivity.class);
        context.startActivity(intent);
    }
}
