package com.example.shouryakhare.rhyme_a_zoo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class RhymeMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rhyme_menu);

        final Button home = findViewById(R.id.rhymeActivity_home);

        ImageView rhymeThumbnail1 = findViewById(R.id.imageView1);
        ImageView rhymeThumbnail2 = findViewById(R.id.imageView2);
        ImageView rhymeThumbnail3 = findViewById(R.id.imageView3);
        ImageView rhymeThumbnail4 = findViewById(R.id.imageView4);
        ImageView rhymeThumbnail5 = findViewById(R.id.imageView5);
        ImageView rhymeThumbnail6 = findViewById(R.id.imageView6);
        ImageView rhymeThumbnail7 = findViewById(R.id.imageView7);
        ImageView rhymeThumbnail8 = findViewById(R.id.imageView8);
        ImageView rhymeThumbnail9 = findViewById(R.id.imageView9);
        ImageView rhymeThumbnail10 = findViewById(R.id.imageView10);
        ImageView rhymeThumbnail11 = findViewById(R.id.imageView11);
        ImageView rhymeThumbnail12 = findViewById(R.id.imageView12);
        ImageView rhymeThumbnail13 = findViewById(R.id.imageView13);
        ImageView rhymeThumbnail14 = findViewById(R.id.imageView14);
        ImageView rhymeThumbnail15 = findViewById(R.id.imageView15);
        ImageView rhymeThumbnail16 = findViewById(R.id.imageView16);
        ImageView rhymeThumbnail17 = findViewById(R.id.imageView17);
        ImageView rhymeThumbnail18 = findViewById(R.id.imageView18);
        ImageView rhymeThumbnail19 = findViewById(R.id.imageView19);
        ImageView rhymeThumbnail20 = findViewById(R.id.imageView20);
        ImageView rhymeThumbnail21 = findViewById(R.id.imageView21);

        rhymeThumbnail1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RhymeMenuActivity.this, RhymeActivity.class);
                myIntent.putExtra("id", "1");
                RhymeMenuActivity.this.startActivity(myIntent);
            }
        });
        rhymeThumbnail2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RhymeMenuActivity.this, RhymeActivity.class);
                myIntent.putExtra("id", "2");
                RhymeMenuActivity.this.startActivity(myIntent);
            }
        });
        rhymeThumbnail3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RhymeMenuActivity.this, RhymeActivity.class);
                myIntent.putExtra("id", "3");
                RhymeMenuActivity.this.startActivity(myIntent);
            }
        });
        rhymeThumbnail4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RhymeMenuActivity.this, RhymeActivity.class);
                myIntent.putExtra("id", "4");
                RhymeMenuActivity.this.startActivity(myIntent);
            }
        });
        rhymeThumbnail5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RhymeMenuActivity.this, RhymeActivity.class);
                myIntent.putExtra("id", "5");
                RhymeMenuActivity.this.startActivity(myIntent);
            }
        });
        rhymeThumbnail6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RhymeMenuActivity.this, RhymeActivity.class);
                myIntent.putExtra("id", "6");
                RhymeMenuActivity.this.startActivity(myIntent);
            }
        });
        rhymeThumbnail7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RhymeMenuActivity.this, RhymeActivity.class);
                myIntent.putExtra("id", "7");
                RhymeMenuActivity.this.startActivity(myIntent);
            }
        });
        rhymeThumbnail8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RhymeMenuActivity.this, RhymeActivity.class);
                myIntent.putExtra("id", "8");
                RhymeMenuActivity.this.startActivity(myIntent);
            }
        });
        rhymeThumbnail9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RhymeMenuActivity.this, RhymeActivity.class);
                myIntent.putExtra("id", "9");
                RhymeMenuActivity.this.startActivity(myIntent);
            }
        });
        rhymeThumbnail10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RhymeMenuActivity.this, RhymeActivity.class);
                myIntent.putExtra("id", "10");
                RhymeMenuActivity.this.startActivity(myIntent);
            }
        });
        rhymeThumbnail11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RhymeMenuActivity.this, RhymeActivity.class);
                myIntent.putExtra("id", "11");
                RhymeMenuActivity.this.startActivity(myIntent);
            }
        });
        rhymeThumbnail12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RhymeMenuActivity.this, RhymeActivity.class);
                myIntent.putExtra("id", "12");
                RhymeMenuActivity.this.startActivity(myIntent);
            }
        });
        rhymeThumbnail13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RhymeMenuActivity.this, RhymeActivity.class);
                myIntent.putExtra("id", "13");
                RhymeMenuActivity.this.startActivity(myIntent);
            }
        });
        rhymeThumbnail14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RhymeMenuActivity.this, RhymeActivity.class);
                myIntent.putExtra("id", "14");
                RhymeMenuActivity.this.startActivity(myIntent);
            }
        });



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(RhymeMenuActivity.this, MainActivity.class);
                RhymeMenuActivity.this.startActivity(myIntent);
            }
        });


    }
}
