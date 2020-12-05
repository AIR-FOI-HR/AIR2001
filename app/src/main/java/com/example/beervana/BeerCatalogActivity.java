package com.example.beervana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.webservice.DohvatPodataka;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BeerCatalogActivity extends AppCompatActivity {

    ListView listView;
    private RequestQueue requestQueue;
    MyAdapter adapter;
    public static ArrayList<Beer> BeerArrayList = new ArrayList<>();
    String url = "https://beervana2020.000webhostapp.com/test/dohvacanjePiva.php";
    Beer beer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_catalog);

        listView = findViewById(R.id.ListViewBeer);
        adapter = new MyAdapter(this,BeerArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),PrikazZaPodatkeOPivuActivity.class).putExtra("position",position));
            }
        });
        retrieveData();
    }

    private void retrieveData() {
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
                    BeerArrayList.clear();
                    BeerArrayList.addAll(beerLogic.parsiranjePodatakaPiva(odgovor));
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }




}