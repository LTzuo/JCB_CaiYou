package com.cjkj.jcb_caiyou.ui.is_native.lottery.SSQ;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.adapter.lottery.SSQ.SSQ_ViewPagerAdapter;
import com.cjkj.jcb_caiyou.base.RxBaseActivity;
import com.cjkj.jcb_caiyou.util.IntentUtils;
import com.cjkj.jcb_caiyou.util.ToastUtil;
import com.cjkj.jcb_caiyou.widget.tabbarhelper.NoScrollViewPager;
import butterknife.Bind;
import butterknife.OnClick;
/**
 * 双色球
 * author:linliang
 */
public class SSQ_LotteryActivity extends RxBaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.toolbar_title)
    TextView mToolbarTitle;
    @Bind(R.id.mSpinner)
    Spinner mSpinner;
    @Bind(R.id.menu_custom)
    ImageView menu_custom;

    @Bind(R.id.mViewPager)
    NoScrollViewPager mViewPager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ssq_lottery;
    }

    @OnClick(R.id.menu_custom)
    public void BtnClick(View v){
            if(v.getId() == R.id.menu_custom){
                IntentUtils.Goto(this,LotteryTrendActivity.class);
        }
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        SSQ_ViewPagerAdapter mAdapter = new SSQ_ViewPagerAdapter(getSupportFragmentManager(), getApplicationContext());
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public void initToolBar() {
        mToolbarTitle.setText("双色球");
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolbar.setPopupTheme(R.style.ToolBarPopupThemeDay);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.lottery_ssq, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setVisibility(View.VISIBLE);
        menu_custom.setImageDrawable(getResources().getDrawable(R.drawable.zoushi));
        menu_custom.setVisibility(View.VISIBLE);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mViewPager.setCurrentItem(position,false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
   }

}
