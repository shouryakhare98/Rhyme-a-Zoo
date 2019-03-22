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

public class ZooSection2Activity extends AppCompatActivity  {

    Button home, cancel, back, zooCatch, number, info;
    Button owlCoins, flamingoCoins, ostrichCoins, parrotCoins;
    ImageView owl, flamingo, ostrich, parrot;

    int[] owlFactsList = {R.raw.owl_1, R.raw.owl_2, R.raw.owl_3};
    int[] flamingoFactsList = {R.raw.flamingo_1, R.raw.flamingo_2, R.raw.flamingo_3};
    int[] ostrichFactsList = {R.raw.ostrich_1, R.raw.ostrich_2, R.raw.ostrich_3};
    int[] parrotFactsList = {R.raw.parrot_1, R.raw.parrot_2, R.raw.parrot_3};

    MediaPlayer birds, owlFact, flamingoFact, ostrichFact, parrotFact;
    MediaPlayer owlSound, flamingoSound, ostrichSound, parrotSound;

    private float xcoord, ycoord;

    final int coinsNeeded = 20;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo_section2);

        this.pref = getApplicationContext().getSharedPreferences("MyPref", 0); //0 for private mode

        this.home = findViewById(R.id.zooSection2_home);
        this.cancel = findViewById(R.id.zooSection2_cancel);
        this.back = findViewById(R.id.zooSection2_back);
        this.zooCatch = findViewById(R.id.zooSection2_catch);
        this.number = findViewById(R.id.zooSection2_section);
        this.info = findViewById(R.id.zooSection2_info);

        this.owl = findViewById(R.id.zooSection_owl);
        this.flamingo = findViewById(R.id.zooSection_flamingo);
        this.ostrich = findViewById(R.id.zooSection_ostrich);
        this.parrot = findViewById(R.id.zooSection_parrot);

        this.owlCoins = findViewById(R.id.zooSection_owlCoins);
        this.flamingoCoins = findViewById(R.id.zooSection_flamingoCoins);
        this.ostrichCoins = findViewById(R.id.zooSection_ostrichCoins);
        this.parrotCoins = findViewById(R.id.zooSection_parrotCoins);

        this.cancel.setVisibility(View.INVISIBLE);
        this.cancel.setEnabled(false);

        this.birds = MediaPlayer.create(ZooSection2Activity.this, R.raw.birds);
        this.owlFact = MediaPlayer.create(ZooSection2Activity.this, this.owlFactsList[(int)(Math.random() * this.owlFactsList.length)]);
        this.flamingoFact = MediaPlayer.create(ZooSection2Activity.this, this.flamingoFactsList[(int)(Math.random() * this.flamingoFactsList.length)]);
        this.ostrichFact = MediaPlayer.create(ZooSection2Activity.this, this.ostrichFactsList[(int)(Math.random() * this.ostrichFactsList.length)]);
        this.parrotFact = MediaPlayer.create(ZooSection2Activity.this, this.parrotFactsList[(int)(Math.random() * this.parrotFactsList.length)]);

        this.owlSound = MediaPlayer.create(ZooSection2Activity.this, R.raw.owl);
        this.flamingoSound = MediaPlayer.create(ZooSection2Activity.this, R.raw.flamingo);
        this.ostrichSound = MediaPlayer.create(ZooSection2Activity.this, R.raw.ostrich);
        this.parrotSound = MediaPlayer.create(ZooSection2Activity.this, R.raw.parrot);

        //True if colored animal should be shown
        //False if grey animal should be shown
        SharedPreferences.Editor editor = this.pref.edit();
        if (!(this.pref.contains("owl")) || !(this.pref.contains("flamingo")) || !(this.pref.contains("ostrich")) || !(this.pref.contains("parrot"))) {
            //SharedPreferences does not contain currentCoins
            //Initialize current coins and total coins to 0

            editor.putBoolean("owl", false);
            editor.putBoolean("flamingo", false);
            editor.putBoolean("ostrich", false);
            editor.putBoolean("parrot", false);
        }
        if (!(this.pref.contains("totalAnimalsBought"))) {
            editor.putInt("totalAnimalsBought", 0);
        } else if (this.pref.getInt("totalAnimalsBought", 0) < 4) {
            this.owlCoins.setEnabled(false);
            this.owlCoins.setVisibility(View.INVISIBLE);

            this.flamingoCoins.setEnabled(false);
            this.flamingoCoins.setVisibility(View.INVISIBLE);

            this.ostrichCoins.setEnabled(false);
            this.ostrichCoins.setVisibility(View.INVISIBLE);

            this.parrotCoins.setEnabled(false);
            this.parrotCoins.setVisibility(View.INVISIBLE);
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

        this.owl.setEnabled(enable);
        this.flamingo.setEnabled(enable);
        this.ostrich.setEnabled(enable);
        this.parrot.setEnabled(enable);

        this.owlCoins.setEnabled(enable);
        this.flamingoCoins.setVisibility(visibility);
        this.ostrichCoins.setEnabled(enable);
        this.parrotCoins.setVisibility(visibility);
        this.owlCoins.setEnabled(enable);
        this.flamingoCoins.setVisibility(visibility);
        this.ostrichCoins.setEnabled(enable);
        this.parrotCoins.setVisibility(visibility);
    }

    void stopAllMedia() {
        if (this.birds.isPlaying()) this.birds.stop();
        if (this.owlFact.isPlaying()) this.owlFact.stop();
        if (this.flamingoFact.isPlaying()) this.flamingoFact.stop();
        if (this.ostrichFact.isPlaying()) this.ostrichFact.stop();
        if (this.parrotFact.isPlaying()) this.parrotFact.stop();
        if (this.owlSound.isPlaying()) this.owlSound.stop();
        if (this.flamingoSound.isPlaying()) this.flamingoSound.stop();
        if (this.ostrichSound.isPlaying()) this.ostrichSound.stop();
        if (this.parrotSound.isPlaying()) this.parrotSound.stop();
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
                iv.setImageResource(ZooSection2Activity.this.getApplicationContext().getResources().getIdentifier(zookeeper, "drawable", ZooSection2Activity.this.getPackageName()));
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
                Intent myIntent = new Intent(ZooSection2Activity.this, MainActivity.class);
                ZooSection2Activity.this.startActivity(myIntent);
            }
        });

        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                Intent myIntent = new Intent(ZooSection2Activity.this, ZooActivity.class);
                ZooSection2Activity.this.startActivity(myIntent);
            }
        });

        this.number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                birds = MediaPlayer.create(ZooSection2Activity.this, R.raw.birds);
                birds.start();
            }
        });

        this.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                birds = MediaPlayer.create(ZooSection2Activity.this, R.raw.birds);
                birds.start();
            }
        });
    }

    void setAnimalListeners() {
        this.owl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                owlFact = MediaPlayer.create(ZooSection2Activity.this, owlFactsList[(int)(Math.random() * owlFactsList.length)]);
                owlFact.start();
            }
        });

        this.ostrich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                ostrichFact = MediaPlayer.create(ZooSection2Activity.this, ostrichFactsList[(int)(Math.random() * ostrichFactsList.length)]);
                ostrichFact.start();
            }
        });

        this.flamingo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                flamingoFact = MediaPlayer.create(ZooSection2Activity.this, flamingoFactsList[(int)(Math.random() * flamingoFactsList.length)]);
                flamingoFact.start();
            }
        });

        this.parrot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                parrotFact = MediaPlayer.create(ZooSection2Activity.this, parrotFactsList[(int)(Math.random() * parrotFactsList.length)]);
                parrotFact.start();
            }
        });
    }

    void setCoinListeners() {
        this.owlCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("owl", false)) {
                    owlSound = MediaPlayer.create(ZooSection2Activity.this, R.raw.owl);
                    owlSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection2Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("owl", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection2Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                owlSound = MediaPlayer.create(ZooSection2Activity.this, R.raw.owl);
                                owlSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });

        this.parrotCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("parrot", false)) {
                    parrotSound = MediaPlayer.create(ZooSection2Activity.this, R.raw.parrot);
                    parrotSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection2Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("parrot", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection2Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                parrotSound = MediaPlayer.create(ZooSection2Activity.this, R.raw.parrot);
                                parrotSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });

        this.flamingoCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("flamingo", false)) {
                    flamingoSound = MediaPlayer.create(ZooSection2Activity.this, R.raw.flamingo);
                    flamingoSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection2Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("flamingo", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection2Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                flamingoSound = MediaPlayer.create(ZooSection2Activity.this, R.raw.flamingo);
                                flamingoSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });

        this.ostrichCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllMedia();
                if (pref.getBoolean("ostrich", false)) {
                    ostrichSound = MediaPlayer.create(ZooSection2Activity.this, R.raw.ostrich);
                    ostrichSound.start();
                } else {
                    int currentCoins = pref.getInt("currentCoins", 0);

                    if (currentCoins < coinsNeeded) {
                        MediaPlayer moreMoney = MediaPlayer.create(ZooSection2Activity.this, R.raw.more_money);
                        moreMoney.start();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("currentCoins", currentCoins - coinsNeeded);
                        editor.putBoolean("ostrich", true);
                        editor.putInt("totalAnimalsBought", pref.getInt("totalAnimalsBought", 0)+1);
                        editor.apply();
                        setImageColor();

                        MediaPlayer correct = MediaPlayer.create(ZooSection2Activity.this, R.raw.correct);
                        correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                ostrichSound = MediaPlayer.create(ZooSection2Activity.this, R.raw.ostrich);
                                ostrichSound.start();
                            }
                        });
                        correct.start();
                    }
                }
            }
        });
    }

    void setImageColor() {
        if (this.pref.getBoolean("owl", false)) {
            this.owl.setImageResource(R.drawable.owl_color);
            this.owlCoins.setBackgroundResource(R.drawable.play3x);
            this.owlCoins.setAlpha(1.0f);
        }

        if (this.pref.getBoolean("flamingo", false)) {
            this.flamingo.setImageResource(R.drawable.flamingo_color);
            this.flamingoCoins.setBackgroundResource(R.drawable.play3x);
            this.flamingoCoins.setAlpha(1.0f);
        }

        if (this.pref.getBoolean("ostrich", false)) {
            this.ostrich.setImageResource(R.drawable.ostrich_color);
            this.ostrichCoins.setBackgroundResource(R.drawable.play3x);
            this.ostrichCoins.setAlpha(1.0f);
        }

        if (this.pref.getBoolean("parrot", false)) {
            this.parrot.setImageResource(R.drawable.parrot_color);
            this.parrotCoins.setBackgroundResource(R.drawable.play3x);
            this.parrotCoins.setAlpha(1.0f);
        }
    }
}