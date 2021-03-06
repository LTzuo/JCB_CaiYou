package com.cjkj.jcb_caiyou.entity.lottery.SSQ;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 双色球实体
 * Created by 1 on 2018/1/29.
 */
public class SSQEntity implements Parcelable {

    private String redBall; //红球数组

    private String buleBall; //篮球数组

    private int zhuCount;   //注数

    private int money;     //金额

    public SSQEntity(){
       super();
    }

    public SSQEntity(String redBall,String buleBall,int zhuCount,int money){
        super();
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

    public int getZhuCount() {
        return zhuCount;
    }

    public void setZhuCount(int zhuCount) {
        this.zhuCount = zhuCount;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // 序列化过程：必须按成员变量声明的顺序进行封装
        dest.writeString(redBall);
        dest.writeString(buleBall);
        dest.writeInt(zhuCount);
        dest.writeInt(money);
    }

    public static final Parcelable.Creator<SSQEntity> CREATOR = new Creator<SSQEntity>() {

        @Override
        public SSQEntity createFromParcel(Parcel source) {
            return new SSQEntity(source.readString(), source.readString(),source.readInt(),source.readInt());
        }

        @Override
        public SSQEntity[] newArray(int size) {
            return new SSQEntity[size];
        }
    };
}
