package com.example.beervana.GlavniIzbornik;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BaseActivity;
import com.example.beervana.BeerMenu.BeerCatalogActivity;
import com.example.beervana.BeerplacePage.Modularnost.PromotionCatalogActivity;
import com.example.beervana.BeerplacePage.ReviewsActivity;
import com.example.beervana.EventMenu.EventCatalogActivity;
import com.example.beervana.R;
import com.example.beervana.Statistika.StatistikaViewModel;
import com.example.beervana.TastingMenu.TastingMenuActivity;
import com.example.modulzamodule.Reviews.Review;
import com.example.modulzamodule.Reviews.ReviewsLogic;
import com.example.webservice.DohvatPodataka;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GlavniIzbornikClient extends BaseActivity {
    DohvatPodataka dohvatPodataka;
    RequestQueue requestQueue;
    String urlStatistika1 = "https://beervana2020.000webhostapp.com/test/DohvatStatistikaOcjena.php";
    String urlStatistika2 = "https://beervana2020.000webhostapp.com/test/DohvatStatistikaOmiljena.php";
    String urlReviewLokacija ="https://beervana2020.000webhostapp.com/test/getRecentLocationReviews.php";
    String urlReviewProizvod ="https://beervana2020.000webhostapp.com/test/getRecentBeerReviews.php";
    StatistikaViewModel model;
    ReviewsLogic logika;
    GraphView graphOcjena,graphOmiljena;
    SharedPreferences sp;
    String idLokacija;
    TextView recenzija1Lokacija,recenzija2Lokacija,recenzijaPiva;
    ImageView slika1Lokacija,slika2Lokacija,slikaPivo;
    Button buttonRecenzijeLokacije;
    Button buttonRecenzijePive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gl_izbornik_client);
        initToolbar();
        logika = new ReviewsLogic();
        sp = getSharedPreferences("login", MODE_PRIVATE);
        idLokacija = sp.getString("id_lokacija", "Nema Lokacija").split(",")[0];
        Button button = (Button) findViewById(R.id.button7);
        recenzija1Lokacija = (TextView) findViewById(R.id.textView3);
        recenzija2Lokacija = (TextView) findViewById(R.id.textView5);
        recenzijaPiva = (TextView) findViewById(R.id.textView7);
        slika1Lokacija = (ImageView) findViewById(R.id.imageView3);
        slika2Lokacija = (ImageView) findViewById(R.id.imageView4);
        slikaPivo = (ImageView) findViewById(R.id.imageView7);
        buttonRecenzijeLokacije = (Button) findViewById(R.id.button);
        buttonRecenzijePive = (Button) findViewById(R.id.button1);

        buttonRecenzijeLokacije.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GlavniIzbornikClient.this, ReviewsActivity.class).putExtra("id_lokacija",idLokacija);
                startActivity(intent);
            }
        });
        buttonRecenzijePive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GlavniIzbornikClient.this, ReviewsActivity.class).putExtra("id_proizvod",model.getReviewBeer().get(0).getId_proizvod());
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
        Button buttonBeerCatalog = (Button) findViewById(R.id.button2);
        buttonBeerCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivityBeerCatalog();
            }
        });
        Button buttonEventCatalog = (Button) findViewById(R.id.button6);
        buttonEventCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivityEventCatalog();
            }
        });
        Button buttonTastingMeni = (Button) findViewById(R.id.button3);
        buttonTastingMeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivityTastingMeni();
            }
        });
        Button buttonPromocije = (Button) findViewById(R.id.button5);
        buttonPromocije.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivityPromotionCatalog();
            }
        });
        graphOcjena = (GraphView) findViewById(R.id.graphOcjena);
        graphOcjena.setVisibility(View.VISIBLE);
        graphOmiljena = (GraphView) findViewById(R.id.graphOmiljena);
        graphOmiljena.setVisibility(View.VISIBLE);
        model = new ViewModelProvider(this).get(StatistikaViewModel.class);
        ReceiveData();
    }



    private void ReceiveData() {
        requestQueue = Volley.newRequestQueue(this);
        dohvatPodataka = new DohvatPodataka();
        dohvatPodataka.setSendUrl(urlStatistika1);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_lokacija", idLokacija);
        dohvatPodataka.setParametri(params);
        dohvatPodataka.retrieveData(this,requestQueue);

        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                String url = dohvatPodataka.getSendUrl();
                if (odgovor != null) {
                    if(url.contains("Ocjena")){
                        model.PostaviPodatke(odgovor);
                        PostaviGrafStatOcjena();
                        dohvatPodataka.setSendUrl(urlStatistika2);
                        dohvatPodataka.retrieveData(GlavniIzbornikClient.this,requestQueue);
                    }else if(url.contains("Omiljena")){
                        model.PostaviPodatke(odgovor);
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("id_lokacija", idLokacija);
                        dohvatPodataka.setParametri(params);
                        dohvatPodataka.setSendUrl(urlReviewLokacija);
                        dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);
                        PostaviGrafStatOmiljena();
                    }else if(url.contains("LocationReviews")){
                        model.setReviewLocation(logika.parsiranjePodatakaReviewa(odgovor));
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("id_lokacija", idLokacija);
                        dohvatPodataka.setParametri(params);
                        dohvatPodataka.setSendUrl(urlReviewProizvod);
                        dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);
                        PostaviRecenzijeLokacija();
                    }else if(url.contains("RecentBeer")){
                        model.setReviewBeer(logika.parsiranjePodatakaReviewa(odgovor));
                        PostaviRecenzijePiva();
                    }

                }
            }
        });

    }

    private void PostaviRecenzijePiva() {
        ArrayList<Review> recenzijePiva = model.getReviewBeer();
        if (recenzijePiva == null || recenzijePiva.size()==0) {
            buttonRecenzijePive.setEnabled(false);
            recenzijaPiva.setVisibility(View.GONE);
            slikaPivo.setVisibility(View.GONE);
        } else if (recenzijePiva.size() > 0) {
            recenzijaPiva.setText(recenzijePiva.get(0).getKomentar().concat("\n".concat(recenzijePiva.get(0).getOcjena()).concat("\n".concat(recenzijePiva.get(0).getDatum_i_vrijeme_recenzije()))));
        }

    }

    private void PostaviRecenzijeLokacija() {
        ArrayList<Review> recenzijeLokacije = model.getReviewLocation();
        if (recenzijeLokacije == null || recenzijeLokacije.size()==0) {
            recenzija1Lokacija.setVisibility(View.GONE);
            recenzija2Lokacija.setVisibility(View.GONE);
            slika1Lokacija.setVisibility(View.GONE);
            slika2Lokacija.setVisibility(View.GONE);
        } else if (recenzijeLokacije.size() == 2) {
            recenzija1Lokacija.setText(recenzijeLokacije.get(0).getKomentar().concat("\n".concat(recenzijeLokacije.get(0).getOcjena()).concat("\n".concat(recenzijeLokacije.get(0).getDatum_i_vrijeme_recenzije()))));
            recenzija2Lokacija.setText(recenzijeLokacije.get(1).getKomentar().concat("\n".concat(recenzijeLokacije.get(1).getOcjena()).concat("\n".concat(recenzijeLokacije.get(1).getDatum_i_vrijeme_recenzije()))));
        } else if (recenzijeLokacije.size() == 1) {
            recenzija1Lokacija.setText(recenzijeLokacije.get(0).getKomentar().concat("\n".concat(recenzijeLokacije.get(0).getOcjena()).concat("\n".concat(recenzijeLokacije.get(0).getDatum_i_vrijeme_recenzije()))));
            slika2Lokacija.setVisibility(View.GONE);
            recenzija2Lokacija.setVisibility(View.GONE);
        }
    }

    private void PostaviGrafStatOmiljena() {
        LineGraphSeries <DataPoint> series  = new LineGraphSeries<>(model.getPodaci());
        graphOmiljena.addSeries(series);
        graphOmiljena.getGridLabelRenderer().setHorizontalAxisTitle("Month");
        graphOmiljena.getGridLabelRenderer().setVerticalAxisTitle("Omiljena Lokacija");
        graphOmiljena.getGridLabelRenderer().setPadding(55);
    }

    private void PostaviGrafStatOcjena() {
        LineGraphSeries <DataPoint> series  = new LineGraphSeries<>(model.getPodaci());
        graphOcjena.addSeries(series);
        graphOcjena.getGridLabelRenderer().setHorizontalAxisTitle("Month");
        graphOcjena.getGridLabelRenderer().setVerticalAxisTitle("Average Grade");
        graphOcjena.getGridLabelRenderer().setPadding(55);
    }

    public void openActivity2(){
        Intent intent = new Intent(this, IzbornikZaDodavanjeActivity.class);
        startActivity(intent);
    }
    public void OpenActivityBeerCatalog(){
        Intent intent = new Intent(this, BeerCatalogActivity.class);
        startActivity(intent);
    }
    public void OpenActivityEventCatalog(){
        Intent intent = new Intent(this, EventCatalogActivity.class);
        startActivity(intent);
    }
    public void OpenActivityTastingMeni(){
        Intent intent = new Intent(this, TastingMenuActivity.class);
        startActivity(intent);
    }
    private void OpenActivityPromotionCatalog() {
        Intent intent = new Intent(this, PromotionCatalogActivity.class);
        startActivity(intent);
    }


}