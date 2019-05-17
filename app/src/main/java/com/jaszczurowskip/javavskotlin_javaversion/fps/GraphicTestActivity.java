package com.jaszczurowskip.javavskotlin_javaversion.fps;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jaszczurowskip.javavskotlin_javaversion.R;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;

public class GraphicTestActivity extends AppCompatActivity {

    private myGlSurfaceView myGlView;
    private TextView tvFPS;
    private ScrollableNumberPicker npCubesAmountPicker;
    private ScrollableNumberPicker npRotateSpeedPicker;
    private Handler handler = new Handler();
    private int delay = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic_test);
        myGlView = findViewById(R.id.glView);
        tvFPS = findViewById(R.id.tvFps);
        npCubesAmountPicker = findViewById(R.id.npCubesAmount);
        npRotateSpeedPicker = findViewById(R.id.npRotateSpeed);
        myGlView.setupView(npCubesAmountPicker.getValue(), npRotateSpeedPicker.getValue());
        tvFPS.setText(myGlView.getFramerate());
        npCubesAmountPicker.setListener(value -> myGlView.changeAmountOfCubes(npCubesAmountPicker.getValue(), npRotateSpeedPicker.getValue()));
        npRotateSpeedPicker.setListener(value -> myGlView.changeAmountOfCubes(npCubesAmountPicker.getValue(), npRotateSpeedPicker.getValue()));
        updateFPSPeriodically();

    }

    private void updateFPSPeriodically() {
        handler.postDelayed(new Runnable() {
            public void run() {
                tvFPS.setText(myGlView.getFramerate());
                handler.postDelayed(this, delay);
            }
        }, delay);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(this::onDestroy);

    }
}