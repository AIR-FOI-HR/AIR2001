package com.example.beervana.BeerplacePage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beervana.BaseActivity;
import com.example.beervana.BeerMenu.BeerCatalogActivity;
import com.example.beervana.EventMenu.EventCatalogActivity;
import com.example.beervana.R;
import com.example.beervana.SettingsActivity;
import com.example.beervana.TastingMenu.TastingMenu;
import com.example.beervana.TastingMenu.TastingMenuActivity;

public class BeerplaceHomepageActivityNew extends BaseActivity {
    private String id_lokacija ;
    private String naziv_lokacije;
    private String ocjena_lokacije;
    private TextView naziv , ocjena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beerplace_homepage_new);
        initToolbar();

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

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        id_lokacija = extras.getString("id_lokacija","");
        naziv_lokacije = extras.getString("naziv_lokacije","");
        ocjena_lokacije = extras.getString("ocjena_lokacije","");

        naziv = (TextView)findViewById(R.id.textView17);
        naziv.setText(naziv_lokacije);

        ocjena = (TextView)findViewById(R.id.textView18);
        ocjena.setText(ocjena_lokacije);



    }

    private void openMapsActivity() {
        Intent intent = new Intent(this, MapsActivity.class).putExtra("id_lokacija", id_lokacija);
        startActivity(intent);

    }

    //TODO još povezati s recenzijama kad se naprave
    private void openActivityReviews() {
        Intent intent = new Intent(this, ReviewsActivity.class).putExtra("id_lokacija", id_lokacija);
        startActivity(intent);
    }

    private void openActivityMenuCatalog() {
        Intent intent = new Intent(this, TastingMenuActivity.class).putExtra("id_lokacija", id_lokacija);
        startActivity(intent);
    }

    private void openActivityEventCatalog() {
        Intent intent = new Intent(this, EventCatalogActivity.class).putExtra("id_lokacija", id_lokacija);
        startActivity(intent);
    }

    private void openActivityBeerCatalog(){
        Intent intent = new Intent(this, BeerCatalogActivity.class).putExtra("id_lokacija", id_lokacija);
        startActivity(intent);
    }



}