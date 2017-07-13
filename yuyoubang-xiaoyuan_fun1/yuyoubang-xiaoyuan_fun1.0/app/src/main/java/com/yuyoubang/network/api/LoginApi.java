package com.yuyoubang.network.api;

import com.yuyoubang.bean.LoginUserBean;
import com.yuyoubang.bean.Rigster;
import com.yuyoubang.bean.RigsterUserBean;
import com.yuyoubang.bean.SetPwd;
import com.yuyoubang.config.UrlConfig;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * date: Created xiaoyuan on 16/11/05.
 */
public interface LoginApi {
    //用户通过账号跟密码
//    @Headers(HttpClient.CACHE_CONTROL_AGE + HttpClient.CACHE_SHORT)
    @POST(UrlConfig.USER_LOGIN_BY_PHONE_PASSWORD)
    Call<LoginUserBean> login(@Body FormBody body);
    //获取验证码
//    @Headers(HttpClient.CACHE_CONTROL_AGE + HttpClient.CACHE_SHORT)
    @POST(UrlConfig.USER_GET_VERIFY_CODE)
    Call<Object> get_code(@Body FormBody body);
    //用户验证验证码并注册
//    @Headers(HttpClient.CACHE_CONTROL_AGE + HttpClient.CACHE_SHORT)
    @POST(UrlConfig.USER_CREATE_BY_PHONE)
    Call<Rigster> verificationCode(@Body FormBody body);
    //用户更新密码
//    @Headers(HttpClient.CACHE_CONTROL_AGE + HttpClient.CACHE_SHORT)
    @POST(UrlConfig.USER_UPDATE_PASSWORD)
    Call<Object> setPwd(@Body FormBody body);
    //用户设置或更新用户资料
//    @Headers(HttpClient.CACHE_CONTROL_AGE + HttpClient.CACHE_SHORT)
    @POST(UrlConfig.USER_INFO_UPDATE)
    Call<RigsterUserBean> setUserInfo(@Body FormBody body);
    //用户设置密码
//    @Headers(HttpClient.CACHE_CONTROL_AGE + HttpClient.CACHE_SHORT)
    @POST(UrlConfig.USER_SET_PASSWORD)
    Call<SetPwd> setPassWord(@Body FormBody body);


    //修改手机号
    @POST(UrlConfig.UPDATE_PHONE)
    Call<Rigster> update_psw(@Body FormBody body);

    //修改手机号
    @POST(UrlConfig.VERIFY_CODE)
    Call<Rigster> verify_code(@Body FormBody body);
}
