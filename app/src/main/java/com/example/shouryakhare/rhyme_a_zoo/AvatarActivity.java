package com.example.shouryakhare.rhyme_a_zoo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AvatarActivity extends AppCompatActivity {

    int index = 0;
    String boy = "zookeeper_boy";
    String girl = "zookeeper_girl";
    boolean isBoy = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);

        final ImageView avatar =(ImageView) findViewById(R.id.avatar);

        final ImageView forward =(ImageView) findViewById(R.id.forward);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (forward.isPressed()) {
                    index += 1;
                    String imagename = ((isBoy) ? boy : girl) + Integer.toString(index % 36);
                    int res = getResources().getIdentifier(imagename, "drawable", getPackageName());
                    avatar.setImageResource(res);
                }
            }
        });
        final ImageView back =(ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (back.isPressed()) {
                    index -= 1;
                    String imagename = ((isBoy) ? boy : girl) + Integer.toString(index % 36);
                    int res = getResources().getIdentifier(imagename, "drawable", getPackageName());
                    avatar.setImageResource(res);
                }
            }
        });
        final ImageView boyimg =(ImageView) findViewById(R.id.boy);
        boyimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (boyimg.isPressed()) {
                    isBoy = true;
                    String imagename = ((isBoy) ? boy : girl) + Integer.toString(index % 36);
                    int res = getResources().getIdentifier(imagename, "drawable", getPackageName());
                    avatar.setImageResource(res);
                }
            }
        });
        final ImageView girlimg =(ImageView) findViewById(R.id.girl);
        girlimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (girlimg.isPressed()) {
                    isBoy = false;
                    String imagename = ((isBoy) ? boy : girl) + Integer.toString(index % 36);
                    int res = getResources().getIdentifier(imagename, "drawable", getPackageName());
                    avatar.setImageResource(res);
                }
            }
        });
        final ImageView home =(ImageView) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(AvatarActivity.this, MainActivity.class);
                AvatarActivity.this.startActivity(myIntent);
            }
        });
    }
}
