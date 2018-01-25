package com.cjkj.jcb_caiyou.ui.is_native.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.adapter.lottery.LotteryAdapter;
import com.cjkj.jcb_caiyou.base.RxLazyFragment;
import com.cjkj.jcb_caiyou.entity.lottery.PictureModel;
import com.cjkj.jcb_caiyou.presenter.lottery.LotteryContract;
import com.cjkj.jcb_caiyou.presenter.lottery.LotteryPresenter;
import com.cjkj.jcb_caiyou.ui.is_native.avtivity.lottery.MoreLotteryActivity;
import com.cjkj.jcb_caiyou.ui.is_native.avtivity.lottery.SSQ_LotteryActivity;
import com.cjkj.jcb_caiyou.util.GlideImageLoader;
import com.cjkj.jcb_caiyou.util.IntentUtils;
import com.cjkj.jcb_caiyou.util.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import java.util.List;
import butterknife.Bind;
/**
 * 购彩Fragment
 * Created by 1 on 2018/1/24.
 */
public class LotteryFragment extends RxLazyFragment implements LotteryContract.ILotteryView,OnBannerListener {

    @Bind(R.id.main_banner)
    Banner mBanner;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.rv_lottery)
    RecyclerView mRecyclerView;
    @Bind(R.id.header)
    RecyclerViewHeader header;
    LotteryPresenter mLotteryPresenter;
    LotteryAdapter  mLotteryAdapter;

    public static LotteryFragment newInstance() {
        return new LotteryFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_lottery;
    }

    @Override
    public void finishCreateView(Bundle state) {
        mLotteryPresenter = new LotteryPresenter(this);
        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        if (!isPrepared || !isVisible) {
            return;
        }
        initstatusManagerLayout();
        initRecyclerView();
        isPrepared = false;
    }

    @Override
    protected void initstatusManagerLayout() {
        super.initstatusManagerLayout();
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red_liget);
        mSwipeRefreshLayout.post(() -> {
            mSwipeRefreshLayout.setRefreshing(true);
            loadData();
        });
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            loadData();
        });
    }

    @Override
    protected void loadData() {
        super.loadData();
        mLotteryPresenter.subscribe();
    }

    @Override
    protected void initRecyclerView() {
        mLotteryAdapter = new LotteryAdapter(mRecyclerView);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mLotteryAdapter);
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        mBanner.setOnBannerListener(this);
        header.attachTo(mRecyclerView);
        mLotteryAdapter.setOnItemClickListener((position, holder) -> {
            switch (position) {
                case 0:
                   IntentUtils.Goto(getActivity(),SSQ_LotteryActivity.class);
                    break;
                case 1:
                    ToastUtil.ShortToast("3D");
                    break;
                case 2:
                    ToastUtil.ShortToast("七乐彩");
                    break;
                case 3:
                    ToastUtil.ShortToast("大乐透");
                    break;
                case 4:
                    ToastUtil.ShortToast("排列三");
                    break;
                case 5:
                    ToastUtil.ShortToast("排列五");
                    break;
                case 6:
                    ToastUtil.ShortToast("七星彩");
                    break;
                case 7:
                    ToastUtil.ShortToast("竞彩足球");
                    break;
                case 8:
                    IntentUtils.Goto(getActivity(), MoreLotteryActivity.class);
                    break;
            }
            });
    }

    @Override
    public void OnBannerClick(int position) {
        PictureModel model = mLotteryPresenter.getBannerModel().get(position);
        ToastUtil.ShortToast(model.url);
    }

    @Override
    public void showBannerFail(String failMessage) {
        ToastUtil.ShortToast(failMessage);
    }

    @Override
    public void setBanner(List<String> imgUrls) {
        mBanner.setImages(imgUrls).setImageLoader(new GlideImageLoader()).start();
        finishTask();
    }

    @Override
    protected void finishTask() {
        super.finishTask();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLotteryPresenter != null){
            mLotteryPresenter.unSubscribe();
        }
    }
}
