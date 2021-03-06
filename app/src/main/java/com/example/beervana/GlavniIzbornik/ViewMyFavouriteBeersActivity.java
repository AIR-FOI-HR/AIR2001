package com.example.beervana.GlavniIzbornik;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BaseActivity;
import com.example.beervana.BeerMenu.PrikazZaPodatkeOPivuActivity;
import com.example.beervana.R;
import com.example.modulzamodule.Beer.BeerLogic;
import com.example.modulzamodule.Beer.ModelPodatakaPivoSOcjenom;
import com.example.webservice.DohvatPodataka;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewMyFavouriteBeersActivity extends BaseActivity implements ActivityRecyclerAdaptetPivoSOcjenom.onPivaListener {
    private ArrayList<ModelPodatakaPivoSOcjenom> pronadjenaPiva;
    private RequestQueue requestQueue;
    private final BeerLogic beerLogic = new BeerLogic();
    private SharedPreferences sp;
    private int idKorisnika;
    private final String urlMojaNajdrazaPiva = "https://beervana2020.000webhostapp.com/test/mojaNajdrazaPiva.php";
    View view;
    RecyclerView beerRecyclerView;
    private ActivityRecyclerAdaptetPivoSOcjenom adapterPivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_beers);
        initToolbar();
        view = findViewById(android.R.id.content).getRootView();
        pronadjenaPiva = new ArrayList<>();
        beerRecyclerView = findViewById(R.id.favoriteBeersRecylcerView);
        beerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        sp = getSharedPreferences("login", MODE_PRIVATE);
        idKorisnika = sp.getInt("id_korisnik", 0);

        RetriveData();
    }

    private void RetriveData() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        DohvatPodataka dohvatPodataka = new DohvatPodataka();
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_korisnik", Integer.toString(idKorisnika));
        dohvatPodataka.setParametri(params);
        dohvatPodataka.setSendUrl(urlMojaNajdrazaPiva);
        dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                try {
                    if (odgovor.getString("message").equals("Successfully retrieved my beers")) {
                        pronadjenaPiva.clear();
                        pronadjenaPiva.addAll(beerLogic.parsiranjePivaZaNajdrazaPiva(odgovor));
                        adapterPivo = new ActivityRecyclerAdaptetPivoSOcjenom(ViewMyFavouriteBeersActivity.this, pronadjenaPiva, ViewMyFavouriteBeersActivity.this);
                        beerRecyclerView.setAdapter(adapterPivo);
                        adapterPivo.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onBeerClick(int position) {
        startActivity(new Intent(getApplicationContext(), PrikazZaPodatkeOPivuActivity.class)
                .putExtra("naziv_proizvoda", pronadjenaPiva.get(position).getBeer().getNaziv_proizvoda())
                .putExtra("id_proizvod", pronadjenaPiva.get(position).getBeer().getId_proizvod())
                .putExtra("okus", pronadjenaPiva.get(position).getBeer().getOkus())
                .putExtra("litara", pronadjenaPiva.get(position).getBeer().getLitara())
                .putExtra("slika_proizvoda", pronadjenaPiva.get(position).getBeer().getSlika())
                .putExtra("cijena_proizvoda", pronadjenaPiva.get(position).getBeer().getCijena_proizvoda()));
    }
}
