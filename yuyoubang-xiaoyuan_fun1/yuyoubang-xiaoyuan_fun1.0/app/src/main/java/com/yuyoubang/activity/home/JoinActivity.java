package com.yuyoubang.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.app.GlobalParams;
import com.yuyoubang.app.TripIsOrder;
import com.yuyoubang.bean.ActionInfoBean;
import com.yuyoubang.bean.BaseBean;
import com.yuyoubang.fragment.message.ChatActivity;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.HomeApi;
import com.yuyoubang.network.api.TripApi;
import com.yuyoubang.otto.AppBus;
import com.yuyoubang.utils.DateUtils;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.QLog;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.utils.ValidateUtil;
import com.yuyoubang.view.AmountView;
import com.yuyoubang.view.ApplyDialog;
import com.yuyoubang.view.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 16/11/14.
 */
public class JoinActivity extends BaseActivity {
    @Bind(R.id.join_avatar)
    CircleImageView joinAvatar;
    @Bind(R.id.join_sponsor)
    TextView joinSponsor;
    @Bind(R.id.join_trip_pic)
    ImageView joinTripPic;
    @Bind(R.id.join_title)
    TextView joinTitle;
    @Bind(R.id.join_time)
    TextView joinTime;
    @Bind(R.id.join_all_price)
    TextView joinAllPrice;
    @Bind(R.id.join_name)
    EditText joinName;
    @Bind(R.id.join_phone)
    EditText joinPhone;
    @Bind(R.id.join_man)
    AmountView joinMan;
    @Bind(R.id.join_woman)
    AmountView joinWoman;
    @Bind(R.id.join_cb)
    CheckBox joinCb;
    @Bind(R.id.join_price)
    TextView joinPrice;
    @Bind(R.id.join_sure)
    TextView joinSure;
    @Bind(R.id.sign_know)
    TextView sign_know;

    public ActionInfoBean actionInfoBean;
    @Bind(R.id.join_apply_count)
    TextView joinApplyCount;


    private int allCount = 0;
    private int manCount = 0;
    private int womanCount = 0;
    private boolean b;

    private long allManprice; //todo float //总价
    private long allWomanprice; //todo float //总价
    private long price; //todo float //单价
    private String phone;
    private String name;
    private ApplyDialog applydialog;

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("我要参加");
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_join;
    }


    public static void start(Context context, ActionInfoBean actionInfoBean) {
        Intent intent = new Intent(context, JoinActivity.class);
        intent.putExtra("actionInfoBean", actionInfoBean);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        AppBus.getInstance().register(this);
        initView();

    }

    @OnClick({R.id.sign_know})
    public void onClick(View view) {
        LaunchOperate.openRuleActivity(JoinActivity.this, GlobalParams.SIGN_KNOW, 4, "temp");
    }

    private void initView() {
        b = joinCb.isChecked();
        joinCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                b = isChecked;
            }
        });
        joinMan.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                manCount = amount;
                joinWoman.setGoods_storage(allCount - manCount);
                allManprice = amount * price;
                joinPrice.setText((allManprice + allWomanprice) + "");


            }
        });

        joinWoman.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                womanCount = amount;
                joinMan.setGoods_storage(allCount - womanCount);
                allWomanprice = amount * price;
                joinPrice.setText((allManprice + allWomanprice) + "");
            }
        });
        actionInfoBean = (ActionInfoBean) getIntent().getExtras().getSerializable("actionInfoBean");
        allCount = actionInfoBean.getResult().getTrip().get(0).getData().getParticipants_limit_count() - actionInfoBean.getResult().getTrip().get(0).getData().getParticipants_female_count() - actionInfoBean.getResult().getTrip().get(0).getData().getParticipants_male_count();
        joinMan.setGoods_storage(allCount);
        joinWoman.setGoods_storage(allCount);
        price = actionInfoBean.getResult().getTrip().get(0).getData().getTrip_price();
        if (actionInfoBean.getResult().getTrip().get(0).getUser_result() != null) {
            if (actionInfoBean.getResult().getTrip().get(0).getUser_result().getUser_data() != null) {
                if (actionInfoBean.getResult().getTrip().get(0).getUser_result().getUser_data().getProfile_pic_url() != null) {
                    ImageLoader.getInstance().displayImage(actionInfoBean.getResult().getTrip().get(0).getUser_result().getUser_data().getProfile_pic_url(), joinAvatar, ImageOption.defaultOptions);
                }
                joinSponsor.setText(actionInfoBean.getResult().getTrip().get(0).getUser_result().getUser_data().getUser_name());
            }
        }
        ImageLoader.getInstance().displayImage(actionInfoBean.getResult().getTrip().get(0).getData().getTrip_cover_pic(), joinTripPic, ImageOption.default_trip_options);
        joinTime.setText(DateUtils.getDateToMonth(actionInfoBean.getResult().getTrip().get(0).getData().getStart_time()) + "一" + DateUtils.getDateToMonth(actionInfoBean.getResult().getTrip().get(0).getData().getEnd_time()));
        joinAllPrice.setText(actionInfoBean.getResult().getTrip().get(0).getData().getTrip_price() + "");
        joinTitle.setText(actionInfoBean.getResult().getTrip().get(0).getData().getTrip_name() + "");
        joinApplyCount.setText("报名人数：" + (actionInfoBean.getResult().getTrip().get(0).getData().getParticipants_female_count() + actionInfoBean.getResult().getTrip().get(0).getData().getParticipants_male_count()) + "人");
        joinSure.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                phone = joinPhone.getText().toString().trim();
                name = joinName.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    ToastUtils.showLong("请填写联系人");
                    return;
                }
                if (!ValidateUtil.isMobile(phone)) {
                    ToastUtils.showShort("请输入正确的手机号哦！");
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    ToastUtils.showLong("请填写手机号");
                    return;
                }

                if (womanCount + manCount == 0) {
                    ToastUtils.showLong("请选择报名人数");
                    return;
                }

                if (!b) {
                    ToastUtils.showLong("请同意遇游邦活动报名须知");
                    return;
                }

