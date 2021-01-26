package com.example.beervana.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.beervana.BaseActivity;
import com.example.beervana.MainActivity;
import com.example.beervana.R;
import com.example.beervana.Toolbar.AboutUsActivity;


public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initToolbar();

        Button button = findViewById(R.id.buttonAboutUs);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        Button logout = findViewById(R.id.buttonLogOut);
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);


                SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                startActivity(i);
                finish();
            }
        });


    }

    public void openActivity2() {
        Intent intent = new Intent(this, AboutUsActivity.class);
        startActivity(intent);
    }


}