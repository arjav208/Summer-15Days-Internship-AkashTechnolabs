package com.example.audioplay;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button start, paush, stop;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.start);
        paush = findViewById(R.id.paush);
        stop = findViewById(R.id.stop);

        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.music);

        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.start:
                mediaPlayer.start();
                break;

            case R.id.paush:
                mediaPlayer.pause();
                break;

            case R.id.stop:
                mediaPlayer.stop();
                break;
        }
    }
}