package com.yuyoubang.fragment.home;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.yuyoubang.R;
import com.yuyoubang.activity.home.InfoActivity;
import com.yuyoubang.bean.BizCommentValue;
import com.yuyoubang.fragment.base.BaseNetFragment;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.HomeApi;
import com.yuyoubang.utils.QLog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import retrofit2.Call;

/**
 * Created by xiaoyuan on 16/11/6.
 */

public class ActionEvaluateFragment extends BaseNetFragment<BizCommentValue> {
    @Bind(R.id.pb_five)
    RoundCornerProgressBar pbFive;
    @Bind(R.id.pb_four)
    RoundCornerProgressBar pbFour;
    @Bind(R.id.pb_three)
    RoundCornerProgressBar pbThree;
    @Bind(R.id.pb_two)
    RoundCornerProgressBar pbTwo;
    @Bind(R.id.pb_one)
    RoundCornerProgressBar pbOne;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.vp_evaluate)
    ViewPager vpEvaluate;
    @Bind(R.id.comment_avg_value)
    TextView commentAvgValue;
    @Bind(R.id.comment_count)
    TextView commentCount;
    @Bind(R.id.comment_rb_count)
    RatingBar commentRbCount;
    @Bind(R.id.pb_five_percentage)
    TextView pbFivePercentage;
    @Bind(R.id.pb_four_percentage)
    TextView pbFourPercentage;
    @Bind(R.id.pb_three_percentage)
    TextView pbThreePercentage;
    @Bind(R.id.pb_two_percentage)
    TextView pbTwoPercentage;
    @Bind(R.id.pb_one_percentage)
    TextView pbOnePercentage;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<>();


