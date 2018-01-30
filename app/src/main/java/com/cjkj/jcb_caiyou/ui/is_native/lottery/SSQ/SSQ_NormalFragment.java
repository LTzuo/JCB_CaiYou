package com.cjkj.jcb_caiyou.ui.is_native.lottery.SSQ;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.adapter.indicatordialog.IndicatorDialogAdapter;
import com.cjkj.jcb_caiyou.adapter.helper.AbsRecyclerViewAdapter;
import com.cjkj.jcb_caiyou.adapter.indicatordialog.IndicatorDialogViewHolder;
import com.cjkj.jcb_caiyou.adapter.lottery.SSQ.SSQ_BuleBallAdapter;
import com.cjkj.jcb_caiyou.adapter.lottery.SSQ.SSQ_RedBallAdapter;
import com.cjkj.jcb_caiyou.base.RxLazyFragment;
import com.cjkj.jcb_caiyou.sensor.ShakeSensor;
import com.cjkj.jcb_caiyou.util.IntentUtils;
import com.cjkj.jcb_caiyou.util.LotteryAlgorithmUtils;
import com.cjkj.jcb_caiyou.util.ToastUtil;
import com.cjkj.jcb_caiyou.widget.RvGridLayoutManager;
import com.jiang.android.indicatordialog.IndicatorBuilder;
import com.jiang.android.indicatordialog.IndicatorDialog;
import com.sflin.csstextview.CSSTextView;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * 双色球-普通投注
 * Created by 1 on 2018/1/26.
 */
public class SSQ_NormalFragment extends RxLazyFragment implements ShakeSensor.onShakeListener {

    @Bind(R.id.rv_red)
    RecyclerView mRedBallRecyclerView;
    @Bind(R.id.rv_blue)
    RecyclerView mBlueBallRecyclerView;
    @Bind(R.id.img_yyy)
    ImageView img_yyy;
    @Bind(R.id.tv_yyy)
    TextView tv_yyy;
    @Bind(R.id.content_text)
    CSSTextView mCSSTextView;
    @Bind(R.id.img_dele)
    ImageView img_dele;
    @Bind(R.id.tv_random)
    TextView tv_random;

    private ShakeSensor mShakeSensor;//传感器
    private Vibrator mVibrator;//震动效果

    SSQ_RedBallAdapter mRedBallAdapter;
    SSQ_BuleBallAdapter mBuleBallAdapter;

    private List<String> mLists = new ArrayList<>();

    private boolean isSensorRegist = false;//速度传感器是否注册,默认传感器为关闭状态

    public static SSQ_NormalFragment newInstance() {
        return new SSQ_NormalFragment();
    }

    @OnClick({R.id.layout_yyy,R.id.next,R.id.tv_random,R.id.img_dele})
    public void BtnClick(View v) {
        if (v.getId() == R.id.layout_yyy) {
            if (isSensorRegist) {
                img_yyy.setColorFilter(getResources().getColor(R.color.gray));
                tv_yyy.setTextColor(getResources().getColor(R.color.gray));
                mShakeSensor.unRegisterListener();
                isSensorRegist = false;
            } else {
                img_yyy.setColorFilter(getResources().getColor(R.color.orange_yyy));
                tv_yyy.setTextColor(getResources().getColor(R.color.orange_yyy));
                mShakeSensor.init();
                isSensorRegist = true;
            }
        }else if(v.getId() == R.id.next){
            IntentUtils.Goto(getActivity(),SSQ_CathecticActivity.class);
            getActivity().finish();
        }else if(v.getId() == R.id.tv_random){
            showBottomDialog(v, 0.1f, IndicatorBuilder.GRAVITY_LEFT);
        }else if(v.getId() == R.id.img_dele){
            mRedBallAdapter.clear();
            mBuleBallAdapter.clear();
            Calculation();
        }
    }

