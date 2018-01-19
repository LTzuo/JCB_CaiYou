package com.cjkj.jcb_caiyou.contract;

import com.cjkj.jcb_caiyou.base.BasePresenter;
import com.cjkj.jcb_caiyou.base.BaseView;

/**
 * Created by 1 on 2018/1/19.
 * 封装登录页面相关接口，关联页面和数据
 */
public interface LoginContract {

    interface ILogView extends BaseView{
        void ShowFail(String msg);

        void LoginSussesful();

        void VerificationCodeSussesfuly(String msg);
    }

    interface  ILoginPressenter extends BasePresenter{
        /**
         * 用户正常登录
         * @param value1
         * @param value2
         */
        void userLogin(String value1,String value2);

        /**
         * 用户短信登录
         * @param value1
         * @param value2
         */
        void userShortMessageLogin(String value1,String value2);

        /**
         *  获取验证码（短信验证登录会用到）
         * @param phonenum
         */
        void getVerificationCode(String phonenum);

    }
}
