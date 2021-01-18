package com.example.beervana.BeerplacePage.Modularnost;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.basemodule.BaseClassForModules;
import com.example.beervana.BaseActivity;
import com.example.beervana.EventMenu.AddEventActivity;
import com.example.beervana.EventMenu.EventCatalogActivity;
import com.example.beervana.EventMenu.EventCatalogRecyclerAdapter;
import com.example.beervana.EventMenu.PrikazZaEventPodatkeActivity;
import com.example.beervana.R;
import com.example.modulzamodule.EventCatalogLogika;
import com.example.modulzamodule.Promotion;
import com.example.modulzamodule.Promotion1Logic;
import com.example.webservice.DohvatPodataka;
import com.example.webservice.SlanjePodataka;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromotionCatalogActivity extends BaseActivity implements PromotionCatalogRecyclerAdapter.onAddPromosListener {
    RecyclerView promotionRecyclerView;
    private RequestQueue requestQueue;
    private RequestQueue requestQueueBrisanje;
    PromotionCatalogRecyclerAdapter adapter;
    String pozicija;
    public static ArrayList<Promotion> promotionDataList;
    String url = "https://beervana2020.000webhostapp.com/test/DohvatiPromocijeKatalog.php";
    String urlBrisanje = "https://beervana2020.000webhostapp.com/test/ObrisiPromociju.php";
    private SharedPreferences sp;
    private String idLokacija;
    private int korisnik = 0;

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_catalog);
        initToolbar();
        view = findViewById(android.R.id.content).getRootView();
        promotionDataList = new ArrayList<>();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        sp = getSharedPreferences("login", MODE_PRIVATE);
        if (extras != null) {
            idLokacija = extras.getString("id_lokacija");
            korisnik = sp.getInt("id_uloga",0);
        } else {
            idLokacija = sp.getString("id_lokacija", "Nema Lokacija").split(",")[0];
        }

        promotionRecyclerView = findViewById(R.id.promotionRecyclerView);
        promotionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        retrieveData();

    }

    private void retrieveData() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        Promotion1Logic promotion1Logic = new Promotion1Logic();
        DohvatPodataka dohvatPodataka = new DohvatPodataka();
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_lokacija", idLokacija);
        dohvatPodataka.setParametri(params);
        dohvatPodataka.setSendUrl(url);
        dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);

        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                if (odgovor != null) {
                    /*eventDataList.clear();
                    eventDataList.addAll(logikaEventCatalog.parsiranjePodatakaEventData(odgovor));
                    adapter.notifyDataSetChanged();*/
                    promotionDataList.clear();
                    promotionDataList.addAll(promotion1Logic.parsiranjePodatakaPromotion(odgovor));
                    adapter = new PromotionCatalogRecyclerAdapter(PromotionCatalogActivity.this, promotionDataList, PromotionCatalogActivity.this);
                    promotionRecyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }
            }
        });

    }



    @Override
    public void onPromotionClick(int position) {
        if (korisnik == 1) {
            startActivity(new Intent(getApplicationContext(), PrikazZaEventPodatkeActivity.class).putExtra("position", position));
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            List<BaseClassForModules> modulesList = Modules.getModulesList();
            for(int i = 0; i<modulesList.size();i++){
                if(modulesList.get(i).getNaslov().equals(promotionDataList.get(position).getTip_promocije())){
                    pozicija = String.valueOf(i);
                }
            }
            CharSequence[] dijalogStavke = {"View data", "Edit data ", "Delete data"};
            builder.setTitle(promotionDataList.get(position).getNaziv_promocije());
            builder.setItems(dijalogStavke, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int odabir) {
                    switch (odabir) {
                        case 0:
                            startActivity(new Intent(getApplicationContext(), PrikazZaEventPodatkeActivity.class).putExtra("position", pozicija));
                            break;
                        case 1:
                            startActivity(new Intent(getApplicationContext(), LoadModuleFragmentActivity.class).putExtra("position", pozicija)
                                    .putExtra("id_lokacija",idLokacija).putExtra("id_promocija",promotionDataList.get(position).getId_promocija()));
                            break;
                        case 2:
                            DeletePromotion(position);
                            break;
                    }
                }
            });
            builder.create().show();
        }

    }

    private void DeletePromotion(int position) {
        requestQueueBrisanje = Volley.newRequestQueue(getApplicationContext());
        SlanjePodataka slanjePodataka = new SlanjePodataka(urlBrisanje);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_promocija", PromotionCatalogActivity.promotionDataList.get(position).getId_promocija());
        slanjePodataka.setParametri(params);
        slanjePodataka.sendData(getApplicationContext(), requestQueueBrisanje);
        requestQueueBrisanje.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                String odgovor = slanjePodataka.getOdgovor();
                if (odgovor.equals("Succesfully deleted your promotion!")) {
                    promotionDataList.remove(position);
                    adapter.notifyDataSetChanged();
                }
                Toast toast = Toast.makeText(getApplicationContext(), odgovor, Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}
