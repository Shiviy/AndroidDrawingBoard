package com.demo.leo.androiddrawingboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.demo.leo.androiddrawingboard.DrawingBoard.FreeDrawView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FreeDrawView drawView = (FreeDrawView) findViewById(R.id.drawing);
//        drawView.setOnTouchListener(drawView);
    }
}
