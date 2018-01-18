package com.cjkj.jcb_caiyou.entity;

import java.io.Serializable;

/**
 * Created by 1 on 2018/1/17.
 * 获取验证码模型
 */
public class VerifitcationCodeEntity implements Serializable {

    private int result;
    private String resultTxt;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getResultTxt() {
        return resultTxt;
    }

    public void setResultTxt(String resultTxt) {
        this.resultTxt = resultTxt;
    }
}
