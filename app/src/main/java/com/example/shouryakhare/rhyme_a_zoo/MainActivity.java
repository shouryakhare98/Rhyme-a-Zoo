package com.example.shouryakhare.rhyme_a_zoo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bank = findViewById(R.id.mainActivity_bank);
        Button rhymeMenu = findViewById(R.id.mainActivity_rhymes);
        Button avatar = findViewById(R.id.mainActivity_zookeeper);
        TextView username = findViewById(R.id.mainActivity_username);
        Button exit = findViewById(R.id.mainActivity_logout);

        //Global variable of current coins using Shared Preferences
        //Set here
        //Can access in other activities
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); //0 for private mode
        SharedPreferences.Editor editor = pref.edit();
        if (!(pref.contains("currentCoins")) || !(pref.contains("totalCoins"))) {
            //SharedPreferences does not contain currentCoins
            //Initialize current coins and total coins to 0

            editor.putInt("currentCoins", 0);
            editor.putInt("totalCoins", 0);

            Intent videoIntent = new Intent(MainActivity.this, VideoActivity.class);
            MainActivity.this.startActivity(videoIntent);
        }
        editor.apply(); //commit changes

        bank.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Check if current coins are 0 or not
                //If 0, launch no coins activity
                //If not 0, display coins activity

                SharedPreferences pref = getSharedPreferences("MyPref", 0);
                int currentCoins = pref.getInt("currentCoins", 0); //0 is default value if totalCoins does not exist
                if (currentCoins <= 0) {
                    Intent bankIntent = new Intent(MainActivity.this, BankActivity.class);
                    MainActivity.this.startActivity(bankIntent);
                } else {
                    Intent bankIntent = new Intent(MainActivity.this, CoinsBankActivity.class);
                    MainActivity.this.startActivity(bankIntent);
                }
            }
        });

        rhymeMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent rhymeMenuIntent = new Intent(MainActivity.this, RhymeMenuActivity.class);
                MainActivity.this.startActivity(rhymeMenuIntent);
            }
        });

        avatar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent avatarIntent = new Intent(MainActivity.this, AvatarActivity.class);
                MainActivity.this.startActivity(avatarIntent);
            }
        });
//
//        username.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent avatarIntent = new Intent(MainActivity.this, AvatarActivity.class);
//                MainActivity.this.startActivity(avatarIntent);
//            }
//        });

        exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
