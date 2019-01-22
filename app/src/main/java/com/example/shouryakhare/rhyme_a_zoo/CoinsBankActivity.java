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

public class CoinsBankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coins_bank);

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

        final int totalSilver = totalCoins % 2;
        final int totalGold = totalCoins / 2;
        numTotalGoldCoins.setText(String.valueOf(totalGold));
        if (totalSilver != 0) {
            numTotalSilverCoins.setText(String.valueOf(totalSilver));
            plusSign.setText("+");
        }

        // too many coins to show so stop at 140
        if (currentCoins > 140) currentCoins = 140;

        if (currentCoins > 0) {
            LinearLayout layout = findViewById(R.id.coinShowLayout);

            // display twenty stack
            for (int i = 0; i < twenty; i++) {
                ImageView iv = new ImageView(this);
                iv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                iv.setImageResource(R.drawable.coin20);
                iv.getLayoutParams().height = 200;
                iv.getLayoutParams().width = 200;

                layout.addView(iv);
            }

            // display ten stack
            for (int i = 0; i < ten; i++) {
                ImageView iv = new ImageView(this);
                iv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                iv.setImageResource(R.drawable.coin10);
                iv.getLayoutParams().height = 200;
                iv.getLayoutParams().width = 200;

                layout.addView(iv);
            }

            // display five stack
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

        numTotalGoldCoins.setVisibility(View.INVISIBLE);
        plusSign.setVisibility(View.INVISIBLE);
        numTotalSilverCoins.setVisibility(View.INVISIBLE);
        earnedTotal.setVisibility(View.INVISIBLE);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(CoinsBankActivity.this, MainActivity.class);
                CoinsBankActivity.this.startActivity(myIntent);
            }
        });

        repeat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                repeat.setEnabled(false);
                home.setEnabled(false);

                MediaPlayer coinsAvailable = MediaPlayer.create(CoinsBankActivity.this, R.raw.coins_available);
                final MediaPlayer coinsTotal = MediaPlayer.create(CoinsBankActivity.this, R.raw.coins_total);

                coinsAvailable.start();
                coinsAvailable.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
//                        View currentView = CoinsBankActivity.this.findViewById(android.R.id.content);
//                        AlphaAnimation animate = new AlphaAnimation(1.0f, 0.4f);
//                        animate.setDuration(1000);
//                        animate.setFillAfter(true);
//                        currentView.startAnimation(animate);

                        numTotalGoldCoins.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
                        numTotalGoldCoins.setVisibility(View.VISIBLE);
                        earnedTotal.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
                        earnedTotal.setVisibility(View.VISIBLE);

                        if (totalSilver != 0) {
                            numTotalSilverCoins.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
                            numTotalSilverCoins.setVisibility(View.VISIBLE);

                            plusSign.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
                            plusSign.setVisibility(View.VISIBLE);
                        }

                        coinsTotal.start();
                    }
                });

                coinsTotal.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
//                        View currentView = CoinsBankActivity.this.findViewById(android.R.id.content);
//                        AlphaAnimation animate = new AlphaAnimation(0.4f, 1.0f);
//                        animate.setDuration(1000);
//                        currentView.startAnimation(animate);

                        repeat.setEnabled(true);
                        home.setEnabled(true);

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

        repeat.performClick();
    }

}
