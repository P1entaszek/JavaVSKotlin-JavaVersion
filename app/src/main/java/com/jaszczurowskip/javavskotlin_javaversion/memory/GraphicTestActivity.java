package com.jaszczurowskip.javavskotlin_javaversion.memory;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jaszczurowskip.javavskotlin_javaversion.R;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;

public class GraphicTestActivity extends AppCompatActivity {

    private myGlSurfaceView myGlView;
    private TextView tvFPS;
    private ScrollableNumberPicker npCubesAmountPicker;
    private ScrollableNumberPicker npRotateSpeedPicker;

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
        npCubesAmountPicker.setListener(value -> {

        });

    }

}