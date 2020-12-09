package com.example.beervana;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BeerplaceHomepageActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterBeerplace adapter;
    ArrayList<String> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_beerplace_homepage);

        items = new ArrayList<>();
        items.add("First blablabla");
        items.add("Second blablabla");
        items.add("Tretji blablabla");
        items.add("Štiriti blablabla");
        items.add("Peti blablabla");
        items.add("Sekstji blablabla");
        items.add("Sedmi blablabla");

        recyclerView = findViewById(R.id.idRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterBeerplace(this, items);
        recyclerView.setAdapter(adapter);
    }
}
