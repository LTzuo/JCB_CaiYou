package com.cjkj.jcb_caiyou.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.base.RxBaseActivity;
import com.lucenlee.countdownlibrary.CountdownButton;

/**
 * 忘记密码业
 */
public class ForgetPasswordActivity extends RxBaseActivity implements View.OnClickListener{

    CountdownButton btn_countdown;

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_password;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        btn_countdown = (CountdownButton) findViewById(R.id.activity_main_btn_countdown);
        initListeners();
    }

    private void initListeners(){
        btn_countdown.setOnClickListener(this);
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.activity_main_btn_countdown){
            btn_countdown.startDown();
        }
    }
}
