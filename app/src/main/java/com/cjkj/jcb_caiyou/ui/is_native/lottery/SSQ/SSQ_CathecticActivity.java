package com.cjkj.jcb_caiyou.ui.is_native.lottery.SSQ;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.cjkj.jcb_caiyou.util.IntentUtils;
import com.cjkj.jcb_caiyou.util.LotteryAlgorithmUtils;
import com.cjkj.jcb_caiyou.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 双色球投注界面
 */
public class SSQ_CathecticActivity extends RxBaseActivity {

    private String[] RED = new String[]{
            "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31", "32", "33"
    };

    private String[] BULE = new String[]{
            "01", "02", "03", "04", "05", "06", "07", "08",
            "09", "10", "11", "12", "13", "14", "15", "16"
    };

    public enum RandomEnum {
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
            randomSwich(RandomEnum.ONERANDOM);
        } else if (v.getId() == R.id.random_5) {
            randomSwich(RandomEnum.FIVERANDOM);
        } else if (v.getId() == R.id.random_10) {
            randomSwich(RandomEnum.TENRANDOM);
        }
    }

    /**
     * * 机选
     *
     * @param mRandomEnum
     */
    private void randomSwich(RandomEnum mRandomEnum) {
        for (int i = 0; i < mRandomEnum.getValue(); i++) {
            random();
        }
    }

    /**
     * 机选
     */
    private void random() {
        int[] random_red = LotteryAlgorithmUtils.randomCommon(0, RED.length + 1, 6);
        int[] random_bule = LotteryAlgorithmUtils.randomCommon(0, BULE.length + 1, 1);
        StringBuffer buffer_red = new StringBuffer();
        StringBuffer buffer_bule = new StringBuffer();

        for (int index : random_red) {
            buffer_red.append(RED[index - 1] + " ");
        }
        for (int index : random_bule) {
            buffer_bule.append(BULE[index - 1]);
        }
        mAdapter.addData(0, new SSQEntity(buffer_red.toString(), buffer_bule.toString(), "1", "2"));
        mRecyclerView.scrollToPosition(0);
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
        mAdapter.setInfo(mDatas);
        mAdapter.notifyDataSetChanged();
        mAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final  int position, AbsRecyclerViewAdapter.ClickableViewHolder holder) {

                if (holder.getItemViewType() != mAdapter.ITEM_TYPE_FOOTER){
                    Intent i = new Intent(SSQ_CathecticActivity.this, SSQ_LotteryActivity.class);
                    i.putExtra("SSQEntity", (Parcelable) mDatas.get(position));
                    startActivity(i);
                }
            }
        });
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
