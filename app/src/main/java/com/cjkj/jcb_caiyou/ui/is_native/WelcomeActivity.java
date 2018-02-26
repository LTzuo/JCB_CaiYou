package com.cjkj.jcb_caiyou.ui.is_native;

import android.content.Intent;
import android.os.Bundle;

import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.ui.is_h5.H5MainActivity;
import com.cjkj.jcb_caiyou.util.SystemUiVisibilityUtil;
import com.trello.rxlifecycle.components.RxActivity;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
/**
 * 欢迎页
 */
public class WelcomeActivity extends RxActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        SystemUiVisibilityUtil.hideStatusBar(getWindow(), true);
        setUpSplash();
    }

    private void setUpSplash() {
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> finishTask());
    }

    private void finishTask() {
//        boolean isLogin = PreferenceUtil.getBoolean(ConstantUtil.KEY, false);
//        if (isLogin) {
             //startActivity(new Intent(WelcomeActivity.this, H5MainActivity.class));
             startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
//        } else {
//            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
//        }
           //  CaiYouApp.getActivityManager().popActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
