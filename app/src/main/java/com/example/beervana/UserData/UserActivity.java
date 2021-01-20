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
import com.example.beervana.BeerMenu.AddBeersViewModel;
import com.example.beervana.BeerplacePage.AdapterReview;
import com.example.beervana.BeerplacePage.ReviewsActivity;
import com.example.beervana.BeerplacePage.ReviewsLogic;
import com.example.beervana.databinding.AddBeersActivityBinding;
import com.example.beervana.databinding.UserDataActivityBinding;
import com.example.webservice.DohvatPodataka;
import com.example.webservice.SlanjePodataka;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    EditText new_username;
    EditText old_password;
    EditText new_password1;
    EditText new_password2;
    private static final String url = "https://beervana2020.000webhostapp.com/test/fetchUserData.php";
    private static final String url_username = "https://beervana2020.000webhostapp.com/test/AzurirajUsername.php";
    private static final String url_password = "https://beervana2020.000webhostapp.com/test/AzurirajPassword.php";
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

        Button promijeni_username = (Button) findViewById(R.id.promijeni_username);
        promijeni_username.setOnClickListener(v -> setNewUsername());

        Button promijeni_lozinku = (Button) findViewById(R.id.promijeni_lozinku);
        promijeni_lozinku.setOnClickListener(v -> setNewPassword());

    }

    private void setNewPassword() {
        old_password = (EditText) findViewById(R.id.editTextTextPassword);
        new_password1 = (EditText) findViewById(R.id.editTextTextPassword2);
        new_password2 = (EditText) findViewById(R.id.editTextTextPassword3);
        String old_password_a = old_password.getText().toString();
        String new_password1_a = new_password1.getText().toString();
        String old_password2_a = new_password2.getText().toString();
        loadPassword(old_password_a, new_password1_a, old_password2_a);
    }

    private void loadPassword(String old_password, String new_password_1, String new_password_2){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {

                JSONArray array = response.getJSONArray("korisnik");
                String korime = " ";
                String lozinka = " ";
                for(int i = 0; i < array.length(); i++){
                    JSONObject object = (JSONObject) array.get(i);
                    lozinka = object.getString("lozinka");

                }

                String userLogic = new UserLogic().ProvjeraUnosaLozinke(new_password_1, new_password_2);
                String userLogic2 = new UserLogic().ProvjeraIspravnostiLozinke(new_password_1, new_password_2);
                String userLogic3 = new UserLogic().ProvjeraIspravnostiNoveLozinke(new_password_1, new_password_2, old_password);

                if(userLogic.equals("") | userLogic2.equals("") | userLogic3.equals("")){
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("new_password",new_password_1);
                    params.put("id_user", id_korisnik);

                    SlanjePodataka slanjePodataka = new SlanjePodataka(url_password);
                    slanjePodataka.setParametri(params);
                    slanjePodataka.sendData(getApplicationContext(), requestQueue);

                    requestQueue.addRequestFinishedListener(request1 -> {
                        String odgovor = slanjePodataka.getOdgovor();
                        if (odgovor.equals("Succesfully changed your password")) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Succesfully changed your password", Toast.LENGTH_LONG);
                            toast.show();

                        }
                    });
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Toast.makeText(UserActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void setNewUsername() {
        new_username = (EditText) findViewById(R.id.editTextTextPersonName2);
        String username = new_username.getText().toString();
        loadUsername(username);
    }

    private void loadUsername(String new_username){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {

                JSONArray array = response.getJSONArray("korisnik");
                String korime = " ";
                String lozinka = " ";
                for(int i = 0; i < array.length(); i++){
                    JSONObject object = (JSONObject) array.get(i);
                    korime = object.getString("korsnicko_ime");

                }


                String userLogic = new UserLogic().ProvjeraUnosaKorisnickogImena(new_username);
                String userLogic2 = new UserLogic().ProvjeraIspravnostiKorisnickogImena(korime, new_username);

                if(userLogic.equals("") | userLogic2.equals("")){
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("new_username",new_username);
                    params.put("old_username",korime);

                    SlanjePodataka slanjePodataka = new SlanjePodataka(url_username);
                    slanjePodataka.setParametri(params);
                    slanjePodataka.sendData(getApplicationContext(), requestQueue);

                    requestQueue.addRequestFinishedListener(request1 -> {
                        String odgovor = slanjePodataka.getOdgovor();
                        if (odgovor.equals("Succesfully changed your username")) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Succesfully changed your username", Toast.LENGTH_LONG);
                            toast.show();

                        }
                    });
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Toast.makeText(UserActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void loadUserData() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONArray array = response.getJSONArray("korisnik");
                String korime = " ";
                String lozinka = " ";
                for(int i = 0; i < array.length(); i++){
                    JSONObject object = (JSONObject) array.get(i);
                    korime = "Username: " + (object.getString("korsnicko_ime"));
                    lozinka = object.getString("lozinka");

                }
                username1.setText(korime);

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