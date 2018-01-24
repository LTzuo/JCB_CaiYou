package com.cjkj.jcb_caiyou.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.cjkj.jcb_caiyou.ui.is_h5.LtzWebview;

/**
 * Created by ltz 2018/1/18.
 */
public class WebUtils {
    /**
     * App 内部打开一个网页
     *
     * @param context
     * @param url
     */
    public static void openInternal(Context context, String url,String title) {
        Intent intent = new Intent(context, LtzWebview.class);
           intent.putExtra("title",title) ;
           intent.putExtra("url",url) ;
           context.startActivity(intent);
    }

    /**
     * 跳转到外部浏览器打开 url
     *
     * @param context
     * @param url
     */
    public static void openExternal(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(uri);
        context.startActivity(intent);
    }


}
