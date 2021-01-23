package com.example.beervana.BeerMenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceGroup;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BaseActivity;
import com.example.beervana.EventMenu.AddEventActivity;
import com.example.beervana.MainActivity;
import com.example.beervana.R;
import com.example.modulzamodule.Beer;
import com.example.modulzamodule.BeerLogic;
import com.example.webservice.DohvatPodataka;
import com.example.webservice.SlanjePodataka;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BeerCatalogActivity extends BaseActivity implements BeerCatalogRecyclerAdapter.onPivoListener{
    RecyclerView beerRecyclerView;
    private RequestQueue requestQueue;
    private RequestQueue requestQueueBrisanje;
    BeerCatalogRecyclerAdapter adapter;
    public static ArrayList<Beer> BeerArrayList = new ArrayList<>();
    String url = "https://beervana2020.000webhostapp.com/test/dohvacanjePiva.php";
    String urlBrisanje = "https://beervana2020.000webhostapp.com/test/BrisanjePiva.php";
    Beer beer;
    String idLokacija;
    SharedPreferences sp;
    View view;
    private int korisnik=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_catalog);
        initToolbar();
        view = findViewById(android.R.id.content).getRootView();
        beerRecyclerView = findViewById(R.id.RecyclerBeerCatalog);
        beerRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        sp = getSharedPreferences("login", MODE_PRIVATE);
        if(extras != null){
            idLokacija = extras.getString("id_lokacija");
            korisnik = sp.getInt("id_uloga", 0);
        }else{
            idLokacija = sp.getString("id_lokacija", "Nema Lokacija").split(",")[0];
        }

        retrieveData();
    }


    private void DeleteBeer(int position) {
        requestQueueBrisanje = Volley.newRequestQueue(getApplicationContext());
        SlanjePodataka slanjePodataka = new SlanjePodataka(urlBrisanje);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_proizvod", BeerCatalogActivity.BeerArrayList.get(position).getId_proizvod());
        slanjePodataka.setParametri(params);
        slanjePodataka.sendData(getApplicationContext(), requestQueueBrisanje);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                String odgovor = slanjePodataka.getOdgovor();
                if (odgovor.equals("Successfully deleted an tasting menu")) {
                    BeerArrayList.remove(position);
                    adapter.notifyDataSetChanged();
                }
                Toast toast = Toast.makeText(getApplicationContext(), odgovor, Toast.LENGTH_LONG);
                toast.show();
            }
        });
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
        if(korisnik == 1){
            startActivity(new Intent(getApplicationContext(), PrikazZaPodatkeOPivuActivity.class).putExtra("position",position));
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            CharSequence[] dialogItem = {"View data", "Edit data", "Delete data"};
            builder.setTitle(BeerArrayList.get(position).getNaziv_proizvoda());
            builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            startActivity(new Intent(getApplicationContext(), PrikazZaPodatkeOPivuActivity.class).putExtra("position",position));
                            break;
                        case 1:
                            startActivity(new Intent(getApplicationContext(), AddBeers.class).putExtra("position", position));
                            break;
                        case 2:
                            DeleteBeer(position);
                            break;
                    }
                }
            });
            builder.create().show();
        }
    }
}