    private void showBottomDialog(View v, float v1, int gravityCenter) {
        mLists.clear();
        mLists.add("10注");
        mLists.add("5注");
        mLists.add("1注");
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int height = dm.heightPixels;
        IndicatorDialog dialog = new IndicatorBuilder(getActivity())
                .width(200)
                .height((int) (height * 0.5))
                .ArrowDirection(IndicatorBuilder.BOTTOM)
                .bgColor(getResources().getColor(R.color.black_alpha_30))
                .gravity(gravityCenter)
                .radius(18)
                .ArrowRectage(v1)
                .layoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false))
                .adapter(new IndicatorDialogAdapter() {
                    @Override
                    public void onBindView(IndicatorDialogViewHolder holder, int position) {
                        TextView tv = holder.getView(R.id.item_add);
                        tv.setText(mLists.get(position));

                        if (position == mLists.size() - 1) {
                            holder.setVisibility(R.id.item_line, IndicatorDialogViewHolder.GONE);
                        } else {
                            holder.setVisibility(R.id.item_line, IndicatorDialogViewHolder.VISIBLE);

                        }
                    }

                    @Override
                    public int getLayoutID(int position) {
                        return R.layout.item_indicator_dialog;
                    }

                    @Override
                    public boolean clickable() {
                        return true;
                    }

                    @Override
                    public void onItemClick(View v, int position) {
                        ToastUtil.ShortToast(""+position);
                    }

                    @Override
                    public int getItemCount() {
                        return mLists.size();
                    }
                }).create();

        dialog.setCanceledOnTouchOutside(true);
        dialog.show(v);
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_ssq_normal;
    }

    @Override
    public void finishCreateView(Bundle state) {
        initRedBall();
        initBuleBall();
        mShakeSensor = new ShakeSensor(getContext());
        //注册回调事件
        mShakeSensor.setOnShakeListener(this);
        mVibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
    }

    private void initRedBall() {
        RvGridLayoutManager mLayoutManager = new RvGridLayoutManager(getContext(), 7);
        mRedBallAdapter = new SSQ_RedBallAdapter(mRedBallRecyclerView);
        mRedBallRecyclerView.setLayoutManager(mLayoutManager);
        mRedBallRecyclerView.setNestedScrollingEnabled(false);
        mRedBallRecyclerView.setAdapter(mRedBallAdapter);
        //禁止滑动
        mRedBallRecyclerView.setNestedScrollingEnabled(false);
        mRedBallAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder) {
                mRedBallAdapter.setItemChoice(position);
                Calculation();
            }
        });
    }

    private void initBuleBall() {
        RvGridLayoutManager mLayoutManager = new RvGridLayoutManager(getContext(), 7);
        mBuleBallAdapter = new SSQ_BuleBallAdapter(mBlueBallRecyclerView);
        mBlueBallRecyclerView.setLayoutManager(mLayoutManager);
        mBlueBallRecyclerView.setNestedScrollingEnabled(false);
        mBlueBallRecyclerView.setAdapter(mBuleBallAdapter);
        mBuleBallAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder) {
                mBuleBallAdapter.setItemChoice(position);
                Calculation();
            }
        });
    }

    /**
     * 根据红球和篮球的个数
     * 计算彩票注数和金额
     */
    private void Calculation(){
        if(mRedBallAdapter.getBallCount()>=6 && mBuleBallAdapter.getBallCount()>=1){
           String count =  LotteryAlgorithmUtils.Calculation(mRedBallAdapter.getBallCount(),6,mBuleBallAdapter.getBallCount(),1);
            mCSSTextView.setText("共"+count+"注"+LotteryAlgorithmUtils.mul(Long.valueOf(count),2)+"元");
            mCSSTextView.setTextArrColor("共",getResources().getColor(R.color.background_dark));
            mCSSTextView.setTextArrColor("注",getResources().getColor(R.color.background_dark));
            mCSSTextView.setTextArrColor("元",getResources().getColor(R.color.background_dark));
            img_dele.setVisibility(View.VISIBLE);
            tv_random.setVisibility(View.GONE);
        }else{
            mCSSTextView.setText(null);
            img_dele.setVisibility(View.GONE);
            tv_random.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onShake() {
        //震动效果
        long pattern[] = {300, 200};//周期多长震动
        mVibrator.vibrate(pattern, -1);
        mRedBallAdapter.Random();
        mBuleBallAdapter.Random();
        Calculation();
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        if (isSensorRegist)
            mShakeSensor.init();
    }

    @Override
    protected void onInvisible() {
        super.onInvisible();
        if (isSensorRegist)
            mShakeSensor.unRegisterListener();
    }
}
