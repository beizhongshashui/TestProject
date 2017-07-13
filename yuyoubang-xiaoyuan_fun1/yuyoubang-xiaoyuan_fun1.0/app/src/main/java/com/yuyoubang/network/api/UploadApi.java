package com.yuyoubang.network.api;

import com.yuyoubang.bean.UploadBean;
import com.yuyoubang.config.UrlConfig;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * date: Created xiaoyuan on 16/11/05.
 */
public interface UploadApi {
    //上传文件
    @Multipart
    @POST(UrlConfig.UTIL_FILE_UPLOAD)
    Call<UploadBean> upload(@Part MultipartBody.Part filePart);




}
