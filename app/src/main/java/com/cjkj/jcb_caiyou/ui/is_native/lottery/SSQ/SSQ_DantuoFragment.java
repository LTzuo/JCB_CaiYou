package com.cjkj.jcb_caiyou.ui.is_native.lottery.SSQ;

import android.os.Bundle;

import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.base.RxLazyFragment;

/**
 * 双色球-胆拖投注
 * Created by 1 on 2018/1/26.
 */
public class SSQ_DantuoFragment extends RxLazyFragment{

    public static SSQ_DantuoFragment newInstance() {
        return new SSQ_DantuoFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_ssq_dantuo;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }
}
