package com.cjkj.jcb_caiyou.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * 加速度传感器实现
 */

public class ShakeSensor implements SensorEventListener {
    private static final String TAG = ShakeSensor.class.getCanonicalName();
    private static final int SPEED_SHAKE = 4500;
    private Context mContext;
    private SensorManager mSensorManager;
    private Sensor mSensor;//加速度传感器

    private float last_x;//记录x轴最后一次的值
    private float last_y;//记录y轴最后一次的值
    private float last_z;//记录z轴最后一次的值
    private long last_time;//记录最后一次时间

    private onShakeListener mOnshakelistener;

    @Override
    public void onSensorChanged(SensorEvent event) {
        long currentTime = System.currentTimeMillis();

        if (currentTime - last_time > 45) {
            //两次摇动的时间的间隔
            long timeDistance = currentTime - last_time;
            last_time = currentTime;

            //当前的值
            float x = event.values[0];//x轴变化的值
            float y = event.values[1];//y轴变化的值
            float z = event.values[2];//z轴变化的值
            Log.e(TAG, "x轴变化的值:" + ",y轴变化的值:" + y + ",z轴变化的值:" + z);

            //速度的阀值
            double speed;

            double absvalue = Math.abs(x + y + z - last_x - last_y - last_z);
            speed = absvalue / timeDistance * 10000;
            if (speed > SPEED_SHAKE) {
                //当x,y,z达到一定值进行后续操作
                if (mOnshakelistener != null) {
                    mOnshakelistener.onShake();
                }
            }
            last_x = x;
            last_y = y;
            last_z = z;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public ShakeSensor(Context context) {
        mContext = context;
        // init();
    }

    public ShakeSensor() {
    }

    public void init() {
        mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //注册传感器
        mSensorManager.registerListener(this, mSensor, Sensor.TYPE_ACCELEROMETER);
    }

    /**
     * 取消注册
     */

    public void unRegisterListener() {
        mSensorManager.unregisterListener(this, mSensor);
    }

    public void setOnShakeListener(onShakeListener onshakelistener) {
        mOnshakelistener = onshakelistener;
    }

    public interface onShakeListener {
        void onShake();
    }
}
