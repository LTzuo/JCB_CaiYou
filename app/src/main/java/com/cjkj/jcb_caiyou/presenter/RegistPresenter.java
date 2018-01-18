package com.cjkj.jcb_caiyou.presenter;

import com.cjkj.jcb_caiyou.contract.RegistContract.IRegistPresenter;
import com.cjkj.jcb_caiyou.contract.RegistContract.IRegistView;
import com.cjkj.jcb_caiyou.entity.VerifitcationCodeBean;
import com.cjkj.jcb_caiyou.network.RetrofitHelper;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 1 on 2018/1/18.
 * 注册页面控制器
 */
public class RegistPresenter implements IRegistPresenter{

    private Subscription mSubscription;

    private IRegistView mRegistView;

    public RegistPresenter(IRegistView registview){
        this.mRegistView = registview;
    }

    @Override
    public void getVerificationCode(String phonenum) {
        mSubscription = RetrofitHelper.getMineApi()
                .getVerificationCode(phonenum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VerifitcationCodeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mRegistView.showVerificationCodeFail("");
                    }

                    @Override
                    public void onNext(VerifitcationCodeBean results) {
                        if (results != null){
//                            List<String> imgUrls = new ArrayList<>();
//                            for (ResultsBean result : categoryResult.results) {
//                                if (!result.url.isEmpty()){
//                                    imgUrls.add(result.url);
//                                }
//                                PictureModel model = new PictureModel();
//                                model.desc = result.desc;
//                                model.url = result.url;
//                                mModels.add(model);
//                            }
                            mRegistView.setVerificationCode("sussesful");

                        }else{
                            mRegistView.showVerificationCodeFail("fild");
                        }
                    }
                });
    }

    @Override
    public void subscribe() {
//        if(id == R.id.btn_newregist){
//            getVerificationCode();
//        }

    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
    }
}
