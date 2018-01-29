package com.cjkj.jcb_caiyou.ui.is_native.lottery.SSQ;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.adapter.lottery.SSQ.SSQ_CathecticAdapter;
import com.cjkj.jcb_caiyou.base.RxBaseActivity;
import com.cjkj.jcb_caiyou.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
/**
 * 双色球投注界面
 */
public class SSQ_CathecticActivity extends RxBaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.mSSQCathectRecyclerView)
    RecyclerView mRecyclerView;

    SSQ_CathecticAdapter mSSQ_CathecticAdapter;

    List<String> mDatas = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_ssq__cathectic;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mSSQ_CathecticAdapter = new SSQ_CathecticAdapter(mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mSSQ_CathecticAdapter);
        //设置分割线
        mRecyclerView.addItemDecoration(new RecycleViewDivider());

        mDatas.add("01 02 03 04 05 06 12");
        mDatas.add("01 02 03 04 05 06 12");
        mDatas.add("01 02 03 04 05 06 12");
        mDatas.add("01 02 03 04 05 06 12");
        mDatas.add("01 02 03 04 05 06 12");
        mDatas.add("01 02 03 04 05 06 12");
        mDatas.add("01 02 03 04 05 06 12");
        mDatas.add("01 02 03 04 05 06 12");

        mSSQ_CathecticAdapter.setInfo(mDatas);
        mSSQ_CathecticAdapter.notifyDataSetChanged();
    }

    @Override
    public void initToolBar() {
        mToolbar.setTitle("双色球投注");
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolbar.setPopupTheme(R.style.ToolBarPopupThemeDay);
    }


}
