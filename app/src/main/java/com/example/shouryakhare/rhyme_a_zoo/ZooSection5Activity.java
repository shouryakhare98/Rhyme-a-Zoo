package com.example.shouryakhare.rhyme_a_zoo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by heenapatel on 3/21/19.
 */

public class ZooSection5Activity extends AppCompatActivity  {

    Button home, cancel, back, zooCatch, number, info;
    Button rattlesnakeCoins, alligatorCoins, turtleCoins, iguanaCoins;
    ImageView rattlesnake, alligator, turtle, iguana;

    int[] rattlesnakeFactsList = {R.raw.rattlesnake_1, R.raw.rattlesnake_2, R.raw.rattlesnake_3};
    int[] alligatorFactsList = {R.raw.alligator_1, R.raw.alligator_2, R.raw.alligator_3};
    int[] turtleFactsList = {R.raw.turtle_1, R.raw.turtle_2, R.raw.turtle_3};
    int[] iguanaFactsList = {R.raw.iguana_1, R.raw.iguana_2, R.raw.iguana_3};

    MediaPlayer reptiles, rattlesnakeFact, alligatorFact, turtleFact, iguanaFact;
    MediaPlayer rattlesnakeSound, alligatorSound, turtleSound, iguanaSound;

    private float xcoord, ycoord;

