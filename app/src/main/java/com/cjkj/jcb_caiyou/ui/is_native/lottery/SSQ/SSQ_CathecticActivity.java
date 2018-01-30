package com.cjkj.jcb_caiyou.ui.is_native.lottery.SSQ;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.adapter.helper.AbsRecyclerViewAdapter;
import com.cjkj.jcb_caiyou.adapter.lottery.SSQ.SSQ_CathecticAdapter;
import com.cjkj.jcb_caiyou.base.RxBaseActivity;
import com.cjkj.jcb_caiyou.entity.lottery.SSQ.SSQEntity;
import com.cjkj.jcb_caiyou.util.ToastUtil;
import com.cjkj.jcb_caiyou.widget.RecycleViewDivider;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * 双色球投注界面
 */
public class SSQ_CathecticActivity extends RxBaseActivity {

    public  enum RandomEnum {
        ONERANDOM(1), FIVERANDOM(5), TENRANDOM(10);
         int RANDOM;
         RandomEnum(int RANDOM) {
            this.RANDOM = RANDOM;
        }
        public int getValue() {
            return RANDOM;
        }
    }

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.mSSQCathectRecyclerView)
    RecyclerView mRecyclerView;

    SSQ_CathecticAdapter mAdapter;

    List<SSQEntity> mDatas = new ArrayList<>();

    @OnClick({R.id.random_1, R.id.random_5, R.id.random_10})
    public void BtnClick(View v) {
        if (v.getId() == R.id.random_1) {
            mAdapter.addData(0,new SSQEntity("1 2 3 4 5", "1 2 3 4 5", "1", "2"));
            mRecyclerView.scrollToPosition(0);
        } else if (v.getId() == R.id.random_5) {
            ToastUtil.ShortToast(RandomEnum.FIVERANDOM.getValue() + "");
        } else if (v.getId() == R.id.random_10) {
            ToastUtil.ShortToast(RandomEnum.TENRANDOM.getValue()+ "");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ssq__cathectic;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mAdapter = new SSQ_CathecticAdapter(mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        //设置分割线
       // mRecyclerView.addItemDecoration(new RecycleViewDivider());
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置Item增加、移除动画
        mDatas.add(new SSQEntity("11 22 23 24 25", "1 2 3 4 5", "1", "2"));
        mDatas.add(new SSQEntity("12 21 23 24 30", "1 2 3 4 5", "1", "2"));
        mDatas.add(new SSQEntity("11 16 17 22 26 29", "01 02 03 04 05 06", "1", "2"));
        mAdapter.setInfo(mDatas);
        mAdapter.notifyDataSetChanged();

        mAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder) {
                 ToastUtil.ShortToast("详情"+position);
            }
        });

//        mAdapter.setOnItemDeleListener(new SSQ_CathecticAdapter.OnItemDeleListener() {
//            @Override
//            public void onItemDele(int position) {
//              mAdapter.removeData(position);
//            }
//        });
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
