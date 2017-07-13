package com.yuyoubang.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.ExifInterface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yuyoubang.R;
import com.zhy.view.flowlayout.FlowLayout;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author xiaoyuan
 */

public class FlowLayout_AddPicture extends FlowLayout {

    public int mMaxAllowedCount = 9;
    public LinkedHashSet<String> mPictureUrls = new LinkedHashSet<String>();

    public FlowLayout_AddPicture(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.post(new Runnable() {

            @Override
            public void run() {
                addAddView();
            }
        });
    }

    public void setMaxAllowedCount(int maxCount) {
        this.mMaxAllowedCount = maxCount;
    }

    ClickAddPictureListener mClickAddPictureListener;

    public void setOnClickAddPictureListener(
            ClickAddPictureListener mClickAddPictureListener) {
        this.mClickAddPictureListener = mClickAddPictureListener;
    }

    public interface ClickAddPictureListener {
        void addPicture();
    }

    public int getSelectedPictureCount() {
        return mPictureUrls.size();
    }

    public List<String> getSelectedPictures() {

        return new ArrayList<>(mPictureUrls);
    }

    /**
     * 添加一个图片
     *
     * @param path
     */
    public void addPicture(final String path) {
//        if (mPictureUrls.contains(path)) {
//            Toast.makeText(getContext(), "have added!", Toast.LENGTH_SHORT).show();
//            return;
//        }
        this.post(new Runnable() {

            @Override
            public void run() {
                final RelativeLayout relativeLayout = new RelativeLayout(getContext());
                relativeLayout.setTag(path);
                ImageView iv = new ImageView(getContext());
                iv.setScaleType(ScaleType.CENTER_CROP);
                iv.setImageBitmap(getBitmap(path, iv));
                relativeLayout.addView(iv, new LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT));
                ImageView close = new ImageView(getContext());
                close.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        FlowLayout_AddPicture.this.removeView(relativeLayout);
                        mPictureUrls.remove((String) relativeLayout.getTag());
                        if (mPictureUrls.size() == mMaxAllowedCount - 1) {
                            addAddView();
                        }
                    }
                });
                RelativeLayout.LayoutParams lp_close = new RelativeLayout.LayoutParams(
                        getWidth() / 30, getWidth() / 30);
                lp_close.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                lp_close.rightMargin = getWidth() / 120;
                lp_close.topMargin = getWidth() / 150;
                relativeLayout.addView(close, lp_close);
                close.setImageResource(R.mipmap.icon_addpicture_close);
                MarginLayoutParams lp = new MarginLayoutParams(getWidth() / 6,
                        getWidth() / 6);
                lp.leftMargin = getWidth() / 60;
                lp.rightMargin = getWidth() / 60;
                lp.topMargin = getWidth() / 60;
                if (mPictureUrls.size() == mMaxAllowedCount - 1) {
                    FlowLayout_AddPicture.this.removeViewAt(getChildCount() - 1);
                    ;
                }
                FlowLayout_AddPicture.this.addView(relativeLayout, getChildCount() - 1, lp);
                mPictureUrls.add(path);
                FlowLayout_AddPicture.this.forceLayout();
            }
        });

    }

    public int getExifOrientation(String filepath) {
        int degree = 0;
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(filepath);
        } catch (IOException ex) {
        }
        if (exif != null) {
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
            if (orientation != -1) {
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }
            }
        }
        return degree;
    }

    private String getOnePictureSize(String path) {
        if (new File(path).exists()) {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);
            int degree = getExifOrientation(path);
            if (degree == 90 || degree == 270) {
                return options.outHeight + "x️" + options.outWidth;
            } else {
                return options.outWidth + "x️" + options.outHeight;
            }
        } else {
            return "";
        }
    }

    public static int caculateInSampleSize(Options options, int reqWidth,
                                           int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;

        int inSampleSize = 1;

        if (width > reqWidth || height > reqHeight) {
            int widthRadio = Math.round(width * 1.0f / reqWidth);
            int heightRadio = Math.round(height * 1.0f / reqHeight);

            inSampleSize = Math.max(widthRadio, heightRadio);
        }

        return inSampleSize;
    }

    protected Bitmap getBitmap(String path, View view) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inSampleSize = caculateInSampleSize(options,
                view.getWidth() < 100 ? 100 : view.getWidth(), view.getHeight() < 100 ? 100 : view.getHeight());
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    LinearLayout mAddViewContainer;

    /**
     * 加入添加按钮
     */
    private void addAddView() {
        if (this.getChildCount() >= mMaxAllowedCount) {
            return;
        }
        if (mAddViewContainer == null) {
            mAddViewContainer = new LinearLayout(getContext());
            mAddViewContainer.setOrientation(LinearLayout.VERTICAL);
            ImageView iv = new ImageView(getContext());
            MarginLayoutParams lp_iv = new MarginLayoutParams(getWidth() / 6,
                    getWidth() / 6);
            iv.setImageResource(R.mipmap.icon_addpicture_add);
            mAddViewContainer.addView(iv, lp_iv);
            TextView tv = new TextView(getContext());
//            tv.setText(getResources().getString(R.string.add_a_photo));
//            tv.setTextColor(Color.parseColor("#D1D4D6"));
//            tv.setTextSize(10);
            LinearLayout.LayoutParams lp_tv = new LinearLayout.LayoutParams(
                    getWidth() / 6, getWidth() / 24);
            lp_tv.gravity = Gravity.CENTER_HORIZONTAL;
            mAddViewContainer.addView(tv, lp_tv);
            mAddViewContainer.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mClickAddPictureListener != null) {
                        mClickAddPictureListener.addPicture();
                    }
                }
            });
        }
        MarginLayoutParams lp = new MarginLayoutParams(getWidth() / 6,
                getWidth() / 6 + getWidth() / 24);
        lp.leftMargin = getWidth() / 60;
        lp.rightMargin = getWidth() / 60;
        lp.topMargin = getWidth() / 60;
        this.addView(mAddViewContainer, lp);
    }

    public String getImageSizes() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = mPictureUrls.iterator();
        while (iterator.hasNext()) {
            String path = iterator.next();
            if (new File(path).exists()) {
                if (sb.toString().length() > 0) {
                    sb.append(",");
                }
                sb.append(getOnePictureSize(path));
            }
        }
        return sb.toString();
    }
}
