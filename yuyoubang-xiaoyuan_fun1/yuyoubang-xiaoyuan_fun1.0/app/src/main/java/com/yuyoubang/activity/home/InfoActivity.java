package com.yuyoubang.activity.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseNetActivity;
import com.yuyoubang.adapter.TripLikeAdapter;
import com.yuyoubang.app.TripIsOrder;
import com.yuyoubang.bean.ActionInfoBean;
import com.yuyoubang.bean.TripLikeBean;
import com.yuyoubang.bean.TripUnLikeBean;
import com.yuyoubang.fragment.home.ActionEvaluateFragment;
import com.yuyoubang.fragment.home.ActionInfoFragment;
import com.yuyoubang.fragment.message.ChatActivity;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.HomeApi;
import com.yuyoubang.network.api.TripApi;
import com.yuyoubang.utils.DateUtils;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.LogUtils;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.QLog;
import com.yuyoubang.utils.ScreenUtil;
import com.yuyoubang.utils.TimeUtil;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.view.SharePopwin;
import com.yuyoubang.view.StickyScrollView;
import com.yuyoubang.widget.recycleview.RecyclerListView;

import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 16/11/9.
 */
public class InfoActivity extends BaseNetActivity<ActionInfoBean> implements StickyScrollView.OnScrollChangedListener {
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Bind(R.id.trip_end_time)
    TextView tripEndTime;
    @Bind(R.id.trip_name)
    TextView tripName;
    @Bind(R.id.trip_old)
    TextView tripOld;
    @Bind(R.id.trip_location)
    TextView tripLocation;
    @Bind(R.id.trip_title)
    TextView tripTitle;
    @Bind(R.id.trip_label)
    LinearLayout tripLabel;
    @Bind(R.id.trip_action_date)
    TextView tripActionDate;
    @Bind(R.id.trip_enter)
    TextView tripEnter;
    @Bind(R.id.trip_enter_count)
    TextView tripEnterCount;
    @Bind(R.id.trip_like_count)
    TextView tripLikeCount;
    @Bind(R.id.trip_enter_term)
    TextView tripEnterTerm;
    @Bind(R.id.trip_like_rl)
    RecyclerListView tripLikeRl;
    @Bind(R.id.trip_like)
    TextView tripLike;
    @Bind(R.id.trip_ask)
    TextView tripAsk;
    @Bind(R.id.trip_sign_up)
    TextView tripSignUp;
    @Bind(R.id.trip_pic)
    ImageView tripPic;
    @Bind(R.id.trip_price)
    TextView tripPrice;
    @Bind(R.id.trip_cover)
    ImageView tripCover;
    @Bind(R.id.share_icon)
    ImageView share_icon;
    @Bind(R.id.iv_sex)
    ImageView iv_sex;
    @Bind(R.id.trip_like_more)
    ImageView tripLikeMore;
    @Bind(R.id.scrollView)
    StickyScrollView scrollView;
    @Bind(R.id.ll_content)
    LinearLayout llContent;
    @Bind(R.id.title)
    RelativeLayout rlTitle;
    @Bind(R.id.trip_enter_tyip)
    TextView tripEnterTyip;
    @Bind(R.id.trip_enter_sure)
    TextView tripEnterSure;
    @Bind(R.id.info_meet_place)
    TextView infoMeetPlace;
    @Bind(R.id.send_msg)
    LinearLayout send_msg;
    @Bind(R.id.bg_color)
    LinearLayout bg_color;
    @Bind(R.id.trip_islike)
    ImageView tripIslike;
    @Bind(R.id.report)
    TextView report;
    private String[] title;
    private FragmentPagerAdapter adapter;
    private String trip_id;
    private TripLikeAdapter tripLikeAdapter;

    public ActionInfoBean actionInfoBean;

    public String biz_uid;
    private int height;
    private int trip_state;
    private String image;
    private String titleShare;
    private String locationCity = "";


