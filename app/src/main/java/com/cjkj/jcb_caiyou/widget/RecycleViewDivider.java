package com.cjkj.jcb_caiyou.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
/**
 * 自定义样式 虚线RecyclerView分割线
 * Created by 1 on 2018/1/29.
 */
public  class RecycleViewDivider extends RecyclerView.ItemDecoration {

    /**
     * 设置ItemView的分割线(左上右下) 针对每个ItemView
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
//    @Override
//    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect,view,parent,state);
//        outRect.set(0,0,0,0);
//    }

    /**
     * 在ItemView以下绘制Decoration 针对整个RecyclerView 绘制需遍历所有ItemView(仅可见Item)
     * @param canvas
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(canvas,parent,state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            if(i == count - 1){
                break;
            }
            final View childview = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childview
                    .getLayoutParams();
            //以下计算主要用来确定绘制的位置
            int top = childview.getBottom() +params.bottomMargin;
           // int bottom = top + diver;
            //开始绘制虚线
            Paint mPaint = new Paint();
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.GRAY);
            mPaint.setStrokeWidth(1);
            Path path = new Path();
            path.moveTo(left+40, top);
            path.lineTo(right-40,top);
            PathEffect effects = new DashPathEffect(new float[]{8,8,8,8},0);
            mPaint.setPathEffect(effects);
            canvas.drawPath(path, mPaint);
        }
    }
}
