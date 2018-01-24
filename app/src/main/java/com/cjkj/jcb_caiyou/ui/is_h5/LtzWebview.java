package com.cjkj.jcb_caiyou.ui.is_h5;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.base.RxBaseActivity;
import com.cjkj.jcb_caiyou.network.ApiConstants;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import butterknife.Bind;
/**
 * Created by 1 on 2018/1/19.
 * @author ltz
 * 公共webview浏览界面
 */
public class LtzWebview extends RxBaseActivity {

    @Bind(R.id.webView)
    WebView mWebView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private String title,url;

    @Override
    public int getLayoutId() {
        return R.layout.web_ltz;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        url = intent.getStringExtra("url");
        TwinklingRefreshLayout refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refreshLayout);
        ProgressLayout header = new ProgressLayout(this);
        refreshLayout.setHeaderView(header);
        refreshLayout.setFloatRefresh(true);
        refreshLayout.setOverScrollRefreshShow(false);
        refreshLayout.setHeaderHeight(44);
        refreshLayout.setOverScrollHeight(200);
        refreshLayout.setEnableLoadmore(false);
        header.setColorSchemeResources(R.color.Blue, R.color.Orange, R.color.Yellow, R.color.Green);
//        header.setColorSchemeColors(0xff4674e7,0xff0ba62c);
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(url);

        refreshLayout.startRefresh();
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefreshing();
                    }
                }, 4000);
            }
        });
    }

    @Override
    public void initToolBar() {
        mToolbar.setTitle(title);// 标题的文字需在setSupportActionBar之前，不然会无效
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolbar.setPopupTheme(R.style.ToolBarPopupThemeDay);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        destroyWebView();
        super.onDestroy();
    }

    /**
     * 解决WebView持有mContext导致的内存泄漏问题
     */
    private void destroyWebView() {
        if (mWebView != null) {
            ViewParent parent = mWebView.getParent();
            if (parent != null) ((ViewGroup) parent).removeView(mWebView);
            mWebView.stopLoading();
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.clearView();
            mWebView.removeAllViews();
            try {
                mWebView.destroy();
            } catch (Throwable e) {
            }
        }
    }
}
