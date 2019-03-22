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

public class ZooSection1Activity extends AppCompatActivity  {

    Button home, cancel, back, zooCatch, number, info;
    Button kangarooCoins, pandaCoins, giraffeCoins, hippoCoins;
    ImageView kangaroo, panda, giraffe, hippo;

    int[] kangarooFactsList = {R.raw.kangaroo_1, R.raw.kangaroo_2, R.raw.kangaroo_3};
    int[] pandaFactsList = {R.raw.panda_1, R.raw.panda_2, R.raw.panda_3};
    int[] hippoFactsList = {R.raw.hippo_1, R.raw.hippo_2, R.raw.hippo_3};
    int[] giraffeFactsList = {R.raw.giraffe_1, R.raw.giraffe_2, R.raw.giraffe_3};

    MediaPlayer herbivores, kangarooFact, pandaFact, giraffeFact, hippoFact;
    MediaPlayer kangarooSound, pandaSound, giraffeSound, hippoSound;

    private float xcoord, ycoord;

    final int coinsNeeded = 20;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo_section1);

        this.pref = getApplicationContext().getSharedPreferences("MyPref", 0); //0 for private mode

        this.home = findViewById(R.id.zooSection1_home);
        this.cancel = findViewById(R.id.zooSection1_cancel);
        this.back = findViewById(R.id.zooSection1_back);
        this.zooCatch = findViewById(R.id.zooSection1_catch);
        this.number = findViewById(R.id.zooSection1_section);
        this.info = findViewById(R.id.zooSection1_info);

        this.kangaroo = findViewById(R.id.zooSection_kangaroo);
        this.panda = findViewById(R.id.zooSection_panda);
        this.giraffe = findViewById(R.id.zooSection_giraffe);
        this.hippo = findViewById(R.id.zooSection_hippo);

        this.kangarooCoins = findViewById(R.id.zooSection_kangarooCoins);
        this.pandaCoins = findViewById(R.id.zooSection_pandaCoins);
        this.giraffeCoins = findViewById(R.id.zooSection_giraffeCoins);
        this.hippoCoins = findViewById(R.id.zooSection_hippoCoins);

        this.cancel.setVisibility(View.INVISIBLE);
        this.cancel.setEnabled(false);

        this.herbivores = MediaPlayer.create(ZooSection1Activity.this, R.raw.herbivores);
        this.giraffeFact = MediaPlayer.create(ZooSection1Activity.this, this.giraffeFactsList[(int)(Math.random() * this.giraffeFactsList.length)]);
        this.pandaFact = MediaPlayer.create(ZooSection1Activity.this, this.pandaFactsList[(int)(Math.random() * this.pandaFactsList.length)]);
        this.kangarooFact = MediaPlayer.create(ZooSection1Activity.this, this.kangarooFactsList[(int)(Math.random() * this.kangarooFactsList.length)]);
        this.hippoFact = MediaPlayer.create(ZooSection1Activity.this, this.hippoFactsList[(int)(Math.random() * this.hippoFactsList.length)]);

        this.giraffeSound = MediaPlayer.create(ZooSection1Activity.this, R.raw.giraffe);
        this.pandaSound = MediaPlayer.create(ZooSection1Activity.this, R.raw.panda);
        this.hippoSound = MediaPlayer.create(ZooSection1Activity.this, R.raw.hippo);
        this.kangarooSound = MediaPlayer.create(ZooSection1Activity.this, R.raw.kangaroo);

        //True if colored animal should be shown
        //False if grey animal should be shown
        SharedPreferences.Editor editor = this.pref.edit();
        if (!(this.pref.contains("kangaroo")) || !(this.pref.contains("hippo")) || !(this.pref.contains("panda")) || !(this.pref.contains("giraffe"))) {
            //SharedPreferences does not contain currentCoins
            //Initialize current coins and total coins to 0

            editor.putBoolean("kangaroo", false);
            editor.putBoolean("hippo", false);
            editor.putBoolean("panda", false);
            editor.putBoolean("giraffe", false);
        }
        if (!(this.pref.contains("totalAnimalsBought"))) {
            editor.putInt("totalAnimalsBought", 0);
        }
        editor.apply(); //commit changes

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

        this.kangaroo.setEnabled(enable);
        this.panda.setEnabled(enable);
        this.giraffe.setEnabled(enable);
        this.hippo.setEnabled(enable);

        this.kangarooCoins.setEnabled(enable);
        this.kangarooCoins.setVisibility(visibility);
        this.pandaCoins.setEnabled(enable);
        this.pandaCoins.setVisibility(visibility);
        this.giraffeCoins.setEnabled(enable);
        this.giraffeCoins.setVisibility(visibility);
        this.hippoCoins.setEnabled(enable);
        this.hippoCoins.setVisibility(visibility);
    }

    void stopAllMedia() {
        if (this.herbivores.isPlaying()) this.herbivores.stop();
        if (this.kangarooFact.isPlaying()) this.kangarooFact.stop();
        if (this.pandaFact.isPlaying()) this.pandaFact.stop();
        if (this.giraffeFact.isPlaying()) this.giraffeFact.stop();
        if (this.hippoFact.isPlaying()) this.hippoFact.stop();
        if (this.kangarooSound.isPlaying()) this.kangarooSound.stop();
        if (this.pandaSound.isPlaying()) this.pandaSound.stop();
        if (this.giraffeSound.isPlaying()) this.giraffeSound.stop();
        if (this.hippoSound.isPlaying()) this.hippoSound.stop();
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

                ConstraintLayout layout = findViewById(R.id.zooSection1_layout);
                iv.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
                iv.getLayoutParams().width = 300;
                iv.getLayoutParams().height = 500;
                iv.setImageResource(ZooSection1Activity.this.getApplicationContext().getResources().getIdentifier(zookeeper, "drawable", ZooSection1Activity.this.getPackageName()));
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
                cancel.setVisibility(View.INVISIBLE);
                cancel.setEnabled(false);
            }
        });

        this.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                Intent myIntent = new Intent(ZooSection1Activity.this, MainActivity.class);
                ZooSection1Activity.this.startActivity(myIntent);
            }
        });

        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                Intent myIntent = new Intent(ZooSection1Activity.this, ZooActivity.class);
                ZooSection1Activity.this.startActivity(myIntent);
            }
        });

        this.number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                herbivores = MediaPlayer.create(ZooSection1Activity.this, R.raw.herbivores);
                herbivores.start();
            }
        });

        this.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                herbivores = MediaPlayer.create(ZooSection1Activity.this, R.raw.herbivores);
                herbivores.start();
            }
        });
    }

    void setAnimalListeners() {
        this.kangaroo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                kangarooFact = MediaPlayer.create(ZooSection1Activity.this, kangarooFactsList[(int)(Math.random() * kangarooFactsList.length)]);
                kangarooFact.start();
            }
        });

        this.giraffe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                giraffeFact = MediaPlayer.create(ZooSection1Activity.this, giraffeFactsList[(int)(Math.random() * giraffeFactsList.length)]);
                giraffeFact.start();
            }
        });

        this.panda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                pandaFact = MediaPlayer.create(ZooSection1Activity.this, pandaFactsList[(int)(Math.random() * pandaFactsList.length)]);
                pandaFact.start();
            }
        });

        this.hippo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                hippoFact = MediaPlayer.create(ZooSection1Activity.this, hippoFactsList[(int)(Math.random() * hippoFactsList.length)]);
                hippoFact.start();
            }
        });
    }

    void setCoinListeners() {
        this.kangarooCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("kangaroo", false)) {
                    kangarooSound = MediaPlayer.create(ZooSection1Activity.this, R.raw.kangaroo);
                    kangarooSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection1Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("kangaroo", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection1Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                kangarooSound = MediaPlayer.create(ZooSection1Activity.this, R.raw.kangaroo);
                                kangarooSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });

        this.hippoCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("hippo", false)) {
                    hippoSound = MediaPlayer.create(ZooSection1Activity.this, R.raw.hippo);
                    hippoSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection1Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("hippo", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection1Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                hippoSound = MediaPlayer.create(ZooSection1Activity.this, R.raw.hippo);
                                hippoSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });

        this.pandaCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("panda", false)) {
                    pandaSound = MediaPlayer.create(ZooSection1Activity.this, R.raw.panda);
                    pandaSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection1Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("panda", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection1Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                pandaSound = MediaPlayer.create(ZooSection1Activity.this, R.raw.panda);
                                pandaSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });

        this.giraffeCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("giraffe", false)) {
                    giraffeSound = MediaPlayer.create(ZooSection1Activity.this, R.raw.giraffe);
                    giraffeSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection1Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("giraffe", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection1Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                giraffeSound = MediaPlayer.create(ZooSection1Activity.this, R.raw.giraffe);
                                giraffeSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });
    }

    void setImageColor() {
        if (this.pref.getBoolean("kangaroo", false)) {
            this.kangaroo.setImageResource(R.drawable.kangaroo_color);
            this.kangarooCoins.setBackgroundResource(R.drawable.play3x);
            this.kangarooCoins.setAlpha(1.0f);
        }

        if (this.pref.getBoolean("panda", false)) {
            this.panda.setImageResource(R.drawable.panda_color);
            this.pandaCoins.setBackgroundResource(R.drawable.play3x);
            this.pandaCoins.setAlpha(1.0f);
        }

        if (this.pref.getBoolean("giraffe", false)) {
            this.giraffe.setImageResource(R.drawable.giraffe_color);
            this.giraffeCoins.setBackgroundResource(R.drawable.play3x);
            this.giraffeCoins.setAlpha(1.0f);
        }

        if (this.pref.getBoolean("hippo", false)) {
            this.hippo.setImageResource(R.drawable.hippo_color);
            this.hippoCoins.setBackgroundResource(R.drawable.play3x);
            this.hippoCoins.setAlpha(1.0f);
        }
    }
}