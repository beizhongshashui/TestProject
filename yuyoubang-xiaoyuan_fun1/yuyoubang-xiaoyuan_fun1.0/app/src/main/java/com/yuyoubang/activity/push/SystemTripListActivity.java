package com.yuyoubang.activity.push;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseNetActivity;
import com.yuyoubang.adapter.SystemTripAdapter;
import com.yuyoubang.bean.SystemTripBean;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.TripApi;
import com.yuyoubang.widget.recycleview.OnItemClickListener;
import com.yuyoubang.widget.recycleview.RecyclerListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import retrofit2.Call;

/**
 * Created by xiaoyuan on 16/11/21.
 */
public class SystemTripListActivity extends BaseNetActivity<SystemTripBean> {
    @Bind(R.id.system_tyrp_rl)
    RecyclerListView systemTyrpRl;
    private SystemTripAdapter systemTripAdapter;
    private SystemTripBean.ResultBean.ListBean ListBean;

    public static void start(Activity context, int requestCode) {
        Intent intent = new Intent(context, SystemTripListActivity.class);
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        systemTyrpRl.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                for (int i = 0; i < systemTripAdapter.getData().size(); i++) {
                    systemTripAdapter.getData().get(i).setIsChoose(0);
                }
                systemTripAdapter.getData().get(position).setIsChoose(1);
                systemTripAdapter.notifyDataSetChanged();
                ListBean = systemTripAdapter.getData().get(position);

                Intent intent = new Intent();
                intent.putExtra("system_trip", ListBean);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

    }

    @Override
    protected void loadData() {
        TripApi tripApi = RetrofitManager.getTestRetrofit().create(TripApi.class);
        FormBody body = new FormBody.Builder().build();
        Call<SystemTripBean> data = tripApi.system_trip(body);
        data.enqueue(this);

    }

    @Override
    protected void processData(SystemTripBean systemTripBean) {
        systemTripAdapter = new SystemTripAdapter(systemTyrpRl, SystemTripListActivity.this, systemTripBean.getResult().getList());
        systemTyrpRl.setAdapter(systemTripAdapter);
        goneLoading();
    }


    @Override
    protected int getContentResId() {
        return R.layout.act_system_trip;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
//        builder.setTitle("活动类型");
//        builder.setRightTvText("保存", new OnClickListener() {
//            @Override
//            protected void clickOperate() {
//                Intent intent = new Intent();
//                intent.putExtra("system_trip", ListBean);
//                setResult(Activity.RESULT_OK,intent);
//                finish();
//            }
//        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: add setContentView(...) invocation
    }
}
