package com.yuyoubang.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.fragment.home.ApplyUserFragment;
import com.yuyoubang.fragment.home.LikeUserFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiaoyuan on 16/11/14.
 */
public class ApplyAndLikeActivity extends BaseActivity {
    @Bind(R.id.apply_tablayout)
    TabLayout applyTablayout;
    @Bind(R.id.apply_vp)
    ViewPager applyVp;
    private FragmentPagerAdapter adapter;
    private String trip_id;
    private int flag;


    public static void start(Context context, String trip_id, int flag) {
        Intent intent = new Intent(context, ApplyAndLikeActivity.class);
        intent.putExtra("trip_id", trip_id);
        intent.putExtra("flag", flag);
        context.startActivity(intent);

    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("报名人数");
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_apply_and_like;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initView();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void initView() {
        flag = getIntent().getIntExtra("flag", -1);
        trip_id = getIntent().getExtras().getString("trip_id");
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                /*switch (flag) {
                    case 1:
                        applyVp.setCurrentItem(0);
                        return ApplyUserFragment.newInstance(trip_id);
                    case 2:
                        applyVp.setCurrentItem(1);
                        return LikeUserFragment.newInstance(trip_id);
                }*/
                return getFragments().get(position);
            }

            @Override
            public int getCount() {
                return getFragments().size();
            }
        };
        applyVp.setAdapter(adapter);
        applyTablayout.setTabMode(TabLayout.MODE_FIXED);
        initTabLine();
    }

    private void initTabLine() {
        applyTablayout.setupWithViewPager(applyVp);
        applyTablayout.getTabAt(0).setText("报名用户");
        applyTablayout.getTabAt(1).setText("喜欢用户");
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(ApplyUserFragment.newInstance(trip_id));
        fragments.add(LikeUserFragment.newInstance(trip_id));
        return fragments;
    }
}
