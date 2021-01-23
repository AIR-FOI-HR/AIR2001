package com.example.beervana.BeerMenu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BaseActivity;
import com.example.beervana.BeerplacePage.AddReviewsActivity;
import com.example.beervana.R;
import com.example.webservice.SlanjePodataka;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class PrikazZaPodatkeOPivuActivity extends BaseActivity {

    TextView beerName1, beerPrice, beerTaste, beerLitres;
    ImageView beerImage;
    String beerId;
    int position;
    private ImageView addToFavorites;
    private boolean favorite;
    SlanjePodataka slanjePodataka;
    private final String CheckFavoriteBeer = "https://beervana2020.000webhostapp.com/test/isFavoriteBeer.php";

    private final String AddToFavoritesUrl = "https://beervana2020.000webhostapp.com/test/addFavoriteBeer.php";
    private final String RemoveFromFavoritesUrl = "https://beervana2020.000webhostapp.com/test/removeFavoriteBeer.php";

    Button addReviews;
    String id_proizvod = "12";

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
        if (intent.getExtras().containsKey("position")) {
            position = intent.getExtras().getInt("position");
            beerName1.setText(BeerCatalogActivity.BeerArrayList.get(position).getNaziv_proizvoda());
            beerPrice.setText("Beer price: " + BeerCatalogActivity.BeerArrayList.get(position).getCijena_proizvoda());
            beerTaste.setText("Beer taste: " + BeerCatalogActivity.BeerArrayList.get(position).getOkus());
            beerLitres.setText("Beer litres: " + BeerCatalogActivity.BeerArrayList.get(position).getLitara());
            String imageUri = BeerCatalogActivity.BeerArrayList.get(position).getSlika();
            Picasso.with(this).load(imageUri).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE).into(beerImage);
            beerId = BeerCatalogActivity.BeerArrayList.get(position).getId_proizvod();
        } else {
            beerName1.setText(intent.getExtras().getString("naziv_proizvoda"));
            beerPrice.setText("Beer price: " + intent.getExtras().getString("cijena_proizvoda"));
            beerTaste.setText("Beer taste: " + intent.getExtras().getString("okus"));
            beerLitres.setText("Beer litres: " + intent.getExtras().getString("litara"));
            String imageUri = intent.getExtras().getString("slika_proizvoda");
            Picasso.with(this).load(imageUri).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE).into(beerImage);
            beerId = intent.getExtras().getString("id_proizvod");
        }
        addToFavorites = findViewById(R.id.addToFavorites);
        addToFavorites.setOnClickListener(v -> AddToFavoriteLocations());
        addReviews.setOnClickListener(v -> openReviewsActivity());
        CheckIfFavoriteBeer();

    }

    private void CheckIfFavoriteBeer() {
        slanjePodataka = new SlanjePodataka(CheckFavoriteBeer);
        RequestQueue requestQueueCheck = Volley.newRequestQueue(getApplicationContext());
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_proizvod", beerId);
        params.put("id_korisnik", "20");
        slanjePodataka.setParametri(params);
        slanjePodataka.sendData(this, requestQueueCheck);


        requestQueueCheck.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                String odgovor = slanjePodataka.getOdgovor();
                if (odgovor.equals("This is a favorite beer")) {
                    addToFavorites.setImageResource(R.drawable.removefromfavorites);
                    favorite = true;
                } else if (odgovor.equals("This is not a favorite beer")) {
                    addToFavorites.setImageResource(R.drawable.addtofavorites);
                    favorite = false;
                }
            }
        });
    }

    private void openReviewsActivity() {
        Intent intent = new Intent(this, AddReviewsActivity.class).putExtra("id_proizvod", id_proizvod);
        startActivity(intent);
    }


    private void AddToFavoriteLocations() {
        if (!favorite) {
            slanjePodataka = new SlanjePodataka(AddToFavoritesUrl);
        } else {
            slanjePodataka = new SlanjePodataka(RemoveFromFavoritesUrl);
        }

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_proizvod", beerId);
        params.put("id_korisnik", "20");
        slanjePodataka.setParametri(params);
        slanjePodataka.sendData(this, requestQueue);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                String odgovor = slanjePodataka.getOdgovor();
                if (odgovor.equals("Succesfully added favorite beer")) {
                    favorite = true;
                    addToFavorites.setImageResource(R.drawable.removefromfavorites);
                    Toast toast = Toast.makeText(getApplicationContext(), "Successfully added favorite beer! ", Toast.LENGTH_LONG);
                    toast.show();
                } else if (odgovor.equals("Successfully deleted a favorite beer")) {
                    favorite = false;
                    addToFavorites.setImageResource(R.drawable.addtofavorites);
                    Toast toast = Toast.makeText(getApplicationContext(), "Successfully deleted favorite beer! ", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), odgovor, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

    }
}
