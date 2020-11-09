package com.example.beervana;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AddBeers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_beers_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, AddBeersFragment.newInstance())
                    .commitNow();
        }
    }
}