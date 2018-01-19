package com.cjkj.jcb_caiyou.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.ac_manager.ActivityManager;
import com.cjkj.jcb_caiyou.base.RxBaseActivity;
import com.cjkj.jcb_caiyou.network.ApiConstants;
import com.cjkj.jcb_caiyou.presenter.RegistPresenter;
import com.cjkj.jcb_caiyou.util.AppValidationMgr;
import com.cjkj.jcb_caiyou.util.ToastUtil;
import com.cjkj.jcb_caiyou.util.WebUtils;
import com.lucenlee.countdownlibrary.CountdownButton;
import com.mixiaoxiao.smoothcompoundbutton.SmoothCheckBox;
import butterknife.Bind;
import butterknife.OnClick;
import com.cjkj.jcb_caiyou.contract.RegistContract.IRegistView;
/**
 * 注册业
 */
public class RegistActivity extends RxBaseActivity implements IRegistView{

    private RegistPresenter mRegistPresenter;

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
        startActivity(new Intent(RegistActivity.this, MainActivity.class));
    }

    @Bind(R.id.checkbox)
    SmoothCheckBox checkbox;
    @Bind(R.id.activity_main_btn_countdown)
    CountdownButton mBtnCountDown;
    @Bind(R.id.et_username)
    EditText et_username;
    @Bind(R.id.et_VerificationCode)
    EditText et_VerificationCode;
    @Bind(R.id.et_pwd)
    EditText et_pwd;
    @Bind(R.id.et_confirm_pwd)
    EditText et_confirm_pwd;
    @Override
    public int getLayoutId() {
        return R.layout.activity_regist;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mRegistPresenter = new RegistPresenter(this);
    }

    @Override
    public void initToolBar() {

    }

    @OnClick({R.id.btn_newregist,R.id.activity_main_btn_countdown,R.id.seeProtocol})
    public void BtnClick(View v){
        if(v.getId() == R.id.btn_newregist){
            if(AppValidationMgr.checkPhoneNum(et_username.getText().toString()) &&
              AppValidationMgr.checkVerCode(et_VerificationCode.getText().toString())&&
                    AppValidationMgr.checkPwd(et_pwd.getText().toString(),et_confirm_pwd.getText().toString()) &&
                    AppValidationMgr.checkBoxcheck(checkbox.isChecked())){
                mRegistPresenter.userRegist(et_username.getText().toString(),et_pwd.getText().toString(),et_VerificationCode.getText().toString());
            }

        }else if(v.getId() == R.id.activity_main_btn_countdown){
            if(AppValidationMgr.checkPhoneNum(et_username.getText().toString())){
                mRegistPresenter.getVerificationCode(et_username.getText().toString());
                mBtnCountDown.startDown();
            }
        }else if(v.getId() == R.id.seeProtocol){
            WebUtils.openInternal(RegistActivity.this, ApiConstants.XIEYIURL,getBaseContext().getString(R.string.USERXIEYITITLE));
//          WebUtils.openInternal(RegistActivity.this, "file:///android_asset/index.html");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRegistPresenter.unSubscribe();
    }
}
