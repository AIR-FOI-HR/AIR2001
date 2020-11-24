package com.example.beervana;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class PrikazZaPodatkeOPivuActivity extends AppCompatActivity{

    TextView beerName1, beerName2, beerTaste, beerDescription;
    ImageView beerImage;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_za_podatke_o_pivu);

        beerName1 = findViewById(R.id.beerNameTextView1);
        beerName2 = findViewById(R.id.beerNameTextView2);
        beerTaste = findViewById(R.id.alcoholPercentageTextView);
        beerImage = findViewById(R.id.beerImage);
        beerDescription = findViewById(R.id.descriptionTextView);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        beerName1.setText("Beer name: " + BeerCatalogActivity.BeerArrayList.get(position).getNaziv_proizvoda());
        beerName2.setText("Beer name: " + BeerCatalogActivity.BeerArrayList.get(position).getNaziv_proizvoda());
        beerTaste.setText("Beer taste: "+ BeerCatalogActivity.BeerArrayList.get(position).getOkus());
        beerDescription.setText("Beer price: " + BeerCatalogActivity.BeerArrayList.get(position).getCijena_proizvoda());
        String imageUri = BeerCatalogActivity.BeerArrayList.get(position).getSlika();
        Picasso.with(this).load(imageUri).into(beerImage);


    }

}
