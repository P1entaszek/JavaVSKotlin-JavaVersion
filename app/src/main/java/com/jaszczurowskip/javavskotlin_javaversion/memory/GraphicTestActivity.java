package com.jaszczurowskip.javavskotlin_javaversion.memory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jaszczurowskip.javavskotlin_javaversion.R;

public class GraphicTestActivity extends AppCompatActivity {

    private myGlSurfaceView myGlView;
    private TextView tvFPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic_test);
        myGlView = findViewById(R.id.glView);
        tvFPS = findViewById(R.id.tvFpsPerformance);
        myGlView.setupView();
        tvFPS.setText("FPS/sec: 66.3");
    }
}