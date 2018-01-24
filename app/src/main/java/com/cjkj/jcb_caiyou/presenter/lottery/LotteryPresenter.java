package com.cjkj.jcb_caiyou.presenter.lottery;

import com.cjkj.jcb_caiyou.entity.lottery.CategoryResult;
import com.cjkj.jcb_caiyou.entity.lottery.PictureModel;
import com.cjkj.jcb_caiyou.network.RetrofitHelper;
import java.util.ArrayList;
import java.util.List;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 1 on 2018/1/24.
 * 购彩页适配器
 */
public class LotteryPresenter implements LotteryContract.ILotteryPresenter{

    private Subscription mSubscription;

    private LotteryContract.ILotteryView mLotteryView;

    private List<PictureModel> mModels;

    public LotteryPresenter(LotteryContract.ILotteryView mLotteryView){
        this.mLotteryView = mLotteryView;
        mModels = new ArrayList<>();
    }

    public List<PictureModel> getBannerModel(){
        return this.mModels;
    }

    @Override
    public void subscribe() {
        getBannerData();
    }

    @Override
    public void getBannerData() {
        mSubscription = RetrofitHelper.getTestApi()
                .getCategoryData("福利",5,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mLotteryView.showBannerFail("Banner 图加载失败");
                    }

                    @Override
                    public void onNext(CategoryResult categoryResult) {
                        if (categoryResult != null && categoryResult.results != null
                                && categoryResult.results.size() > 0){
                            List<String> imgUrls = new ArrayList<>();
                            for (CategoryResult.ResultsBean result : categoryResult.results) {
                                if (!result.url.isEmpty()){
                                    imgUrls.add(result.url);
                                }
                                PictureModel model = new PictureModel();
                                model.desc = result.desc;
                                model.url = result.url;
                                mModels.add(model);
                            }
                            mLotteryView.setBanner(imgUrls);

                        }else{
                            mLotteryView.showBannerFail("Banner 图加载失败");
                        }
                    }
                });
    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
    }
}
