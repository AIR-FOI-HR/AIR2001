package com.example.beervana.BeerplacePage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beervana.BeerMenu.BeerCatalogActivity;
import com.example.beervana.EventMenu.EventCatalogActivity;
import com.example.beervana.R;
import com.example.beervana.TastingMenu.TastingMenu;
import com.example.beervana.TastingMenu.TastingMenuActivity;

public class BeerplaceHomepageActivityNew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beerplace_homepage_new);

        Button button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(v -> openMapsActivity());

        ImageView mImageView = (ImageView)findViewById(R.id.imageView14);
        mImageView.setOnClickListener(v -> openActivityBeerCatalog());

        ImageView nImageView = (ImageView)findViewById(R.id.imageView15);
        nImageView.setOnClickListener(v -> openActivityEventCatalog());

        ImageView oImageView = (ImageView)findViewById(R.id.imageView16);
        oImageView.setOnClickListener(v -> openActivityMenuCatalog());

        ImageView pImageView = (ImageView)findViewById(R.id.imageView17);
        pImageView.setOnClickListener(v -> openActivityReviews());


    }

    private void openMapsActivity() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    private void openActivityReviews() {
        Intent intent = new Intent(this, ReviewsActivity.class);
        startActivity(intent);
    }



    private void openActivityMenuCatalog() {
        Intent intent = new Intent(this, TastingMenuActivity.class);
        startActivity(intent);
    }

    private void openActivityEventCatalog() {
        Intent intent = new Intent(this, EventCatalogActivity.class);
        startActivity(intent);
    }

    private void openActivityBeerCatalog(){
        Intent intent = new Intent(this, BeerCatalogActivity.class);
        startActivity(intent);
    }
}