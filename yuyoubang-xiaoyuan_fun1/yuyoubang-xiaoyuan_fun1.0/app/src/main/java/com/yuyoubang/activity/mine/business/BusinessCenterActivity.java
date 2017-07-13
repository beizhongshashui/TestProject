package com.yuyoubang.activity.mine.business;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseNetActivity;
import com.yuyoubang.activity.push.PushActActivity;
import com.yuyoubang.bean.BizBean;
import com.yuyoubang.bean.mine.ApplyCompanyState;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.BusinessApi;
import com.yuyoubang.network.api.MineApi;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.QLog;
import com.yuyoubang.utils.TimeUtil;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.view.RoundImageView;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 16/11/30.
 */
public class BusinessCenterActivity extends BaseNetActivity<BizBean> {

    @Bind(R.id.avatar)
    RoundImageView avatar;
    @Bind(R.id.biz_name)
    TextView bizName;
    @Bind(R.id.biz_age)
    TextView bizAge;
    @Bind(R.id.biz_sign)
    TextView bizSign;
    @Bind(R.id.act_manger)
    LinearLayout actManger;
    @Bind(R.id.push_new_act)
    LinearLayout pushNewAct;
    @Bind(R.id.money_manger)
    LinearLayout moneyManger;
    @Bind(R.id.company_apply)
    LinearLayout companyApply;
    @Bind(R.id.no_pay_no_sure)
    TextView noPayNoSure;
    @Bind(R.id.no_pay_yes_sure)
    TextView noPayYesSure;
    @Bind(R.id.yes_pay)
    TextView yesPay;
    @Bind(R.id.complete)
    TextView complete;
    @Bind(R.id.complaint)
    TextView complaint;
    @Bind(R.id.cancel)
    TextView cancel;
    private TextView apply_state;
    private int apply_state1 = -1;
    private String biz_id;
    private ImageView iv_sex;
    private LinearLayout bg_color;

