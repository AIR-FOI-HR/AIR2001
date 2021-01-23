package com.example.beervana.UserData;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
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

public class UserDataActivity extends BaseActivity {
    private Integer id_korisnik;
    private TextView username1;
    private static final String url = "https://beervana2020.000webhostapp.com/test/fetchUserData.php";
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_data_activity);
        initToolbar();

        sp = getSharedPreferences("login", MODE_PRIVATE);
        id_korisnik = sp.getInt("id_korisnik", 0);

        username1 = findViewById(R.id.textVieww17);
        loadUserData();

        ImageView mImageView = findViewById(R.id.imageVieww14);
        mImageView.setOnClickListener(v -> openActivityUserSettings());

        ImageView mImageView1 = findViewById(R.id.imageVieww15);
        mImageView1.setOnClickListener(v -> openActivityUserStatistics());
    }

    private void loadUserData() {
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
                        jsonArray = odgovor.getJSONArray("korisnik");
                        jsonObject = jsonArray.getJSONObject(0);
                        username1.setText(jsonObject.getString("korsnicko_ime"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    private void openActivityUserStatistics() {
        Intent intent = new Intent(this, StatisticsActivity.class).putExtra("id_korisnik", id_korisnik);
        startActivity(intent);
    }

    private void openActivityUserSettings() {
        Intent intent = new Intent(this, UserActivity.class).putExtra("id_korisnik", id_korisnik);
        startActivity(intent);
    }
}