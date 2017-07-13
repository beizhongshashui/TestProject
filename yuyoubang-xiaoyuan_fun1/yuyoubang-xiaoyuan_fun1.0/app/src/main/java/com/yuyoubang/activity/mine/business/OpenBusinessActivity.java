package com.yuyoubang.activity.mine.business;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.activity.model.ApplyStateWindow;
import com.yuyoubang.activity.model.CommentPopupWindow;
import com.yuyoubang.app.GlobalParams;
import com.yuyoubang.bean.mine.ApplyCompanyState;
import com.yuyoubang.bean.mine.UserSureName;
import com.yuyoubang.listener.IsCloseClickListener;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.MineApi;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 16/12/1.
 */
public class OpenBusinessActivity extends BaseActivity {
    @Bind(R.id.tv_open_biz)
    TextView tvOpenBiz;
    @Bind(R.id.cb_argee)
    CheckBox cbArgee;

    private boolean b;
    private TextView yuyoubang_rule;

    public static void start(Context context) {
        Intent intent = new Intent(context, OpenBusinessActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("商家中心");
    }


    @Override
    protected int getContentResId() {
        return R.layout.act_open_business;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        yuyoubang_rule = getViewById(R.id.yuyoubang_rule);
        cbArgee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                b = isChecked;
            }
        });


        yuyoubang_rule.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                LaunchOperate.openRuleActivity(OpenBusinessActivity.this, GlobalParams.RULE, 1, "temp");
            }
        });
    }

    @OnClick(R.id.tv_open_biz)
    public void onClick() {
        if (!b) {
            ToastUtils.showLong("请同意遇游邦邦主管理规则");
            return;
        }
//        lookUserApplyState();
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
                            //biz_id
                            if (body.getResult().getList().get(0).getData().getApply_state() == 1) {//通过
                                BusinessCenterActivity.start(OpenBusinessActivity.this, PreferenceUtils.getsessionId(OpenBusinessActivity.this));
                            } else {
                                lookUserApplyState();
                            }
                        } else {
                            lookUserApplyState();
                        }
                    } else {
                        lookUserApplyState();
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


    private void lookUserApplyState() {
        FormBody formBody = new FormBody.Builder()
                .add("uid", PreferenceUtils.getsessionId(this))
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<UserSureName> userSureNameCall = mineApi.person_sure_name(formBody);
        userSureNameCall.enqueue(new Callback<UserSureName>() {
            @Override
            public void onResponse(Call<UserSureName> call, Response<UserSureName> response) {
                UserSureName body = response.body();
                if (body != null) {
                    if (body.getResult() != null) {
                        if (body.getResult().getList().size() > 0) {
                            if (body.getResult().getList().get(0).getData().getApply_state() == 0) {
                                showPopuWindow();
                            }
                            if (body.getResult().getList().get(0).getData().getApply_state() == 1) {//实名
                                OpenBusinessTypeActivity.start(OpenBusinessActivity.this);
                            }
                            if (body.getResult().getList().get(0).getData().getApply_state() == 2) {//
                                showPopuWindow();
                            }
                        } else {
                            showPopuWindow();
                        }
                    } else {
                        showPopuWindow();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserSureName> call, Throwable t) {

            }
        });
    }

    private void showPopuWindow() {
        ApplyStateWindow msgPopupWindow = new ApplyStateWindow(this, new IsCloseClickListener() {
            @Override
            public void sure() {
                LaunchOperate.openUserInfoStatsActivity(OpenBusinessActivity.this);
            }

            @Override
            public void notSure() {
            }
        });
        msgPopupWindow.showAtLocation(
                getViewById(R.id.tv_open_biz), Gravity.BOTTOM
                        | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

}
