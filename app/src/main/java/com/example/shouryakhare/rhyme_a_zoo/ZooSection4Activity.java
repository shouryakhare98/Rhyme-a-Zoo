package com.example.shouryakhare.rhyme_a_zoo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by heenapatel on 3/21/19.
 */

public class ZooSection4Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo_section4);

        //True if colored animal should be shown
        //False if grey animal should be shown
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); //0 for private mode
        SharedPreferences.Editor editor = pref.edit();
        if (!(pref.contains("lion")) || !(pref.contains("tiger")) || !(pref.contains("jaguar")) || !(pref.contains("wolf"))) {
            //SharedPreferences does not contain currentCoins
            //Initialize current coins and total coins to 0

            editor.putBoolean("lion", false);
            editor.putBoolean("tiger", false);
            editor.putBoolean("jaguar", false);
            editor.putBoolean("wolf", false);
        }
        editor.apply(); //commit changes
    }
}
