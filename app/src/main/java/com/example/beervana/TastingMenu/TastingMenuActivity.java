package com.example.beervana.TastingMenu;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BaseActivity;
import com.example.beervana.EventMenu.EventCatalogActivity;
import com.example.beervana.EventMenu.EventCatalogRecyclerAdapter;
import com.example.beervana.R;
import com.example.beervana.SettingsActivity;
import com.example.webservice.DohvatPodataka;
import com.example.webservice.SlanjePodataka;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TastingMenuActivity extends BaseActivity implements RecyclerTastingMenuAdapter.onTastingMenuListener {
    public static final String EXTRA_MESSAGE = "com.example.beervana.TastingMenu.MESSAGE";
    RecyclerView tastingMenuRecyclerView;
    RecyclerTastingMenuAdapter adapter;
    public static ArrayList<TastingMenu> tastingMenuArray = new ArrayList<>();
    String url = "https://beervana2020.000webhostapp.com/test/dohvacanjeDegMenia.php";
    private SharedPreferences sp;
    private String idLokacija;
    View view;
    private int korisnik = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasting_menu);
        initToolbar();
        view = findViewById(android.R.id.content).getRootView();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        sp = getSharedPreferences("login", MODE_PRIVATE);
        if(extras != null){
            idLokacija = extras.getString("id_lokacija");
            korisnik = sp.getInt("id_uloga",0);
        }else{
            idLokacija = sp.getString("id_lokacija", "Nema Lokacija").split(",")[0];
        }

        tastingMenuRecyclerView = findViewById(R.id.tastingMenuList);
        tastingMenuRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        retriveData();


    }

    private void DeleteMenu(int position) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String urlBrisanje = "https://beervana2020.000webhostapp.com/test/DeleteTastingMenu.php";
        SlanjePodataka slanjePodataka = new SlanjePodataka(urlBrisanje);
        Map<String, String> params = new HashMap<String, String>();
        params.put("tastingMenuName", tastingMenuArray.get(position).getName());
        slanjePodataka.setParametri(params);
        slanjePodataka.sendData(getApplicationContext(), requestQueue);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                String odgovor = slanjePodataka.getOdgovor();
                if (odgovor.equals("Successfully deleted an tasting menu")) {
                    tastingMenuArray.remove(position);
                    adapter.notifyDataSetChanged();
                }
                Toast toast = Toast.makeText(getApplicationContext(), odgovor, Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    private void retriveData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        DohvatPodataka dohvatPodataka = new DohvatPodataka();
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_lokacija", idLokacija);
        dohvatPodataka.setParametri(params);
        dohvatPodataka.setSendUrl(url);
        dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);
        LoadTastingMenu loadTastingMenu = new LoadTastingMenu();
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                if (odgovor != null) {
                    tastingMenuArray.clear();
                    tastingMenuArray.addAll(loadTastingMenu.loadTastingMenu(odgovor));
                    adapter = new RecyclerTastingMenuAdapter(TastingMenuActivity.this, tastingMenuArray,TastingMenuActivity.this);
                    tastingMenuRecyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onTastingMenuClick(int position) {
        if(korisnik == 1){
            startActivity(
                    new Intent(getApplicationContext(),
                            TastingMenuDetailsActivity.class
                    ).putExtra(EXTRA_MESSAGE, tastingMenuArray.get(position).getName()));
        }else{
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        ProgressDialog progressDialog = new ProgressDialog(view.getContext());
        CharSequence[] dialogItem = {"View data", "Edit data", " Delete data"};
        builder.setTitle(tastingMenuArray.get(position).getName());
        builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        startActivity(
                                new Intent(getApplicationContext(),
                                        TastingMenuDetailsActivity.class
                                ).putExtra(EXTRA_MESSAGE, tastingMenuArray.get(position).getName()));
                        break;
                    case 1:
                        startActivity(
                                new Intent(getApplicationContext(),
                                        DodavanjeDegustacijskihMeniaActivity.class
                                ).putExtra(EXTRA_MESSAGE, tastingMenuArray.get(position).getName()));
                        break;
                    case 2:
                        DeleteMenu(position);
                        break;
                }
            }
        });
        builder.create().show();
    }
    }



}