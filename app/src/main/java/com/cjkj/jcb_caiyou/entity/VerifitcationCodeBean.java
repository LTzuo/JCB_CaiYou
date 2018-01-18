package com.cjkj.jcb_caiyou.entity;

import java.io.Serializable;

/**
 * Created by 1 on 2018/1/17.
 * 获取验证码模型
 */
public class VerifitcationCodeBean implements Serializable {

    private String result;
    private String resultTxt;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultTxt() {
        return resultTxt;
    }

    public void setResultTxt(String resultTxt) {
        this.resultTxt = resultTxt;
    }
}
