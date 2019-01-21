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

public class BankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        final Button repeat = findViewById(R.id.bankActivity_repeat);
        final Button home = findViewById(R.id.bankActivity_home);
        final TextView numTotalGoldCoins = findViewById(R.id.bankActivity_numgold);
        final TextView earnedTotal = findViewById(R.id.bankActivity_earnedtotal);

        numTotalGoldCoins.setVisibility(View.INVISIBLE);
        numTotalGoldCoins.setText(String.valueOf(0));
        earnedTotal.setVisibility(View.INVISIBLE);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(BankActivity.this, MainActivity.class);
                BankActivity.this.startActivity(myIntent);
            }
        });

        repeat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                repeat.setEnabled(false);
                home.setEnabled(false);

                MediaPlayer coinsAvailable = MediaPlayer.create(BankActivity.this, R.raw.coins_available);
                final MediaPlayer coinsTotal = MediaPlayer.create(BankActivity.this, R.raw.coins_total);

                coinsAvailable.start();
                coinsAvailable.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        View currentView = BankActivity.this.findViewById(android.R.id.content);
                        AlphaAnimation animate = new AlphaAnimation(1.0f, 0.4f);
                        animate.setDuration(1000);
                        animate.setFillAfter(true);
                        currentView.startAnimation(animate);

                        numTotalGoldCoins.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
                        numTotalGoldCoins.setVisibility(View.VISIBLE);
                        earnedTotal.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
                        earnedTotal.setVisibility(View.VISIBLE);

                        coinsTotal.start();
                    }
                });

                coinsTotal.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        View currentView = BankActivity.this.findViewById(android.R.id.content);
                        AlphaAnimation animate = new AlphaAnimation(0.4f, 1.0f);
                        animate.setDuration(1000);
                        currentView.startAnimation(animate);

                        repeat.setEnabled(true);
                        home.setEnabled(true);

                        numTotalGoldCoins.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out));
                        numTotalGoldCoins.setVisibility(View.INVISIBLE);
                        earnedTotal.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out));
                        earnedTotal.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });

        repeat.performClick();
    }
}
