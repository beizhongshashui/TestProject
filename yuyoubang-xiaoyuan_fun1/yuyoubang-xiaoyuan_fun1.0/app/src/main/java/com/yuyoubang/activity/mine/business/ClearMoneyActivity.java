package com.yuyoubang.activity.mine.business;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.activity.base.BaseNetActivity;
import com.yuyoubang.app.GlobalParams;
import com.yuyoubang.bean.BizMoney;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.BusinessApi;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.ToastUtils;

import okhttp3.FormBody;
import retrofit2.Call;

/**
 * Created by xiaoyuan on 16/12/1.
 */
public class ClearMoneyActivity extends BaseNetActivity<BizMoney> {

    private TextView yu_e;

    public static void start(Context context) {
        Intent intent = new Intent(context, ClearMoneyActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("财务管理");
        builder.setRightTvText("结算说明", new OnClickListener() {
            @Override
            protected void clickOperate() {
                LaunchOperate.openRuleActivity(ClearMoneyActivity.this, GlobalParams.INTRO, 2, "temp");

            }
        });
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_clear_money;
    }

    @Override
    protected void initViews() {
        yu_e = getViewById(R.id.yu_e);
    }

    @Override
    protected void loadData() {
        FormBody formBody = new FormBody.Builder()
                .add("biz_uid", PreferenceUtils.getsessionId(this))
                .build();
        BusinessApi businessApi = RetrofitManager.getTestRetrofit().create(BusinessApi.class);
        Call<BizMoney> bizMoneyCall = businessApi.biz_money(formBody);
        bizMoneyCall.enqueue(this);
    }

    @Override
    protected void processData(BizMoney bizMoney) {
        goneLoading();
        if (bizMoney.getError_code() == 10001) {
            ToastUtils.showShort("系统错误");
            return;
        }
        if (bizMoney.getError_code() == 0) {
            if (bizMoney.getResult() != null) {
                if (bizMoney.getResult().getAccount_info() != null) {
                    yu_e.setText(bizMoney.getResult().getAccount_info().getValue());
                }
            }
        }
    }
}
