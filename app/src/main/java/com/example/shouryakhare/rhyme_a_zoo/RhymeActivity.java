package com.example.shouryakhare.rhyme_a_zoo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RhymeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rhyme);

        final int[] illustrations = {
                R.drawable.rhyme001_illustration, R.drawable.rhyme002_illustration, R.drawable.rhyme004_illustration,
                R.drawable.rhyme006_illustration, R.drawable.rhyme008_illustration, R.drawable.rhyme009_illustration
        };

        final int[] mp3 = {
                R.raw.rhyme_001, R.raw.rhyme_002, R.raw.rhyme_004, R.raw.rhyme_006, R.raw.rhyme_008, R.raw.rhyme_009
        };

        Button home = findViewById(R.id.rhymeActivity_home);
        Button rhymeMenu = findViewById(R.id.rhymeActivity_listRhymes);
        Button forward = findViewById(R.id.rhymeActivity_forward);
        Button backward = findViewById(R.id.rhymeActivity_backward);
        final Button check = findViewById(R.id.rhymeActivity_check);
        final ImageView repeat = findViewById(R.id.rhymeActivity_repeat);
        ImageView illustration = findViewById(R.id.rhymeActivity_illustration);
        TextView rhymeText = findViewById(R.id.rhymeActivity_rhymeText);

        final int rhymeIndex = getIntent().getIntExtra("id",0);
        if (rhymeIndex == 0) {
            backward.setVisibility(View.INVISIBLE);
            backward.setEnabled(false);
        } else if (rhymeIndex == illustrations.length-1) {
            forward.setVisibility(View.INVISIBLE);
            forward.setEnabled(false);
        }

        final MediaPlayer rhymeMedia = MediaPlayer.create(RhymeActivity.this, mp3[rhymeIndex]);

        illustration.setImageResource(illustrations[rhymeIndex]);

        JSONReader reader = new JSONReader(this);
        rhymeText.setText(reader.getRhyme(rhymeIndex));
        rhymeText.setMovementMethod(new ScrollingMovementMethod());

        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repeat.setAlpha(0.5f);
                repeat.setEnabled(false);
                check.setAlpha(0.5f);
                check.setEnabled(false);

                rhymeMedia.start();
                rhymeMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        repeat.setAlpha(1.0f);
                        repeat.setEnabled(true);
                        check.setAlpha(1.0f);
                        check.setEnabled(true);
                    }
                });
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rhymeMedia.stop();

                Intent myIntent = new Intent(RhymeActivity.this, RhymeActivity.class);
                myIntent.putExtra("id", rhymeIndex+1);
                RhymeActivity.this.startActivity(myIntent);
            }
        });

        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rhymeMedia.stop();

                Intent myIntent = new Intent(RhymeActivity.this, RhymeActivity.class);
                myIntent.putExtra("id", rhymeIndex-1);
                RhymeActivity.this.startActivity(myIntent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rhymeMedia.stop();

                Intent myIntent = new Intent(RhymeActivity.this, MainActivity.class);
                RhymeActivity.this.startActivity(myIntent);
            }
        });

        rhymeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rhymeMedia.stop();

                Intent myIntent = new Intent(RhymeActivity.this, RhymeMenuActivity.class);
                RhymeActivity.this.startActivity(myIntent);
            }
        });

        repeat.performClick();
    }
}
