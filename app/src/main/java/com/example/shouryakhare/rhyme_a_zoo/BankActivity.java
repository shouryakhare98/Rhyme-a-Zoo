package com.example.shouryakhare.rhyme_a_zoo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

/**
 * Activity to show the bank if user has 0 coins to spend.
 */
public class BankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        // Get all views
        final Button repeat = findViewById(R.id.bankActivity_repeat);
        final Button home = findViewById(R.id.bankActivity_home);
        final TextView numTotalGoldCoins = findViewById(R.id.bankActivity_numgold);
        final TextView earnedTotal = findViewById(R.id.bankActivity_earnedtotal);

        // Total coins graphic is set invisible
        numTotalGoldCoins.setVisibility(View.INVISIBLE);
        numTotalGoldCoins.setText(String.valueOf(0));
        earnedTotal.setVisibility(View.INVISIBLE);

        // Get audio files to play
        final MediaPlayer coinsAvailable = MediaPlayer.create(BankActivity.this, R.raw.coins_available);
        final MediaPlayer coinsTotal = MediaPlayer.create(BankActivity.this, R.raw.coins_total);

        // If home button is pressed, go to MainActivity
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coinsAvailable.stop();
                coinsTotal.stop();
                Intent myIntent = new Intent(BankActivity.this, MainActivity.class);
                BankActivity.this.startActivity(myIntent);
            }
        });

        // If repeat button is pressed, play audio and show total coins graphic
        repeat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Dim button and disable it
                repeat.setAlpha(0.5f);
                repeat.setEnabled(false);

                // Play coins available audio. When completed, display total coins graphic
                coinsAvailable.start();
                coinsAvailable.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {

                        numTotalGoldCoins.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
                        numTotalGoldCoins.setVisibility(View.VISIBLE);
                        earnedTotal.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
                        earnedTotal.setVisibility(View.VISIBLE);

                        // Play total coins audio.
                        coinsTotal.start();
                    }
                });

                // When total coins audio ends, hide total coins graphic
                coinsTotal.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        // Repeat button is enabled
                        repeat.setAlpha(1.0f);
                        repeat.setEnabled(true);

                        numTotalGoldCoins.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out));
                        numTotalGoldCoins.setVisibility(View.INVISIBLE);
                        earnedTotal.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out));
                        earnedTotal.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });

        // Perform repeat button click to play audio files when user loads this activity
        repeat.performClick();
    }
}