    public static void start(Context context, String biz_id) {
        Intent intent = new Intent(context, BusinessCenterActivity.class);
        intent.putExtra("biz_id", biz_id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("商家管理中心");
    }


    @Override
    protected int getContentResId() {
        return R.layout.act_business_center;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void initViews() {
        biz_id = getIntent().getStringExtra("biz_id");
        apply_state = getViewById(R.id.apply_state);
        iv_sex = getViewById(R.id.iv_sex);
        bg_color = getViewById(R.id.bg_color);
        LinearLayout type_1 = getViewById(R.id.type_1);
        LinearLayout type_2 = getViewById(R.id.type_2);
        LinearLayout type_3 = getViewById(R.id.type_3);
        LinearLayout type_4 = getViewById(R.id.type_4);
        LinearLayout type_5 = getViewById(R.id.type_5);
        LinearLayout type_6 = getViewById(R.id.type_6);


        type_1.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                LaunchOperate.openOrderTypeActivity(BusinessCenterActivity.this, 0, PreferenceUtils.getsessionId(BusinessCenterActivity.this));
            }
        });
        type_2.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                LaunchOperate.openOrderTypeActivity(BusinessCenterActivity.this, 1, PreferenceUtils.getsessionId(BusinessCenterActivity.this));
            }
        });
        type_3.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                LaunchOperate.openOrderTypeActivity(BusinessCenterActivity.this, 2, PreferenceUtils.getsessionId(BusinessCenterActivity.this));
            }
        });
        type_4.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                LaunchOperate.openOrderTypeActivity(BusinessCenterActivity.this, 3, PreferenceUtils.getsessionId(BusinessCenterActivity.this));
            }
        });
        type_5.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                LaunchOperate.openOrderTypeActivity(BusinessCenterActivity.this, 4, PreferenceUtils.getsessionId(BusinessCenterActivity.this));
            }
        });
        type_6.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                LaunchOperate.openOrderTypeActivity(BusinessCenterActivity.this, 5, PreferenceUtils.getsessionId(BusinessCenterActivity.this));
            }
        });
    }

    @Override
    protected void loadData() {
        BusinessApi businessApi = RetrofitManager.getTestRetrofit().create(BusinessApi.class);
        FormBody body = new FormBody.Builder().add("biz_uid", PreferenceUtils.getsessionId(this)).build();//todo biz_uid//写死，因为我还不是邦主//962033467064324
        Call<BizBean> business = businessApi.BIZ_USER_HOME_PAGE_AND_STATISTICS_SHOW(body);
        business.enqueue(this);
        QLog.d("xiaoyuan", "uid" + PreferenceUtils.getsessionId(this));
    }

    @Override
    protected void processData(BizBean bizBean) {
        goneLoading();
        if (bizBean != null) {
            if (bizBean.getResult() != null) {
                cancel.setText(bizBean.getResult().getCancelled_participate_count() + "");
                complete.setText(bizBean.getResult().getFinished_participate_count() + "");
                noPayNoSure.setText(bizBean.getResult().getNot_paied_not_confirmed_participate_count() + "");
                noPayYesSure.setText(bizBean.getResult().getNot_paied_confirmed_participate_count() + "");
                yesPay.setText(bizBean.getResult().getPaied_participate_count() + "");
                complaint.setText(bizBean.getResult().getRefund_participate_count() + "");
                if (bizBean.getResult().getUser() != null) {
                    ImageLoader.getInstance().displayImage(bizBean.getResult().getUser().get(0).getUser_data().getProfile_pic_url(), avatar, ImageOption.defaultOptions);
                    bizName.setText(bizBean.getResult().getUser().get(0).getUser_data().getUser_name());
//                bizAge.setText("todo");
                    bizSign.setText(bizBean.getResult().getUser().get(0).getUser_data().getUser_desc());
                    if (!TextUtils.isEmpty(bizBean.getResult().getUser().get(0).getUser_data().getGender())) {
                        if (Integer.valueOf(bizBean.getResult().getUser().get(0).getUser_data().getGender()) == 0) {
                            iv_sex.setImageResource(R.mipmap.sex_men);
                            bg_color.setBackgroundResource(R.drawable.bg_color_ff9600);
                        } else {
                            iv_sex.setImageResource(R.mipmap.sex_women);
                            bg_color.setBackgroundResource(R.drawable.bg_color_fd89cb);
                        }
                    }

                    long birthday = Long.valueOf(bizBean.getResult().getUser().get(0).getUser_data().getBirthday());
                    if (birthday != 0) {
                        try {
                            Date longToData = TimeUtil.getLongToData(birthday);
                            int age = TimeUtil.getAge(longToData);
                            bizAge.setText(age + "");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        requestApplyState();
    }

    private void requestApplyState() {
        FormBody formBody = new FormBody.Builder()
                .add("biz_uid", PreferenceUtils.getsessionId(this))
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<ApplyCompanyState> applyCompanyStateCall = mineApi.apply_business_state(formBody);
        applyCompanyStateCall.enqueue(new Callback<ApplyCompanyState>() {
            @Override
            public void onResponse(Call<ApplyCompanyState> call, Response<ApplyCompanyState> response) {
                ApplyCompanyState body = response.body();
                if (body != null) {
                    if (body.getResult() != null) {
                        if (body.getResult().getList().size() > 0) {
                            apply_state1 = body.getResult().getList().get(0).getData().getApply_state();
                            if (body.getResult().getList().get(0).getData().getApply_state() == 0) {//审核中
                                apply_state.setText("  企业认证(审核中)");
                            }
                            if (body.getResult().getList().get(0).getData().getApply_state() == 1) {//通过
                                apply_state.setText("  企业认证(已认证)");
                            }
                            if (body.getResult().getList().get(0).getData().getApply_state() == 2) {//拒绝
                                apply_state.setText("  企业认证(拒绝)");
                            }
                        } else {//未申请
                            apply_state.setText("  企业认证(未申请)");
                        }
                    }
                }
                cancelProgressDlg();
            }

            @Override
            public void onFailure(Call<ApplyCompanyState> call, Throwable t) {
                cancelProgressDlg();
            }
        });
    }


    @OnClick({R.id.act_manger, R.id.push_new_act, R.id.money_manger, R.id.company_apply})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.act_manger:
                ActionMangerActivity.start(this);
                break;
            case R.id.push_new_act:
                PushActActivity.start(this);
                break;
            case R.id.money_manger:
                ClearMoneyActivity.start(this);
                break;
            case R.id.company_apply:
                switch (apply_state1) {
                    case 0:
                        ToastUtils.showShort("审核中");
                        break;
                    case 1:
                        ToastUtils.showShort("已认证");
                        break;
                    case 2:
                        ToastUtils.showShort("拒绝");
                        break;
                    case -1:
                        CompanyActivity.start(BusinessCenterActivity.this);
                        break;
                }
                break;
        }
    }
}
