package com.cjkj.jcb_caiyou.adapter.lottery;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.ui.is_native.lottery.SSQ.SSQ_DantuoFragment;
import com.cjkj.jcb_caiyou.ui.is_native.lottery.SSQ.SSQ_NormalFragment;

/**
 * 购彩-双色球viewpager适配器
 * Created by 1 on 2018/1/26.
 */
public class SSQ_ViewPagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES;
    private Fragment[] fragments;

    public SSQ_ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        TITLES = context.getResources().getStringArray(R.array.lottery_ssq);
        fragments = new Fragment[TITLES.length];
    }


    @Override
    public Fragment getItem(int position) {
        if (fragments[position] == null) {
            switch (position) {
                case 0:
                    fragments[position] = SSQ_NormalFragment.newInstance();
                    break;
                case 1:
                    fragments[position] = SSQ_DantuoFragment.newInstance();
                    break;
                default:
                    break;
            }
        }
        return fragments[position];
    }


    @Override
    public int getCount() {
        return TITLES.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