    public static void start(Context context, String trip_id) {
        Intent intent = new Intent(context, InfoActivity.class);
        intent.putExtra("trip_id", trip_id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        mHeaderBuilder.goneToolbar();
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_info_2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);

        trip_id = getIntent().getExtras().getString("trip_id");
        scrollView.setOnScrollListener(this);
        tripEnterTerm.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tripEnterTerm.getPaint().setAntiAlias(true);//抗锯齿
        Log.d("InfoActivity", "高度" + ScreenUtil.getScreenHeight(InfoActivity.this) + "");
        initListeners();

//        StatusBarUtil.setTranslucentForImageView(InfoActivity.this, 0, tripEndTime);
//        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        layoutParams.height = ScreenUtil.getScreenHeight(this)/;
//        tripCover.setLayoutParams(layoutParams);

//        loadData();
//        StatusBarUtil.setTranslucentForImageView(InfoActivity.this, 0, coorLayout);
    }


    private void initListeners() {
        //获取内容总高度
        final ViewTreeObserver vto = llContent.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                height = llContent.getHeight();
                //注意要移除
                llContent.getViewTreeObserver()
                        .removeGlobalOnLayoutListener(this);

            }
        });

//        //获取Fragment高度
//        ViewTreeObserver viewTreeObserver = frameLayout.getViewTreeObserver();
//        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                height = height - frameLayout.getHeight();
//                //注意要移除
//                frameLayout.getViewTreeObserver()
//                        .removeGlobalOnLayoutListener(this);
//            }
//        });


