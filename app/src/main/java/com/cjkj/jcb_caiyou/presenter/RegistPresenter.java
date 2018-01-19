package com.cjkj.jcb_caiyou.presenter;

import com.cjkj.jcb_caiyou.config.Constants;
import com.cjkj.jcb_caiyou.contract.RegistContract.IRegistPresenter;
import com.cjkj.jcb_caiyou.contract.RegistContract.IRegistView;
import com.cjkj.jcb_caiyou.network.RetrofitHelper;
import com.cjkj.jcb_caiyou.util.ToastUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
    public void getVerificationCode(String value) {
        mSubscription = RetrofitHelper.getMineApi()
                .getVerificationCode(value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mRegistView.ShowFail("获取验证码失败");
                    }

                    @Override
                    public void onNext(JsonObject json) {
                        try {
                            JsonElement resultTxt = json.get("resultTxt");
                            mRegistView.VerificationCodeSussesfuly(String.valueOf(resultTxt));
                        }catch(Exception e){
                            e.printStackTrace();
                            mRegistView.ShowFail("获取验证码失败");
                        }
                    }
                });
    }

    @Override
    public void userRegist(String value1, String value2, String value3) {
          mSubscription = RetrofitHelper.getMineApi()
                .userRegist(value1,value2,value3,Constants.Province,Constants.City)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mRegistView.ShowFail("用户注册失败");
                    }

                    @Override
                    public void onNext(JsonObject json) {
                        ToastUtil.ShortToast(json.toString());
//                        try {
//                            JsonElement resultTxt = json.get("resultTxt");
//                            mRegistView.VerificationCodeSussesfuly(String.valueOf(resultTxt));
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            mRegistView.showFail("用户注册失败");
//                        }
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
