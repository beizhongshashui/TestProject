package com.yuyoubang.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.widget.scanimg.HackyViewPager;
import com.yuyoubang.widget.scanimg.PhotoView;
import com.yuyoubang.widget.scanimg.PhotoViewAttacher;

import java.util.List;


/**
 * 图片浏览
 */
public class ScanImgsActivity extends BaseActivity {
    private ViewPager mViewPager;
    private SamplePagerAdapter mPagerAdapter;
    private TextView mImagePager;

    private List<String> imgurls;
    private int pos;//当前位置

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();

        mPagerAdapter = new SamplePagerAdapter(imgurls);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(pos);

        mImagePager.setText(String.format("%d / %d", pos + 1, mPagerAdapter.getCount()));

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int status) {
            }

            @Override
            public void onPageScrolled(int position, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int position) {
                pos = position;
                mImagePager.setText(String.format("%d / %d", pos + 1, mPagerAdapter.getCount()));
            }

        });
    }

    @Override
    protected int getContentResId() {
        return R.layout.activity_scanimgs_view;
    }

    void initViews() {
//		imgurls = new ArrayList<>();
//		String imgurl = getIntent().getExtras().getString("imgurl");
        imgurls = (List<String>) getIntent().getSerializableExtra("imgurl");
//		imgurls.add(imgurl);
        pos = getIntent().getExtras().getInt("pos");
        mImagePager = getViewById(R.id.img_pager);
        LinearLayout layout = getViewById(R.id.imgsArea);

        TextView tvBack = getViewById(R.id.tvBack);
        tvBack.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                finish();
            }
        });
        mViewPager = new HackyViewPager(this);
        layout.addView(mViewPager, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("图片");
    }


    class SamplePagerAdapter extends PagerAdapter {
        private List<String> mImageList;
        private PhotoView mImage;

        public SamplePagerAdapter(List<String> imgUrls) {
            this.mImageList = imgUrls;
        }

        @Override
        public int getCount() {
            return mImageList.size();
        }

        public String getImageUrl(int position) {
            return mImageList.get(position);
        }

        public PhotoView getImageView() {
            return mImage;
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            //PhotoView
            mImage = new PhotoView(container.getContext());
            container.addView(mImage, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

            mImage.setBackgroundColor(getResources().getColor(R.color.color_ffffff));
            mImage.setAlpha(0.9f);
            mImage.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    finish();
                }
            });
            String img = mImageList.get(position);
            if (!TextUtils.isEmpty(img)) {
                ImageLoader.getInstance().displayImage(img, mImage, ImageOption.default_trip_options);//图片显示
            }
            return mImage;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
