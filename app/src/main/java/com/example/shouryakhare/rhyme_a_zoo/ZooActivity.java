package com.example.shouryakhare.rhyme_a_zoo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class ZooActivity extends AppCompatActivity {

    private float xcoord, ycoord;
    Button home, zooCatch, help, cancel;
    Button[] lowerSet, upperSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo);

        this.home = findViewById(R.id.zooActivity_home);
        this.zooCatch = findViewById(R.id.zooActivity_catch);
        this.lowerSet = new Button[] {
                findViewById(R.id.zooActivity_section1), findViewById(R.id.zooActivity_section2),
                findViewById(R.id.zooActivity_section3), findViewById(R.id.zooActivity_section4),
                findViewById(R.id.zooActivity_section5), findViewById(R.id.zooActivity_section6)
        };
        this.upperSet = new Button[] {
                findViewById(R.id.zooActivity_copysection1), findViewById(R.id.zooActivity_copysection2),
                findViewById(R.id.zooActivity_copysection3), findViewById(R.id.zooActivity_copysection4),
                findViewById(R.id.zooActivity_copysection5), findViewById(R.id.zooActivity_copysection6)
        };
        this.help = findViewById(R.id.zooActivity_help);
        this.cancel = findViewById(R.id.zooActivity_cancel);
        final ImageView iv = new ImageView(this);

        this.cancel.setEnabled(false);
        this.cancel.setVisibility(View.INVISIBLE);

        for (int i = 0; i < lowerSet.length; i++) {
            lowerSet[i].setOnClickListener(returnListener(i+1));
            upperSet[i].setOnClickListener(returnListener(i+1));
            upperSet[i].setBackgroundResource(android.R.color.transparent);
        }

        this.zooCatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleButtons(false);

                cancel.setVisibility(View.VISIBLE);
                cancel.setEnabled(true);

                SharedPreferences pref = getSharedPreferences("MyPref", 0);
                String zookeeper = pref.getString("zookeeper", "zookeeper_boy1");

                ConstraintLayout layout = findViewById(R.id.zooActivity_layout);
                iv.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
                iv.getLayoutParams().width = 300;
                iv.getLayoutParams().height = 500;
                iv.setImageResource(ZooActivity.this.getApplicationContext().getResources().getIdentifier(zookeeper, "drawable", ZooActivity.this.getPackageName()));
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

        this.help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < upperSet.length; i++) {
                    final int num = i;
                    upperSet[num].setBackgroundResource(ZooActivity.this.getApplicationContext().getResources().getIdentifier("number"+(num+1), "drawable", ZooActivity.this.getPackageName()));

                    upperSet[i].postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            upperSet[num].setBackgroundResource(android.R.color.transparent);
                        }
                    }, 5000);
                }
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
                Intent myIntent = new Intent(ZooActivity.this, MainActivity.class);
                ZooActivity.this.startActivity(myIntent);
            }
        });
    }

    View.OnClickListener returnListener(final int sectionNum) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ZooActivity.this, ZooActivity.class);
                switch (sectionNum) {
                    case 1:
                        myIntent = new Intent(ZooActivity.this, ZooSection1Activity.class);
                        break;
                    case 2:
                        myIntent = new Intent(ZooActivity.this, ZooSection2Activity.class);
                        break;
                    case 3:
                        myIntent = new Intent(ZooActivity.this, ZooSection3Activity.class);
                        break;
                    case 4:
                        myIntent = new Intent(ZooActivity.this, ZooSection4Activity.class);
                        break;
                    case 5:
                        myIntent = new Intent(ZooActivity.this, ZooSection5Activity.class);
                        break;
                    case 6:
                        myIntent = new Intent(ZooActivity.this, ZooSection6Activity.class);
                        break;
                }
                ZooActivity.this.startActivity(myIntent);
            }
        };
    }

    void toggleButtons(boolean enable) {
        int visibility = (enable)? View.VISIBLE : View.INVISIBLE;

        this.zooCatch.setEnabled(enable);
        this.zooCatch.setVisibility(visibility);

        this.home.setEnabled(enable);
        this.home.setVisibility(visibility);

        for (int i = 0; i < this.lowerSet.length; i++) {
            this.lowerSet[i].setVisibility(visibility);
            this.lowerSet[i].setEnabled(enable);

            this.upperSet[i].setEnabled(enable);
        }

        this.help.setVisibility(visibility);
        this.help.setEnabled(enable);
    }
}
