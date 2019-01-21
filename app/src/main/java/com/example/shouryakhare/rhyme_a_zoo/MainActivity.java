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

        //Global variable of current coins using Shared Preferences
        //Set here
        //Can access in other activities
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); //0 for private mode
        SharedPreferences.Editor editor = pref.edit();
        if (!(pref.contains("currentCoins"))) {
            //SharedPreferences does not contain currentCoins
            //Initialize current coins to 0

            editor.putInt("currentCoins", 0);
        }
        if (!(pref.contains("totalCoins"))) {
            //SharedPreferences does not contain totalCoins
            //Initialize total coins to 0

            editor.putInt("totalCoins", 0);
        }
        editor.commit(); //commit changes

        final Button bank = findViewById(R.id.mainActivity_bank);
        bank.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent bankIntent = new Intent(MainActivity.this, BankActivity.class);
                MainActivity.this.startActivity(bankIntent);
            }
        });

        final Button avatar = findViewById(R.id.mainActivity_avatar);
        avatar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent avatarIntent = new Intent(MainActivity.this, AvatarActivity.class);
                MainActivity.this.startActivity(avatarIntent);
            }
        });

        final TextView username = findViewById(R.id.mainActivity_username);
        username.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent avatarIntent = new Intent(MainActivity.this, AvatarActivity.class);
                MainActivity.this.startActivity(avatarIntent);
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
