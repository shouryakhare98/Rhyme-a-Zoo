package com.example.shouryakhare.rhyme_a_zoo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CoinsBankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Retrive coin values with shared preferences
        SharedPreferences pref = getSharedPreferences("MyPref", 0);
        int currentCoins = pref.getInt("currentCoins", 0); //0 is default value if currentCoins does not exist
        int totalCoins = pref.getInt("totalCoins", 0); //0 is default value if totalCoins does not exist
        int coins = 0; //get coins here

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

        if (currentCoins >= 140) {
            // hide text view that says no coins
            //display 140 coins

        } if (currentCoins <= 0) {
            //show no coins text
        } else {
            // hide text view that says no coins
            //display coin piles

            
//            LinearLayout linearLayout= new LinearLayout(this);
//            linearLayout.setOrientation(LinearLayout.VERTICAL);
//
//            linearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
//                    LayoutParams.MATCH_PARENT));
//
//            ImageView imageView = new ImageView(this);
//
//            int id = getResources().getIdentifier("goldcoin", "drawable", getPackageName());
//            imageView.setImageResource(id);
//
//            imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
//                    LayoutParams.WRAP_CONTENT));
//            linearLayout.addView(imageView);
//            setContentView(linearLayout);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);


        final Button home = findViewById(R.id.bankActivity_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(CoinsBankActivity.this, MainActivity.class);
                CoinsBankActivity.this.startActivity(myIntent);
            }
        });

        TextView noCoins = findViewById(R.id.bankActivity_nocoins);
        ImageView emote = findViewById(R.id.bankActivity_worriedface);

        final Button repeat = findViewById(R.id.bankActivity_repeat);
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
                        View currentView = CoinsBankActivity.this.findViewById(android.R.id.content);
                        AlphaAnimation animate = new AlphaAnimation(1.0f, 0.4f);
                        animate.setDuration(1000);
                        animate.setFillAfter(true);
                        currentView.startAnimation(animate);

                        coinsTotal.start();
                    }
                });

                coinsTotal.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        View currentView = CoinsBankActivity.this.findViewById(android.R.id.content);
                        AlphaAnimation animate = new AlphaAnimation(0.4f, 1.0f);
                        animate.setDuration(1000);
                        currentView.startAnimation(animate);

                        repeat.setEnabled(true);
                        home.setEnabled(true);
                    }
                });
            }
        });

        repeat.performClick();
    }

}
