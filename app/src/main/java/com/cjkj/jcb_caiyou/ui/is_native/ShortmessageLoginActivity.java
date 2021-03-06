package com.cjkj.jcb_caiyou.ui.is_native;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.ac_manager.ActivityManager;
import com.cjkj.jcb_caiyou.base.RxBaseActivity;
import com.cjkj.jcb_caiyou.presenter.login.LoginContract;
import com.cjkj.jcb_caiyou.presenter.login.LoginPressenter;
import com.cjkj.jcb_caiyou.ui.is_h5.H5MainActivity;
import com.cjkj.jcb_caiyou.util.AppValidationMgr;
import com.cjkj.jcb_caiyou.util.ToastUtil;
import com.lucenlee.countdownlibrary.CountdownButton;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 短信验证登录业
 */
public class ShortmessageLoginActivity extends RxBaseActivity implements LoginContract.ILogView{

    LoginPressenter mLoginRresenter;

    @Bind(R.id.et_username)
    EditText et_username;
    @Bind(R.id.et_vercode)
    EditText et_vercode;
    @Bind(R.id.activity_main_btn_countdown)
    CountdownButton mBtnCountDown;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shortmessage_login;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mLoginRresenter = new LoginPressenter(this);
    }

    @Override
    public void initToolBar() {

    }

    @OnClick({R.id.activity_main_btn_countdown,R.id.btn_login})
    public void btnClick(View v) {
        if(v.getId() == R.id.btn_login){
            if(AppValidationMgr.checkPhoneNum(et_username.getText().toString()) &&
                    AppValidationMgr.checkVerCode(et_vercode.getText().toString())) {
                mLoginRresenter.userShortMessageLogin(et_username.getText().toString(),et_vercode.getText().toString());
            }
        }
       else if(v.getId() == R.id.activity_main_btn_countdown){
            if(AppValidationMgr.checkPhoneNum(et_username.getText().toString())){
                mLoginRresenter.getVerificationCode(et_username.getText().toString());
                mBtnCountDown.startDown();
            }
        }
    }

    @Override
    public void ShowFail(String msg) {
        ToastUtil.ShortToast(msg);
    }

    @Override
    public void LoginSussesful() {
        ActivityManager.getInstance().finishActivity(H5MainActivity.class);
        startActivity(new Intent(ShortmessageLoginActivity.this, H5MainActivity.class));
    }

    @Override
    public void VerificationCodeSussesfuly(String msg) {
        //短信验证登录，走此方法
        ToastUtil.ShortToast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginRresenter.unSubscribe();
    }
}
