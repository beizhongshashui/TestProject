package com.yuyoubang.network.api;

import com.yuyoubang.bean.ChooseStatusResult;
import com.yuyoubang.bean.City;
import com.yuyoubang.bean.FindCloseStar;
import com.yuyoubang.bean.FindCommentList;
import com.yuyoubang.bean.FindFollowBean;
import com.yuyoubang.bean.FindFollowInfoBean;
import com.yuyoubang.bean.FindOpenStar;
import com.yuyoubang.bean.FindRecomendBean;
import com.yuyoubang.bean.MineVisit;
import com.yuyoubang.bean.PostTeams;
import com.yuyoubang.bean.RemoveUser;
import com.yuyoubang.bean.SendComment;
import com.yuyoubang.bean.StatusLike;
import com.yuyoubang.bean.SysMessage;
import com.yuyoubang.bean.TeamsMember;
import com.yuyoubang.bean.TripLikeListBean;
import com.yuyoubang.bean.TripLikeUser;
import com.yuyoubang.bean.VisitUser;
import com.yuyoubang.bean.findfollowinfo.FollowUser;
import com.yuyoubang.bean.findfollowinfo.MoreLikeList;
import com.yuyoubang.bean.findfollowinfo.OtherHome;
import com.yuyoubang.config.UrlConfig;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by xiaoyuan on 16/11/21.
 */
public interface FindApi {
    //获取推荐用户列表
    @POST(UrlConfig.USER_RECOMMEND_LIST)
    Call<FindRecomendBean> recommend_list(@Body FormBody body);

    //获取推荐用户列表
    @POST(UrlConfig.STATUS_TIMELINE)
    Call<FindFollowBean> status_timeline(@Body FormBody body);

    //获取热门列表
    @POST(UrlConfig.STATUS_HOT_LIST)
    Call<FindFollowBean> status_hot_list(@Body FormBody body);

    //获取同城列表
    @POST(UrlConfig.STATUS_SAME_LOC_LIST)
    Call<FindFollowBean> status_same_loc_list(@Body FormBody body);

    //获取动态详情
    @POST(UrlConfig.STATUS_SHOW)
    Call<FindFollowInfoBean> status_show(@Body FormBody body);

    //获取动态评论
    @POST(UrlConfig.STATUS_COMMENT_LIST)
    Call<FindCommentList> status_comment_list(@Body FormBody body);

    //获取赞过这个活动的用户列表
    @POST(UrlConfig.TRIP_LIKE_LIST)
    Call<TripLikeUser> trip_like_list(@Body FormBody body);

    //评论动态
    @POST(UrlConfig.STATUS_COMMENT)
    Call<SendComment> status_comment(@Body FormBody body);

    //赞动态
    @POST(UrlConfig.STATUS_LIKE)
    Call<FindOpenStar> status_like(@Body FormBody body);

    //取消赞动态
    @POST(UrlConfig.STATUS_LIKE_REMOVE)
    Call<FindCloseStar> status_like_remove(@Body FormBody body);

    //获取用户关注用户列表
    @POST(UrlConfig.MY_FOLLOW_LIST)
    Call<MoreLikeList> my_follow_list(@Body FormBody body);

    //粉丝列表
    @POST(UrlConfig.MY_FANS_LISt)
    Call<MoreLikeList> my_fans_list(@Body FormBody body);

    //显示用户主页接口
    @POST(UrlConfig.USER_INFO_SHOW)
    Call<OtherHome> user_info_show(@Body FormBody body);

    //关注一个用户
    @POST(UrlConfig.FOLLOW_USER)
    Call<FollowUser> follow_user(@Body FormBody body);

    //用户取消关注
    @POST(UrlConfig.REMOVE_FOLLOW_USER)
    Call<RemoveUser> remove_follow_user(@Body FormBody body);

    //用户取消关注
    @POST(UrlConfig.GROUP_LIST)
    Call<TeamsMember> group_list(@Body FormBody body);

    //访问主页
    @POST(UrlConfig.VISIT_USER)
    Call<VisitUser> visit_user(@Body FormBody body);

    //我的访客
    @POST(UrlConfig.MY_VISIT)
    Call<MineVisit> mine_visit(@Body FormBody body);

    //系统消息
    @POST(UrlConfig.SYS_MESSAGE)
    Call<SysMessage> sys_message(@Body FormBody body);

    //系统消息
    @POST(UrlConfig.SERACH_STATUS)
    Call<FindFollowBean> search_status(@Body FormBody body);

    //赞动态列表
    @POST(UrlConfig.STATUS_LIKE_LIST)
    Call<StatusLike> status_like_list(@Body FormBody body);

    //推荐活动列表
    @POST(UrlConfig.POST_TEAMS)
    Call<PostTeams> post_teams(@Body FormBody body);
}
