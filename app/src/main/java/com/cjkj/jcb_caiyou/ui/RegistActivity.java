package com.cjkj.jcb_caiyou.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.base.RxBaseActivity;
import com.cjkj.jcb_caiyou.network.RetrofitHelper;
import com.cjkj.jcb_caiyou.util.ToastUtil;
import com.lucenlee.countdownlibrary.CountdownButton;
import com.mixiaoxiao.smoothcompoundbutton.SmoothCheckBox;

import butterknife.Bind;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 注册业
 */
public class RegistActivity extends RxBaseActivity{

    Button btn_newregist;
    SmoothCheckBox checkbox;
    CountdownButton mBtnCountDown;

    @Bind(R.id.et_username)
    EditText et_username;
    @Override
    public int getLayoutId() {
        return R.layout.activity_regist;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        btn_newregist = (Button) findViewById(R.id.btn_newregist);
        checkbox = (SmoothCheckBox) findViewById(R.id.checkbox);
        mBtnCountDown = (CountdownButton) findViewById(R.id.activity_main_btn_countdown);
    }

    @Override
    public void initToolBar() {

    }

    @OnClick({R.id.btn_newregist,R.id.activity_main_btn_countdown})
    public void BtnClick(View v){
        if(v.getId() == R.id.btn_newregist){
            if(!checkbox.isChecked()){
                ToastUtil.ShortToast("请您阅读并同意用户服务协议");
            }
//            RetrofitHelper.getMineApi()
//                    .getVerificationCode(et_username.getText().toString())
//                    .compose(bindToLifecycle())
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(Info -> {
//                        mTestAdapter.setTestInfo(Info.getResults());
//                        finishTask();
//                    }, throwable -> initEmptyView());
        }else if(v.getId() == R.id.activity_main_btn_countdown){
            mBtnCountDown.startDown();
        }
    }



}
