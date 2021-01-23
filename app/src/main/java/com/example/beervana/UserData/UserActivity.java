package com.example.beervana.UserData;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BaseActivity;
import com.example.beervana.R;
import com.example.webservice.DohvatPodataka;
import com.example.webservice.SlanjePodataka;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserActivity extends BaseActivity {
    private Integer id_korisnik;
    private TextView username1;
    private TextView lozinka1;
    EditText new_username;
    EditText old_password;
    EditText new_password1;
    EditText new_password2;
    private static final String url = "https://beervana2020.000webhostapp.com/test/fetchUserData.php";
    private static final String url_username = "https://beervana2020.000webhostapp.com/test/AzurirajUsername.php";
    private static final String url_password = "https://beervana2020.000webhostapp.com/test/AzurirajPassword.php";
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        initToolbar();

        username1 = findViewById(R.id.username1);
        lozinka1 = findViewById(R.id.password1);

        sp = getSharedPreferences("login", MODE_PRIVATE);
        id_korisnik = sp.getInt("id_korisnik", 0);

        loadUserData();

        Button promijeni_username = findViewById(R.id.promijeni_username);
        promijeni_username.setOnClickListener(v -> setNewUsername());

        Button promijeni_lozinku = findViewById(R.id.promijeni_lozinku);
        promijeni_lozinku.setOnClickListener(v -> setNewPassword());

    }

    private void setNewPassword() {
        old_password = findViewById(R.id.editTextTextPassword);
        new_password1 = findViewById(R.id.editTextTextPassword2);
        new_password2 = findViewById(R.id.editTextTextPassword3);
        String old_password_a = old_password.getText().toString();
        String new_password1_a = new_password1.getText().toString();
        String new_password2_a = new_password2.getText().toString();
        UserLogic uL = new UserLogic();
        if (uL.ProvjeraUnosaLozinke(new_password1_a, new_password2_a) == ""
                || uL.ProvjeraIspravnostiNoveLozinke(new_password1_a, new_password2_a, old_password_a) == ""
                || uL.ProvjeraIspravnostiLozinke(new_password1_a, new_password2_a) == "") {
            loadPassword(new_password1_a);
        }
    }

    private void loadPassword(String new_password_1) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        SlanjePodataka slanjePodataka = new SlanjePodataka(url_password);
        Map<String, String> params = new HashMap<>();
        params.put("id_user", id_korisnik.toString());
        params.put("new_password", new_password_1);
        slanjePodataka.setParametri(params);
        slanjePodataka.sendData(getApplicationContext(), requestQueue);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                String odgovor = slanjePodataka.getOdgovor();
                if (odgovor.equals("Succesfully changed your password")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Succesfully changed your password", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    private void setNewUsername() {
        new_username = findViewById(R.id.editTextTextPersonName2);
        String username = new_username.getText().toString();
        UserLogic uL = new UserLogic();
        if (uL.ProvjeraIspravnostiKorisnickogImena(username1.getText().toString(), username) == "") {
            loadUsername(username);
        }
    }

    private void loadUsername(String new_username) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        SlanjePodataka slanjePodataka = new SlanjePodataka(url_username);
        Map<String, String> params = new HashMap<>();
        params.put("new_username", new_username);
        params.put("korsnicko_ime", username1.getText().toString());
        slanjePodataka.setParametri(params);

        slanjePodataka.sendData(getApplicationContext(), requestQueue);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                String odgovor = slanjePodataka.getOdgovor();
                if (odgovor.equals("Succesfully changed your username")) {
                    username1.setText(new_username);
                    Toast toast = Toast.makeText(getApplicationContext(), "Succesfully changed your username", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), odgovor, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
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
}