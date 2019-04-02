package com.example.shouryakhare.rhyme_a_zoo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Activity for changing the user's zookeeper avatar.
 */
public class AvatarActivity extends AppCompatActivity {

    int index = 0;
    String boy = "zookeeper_boy";
    String girl = "zookeeper_girl";
    boolean isBoy = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);

        // Get current avatar using Shared Preferences
        SharedPreferences pref = getSharedPreferences("MyPref", 0);
        final SharedPreferences.Editor editor = pref.edit();
        String imgname = pref.getString("zookeeper", "zookeeper_boy1");
        int res = getResources().getIdentifier(imgname, "drawable", getPackageName());

        // Set avatar to image view to display
        final ImageView avatar = findViewById(R.id.avatarActivity_avatar);
        avatar.setImageResource(res);

        // Checking if current avatar is boy or girl
        isBoy = imgname.contains("boy");

        // If forward button is pressed
        final Button forward = findViewById(R.id.avatarActivity_forward);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (forward.isPressed()) {

                    // Increasing avatar index and get next avatar image
                    index += 1;
                    String imagename = ((isBoy) ? boy : girl) + Integer.toString(modulo(index, 36));
                    int res = getResources().getIdentifier(imagename, "drawable", getPackageName());
                    avatar.setImageResource(res);

                    // Save avatar name to Shared Preferences
                    editor.putString("zookeeper", imagename);
                    editor.apply();
                }
            }
        });

        // If back button is pressed
        final Button back = findViewById(R.id.avatarActivity_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (back.isPressed()) {

                    // Decreasing avatar index and get previous avatar image
                    index -= 1;
                    String imagename = ((isBoy) ? boy : girl) + Integer.toString(modulo(index, 36));
                    int res = getResources().getIdentifier(imagename, "drawable", getPackageName());
                    avatar.setImageResource(res);

                    // Save avatar name to Shared Preferences
                    editor.putString("zookeeper", imagename);
                    editor.apply();
                }
            }
        });

        // If boy button is pressed
        final Button boyimg = findViewById(R.id.avatarActivity_boy);
        boyimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (boyimg.isPressed()) {

                    // Change isBoy flag and display appropriate avatar
                    isBoy = true;
                    String imagename = boy + Integer.toString(modulo(index, 36));
                    int res = getResources().getIdentifier(imagename, "drawable", getPackageName());
                    avatar.setImageResource(res);

                    // Save avatar name to Shared Preferences
                    editor.putString("zookeeper", imagename);
                    editor.apply();
                }
            }
        });

        // If girl button is pressed
        final Button girlimg = findViewById(R.id.avatarActivity_girl);
        girlimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (girlimg.isPressed()) {

                    // Change isBoy flag and display appropriate avatar
                    isBoy = false;
                    String imagename = girl + Integer.toString(modulo(index, 36));
                    int res = getResources().getIdentifier(imagename, "drawable", getPackageName());
                    avatar.setImageResource(res);

                    // Save avatar name to Shared Preferences
                    editor.putString("zookeeper", imagename);
                    editor.apply();
                }
            }
        });

        // If home button is pressed, go to MainActivity
        final Button home = findViewById(R.id.avatarActivity_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(AvatarActivity.this, MainActivity.class);
                AvatarActivity.this.startActivity(myIntent);
            }
        });
    }

    // Function to get mod of a number that wraps around if modulus is 0
    public int modulo( int m, int n ){
        int mod =  m % n ;
        return ( mod <= 0 ) ? mod + n : mod;
    }
}
