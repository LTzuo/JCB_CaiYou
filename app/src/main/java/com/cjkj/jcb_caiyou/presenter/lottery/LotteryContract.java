package com.cjkj.jcb_caiyou.presenter.lottery;

import com.cjkj.jcb_caiyou.base.BasePresenter;
import com.cjkj.jcb_caiyou.base.BaseView;

import java.util.List;

/**
 * Created by 1 on 2018/1/24.
 * 购彩页面接口，关联页面和数据
 */
public interface LotteryContract {

    interface ILotteryView extends BaseView{
        void showBannerFail(String failMessage);

        void setBanner(List<String> imgUrls);
    }

    interface ILotteryPresenter extends BasePresenter{
        /**
         * 获取Banner轮播图数据
         */
        void getBannerData();
    }
}
