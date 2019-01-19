package com.example.shouryakhare.rhyme_a_zoo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class BankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        final ImageView home = findViewById(R.id.bankActivity_home);
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(BankActivity.this, MainActivity.class);
                BankActivity.this.startActivity(myIntent);
            }
        });
    }
}
