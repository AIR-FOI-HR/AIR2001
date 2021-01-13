package com.example.beervana.BeerplacePage.Modularnost;

import android.content.Intent;
import android.os.Bundle;

import com.example.beervana.BaseActivity;
import com.example.beervana.MainActivity;
import com.example.beervana.R;

public class AddPromotions extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beerplace_homepage_new);
        initToolbar();
        MainActivity m = new MainActivity();

        Intent intent = new Intent();
        m.startActivity(intent);
        startActivity(intent);


    }
}
