package com.yuyoubang.activity.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.activity.mine.commend.CommendFragment;
import com.yuyoubang.activity.mine.commend.NoCommendFragment;
import com.yuyoubang.listener.OnClickListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hongchen on 16/12/18.
 */

public class SearchResultActivity extends BaseActivity {

    @Bind(R.id.my_order_tablayout)
    TabLayout myOrderTablayout;
    @Bind(R.id.my_order_vp)
    ViewPager myOrderVp;
    private FragmentPagerAdapter adapter;
    private String content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        content = getIntent().getStringExtra("content");
        builder.setTitle(content);
        initView();
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_search_result;
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
        myOrderTablayout.getTabAt(0).setText("活动");
        myOrderTablayout.getTabAt(1).setText("用户");
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(SearchActFragment.newInstance(content));
        fragments.add(SearchUserFragment.newInstance(content));
        return fragments;
    }

    private HeaderBuilder builder;

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        this.builder = builder;
        builder.setBackGround(Color.WHITE);
        builder.setTitleColor(Color.BLACK);
        builder.setLeftOperate(R.mipmap.back_black, new OnClickListener() {
            @Override
            protected void clickOperate() {
                finish();
            }
        });
    }
}
