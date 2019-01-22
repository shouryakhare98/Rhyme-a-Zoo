package com.example.shouryakhare.rhyme_a_zoo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        VideoView video = findViewById(R.id.videoActivity_video);
        Button forward = findViewById(R.id.videoActivity_forward);

        video.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/raw/intro_video"));
        video.start();

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Intent transitionIntent = new Intent(VideoActivity.this, MainActivity.class);
                VideoActivity.this.startActivity(transitionIntent);
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent transitionIntent = new Intent(VideoActivity.this, MainActivity.class);
                VideoActivity.this.startActivity(transitionIntent);
            }
        });
    }

}
