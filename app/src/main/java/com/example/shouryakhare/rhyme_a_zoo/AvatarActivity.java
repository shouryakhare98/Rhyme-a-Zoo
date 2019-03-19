package com.example.shouryakhare.rhyme_a_zoo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        SharedPreferences pref = getSharedPreferences("MyPref", 0);
        final SharedPreferences.Editor editor = pref.edit();

        final ImageView avatar = findViewById(R.id.avatarActivity_avatar);

        String imgname = pref.getString("zookeeper", "zookeeper_boy1");
        int res = getResources().getIdentifier(imgname, "drawable", getPackageName());
        avatar.setImageResource(res);

        isBoy = imgname.contains("boy");

        final Button forward = findViewById(R.id.avatarActivity_forward);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (forward.isPressed()) {
                    index += 1;
                    String imagename = ((isBoy) ? boy : girl) + Integer.toString(modulo(index, 36));
                    int res = getResources().getIdentifier(imagename, "drawable", getPackageName());
                    avatar.setImageResource(res);

                    editor.putString("zookeeper", imagename);
                    editor.apply();
                }
            }
        });
        final Button back = findViewById(R.id.avatarActivity_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (back.isPressed()) {
                    index -= 1;
                    String imagename = ((isBoy) ? boy : girl) + Integer.toString(modulo(index, 36));
                    int res = getResources().getIdentifier(imagename, "drawable", getPackageName());
                    avatar.setImageResource(res);

                    editor.putString("zookeeper", imagename);
                    editor.apply();
                }
            }
        });
        final Button boyimg = findViewById(R.id.avatarActivity_boy);
        boyimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (boyimg.isPressed()) {
                    isBoy = true;
                    String imagename = boy + Integer.toString(modulo(index, 36));
                    int res = getResources().getIdentifier(imagename, "drawable", getPackageName());
                    avatar.setImageResource(res);

                    editor.putString("zookeeper", imagename);
                    editor.apply();
                }
            }
        });
        final Button girlimg = findViewById(R.id.avatarActivity_girl);
        girlimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (girlimg.isPressed()) {
                    isBoy = false;
                    String imagename = girl + Integer.toString(modulo(index, 36));
                    int res = getResources().getIdentifier(imagename, "drawable", getPackageName());
                    avatar.setImageResource(res);

                    editor.putString("zookeeper", imagename);
                    editor.apply();
                }
            }
        });
        final Button home = findViewById(R.id.avatarActivity_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(AvatarActivity.this, MainActivity.class);
                AvatarActivity.this.startActivity(myIntent);
            }
        });
    }
    public int modulo( int m, int n ){
        int mod =  m % n ;
        return ( mod <= 0 ) ? mod + n : mod;
    }
}
