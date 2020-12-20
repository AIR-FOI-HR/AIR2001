package com.example.beervana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button button = (Button) findViewById(R.id.buttonAboutUs);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        Button logout = (Button) findViewById(R.id.buttonLogOut);
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

                SharedPreferences preferences =getSharedPreferences("login",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                finish();
            }
        });
        //OVAJ DIO DODATI ZA TOOLBAR
        ImageView mImageView = (ImageView)findViewById(R.id.settings_icon);
        mImageView.setOnClickListener(v -> openActivity3());

        //OVAJ DIO OTKOMENTIRATI KAD SE KREIRAJU AKTIVNOSTI
        /*
        ImageView mImageView = (ImageView)findViewById(R.id.user_icon);
        mImageView.setOnClickListener(v -> openActivity4());

        ImageView mImageView = (ImageView)findViewById(R.id.search_icon);
        mImageView.setOnClickListener(v -> openActivity5());
        */

        //

    }

    public void openActivity2(){
        //Intent intent = new Intent(this, AboutUsActivity.class);
        //startActivity(intent);
    }

    //I OVAJ DIO DODATI ZA TOOLBAR
    public void openActivity3(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    //OVAJ DIO OTKOMENTIRATI KAD SE KREIRAJU AKTIVNOSTI
    /*
    public void openActivity4(){
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }
    public void openActivity5(){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
     */

    //KRAJ

}