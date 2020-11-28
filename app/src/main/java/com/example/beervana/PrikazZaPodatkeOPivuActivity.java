package com.example.beervana;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class PrikazZaPodatkeOPivuActivity extends AppCompatActivity{

    TextView beerName1, beerPrice, beerTaste,beerLitres;
    ImageView beerImage;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_za_podatke_o_pivu);

        beerName1 = findViewById(R.id.beerNameTextView1);
        beerPrice = findViewById(R.id.textView19);
        beerLitres = findViewById(R.id.alcoholPercentageTextView);
        beerImage = findViewById(R.id.beerImage);
        beerTaste = findViewById(R.id.descriptionTextView);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        beerName1.setText("Beer name: " + BeerCatalogActivity.BeerArrayList.get(position).getNaziv_proizvoda());
        beerPrice.setText("Beer price: " + BeerCatalogActivity.BeerArrayList.get(position).getCijena_proizvoda());
        beerTaste.setText("Beer taste: "+ BeerCatalogActivity.BeerArrayList.get(position).getOkus());
        beerLitres.setText("Beer litres: " + BeerCatalogActivity.BeerArrayList.get(position).getLitara());
        String imageUri = BeerCatalogActivity.BeerArrayList.get(position).getSlika();
        Picasso.with(this).load(imageUri).into(beerImage);


    }

}
