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

public class ZooSection3Activity extends AppCompatActivity  {

    Button home, cancel, back, zooCatch, number, info;
    Button sealCoins, squidCoins, dolphinCoins, sharkCoins;
    ImageView seal, squid, dolphin, shark;

    int[] sealFactsList = {R.raw.seal_1, R.raw.seal_2, R.raw.seal_3};
    int[] squidFactsList = {R.raw.squid_1, R.raw.squid_2, R.raw.squid_3};
    int[] dolphinFactsList = {R.raw.dolphin_1, R.raw.dolphin_2, R.raw.dolphin_3};
    int[] sharkFactsList = {R.raw.shark_1, R.raw.shark_2, R.raw.shark_3};

    MediaPlayer aquatic, sealFact, squidFact, dolphinFact, sharkFact;
    MediaPlayer sealSound, squidSound, dolphinSound, sharkSound;

    private float xcoord, ycoord;

    final int coinsNeeded = 20;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo_section3);

        this.pref = getApplicationContext().getSharedPreferences("MyPref", 0); //0 for private mode

        this.home = findViewById(R.id.zooSection3_home);
        this.cancel = findViewById(R.id.zooSection3_cancel);
        this.back = findViewById(R.id.zooSection3_back);
        this.zooCatch = findViewById(R.id.zooSection3_catch);
        this.number = findViewById(R.id.zooSection3_section);
        this.info = findViewById(R.id.zooSection3_info);

        this.seal = findViewById(R.id.zooSection_seal);
        this.squid = findViewById(R.id.zooSection_squid);
        this.dolphin = findViewById(R.id.zooSection_dolphin);
        this.shark = findViewById(R.id.zooSection_shark);

        this.sealCoins = findViewById(R.id.zooSection_sealCoins);
        this.squidCoins = findViewById(R.id.zooSection_squidCoins);
        this.dolphinCoins = findViewById(R.id.zooSection_dolphinCoins);
        this.sharkCoins = findViewById(R.id.zooSection_sharkCoins);

        this.cancel.setVisibility(View.INVISIBLE);
        this.cancel.setEnabled(false);

        this.aquatic = MediaPlayer.create(ZooSection3Activity.this, R.raw.aquatic);
        this.sealFact = MediaPlayer.create(ZooSection3Activity.this, this.sealFactsList[(int)(Math.random() * this.sealFactsList.length)]);
        this.squidFact = MediaPlayer.create(ZooSection3Activity.this, this.squidFactsList[(int)(Math.random() * this.squidFactsList.length)]);
        this.dolphinFact = MediaPlayer.create(ZooSection3Activity.this, this.dolphinFactsList[(int)(Math.random() * this.dolphinFactsList.length)]);
        this.sharkFact = MediaPlayer.create(ZooSection3Activity.this, this.sharkFactsList[(int)(Math.random() * this.sharkFactsList.length)]);

        this.sealSound = MediaPlayer.create(ZooSection3Activity.this, R.raw.seal);
        this.squidSound = MediaPlayer.create(ZooSection3Activity.this, R.raw.squid);
        this.dolphinSound = MediaPlayer.create(ZooSection3Activity.this, R.raw.dolphin);
        this.sharkSound = MediaPlayer.create(ZooSection3Activity.this, R.raw.shark);

        //True if colored animal should be shown
        //False if grey animal should be shown
        SharedPreferences.Editor editor = this.pref.edit();
        if (!(this.pref.contains("seal")) || !(this.pref.contains("squid")) || !(this.pref.contains("dolphin")) || !(this.pref.contains("shark"))) {
            //SharedPreferences does not contain currentCoins
            //Initialize current coins and total coins to 0

            editor.putBoolean("seal", false);
            editor.putBoolean("squid", false);
            editor.putBoolean("dolphin", false);
            editor.putBoolean("shark", false);
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

        this.seal.setEnabled(enable);
        this.squid.setEnabled(enable);
        this.dolphin.setEnabled(enable);
        this.shark.setEnabled(enable);

        this.sealCoins.setEnabled(enable);
        this.squidCoins.setVisibility(visibility);
        this.dolphinCoins.setEnabled(enable);
        this.sharkCoins.setVisibility(visibility);
        this.sealCoins.setEnabled(enable);
        this.squidCoins.setVisibility(visibility);
        this.dolphinCoins.setEnabled(enable);
        this.sharkCoins.setVisibility(visibility);
    }

    void stopAllMedia() {
        if (this.aquatic.isPlaying()) this.aquatic.stop();
        if (this.sealFact.isPlaying()) this.sealFact.stop();
        if (this.squidFact.isPlaying()) this.squidFact.stop();
        if (this.dolphinFact.isPlaying()) this.dolphinFact.stop();
        if (this.sharkFact.isPlaying()) this.sharkFact.stop();
        if (this.sealSound.isPlaying()) this.sealSound.stop();
        if (this.squidSound.isPlaying()) this.squidSound.stop();
        if (this.dolphinSound.isPlaying()) this.dolphinSound.stop();
        if (this.sharkSound.isPlaying()) this.sharkSound.stop();
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

                ConstraintLayout layout = findViewById(R.id.zooSection3Activity_layout);
                iv.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
                iv.getLayoutParams().width = 300;
                iv.getLayoutParams().height = 500;
                iv.setImageResource(ZooSection3Activity.this.getApplicationContext().getResources().getIdentifier(zookeeper, "drawable", ZooSection3Activity.this.getPackageName()));
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
                Intent myIntent = new Intent(ZooSection3Activity.this, MainActivity.class);
                ZooSection3Activity.this.startActivity(myIntent);
            }
        });

        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                Intent myIntent = new Intent(ZooSection3Activity.this, ZooActivity.class);
                ZooSection3Activity.this.startActivity(myIntent);
            }
        });

        this.number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                aquatic = MediaPlayer.create(ZooSection3Activity.this, R.raw.aquatic);
                aquatic.start();
            }
        });

        this.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                aquatic = MediaPlayer.create(ZooSection3Activity.this, R.raw.aquatic);
                aquatic.start();
            }
        });
    }

    void setAnimalListeners() {
        this.seal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                sealFact = MediaPlayer.create(ZooSection3Activity.this, sealFactsList[(int)(Math.random() * sealFactsList.length)]);
                sealFact.start();
            }
        });

        this.dolphin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                dolphinFact = MediaPlayer.create(ZooSection3Activity.this, dolphinFactsList[(int)(Math.random() * dolphinFactsList.length)]);
                dolphinFact.start();
            }
        });

        this.squid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                squidFact = MediaPlayer.create(ZooSection3Activity.this, squidFactsList[(int)(Math.random() * squidFactsList.length)]);
                squidFact.start();
            }
        });

        this.shark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                sharkFact = MediaPlayer.create(ZooSection3Activity.this, sharkFactsList[(int)(Math.random() * sharkFactsList.length)]);
                sharkFact.start();
            }
        });
    }

    void setCoinListeners() {
        this.sealCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("seal", false)) {
                    sealSound = MediaPlayer.create(ZooSection3Activity.this, R.raw.seal);
                    sealSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection3Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("seal", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection3Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                sealSound = MediaPlayer.create(ZooSection3Activity.this, R.raw.seal);
                                sealSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });

        this.sharkCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("shark", false)) {
                    sharkSound = MediaPlayer.create(ZooSection3Activity.this, R.raw.shark);
                    sharkSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection3Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("shark", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection3Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                sharkSound = MediaPlayer.create(ZooSection3Activity.this, R.raw.shark);
                                sharkSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });

        this.squidCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("squid", false)) {
                    squidSound = MediaPlayer.create(ZooSection3Activity.this, R.raw.squid);
                    squidSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection3Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("squid", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection3Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                squidSound = MediaPlayer.create(ZooSection3Activity.this, R.raw.squid);
                                squidSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });

        this.dolphinCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("dolphin", false)) {
                    dolphinSound = MediaPlayer.create(ZooSection3Activity.this, R.raw.dolphin);
                    dolphinSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection3Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("dolphin", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection3Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                dolphinSound = MediaPlayer.create(ZooSection3Activity.this, R.raw.dolphin);
                                dolphinSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });
    }

    void setImageColor() {
        if (this.pref.getBoolean("seal", false)) {
            this.seal.setImageResource(R.drawable.seal_color);
            this.sealCoins.setBackgroundResource(R.drawable.play3x);
            this.sealCoins.setAlpha(1.0f);
        }

        if (this.pref.getBoolean("squid", false)) {
            this.squid.setImageResource(R.drawable.squid_color);
            this.squidCoins.setBackgroundResource(R.drawable.play3x);
            this.squidCoins.setAlpha(1.0f);
        }

        if (this.pref.getBoolean("dolphin", false)) {
            this.dolphin.setImageResource(R.drawable.dolphin_color);
            this.dolphinCoins.setBackgroundResource(R.drawable.play3x);
            this.dolphinCoins.setAlpha(1.0f);
        }

        if (this.pref.getBoolean("shark", false)) {
            this.shark.setImageResource(R.drawable.shark_color);
            this.sharkCoins.setBackgroundResource(R.drawable.play3x);
            this.sharkCoins.setAlpha(1.0f);
        }
    }

    void checkIfEnoughAnimals(int totalAnimals) {
        if (totalAnimals < 8) {
            this.sealCoins.setEnabled(false);
            this.sealCoins.setVisibility(View.INVISIBLE);

            this.squidCoins.setEnabled(false);
            this.squidCoins.setVisibility(View.INVISIBLE);

            this.dolphinCoins.setEnabled(false);
            this.dolphinCoins.setVisibility(View.INVISIBLE);

            this.sharkCoins.setEnabled(false);
            this.sharkCoins.setVisibility(View.INVISIBLE);
        }
    }
}