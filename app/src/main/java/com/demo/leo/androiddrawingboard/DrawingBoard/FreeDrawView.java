package com.demo.leo.androiddrawingboard.DrawingBoard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.demo.leo.androiddrawingboard.R;

/**
 * 自定义画板
 * Created by leo on 2017/12/22.
 */

public class FreeDrawView extends View implements View.OnTouchListener {
    private static final String TAG = "FreeDrawView";

    private static final int DEFAULT_PAINT_COLOR = Color.BLACK;//默认画笔颜色
    private static final int DEFAULT_PAINT_ALPHA = 1;
    private static final float DEFAULE_PAINT_WIDTH = 4;//默认画笔宽度 单位dp

    private Paint mPaint;

    public FreeDrawView(Context context) {
        this(context, null);
    }

    public FreeDrawView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FreeDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //设置触摸监听
        setOnTouchListener(this);
        TypedArray a = null;
        try {
            a = context.obtainStyledAttributes(attrs,
                    R.styleable.FreeDrawView,
                    defStyleAttr, 0);

            initPaint(a);
        } finally {
            if (a != null) {
                a.recycle();
            }
        }

    }

    /**
     * 初始化画笔
     *
     * @param a xml参数
     */
    private void initPaint(TypedArray a) {
        int paintColor = a != null ?
                a.getColor(R.styleable.FreeDrawView_paintColor, DEFAULT_PAINT_COLOR) : DEFAULT_PAINT_COLOR;
        int paintAlpha = a != null ?
                a.getInt(R.styleable.FreeDrawView_paintAlpha, DEFAULT_PAINT_ALPHA) : DEFAULT_PAINT_ALPHA;
        float paintWidth = a != null ?
                a.getFloat(R.styleable.FreeDrawView_paintWidth, DrawBoardHelper.dip2Dp(DEFAULE_PAINT_WIDTH)) :
                DrawBoardHelper.dip2Dp(DEFAULE_PAINT_WIDTH);
        mPaint = DrawBoardHelper.cerateAndInitializePaint(paintColor, paintAlpha, paintWidth, false);

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i(TAG, "onTouch: ");
        return true;
    }

}
