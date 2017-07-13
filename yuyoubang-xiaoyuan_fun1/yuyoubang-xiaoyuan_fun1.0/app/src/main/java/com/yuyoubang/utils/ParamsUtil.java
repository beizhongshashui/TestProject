package com.yuyoubang.utils;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;

/**
 * Created by xiaoyuan on 16/12/21.
 */
public class ParamsUtil {

    public static FormBody.Builder filterParams(Map<String, String> params, FormBody.Builder builder) {
        for (String key : params.keySet()) {
            if (!TextUtils.isEmpty(params.get(key))) {
                builder.add(key, params.get(key));
            }
            System.out.println("key= " + key + " and value= " + params.get(key));
        }

        return builder;
    }


    public static Map<String, String> getParams() {
        return new HashMap<>();
    }
}
