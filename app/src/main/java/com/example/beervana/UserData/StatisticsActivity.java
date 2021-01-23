package com.example.beervana.UserData;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BaseActivity;
import com.example.beervana.R;
import com.example.webservice.DohvatPodataka;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StatisticsActivity extends BaseActivity {
    private Integer id_korisnik;
    private static final String url = "https://beervana2020.000webhostapp.com/test/FetchUserStatistics.php";
    private TextView broj_rec, broj_lok, broj_piva;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        initToolbar();
        sp = getSharedPreferences("login", MODE_PRIVATE);
        id_korisnik = sp.getInt("id_korisnik", 0);

        broj_rec = findViewById(R.id.broj_rec);
        broj_lok = findViewById(R.id.broj_lok);
        broj_piva = findViewById(R.id.broj_piva);
        loadStatisticsData();
    }

    private void loadStatisticsData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        DohvatPodataka dohvatPodataka = new DohvatPodataka();
        Map<String, String> params = new HashMap<>();
        params.put("id_korisnik", id_korisnik.toString());
        dohvatPodataka.setParametri(params);
        dohvatPodataka.setSendUrl(url);
        dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                if (odgovor != null) {
                    JSONArray jsonArray = null;
                    JSONObject jsonObject = null;
                    try {
                        JSONArray array = odgovor.getJSONArray("statistika");
                        String Recenzije = " ";
                        String Lokacije = " ";
                        String Piva = " ";
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = (JSONObject) array.get(i);
                            Recenzije = "You have written " + (object.getString("broj_recenzija")) + " reviews";
                            Lokacije = "You have " + (object.getString("omiljene_lokacije")) + " favourite places";
                            Piva = "You have " + (object.getString("omiljena_piva")) + " favourite beers";

                        }
                        broj_rec.setText(Recenzije);
                        broj_lok.setText(Lokacije);
                        broj_piva.setText(Piva);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
}