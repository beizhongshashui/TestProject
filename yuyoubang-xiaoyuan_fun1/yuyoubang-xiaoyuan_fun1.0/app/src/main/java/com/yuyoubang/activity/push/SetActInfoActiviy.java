package com.yuyoubang.activity.push;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.bean.NewTrip;
import com.yuyoubang.listener.OnClickListener;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiaoyuan on 16/11/18.
 */
public class SetActInfoActiviy extends BaseActivity {
    @Bind(R.id.travel_info)
    EditText travelInfo;
    @Bind(R.id.cost)
    EditText cost;
    @Bind(R.id.onther)
    EditText onther;
    private NewTrip newTrip;

    public static void start(Activity context, NewTrip newTrip, int requestCode) {
        Intent intent = new Intent(context, SetActInfoActiviy.class);
        intent.putExtra("trip", newTrip);
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("活动详情");
        builder.setRightTvText("保存", new OnClickListener() {
            @Override
            protected void clickOperate() {
                String tInfo = travelInfo.getText().toString();
                String tCost = cost.getText().toString();
                String tOhther = onther.getText().toString();
                newTrip.setTrip_intro(tInfo);//todo
                newTrip.setTrip_cost(tCost);//todo
                newTrip.setTrip_other(tOhther);//todo
                Intent intent   = new Intent();
                intent.putExtra("trip",newTrip);
                setResult(Activity.RESULT_OK,intent);
                finish();

            }
        });
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_set_act_info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        newTrip = (NewTrip) getIntent().getExtras().getSerializable("trip");
        initView(newTrip);
    }

    private void initView(NewTrip newTrip){
        travelInfo.setText(newTrip.getTrip_intro());
        cost.setText(newTrip.getTrip_cost());
        onther.setText(newTrip.getTrip_other());

    }
}
