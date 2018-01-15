package com.cjkj.jcb_caiyou.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.base.RxBaseActivity;
import com.cjkj.jcb_caiyou.util.ToastUtil;
import com.lucenlee.countdownlibrary.CountdownButton;

/**
 * 短信验证登录业
 */
public class ShortmessageLoginActivity extends RxBaseActivity implements View.OnClickListener{

    CountdownButton btn_countdown;
    ImageButton delete_username;
    EditText et_username;
    EditText et_pwd;
    Button btn_login;
    @Override
    public int getLayoutId() {
        return R.layout.activity_shortmessage_login;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        btn_countdown = (CountdownButton) findViewById(R.id.activity_main_btn_countdown);
        delete_username = (ImageButton) findViewById(R.id.delete_username);
        et_username = (EditText) findViewById(R.id.et_username);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        btn_login = (Button) findViewById(R.id.btn_login);
        et_username.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && et_username.getText().length() > 0) {
                delete_username.setVisibility(View.VISIBLE);
            } else {
                delete_username.setVisibility(View.GONE);
            }

        });

        et_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 如果用户名清空了 清空密码 清空记住密码选项
                et_pwd.setText("");
                if (s.length() > 0) {
                    // 如果用户名有内容时候 显示删除按钮
                    delete_username.setVisibility(View.VISIBLE);
                } else {
                    // 如果用户名有内容时候 显示删除按钮
                    delete_username.setVisibility(View.GONE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        initListeners();
    }

    private void initListeners() {
        delete_username.setOnClickListener(this);
        btn_countdown.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.delete_username){
            et_username.setText("");
            et_pwd.setText("");
            delete_username.setVisibility(View.GONE);
            et_username.setFocusable(true);
            et_username.setFocusableInTouchMode(true);
            et_username.requestFocus();
        }else if(v.getId() == R.id.btn_login){
            ToastUtil.ShortToast("登录");
        }
       else if(v.getId() == R.id.activity_main_btn_countdown){
            btn_countdown.startDown();
        }
    }
}
