package com.cjkj.jcb_caiyou.ui.is_native.lottery;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.adapter.helper.AbsRecyclerViewAdapter;
import com.cjkj.jcb_caiyou.adapter.lottery.MoreLotteryAdapter;
import com.cjkj.jcb_caiyou.base.RxBaseActivity;
import com.cjkj.jcb_caiyou.ui.is_native.lottery.SSQ.SSQ_LotteryActivity;
import com.cjkj.jcb_caiyou.util.IntentUtils;

import butterknife.Bind;

/**
 * 更多彩种
 * Created by 1 on 2018/1/24.
 */
public class MoreLotteryActivity extends RxBaseActivity{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.rv_more)
    RecyclerView mRecyclerView;

    MoreLotteryAdapter mLotteryAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_morelottery;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mLotteryAdapter = new MoreLotteryAdapter(mRecyclerView);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mLotteryAdapter);
        mLotteryAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder) {
                switch (position){
                    case 0:
                        IntentUtils.Goto(MoreLotteryActivity.this,SSQ_LotteryActivity.class);
                        break;
                }
            }
        });
    }

    @Override
    public void initToolBar() {
        mToolbar.setTitle("聚彩宝");// 标题的文字需在setSupportActionBar之前，不然会无效
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolbar.setPopupTheme(R.style.ToolBarPopupThemeDay);
    }


}
