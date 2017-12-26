package com.demo.leo.androiddrawingboard;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.demo.leo.androiddrawingboard.DrawingBoard.FreeDrawView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, FreeDrawView.GenerateDrawListener {

    private FreeDrawView mDrawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawView = (FreeDrawView) findViewById(R.id.draw_board);
        Button change_color = (Button) findViewById(R.id.bt_change_color);
        Button bt_undo = (Button) findViewById(R.id.bt_undo);
        Button bt_redo = (Button) findViewById(R.id.bt_redo);
        Button bt_generate_darw = (Button) findViewById(R.id.bt_generate_draw);

        change_color.setOnClickListener(this);
        bt_undo.setOnClickListener(this);
        bt_redo.setOnClickListener(this);
        bt_generate_darw.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_change_color:
                setRandomColor();
                break;
            case R.id.bt_undo:
                mDrawView.undo();
                break;
            case R.id.bt_redo:
                mDrawView.redo();
                break;
            case R.id.bt_generate_draw:
                mDrawView.generateViewBitmap(this);
                break;
        }
    }

    private void setRandomColor() {
        String r, g, b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;
        mDrawView.changeColor(Color.parseColor("#" + r + g + b));
    }

    @Override
    public void onDrawCreated(Bitmap draw) {
        Toast.makeText(this, "drawCreated", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDrawCreatedError() {
        Toast.makeText(this, "生成图片失败", Toast.LENGTH_SHORT).show();
    }
}
