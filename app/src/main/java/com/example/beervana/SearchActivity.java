package com.example.beervana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.modulzamodule.Beer;
import com.example.modulzamodule.BeerLogic;
import com.example.beervana.BeerMenu.PrikazZaPodatkeOPivuActivity;
import com.example.beervana.BeerplacePage.BeerplaceHomepageActivityNew;
import com.example.modulzamodule.LokacijaLogika;
import com.example.modulzamodule.ModelPodatakaLokacijaSOcjenom;
import com.example.webservice.DohvatPodataka;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends BaseActivity implements SearchActivityRecyclerAdapterPivo.onPivaListener,SearchActivityRecyclerAdapterPivnica.onLokacijaListener{
    RecyclerView beerRecyclerView;
    RecyclerView locationRecyclerView;
    private String pretrazivanje;
    private String urlPive = "https://beervana2020.000webhostapp.com/test/PretrazivanjePiva.php";
    private String urlPivnica = "https://beervana2020.000webhostapp.com/test/PretrazivanjePivnice.php";
    private RequestQueue requestQueue;
    private ArrayList<Beer> pronadjenePive;
    private ArrayList<ModelPodatakaLokacijaSOcjenom> pronadjeneLokacije;
    private BeerLogic logikaPiva= new BeerLogic();
    private LokacijaLogika logikaLokacija= new LokacijaLogika();
    private SearchActivityRecyclerAdapterPivo adapterPivo;
    private SearchActivityRecyclerAdapterPivnica adapterPivnica;
    private TextView nemaLokacija,nemaPiva;

    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initToolbar();
        view = findViewById(android.R.id.content).getRootView();
        pronadjenePive = new ArrayList<>();
        pronadjeneLokacije = new ArrayList<>();
        Intent intent = getIntent();
        pretrazivanje = intent.getExtras().getString("search");
        beerRecyclerView = findViewById(R.id.RecyclerBeerSearch);
        beerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        locationRecyclerView = findViewById(R.id.RecyclerLocationSearch);
        locationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        nemaLokacija = (TextView) findViewById(R.id.txtPretrazivanjeNemaPivnica);
        nemaPiva = (TextView) findViewById(R.id.txtPretrazivanjeNemaPiva);
        RetriveData();
    }

    private void RetriveData() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        DohvatPodataka dohvatPodataka = new DohvatPodataka();
        Map<String, String> params = new HashMap<String, String>();
        params.put("pretrazivanje", pretrazivanje);
        dohvatPodataka.setParametri(params);
        dohvatPodataka.setSendUrl(urlPive);
        dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                if (odgovor != null) {
                    try {
                        if(odgovor.getString("message").equals("Successfully searched bears")||odgovor.getString("message").equals("No beers with this name")){
                            dohvatPodataka.setSendUrl(urlPivnica);
                            dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);
                        }
                        if (odgovor.getString("message").equals("Successfully searched bears")) {
                            pronadjenePive.clear();
                            pronadjenePive.addAll(logikaPiva.parsiranjePivaZaPretrazivanje(odgovor));
                            adapterPivo = new SearchActivityRecyclerAdapterPivo(SearchActivity.this,pronadjenePive,SearchActivity.this);
                            beerRecyclerView.setAdapter(adapterPivo);
                            adapterPivo.notifyDataSetChanged();

                        }else if(odgovor.getString("message").equals("No beers with this name")){
                            nemaPiva.setVisibility(View.VISIBLE);
                        }else if(odgovor.getString("message").equals("Successfully searched bearplaces")){
                            pronadjeneLokacije.clear();
                            pronadjeneLokacije.addAll(logikaLokacija.ParsiranjeLokacijeZaPretrazivanje(odgovor));
                            adapterPivnica = new SearchActivityRecyclerAdapterPivnica(SearchActivity.this,pronadjeneLokacije,SearchActivity.this);
                            locationRecyclerView.setAdapter(adapterPivnica);
                            adapterPivnica.notifyDataSetChanged();
                        }else if(odgovor.getString("message").equals("No beerplaces with this name")){
                            nemaLokacija.setVisibility(View.VISIBLE);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    @Override
    public void onBeerClick(int position) {
        startActivity(new Intent(getApplicationContext(), PrikazZaPodatkeOPivuActivity.class).
                putExtra("id_proizvod",pronadjenePive.get(position).getId_proizvod()).
                putExtra("naziv_proizvoda",pronadjenePive.get(position).getNaziv_proizvoda()).
                putExtra("cijena_proizvoda",pronadjenePive.get(position).getCijena_proizvoda()).
                putExtra("slika_proizvoda",pronadjenePive.get(position).getSlika()).
                putExtra("okus",pronadjenePive.get(position).getOkus()).
                putExtra("litara",pronadjenePive.get(position).getLitara()));

    }

    @Override
    public void onLocationClick(int position) {
        startActivity(new Intent(getApplicationContext(), BeerplaceHomepageActivityNew.class).
                putExtra("id_lokacija",pronadjeneLokacije.get(position).getLokacija().getId_lokacija()).
                putExtra("naziv_lokacije",pronadjeneLokacije.get(position).getLokacija().getNazivLokacija()).
                putExtra("ocjena_lokacije",pronadjeneLokacije.get(position).getOcjena()));
    }
}
