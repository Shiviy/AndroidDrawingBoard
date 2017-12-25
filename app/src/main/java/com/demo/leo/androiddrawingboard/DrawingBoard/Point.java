package com.demo.leo.androiddrawingboard.DrawingBoard;

import java.io.Serializable;

/**
 * 点的类
 * Created by leo on 2017/12/22.
 */

public class Point implements Serializable {

    private static final long serialVersionUID = 9191873447547741685L;

    private float x;
    private float y;

    public Point() {
        x = y = -1;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
