package com.example.beervana.UserData;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BaseActivity;
import com.example.beervana.BeerplacePage.ReviewsActivity;
import com.example.beervana.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserDataActivity extends BaseActivity {
    private String id_korisnik;
    private TextView username1;
    private static final String url = "https://beervana2020.000webhostapp.com/test/fetchUserData.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_data_activity);
        initToolbar();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        //id_korisnik = extras.getString("id_korisnik", "20");
        id_korisnik = "20";
        username1 = findViewById(R.id.textVieww17);
        loadUserData();

        ImageView mImageView = (ImageView) findViewById(R.id.imageVieww14);
        mImageView.setOnClickListener(v -> openActivityUserSettings());

        ImageView mImageView1 = (ImageView) findViewById(R.id.imageVieww15);
        mImageView1.setOnClickListener(v -> openActivityUserStatistics());
    }

    private void loadUserData() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONArray array = response.getJSONArray("korisnik");
                String korime = " ";
                String lozinka = " ";
                for(int i = 0; i < array.length(); i++){
                    JSONObject object = (JSONObject) array.get(i);
                    korime = "Hello, " + (object.getString("korsnicko_ime")) + "!";
                    //lozinka = object.getString("lozinka");

                }
                username1.setText(korime);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Toast.makeText(UserDataActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void openActivityUserStatistics() {
        Intent intent = new Intent(this, UserActivity.class).putExtra("id_korisnik", id_korisnik);
        startActivity(intent);
    }

    private void openActivityUserSettings() {
        Intent intent = new Intent(this, UserActivity.class).putExtra("id_korisnik", id_korisnik);
        startActivity(intent);
    }
}