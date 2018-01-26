package com.cjkj.jcb_caiyou.ui.is_native.lottery.SSQ;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.adapter.helper.AbsRecyclerViewAdapter;
import com.cjkj.jcb_caiyou.adapter.lottery.SSQ_BuleBallAdapter;
import com.cjkj.jcb_caiyou.adapter.lottery.SSQ_RedBallAdapter;
import com.cjkj.jcb_caiyou.base.RxLazyFragment;
import com.cjkj.jcb_caiyou.sensor.ShakeSensor;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * 双色球-普通投注
 * Created by 1 on 2018/1/26.
 */
public class SSQ_NormalFragment extends RxLazyFragment implements ShakeSensor.onShakeListener{

    @Bind(R.id.rv_red)
    RecyclerView mRedBallRecyclerView;
    @Bind(R.id.rv_blue)
    RecyclerView mBlueBallRecyclerView;
    @Bind(R.id.img_yyy)
    ImageView img_yyy;
    @Bind(R.id.tv_yyy)
    TextView tv_yyy;
    private ShakeSensor mShakeSensor;//传感器
    private Vibrator mVibrator;//震动效果

    SSQ_RedBallAdapter mRedBallAdapter;
    SSQ_BuleBallAdapter mBuleBallAdapter;

    private boolean isSensorRegist = false;//速度传感器是否注册,默认传感器为关闭状态

    public static SSQ_NormalFragment newInstance() {
        return new SSQ_NormalFragment();
    }

    @OnClick(R.id.layout_yyy)
    public void BtnClick(View v){
        if(v.getId() == R.id.layout_yyy){
            if(isSensorRegist){
                img_yyy.setColorFilter(getResources().getColor(R.color.gray));
                tv_yyy.setTextColor(getResources().getColor(R.color.gray));
                mShakeSensor.unRegisterListener();
                isSensorRegist = false;
            }else{
                img_yyy.setColorFilter(getResources().getColor(R.color.orange_yyy));
                tv_yyy.setTextColor(getResources().getColor(R.color.orange_yyy));
                mShakeSensor.init();
                isSensorRegist = true;
            }
        }

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

    private void initRedBall(){
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 7);
        mRedBallAdapter = new SSQ_RedBallAdapter(mRedBallRecyclerView);
        mRedBallRecyclerView.setLayoutManager(mLayoutManager);
        mRedBallRecyclerView.setAdapter(mRedBallAdapter);
        //禁止滑动
        mRedBallRecyclerView.setNestedScrollingEnabled(false);
        mRedBallAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder) {
                mRedBallAdapter.setItemChoice(position);
            }
        });
    }

    private void initBuleBall(){
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 7);
        mBuleBallAdapter = new SSQ_BuleBallAdapter(mBlueBallRecyclerView);
        mBlueBallRecyclerView.setLayoutManager(mLayoutManager);
        mBlueBallRecyclerView.setAdapter(mBuleBallAdapter);
        mBuleBallAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder) {
                mBuleBallAdapter.setItemChoice(position);
            }
        });
    }

    @Override
    public void onShake() {
        //震动效果
        long pattern[] = {300, 200};//周期多长震动
        mVibrator.vibrate(pattern, -1);
        mRedBallAdapter.Random();
        mBuleBallAdapter.Random();
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        if(isSensorRegist)
            mShakeSensor.init();
    }

    @Override
    protected void onInvisible() {
        super.onInvisible();
        if (isSensorRegist)
        mShakeSensor.unRegisterListener();
    }
}
