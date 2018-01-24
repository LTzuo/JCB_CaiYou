package com.cjkj.jcb_caiyou.presenter.login;

import android.util.Log;
import com.cjkj.jcb_caiyou.CaiYouApp;
import com.cjkj.jcb_caiyou.config.Constants;
import com.cjkj.jcb_caiyou.presenter.login.LoginContract.ILoginPressenter;
import com.cjkj.jcb_caiyou.presenter.login.LoginContract.ILogView;
import com.cjkj.jcb_caiyou.network.RetrofitHelper;
import com.cjkj.jcb_caiyou.util.AppValidationMgr;
import com.cjkj.jcb_caiyou.util.SPUtil;
import com.cjkj.jcb_caiyou.util.ToastUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 1 on 2018/1/19.
 * 登录控制器
 */
public class LoginPressenter implements ILoginPressenter {

    private Subscription mSubscription;

    private ILogView mLoginView;

    public LoginPressenter(ILogView mLoginView){
        this.mLoginView = mLoginView;
    }

    @Override
    public void userLogin(String value1,String value2) {
        mSubscription = RetrofitHelper.getMineApi()
                .userLogin(value1,value2, Constants.Province,Constants.City)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginView.ShowFail("用户登录失败");
                    }

                    @Override
                    public void onNext(JsonObject json) {
                        Log.i(Constants.LOG,json.toString());
                        if(json.has("resultTxt") && AppValidationMgr.isNotEmpty(json.get("resultTxt").toString())) {
                            JsonElement resultTxt = json.get("resultTxt");
                            ToastUtil.ShortToast(String.valueOf(resultTxt));
                        }
                        if(json.has("result") && AppValidationMgr.isNotEmpty(json.get("result").toString())) {
                            JsonElement result = json.get("result");
                            if(String.valueOf(result).equals("0")){
                                if(json.has("sessionId") && AppValidationMgr.isNotEmpty(json.get("sessionId").toString())) {
                                    SPUtil.put(CaiYouApp.getInstance(),Constants.key_SessionId,json.get("sessionId"));
                                }
                                if(json.has("uSessionId") && AppValidationMgr.isNotEmpty(json.get("uSessionId").toString())) {
                                    SPUtil.put(CaiYouApp.getInstance(),Constants.key_uSessionId,json.get("uSessionId"));
                                }
                                mLoginView.LoginSussesful();
                            }
                        }else{
                            mLoginView.ShowFail("用户登录失败");
                        }
                    }
                });
    }

    @Override
    public void userShortMessageLogin(String value1, String value2) {
        mSubscription = RetrofitHelper.getMineApi()
                .userShortMessageLogin(value1,value2, Constants.Province,Constants.City)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginView.ShowFail("用户登录失败");
                    }

                    @Override
                    public void onNext(JsonObject json) {
                        Log.i(Constants.LOG,json.toString());
                        if(json.has("resultTxt") && AppValidationMgr.isNotEmpty(json.get("resultTxt").toString())) {
                            JsonElement resultTxt = json.get("resultTxt");
                            ToastUtil.ShortToast(String.valueOf(resultTxt));
                        }
                        if(json.has("result") && AppValidationMgr.isNotEmpty(json.get("result").toString())) {
                            JsonElement result = json.get("result");
                            if(String.valueOf(result).equals("0")){
                                if(json.has("sessionId") && AppValidationMgr.isNotEmpty(json.get("sessionId").toString())) {
                                    SPUtil.put(CaiYouApp.getInstance(),Constants.key_SessionId,json.get("sessionId"));
                                }
                                if(json.has("uSessionId") && AppValidationMgr.isNotEmpty(json.get("uSessionId").toString())) {
                                    SPUtil.put(CaiYouApp.getInstance(),Constants.key_uSessionId,json.get("uSessionId"));
                                }
                                mLoginView.LoginSussesful();
                            }
                        }else{
                            mLoginView.ShowFail("用户登录失败");
                        }
                    }
                });
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
                        mLoginView.ShowFail("获取验证码失败");
                    }

                    @Override
                    public void onNext(JsonObject json) {
                        Log.i(Constants.LOG,json.toString());
                        if(json.has("resultTxt") && AppValidationMgr.isNotEmpty(json.get("resultTxt").toString())) {
                            JsonElement resultTxt = json.get("resultTxt");
                            mLoginView.VerificationCodeSussesfuly(String.valueOf(resultTxt));
                        }else{
                            mLoginView.ShowFail("获取验证码失败");
                        }
                    }
                });
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
    }
}
