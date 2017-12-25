package com.demo.leo.androiddrawingboard.DrawingBoard;

import android.content.res.Resources;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;

/**
 * 画板工具类
 * Created by leo on 2017/12/22.
 */

public class DrawBoardHelper {

    /**
     * 创建画笔
     *
     * @return
     */
    public static Paint createPaint() {
        return new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    /**
     * 初始化画笔
     *
     * @param paint
     */
    public static void initPaint(Paint paint, int paintColor, int paintAlpha,
                                 float paintWidth, boolean fill) {
        if (paint == null) {
            throw new RuntimeException("Paint has not been created");
        }

        if (fill) {
            setFillPaint(paint);
        } else {
            setStrokePaint(paint);
        }
        paint.setStrokeWidth(paintWidth);
        paint.setColor(paintColor);
        paint.setAlpha(paintAlpha);

    }

    /**
     * 创建 初始化画笔
     *
     * @param paintColor 画笔颜色
     * @param paintAlpha 画笔透明度
     * @param paintWidth 画笔宽度
     * @param fill       是否为实心
     * @return paint object
     */
    public static Paint createAndInitializePaint(int paintColor, int paintAlpha,
                                                 float paintWidth, boolean fill) {
        Paint paint = createPaint();
        initPaint(paint, paintColor, paintAlpha, paintWidth, fill);
        return paint;
    }

    private static void setFillPaint(Paint paint) {
        paint.setStyle(Paint.Style.FILL);
    }

    /**
     * 设置空心画笔
     *
     * @param paint
     */
    public static void setStrokePaint(Paint paint) {
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setPathEffect(new ComposePathEffect(
                new CornerPathEffect(100f),
                new CornerPathEffect(100f)
        ));
        paint.setStyle(Paint.Style.STROKE);
    }

    /**
     * dp转px
     *
     * @param dp
     * @return
     */
    public static float dip2Dp(float dp) {
        return (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
