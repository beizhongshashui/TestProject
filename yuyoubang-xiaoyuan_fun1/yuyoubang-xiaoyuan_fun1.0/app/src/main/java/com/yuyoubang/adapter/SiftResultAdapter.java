package com.yuyoubang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;
import com.yuyoubang.R;
import com.yuyoubang.bean.SiftResultBean;
import com.yuyoubang.loading.GlideImageLoader;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.utils.DateUtils;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.base.MultiBaseAdapter;

import java.util.List;

/**
 * @author xiaoyuan
 */

public class SiftResultAdapter extends MultiBaseAdapter<SiftResultBean.ResultBean.ListBean> {
    private Context context;
    private int currentIndex;
    private List<String> strings;

    public SiftResultAdapter(Context context, List<SiftResultBean.ResultBean.ListBean> listBean, boolean isOpenLoadMore) {
        super(context, listBean, isOpenLoadMore);
        this.context = context;
        setHaed(false);//无头部
    }

    public void setHeadData(List<String> data) {
        this.strings = data;
    }

    @Override
    protected int getViewType(int position, SiftResultBean.ResultBean.ListBean data) {
        if (data == null) {
            return 0;
        } else {
            return 1;
        }

    }


    @Override
    protected void convert(ViewHolder holder, SiftResultBean.ResultBean.ListBean data, int viewType) {
        if (viewType == 0) {
            Banner banner = holder.getView(R.id.banner);
            setBanner(banner);
        } else {
            ImageView imageView = holder.getView(R.id.iv_pic);
            TextView tv_company = holder.getView(R.id.tv_company);

            ImageLoader.getInstance().displayImage(data.getData().getTrip_cover_pic(), imageView, ImageOption.defaultOptions);
            holder.setText(R.id.tv_title, data.getData().getTrip_name());
            holder.setText(R.id.tv_time, DateUtils.getDateToMonth(Long.valueOf(data.getData().getStart_time())) + "一" + DateUtils.getDateToMonth(Long.valueOf(data.getData().getEnd_time())));
            holder.setText(R.id.tv_price, data.getData().getTrip_price() + "");
            holder.setText(R.id.tv_apply, data.getData().getParticipants_female_count() + data.getData().getParticipants_male_count() + "人报名");
            if (data.getUser_result() != null) {
                holder.setText(R.id.tv_biz_user_name, data.getUser_result().getUser_data().getUser_name());
                holder.setText(R.id.tv_days, "畅游" + data.getData().getTrip_days() + "天");
            }
            LinearLayout llLabel = holder.getView(R.id.ll_label);
            llLabel.removeAllViews();
            if (data.getData() != null) {
                if (data.getData().getTrip_tags() != null) {
                    for (int i = 0; i < data.getData().getTrip_tags().size(); i++) {
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(10, 0, 0, 0);
                        TextView textView = new TextView(context);
                        textView.setText(data.getData().getTrip_tags().get(i));
                        textView.setTextColor(context.getResources().getColor(R.color.color_ff9600));
                        textView.setBackgroundResource(R.drawable.yellow_circle);
                        llLabel.addView(textView, params);
                    }
                }
            }

            if (data.getUser_result() != null) {
                if (data.getUser_result().getUser_data() != null) {
                    if (data.getUser_result().getUser_data().getVerification_type() != null) {
                        if (Integer.parseInt(data.getUser_result().getUser_data().getVerification_type()) == 2) {
                            tv_company.setVisibility(View.VISIBLE);
                        } else {
                            tv_company.setVisibility(View.GONE);
                        }
                    } else {
                        tv_company.setVisibility(View.GONE);
                    }
                }
            }
        }
    }


    @Override
    protected int getItemLayoutId(int viewType) {
        if (viewType == 0) {
            return R.layout.item_article_rotations;
        }
        return R.layout.home_item;
    }


    private void setBanner(Banner banner) {
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(strings);
        //设置banner动画效果
//        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(Arrays.asList(titles));
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtils.showLong("点击了" + position);
            }
        });

    }
}
