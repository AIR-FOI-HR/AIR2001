package com.example.beervana.UserData;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beervana.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StatisticsActivity extends BaseActivity {
    private String id_korisnik;
    private static final String url = "https://beervana2020.000webhostapp.com/test/FetchUserStatistics.php";
    private TextView broj_rec, broj_lok, broj_piva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        initToolbar();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        //id_korisnik = extras.getString("id_korisnik", "20");
        id_korisnik = "20";

        broj_rec = (TextView) findViewById(R.id.broj_rec);
        broj_lok = (TextView) findViewById(R.id.broj_lok);
        broj_piva = (TextView) findViewById(R.id.broj_piva);
        loadStatisticsData();
    }
    private void loadStatisticsData() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONArray array = response.getJSONArray("statistika");
                String Recenzije = " ";
                String Lokacije = " ";
                String Piva = " ";
                for(int i = 0; i < array.length(); i++){
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
        }, error -> {
            Toast.makeText(StatisticsActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}