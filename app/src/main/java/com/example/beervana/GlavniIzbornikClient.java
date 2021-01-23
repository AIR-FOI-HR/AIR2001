package com.example.beervana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BeerMenu.BeerCatalogActivity;
import com.example.beervana.EventMenu.EventCatalogActivity;
import com.example.beervana.Statistika.StatistikaViewModel;
import com.example.beervana.TastingMenu.TastingMenuActivity;
import com.example.webservice.DohvatPodataka;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GlavniIzbornikClient extends BaseActivity {
    DohvatPodataka dohvatPodataka;
    RequestQueue requestQueue;
    String urlStatistika1 = "https://beervana2020.000webhostapp.com/test/DohvatStatistikaOcjena.php";
    String urlStatistika2 = "https://beervana2020.000webhostapp.com/test/DohvatStatistikaOmiljena.php";
    StatistikaViewModel model;
    GraphView graphOcjena, graphOmiljena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gl_izbornik_client);
        initToolbar();
        Button button = findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
        Button buttonBeerCatalog = findViewById(R.id.button2);
        buttonBeerCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivityBeerCatalog();
            }
        });
        Button buttonEventCatalog = findViewById(R.id.button6);
        buttonEventCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivityEventCatalog();
            }
        });
        Button buttonTastingMeni = findViewById(R.id.button3);
        buttonTastingMeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivityTastingMeni();
            }
        });
        graphOcjena = findViewById(R.id.graphOcjena);
        graphOcjena.setVisibility(View.VISIBLE);
        graphOmiljena = findViewById(R.id.graphOmiljena);
        graphOmiljena.setVisibility(View.VISIBLE);
        model = new ViewModelProvider(this).get(StatistikaViewModel.class);
        ReceiveData();
    }

    private void ReceiveData() {
        requestQueue = Volley.newRequestQueue(this);
        dohvatPodataka = new DohvatPodataka();
        dohvatPodataka.setSendUrl(urlStatistika1);
        Map<String, String> params = new HashMap<String, String>();
        //TODO Zamjeni sa pravom lokacijom
        params.put("id_lokacija", "8");
        dohvatPodataka.setParametri(params);
        dohvatPodataka.retrieveData(this, requestQueue);

        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                String url = dohvatPodataka.getSendUrl();
                if (odgovor != null) {
                    if (url.contains("Ocjena")) {
                        model.PostaviPodatke(odgovor);
                        PostaviGrafStatOcjena();
                        dohvatPodataka.setSendUrl(urlStatistika2);
                        dohvatPodataka.retrieveData(GlavniIzbornikClient.this, requestQueue);
                    } else {
                        model.PostaviPodatke(odgovor);
                        PostaviGrafStatOmiljena();
                    }

                }
            }
        });

    }

    private void PostaviGrafStatOmiljena() {
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(model.getPodaci());
        graphOmiljena.addSeries(series);
        graphOmiljena.getGridLabelRenderer().setHorizontalAxisTitle("Month");
        graphOmiljena.getGridLabelRenderer().setVerticalAxisTitle("Omiljena Lokacija");
        graphOmiljena.getGridLabelRenderer().setPadding(55);
    }

    private void PostaviGrafStatOcjena() {
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(model.getPodaci());
        graphOcjena.addSeries(series);
        graphOcjena.getGridLabelRenderer().setHorizontalAxisTitle("Month");
        graphOcjena.getGridLabelRenderer().setVerticalAxisTitle("Average Grade");
        graphOcjena.getGridLabelRenderer().setPadding(55);
    }

    public void openActivity2() {
        Intent intent = new Intent(this, IzbornikZaDodavanjeActivity.class);
        startActivity(intent);
    }

    public void OpenActivityBeerCatalog() {
        Intent intent = new Intent(this, BeerCatalogActivity.class);
        startActivity(intent);
    }

    public void OpenActivityEventCatalog() {
        Intent intent = new Intent(this, EventCatalogActivity.class);
        startActivity(intent);
    }

    public void OpenActivityTastingMeni() {
        Intent intent = new Intent(this, TastingMenuActivity.class);
        startActivity(intent);
    }


}