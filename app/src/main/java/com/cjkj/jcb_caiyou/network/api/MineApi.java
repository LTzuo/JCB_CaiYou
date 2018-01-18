package com.cjkj.jcb_caiyou.network.api;

import com.cjkj.jcb_caiyou.entity.VerifitcationCodeBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 1 on 2018/1/17.
 * 聚彩宝-api相关
 */
public interface MineApi {

    //获取验证码
    @GET("getcode.jspx?")
    Call<VerifitcationCodeBean> getVerificationCode(@Query("destAddr") String destAddr);


}
