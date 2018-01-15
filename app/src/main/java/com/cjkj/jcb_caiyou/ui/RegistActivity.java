package com.cjkj.jcb_caiyou.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.base.RxBaseActivity;
import com.cjkj.jcb_caiyou.util.ToastUtil;
import com.lucenlee.countdownlibrary.CountdownButton;
import com.mixiaoxiao.smoothcompoundbutton.SmoothCheckBox;
/**
 * 注册业
 */
public class RegistActivity extends RxBaseActivity implements View.OnClickListener{

    Button btn_newregist;
    SmoothCheckBox checkbox;
    CountdownButton mBtnCountDown;

    @Override
    public int getLayoutId() {
        return R.layout.activity_regist;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        btn_newregist = (Button) findViewById(R.id.btn_newregist);
        checkbox = (SmoothCheckBox) findViewById(R.id.checkbox);
        mBtnCountDown = (CountdownButton) findViewById(R.id.activity_main_btn_countdown);
        initListeners();
    }

    private void initListeners() {
        btn_newregist.setOnClickListener(this);
        mBtnCountDown.setOnClickListener(this);
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_newregist){
            if(!checkbox.isChecked()){
                ToastUtil.ShortToast("请您阅读并同意用户服务协议");
            }
        }else if(v.getId() == R.id.activity_main_btn_countdown){
                mBtnCountDown.startDown();
        }
    }
}
