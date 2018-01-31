package com.cjkj.jcb_caiyou.ui.is_native.lottery.SSQ;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.adapter.helper.AbsRecyclerViewAdapter;
import com.cjkj.jcb_caiyou.adapter.lottery.SSQ.SSQ_CathecticAdapter;
import com.cjkj.jcb_caiyou.base.RxBaseActivity;
import com.cjkj.jcb_caiyou.entity.lottery.SSQ.SSQEntity;
import com.cjkj.jcb_caiyou.util.LotteryAlgorithmUtils;
import com.cjkj.jcb_caiyou.util.ToastUtil;
import com.sflin.csstextview.CSSTextView;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        ONERANDOM(1, "机选1注"), FIVERANDOM(5, "机选1注"), TENRANDOM(10, "机选5注");
        int value;
        String name;
        RandomEnum(int value, String name) {
            this.value = value;
            this.name = name;
        }
        public int getValue() {
            return value;
        }
    }

    /**
     * 计算当前页面的总注数和总金额
     */
    public void Calculation() {
        tv_calculation.setText("合计"+2*mAdapter.getZhuAllCount()+"元");
        tv_calculation.setTextArrColor("合计",getResources().getColor(R.color.background_dark));
        tv_calculation.setTextArrColor("元",getResources().getColor(R.color.background_dark));
        tv_zhu.setText("共"+mAdapter.getZhuAllCount()+"注");
    }

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.mSSQCathectRecyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.tv_calculation)
    CSSTextView tv_calculation;
    @Bind(R.id.tv_zhu)
    TextView tv_zhu;
    SSQ_CathecticAdapter mAdapter;
    List<SSQEntity> mDatas = new ArrayList<>();

    private int random;
    @OnClick({R.id.tv_continue, R.id.random_1, R.id.random_5})
    public void BtnClick(View v) {
        if (v.getId() == R.id.tv_continue) {
            ToastUtil.ShortToast("继续选号");
        } else if (v.getId() == R.id.random_1) {
            randomSwich(RandomEnum.ONERANDOM);
        } else if (v.getId() == R.id.random_5) {
            randomSwich(RandomEnum.FIVERANDOM);
        }
    }

    /**
     * * 机选
     *
     * @param mRandomEnum
     */
    private void randomSwich(RandomEnum mRandomEnum) {
        Observable.create(new Observable.OnSubscribe<List<SSQEntity>>() {
            @Override
            public void call(Subscriber<? super List<SSQEntity>> subscriber) {
                subscriber.onNext(random(mRandomEnum));
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    mAdapter.addData(0, list);
                    mRecyclerView.scrollToPosition(0);
                    Calculation();
                }, throwable -> {
                });
    }

    /**
     * 机选
     */
    private List<SSQEntity> random(RandomEnum mRandomEnum) {
        List<SSQEntity> list = new ArrayList<>();
        list.clear();
        for (int i = 0; i < mRandomEnum.getValue(); i++) {
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
            list.add(new SSQEntity(buffer_red.toString(), buffer_bule.toString(), 1, 2));
        }
        return list;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ssq__cathectic;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        initRecyclerView();
        loadData();
    }

    @Override
    public void initRecyclerView() {
        mAdapter = new SSQ_CathecticAdapter(mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        // mRecyclerView.addItemDecoration(new RecycleViewDivider());
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置Item增加、移除动画
        mAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int position, AbsRecyclerViewAdapter.ClickableViewHolder holder) {
                if (holder.getItemViewType() != mAdapter.ITEM_TYPE_FOOTER) {
                    ToastUtil.ShortToast(mAdapter.getmDatas().get(position).getRedBall()+","+mAdapter.getmDatas().get(position).getBuleBall());
                }
            }
        });
    }

    @Override
    public void loadData() {
        random = getIntent().getIntExtra("random",0);
        if(random == 0){
           mDatas.add(getIntent().getParcelableExtra("SSQEntity"));
        }
        Observable.just(mDatas)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mDatas -> {
                    mAdapter.setInfo(mDatas);
                    finishTask();
                }, throwable -> {
                });
    }

    @Override
    public void finishTask() {
        mAdapter.notifyDataSetChanged();
        switch (random){
            case 0:
                Calculation();
                break;
            case 1:
                randomSwich(RandomEnum.ONERANDOM);
                break;
            case 5:
                randomSwich(RandomEnum.FIVERANDOM);
                break;
            case 10:
                randomSwich(RandomEnum.TENRANDOM);
                break;
        }
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