//        //获取title高度
        ViewTreeObserver viewTreeObserver1 = rlTitle.getViewTreeObserver();
        viewTreeObserver1.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                scrollView.setStickTop(rlTitle.getHeight());//设置距离多少悬浮
                height = height - rlTitle.getHeight();//计算滑动的总距离

                //注意要移除
                rlTitle.getViewTreeObserver()
                        .removeGlobalOnLayoutListener(this);
            }
        });

        ViewTreeObserver viewTreeObserver2 = tabLayout.getViewTreeObserver();
        viewTreeObserver2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                height = height - tabLayout.getHeight();//计算滑动的总距离

                //注意要移除
                tabLayout.getViewTreeObserver()
                        .removeGlobalOnLayoutListener(this);
            }
        });
        ViewTreeObserver viewTreeObserver3 = viewPager.getViewTreeObserver();
        viewTreeObserver3.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                height = height - viewPager.getHeight();//计算滑动的总距离

                //注意要移除
                viewPager.getViewTreeObserver()
                        .removeGlobalOnLayoutListener(this);
            }
        });

    }


    @Override
    protected void loadData() {
        HomeApi homeApi = RetrofitManager.getTestRetrofit().create(HomeApi.class);
        FormBody body = new FormBody.Builder()
                .add("trip_id", trip_id)
                .add("uid", PreferenceUtils.getsessionId(this))
                .build();
        QLog.d("uid", "uid=" + PreferenceUtils.getsessionId(this));
        Call<ActionInfoBean> beanCall = homeApi.trip_show(body);
        beanCall.enqueue(this);
    }

    @Override
    protected void processData(ActionInfoBean actionInfoBean) {

        this.actionInfoBean = actionInfoBean;
        if (actionInfoBean.getResult().getTrip() != null) {
            if (actionInfoBean.getResult().getTrip().get(0).getUser_result() != null) {
                if (!TextUtils.isEmpty(actionInfoBean.getResult().getTrip().get(0).getUser_result().getUser_data().getGender())) {
                    if (Integer.valueOf(actionInfoBean.getResult().getTrip().get(0).getUser_result().getUser_data().getGender()) == 0) {
                        iv_sex.setImageResource(R.mipmap.sex_men);
                        bg_color.setBackgroundResource(R.drawable.bg_color_ff9600);
                        tripLocation.setBackgroundResource(R.drawable.bg_color_ff9600);
                    } else {
                        iv_sex.setImageResource(R.mipmap.sex_women);
                        bg_color.setBackgroundResource(R.drawable.bg_color_fd89cb);
                        tripLocation.setBackgroundResource(R.drawable.bg_color_fd89cb);
                    }
                }
                long birthday = actionInfoBean.getResult().getTrip().get(0).getUser_result().getUser_data().getBirthday();
                if (birthday != 0) {
                    try {
                        Date longToData = TimeUtil.getLongToData(birthday);
                        int age = TimeUtil.getAge(longToData);
                        tripOld.setText(age + "");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                trip_state = actionInfoBean.getResult().getTrip().get(0).getData().getTrip_state();
                if (trip_state > 1) {
                    tripSignUp.setText("活动已下线");
                    tripSignUp.setBackgroundColor(Color.parseColor("#F7F7F7"));
                }
                ImageLoader.getInstance().displayImage(actionInfoBean.getResult().getTrip().get(0).getUser_result().getUser_data().getProfile_pic_url(), tripPic, ImageOption.defaultOptions);
//        tripOld.setText(actionInfoBean.getResult().getTrip().get(0).getUser().getUser_data().getUser_name());

                if (actionInfoBean.getResult().getTrip().get(0).getUser_result().getUser_data().getProvince().contains("市")) {//直辖市
                    locationCity = actionInfoBean.getResult().getTrip().get(0).getUser_result().getUser_data().getProvince();
                } else {
                    locationCity = actionInfoBean.getResult().getTrip().get(0).getUser_result().getUser_data().getUser_location();
                }

                if (!TextUtils.isEmpty(locationCity)) {
                    if (locationCity.contains("市")) {
                        String substring = locationCity.substring(0, locationCity.length() - 1);
                        tripLocation.setText(substring);
                    } else {
                        tripLocation.setText(locationCity);
                    }
                }
//                tripLocation.setText(actionInfoBean.getResult().getTrip().get(0).getUser_result().getUser_data().getUser_location());
                tripName.setText(actionInfoBean.getResult().getTrip().get(0).getUser_result().getUser_data().getUser_name());
                biz_uid = actionInfoBean.getResult().getTrip().get(0).getUser_result().getId() + "";

            }

            titleShare = actionInfoBean.getResult().getTrip().get(0).getData().getTrip_name();
            tripTitle.setText(actionInfoBean.getResult().getTrip().get(0).getData().getTrip_name());
            tripEndTime.setText("报名截止:" + DateUtils.getDateToMonth(actionInfoBean.getResult().getTrip().get(0).getData().getParticipate_end_time()));
            image = actionInfoBean.getResult().getTrip().get(0).getData().getTrip_cover_pic();
            ImageLoader.getInstance().displayImage(actionInfoBean.getResult().getTrip().get(0).getData().getTrip_cover_pic(), tripCover, ImageOption.defaultOptions);
            tripEnterSure.setText(actionInfoBean.getResult().getTrip().get(0).getData().getParticipants_female_count() + actionInfoBean.getResult().getTrip().get(0).getData().getParticipants_male_count() + "");
            tripEnterCount.setText("报名" + "（" + (actionInfoBean.getResult().getTrip().get(0).getData().getParticipants_female_count() + actionInfoBean.getResult().getTrip().get(0).getData().getParticipants_male_count()) + " )");
            tripLikeCount.setText("喜欢" + "（" + actionInfoBean.getResult().getTrip().get(0).getData().getLike_count() + " )");
            tripEnter.setText("/" + actionInfoBean.getResult().getTrip().get(0).getData().getParticipants_limit_count() + "人");
            tripEnterTyip.setText("   其中男：" + actionInfoBean.getResult().getTrip().get(0).getData().getParticipants_male_count() + "人；" + "女：" + actionInfoBean.getResult().getTrip().get(0).getData().getParticipants_female_count() + "人");

            if (actionInfoBean.getResult().getTrip().get(0).getData().getTrip_tags() != null) {

                if (actionInfoBean.getResult().getTrip().get(0).getData().getTrip_tags().size() > 0) {

                    for (int i = 0; i < actionInfoBean.getResult().getTrip().get(0).getData().getTrip_tags().size(); i++) {
                        TextView textView = new TextView(InfoActivity.this);
                        textView.setText(actionInfoBean.getResult().getTrip().get(0).getData().getTrip_tags().get(i));
                        textView.setTextColor(getResources().getColor(R.color.color_ff9600));
                        textView.setBackgroundResource(R.drawable.yellow_circle);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        layoutParams.setMargins(30, 0, 0, 0);
                        tripLabel.addView(textView, layoutParams);
                    }
                }
            }

            infoMeetPlace.setText(/*actionInfoBean.getResult().getTrip().get(0).getData().getMeeting_province() + " " + */actionInfoBean.getResult().getTrip().get(0).getData().getMeeting_city() + " " + actionInfoBean.getResult().getTrip().get(0).getData().getMeeting_place());
            tripPrice.setText(actionInfoBean.getResult().getTrip().get(0).getData().getTrip_price() + "");
            tripActionDate.setText(DateUtils.getDateToMonth(actionInfoBean.getResult().getTrip().get(0).getData().getStart_time()) + "一" + DateUtils.getDateToMonth(actionInfoBean.getResult().getTrip().get(0).getData().getEnd_time()));
//        tripEnter.setText();
//        tripLikeCount.setText("喜欢" + DateUtils.getDateToMonth(actionInfoBean.getResult().getTrip().get(0).getData().getLike_count()) + "");
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
            mLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
            tripLikeRl.setLayoutManager(mLayoutManager);
            tripLikeAdapter = new TripLikeAdapter(tripLikeRl, InfoActivity.this, actionInfoBean.getResult().getLike_user_list());
            tripLikeRl.setAdapter(tripLikeAdapter);
        }


        if (actionInfoBean.getResult().getTrip().get(0).getData().getIs_liked() == 1) {
            tripIslike.setImageResource(R.mipmap.act_info_like_focus);
        }
        goneLoading();
        initView();

    }


    private void initView() {
        title = this.getResources().getStringArray(R.array.info);
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return getFragments().get(position);
            }

            @Override
            public int getCount() {
                return getFragments().size();
            }
        };
        viewPager.setAdapter(adapter);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        initTabLine();
    }

    private void initTabLine() {
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("活动详情");
        tabLayout.getTabAt(1).setText("邦主评价");
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(ActionInfoFragment.newInstance());
        fragments.add(ActionEvaluateFragment.newInstance());
        return fragments;
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1000) {
                ToastUtils.showLong("进入讨论组成功");
            }
            if (msg.what == 1001) {
                ToastUtils.showLong("进入讨论组失败");
            }
        }
    };

    @OnClick({R.id.trip_enter_term, R.id.send_msg, R.id.trip_islike, R.id.trip_ask, R.id.trip_sign_up, R.id.trip_like_more, R.id.info_left_iv, R.id.share_icon, R.id.report, R.id.trip_enter_count, R.id.trip_like_count, R.id.trip_pic})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.trip_enter_term:
                requestTripIsOrder();
                /*if (actionInfoBean.getResult().getIs_payed().size() > 0) {//已经报名
                    if (actionInfoBean.getResult().getTrip().get(0).getData().getUser_create_group_id() > 0) {
                        onShowProgressDlg();
                        EMClient.getInstance().groupManager().asyncJoinGroup(actionInfoBean.getResult().getTrip().get(0).getData().getUser_create_group_id() + "", new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                ChatActivity.start(InfoActivity.this, actionInfoBean.getResult().getTrip().get(0).getData().getUser_create_group_id() + "", ChatActivity.Group);
                                handler.sendEmptyMessage(1000);
                                cancelProgressDlg();
                            }

                            @Override
                            public void onError(int i, String s) {
                                LogUtils.e(s);
                                if (i == 601) {//已经加入过群组
                                    ChatActivity.start(InfoActivity.this, actionInfoBean.getResult().getTrip().get(0).getData().getUser_create_group_id() + "", ChatActivity.Group);
                                    handler.sendEmptyMessage(1000);
                                } else {
                                    handler.sendEmptyMessage(1001);
                                }
                                cancelProgressDlg();
                            }

                            @Override
                            public void onProgress(int i, String s) {

                            }
                        });
                    }
                } else {
                    ToastUtils.showShort("请先报名");
                }*/

                break;
            case R.id.trip_islike:
                if (actionInfoBean.getResult().getTrip().get(0).getData().getIs_liked() == 1) {
                    unlike();
                } else {
                    like();
                }

                break;
            case R.id.trip_ask:
                break;
            case R.id.trip_sign_up:
                if (trip_state > 1) {
                    ToastUtils.showShort("该活动已下线");
                    return;
                }
                JoinActivity.start(InfoActivity.this, actionInfoBean);
                break;
            case R.id.trip_like_more:
                ApplyAndLikeActivity.start(InfoActivity.this, trip_id, 0);
                break;
            case R.id.info_left_iv:
                finish();
                break;
            case R.id.share_icon:
                showPopFormBottom();
                break;
            case R.id.send_msg:
                ChatActivity.start(InfoActivity.this, biz_uid, ChatActivity.SINGLE);
                break;
            case R.id.report:
                LaunchOperate.openReportActivity(this, trip_id, biz_uid);
