package com.example.beervana.TastingMenu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BaseActivity;
import com.example.modulzamodule.Beer;
import com.example.modulzamodule.BeerLogic;
import com.example.beervana.R;
import com.example.webservice.DohvatPodataka;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TastingMenuDetailsActivity extends BaseActivity {
    ListView listView;
    TastingMenuContentAdapter adapter;
    TextView tastingMenuName;
    public static ArrayList<Beer> tastingMenuContentArray = new ArrayList<Beer>();
    String url = "https://beervana2020.000webhostapp.com/test/GetTastingMenuBeers.php";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasting_menu_content);
        initToolbar();
        Intent intent = getIntent();
        String message = intent.getStringExtra("menu name");
        String menuId = intent.getStringExtra("menuId");
        tastingMenuName = findViewById(R.id.tastingMenuContentName);
        tastingMenuName.setText(message);

        listView = findViewById(R.id.tastingMenuContentList);
        adapter = new TastingMenuContentAdapter(this, tastingMenuContentArray);
        listView.setAdapter(adapter);


        retriveData(menuId);
    }

    private void retriveData(String message) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        DohvatPodataka dohvatPodataka = new DohvatPodataka();
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_meni", message);
        dohvatPodataka.setParametri(params);
        dohvatPodataka.setSendUrl(url);
        dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);
        BeerLogic beers = new BeerLogic();
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                try {
                    if(odgovor!=null) {
                        if (odgovor.getString("message").equals(" Beers successfully loaded")) {
                            tastingMenuContentArray.clear();
                            tastingMenuContentArray.addAll(beers.parsiranjePodatakaPivaZaDegustacijskiMeni(odgovor));
                            adapter.notifyDataSetChanged();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ;
            }
        });
    }
}

