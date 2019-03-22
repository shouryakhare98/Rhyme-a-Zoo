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

public class ZooSection5Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo_section5);

        //True if colored animal should be shown
        //False if grey animal should be shown
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); //0 for private mode
        SharedPreferences.Editor editor = pref.edit();
        if (!(pref.contains("rattlesnake")) || !(pref.contains("alligator")) || !(pref.contains("turtle")) || !(pref.contains("iguana"))) {
            //SharedPreferences does not contain currentCoins
            //Initialize current coins and total coins to 0

            editor.putBoolean("rattlesnake", false);
            editor.putBoolean("alligator", false);
            editor.putBoolean("turtle", false);
            editor.putBoolean("iguana", false);
        }
        editor.apply(); //commit changes
    }
}
