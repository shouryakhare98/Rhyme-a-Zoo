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
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
/**
 * Created by heenapatel on 3/21/19.
 */

public class ZooSection2Activity extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo_section2);

        //True if colored animal should be shown
        //False if grey animal should be shown
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); //0 for private mode
        SharedPreferences.Editor editor = pref.edit();
        if (!(pref.contains("owl")) || !(pref.contains("flamingo")) || !(pref.contains("ostrich")) || !(pref.contains("parrot"))) {
            //SharedPreferences does not contain currentCoins
            //Initialize current coins and total coins to 0

            editor.putBoolean("owl", false);
            editor.putBoolean("flamingo", false);
            editor.putBoolean("ostrich", false);
            editor.putBoolean("parrot", false);
        }
        editor.apply(); //commit changes
    }
}
