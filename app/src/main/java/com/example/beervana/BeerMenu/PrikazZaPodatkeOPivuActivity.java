package com.example.beervana.BeerMenu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BaseActivity;
import com.example.beervana.BeerplacePage.AddReviewsActivity;
import com.example.beervana.BeerplacePage.ReviewsActivity;
import com.example.beervana.R;
import com.example.webservice.DohvatPodataka;
import com.example.webservice.SlanjePodataka;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PrikazZaPodatkeOPivuActivity extends BaseActivity {

    TextView beerName1, beerPrice, beerTaste, beerLitres, komentar, komentar2, datum,datum2, ocjena,ocjena2;
    ImageView beerImage, zvijezda, zvijezda2;
    int position;
    private ImageView addToFavorites;
    private boolean favorite;
    SlanjePodataka slanjePodataka;
    private final String CheckFavoriteBeer = "https://beervana2020.000webhostapp.com/test/isFavoriteBeer.php";
    private final String url="https://beervana2020.000webhostapp.com/test/BeerViewReviews.php";
    private final String AddToFavoritesUrl = "https://beervana2020.000webhostapp.com/test/addFavoriteBeer.php";
    private final String RemoveFromFavoritesUrl = "https://beervana2020.000webhostapp.com/test/removeFavoriteBeer.php";
    RequestQueue requestQueue;
    ArrayList<JSONObject> reviews = new ArrayList();
    Button addReviews, allReviews;
    String id, idKorisnika;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_za_podatke_o_pivu);
        initToolbar();
        sp = getSharedPreferences("login", MODE_PRIVATE);
        idKorisnika = sp.getString("id_uloga", "0");
        beerName1 = findViewById(R.id.beerNameTextView1);
        beerPrice = findViewById(R.id.textView19);
        beerLitres = findViewById(R.id.alcoholPercentageTextView);
        beerImage = findViewById(R.id.beerImage);
        beerTaste = findViewById(R.id.descriptionTextView);
        addReviews = findViewById(R.id.addReview);
        komentar = findViewById(R.id.komentar);
        komentar2 = findViewById(R.id.komentar2);
        ocjena = findViewById(R.id.ocjena);
        ocjena2 = findViewById(R.id.ocjena2);
        datum = findViewById(R.id.datum);
        datum2 = findViewById(R.id.datum2);
        zvijezda = findViewById(R.id.zvijezda);
        zvijezda2 = findViewById(R.id.zvijezda2);
        Intent intent = getIntent();
        if (intent.getExtras().containsKey("position")) {
            position = intent.getExtras().getInt("position");
            id = BeerCatalogActivity.BeerArrayList.get(position).getId_proizvod();
            beerName1.setText(BeerCatalogActivity.BeerArrayList.get(position).getNaziv_proizvoda());
            beerPrice.setText("Beer price: " + BeerCatalogActivity.BeerArrayList.get(position).getCijena_proizvoda());
            beerTaste.setText("Beer taste: " + BeerCatalogActivity.BeerArrayList.get(position).getOkus());
            beerLitres.setText("Beer litres: " + BeerCatalogActivity.BeerArrayList.get(position).getLitara());
            String imageUri = BeerCatalogActivity.BeerArrayList.get(position).getSlika();
            Picasso.with(this).load(imageUri).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE).into(beerImage);
        } else {
            id = intent.getExtras().getString("id_proizvod");
            beerName1.setText(intent.getExtras().getString("naziv_proizvoda"));
            beerPrice.setText("Beer price: " + intent.getExtras().getString("cijena_proizvoda"));
            beerTaste.setText("Beer taste: " + intent.getExtras().getString("okus"));
            beerLitres.setText("Beer litres: " + intent.getExtras().getString("litara"));
            String imageUri = intent.getExtras().getString("slika_proizvoda");
            Picasso.with(this).load(imageUri).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE).into(beerImage);
        }
        addToFavorites = findViewById(R.id.addToFavorites);
        addToFavorites.setOnClickListener(v -> AddToFavoriteLocations());
        addReviews.setOnClickListener(v -> openReviewsActivity());
        allReviews = findViewById(R.id.allReviews);
        allReviews.setOnClickListener(v -> loadAllReviews());
        CheckIfFavoriteBeer();
        GetReviews();

    }

    private void GetReviews() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        DohvatPodataka dohvatPodataka = new DohvatPodataka();
        HashMap<String,String> params = new HashMap<>();
        params.put("id_proizvod", id);
        dohvatPodataka.setParametri(params);
        dohvatPodataka.setSendUrl(url);
        dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                JSONArray jsonArray = null;
                try {
                    jsonArray= odgovor.getJSONArray("recenzija");
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    reviews.add(jsonObject);
                    jsonObject = jsonArray.getJSONObject(1);
                    reviews.add(jsonObject);
                    SetUpReviews();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void SetUpReviews() {
        if(reviews.size()==2) {
            komentar.setText(reviews.get(0).optString("komentar"));
            ocjena.setText(reviews.get(0).optString("ocjena"));
            datum.setText(reviews.get(0).optString("datum_i_vrijeme_recenzije"));
            komentar2.setText(reviews.get(1).optString("komentar"));
            ocjena2.setText(reviews.get(1).optString("ocjena"));
            datum2.setText(reviews.get(1).optString("datum_i_vrijeme_recenzije"));
        } else if (reviews.size()==1) {
            ocjena2.setVisibility(View.GONE);
            komentar2.setVisibility(View.GONE);
            datum2.setVisibility(View.GONE);
            komentar.setText(reviews.get(0).optString("komentar"));
            ocjena.setText(reviews.get(0).optString("ocjena"));
            datum.setText(reviews.get(0).optString("datum_i_vrijeme_recenzije"));
        } else {
            ocjena2.setVisibility(View.GONE);
            komentar2.setVisibility(View.GONE);
            datum2.setVisibility(View.GONE);
            ocjena.setVisibility(View.GONE);
            komentar.setVisibility(View.GONE);
            datum.setVisibility(View.GONE);
        }
    }

    private void CheckIfFavoriteBeer() {
        slanjePodataka = new SlanjePodataka(CheckFavoriteBeer);
        RequestQueue requestQueueCheck = Volley.newRequestQueue(getApplicationContext());
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_proizvod", id);
        params.put("id_korisnik", idKorisnika);
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
        Intent intent = new Intent(this, AddReviewsActivity.class).putExtra("id_proizvod", id);
        startActivity(intent);
    }
    private void loadAllReviews() {
        Intent intent = new Intent(this, ReviewsActivity.class).putExtra("id_proizvod", id);
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
        params.put("id_proizvod", id);
        params.put("id_korisnik", idKorisnika);
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
