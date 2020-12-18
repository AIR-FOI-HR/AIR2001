package com.example.beervana.BeerMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceGroup;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.MainActivity;
import com.example.beervana.R;
import com.example.webservice.DohvatPodataka;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BeerCatalogActivity extends AppCompatActivity implements BeerCatalogRecyclerAdapter.onPivoListener{

    RecyclerView beerRecyclerView;
    private RequestQueue requestQueue;
    BeerCatalogRecyclerAdapter adapter;
    public static ArrayList<Beer> BeerArrayList = new ArrayList<>();
    String url = "https://beervana2020.000webhostapp.com/test/dohvacanjePiva.php";
    Beer beer;
    String idLokacija;
    SharedPreferences sp;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_catalog);
        view = findViewById(android.R.id.content).getRootView();
        beerRecyclerView = findViewById(R.id.RecyclerBeerCatalog);
        beerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        sp = getSharedPreferences("login", MODE_PRIVATE);
        idLokacija = sp.getString("id_lokacija","Nema Lokacija").split(",")[0];

        retrieveData();
    }

    private void retrieveData() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        BeerLogic beerLogic = new BeerLogic();
        DohvatPodataka dohvatPodataka = new DohvatPodataka();
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_lokacija",idLokacija);
        dohvatPodataka.setParametri(params);
        dohvatPodataka.setSendUrl(url);
        dohvatPodataka.retrieveData(getApplicationContext(),requestQueue);

        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request){
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                if(odgovor != null){
                    BeerArrayList.clear();
                    BeerArrayList.addAll(beerLogic.parsiranjePodatakaPiva(odgovor));
                    adapter = new BeerCatalogRecyclerAdapter(BeerCatalogActivity.this,BeerArrayList,BeerCatalogActivity.this);
                    beerRecyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }


    @Override
    public void onBeerClick(int position) {
        startActivity(new Intent(getApplicationContext(), PrikazZaPodatkeOPivuActivity.class).putExtra("position",position));
    }
}