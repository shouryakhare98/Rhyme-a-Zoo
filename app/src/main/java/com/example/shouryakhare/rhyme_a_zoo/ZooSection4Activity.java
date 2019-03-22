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

public class ZooSection4Activity extends AppCompatActivity  {

    Button home, cancel, back, zooCatch, number, info;
    Button lionCoins, tigerCoins, jaguarCoins, wolfCoins;
    ImageView lion, tiger, jaguar, wolf;

    int[] lionFactsList = {R.raw.lion_1, R.raw.lion_2, R.raw.lion_3};
    int[] tigerFactsList = {R.raw.tiger_1, R.raw.tiger_2, R.raw.tiger_3};
    int[] jaguarFactsList = {R.raw.jaguar_1, R.raw.jaguar_2, R.raw.jaguar_3};
    int[] wolfFactsList = {R.raw.wolf_1, R.raw.wolf_2, R.raw.wolf_3};

    MediaPlayer carnivores, lionFact, tigerFact, jaguarFact, wolfFact;
    MediaPlayer lionSound, tigerSound, jaguarSound, wolfSound;

    private float xcoord, ycoord;

    final int coinsNeeded = 20;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo_section4);

        this.pref = getApplicationContext().getSharedPreferences("MyPref", 0); //0 for private mode

        this.home = findViewById(R.id.zooSection4_home);
        this.cancel = findViewById(R.id.zooSection4_cancel);
        this.back = findViewById(R.id.zooSection4_back);
        this.zooCatch = findViewById(R.id.zooSection4_catch);
        this.number = findViewById(R.id.zooSection4_section);
        this.info = findViewById(R.id.zooSection4_info);

        this.lion = findViewById(R.id.zooSection_lion);
        this.tiger = findViewById(R.id.zooSection_tiger);
        this.jaguar = findViewById(R.id.zooSection_jaguar);
        this.wolf = findViewById(R.id.zooSection_wolf);

        this.lionCoins = findViewById(R.id.zooSection_lionCoins);
        this.tigerCoins = findViewById(R.id.zooSection_tigerCoins);
        this.jaguarCoins = findViewById(R.id.zooSection_jaguarCoins);
        this.wolfCoins = findViewById(R.id.zooSection_wolfCoins);

        this.cancel.setVisibility(View.INVISIBLE);
        this.cancel.setEnabled(false);

        this.carnivores = MediaPlayer.create(ZooSection4Activity.this, R.raw.carnivores);
        this.lionFact = MediaPlayer.create(ZooSection4Activity.this, this.lionFactsList[(int)(Math.random() * this.lionFactsList.length)]);
        this.tigerFact = MediaPlayer.create(ZooSection4Activity.this, this.tigerFactsList[(int)(Math.random() * this.tigerFactsList.length)]);
        this.jaguarFact = MediaPlayer.create(ZooSection4Activity.this, this.jaguarFactsList[(int)(Math.random() * this.jaguarFactsList.length)]);
        this.wolfFact = MediaPlayer.create(ZooSection4Activity.this, this.wolfFactsList[(int)(Math.random() * this.wolfFactsList.length)]);

        this.lionSound = MediaPlayer.create(ZooSection4Activity.this, R.raw.lion);
        this.tigerSound = MediaPlayer.create(ZooSection4Activity.this, R.raw.tiger);
        this.jaguarSound = MediaPlayer.create(ZooSection4Activity.this, R.raw.jaguar);
        this.wolfSound = MediaPlayer.create(ZooSection4Activity.this, R.raw.wolf);

        //True if colored animal should be shown
        //False if grey animal should be shown
        SharedPreferences.Editor editor = this.pref.edit();
        if (!(this.pref.contains("lion")) || !(this.pref.contains("tiger")) || !(this.pref.contains("jaguar")) || !(this.pref.contains("wolf"))) {
            //SharedPreferences does not contain currentCoins
            //Initialize current coins and total coins to 0

            editor.putBoolean("lion", false);
            editor.putBoolean("tiger", false);
            editor.putBoolean("jaguar", false);
            editor.putBoolean("wolf", false);
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

        this.lion.setEnabled(enable);
        this.tiger.setEnabled(enable);
        this.jaguar.setEnabled(enable);
        this.wolf.setEnabled(enable);

        this.lionCoins.setEnabled(enable);
        this.tigerCoins.setVisibility(visibility);
        this.jaguarCoins.setEnabled(enable);
        this.wolfCoins.setVisibility(visibility);
        this.lionCoins.setEnabled(enable);
        this.tigerCoins.setVisibility(visibility);
        this.jaguarCoins.setEnabled(enable);
        this.wolfCoins.setVisibility(visibility);
    }

    void stopAllMedia() {
        if (this.carnivores.isPlaying()) this.carnivores.stop();
        if (this.lionFact.isPlaying()) this.lionFact.stop();
        if (this.tigerFact.isPlaying()) this.tigerFact.stop();
        if (this.jaguarFact.isPlaying()) this.jaguarFact.stop();
        if (this.wolfFact.isPlaying()) this.wolfFact.stop();
        if (this.lionSound.isPlaying()) this.lionSound.stop();
        if (this.tigerSound.isPlaying()) this.tigerSound.stop();
        if (this.jaguarSound.isPlaying()) this.jaguarSound.stop();
        if (this.wolfSound.isPlaying()) this.wolfSound.stop();
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

                ConstraintLayout layout = findViewById(R.id.zooSection4Activity_layout);
                iv.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
                iv.getLayoutParams().width = 300;
                iv.getLayoutParams().height = 500;
                iv.setImageResource(ZooSection4Activity.this.getApplicationContext().getResources().getIdentifier(zookeeper, "drawable", ZooSection4Activity.this.getPackageName()));
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
                Intent myIntent = new Intent(ZooSection4Activity.this, MainActivity.class);
                ZooSection4Activity.this.startActivity(myIntent);
            }
        });

        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                Intent myIntent = new Intent(ZooSection4Activity.this, ZooActivity.class);
                ZooSection4Activity.this.startActivity(myIntent);
            }
        });

        this.number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                carnivores = MediaPlayer.create(ZooSection4Activity.this, R.raw.carnivores);
                carnivores.start();
            }
        });

        this.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                carnivores = MediaPlayer.create(ZooSection4Activity.this, R.raw.carnivores);
                carnivores.start();
            }
        });
    }

    void setAnimalListeners() {
        this.lion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                lionFact = MediaPlayer.create(ZooSection4Activity.this, lionFactsList[(int)(Math.random() * lionFactsList.length)]);
                lionFact.start();
            }
        });

        this.jaguar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                jaguarFact = MediaPlayer.create(ZooSection4Activity.this, jaguarFactsList[(int)(Math.random() * jaguarFactsList.length)]);
                jaguarFact.start();
            }
        });

        this.tiger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                tigerFact = MediaPlayer.create(ZooSection4Activity.this, tigerFactsList[(int)(Math.random() * tigerFactsList.length)]);
                tigerFact.start();
            }
        });

        this.wolf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                wolfFact = MediaPlayer.create(ZooSection4Activity.this, wolfFactsList[(int)(Math.random() * wolfFactsList.length)]);
                wolfFact.start();
            }
        });
    }

    void setCoinListeners() {
        this.lionCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("lion", false)) {
                    lionSound = MediaPlayer.create(ZooSection4Activity.this, R.raw.lion);
                    lionSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection4Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("lion", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection4Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                lionSound = MediaPlayer.create(ZooSection4Activity.this, R.raw.lion);
                                lionSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });

        this.wolfCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("wolf", false)) {
                    wolfSound = MediaPlayer.create(ZooSection4Activity.this, R.raw.wolf);
                    wolfSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection4Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("wolf", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection4Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                wolfSound = MediaPlayer.create(ZooSection4Activity.this, R.raw.wolf);
                                wolfSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });

        this.tigerCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("tiger", false)) {
                    tigerSound = MediaPlayer.create(ZooSection4Activity.this, R.raw.tiger);
                    tigerSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection4Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("tiger", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection4Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                tigerSound = MediaPlayer.create(ZooSection4Activity.this, R.raw.tiger);
                                tigerSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });

        this.jaguarCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("jaguar", false)) {
                    jaguarSound = MediaPlayer.create(ZooSection4Activity.this, R.raw.jaguar);
                    jaguarSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection4Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("jaguar", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection4Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                jaguarSound = MediaPlayer.create(ZooSection4Activity.this, R.raw.jaguar);
                                jaguarSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });
    }

    void setImageColor() {
        if (this.pref.getBoolean("lion", false)) {
            this.lion.setImageResource(R.drawable.lion_color);
            this.lionCoins.setBackgroundResource(R.drawable.play3x);
            this.lionCoins.setAlpha(1.0f);
        }

        if (this.pref.getBoolean("tiger", false)) {
            this.tiger.setImageResource(R.drawable.tiger_color);
            this.tigerCoins.setBackgroundResource(R.drawable.play3x);
            this.tigerCoins.setAlpha(1.0f);
        }

        if (this.pref.getBoolean("jaguar", false)) {
            this.jaguar.setImageResource(R.drawable.jaguar_color);
            this.jaguarCoins.setBackgroundResource(R.drawable.play3x);
            this.jaguarCoins.setAlpha(1.0f);
        }

        if (this.pref.getBoolean("wolf", false)) {
            this.wolf.setImageResource(R.drawable.wolf_color);
            this.wolfCoins.setBackgroundResource(R.drawable.play3x);
            this.wolfCoins.setAlpha(1.0f);
        }
    }

    void checkIfEnoughAnimals(int totalAnimals) {
        if (totalAnimals < 12) {
            this.lionCoins.setEnabled(false);
            this.lionCoins.setVisibility(View.INVISIBLE);

            this.tigerCoins.setEnabled(false);
            this.tigerCoins.setVisibility(View.INVISIBLE);

            this.jaguarCoins.setEnabled(false);
            this.jaguarCoins.setVisibility(View.INVISIBLE);

            this.wolfCoins.setEnabled(false);
            this.wolfCoins.setVisibility(View.INVISIBLE);
        }
    }
}