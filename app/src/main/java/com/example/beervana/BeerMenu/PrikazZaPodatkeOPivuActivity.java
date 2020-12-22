package com.example.beervana.BeerMenu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beervana.BeerMenu.BeerCatalogActivity;
import com.example.beervana.R;
import com.example.beervana.SettingsActivity;
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
