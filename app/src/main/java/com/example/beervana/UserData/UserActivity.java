package com.example.beervana.UserData;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BaseActivity;
import com.example.beervana.BeerplacePage.AdapterReview;
import com.example.beervana.BeerplacePage.ReviewsActivity;
import com.example.beervana.BeerplacePage.ReviewsLogic;
import com.example.webservice.DohvatPodataka;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.beervana.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserActivity extends BaseActivity {
    private String id_korisnik;
    ConstraintLayout constraintLayout;
    private static final String url = "https://beervana2020.000webhostapp.com/test/fetchUserData.php";
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initToolbar();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        id_korisnik = extras.getString("id_korisnik","20");
        loadUserData();
    }

    private void loadUserData() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        ReviewsLogic reviewsLogic = new ReviewsLogic();
        DohvatPodataka dohvatPodataka = new DohvatPodataka();
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_korisnik", id_korisnik);
        dohvatPodataka.setParametri(params);

        dohvatPodataka.setSendUrl(url);
        dohvatPodataka.retrieveData(getApplicationContext(),requestQueue);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request){
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                if(odgovor != null){
                    //TODO
                }
            }
        });
    }
}