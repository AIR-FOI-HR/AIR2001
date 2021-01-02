package com.example.beervana.BeerMenu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beervana.BaseActivity;
import com.example.beervana.BeerMenu.BeerCatalogActivity;
import com.example.beervana.BeerplacePage.AddReviewsActivity;
import com.example.beervana.R;
import com.example.beervana.SettingsActivity;
import com.squareup.picasso.Picasso;

public class PrikazZaPodatkeOPivuActivity extends BaseActivity {

    TextView beerName1, beerPrice, beerTaste,beerLitres;
    ImageView beerImage;
    int position;
    Button addReviews;
    String id_proizvod="12";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_za_podatke_o_pivu);
        initToolbar();

        beerName1 = findViewById(R.id.beerNameTextView1);
        beerPrice = findViewById(R.id.textView19);
        beerLitres = findViewById(R.id.alcoholPercentageTextView);
        beerImage = findViewById(R.id.beerImage);
        beerTaste = findViewById(R.id.descriptionTextView);
        addReviews = findViewById(R.id.addReview);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        beerName1.setText("Beer name: " + BeerCatalogActivity.BeerArrayList.get(position).getNaziv_proizvoda());
        beerPrice.setText("Beer price: " + BeerCatalogActivity.BeerArrayList.get(position).getCijena_proizvoda());
        beerTaste.setText("Beer taste: "+ BeerCatalogActivity.BeerArrayList.get(position).getOkus());
        beerLitres.setText("Beer litres: " + BeerCatalogActivity.BeerArrayList.get(position).getLitara());
        String imageUri = BeerCatalogActivity.BeerArrayList.get(position).getSlika();
        Picasso.with(this).load(imageUri).into(beerImage);
        addReviews.setOnClickListener(v -> openReviewsActivity());

    }
    private void openReviewsActivity() {
        Intent intent = new Intent(this, AddReviewsActivity.class).putExtra("id_proizvod", id_proizvod);
        startActivity(intent);
    }
}
