package com.cjkj.jcb_caiyou.contract;

import com.cjkj.jcb_caiyou.base.BasePresenter;
import com.cjkj.jcb_caiyou.base.BaseView;

import java.util.List;

/**
 * Created by 1 on 2018/1/18.
 *
 */
public interface RegistContract {

   interface IRegistView extends BaseView{
       void showVerificationCodeFail(String failMessage);

       void setVerificationCode(String msg);
   }

    interface IRegistPresenter extends BasePresenter {
        /**
         * 获取验证码
         */
        void getVerificationCode(String phonenum);

    }
}
