package com.yuyoubang.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.utils.BitmapUtil;
import com.yuyoubang.utils.QLog;
import com.yuyoubang.view.ClipSquareImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class ClipImaviewActivity extends BaseActivity {
    private final static String TAG = ClipImaviewActivity.class.getSimpleName();
    private ClipSquareImageView imageView;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUiAndListener();

    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.goneToolbar();

    }


    @Override
    protected int getContentResId() {
        return R.layout.act_clip_img;
    }


    public void initUiAndListener() {
        onShowProgressDlg();
        String path = getIntent().getExtras().getString("path");
        int angle = getExifOrientation(path);
        bmp = BitmapUtil.decodeSampledBitmap(path, 600, 800);
        if (angle != 0) {
            // 下面的方法主要作用是把图片转一个角度，也可以放大缩小等
            Matrix m = new Matrix();
            int width = bmp.getWidth();
            int height = bmp.getHeight();
            m.setRotate(angle); // 旋转angle度
            bmp = Bitmap.createBitmap(bmp, 0, 0, width, height,
                    m, true);// 从新生成图片

        }
//        bmp = BitmapFactory.decodeFile(path);
        imageView = (ClipSquareImageView) findViewById(R.id.clipSquareIV);
        imageView.setImageBitmap(bmp);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.setBorderWeight(16, 16);
                cancelProgressDlg();

            }
        }, 2000);

        findViewById(R.id.doneBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 此处获取剪裁后的bitmap
                Bitmap bitmap = imageView.clip();
                QLog.d("ClipImaviewActivity", bitmap.getByteCount() + "");
                if (bitmap != null) {
                    Intent intent = new Intent();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, baos);
                    byte[] bitmapByte = baos.toByteArray();
                    intent.putExtra("bitmap", bitmapByte);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                    if (bmp != null) {
                        bmp.recycle();
                    }
                }
//                // 由于Intent传递bitmap不能超过40k,此处使用二进制数组传递


            }

        });
        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                bmp.recycle();
            }
        });

    }


    private Bitmap getBitmapFromUrl(String url, double width, double height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 设置了此属性一定要记得将值设置为false
        Bitmap bitmap = BitmapFactory.decodeFile(url);
        // 防止OOM发生
        options.inJustDecodeBounds = false;
        int mWidth = bitmap.getWidth();
        int mHeight = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = 1;
        float scaleHeight = 1;
//        try {
//            ExifInterface exif = new ExifInterface(url);
//            String model = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        // 按照固定宽高进行缩放
        // 这里希望知道照片是横屏拍摄还是竖屏拍摄
        // 因为两种方式宽高不同，缩放效果就会不同
        // 这里用了比较笨的方式
        if (mWidth <= mHeight) {
            scaleWidth = (float) (width / mWidth);
            scaleHeight = (float) (height / mHeight);
        } else {
            scaleWidth = (float) (height / mWidth);
            scaleHeight = (float) (width / mHeight);
        }
//        matrix.postRotate(90); /* 翻转90度 */
        // 按照固定大小对图片进行缩放
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, mWidth, mHeight, matrix, true);
        // 用完了记得回收
        bitmap.recycle();
        return newBitmap;
    }


    public static int getExifOrientation(String filepath) {
        int degree = 0;
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(filepath);
        } catch (IOException ex) {
            QLog.d(TAG, "cannot read exif" + ex);
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


}
