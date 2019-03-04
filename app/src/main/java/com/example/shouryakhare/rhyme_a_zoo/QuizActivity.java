package com.example.shouryakhare.rhyme_a_zoo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

public class QuizActivity extends AppCompatActivity {

    Button home;
    Button replay;
    ImageView rhyme;

    TextView question;
    ImageView[] optionButtons;
    TextView[] optionTexts;

    String[] questions;
    String[][] options;

    Button transition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        this.home = findViewById(R.id.quizActivity_home);
        this.replay = findViewById(R.id.quizActivity_replay);
        this.rhyme = findViewById(R.id.quizActivity_rhymeButton);
        this.question = findViewById(R.id.quizActivity_question);
        this.optionButtons = new ImageView[] {
                findViewById(R.id.quizActivity_option1button), findViewById(R.id.quizActivity_option2button),
                findViewById(R.id.quizActivity_option3button), findViewById(R.id.quizActivity_option4button)
        };
        this.optionTexts = new TextView[] {
                findViewById(R.id.quizActivity_option1text), findViewById(R.id.quizActivity_option2text),
                findViewById(R.id.quizActivity_option3text), findViewById(R.id.quizActivity_option4text)
        };
        this.transition = findViewById(R.id.quizActivity_transitionButton);

        final int rhymeIndex = getIntent().getIntExtra("id",0);

        JSONReader reader = new JSONReader(this);
        IDProvider idp = new IDProvider();

        this.questions = reader.getQuestions(rhymeIndex);
        this.options = reader.getOptions(rhymeIndex);

        final int[] lastQuestion = {getIntent().getIntExtra("lastQuestion", -1)};
        int correctOption;
        final int[] numTries = {1};
        final int[] coinsEarned = {getIntent().getIntExtra("coinsEarned", 0)};

        if (lastQuestion[0] == 2) {
            lastQuestion[0] = 4;
        } else {
            lastQuestion[0]++;
        }
        correctOption = randomDisplay(lastQuestion[0]);

        for (int i = 0; i < this.optionButtons.length; i++) {
            final int index = i;
            if (i == correctOption) {
                this.optionButtons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MediaPlayer correct = MediaPlayer.create(QuizActivity.this, R.raw.correct);
                        correct.start();

                        for (int j = 0; j < optionButtons.length; j++) {
                            if (j != index) {
                                if (optionButtons[j].getVisibility() == View.VISIBLE) optionButtons[j].startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out));
                                optionButtons[j].setVisibility(View.INVISIBLE);

                                if (optionTexts[j].getVisibility() == View.VISIBLE) optionTexts[j].startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out));
                                optionTexts[j].setVisibility(View.INVISIBLE);
                            }
                        }

                        if (numTries[0] == 1) coinsEarned[0] += 2;
                        else if (numTries[0] == 2) coinsEarned[0] += 1;

                        (new Handler()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                transition.performClick();
                            }
                        }, 2000);
                    }
                });
            } else {
                this.optionButtons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MediaPlayer incorrect = MediaPlayer.create(QuizActivity.this, R.raw.incorrect);
                        incorrect.start();

                        optionButtons[index].startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out));
                        optionButtons[index].setVisibility(View.INVISIBLE);

                        optionTexts[index].startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out));
                        optionTexts[index].setVisibility(View.INVISIBLE);

                        numTries[0]++;
                    }
                });
            }
        }

        this.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(QuizActivity.this, MainActivity.class);
                QuizActivity.this.startActivity(myIntent);
            }
        });

        this.rhyme.setImageResource(idp.getThumbnailId(rhymeIndex));
        this.rhyme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(QuizActivity.this, RhymeActivity.class);
                myIntent.putExtra("id", rhymeIndex);
                QuizActivity.this.startActivity(myIntent);
            }
        });

        this.replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        this.transition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lastQuestion[0] == 4) {
                    Intent myIntent = new Intent(QuizActivity.this, QuizCompletedActivity.class);
                    myIntent.putExtra("coinsEarned", coinsEarned[0]);
                    myIntent.putExtra("id", rhymeIndex);
                    QuizActivity.this.startActivity(myIntent);
                } else {
                    Intent myIntent = new Intent(QuizActivity.this, QuizActivity.class);
                    myIntent.putExtra("coinsEarned", coinsEarned[0]);
                    myIntent.putExtra("lastQuestion", lastQuestion[0]);
                    myIntent.putExtra("id", rhymeIndex);
                    QuizActivity.this.startActivity(myIntent);
                }
            }
        });

        this.replay.performClick();
    }

    private int randomDisplay(int questionIndex) {
        int[] indexArray = {0,1,2,3};
        shuffle(indexArray);

        int correct = 0;

        for (int i = 0; i < indexArray.length; i++) {
            String optionText = this.options[questionIndex][indexArray[i]];
            optionText = optionText.replaceAll("[ -]", "_").toLowerCase();

            optionButtons[i].setBackgroundResource(this.getApplicationContext().getResources().getIdentifier(optionText, "drawable", this.getPackageName()));

            optionText = StringUtils.capitalize(optionText);
            this.optionTexts[i].setText(optionText);

            if (indexArray[i] == 0) correct = i;
        }

        this.question.setText(this.questions[questionIndex]);

        return correct;
    }

    private void shuffle(int[] array) {
        int index;
        java.util.Random random = new java.util.Random();
        for (int i = array.length-1; i > 0; i--) {
            index = random.nextInt(i+1);
            if (index != i) {
                array[index] ^= array[i];
                array[i] ^= array[index];
                array[index] ^= array[i];
            }
        }
    }
}
