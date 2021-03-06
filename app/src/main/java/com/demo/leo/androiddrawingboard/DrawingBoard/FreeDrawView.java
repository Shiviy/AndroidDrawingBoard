package com.demo.leo.androiddrawingboard.DrawingBoard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.demo.leo.androiddrawingboard.R;

import java.util.ArrayList;

/**
 * 自定义画板
 * Created by leo on 2017/12/22.
 */

public class FreeDrawView extends View implements View.OnTouchListener {
    private static final String TAG = "FreeDrawView";

    private static final int DEFAULT_PAINT_COLOR = Color.BLACK;//默认画笔颜色
    private static final int DEFAULT_PAINT_ALPHA = 255;
    private static final float DEFAULT_PAINT_WIDTH = 4;//默认画笔宽度 单位dp

    private Path mPath;
    private Paint mPaint;
    private ArrayList<Point> currentPoints = new ArrayList<>();
    private ArrayList<HistoricPath> historicPaths = new ArrayList<>();

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
                a.getFloat(R.styleable.FreeDrawView_paintWidth, DrawBoardHelper.dip2Dp(DEFAULT_PAINT_WIDTH)) :
                DrawBoardHelper.dip2Dp(DEFAULT_PAINT_WIDTH);
        mPaint = DrawBoardHelper.createAndInitializePaint(paintColor, paintAlpha, paintWidth, false);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPath == null) {
            mPath = new Path();
        } else {
            mPath.rewind();
        }

        if (historicPaths != null && historicPaths.size() > 0) {
            for (HistoricPath historicPath : historicPaths) {
                canvas.drawPath(historicPath.getPath(), historicPath.getPaint());
            }
        }

        boolean first = true;
        if (currentPoints.size() > 1) {
            for (Point point : currentPoints) {
                if (first) {
                    first = false;
                    mPath.moveTo(point.getX(), point.getY());
                } else {
                    mPath.lineTo(point.getX(), point.getY());
                }
            }
            canvas.drawPath(mPath, mPaint);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (currentPoints.size() > 0) {
                HistoricPath historicPath = new HistoricPath(currentPoints, mPaint);
                historicPaths.add(historicPath);
            }
            currentPoints.clear();
        }
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            Point point = new Point();
            point.setX(event.getX());
            point.setY(event.getY());
            currentPoints.add(point);
        }

        invalidate();
        return true;
    }

}
