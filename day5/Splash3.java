package com.example.splashactivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class Splash3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Thread thread = new Thread() {
            @Override
            public void run() {

                try {
                    setContentView(R.layout.activity_main);
                    sleep(3000);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                finally {
                    Intent intent = new Intent(Splash3.this, Home.class);
                    startActivity(intent);
                }
            }
        };
        thread.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
//        ourSound.release();
        finish();

    }
}