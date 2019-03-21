package com.jaszczurowskip.javavskotlin_javaversion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button graphicTest;
    Button threadTest;
    Button processorTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        graphicTest = findViewById(R.id.bttnGraphicTransformations);
        threadTest = findViewById(R.id.bttnThreadTest);
        processorTest = findViewById(R.id.bttnProcessorOperations);
        registerListeners();
    }

    private void registerListeners() {
        graphicTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "graphic", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, GraphicTestActivity.class);
                startActivity(intent);
            }
        });
        threadTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "thread", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ThreadTestActivity.class);
                startActivity(intent);
            }
        });
        processorTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "processor", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ProcessorTestActivity.class);
                startActivity(intent);
            }
        });
    }


}
