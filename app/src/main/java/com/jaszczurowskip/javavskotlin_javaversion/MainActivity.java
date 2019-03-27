package com.jaszczurowskip.javavskotlin_javaversion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button graphicTest;
    private Button threadTest;
    private Button aboutApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        graphicTest = findViewById(R.id.memoryTest);
        threadTest = findViewById(R.id.bttnThreadTest);
        aboutApp = findViewById(R.id.bttnAboutApp);
        registerListeners();
    }

    private void registerListeners() {
        graphicTest.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GraphicTestActivity.class);
            startActivity(intent);
        });
        threadTest.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ThreadTestActivity.class);
            startActivity(intent);
        });
        aboutApp.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutApp.class);
            startActivity(intent);
        });
    }
}
