package com.example.shouryakhare.rhyme_a_zoo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent.*;
import android.widget.ImageView;

import static android.app.PendingIntent.getActivity;

public class RhymeActivity extends AppCompatActivity {

    int id = 0;
    //int coins = 0; TODO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rhyme);
        //set the rhyme image to be the corresponding id
        id = Integer.valueOf(getIntent().getExtras().getString("id"));
        ImageView imageView = findViewById(R.id.pic);
        String picString = "drawable/rhyme" + String.format("%03d", id) + "_illustration";
        int imgResource = getResources().getIdentifier(picString, null, getPackageName());
        imageView.setImageResource(imgResource);

    }
}
