package com.cjkj.jcb_caiyou.entity.lottery.SSQ;

/**
 * 双色球实体
 * Created by 1 on 2018/1/29.
 */
public class SSQEntity {

    private String redBall; //红球数组

    private String buleBall; //篮球数组

    private String zhuCount;   //注数

    private String money;     //金额

    public SSQEntity(){

    }

    public SSQEntity(String redBall,String buleBall,String zhuCount,String money){
        this.redBall = redBall;
        this.buleBall = buleBall;
        this.zhuCount = zhuCount;
        this.money = money;
    }

    public String getRedBall() {
        return redBall;
    }

    public void setRedBall(String redBall) {
        this.redBall = redBall;
    }

    public String getBuleBall() {
        return buleBall;
    }

    public void setBuleBall(String buleBall) {
        this.buleBall = buleBall;
    }

    public String getZhuCount() {
        return zhuCount;
    }

    public void setZhuCount(String zhuCount) {
        this.zhuCount = zhuCount;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }




}
