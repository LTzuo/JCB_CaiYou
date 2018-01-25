package com.cjkj.jcb_caiyou.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.cjkj.jcb_caiyou.ui.is_h5.LtzWebview;

/**
 * 页面之间跳转工具
 * Created by 1 on 2018/1/24.
 */
public class IntentUtils {


    /**
     * 页面跳转
     * @param context
     * @param clzz
     */
    public static void Goto(Activity context, Class<?> clzz) {
        Intent intent = new Intent(context, clzz);
        context.startActivity(intent);
    }
}
