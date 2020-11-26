package com.example.beervana;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GlavniIzbornikUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glavni_izbornik_user_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, GlavniIzbornikUserFragment.newInstance())
                    .commitNow();
        }
    }
}