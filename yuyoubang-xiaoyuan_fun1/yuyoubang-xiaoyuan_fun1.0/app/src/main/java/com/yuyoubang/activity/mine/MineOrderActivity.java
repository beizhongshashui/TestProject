package com.yuyoubang.activity.mine;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.activity.mine.order.AllOrderFragment;
import com.yuyoubang.activity.mine.order.CancelFragment;
import com.yuyoubang.activity.mine.order.CompleteFragment;
import com.yuyoubang.activity.mine.order.NoPayFragment;
import com.yuyoubang.activity.mine.order.YesPayFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hongchen on 16/12/2.
 */

public class MineOrderActivity extends BaseActivity {

    @Bind(R.id.my_order_tablayout)
    TabLayout myOrderTablayout;
    @Bind(R.id.my_order_vp)
    ViewPager myOrderVp;
    private FragmentPagerAdapter adapter;
    public final IWXAPI msgApi = WXAPIFactory.createWXAPI(this, null);



    @Override
    protected int getContentResId() {
        return R.layout.act_mine_order;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("我的订单");
    }



    private void initView() {
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
        myOrderVp.setAdapter(adapter);
        myOrderTablayout.setTabMode(TabLayout.MODE_FIXED);
        initTabLine();
    }

    private void initTabLine() {
        myOrderTablayout.setupWithViewPager(myOrderVp);
        myOrderTablayout.getTabAt(0).setText("全部");
        myOrderTablayout.getTabAt(1).setText("未付款");
        myOrderTablayout.getTabAt(2).setText("已付款");
        myOrderTablayout.getTabAt(3).setText("已完成");
        myOrderTablayout.getTabAt(4).setText("取消");
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(AllOrderFragment.newInstance());
        fragments.add(NoPayFragment.newInstance());
        fragments.add(YesPayFragment.newInstance());
        fragments.add(CompleteFragment.newInstance());
        fragments.add(CancelFragment.newInstance());
        return fragments;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initView();
    }


}