//                reportTrip();
                break;

            case R.id.trip_enter_count:
                ApplyAndLikeActivity.start(InfoActivity.this, trip_id, 1);
                break;

            case R.id.trip_like_count:
                ApplyAndLikeActivity.start(InfoActivity.this, trip_id, 2);
                break;
            case R.id.trip_pic:
                LaunchOperate.openOtherHome(this, biz_uid);
                break;
        }
    }

    private void requestTripIsOrder() {
        FormBody formBody = new FormBody.Builder()
                .add("trip_id", trip_id)
                .add("uid", PreferenceUtils.getsessionId(this))
                .build();
        HomeApi homeApi = RetrofitManager.getTestRetrofit().create(HomeApi.class);
        Call<TripIsOrder> tripIsOrderCall = homeApi.trip_id_order(formBody);
        tripIsOrderCall.enqueue(new Callback<TripIsOrder>() {
            @Override
            public void onResponse(Call<TripIsOrder> call, Response<TripIsOrder> response) {
                if (response.body().getError_code() == 0) {
                    if (response.body().getResult().getList() != null) {
                        if (response.body().getResult().getList().size() > 0) {
                            if (actionInfoBean.getResult().getTrip().get(0).getData().getUser_create_group_id() > 0) {
                                onShowProgressDlg();
                                EMClient.getInstance().groupManager().asyncJoinGroup(actionInfoBean.getResult().getTrip().get(0).getData().getUser_create_group_id() + "", new EMCallBack() {
                                    @Override
                                    public void onSuccess() {
                                        ChatActivity.start(InfoActivity.this, actionInfoBean.getResult().getTrip().get(0).getData().getUser_create_group_id() + "", ChatActivity.Group);
                                        handler.sendEmptyMessage(1000);
                                        cancelProgressDlg();
                                    }

                                    @Override
                                    public void onError(int i, String s) {
                                        LogUtils.e(s);
                                        if (i == 601) {//已经加入过群组
                                            ChatActivity.start(InfoActivity.this, actionInfoBean.getResult().getTrip().get(0).getData().getUser_create_group_id() + "", ChatActivity.Group);
                                            handler.sendEmptyMessage(1000);
                                        } else {
                                            handler.sendEmptyMessage(1001);
                                        }
                                        cancelProgressDlg();
                                    }

                                    @Override
                                    public void onProgress(int i, String s) {

                                    }
                                });
                            } else {
                                ToastUtils.showLong("进入讨论组失败,请联系邦主");
                            }
                        } else {
                            ToastUtils.showShort("请先报名");
                        }
                    } else {
                        ToastUtils.showShort("请先报名");
                    }
                } else {
                    ToastUtils.showLong("进入讨论组失败");
                }
            }

            @Override
            public void onFailure(Call<TripIsOrder> call, Throwable t) {
                ToastUtils.showLong("进入讨论组失败");
            }
        });
    }

    /*private void reportTrip() {
        onShowProgressDlg();
        FormBody formBody = new FormBody.Builder()
                .add("data_id", trip_id)//举报数据id
                .add("text", "todo:举报")//举报内容
                .add("to_uid", biz_uid)//与本条数据相关联的用户 id，必须是已存在的用户，否则本条数据创建会失败
                .add("type", "0")//类别（0=活动，1=动态）
                .add("uid", PreferenceUtils.getsessionId(this))//与本条数据相关联的用户 id，必须是已存在的用户，否则本条数据创建会失败
                .build();
        HomeApi homeApi = RetrofitManager.getTestRetrofit().create(HomeApi.class);
        Call<Report> reportCall = homeApi.user_report_create(formBody);
        reportCall.enqueue(new Callback<Report>() {
            @Override
            public void onResponse(Call<Report> call, Response<Report> response) {
                cancelProgressDlg();
                if (response.body().getError_code() == 0) {
                    ToastUtils.showLong("举报成功");
                }
            }

            @Override
            public void onFailure(Call<Report> call, Throwable t) {
                cancelProgressDlg();
                ToastUtils.showLong("举报失败");
            }
        });
    }*/

    //弹出分享页
    public void showPopFormBottom() {
        SharePopwin sharePopwin = new SharePopwin(this, trip_id, image, titleShare, 1);
        //showAtLocation(View parent, int gravity, int x, int y)
        sharePopwin.showAtLocation(findViewById(R.id.share_icon), Gravity.CENTER, 0, 0);
    }

    private void like() {
        QLog.d("like", "trip_id=" + trip_id + "uid" + PreferenceUtils.getsessionId(InfoActivity.this) + "");
        TripApi tripApi = RetrofitManager.getTestRetrofit().create(TripApi.class);
        FormBody body = new FormBody.Builder().add("trip_id", trip_id).add("uid", PreferenceUtils.getsessionId(InfoActivity.this)).build();
        Call<TripLikeBean> beanCall = tripApi.trip_like(body);
        beanCall.enqueue(new Callback<TripLikeBean>() {
            @Override
            public void onResponse(Call<TripLikeBean> call, Response<TripLikeBean> response) {
                if (response.body().getError_code() == 0) {
                    actionInfoBean.getResult().getTrip().get(0).getData().setIs_liked(1);
                    tripIslike.setImageResource(R.mipmap.act_info_like_focus);
                } else {
                    ToastUtils.showLong("喜欢失败");
                }


            }

            @Override
            public void onFailure(Call<TripLikeBean> call, Throwable t) {
                ToastUtils.showLong(R.string.net_error);
            }
        });
    }

    private void unlike() {
        TripApi tripApi = RetrofitManager.getTestRetrofit().create(TripApi.class);
        FormBody body = new FormBody.Builder().add("trip_id", trip_id).add("uid", PreferenceUtils.getsessionId(InfoActivity.this)).build();
        Call<TripUnLikeBean> beanCall = tripApi.trip_unlike(body);
        beanCall.enqueue(new Callback<TripUnLikeBean>() {
            @Override
            public void onResponse(Call<TripUnLikeBean> call, Response<TripUnLikeBean> response) {
                if (response.body().getError_code() == 0) {
                    actionInfoBean.getResult().getTrip().get(0).getData().setIs_liked(0);
                    tripIslike.setImageResource(R.mipmap.act_info_like);
                } else {
                    ToastUtils.showLong("喜欢失败");
                }


            }

            @Override
            public void onFailure(Call<TripUnLikeBean> call, Throwable t) {
                ToastUtils.showLong(R.string.net_error);
            }
        });
    }

    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (t <= 0) {
            rlTitle.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));
        } else if (t > 0 && t <= height) {
            float scale = (float) t / height;
            int alpha = (int) (255 * scale);
            rlTitle.setBackgroundColor(Color.argb((int) alpha, 253, 149, 38));//设置标题栏的透明度及颜色
        } else {
            rlTitle.setBackgroundColor(Color.argb((int) 255, 253, 149, 38));
        }
    }


}
