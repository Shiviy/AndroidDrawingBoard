package com.demo.leo.androiddrawingboard.DrawingBoard;

import android.graphics.Paint;
import android.graphics.Path;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 历史记录
 * Created by leo on 2017/12/25.
 */

public class HistoricPath implements Serializable {
    private static final long serialVersionUID = -7697604467357633517L;

    private int paintColor;
    private int paintAlpha;
    private float paintWidth;
    private float orignX, originY;
    private boolean isPoint;
    private ArrayList<Point> mPoints;

    private transient Path mPath = null;
    private transient Paint mPaint = null;


    public HistoricPath(ArrayList<Point> points, Paint paint) {
        if (paint == null) {
            return;
        }
        if (points != null && points.size() > 0) {
            mPoints = new ArrayList<>(points);
        } else {
            return;
        }
        this.paintColor = paint.getColor();
        this.paintAlpha = paint.getAlpha();
        this.paintWidth = paint.getStrokeWidth();
        this.orignX = points.get(0).getX();
        this.originY = points.get(0).getY();
        generatePaint();
        generatePath();
    }

    /**
     * 生成画笔
     */
    private void generatePaint() {
        mPaint = DrawBoardHelper.createAndInitializePaint(paintColor, paintAlpha, paintWidth, false);
    }

    /**
     * 生成路径
     */
    private void generatePath() {
        mPath = new Path();
        boolean first = true;
        for (Point point : mPoints) {
            if (first) {
                mPath.moveTo(point.getX(), point.getY());
                first = false;
            } else {
                mPath.lineTo(point.getX(), point.getY());
            }
        }
    }

    public int getPaintColor() {
        return paintColor;
    }

    public void setPaintColor(int paintColor) {
        this.paintColor = paintColor;
    }

    public int getPaintAlpha() {
        return paintAlpha;
    }

    public void setPaintAlpha(int paintAlpha) {
        this.paintAlpha = paintAlpha;
    }

    public float getPaintWidth() {
        return paintWidth;
    }

    public void setPaintWidth(float paintWidth) {
        this.paintWidth = paintWidth;
    }

    public float getOrignX() {
        return orignX;
    }

    public void setOrignX(float orignX) {
        this.orignX = orignX;
    }

    public float getOriginY() {
        return originY;
    }

    public void setOriginY(float originY) {
        this.originY = originY;
    }

    public boolean isPoint() {
        return isPoint;
    }

    public void setPoint(boolean point) {
        isPoint = point;
    }

    public Path getPath() {
        if (mPath == null) {
            generatePaint();
        }
        return mPath;
    }

    public Paint getPaint() {
        if (mPaint == null) {
            generatePaint();
        }
        return mPaint;
    }
}
