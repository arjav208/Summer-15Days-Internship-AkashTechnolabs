package com.example.videoplay;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
VideoView video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        video = findViewById(R.id.video);

        MediaController mediaController = new MediaController(this);

        mediaController.setAnchorView(video);

        Uri uri = Uri.parse("android.resourse://"+getPackageName() + "/" + R.raw.video);

        video.setMediaController(mediaController);

        video.setVideoURI(uri);

        video.requestFocus();
        video.start();

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MainActivity.this,"Thank you",Toast.LENGTH_SHORT).show();
            }
        });

        video.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {

                Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }
}