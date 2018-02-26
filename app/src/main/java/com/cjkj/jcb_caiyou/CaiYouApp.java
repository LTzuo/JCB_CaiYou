package com.cjkj.jcb_caiyou;

import android.app.Application;

import com.cjkj.jcb_caiyou.ac_manager.ActivityManager;
import com.mob.MobSDK;

/**
 * Created by 1 on 2018/1/11.
 */
public class CaiYouApp extends Application {

    public static CaiYouApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //注册Activity管理器
        ActivityManager.getInstance().register(this);
        MobSDK.init(this);
        init();
    }

    private void init() {
        //初始化Leak内存泄露检测工具
//        LeakCanary.install(this);
//        //初始化Stetho调试工具
//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//                        .build());
    }

    public static CaiYouApp getInstance() {
        return mInstance;
    }

//    public static ActivityManager getActivityManager() {
//        return ACManager;
//    }
}