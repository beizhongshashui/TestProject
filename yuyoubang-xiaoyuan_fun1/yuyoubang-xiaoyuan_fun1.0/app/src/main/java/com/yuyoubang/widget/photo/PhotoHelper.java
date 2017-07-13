package com.yuyoubang.widget.photo;

import android.content.Context;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 2016/3/4
 */
public class PhotoHelper {

    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static Context getContext() {
        return mContext;
    }


}