    final int coinsNeeded = 20;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo_section5);

        this.pref = getApplicationContext().getSharedPreferences("MyPref", 0); //0 for private mode

        this.home = findViewById(R.id.zooSection5_home);
        this.cancel = findViewById(R.id.zooSection5_cancel);
        this.back = findViewById(R.id.zooSection5_back);
        this.zooCatch = findViewById(R.id.zooSection5_catch);
        this.number = findViewById(R.id.zooSection5_section);
        this.info = findViewById(R.id.zooSection5_info);

        this.rattlesnake = findViewById(R.id.zooSection_rattlesnake);
        this.alligator = findViewById(R.id.zooSection_alligator);
        this.turtle = findViewById(R.id.zooSection_turtle);
        this.iguana = findViewById(R.id.zooSection_iguana);

        this.rattlesnakeCoins = findViewById(R.id.zooSection_rattlesnakeCoins);
        this.alligatorCoins = findViewById(R.id.zooSection_alligatorCoins);
        this.turtleCoins = findViewById(R.id.zooSection_turtleCoins);
        this.iguanaCoins = findViewById(R.id.zooSection_iguanaCoins);

        this.cancel.setVisibility(View.INVISIBLE);
        this.cancel.setEnabled(false);

        this.reptiles = MediaPlayer.create(ZooSection5Activity.this, R.raw.reptiles);
        this.rattlesnakeFact = MediaPlayer.create(ZooSection5Activity.this, this.rattlesnakeFactsList[(int)(Math.random() * this.rattlesnakeFactsList.length)]);
        this.alligatorFact = MediaPlayer.create(ZooSection5Activity.this, this.alligatorFactsList[(int)(Math.random() * this.alligatorFactsList.length)]);
        this.turtleFact = MediaPlayer.create(ZooSection5Activity.this, this.turtleFactsList[(int)(Math.random() * this.turtleFactsList.length)]);
        this.iguanaFact = MediaPlayer.create(ZooSection5Activity.this, this.iguanaFactsList[(int)(Math.random() * this.iguanaFactsList.length)]);

        this.rattlesnakeSound = MediaPlayer.create(ZooSection5Activity.this, R.raw.rattlesnake);
        this.alligatorSound = MediaPlayer.create(ZooSection5Activity.this, R.raw.alligator);
        this.turtleSound = MediaPlayer.create(ZooSection5Activity.this, R.raw.turtle);
        this.iguanaSound = MediaPlayer.create(ZooSection5Activity.this, R.raw.iguana);

        //True if colored animal should be shown
        //False if grey animal should be shown
        SharedPreferences.Editor editor = this.pref.edit();
        if (!(this.pref.contains("rattlesnake")) || !(this.pref.contains("alligator")) || !(this.pref.contains("turtle")) || !(this.pref.contains("iguana"))) {
            //SharedPreferences does not contain currentCoins
            //Initialize current coins and total coins to 0

            editor.putBoolean("rattlesnake", false);
            editor.putBoolean("alligator", false);
            editor.putBoolean("turtle", false);
            editor.putBoolean("iguana", false);
        }
        if (!(this.pref.contains("totalAnimalsBought"))) {
            editor.putInt("totalAnimalsBought", 0);
        }
        editor.apply(); //commit changes

        checkIfEnoughAnimals(this.pref.getInt("totalAnimalsBought", 0));
        setButtonListeners();
        setAnimalListeners();
        setImageColor();
        setCoinListeners();
    }

    void toggleButtons(boolean enable) {
        int visibility = (enable)? View.VISIBLE : View.INVISIBLE;

        this.home.setEnabled(enable);
        this.home.setVisibility(visibility);
        this.back.setEnabled(enable);
        this.back.setVisibility(visibility);
        this.zooCatch.setEnabled(enable);
        this.zooCatch.setVisibility(visibility);
        this.number.setEnabled(enable);
        this.number.setVisibility(visibility);
        this.info.setEnabled(enable);
        this.info.setVisibility(visibility);

        this.rattlesnake.setEnabled(enable);
        this.alligator.setEnabled(enable);
        this.turtle.setEnabled(enable);
        this.iguana.setEnabled(enable);

        this.rattlesnakeCoins.setEnabled(enable);
        this.alligatorCoins.setVisibility(visibility);
        this.turtleCoins.setEnabled(enable);
        this.iguanaCoins.setVisibility(visibility);
        this.rattlesnakeCoins.setEnabled(enable);
        this.alligatorCoins.setVisibility(visibility);
        this.turtleCoins.setEnabled(enable);
        this.iguanaCoins.setVisibility(visibility);
    }

    void stopAllMedia() {
        if (this.reptiles.isPlaying()) this.reptiles.stop();
        if (this.rattlesnakeFact.isPlaying()) this.rattlesnakeFact.stop();
        if (this.alligatorFact.isPlaying()) this.alligatorFact.stop();
        if (this.turtleFact.isPlaying()) this.turtleFact.stop();
        if (this.iguanaFact.isPlaying()) this.iguanaFact.stop();
        if (this.rattlesnakeSound.isPlaying()) this.rattlesnakeSound.stop();
        if (this.alligatorSound.isPlaying()) this.alligatorSound.stop();
        if (this.turtleSound.isPlaying()) this.turtleSound.stop();
        if (this.iguanaSound.isPlaying()) this.iguanaSound.stop();
    }

    void setButtonListeners() {
        final ImageView iv = new ImageView(this);
        this.zooCatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleButtons(false);

                cancel.setVisibility(View.VISIBLE);
                cancel.setEnabled(true);

                String zookeeper = pref.getString("zookeeper", "zookeeper_boy1");

                ConstraintLayout layout = findViewById(R.id.zooSection5Activity_layout);
                iv.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
                iv.getLayoutParams().width = 300;
                iv.getLayoutParams().height = 500;
                iv.setImageResource(ZooSection5Activity.this.getApplicationContext().getResources().getIdentifier(zookeeper, "drawable", ZooSection5Activity.this.getPackageName()));
                iv.setX(300.0f);
                iv.setY(150.0f);

                iv.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getActionMasked()) {
                            case MotionEvent.ACTION_DOWN:
                                xcoord = view.getX() - motionEvent.getRawX();
                                ycoord = view.getY() - motionEvent.getRawY();
                                break;
                            case MotionEvent.ACTION_MOVE:
                                view.animate().x(motionEvent.getRawX() + xcoord).y(motionEvent.getRawY() + ycoord).setDuration(0).start();
                                break;
                            default:
                                return false;
                        }
                        return true;
                    }
                });

                layout.removeView(iv);
                layout.addView(iv);
            }
        });

        this.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv.setImageResource(android.R.color.transparent);
                toggleButtons(true);
                checkIfEnoughAnimals(pref.getInt("totalAnimalsBought", 0));
                cancel.setVisibility(View.INVISIBLE);
                cancel.setEnabled(false);
            }
        });

        this.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                Intent myIntent = new Intent(ZooSection5Activity.this, MainActivity.class);
                ZooSection5Activity.this.startActivity(myIntent);
            }
        });

        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                Intent myIntent = new Intent(ZooSection5Activity.this, ZooActivity.class);
                ZooSection5Activity.this.startActivity(myIntent);
            }
        });

        this.number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                reptiles = MediaPlayer.create(ZooSection5Activity.this, R.raw.reptiles);
                reptiles.start();
            }
        });

        this.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                reptiles = MediaPlayer.create(ZooSection5Activity.this, R.raw.reptiles);
                reptiles.start();
            }
        });
    }

    void setAnimalListeners() {
        this.rattlesnake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                rattlesnakeFact = MediaPlayer.create(ZooSection5Activity.this, rattlesnakeFactsList[(int)(Math.random() * rattlesnakeFactsList.length)]);
                rattlesnakeFact.start();
            }
        });

        this.turtle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                turtleFact = MediaPlayer.create(ZooSection5Activity.this, turtleFactsList[(int)(Math.random() * turtleFactsList.length)]);
                turtleFact.start();
            }
        });

        this.alligator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                alligatorFact = MediaPlayer.create(ZooSection5Activity.this, alligatorFactsList[(int)(Math.random() * alligatorFactsList.length)]);
                alligatorFact.start();
            }
        });

        this.iguana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                iguanaFact = MediaPlayer.create(ZooSection5Activity.this, iguanaFactsList[(int)(Math.random() * iguanaFactsList.length)]);
                iguanaFact.start();
            }
        });
    }

    void setCoinListeners() {
        this.rattlesnakeCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("rattlesnake", false)) {
                    rattlesnakeSound = MediaPlayer.create(ZooSection5Activity.this, R.raw.rattlesnake);
                    rattlesnakeSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection5Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("rattlesnake", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection5Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                rattlesnakeSound = MediaPlayer.create(ZooSection5Activity.this, R.raw.rattlesnake);
                                rattlesnakeSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });

        this.iguanaCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("iguana", false)) {
                    iguanaSound = MediaPlayer.create(ZooSection5Activity.this, R.raw.iguana);
                    iguanaSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection5Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("iguana", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection5Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                iguanaSound = MediaPlayer.create(ZooSection5Activity.this, R.raw.iguana);
                                iguanaSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });

        this.alligatorCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("alligator", false)) {
                    alligatorSound = MediaPlayer.create(ZooSection5Activity.this, R.raw.alligator);
                    alligatorSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection5Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("alligator", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection5Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                alligatorSound = MediaPlayer.create(ZooSection5Activity.this, R.raw.alligator);
                                alligatorSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });

        this.turtleCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("turtle", false)) {
                    turtleSound = MediaPlayer.create(ZooSection5Activity.this, R.raw.turtle);
                    turtleSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection5Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("turtle", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection5Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                turtleSound = MediaPlayer.create(ZooSection5Activity.this, R.raw.turtle);
                                turtleSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });
    }

    void setImageColor() {
        if (this.pref.getBoolean("rattlesnake", false)) {
            this.rattlesnake.setImageResource(R.drawable.rattlesnake_color);
            this.rattlesnakeCoins.setBackgroundResource(R.drawable.play3x);
            this.rattlesnakeCoins.setAlpha(1.0f);
        }

        if (this.pref.getBoolean("alligator", false)) {
            this.alligator.setImageResource(R.drawable.alligator_color);
            this.alligatorCoins.setBackgroundResource(R.drawable.play3x);
            this.alligatorCoins.setAlpha(1.0f);
        }

        if (this.pref.getBoolean("turtle", false)) {
            this.turtle.setImageResource(R.drawable.turtle_color);
            this.turtleCoins.setBackgroundResource(R.drawable.play3x);
            this.turtleCoins.setAlpha(1.0f);
        }

        if (this.pref.getBoolean("iguana", false)) {
            this.iguana.setImageResource(R.drawable.iguana_color);
            this.iguanaCoins.setBackgroundResource(R.drawable.play3x);
            this.iguanaCoins.setAlpha(1.0f);
        }
    }

    void checkIfEnoughAnimals(int totalAnimals) {
        if (totalAnimals < 16) {
            this.rattlesnakeCoins.setEnabled(false);
            this.rattlesnakeCoins.setVisibility(View.INVISIBLE);

            this.alligatorCoins.setEnabled(false);
            this.alligatorCoins.setVisibility(View.INVISIBLE);

            this.turtleCoins.setEnabled(false);
            this.turtleCoins.setVisibility(View.INVISIBLE);

            this.iguanaCoins.setEnabled(false);
            this.iguanaCoins.setVisibility(View.INVISIBLE);
        }
    }
}