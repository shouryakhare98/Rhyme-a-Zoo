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

/**
 * Activity to display a rhyme for the user
 */
public class RhymeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rhyme);

        // Get IDProvider
        IDProvider idProvider = new IDProvider();

        // Get all views
        Button home = findViewById(R.id.rhymeActivity_home);
        Button rhymeMenu = findViewById(R.id.rhymeActivity_listRhymes);
        Button forward = findViewById(R.id.rhymeActivity_forward);
        Button backward = findViewById(R.id.rhymeActivity_backward);
        final Button check = findViewById(R.id.rhymeActivity_check);
        final ImageView repeat = findViewById(R.id.rhymeActivity_repeat);
        ImageView illustration = findViewById(R.id.rhymeActivity_illustration);
        TextView rhymeText = findViewById(R.id.rhymeActivity_rhymeText);

        // Get current rhyme index and disable navigation buttons if at the start or end of rhyme list
        final int rhymeIndex = getIntent().getIntExtra("id",0);
        if (rhymeIndex == 0) {
            backward.setVisibility(View.INVISIBLE);
            backward.setEnabled(false);
        } else if (rhymeIndex == idProvider.getRhymesArrayLength()-1) {
            forward.setVisibility(View.INVISIBLE);
            forward.setEnabled(false);
        }

        // Get rhyme audio
        final MediaPlayer rhymeMedia = MediaPlayer.create(RhymeActivity.this, idProvider.getRhymesId(rhymeIndex));

        // Set illustration image
        illustration.setImageResource(idProvider.getIllustrationId(rhymeIndex));

        // Get rhyme text from JSON and display
        JSONReader reader = new JSONReader(this);
        rhymeText.setText(reader.getRhyme(rhymeIndex));
        rhymeText.setMovementMethod(new ScrollingMovementMethod());

        // If repeat button pressed, replay rhyme audio
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

        // If forward button pressed, move to next rhyme
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rhymeMedia.stop();

                Intent myIntent = new Intent(RhymeActivity.this, RhymeActivity.class);
                myIntent.putExtra("id", rhymeIndex+1);
                RhymeActivity.this.startActivity(myIntent);
            }
        });

        // If previous button pressed, move to previous rhyme
        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rhymeMedia.stop();

                Intent myIntent = new Intent(RhymeActivity.this, RhymeActivity.class);
                myIntent.putExtra("id", rhymeIndex-1);
                RhymeActivity.this.startActivity(myIntent);
            }
        });

        // If home button pressed, go to home activity
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rhymeMedia.stop();

                Intent myIntent = new Intent(RhymeActivity.this, MainActivity.class);
                RhymeActivity.this.startActivity(myIntent);
            }
        });

        // If rhyme menu button pressed, go to RhymeMenuActivity
        rhymeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rhymeMedia.stop();

                Intent myIntent = new Intent(RhymeActivity.this, RhymeMenuActivity.class);
                RhymeActivity.this.startActivity(myIntent);
            }
        });

        // If check button pressed, go to QuizActivity
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RhymeActivity.this, QuizActivity.class);
                myIntent.putExtra("id", rhymeIndex);
                RhymeActivity.this.startActivity(myIntent);
            }
        });

        // Star the rhyme when the user loads this activity
        repeat.performClick();
    }
}
