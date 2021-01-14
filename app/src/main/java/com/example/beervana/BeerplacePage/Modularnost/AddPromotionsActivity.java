package com.example.beervana.BeerplacePage.Modularnost;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.example.beervana.BaseActivity;
import com.example.beervana.MainActivity;
import com.example.beervana.R;

public class AddPromotions extends BaseActivity implements AddPromotionsRecylcerAdapter.onAddPromosListener{
    RecyclerView recyclerView;
    AddPromotionsRecylcerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beerplace_homepage_new);
        initToolbar();




    }

    @Override
    public void onAddPromosClick(int position) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
