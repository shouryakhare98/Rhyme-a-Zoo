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

public class ZooSection6Activity extends AppCompatActivity  {

    Button home, cancel, back, zooCatch, number, info;
    Button chimpCoins, baboonCoins, gorillaCoins, monkeyCoins;
    ImageView chimp, baboon, gorilla, monkey;

    int[] chimpFactsList = {R.raw.chimp_1, R.raw.chimp_2, R.raw.chimp_3};
    int[] baboonFactsList = {R.raw.baboon_1, R.raw.baboon_2, R.raw.baboon_3};
    int[] gorillaFactsList = {R.raw.gorilla_1, R.raw.gorilla_2, R.raw.gorilla_3};
    int[] monkeyFactsList = {R.raw.monkey_1, R.raw.monkey_2, R.raw.monkey_3};

    MediaPlayer primates, chimpFact, baboonFact, gorillaFact, monkeyFact;
    MediaPlayer chimpSound, baboonSound, gorillaSound, monkeySound;

    private float xcoord, ycoord;

    final int coinsNeeded = 20;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo_section6);

        this.pref = getApplicationContext().getSharedPreferences("MyPref", 0); //0 for private mode

        this.home = findViewById(R.id.zooSection6_home);
        this.cancel = findViewById(R.id.zooSection6_cancel);
        this.back = findViewById(R.id.zooSection6_back);
        this.zooCatch = findViewById(R.id.zooSection6_catch);
        this.number = findViewById(R.id.zooSection6_section);
        this.info = findViewById(R.id.zooSection6_info);

        this.chimp = findViewById(R.id.zooSection_chimp);
        this.baboon = findViewById(R.id.zooSection_baboon);
        this.gorilla = findViewById(R.id.zooSection_gorilla);
        this.monkey = findViewById(R.id.zooSection_monkey);

        this.chimpCoins = findViewById(R.id.zooSection_chimpCoins);
        this.baboonCoins = findViewById(R.id.zooSection_baboonCoins);
        this.gorillaCoins = findViewById(R.id.zooSection_gorillaCoins);
        this.monkeyCoins = findViewById(R.id.zooSection_monkeyCoins);

        this.cancel.setVisibility(View.INVISIBLE);
        this.cancel.setEnabled(false);

        this.primates = MediaPlayer.create(ZooSection6Activity.this, R.raw.primates);
        this.chimpFact = MediaPlayer.create(ZooSection6Activity.this, this.chimpFactsList[(int)(Math.random() * this.chimpFactsList.length)]);
        this.baboonFact = MediaPlayer.create(ZooSection6Activity.this, this.baboonFactsList[(int)(Math.random() * this.baboonFactsList.length)]);
        this.gorillaFact = MediaPlayer.create(ZooSection6Activity.this, this.gorillaFactsList[(int)(Math.random() * this.gorillaFactsList.length)]);
        this.monkeyFact = MediaPlayer.create(ZooSection6Activity.this, this.monkeyFactsList[(int)(Math.random() * this.monkeyFactsList.length)]);

        this.chimpSound = MediaPlayer.create(ZooSection6Activity.this, R.raw.chimp);
        this.baboonSound = MediaPlayer.create(ZooSection6Activity.this, R.raw.baboon);
        this.gorillaSound = MediaPlayer.create(ZooSection6Activity.this, R.raw.gorilla);
        this.monkeySound = MediaPlayer.create(ZooSection6Activity.this, R.raw.monkey);

        //True if colored animal should be shown
        //False if grey animal should be shown
        SharedPreferences.Editor editor = this.pref.edit();
        if (!(this.pref.contains("chimp")) || !(this.pref.contains("baboon")) || !(this.pref.contains("gorilla")) || !(this.pref.contains("monkey"))) {
            //SharedPreferences does not contain currentCoins
            //Initialize current coins and total coins to 0

            editor.putBoolean("chimp", false);
            editor.putBoolean("baboon", false);
            editor.putBoolean("gorilla", false);
            editor.putBoolean("monkey", false);
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

        this.chimp.setEnabled(enable);
        this.baboon.setEnabled(enable);
        this.gorilla.setEnabled(enable);
        this.monkey.setEnabled(enable);

        this.chimpCoins.setEnabled(enable);
        this.baboonCoins.setVisibility(visibility);
        this.gorillaCoins.setEnabled(enable);
        this.monkeyCoins.setVisibility(visibility);
        this.chimpCoins.setEnabled(enable);
        this.baboonCoins.setVisibility(visibility);
        this.gorillaCoins.setEnabled(enable);
        this.monkeyCoins.setVisibility(visibility);
    }

    void stopAllMedia() {
        if (this.primates.isPlaying()) this.primates.stop();
        if (this.chimpFact.isPlaying()) this.chimpFact.stop();
        if (this.baboonFact.isPlaying()) this.baboonFact.stop();
        if (this.gorillaFact.isPlaying()) this.gorillaFact.stop();
        if (this.monkeyFact.isPlaying()) this.monkeyFact.stop();
        if (this.chimpSound.isPlaying()) this.chimpSound.stop();
        if (this.baboonSound.isPlaying()) this.baboonSound.stop();
        if (this.gorillaSound.isPlaying()) this.gorillaSound.stop();
        if (this.monkeySound.isPlaying()) this.monkeySound.stop();
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

                ConstraintLayout layout = findViewById(R.id.zooSection6Activity_layout);
                iv.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
                iv.getLayoutParams().width = 300;
                iv.getLayoutParams().height = 500;
                iv.setImageResource(ZooSection6Activity.this.getApplicationContext().getResources().getIdentifier(zookeeper, "drawable", ZooSection6Activity.this.getPackageName()));
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
                Intent myIntent = new Intent(ZooSection6Activity.this, MainActivity.class);
                ZooSection6Activity.this.startActivity(myIntent);
            }
        });

        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                Intent myIntent = new Intent(ZooSection6Activity.this, ZooActivity.class);
                ZooSection6Activity.this.startActivity(myIntent);
            }
        });

        this.number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                primates = MediaPlayer.create(ZooSection6Activity.this, R.raw.primates);
                primates.start();
            }
        });

        this.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                primates = MediaPlayer.create(ZooSection6Activity.this, R.raw.primates);
                primates.start();
            }
        });
    }

    void setAnimalListeners() {
        this.chimp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                chimpFact = MediaPlayer.create(ZooSection6Activity.this, chimpFactsList[(int)(Math.random() * chimpFactsList.length)]);
                chimpFact.start();
            }
        });

        this.gorilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                gorillaFact = MediaPlayer.create(ZooSection6Activity.this, gorillaFactsList[(int)(Math.random() * gorillaFactsList.length)]);
                gorillaFact.start();
            }
        });

        this.baboon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                baboonFact = MediaPlayer.create(ZooSection6Activity.this, baboonFactsList[(int)(Math.random() * baboonFactsList.length)]);
                baboonFact.start();
            }
        });

        this.monkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                monkeyFact = MediaPlayer.create(ZooSection6Activity.this, monkeyFactsList[(int)(Math.random() * monkeyFactsList.length)]);
                monkeyFact.start();
            }
        });
    }

    void setCoinListeners() {
        this.chimpCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("chimp", false)) {
                    chimpSound = MediaPlayer.create(ZooSection6Activity.this, R.raw.chimp);
                    chimpSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection6Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("chimp", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection6Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                chimpSound = MediaPlayer.create(ZooSection6Activity.this, R.raw.chimp);
                                chimpSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });

        this.monkeyCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("monkey", false)) {
                    monkeySound = MediaPlayer.create(ZooSection6Activity.this, R.raw.monkey);
                    monkeySound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection6Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("monkey", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection6Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                monkeySound = MediaPlayer.create(ZooSection6Activity.this, R.raw.monkey);
                                monkeySound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });

        this.baboonCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("baboon", false)) {
                    baboonSound = MediaPlayer.create(ZooSection6Activity.this, R.raw.baboon);
                    baboonSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection6Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("baboon", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection6Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                baboonSound = MediaPlayer.create(ZooSection6Activity.this, R.raw.baboon);
                                baboonSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });

        this.gorillaCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("gorilla", false)) {
                    gorillaSound = MediaPlayer.create(ZooSection6Activity.this, R.raw.gorilla);
                    gorillaSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection6Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("gorilla", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection6Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                gorillaSound = MediaPlayer.create(ZooSection6Activity.this, R.raw.gorilla);
                                gorillaSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });
    }

    void setImageColor() {
        if (this.pref.getBoolean("chimp", false)) {
            this.chimp.setImageResource(R.drawable.chimp_color);
            this.chimpCoins.setBackgroundResource(R.drawable.play3x);
            this.chimpCoins.setAlpha(1.0f);
        }

        if (this.pref.getBoolean("baboon", false)) {
            this.baboon.setImageResource(R.drawable.baboon_color);
            this.baboonCoins.setBackgroundResource(R.drawable.play3x);
            this.baboonCoins.setAlpha(1.0f);
        }

        if (this.pref.getBoolean("gorilla", false)) {
            this.gorilla.setImageResource(R.drawable.gorilla_color);
            this.gorillaCoins.setBackgroundResource(R.drawable.play3x);
            this.gorillaCoins.setAlpha(1.0f);
        }

        if (this.pref.getBoolean("monkey", false)) {
            this.monkey.setImageResource(R.drawable.monkey_color);
            this.monkeyCoins.setBackgroundResource(R.drawable.play3x);
            this.monkeyCoins.setAlpha(1.0f);
        }
    }

    void checkIfEnoughAnimals(int totalAnimals) {
        if (totalAnimals < 20) {
            this.chimpCoins.setEnabled(false);
            this.chimpCoins.setVisibility(View.INVISIBLE);

            this.baboonCoins.setEnabled(false);
            this.baboonCoins.setVisibility(View.INVISIBLE);

            this.gorillaCoins.setEnabled(false);
            this.gorillaCoins.setVisibility(View.INVISIBLE);

            this.monkeyCoins.setEnabled(false);
            this.monkeyCoins.setVisibility(View.INVISIBLE);
        }
    }
}