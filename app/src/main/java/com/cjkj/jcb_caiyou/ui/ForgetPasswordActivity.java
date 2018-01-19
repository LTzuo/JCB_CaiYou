package com.cjkj.jcb_caiyou.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.ac_manager.ActivityManager;
import com.cjkj.jcb_caiyou.base.RxBaseActivity;
import com.cjkj.jcb_caiyou.contract.RegistContract;
import com.cjkj.jcb_caiyou.presenter.RegistPresenter;
import com.cjkj.jcb_caiyou.util.AppValidationMgr;
import com.cjkj.jcb_caiyou.util.ToastUtil;
import com.lucenlee.countdownlibrary.CountdownButton;
import com.mixiaoxiao.smoothcompoundbutton.SmoothCheckBox;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 忘记密码业
 */
public class ForgetPasswordActivity extends RxBaseActivity implements RegistContract.IRegistView {

    private RegistPresenter mRegistPresenter;
    @Bind(R.id.activity_main_btn_countdown)
    CountdownButton mBtnCountDown;
    @Bind(R.id.et_username)
    EditText et_username;
    @Bind(R.id.et_vercode)
    EditText et_vercode;
    @Bind(R.id.et_new_pwd)
    EditText et_new_pwd;
    @Bind(R.id.et_confirm_pwd)
    EditText et_confirm_pwd;

    @Override
    public void ShowFail(String failMessage) {
        ToastUtil.ShortToast(failMessage);
    }

    @Override
    public void VerificationCodeSussesfuly(String msg) {
        ToastUtil.ShortToast(msg);
    }

    @Override
    public void UserRegistSussenfuly() {
        ActivityManager.getInstance().finishActivity(MainActivity.class);
        startActivity(new Intent(ForgetPasswordActivity.this, MainActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_password;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mRegistPresenter = new RegistPresenter(this);
    }

    @Override
    public void initToolBar() {

    }

    @OnClick({R.id.activity_main_btn_countdown,R.id.btn_confirm})
    public void onClick(View v) {
        if(v.getId() == R.id.activity_main_btn_countdown){
            if(AppValidationMgr.checkPhoneNum(et_username.getText().toString())){
                mRegistPresenter.getVerificationCode(et_username.getText().toString());
                mBtnCountDown.startDown();
            }
        }
        else if(v.getId() == R.id.btn_confirm){
            if(AppValidationMgr.checkPhoneNum(et_username.getText().toString()) &&
                    AppValidationMgr.checkVerCode(et_vercode.getText().toString())&&
                    AppValidationMgr.checkPwd(et_new_pwd.getText().toString(),et_confirm_pwd.getText().toString())){
                mRegistPresenter.userRegist(et_username.getText().toString(),et_new_pwd.getText().toString(),et_vercode.getText().toString());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRegistPresenter.unSubscribe();
    }
}
