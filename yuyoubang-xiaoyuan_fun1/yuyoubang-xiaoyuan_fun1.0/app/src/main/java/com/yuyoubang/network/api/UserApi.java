package com.yuyoubang.network.api;

import com.yuyoubang.config.UrlConfig;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * date: Created hongchen on 16/11/05.
 */
public interface UserApi {
    //用户认证
    @POST(UrlConfig.USER_PERSON_VERIFICATION_APPLY)
    Call<Object> USER_PERSON_VERIFICATION_APPLY(@Body FormBody body);


}
