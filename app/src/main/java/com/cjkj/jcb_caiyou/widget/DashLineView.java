package com.cjkj.jcb_caiyou.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import com.cjkj.jcb_caiyou.R;

/**
 * 自定义View 虚线
 * 结合投注界面RecyclerView虚线分割线使用
 * Created by 1 on 2018/1/29.
 */
public class DashLineView  extends View {

    private Paint mPaint;

    public DashLineView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(1);
        mPaint.setPathEffect(new DashPathEffect(new float[] {8, 8,8,8}, 0));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int centerY = getHeight() / 2;
        canvas.drawLine(0+40, centerY, getWidth()-40, centerY, mPaint);
    }



}
