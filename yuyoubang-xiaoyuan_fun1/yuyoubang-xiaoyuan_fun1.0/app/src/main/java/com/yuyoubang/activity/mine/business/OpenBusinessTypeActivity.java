package com.yuyoubang.activity.mine.business;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.bean.mine.ApplyCompanyState;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.MineApi;
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
public class OpenBusinessTypeActivity extends BaseActivity {


    @Bind(R.id.personal)
    LinearLayout personal;
    @Bind(R.id.company)
    LinearLayout company;
    private TextView apply_state;
    private int apply_state1 = -1;
    private long biz_id;

    public static void start(Context context) {
        Intent intent = new Intent(context, OpenBusinessTypeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("开通邦主身份");
    }


    @Override
    protected int getContentResId() {
        return R.layout.act_open_business_type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        onShowProgressDlg();
        initView();
        requestApplyState();
    }

    private void initView() {
        apply_state = getViewById(R.id.apply_state);
    }


    @OnClick({R.id.personal, R.id.company})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.personal:
                BusinessCenterActivity.start(this, String.valueOf(biz_id));
                break;
            case R.id.company:
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
                        CompanyActivity.start(this);
                        break;
                }
                break;
        }
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
                            biz_id = body.getResult().getList().get(0).getId();
                            apply_state1 = body.getResult().getList().get(0).getData().getApply_state();
                            if (body.getResult().getList().get(0).getData().getApply_state() == 0) {//审核中
                                OpenBusinessTypeActivity.this.apply_state.setText("  企业用户(审核中)");
                            }
                            if (body.getResult().getList().get(0).getData().getApply_state() == 1) {//通过
                                OpenBusinessTypeActivity.this.apply_state.setText("  企业用户(已认证)");
                            }
                            if (body.getResult().getList().get(0).getData().getApply_state() == 2) {//拒绝
                                OpenBusinessTypeActivity.this.apply_state.setText("  企业用户(拒绝)");
                            }
                        } else {//未申请
                            apply_state.setText("  企业用户(未申请)");
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
}
