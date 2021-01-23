package com.example.beervana.EventMenu;

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
import com.example.beervana.BaseActivity;
import com.example.beervana.R;
import com.example.modulzamodule.EventCatalogLogika;
import com.example.modulzamodule.ModelPodatakEventCatalog;
import com.example.webservice.DohvatPodataka;
import com.example.webservice.SlanjePodataka;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventCatalogActivity extends BaseActivity implements EventCatalogRecyclerAdapter.onDogadajListener {
    RecyclerView eventsRecyclerView;
    private RequestQueue requestQueue;
    private RequestQueue requestQueueBrisanje;
    EventCatalogRecyclerAdapter adapter;
    public static ArrayList<ModelPodatakEventCatalog> eventDataList;
    String url = "https://beervana2020.000webhostapp.com/test/DohvacanjeDogadaja.php";
    String urlBrisanje = "https://beervana2020.000webhostapp.com/test/ObrisiDogadaj.php";
    private SharedPreferences sp;
    private String idLokacija;
    private int korisnik = 0;

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_catalog);
        initToolbar();
        view = findViewById(android.R.id.content).getRootView();
        eventDataList = new ArrayList<>();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        sp = getSharedPreferences("login", MODE_PRIVATE);
        if (extras != null) {
            idLokacija = extras.getString("id_lokacija");
            korisnik = sp.getInt("id_uloga", 0);
        } else {
            idLokacija = sp.getString("id_lokacija", "Nema Lokacija").split(",")[0];
        }

        eventsRecyclerView = findViewById(R.id.eventsRecyclerView);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        retrieveData();

    }

    private void retrieveData() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        EventCatalogLogika logikaEventCatalog = new EventCatalogLogika();
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
                    eventDataList.clear();
                    eventDataList.addAll(logikaEventCatalog.parsiranjePodatakaEventData(odgovor));
                    adapter = new EventCatalogRecyclerAdapter(EventCatalogActivity.this, eventDataList, EventCatalogActivity.this);
                    eventsRecyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }
            }
        });

    }

    private void DeleteEvent(int pozicija) {
        requestQueueBrisanje = Volley.newRequestQueue(getApplicationContext());
        SlanjePodataka slanjePodataka = new SlanjePodataka(urlBrisanje);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_dogadaja", EventCatalogActivity.eventDataList.get(pozicija).dogadaj.getIdDogadaj());
        slanjePodataka.setParametri(params);
        slanjePodataka.sendData(getApplicationContext(), requestQueueBrisanje);
        requestQueueBrisanje.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                String odgovor = slanjePodataka.getOdgovor();
                if (odgovor.equals("Successfully deleted an event")) {
                    eventDataList.remove(pozicija);
                    adapter.notifyDataSetChanged();
                }
                Toast toast = Toast.makeText(getApplicationContext(), odgovor, Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }

    @Override
    public void onEventClick(int position) {
        if (korisnik == 1) {
            startActivity(new Intent(getApplicationContext(), PrikazZaEventPodatkeActivity.class).putExtra("position", position));
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

            CharSequence[] dijalogStavke = {"View data", "Edit data ", "Delete data"};
            builder.setTitle(eventDataList.get(position).dogadaj.getNazivDogadaj());
            builder.setItems(dijalogStavke, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int odabir) {
                    switch (odabir) {
                        case 0:
                            startActivity(new Intent(getApplicationContext(), PrikazZaEventPodatkeActivity.class).putExtra("position", position));
                            break;
                        case 1:
                            startActivity(new Intent(getApplicationContext(), AddEventActivity.class).putExtra("position", position));
                            break;
                        case 2:
                            DeleteEvent(position);
                            break;
                    }
                }
            });
            builder.create().show();
        }
    }

}
