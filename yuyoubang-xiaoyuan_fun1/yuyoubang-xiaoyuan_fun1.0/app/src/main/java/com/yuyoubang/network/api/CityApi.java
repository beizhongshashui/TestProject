package com.yuyoubang.network.api;

import com.yuyoubang.bean.City;
import com.yuyoubang.bean.HotCityBean;
import com.yuyoubang.config.UrlConfig;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by xiaoyuan on 16/12/2.
 */
public interface CityApi {
    //获取热门城市
    @POST(UrlConfig.SYSTEM_HOT_CITIES_LIST)
    Call<HotCityBean> SYSTEM_HOT_CITIES_LIST(@Body FormBody body);

    //所有省份列表
    @POST(UrlConfig.ALL_LOCATION)
    Call<City> all_location(@Body FormBody body);

    //通过省份获得城市列表
    @POST(UrlConfig.FOUND_CITY)
    Call<City> found_city(@Body FormBody body);
}
