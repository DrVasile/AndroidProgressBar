package com.example.vasile.progressbar;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    int progress = 0;
    boolean started = false;
    ProgressBar progressBar;
    Button startButton;
    Button stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView mImageView = (ImageView) findViewById(R.id.imageview_animation_list);
        ((AnimationDrawable) mImageView.getBackground()).start();

        // initiate progress bar and start button
        progressBar = (ProgressBar) findViewById(R.id.myProgressBar);
        startButton = (Button) findViewById(R.id.startButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        // perform click event on button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call a function
                started = true;
                setProgressValue();

            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call a function
                progress = 0;
                started = false;
                progressBar.setProgress(progress);
            }
        });
    }

    private void setProgressValue() {
        // set the progress
        progressBar.setProgress(progress);
        // thread is used to change the progress value
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (started) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progress += (started ? 10 : 0);
                    progressBar.setProgress(progress);
                }
            }
        });
        thread.start();
    }
}