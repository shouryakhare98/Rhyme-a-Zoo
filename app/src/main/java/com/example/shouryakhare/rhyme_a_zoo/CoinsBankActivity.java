package com.example.shouryakhare.rhyme_a_zoo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;

/**
 * Activity to show the bank when the user has non-zero coins to spend
 */
public class CoinsBankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coins_bank);

        // Get all views
        final Button repeat = findViewById(R.id.bankActivity_repeat);
        final Button home = findViewById(R.id.bankActivity_home);
        final TextView numTotalGoldCoins = findViewById(R.id.bankActivity_numgold);
        final TextView numTotalSilverCoins = findViewById(R.id.bankActivity_numsilver);
        final TextView plusSign = findViewById(R.id.bankActivity_plus);
        final TextView earnedTotal = findViewById(R.id.bankActivity_earnedtotal);

        //Retrieve coin values with shared preferences
        SharedPreferences pref = getSharedPreferences("MyPref", 0);
        int currentCoins = pref.getInt("currentCoins", 0); //0 is default value if currentCoins does not exist
        int totalCoins = pref.getInt("totalCoins", 0); //0 is default value if totalCoins does not exist

        // Math to get current silver and gold coins in the bank
        int silver = currentCoins % 2;
        currentCoins = currentCoins - silver;
        int gold = currentCoins / 2;
        int twenty = gold / 20;
        gold = gold % 20;
        int ten = gold /10;
        gold = gold % 10;
        int five = gold / 5;
        gold = gold % 5;
        int one = gold;

        // Math to get total silver and gold coins ever earned
        final int totalSilver = totalCoins % 2;
        final int totalGold = totalCoins / 2;

        // Set text view to number of total gold coins
        numTotalGoldCoins.setText(String.valueOf(totalGold));
        // Display silver text view only if number of silver coins is non-zero
        if (totalSilver != 0) {
            numTotalSilverCoins.setText(String.valueOf(totalSilver));
            plusSign.setText("+");
        }

        // Too many coins to show so stop at 140
        if (currentCoins > 140) currentCoins = 140;

        // Display current coins in the form of coin images
        if (currentCoins > 0) {
            // Create linear layout to which images are added
            LinearLayout layout = findViewById(R.id.coinShowLayout);

            // display twenty coin stack
            for (int i = 0; i < twenty; i++) {
                ImageView iv = new ImageView(this);
                iv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                iv.setImageResource(R.drawable.coin20);
                iv.getLayoutParams().height = 200;
                iv.getLayoutParams().width = 200;

                layout.addView(iv);
            }

            // display ten coin stack
            for (int i = 0; i < ten; i++) {
                ImageView iv = new ImageView(this);
                iv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                iv.setImageResource(R.drawable.coin10);
                iv.getLayoutParams().height = 200;
                iv.getLayoutParams().width = 200;

                layout.addView(iv);
            }

            // display five coin stack
            for (int i = 0; i < five; i++) {
                ImageView iv = new ImageView(this);
                iv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                iv.setImageResource(R.drawable.coinstack);
                iv.getLayoutParams().height = 200;
                iv.getLayoutParams().width = 200;

                layout.addView(iv);
            }

            // display single coin
            for (int i = 0; i < one; i++) {
                ImageView iv = new ImageView(this);
                iv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                iv.setImageResource(R.drawable.coingold);
                iv.getLayoutParams().height = 200;
                iv.getLayoutParams().width = 200;

                layout.addView(iv);
            }

            // display silver coin
            for (int i = 0; i < silver; i++) {
                ImageView iv = new ImageView(this);
                iv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                iv.setImageResource(R.drawable.coinsilver);
                iv.getLayoutParams().height = 200;
                iv.getLayoutParams().width = 200;

                layout.addView(iv);
            }
        }

        // Set total coins graphic to invisible
        numTotalGoldCoins.setVisibility(View.INVISIBLE);
        plusSign.setVisibility(View.INVISIBLE);
        numTotalSilverCoins.setVisibility(View.INVISIBLE);
        earnedTotal.setVisibility(View.INVISIBLE);

        // Get audio files
        final MediaPlayer coinsAvailable = MediaPlayer.create(CoinsBankActivity.this, R.raw.coins_available);
        final MediaPlayer coinsTotal = MediaPlayer.create(CoinsBankActivity.this, R.raw.coins_total);

        // If home button pressed, go to MainActivity
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coinsAvailable.stop();
                coinsTotal.stop();
                Intent myIntent = new Intent(CoinsBankActivity.this, MainActivity.class);
                CoinsBankActivity.this.startActivity(myIntent);
            }
        });

        // If repeat button pressed, play coin graphic
        repeat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Dim repeat button and disable it
                repeat.setAlpha(0.5f);
                repeat.setEnabled(false);

                // Start coins available audio
                coinsAvailable.start();
                // Show total coins graphic when coins available audio ends
                coinsAvailable.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {

                        numTotalGoldCoins.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
                        numTotalGoldCoins.setVisibility(View.VISIBLE);
                        earnedTotal.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
                        earnedTotal.setVisibility(View.VISIBLE);

                        // Show total silver coins if number of silver coins is non-zero
                        if (totalSilver != 0) {
                            numTotalSilverCoins.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
                            numTotalSilverCoins.setVisibility(View.VISIBLE);

                            plusSign.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
                            plusSign.setVisibility(View.VISIBLE);
                        }

                        coinsTotal.start();
                    }
                });

                // Hide total coins graphic at the end of total coins audio
                coinsTotal.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {

                        repeat.setAlpha(1.0f);
                        repeat.setEnabled(true);

                        numTotalGoldCoins.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out));
                        numTotalGoldCoins.setVisibility(View.INVISIBLE);
                        earnedTotal.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out));
                        earnedTotal.setVisibility(View.INVISIBLE);

                        if (totalSilver != 0) {
                            numTotalSilverCoins.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out));
                            numTotalSilverCoins.setVisibility(View.INVISIBLE);

                            plusSign.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out));
                            plusSign.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });

        // Start coins graphic when the user loads into the activity
        repeat.performClick();
    }

}
