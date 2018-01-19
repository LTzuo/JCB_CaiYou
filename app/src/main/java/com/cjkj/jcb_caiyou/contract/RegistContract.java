package com.cjkj.jcb_caiyou.contract;

import com.cjkj.jcb_caiyou.base.BasePresenter;
import com.cjkj.jcb_caiyou.base.BaseView;

/**
 * Created by 1 on 2018/1/18.
 * 封装注册页面相关接口，关联页面和数据
 */
public interface RegistContract {

   interface IRegistView extends BaseView{

       void ShowFail(String failMessage);

       void VerificationCodeSussesfuly(String msg);

       void UserRegistSussenfuly();
   }

    interface IRegistPresenter extends BasePresenter {
        /**
         *  获取验证码
         * @param phonenum
         */
        void getVerificationCode(String phonenum);

        /**
         *  用户注册
         * @param phonenum 手机号
         * @param pwd      密码
         * @param verCode   验证码
         * @param province   省份
         * @param city       城市
         */
        void userRegist(String phonenum,String pwd,String verCode);
    }
}
