package com.cjkj.jcb_caiyou.util;

/**
 * Created by 1 on 2018/1/25.
 */
public class Utils {
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
        return result;
    }

    /**
     * 获取组合可能性
     * @param ballCount 球数
     * @param standCount 标准数
     * @return
     */
    public static long getCountNumForBalls(int ballCount,int standCount){
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

}
