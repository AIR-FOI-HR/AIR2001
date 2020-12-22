package com.example.beervana.BeerplacePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BaseActivity;
import com.example.beervana.BeerMenu.Beer;
import com.example.beervana.BeerMenu.BeerLogic;
import com.example.beervana.R;
import com.example.beervana.SettingsActivity;
import com.example.webservice.DohvatPodataka;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReviewsActivity extends BaseActivity {

    private static final String url = "https://beervana2020.000webhostapp.com/test/fetchReviews.php";
    RecyclerView recyclerView;
    public ArrayList<Review> reviews;
    private RequestQueue requestQueue;
    private Integer id_korisnika;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        initToolbar();

        reviews = new ArrayList<Review>();
        recyclerView = findViewById(R.id.reviewRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        id_korisnika = extras.getInt("id_korisnika",0);
        loadReviews();


    }

    private void loadReviews() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        ReviewsLogic reviewsLogic = new ReviewsLogic();
        DohvatPodataka dohvatPodataka = new DohvatPodataka();
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_korisnik", Integer.toString(id_korisnika));
        dohvatPodataka.setParametri(params);

        dohvatPodataka.setSendUrl(url);
        dohvatPodataka.retrieveData(getApplicationContext(),requestQueue);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request){
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                if(odgovor != null){
                    reviews.clear();
                    reviews.addAll(ReviewsLogic.parsiranjePodatakaReviewa(odgovor));
                    AdapterReview adapterReview = new AdapterReview(ReviewsActivity.this, reviews);
                    recyclerView.setAdapter(adapterReview);
                    //adapter.notifyDataSetChanged();
                }
            }
        });
    }



}