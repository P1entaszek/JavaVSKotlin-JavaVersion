package com.jaszczurowskip.javavskotlin_javaversion.memory;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;

import com.jaszczurowskip.javavskotlin_javaversion.R;

public class GraphicTestActivity extends AppCompatActivity {

    myGlSurfaceView myGlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic_test);
        myGlView = findViewById(R.id.glView);
        myGlView.setupView();
    }
}
