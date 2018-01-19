package com.cjkj.jcb_caiyou.network.api;

import com.cjkj.jcb_caiyou.network.ApiConstants;
import com.cjkj.jcb_caiyou.network.RetrofitHelper;
import com.google.gson.JsonObject;

import okhttp3.Cookie;
import retrofit2.Retrofit;
import retrofit2.http.HEAD;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import rx.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 1 on 2018/1/17.
 * 聚彩宝-api相关
 */
public interface MineApi {

    //获取验证码
    @POST(ApiConstants.BASEURL+"getcode.jspx?")
    Observable<JsonObject> getVerificationCode(@Query("destAddr") String value);

    //用户注册/忘记密码公用
    @POST(ApiConstants.BASEURL+"userregister.jspx?")
    Observable<JsonObject> userRegist(@Query("destAddr") String value1,
                                  @Query("token") String value2,
                                          @Query("code") String value3,
                                          @Query("province") String value4,
                                          @Query("city") String value5);

    //用户登录
    @POST(ApiConstants.BASEURL+"useraccnumlogin.jspx?")
    Observable<JsonObject> userLogin(@Query("destAddr") String value1,
                                      @Query("token") String value2,
                                      @Query("province") String value4,
                                      @Query("city") String value5);

    //用户短信登录
    @POST(ApiConstants.BASEURL+"useraccnumlogin.jspx?")
    Observable<JsonObject> userShortMessageLogin(@Query("destAddr") String value1,
                                     @Query("code") String value2,
                                     @Query("province") String value4,
                                     @Query("city") String value5);
}
