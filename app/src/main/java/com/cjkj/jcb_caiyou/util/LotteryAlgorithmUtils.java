package com.cjkj.jcb_caiyou.util;

import java.math.BigDecimal;

/**
 * Created by 1 on 2018/1/26.
 * 彩票算法公式
 * author ：林亮
 */
public class LotteryAlgorithmUtils {

    //彩票单价
    private static final long UnitPrice = 2;

    /**
     * 计算注数
     * @param redballCount
     * @param redstandCount
     * @param blueballCount
     * @param blueStandCount
     */
    public static String Calculation(int redballCount,int redstandCount,int blueballCount,int blueStandCount ){
       return mul(getCountNumForBalls(redballCount,redstandCount),getCountNumForBalls(blueballCount,blueStandCount));
    }

    /**
     * 提供精确的乘法运算。
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static  String mul(long v1,long v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).toString();
    }

    /**
     * 获取组合可能性
     * @param ballCount 球数
     * @param standCount 标准数
     * @return
     */
    private static long getCountNumForBalls(int ballCount,int standCount){
        long a=ballCount;
        long b=1;
        for(int i=1;i<standCount;i++){
            a=a*(--ballCount);
            b=b*(i+1);
        }
        return a/b;
    }

    /**
     * 获取排列可能性
     * @param ballCount
     * @param i
     * @return
     */
    public static long getCountNumForAllBalls(int ballCount, int CotSum)
    {
        long sum=1;
        for(int i=0;i<CotSum;i++){
            sum=sum*ballCount;
            ballCount--;
        }
        return sum;
    }

    /**
     * 随机指定范围内N个不重复的数
     * 最简单最基本的方法
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param n 随机数个数
     */
    public static int[] randomCommon(int min, int max, int n){
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while(count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if(num == result[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result[count] = num;
                count++;
            }
        }
       //冒泡排序
        for (int k = 0; k < result.length - 1; k++) {
            for (int j = k + 1; j < result.length; j++) { // 升序把<改成>
                 if (result[k] > result[j]) {
                    int temp = result[k];
                    result[k] = result[j];
                    result[j] = temp;
                }
            }
        }
        return result;
    }

}
