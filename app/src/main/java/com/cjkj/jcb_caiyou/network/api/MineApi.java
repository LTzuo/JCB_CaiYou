package com.cjkj.jcb_caiyou.network.api;

import com.cjkj.jcb_caiyou.entity.VerifitcationCodeEntity;
import com.cjkj.jcb_caiyou.network.ApiConstants;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import rx.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 1 on 2018/1/17.
 * 聚彩宝-api相关
 */
public interface MineApi {

    //获取验证码
    @GET(ApiConstants.BASEURL+"getcode.jspx?")
    Observable<JsonObject> getVerificationCode(@Query("destAddr") String destAddr);

    //用户注册
    @GET(ApiConstants.BASEURL+"")
    Observable<JsonObject> userRegist(@Query("destAddr") String destAddr,
                                  @Query("token") String token,
                                          @Query("code") String code,
                                          @Query("province") String province,
                                          @Query("city") String city);
}
