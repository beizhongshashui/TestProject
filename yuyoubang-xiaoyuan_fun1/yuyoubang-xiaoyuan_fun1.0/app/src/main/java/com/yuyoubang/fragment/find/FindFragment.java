package com.yuyoubang.fragment.find;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yuyoubang.R;
import com.yuyoubang.fragment.base.BaseNetFragment;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiaoyuanxx
 */

public class FindFragment extends BaseNetFragment<Object> {


    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.find_vp)
    ViewPager findVp;
    @Bind(R.id.iv_sel)
    ImageView iv_sel;

    private FindFollowFragment findFollowFragment;
    private FindHotFragment findHotFragment;
    private FindCityFragment findCityFragment;
    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;

    public static FindFragment newInstance() {
        FindFragment fragment = new FindFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        goneLoading();
        setListener();
    }

    private void setListener() {
        iv_sel.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//筛选
                LaunchOperate.openChooseContentActivity(getActivity());
            }
        });
    }

    @Override
    protected void initViews() {
        if (findFollowFragment == null) {
            findFollowFragment = FindFollowFragment.newInstance();
            mFragments.add(findFollowFragment);
        }
        if (findHotFragment == null) {
            findHotFragment = FindHotFragment.newInstance();
            mFragments.add(findHotFragment);
        }
        if (findCityFragment == null) {
            findCityFragment = FindCityFragment.newInstance();
            mFragments.add(findCityFragment);
        }

        mAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };
        findVp.setAdapter(mAdapter);
        initTabLine();

    }


    private void initTabLine() {
        tabLayout.setupWithViewPager(findVp);
        tabLayout.getTabAt(0).setText("关注");
        tabLayout.getTabAt(1).setText("热门");
        tabLayout.getTabAt(2).setText("同城");
    }

    @Override
    protected void loadData() {
//        TestApi testApi = RetrofitManager.getTestRetrofit().create(TestApi.class);
//        Call<Object> testCall = testApi.test();
//        testCall.enqueue(this);
    }

    @Override
    protected void processData(Object o) {

    }


    @Override
    protected int getContentResId() {
        return R.layout.frg_find;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
