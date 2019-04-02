package com.example.shouryakhare.rhyme_a_zoo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

/**
 * The home screen of the application
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get views
        Button bank = findViewById(R.id.mainActivity_bank);
        Button rhymeMenu = findViewById(R.id.mainActivity_rhymes);
        Button avatar = findViewById(R.id.mainActivity_zookeeper);
        Button exit = findViewById(R.id.mainActivity_logout);
        Button zoo = findViewById(R.id.mainActivity_zoo);

        //Global variable of current coins using Shared Preference set here
        //Can access in other activities
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); //0 for private mode
        SharedPreferences.Editor editor = pref.edit();
        if (!(pref.contains("currentCoins")) || !(pref.contains("totalCoins"))) {
            //SharedPreferences does not contain currentCoins or totalCoins
            //Initialize current coins and total coins to 0

            editor.putInt("currentCoins", 0);
            editor.putInt("totalCoins", 0);

            // This also means that application was opened for the first time so show intro video
            Intent videoIntent = new Intent(MainActivity.this, VideoActivity.class);
            MainActivity.this.startActivity(videoIntent);
        }
        editor.apply(); //commit changes

        // If bank button is pressed, go to bank activity
        bank.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Check if current coins are 0 or not
                //If 0, launch bank activity
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

        // If rhyme button is pressed, go to RhymeMenuActivity
        rhymeMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent rhymeMenuIntent = new Intent(MainActivity.this, RhymeMenuActivity.class);
                MainActivity.this.startActivity(rhymeMenuIntent);
            }
        });

        // If avatar button is pressed, go to AvatarActivity
        avatar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent avatarIntent = new Intent(MainActivity.this, AvatarActivity.class);
                MainActivity.this.startActivity(avatarIntent);
            }
        });

        // If zoo button is pressed, go to ZooActivity
        zoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent zooIntent = new Intent(MainActivity.this, ZooActivity.class);
                MainActivity.this.startActivity(zooIntent);
            }
        });

        // If exit button is pressed, leave the application
        exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }

    // Default MainActivity methods required by Android Studio
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
