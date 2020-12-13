package com.example.beervana.BeerplacePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BeerMenu.Beer;
import com.example.beervana.BeerMenu.BeerLogic;
import com.example.beervana.R;
import com.example.webservice.DohvatPodataka;

import org.json.JSONObject;

import java.util.ArrayList;

public class ReviewsActivity extends AppCompatActivity {

    private static final String url = "https://beervana2020.000webhostapp.com/test/fetchReviews.php";
    RecyclerView recyclerView;
    public ArrayList<Review> reviews;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        reviews = new ArrayList<Review>();
        recyclerView = findViewById(R.id.reviewRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadReviews();
    }

    private void loadReviews() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        ReviewsLogic reviewsLogic = new ReviewsLogic();
        DohvatPodataka dohvatPodataka = new DohvatPodataka();
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