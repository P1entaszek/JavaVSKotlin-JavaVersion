package com.jaszczurowskip.javavskotlin_javaversion;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadTestActivity extends AppCompatActivity {
    private Button bttnStart;
    private Spinner spinnerTransdormationMode;
    private Spinner spinnerThreadMode;
    private ImageView imgFirstPhoto;
    private ImageView imgSecondPhoto;
    private ImageView imgThirdPhoto;
    private ImageView imgFourthPhoto;
    private TextView tvFirstThread;
    private TextView tvSecondThread;
    private TextView tvThirdThread;
    private TextView tvFourthThread;
    private TextView tvAllThreadTime;
    private ArrayList<Future> futureList = new ArrayList<>();
    private ArrayList<TextView> textViewsList = new ArrayList<>();
    private ArrayList<ImageView> imageViewsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_test);
        connectView();
        registerButtons();
        setupSpinner(spinnerTransdormationMode, R.array.transformations_mode);
        setupSpinner(spinnerThreadMode, R.array.thread_mode);
    }

    private void connectView() {
        bttnStart = findViewById(R.id.bttnStart);
        imgFirstPhoto = findViewById(R.id.imgFirstPhoto);
        imgSecondPhoto = findViewById(R.id.imgSecondPhoto);
        imgThirdPhoto = findViewById(R.id.imgThirdPhoto);
        imgFourthPhoto = findViewById(R.id.imgFourthPhoto);
        tvFirstThread = findViewById(R.id.tvFirstPhoto);
        tvSecondThread = findViewById(R.id.tvSecondPhoto);
        tvThirdThread = findViewById(R.id.tvThirdPhoto);
        tvFourthThread = findViewById(R.id.tvFourthPhoto);
        tvAllThreadTime = findViewById(R.id.tvAllThreadTime);
        spinnerTransdormationMode = findViewById(R.id.spinnerTransformationMode);
        spinnerThreadMode = findViewById(R.id.spinnerThreadMode);
        textViewsList.add(tvFirstThread);
        textViewsList.add(tvSecondThread);
        textViewsList.add(tvThirdThread);
        textViewsList.add(tvFourthThread);
        tvAllThreadTime = findViewById(R.id.tvAllThreadTime);
        imageViewsList.add(imgFirstPhoto);
        imageViewsList.add(imgSecondPhoto);
        imageViewsList.add(imgThirdPhoto);
        imageViewsList.add(imgFourthPhoto);
    }

    private void setupSpinner(Spinner spinner, int array) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void registerButtons() {
        bttnStart.setOnClickListener(v -> {
            if (spinnerThreadMode.getSelectedItem().equals(getString(R.string.single_thread))) {
                Executors.newSingleThreadExecutor().execute(() -> {
                    long time = System.currentTimeMillis();
                    long threadTime = 0;
                    for (int i = 0; i < imageViewsList.size(); i++) {
                        inversePhotoColors(imageViewsList.get(i));
                        threadTime = (System.currentTimeMillis() - time);
                        textViewsList.get(i).setText("Converting in: " + threadTime + " ms")
                        ;
                    }
                    long finalThreadTime = threadTime;
                    runOnUiThread(() -> tvAllThreadTime.setText("Total converting time in: " + finalThreadTime + " ms"));

                });
            }
            if (spinnerThreadMode.getSelectedItem().equals(getString(R.string.multi_thread))) {
                futureList.clear();
                for (int i = 0; i < imageViewsList.size(); i++) {
                    Callable task = getCallableTask(imageViewsList.get(i));
                    futureList.add(Executors.newFixedThreadPool(1).submit(task));
                }
                executeTasks();
            }
            if (spinnerThreadMode.getSelectedItem().equals(getString(R.string.thread_mode))) {
                Toast.makeText(this, R.string.choose_thread_mode, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void executeTasks() {
        Executors.newSingleThreadExecutor().execute(() -> {
            float averageThreadTime = 0;
            for (int i = 0; i < futureList.size(); i++) {
                float threadTime;
                try {
                    threadTime = (long) futureList.get(i).get();
                    int finalI = i;
                    textViewsList.get(finalI).setText("Converting in: " + threadTime + " ms");
                    averageThreadTime += threadTime;

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            float finalAverageThreadTime = averageThreadTime;
            runOnUiThread(() -> {
                tvAllThreadTime.setText("Average converting time in: " + finalAverageThreadTime / 4 + " ms");
            });
        });
    }

    @NonNull
    private Callable<Long> getCallableTask(ImageView img) {
        return () -> {
            long time = System.currentTimeMillis();
            inversePhotoColors(img);
            time = (System.currentTimeMillis() - time);
            return time;
        };
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
