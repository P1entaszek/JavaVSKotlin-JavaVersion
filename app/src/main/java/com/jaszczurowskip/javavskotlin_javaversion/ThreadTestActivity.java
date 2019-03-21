package com.jaszczurowskip.javavskotlin_javaversion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTestActivity extends AppCompatActivity {
    private TextView tvFirstThread;
    private Button bttnStart;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_test);
        tvFirstThread = findViewById(R.id.tvFirstThread);
        bttnStart = findViewById(R.id.bttnStart);
        bar =  findViewById(R.id.threadProgress);
        registerButtons();
    }

    private void registerButtons() {
        bttnStart.setOnClickListener(v -> {
            bar.setProgress(0);
            //tvFirstThread.setText("");
        });
    }

    private static void spin(int milliseconds) {
        long sleepTime = milliseconds*1000000L; // convert to nanoseconds
        long startTime = System.nanoTime();
        while ((System.nanoTime() - startTime) < sleepTime) {}
    }
}