//                initPay();
                if (actionInfoBean.getResult().getTrip().get(0).getUser_result() == null) {
                    ToastUtils.showShort("此活动信息有误");
                    return;
                }
                bookTirp();

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        AppBus.getInstance().unregister(this);
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

    private void bookTirp() {
        QLog.d("TAG",""+phone+"-"+name+"-"+womanCount+"-"+manCount+"-"+joinPrice.getText().toString()+"-"+actionInfoBean.getResult().getTrip().get(0).getUser_result().getId()+"-"+actionInfoBean.getResult().getTrip().get(0).getId()+"-"+PreferenceUtils.getsessionId(this));
        TripApi tripApi = RetrofitManager.getTestRetrofit().create(TripApi.class);
        FormBody body = new FormBody.Builder()
                .add("contact_phone", phone)
                .add("contact_user_name", name)
                .add("female_count", womanCount + "")
                .add("male_count", manCount + "")
                .add("total_price", joinPrice.getText().toString())
                .add("trip_biz_uid", actionInfoBean.getResult().getTrip().get(0).getUser_result().getId() + "")
                .add("trip_id", actionInfoBean.getResult().getTrip().get(0).getId() + "")
                .add("uid", PreferenceUtils.getsessionId(this))
                .build();

        Call<BaseBean> book = tripApi.trip_participate_book(body);
        book.enqueue(new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                QLog.d("xiaoyuan", response.body().toString() + "报名结果");
                QLog.d("xiaoyuan", new Gson().toJson(response.body().toString()) + "报名结果");
                if (response.body().error_code == 0) {
                    ToastUtils.showLong("报名成功");
                    showDialog();
                } else {

                    ToastUtils.showLong("报名失败");
                }
            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {
                ToastUtils.showLong(R.string.net_error);
            }
        });
//

    }


    private void showDialog() {
        applydialog = new ApplyDialog(JoinActivity.this);
        applydialog.setJoinclickListener(new ApplyDialog.onJoinGroupListener() {
            @Override
            public void onJoinGroupClick() {
                requestTripIsOrder();
//                ToastUtils.showLong("进讨论组");
                /*if (actionInfoBean.getResult().getIs_payed().size() > 0) {
                    if (actionInfoBean.getResult().getTrip().get(0).getData().getUser_create_group_id() > 0) {
                        onShowProgressDlg();
                        EMClient.getInstance().groupManager().asyncJoinGroup(actionInfoBean.getResult().getTrip().get(0).getData().getUser_create_group_id() + "", new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                ChatActivity.start(JoinActivity.this, actionInfoBean.getResult().getTrip().get(0).getData().getUser_create_group_id() + "", ChatActivity.Group);
                                handler.sendEmptyMessage(1000);
                                cancelProgressDlg();
                                applydialog.dismiss();
                            }

                            @Override
                            public void onError(int i, String s) {
                                if (i == 601) {//已经加入过群组
                                    ChatActivity.start(JoinActivity.this, actionInfoBean.getResult().getTrip().get(0).getData().getUser_create_group_id() + "", ChatActivity.Group);
                                    handler.sendEmptyMessage(1000);
                                } else {
                                    handler.sendEmptyMessage(1001);
                                }
                                cancelProgressDlg();
                                applydialog.dismiss();
                            }

                            @Override
                            public void onProgress(int i, String s) {
                                applydialog.dismiss();
                            }
                        });
                    }
                } else {
                    ToastUtils.showLong("进入讨论组失败,请联系邦主");
                    applydialog.dismiss();
                }*/
//                ChatActivity.start(JoinActivity.this, actionInfoBean.getResult().getTrip().get(0).getData().getUser_create_group_id() + "", ChatActivity.Group);
//                finish();
            }
        });

        applydialog.setLookApplyOnclickListener(new ApplyDialog.onLookApplyListener() {
            @Override
            public void onLookApplyClick() {
                ToastUtils.showLong("查看报名");
                applydialog.dismiss();
                LaunchOperate.openMineOrderActivity(JoinActivity.this);
                finish();

            }
        });

        applydialog.show();
    }


    private void requestTripIsOrder() {
        FormBody formBody = new FormBody.Builder()
                .add("trip_id", actionInfoBean.getResult().getTrip().get(0).getId() + "")
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
                                        ChatActivity.start(JoinActivity.this, actionInfoBean.getResult().getTrip().get(0).getData().getUser_create_group_id() + "", ChatActivity.Group);
                                        handler.sendEmptyMessage(1000);
                                        cancelProgressDlg();
                                        applydialog.dismiss();
                                    }

                                    @Override
                                    public void onError(int i, String s) {
                                        if (i == 601) {//已经加入过群组
                                            ChatActivity.start(JoinActivity.this, actionInfoBean.getResult().getTrip().get(0).getData().getUser_create_group_id() + "", ChatActivity.Group);
                                            handler.sendEmptyMessage(1000);
                                        } else {
                                            handler.sendEmptyMessage(1001);
                                        }
                                        cancelProgressDlg();
                                        applydialog.dismiss();
                                    }

                                    @Override
                                    public void onProgress(int i, String s) {
                                        applydialog.dismiss();
                                    }
                                });
                            } else {
                                ToastUtils.showLong("进入讨论组失败,请联系邦主");
                                applydialog.dismiss();
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

}
