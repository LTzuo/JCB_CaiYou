package com.cjkj.jcb_caiyou.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.base.RxBaseActivity;
import com.cjkj.jcb_caiyou.util.SnackbarUtil;
import java.util.Timer;
import java.util.TimerTask;
import butterknife.Bind;
/**
 * 主页
 */
public class MainActivity extends RxBaseActivity {

    private String url = "http://192.168.10.59:8801/userlogin.jspx?sessionId=&uSessionId=";

    private static Boolean isExit = false;

    @Bind(R.id.Webview)
    WebView mWebView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mWebView = (WebView) findViewById(R.id.Webview);
        WebSettings ws = mWebView.getSettings();
        // 是否允许脚本支持
        ws.setJavaScriptEnabled(true);
        ws.setJavaScriptCanOpenWindowsAutomatically(true);
        ws.setSaveFormData(false);
        ws.setSavePassword(false);
        ws.setAppCacheEnabled(true);
        ws.setAppCacheMaxSize(1024);
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        ws.setUseWideViewPort(true);
        // 优先使用缓存
        // ws.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 自动加载图片
        // ws.setLoadsImagesAutomatically(true);
        // ws.setSupportZoom(false);
        // 是否允许缩放
        ws.setBuiltInZoomControls(false);
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        // mWeb.setInitialScale(90);
        mWebView.addJavascriptInterface(new JsInterface(this),"jcb");
        mWebView.getSettings().setDefaultTextEncodingName("UTF -8");// 设置默认为utf-8
        try {
            mWebView.loadUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initToolBar() {
//        mToolbar.setTitle("");// 标题的文字需在setSupportActionBar之前，不然会无效
//       // mToolbar.setNavigationIcon(R.mipmap.ic_launcher);
//        setSupportActionBar(mToolbar);
//        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
//        mToolbar.setPopupTheme(R.style.ToolBarPopupThemeDay);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();// 返回前一个页面
            return true;
        }else{
            exitApp();
        }
        return false;
    }

    public class JsInterface {
		private Context mContext;
		public JsInterface(Context context) {
			this.mContext = context;
		}

		@JavascriptInterface
		public void backPage() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mWebView.canGoBack()){
                        mWebView.goBack();// 返回前一个页面
                    }
                }
            });
		}

        @JavascriptInterface
        public void GotoLogin(){
            Intent i = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(i);
        }
	}

    public class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // Log.i(TAG, "-MyWebViewClient->shouldOverrideUrlLoading()--");
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // Log.i(TAG, "-MyWebViewClient->onPageStarted()--");
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // Log.i(TAG, "-MyWebViewClient->onPageFinished()--");
            super.onPageFinished(view, url);
        }
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            // 这里进行无网络或错误处理，具体可以根据errorCode的值进行判断，做跟详细的处理。
            // view.loadUrl("file:///android_asset/h.html");
        }
    }

    /**
     * 双击退出App
     */
    private void exitApp() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            SnackbarUtil.showMessage(this.mWebView, "再按一次退出");
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            finish();
            System.exit(0);
        }
    }

}
