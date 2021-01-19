package com.example.beervana.UserData;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BaseActivity;
import com.example.beervana.BeerplacePage.AdapterReview;
import com.example.beervana.BeerplacePage.ReviewsActivity;
import com.example.beervana.BeerplacePage.ReviewsLogic;
import com.example.webservice.DohvatPodataka;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beervana.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserActivity extends BaseActivity {
    private String id_korisnik;
    private TextView username1;
    private TextView lozinka1;
    private static final String url = "https://beervana2020.000webhostapp.com/test/fetchUserData.php";
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initToolbar();
        username1 = findViewById(R.id.username1);
        lozinka1 = findViewById(R.id.password1);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        id_korisnik = extras.getString("id_korisnik","20");
        id_korisnik = "20";
        loadUserData();
    }

    private void loadUserData() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONArray array = response.getJSONArray("korisnik");
                String data = " ";
                String data1 = " ";
                for(int i = 0; i < array.length(); i++){
                    JSONObject object = (JSONObject) array.get(i);
                    data = object.getString("korsnicko_ime");
                    data1 = object.getString("lozinka");

                }
                username1.setText(data);
                lozinka1.setText(data1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Toast.makeText(UserActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}