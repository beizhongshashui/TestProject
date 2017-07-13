package com.yuyoubang.loading;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;
import com.yuyoubang.manager.ImageOption;

/**
 * Created by xiaoyuan on 16/11/22.
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage((String) path, imageView, ImageOption.default_banner_options);
    }

}
