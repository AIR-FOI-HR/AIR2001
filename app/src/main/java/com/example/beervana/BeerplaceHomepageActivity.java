package com.example.beervana;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.modulzamodule.Beer;
import com.example.modulzamodule.BeerLogic;
import com.example.modulzamodule.EventCatalogLogika;
import com.example.modulzamodule.ModelPodatakEventCatalog;
import com.example.webservice.DohvatPodataka;

import org.json.JSONObject;

import java.util.ArrayList;

public class BeerplaceHomepageActivity extends AppCompatActivity {

    private static final String url = "https://beervana2020.000webhostapp.com/test/dohvacanjePiva.php";
    private static final String urlEvents = "https://beervana2020.000webhostapp.com/test/DohvacanjeDogadaja.php";

    RecyclerView recyclerView,recyclerViewEvents;
    public ArrayList<Beer> beers;
    private ArrayList<ModelPodatakEventCatalog> events;
    private RequestQueue requestQueue, requestQueue1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_beerplace_homepage);

        beers = new ArrayList<Beer>();
        events = new ArrayList<ModelPodatakEventCatalog>();

        recyclerView = findViewById(R.id.idRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadBeers();


        recyclerViewEvents = findViewById(R.id.idRecyclerViewEvents);
        recyclerViewEvents.setLayoutManager(new LinearLayoutManager(this));
        loadEvents();
    }

    private void loadBeers(){
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        BeerLogic beerLogic = new BeerLogic();
        DohvatPodataka dohvatPodataka = new DohvatPodataka();
        dohvatPodataka.setSendUrl(url);
        dohvatPodataka.retrieveData(getApplicationContext(),requestQueue);

        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request){
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                if(odgovor != null){
                    beers.clear();
                    beers.addAll(beerLogic.parsiranjePodatakaPiva(odgovor));
                    AdapterBeerplace adapterBeerplace = new AdapterBeerplace(BeerplaceHomepageActivity.this, beers);
                    recyclerView.setAdapter(adapterBeerplace);
                    //adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void loadEvents(){
        requestQueue1 = Volley.newRequestQueue(getApplicationContext());
        EventCatalogLogika eventCatalogLogika = new EventCatalogLogika();
        DohvatPodataka dp = new DohvatPodataka();
        dp.setSendUrl(urlEvents);
        dp.retrieveData(getApplicationContext(),requestQueue1);

        requestQueue1.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request){
                JSONObject odgovor = dp.getOdgovor();
                if(odgovor != null){
                    events.clear();
                    events.addAll(eventCatalogLogika.parsiranjePodatakaEventData(odgovor));
                    AdapterEventsRecyclerView adapterEventsRecyclerViewr = new AdapterEventsRecyclerView(BeerplaceHomepageActivity.this, events);
                    recyclerViewEvents.setAdapter(adapterEventsRecyclerViewr);
                    //adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
