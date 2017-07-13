package com.yuyoubang.network.api;


import com.yuyoubang.bean.LoginUserBean;
import com.yuyoubang.config.UrlConfig;
import com.yuyoubang.network.HttpClient;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;


/**
 * date: Created hongchen on 16/11/05.
 */
public interface TestApi {

    @Headers(HttpClient.CACHE_CONTROL_AGE + HttpClient.CACHE_SHORT)
    @POST(UrlConfig.USER_LOGIN_BY_PHONE_PASSWORD)
    Call<LoginUserBean> test();

}