//    @Bind(R.id.recylerview)
//    RecyclerView recylerview;
//    private NewAdapter adapter;

    private InfoActivity infoActivity;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        infoActivity = (InfoActivity) activity;
    }

    public static ActionEvaluateFragment newInstance() {
        ActionEvaluateFragment fragment = new ActionEvaluateFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        goneLoading();
    }

    @Override
    protected void initViews() {
        mFragments.add(EvaluateCountFragment.newInstance(infoActivity.biz_uid, "0"));
        mFragments.add(EvaluateCountFragment.newInstance(infoActivity.biz_uid, "1"));
        mFragments.add(EvaluateCountFragment.newInstance(infoActivity.biz_uid, "2"));
        mFragments.add(EvaluateCountFragment.newInstance(infoActivity.biz_uid, "3"));
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
        vpEvaluate.setAdapter(mAdapter);
        initTabLine();

        if (commentRbCount != null) {
            LayerDrawable layerDrawable = (LayerDrawable) commentRbCount.getProgressDrawable();
            layerDrawable.getDrawable(2).setColorFilter(Color.parseColor("#ff9600"), PorterDuff.Mode.SRC_ATOP);
        }
    }

    @Override
    protected void loadData() {
        HomeApi homeApi = RetrofitManager.getTestRetrofit().create(HomeApi.class);
        if (!TextUtils.isEmpty(infoActivity.biz_uid)) {
            FormBody body = new FormBody.Builder().add("biz_uid", infoActivity.biz_uid).build();
            Call<BizCommentValue> beanCall = homeApi.biz_user_comment_value_show(body);
            beanCall.enqueue(this);
        }
    }

    @Override
    protected void processData(BizCommentValue o) {
        pbOne.setMax(Float.parseFloat(o.getResult().getBiz_user_comment_total_count()));
        pbTwo.setMax(Float.parseFloat(o.getResult().getBiz_user_comment_total_count()));
        pbThree.setMax(Float.parseFloat(o.getResult().getBiz_user_comment_total_count()));
        pbFour.setMax(Float.parseFloat(o.getResult().getBiz_user_comment_total_count()));
        pbFive.setMax(Float.parseFloat(o.getResult().getBiz_user_comment_total_count()));

        pbOne.setProgress(Float.parseFloat(o.getResult().getBiz_user_1_comment_count()));
        pbTwo.setProgress(Float.parseFloat(o.getResult().getBiz_user_2_comment_count()));
        pbThree.setProgress(Float.parseFloat(o.getResult().getBiz_user_3_comment_count()));
        pbFour.setProgress(Float.parseFloat(o.getResult().getBiz_user_4_comment_count()));
        pbFive.setProgress(Float.parseFloat(o.getResult().getBiz_user_5_comment_count()));

        DecimalFormat decimalFormat = new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String p = decimalFormat.format(Float.parseFloat(o.getResult().getBiz_user_comment_avg_value()));//format 返回的是字符串
        if (p.equals(".00")) {
            commentAvgValue.setText("0.00分");
        } else {
            commentAvgValue.setText(p + "分");
        }

        commentCount.setText(Integer.parseInt(o.getResult().getBiz_user_comment_total_count()) + "人评论");
        commentRbCount.setRating(avga(o));
//
//        float all = Float.parseFloat(o.getResult().getBiz_user_1_comment_count()) + Float.parseFloat(o.getResult().getBiz_user_2_comment_count())
//                + Float.parseFloat(o.getResult().getBiz_user_3_comment_count())
//                + Float.parseFloat(o.getResult().getBiz_user_4_comment_count())
//                + Float.parseFloat(o.getResult().getBiz_user_1_comment_count());
        int all = Integer.parseInt(o.getResult().getBiz_user_comment_total_count());
        Float i5 = Float.parseFloat(o.getResult().getBiz_user_5_comment_count()) / all * 100;
        Float i4 = Float.parseFloat(o.getResult().getBiz_user_4_comment_count()) / all * 100;
        Float i3 = Float.parseFloat(o.getResult().getBiz_user_3_comment_count()) / all * 100;
        Float i2 = Float.parseFloat(o.getResult().getBiz_user_2_comment_count()) / all * 100;
        Float i1 = Float.parseFloat(o.getResult().getBiz_user_1_comment_count()) / all * 100;
        QLog.d("xiaoyuan", "i5=" + i5 + "i4=" + i4 + "i3=" + i3 + "i2" + i2 + "i1" + i1);
        pbFivePercentage.setText(Math.round(i5) + "%");
        pbFourPercentage.setText(Math.round(i4) + "%");
        pbThreePercentage.setText(Math.round(i3) + "%");
        pbTwoPercentage.setText(Math.round(i2) + "%");
        pbOnePercentage.setText(Math.round(i1) + "%");
    }


    private float avga(BizCommentValue o) {
//        float all = Float.parseFloat(o.getResult().getBiz_user_1_comment_count()) + Float.parseFloat(o.getResult().getBiz_user_2_comment_count())
//                + Float.parseFloat(o.getResult().getBiz_user_3_comment_count())
//                + Float.parseFloat(o.getResult().getBiz_user_4_comment_count())
//                + Float.parseFloat(o.getResult().getBiz_user_1_comment_count());
        int all = Integer.parseInt(o.getResult().getBiz_user_comment_total_count());
        float one = Float.parseFloat(o.getResult().getBiz_user_1_comment_count()) * 1;
        float two = Float.parseFloat(o.getResult().getBiz_user_2_comment_count()) * 2;
        float three = Float.parseFloat(o.getResult().getBiz_user_3_comment_count()) * 3;
        float four = Float.parseFloat(o.getResult().getBiz_user_4_comment_count()) * 4;
        float five = Float.parseFloat(o.getResult().getBiz_user_5_comment_count()) * 5;
        float f = (one + two + three + four + five) / all;
        return f;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        recylerview.setHasFixedSize(true);
//        recylerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//        recylerview.setAdapter(adapter = new NewAdapter());
//        adapter.replaceAll(getData());

    }


    private void initTabLine() {
        tabLayout.setupWithViewPager(vpEvaluate);
        tabLayout.getTabAt(0).setText("全部");
        tabLayout.getTabAt(1).setText("满意");
        tabLayout.getTabAt(2).setText("一般");
        tabLayout.getTabAt(3).setText("不满意");
    }

    @Override
    protected int getContentResId() {
        return R.layout.frg_action_evaluate;
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
