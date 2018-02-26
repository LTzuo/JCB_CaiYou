package com.cjkj.jcb_caiyou.ui.is_h5;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.baidu.location.BDLocation;
import com.cjkj.jcb_caiyou.CaiYouApp;
import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.ac_manager.ActivityManager;
import com.cjkj.jcb_caiyou.base.RxBaseActivity;
import com.cjkj.jcb_caiyou.config.Constants;
import com.cjkj.jcb_caiyou.location.RxLocation;
import com.cjkj.jcb_caiyou.network.ApiConstants;
import com.cjkj.jcb_caiyou.ui.is_native.LoginActivity;
import com.cjkj.jcb_caiyou.util.SPUtil;
import com.cjkj.jcb_caiyou.util.SnackbarUtil;
import com.cjkj.jcb_caiyou.util.ToastUtil;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;
import butterknife.Bind;
import cn.sharesdk.onekeyshare.OnekeyShare;
import rx.Subscriber;
/**
 * 主页h5
 */
public class H5MainActivity extends RxBaseActivity {

    private static Boolean isExit = false;
    @Bind(R.id.Webview)
    WebView mWebView;

    @Override
    public int getLayoutId() {
        return R.layout.h5_activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        TwinklingRefreshLayout refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refreshLayout);
//        ProgressLayout header = new ProgressLayout(this);
//        refreshLayout.setHeaderView(header);
//        refreshLayout.setFloatRefresh(true);
//        refreshLayout.setOverScrollRefreshShow(false);
//        refreshLayout.setHeaderHeight(44);
//        refreshLayout.setOverScrollHeight(200);
//        refreshLayout.setEnableLoadmore(false);
//        header.setColorSchemeResources(R.color.Blue, R.color.Orange, R.color.Yellow, R.color.Green);
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
        // 开启DOM缓存，开启LocalStorage存储（html5的本地存储方式）
        ws.setDomStorageEnabled(true);
        ws.setDatabaseEnabled(true);
        ws.setDatabasePath(this.getApplicationContext().getCacheDir().getAbsolutePath());
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
            mWebView.loadUrl(ApiConstants.MAINURL+"sessionId="+ SPUtil.get(CaiYouApp.getInstance(),Constants.key_SessionId,"").toString().replace("\"","")
                    +"&uSessionId="+SPUtil.get(CaiYouApp.getInstance(),Constants.key_uSessionId,"").toString().replace("\"",""));
        } catch (Exception e) {
            e.printStackTrace();
        }

//        refreshLayout.startRefresh();
//        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
//            @Override
//            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        refreshLayout.finishRefreshing();
//                    }
//                }, 4000);
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityManager.getInstance().finishAllActivityByWhitelist(H5MainActivity.class);
    }

    @Override
    public void initToolBar() {
        //申请权限
        RxLocation
                .get()
                .locate(H5MainActivity.this).subscribe(new Subscriber<BDLocation>() {
                @Override
                public void onCompleted() {

               }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BDLocation bdLocation) {
                Constants.Province = bdLocation.getProvince() == null ? "" : bdLocation.getProvince();
                Constants.City = bdLocation.getCity() == null ? "" : bdLocation.getCity();
                ToastUtil.ShortToast(bdLocation.getProvince()+bdLocation.getCity());
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();// 返回前一个页面
                return true;
            } else {
                exitApp();
            }
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
            Intent i = new Intent(H5MainActivity.this,LoginActivity.class);
            startActivity(i);
        }

        @JavascriptInterface
        public void GoShare(String title,String content,String shareUrl,String imgSrc) throws UnsupportedEncodingException {
            String s_title = java.net.URLDecoder.decode(title,"UTF-8");
            String s_content = java.net.URLDecoder.decode(content,"UTF-8");
            String s_shareUrl = java.net.URLDecoder.decode(shareUrl,"UTF-8");
            String s_imgSrc = java.net.URLDecoder.decode(imgSrc,"UTF-8");
//            ToastUtil.ShortToast(s_title+"-----"+s_content+"-----"+s_shareUrl+"-----"+s_imgSrc);
            showShare(s_title,s_content,s_shareUrl,s_imgSrc);
        }
	}

    private void showShare(String title,String content,String shareUrl,String imgSrc) {
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setTitle(title);
//        // titleUrl QQ和QQ空间跳转链接
//        oks.setTitleUrl("http://sharesdk.cn");
        oks.setText(content);
        oks.setImagePath(imgSrc);
        oks.setUrl(shareUrl);
//        // comment是我对这条分享的评论，仅在人人网使用
//        oks.setComment("我是测试评论文本");
        // 启动分享GUI
        oks.show(this);
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
