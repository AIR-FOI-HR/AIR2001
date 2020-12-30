package com.example.beervana;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BeerplacePage.BeerplaceHomepageActivityNew;
import com.example.webservice.DohvatPodataka;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewMyFavoriteLocationsActivity extends BaseActivity implements  SearchActivityRecyclerAdapterPivnica.onLokacijaListener{
    private ArrayList<ModelPodatakaLokacijaSOcjenom> pronadjeneLokacije;
    private RequestQueue requestQueue;
    private LokacijaLogika logikaLokacija= new LokacijaLogika();
    private SharedPreferences sp;
    private int idKorisnika;
    private String urlMojeNajdrazeLokacije = "https://beervana2020.000webhostapp.com/test/MojeLokacije.php";
    View view;
    RecyclerView locationRecyclerView;
    private SearchActivityRecyclerAdapterPivnica adapterPivnica;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitvity_favorite_locations);
        initToolbar();
        view = findViewById(android.R.id.content).getRootView();
        pronadjeneLokacije = new ArrayList<>();
        locationRecyclerView = findViewById(R.id.favoriteLocationsRecyclerView);
        locationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        sp = getSharedPreferences("login", MODE_PRIVATE);
        idKorisnika = sp.getInt("id_korisnik", 20);
        RetriveData();
    }

    private void RetriveData() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        DohvatPodataka dohvatPodataka = new DohvatPodataka();
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_korisnik", Integer.toString(idKorisnika));
        dohvatPodataka.setParametri(params);
        dohvatPodataka.setSendUrl(urlMojeNajdrazeLokacije);
        dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                try {
                    if(odgovor.getString("message").equals("Successfully retrieved my locations")){
                        pronadjeneLokacije.clear();
                        pronadjeneLokacije.addAll(logikaLokacija.ParsiranjeLokacijeZaPretrazivanje(odgovor));
                        adapterPivnica = new SearchActivityRecyclerAdapterPivnica(ViewMyFavoriteLocationsActivity.this,pronadjeneLokacije,ViewMyFavoriteLocationsActivity.this);
                        locationRecyclerView.setAdapter(adapterPivnica);
                        adapterPivnica.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onLocationClick(int position) {
        startActivity(new Intent(getApplicationContext(), BeerplaceHomepageActivityNew.class).
                putExtra("id_lokacija",pronadjeneLokacije.get(position).getLokacija().getId_lokacija()).
                putExtra("naziv_lokacije",pronadjeneLokacije.get(position).getLokacija().getNazivLokacija()).
                putExtra("ocjena_lokacije",pronadjeneLokacije.get(position).getOcjena()));
    }
}


