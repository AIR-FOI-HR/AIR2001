package com.example.beervana.BeerplacePage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BaseActivity;
import com.example.beervana.BeerMenu.BeerCatalogActivity;
import com.example.beervana.BeerplacePage.Modularnost.AddPromotions;
import com.example.beervana.EventMenu.EventCatalogActivity;
import com.example.beervana.R;
import com.example.beervana.TastingMenu.TastingMenuActivity;
import com.example.webservice.SlanjePodataka;

import java.util.HashMap;
import java.util.Map;

public class BeerplaceHomepageActivityNew extends BaseActivity {
    private String id_lokacija;
    private String naziv_lokacije;
    private String ocjena_lokacije;
    private TextView naziv, ocjena;
    private boolean favorite;
    private ImageView addToFavorites;
    private SharedPreferences sp;
    private String idKorisnik;
    private SlanjePodataka slanjePodataka;
    private String AddToFavoritesUrl = "https://beervana2020.000webhostapp.com/test/DodajOmiljenuLokaciju.php";
    private String RemoveFromFavoritesUrl = "https://beervana2020.000webhostapp.com/test/UkloniOmiljenuLokaciju.php";
    private String CheckFavoriteLocation = "https://beervana2020.000webhostapp.com/test/DaliOmiljenaLokacija.php";
    private RequestQueue requestQueue;
    private RequestQueue requestQueueCheck;
    private Button promocije;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beerplace_homepage_new);
        initToolbar();


        sp = getSharedPreferences("login", MODE_PRIVATE);

        idKorisnik = Integer.toString(sp.getInt("id_korisnik", 0));

        Button button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(v -> openMapsActivity());

        Button button1 = (Button) findViewById(R.id.button14);
        button1.setOnClickListener(v -> openReviewsActivity());

        ImageView mImageView = (ImageView) findViewById(R.id.imageView14);
        mImageView.setOnClickListener(v -> openActivityBeerCatalog());

        ImageView nImageView = (ImageView) findViewById(R.id.imageView15);
        nImageView.setOnClickListener(v -> openActivityEventCatalog());

        ImageView oImageView = (ImageView) findViewById(R.id.imageView16);
        oImageView.setOnClickListener(v -> openActivityMenuCatalog());

        ImageView pImageView = (ImageView) findViewById(R.id.imageView17);
        pImageView.setOnClickListener(v -> openActivityReviews());

        addToFavorites = (ImageView) findViewById(R.id.addToFavorites);
        addToFavorites.setOnClickListener(v -> AddToFavoriteLocations());

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        id_lokacija = extras.getString("id_lokacija", "");
        naziv_lokacije = extras.getString("naziv_lokacije", "");
        ocjena_lokacije = extras.getString("ocjena_lokacije", "");

        naziv = (TextView) findViewById(R.id.textView17);
        naziv.setText(naziv_lokacije);

        ocjena = (TextView) findViewById(R.id.textView18);
        ocjena.setText(ocjena_lokacije);

        promocije = findViewById(R.id.promocije);
        promocije.setOnClickListener(v -> AddPromos());

        CheckIfFavoriteLocation();


    }

    private void AddPromos() {
        Intent intent = new Intent(this, AddPromotions.class).putExtra("id_lokacija", id_lokacija);
        startActivity(intent);
    }

    private void CheckIfFavoriteLocation() {
        slanjePodataka = new SlanjePodataka(CheckFavoriteLocation);
        requestQueueCheck = Volley.newRequestQueue(getApplicationContext());
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_lokacija", id_lokacija);
        params.put("id_korisnik", "20");
        slanjePodataka.setParametri(params);
        slanjePodataka.sendData(this, requestQueueCheck);

        requestQueueCheck.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                String odgovor = slanjePodataka.getOdgovor();
                if (odgovor.equals("This is a favorite location")) {
                    addToFavorites.setImageResource(R.drawable.removefromfavorites);
                    favorite = true;
                } else if (odgovor.equals("This is not a favorite location")) {
                    addToFavorites.setImageResource(R.drawable.addtofavorites);
                    favorite = false;
                }
            }
        });
    }

    private void AddToFavoriteLocations() {
        if (!favorite) {
            slanjePodataka = new SlanjePodataka(AddToFavoritesUrl);
        } else {
            slanjePodataka = new SlanjePodataka(RemoveFromFavoritesUrl);
        }

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_lokacija", id_lokacija);
        params.put("id_korisnik", "20");
        slanjePodataka.setParametri(params);
        slanjePodataka.sendData(this, requestQueue);

        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                String odgovor = slanjePodataka.getOdgovor();
                if (odgovor.equals("Succesfully added favorite location")) {
                    favorite = true;
                    addToFavorites.setImageResource(R.drawable.removefromfavorites);
                    Toast toast = Toast.makeText(getApplicationContext(), "Successfully added favorite location! ", Toast.LENGTH_LONG);
                    toast.show();
                } else if (odgovor.equals("Successfully deleted a favorite location")) {
                    favorite = false;
                    addToFavorites.setImageResource(R.drawable.addtofavorites);
                    Toast toast = Toast.makeText(getApplicationContext(), "Successfully deleted favorite location! ", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), odgovor, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
        private void openReviewsActivity () {
            Intent intent = new Intent(this, AddReviewsActivity.class).putExtra("id_lokacija", id_lokacija);
            startActivity(intent);
        }

        private void openMapsActivity () {
            Intent intent = new Intent(this, MapsActivity.class).putExtra("id_lokacija", id_lokacija);
            startActivity(intent);

        }

        private void openActivityReviews () {
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

        private void openActivityBeerCatalog() {
            Intent intent = new Intent(this, BeerCatalogActivity.class).putExtra("id_lokacija", id_lokacija);
            startActivity(intent);
        }


}
