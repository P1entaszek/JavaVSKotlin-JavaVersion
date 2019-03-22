package com.jaszczurowskip.javavskotlin_javaversion;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class ThreadTestActivity extends AppCompatActivity {
    private Button bttnStart;
    private Spinner spinnerTransdormationMode;
    private Spinner spinnerThreadMode;
    private ImageView imgFirstPhoto;
    private ImageView imgSecondPhoto;
    private ImageView imgThirdPhoto;
    private ImageView imgFourthPhoto;
    private ImageView imgFifthPhoto;
    private ImageView imgSixthPhoto;
    private TextView tvFirstThread;
    private TextView tvSecondThread;
    private TextView tvThirdThread;
    private TextView tvFourthThread;
    private TextView tvFifthThread;
    private TextView tvSixthThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_test);
        bttnStart = findViewById(R.id.bttnStart);
        imgFirstPhoto = findViewById(R.id.imgFirstPhoto);
        imgSecondPhoto = findViewById(R.id.imgSecondPhoto);
        imgThirdPhoto = findViewById(R.id.imgThirdPhoto);
        imgFourthPhoto = findViewById(R.id.imgFourthPhoto);
        imgFifthPhoto = findViewById(R.id.imgFifthPhoto);
        imgSixthPhoto = findViewById(R.id.imgSixthPhoto);
        tvFirstThread = findViewById(R.id.tvFirstPhoto);
        tvSecondThread = findViewById(R.id.tvSecondPhoto);
        tvThirdThread = findViewById(R.id.tvThirdPhoto);
        tvFourthThread = findViewById(R.id.tvFourthPhoto);
        tvFifthThread = findViewById(R.id.tvFifthPhoto);
        tvSixthThread = findViewById(R.id.tvSixthPhoto);
        spinnerTransdormationMode = findViewById(R.id.spinnerTransformationMode);
        spinnerThreadMode = findViewById(R.id.spinnerThreadMode);
        registerButtons();
        setupSpinner(spinnerTransdormationMode, R.array.transformations_mode);
        setupSpinner(spinnerThreadMode, R.array.thread_mode);
    }

    private void setupSpinner(Spinner spinner, int array) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


    private void registerButtons() {
        bttnStart.setOnClickListener(v -> {
            inversePhotoColors(imgFirstPhoto);
            inversePhotoColors(imgSecondPhoto);
            inversePhotoColors(imgThirdPhoto);
            inversePhotoColors(imgFourthPhoto);
            inversePhotoColors(imgFifthPhoto);
            inversePhotoColors(imgSixthPhoto);
        });
    }

    private void inversePhotoColors(ImageView img) {
        Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
        int length = bitmap.getWidth() * bitmap.getHeight();
        int[] array = new int[length];
        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        mutableBitmap.getPixels(array, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        for (int i = 0; i < length; i++) {
            array[i] = invertRGB(array[i]);
        }
        mutableBitmap.setPixels(array, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        img.setImageBitmap(mutableBitmap);
    }

    private int invertRGB(int rgb) {
        return ((~rgb) & 0x00FFFFFF) | 0xFF000000;
    }
}
