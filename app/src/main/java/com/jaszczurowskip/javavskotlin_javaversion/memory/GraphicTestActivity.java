package com.jaszczurowskip.javavskotlin_javaversion.memory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jaszczurowskip.javavskotlin_javaversion.R;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPickerListener;

public class GraphicTestActivity extends AppCompatActivity {

    private myGlSurfaceView myGlView;
    private TextView tvFPS;
    private ScrollableNumberPicker npCubesAmountPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic_test);
        myGlView = findViewById(R.id.glView);
        tvFPS = findViewById(R.id.tvFps);
        npCubesAmountPicker = findViewById(R.id.npCubesAmount);
        myGlView.setupView();
        tvFPS.setText("66.3");
        npCubesAmountPicker.setListener(value -> {
                //myGlView.changeAmountOfCubes(value);
        });

    }

}