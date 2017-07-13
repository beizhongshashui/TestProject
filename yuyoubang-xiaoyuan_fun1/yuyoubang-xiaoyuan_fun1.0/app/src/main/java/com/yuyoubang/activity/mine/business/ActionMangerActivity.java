package com.yuyoubang.activity.mine.business;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.activity.mine.business.action.AllActionFragment;
import com.yuyoubang.activity.mine.business.action.DelActionFragment;
import com.yuyoubang.activity.mine.business.action.OfflineActionFragment;
import com.yuyoubang.activity.mine.business.action.OnlineActionFragment;
import com.yuyoubang.activity.mine.business.action.UnonlineActionFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiaoyuan on 16/12/1.
 */
public class ActionMangerActivity extends BaseActivity {

    @Bind(R.id.act_tablayout)
    TabLayout actTablayout;
    @Bind(R.id.act_vp)
    ViewPager actVp;
    private FragmentPagerAdapter adapter;

    public static void start(Context context) {
        Intent intent = new Intent(context, ActionMangerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("活动路线管理");


    }

    @Override
    protected int getContentResId() {
        return R.layout.act_act_manger;
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
        actVp.setAdapter(adapter);
        actTablayout.setTabMode(TabLayout.MODE_FIXED);
        initTabLine();
    }

    private void initTabLine() {
        actTablayout.setupWithViewPager(actVp);
        actTablayout.getTabAt(0).setText("全部");
        actTablayout.getTabAt(1).setText("待审核");
        actTablayout.getTabAt(2).setText("已上线");
        actTablayout.getTabAt(3).setText("已下线");
        actTablayout.getTabAt(4).setText("已删除");//by  xiaoyuan
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(AllActionFragment.newInstance());
        fragments.add(UnonlineActionFragment.newInstance());
        fragments.add(OnlineActionFragment.newInstance());
        fragments.add(OfflineActionFragment.newInstance());
        fragments.add(DelActionFragment.newInstance());  //by  xiaoyuan
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
