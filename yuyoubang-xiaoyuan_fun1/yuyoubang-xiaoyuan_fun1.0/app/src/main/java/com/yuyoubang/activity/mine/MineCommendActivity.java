package com.yuyoubang.activity.mine;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.activity.mine.commend.CommendFragment;
import com.yuyoubang.activity.mine.commend.NoCommendFragment;
import com.yuyoubang.activity.mine.order.AllOrderFragment;
import com.yuyoubang.activity.mine.order.CancelFragment;
import com.yuyoubang.activity.mine.order.CompleteFragment;
import com.yuyoubang.activity.mine.order.NoPayFragment;
import com.yuyoubang.activity.mine.order.YesPayFragment;
import com.yuyoubang.listener.OnClickListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hongchen on 16/12/2.
 */

public class MineCommendActivity extends BaseActivity {

    @Bind(R.id.my_order_tablayout)
    TabLayout myOrderTablayout;
    @Bind(R.id.my_order_vp)
    ViewPager myOrderVp;
    private FragmentPagerAdapter adapter;


    @Override
    protected int getContentResId() {
        return R.layout.act_mine_commend;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("我的评价");
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
        myOrderTablayout.getTabAt(0).setText("待评价");
        myOrderTablayout.getTabAt(1).setText("已评价");
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(NoCommendFragment.newInstance());
        fragments.add(CommendFragment.newInstance());
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
