package com.cjkj.jcb_caiyou.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * 重写 RecyclerView 的 LinearLayoutManager
 * ScrollView嵌套RecyclerView滑动冲突解决
 * 重写父控件，让父控件 ScrollView 直接拦截滑动事件，不向下分发给 RecyclerView，
 * 2018/1/16
 * 林天佐
 */
public class RvGridLayoutManager  extends GridLayoutManager {

    public RvGridLayoutManager(Context context, int spanCount){
        super(context, spanCount);
    }

    @Override
    public boolean canScrollVertically() {
        //isScrollEnabled：recyclerview是否支持滑动
        //super.canScrollVertically()：是否为竖直方向滚动
        return false;
    }
}