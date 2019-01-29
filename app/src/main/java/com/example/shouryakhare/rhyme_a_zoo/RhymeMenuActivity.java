package com.example.shouryakhare.rhyme_a_zoo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RhymeMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rhyme_menu);

        final Button home = findViewById(R.id.rhymeActivity_home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(RhymeMenuActivity.this, MainActivity.class);
                RhymeMenuActivity.this.startActivity(myIntent);
            }
        });
    }
}
