package com.cjkj.jcb_caiyou.ui.is_native;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.adapter.pager.HomeViewPagerAdapter;
import com.cjkj.jcb_caiyou.base.RxBaseActivity;
import com.cjkj.jcb_caiyou.ui.is_native.lottery.LotteryFragment;
import com.cjkj.jcb_caiyou.util.SnackbarUtil;
import com.cjkj.jcb_caiyou.widget.tabbarhelper.BottomNavigationViewHelper;
import com.cjkj.jcb_caiyou.widget.tabbarhelper.NoScrollViewPager;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;

/**
 * 主页原生
 * Created by 1 on 2018/1/24.
 */
public class HomeActivity extends RxBaseActivity {

    @Bind(R.id.viewpager)
    NoScrollViewPager viewPager;
    @Bind(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    private MenuItem menuItem;
    private static Boolean isExit = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        viewPager = (NoScrollViewPager) findViewById(R.id.viewpager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_news:
                                viewPager.setCurrentItem(0, false);
                                break;
                            case R.id.item_lib:
                                viewPager.setCurrentItem(1, false);
                                break;
                            case R.id.item_find:
                                viewPager.setCurrentItem(2, false);
                                break;
                            case R.id.item_more:
                                viewPager.setCurrentItem(3, false);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(4);
        adapter.addFragment(LotteryFragment.newInstance());
        adapter.addFragment(LotteryFragment.newInstance());
        adapter.addFragment(LotteryFragment.newInstance());
        adapter.addFragment(LotteryFragment.newInstance());
        viewPager.setAdapter(adapter);
    }

    /**
     * 双击退出App
     */
    private void exitApp() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            SnackbarUtil.showMessage(this.bottomNavigationView, "再按一次退出");
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitApp(); // 调用双击退出函数
        }
        return false;
    }

    @Override
    public void initToolBar() {

    }
}
