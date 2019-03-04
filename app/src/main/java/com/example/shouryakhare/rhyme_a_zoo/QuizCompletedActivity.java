package com.example.shouryakhare.rhyme_a_zoo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class QuizCompletedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_completed);

        Button home = findViewById(R.id.quizCompleted_home);
        ImageView rhyme = findViewById(R.id.quizCompleted_rhymeButton);
        Button back = findViewById(R.id.quizCompleted_back);
        Button next = findViewById(R.id.quizCompleted_next);
        LinearLayout coinShowLayout = findViewById(R.id.quizCompleted_coinShowLayout);
        TextView message = findViewById(R.id.quizCompleted_message);

        final int coinsEarned = (getIntent().getIntExtra("coinsEarned", 0) == 8) ? 10 : getIntent().getIntExtra("coinsEarned", 0);
        final int rhymeIndex = getIntent().getIntExtra("id", 0);

        if (rhymeIndex == 0) {
            back.setEnabled(false);
            back.setVisibility(View.INVISIBLE);
        } else if (rhymeIndex == (new IDProvider()).getThumbnailArrayLength()-1) {
            next.setEnabled(false);
            next.setVisibility(View.INVISIBLE);
        }

        java.util.Random random = new java.util.Random();
        IDProvider idp = new IDProvider();

        final MediaPlayer failure = MediaPlayer.create(QuizCompletedActivity.this, R.raw.quiz_over_bad);
        final MediaPlayer success = MediaPlayer.create(QuizCompletedActivity.this, R.raw.quiz_over);
        final MediaPlayer do_better = MediaPlayer.create(QuizCompletedActivity.this, idp.getDoBetter(random.nextInt(idp.getDoBetterArrayLength())));
        final MediaPlayer encouragement = MediaPlayer.create(QuizCompletedActivity.this, idp.getEncouragement(random.nextInt(idp.getEncouragementArrayLength())));

        int gold = coinsEarned / 2;
        int silver = coinsEarned % 2;

        if (coinsEarned == 0) {
            for (int i = 0; i < 5; i++) {
                ImageView iv = new ImageView(this);
                iv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                iv.setImageResource(R.drawable.goldcoin);
                iv.getLayoutParams().height = 200;
                iv.getLayoutParams().width = 200;
                iv.setAlpha(0.5f);
                coinShowLayout.addView(iv);
            }

            failure.start();
            failure.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    do_better.start();
                }
            });
        } else {
            for (int i = 0; i < gold; i++) {
                ImageView iv = new ImageView(this);
                iv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                iv.setImageResource(R.drawable.coingold);
                iv.getLayoutParams().height = 200;
                iv.getLayoutParams().width = 200;
                coinShowLayout.addView(iv);
            }

            for (int i = 0; i < silver; i++) {
                ImageView iv = new ImageView(this);
                iv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                iv.setImageResource(R.drawable.coinsilver);
                iv.getLayoutParams().height = 200;
                iv.getLayoutParams().width = 200;
                coinShowLayout.addView(iv);
            }

            success.start();
            success.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    if (coinsEarned < 5) do_better.start();
                    else encouragement.start();
                }
            });
        }

        if (coinsEarned == 0) message.setText("You didn't earn any coins.");
        else if (silver == 0) {
            String singPlu = "coins";
            if (gold == 1) singPlu = "coin";
            message.setText("You earned " + gold + " gold " + singPlu + "!");
        } else {
            String singPlu = "coins";
            if (gold == 1) singPlu = "coin";
            message.setText("You earned " + gold + " gold " + singPlu + " and 1 silver coin!");
        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                success.stop();
                failure.stop();
                do_better.stop();
                encouragement.stop();
                Intent myIntent = new Intent(QuizCompletedActivity.this, MainActivity.class);
                QuizCompletedActivity.this.startActivity(myIntent);
            }
        });

        rhyme.setBackgroundResource(idp.getThumbnailId(rhymeIndex));
        rhyme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                success.stop();
                failure.stop();
                do_better.stop();
                encouragement.stop();
                Intent myIntent = new Intent(QuizCompletedActivity.this, MainActivity.class);
                myIntent.putExtra("id", rhymeIndex);
                QuizCompletedActivity.this.startActivity(myIntent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                success.stop();
                failure.stop();
                do_better.stop();
                encouragement.stop();
                Intent myIntent = new Intent(QuizCompletedActivity.this, RhymeActivity.class);
                myIntent.putExtra("id", rhymeIndex-1);
                QuizCompletedActivity.this.startActivity(myIntent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                success.stop();
                failure.stop();
                do_better.stop();
                encouragement.stop();
                Intent myIntent = new Intent(QuizCompletedActivity.this, RhymeActivity.class);
                myIntent.putExtra("id", rhymeIndex+1);
                QuizCompletedActivity.this.startActivity(myIntent);
            }
        });

        SharedPreferences pref = getSharedPreferences("MyPref", 0);
        int currentCoins = pref.getInt("currentCoins", 0); //0 is default value if currentCoins does not exist
        int totalCoins = pref.getInt("totalCoins", 0); //0 is default value if totalCoins does not exist

        currentCoins += coinsEarned;
        totalCoins += coinsEarned;
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("currentCoins", currentCoins);
        editor.putInt("totalCoins", totalCoins);

        if (!pref.contains(rhymeIndex+"_coins") || pref.getInt(rhymeIndex+"_coins", 0) == 0) {
            editor.putInt(rhymeIndex+"_coins", coinsEarned);
        }
        editor.apply();
    }
}
