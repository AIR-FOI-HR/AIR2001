package com.example.beervana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.beervana.databinding.AddBeersActivityBinding;
import com.example.webservice.SlanjePodataka;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddBeers extends AppCompatActivity {


    EditText naziv_proizvoda;
    EditText cijena_proizvoda;
    EditText okus;
    EditText kolicina;

    Spinner spinner1;
    ArrayList<String> lista = new ArrayList<>();
    ArrayAdapter<String> listaAdapter;
    RequestQueue requestQueue;
    String pozicija;
    Integer pozicija_int;

    FloatingActionButton podnesi;
    String sendUrl = "https://beervana2020.000webhostapp.com/test/addBeers.php";

    AddBeersActivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_beers_activity);
        binding = AddBeersActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        final AddBeersViewModel viewModel = new ViewModelProvider(this).get(AddBeersViewModel.class);

        requestQueue = Volley.newRequestQueue(this);
        spinner1 = findViewById(R.id.spinner1);
        String url = "https://beervana2020.000webhostapp.com/test/populateBeerType.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, response -> {
            try {
                JSONArray jsonArray = response.getJSONArray("kategorija");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String naziv_kategorije = jsonObject.optString("naziv_kategorije");
                    lista.add(naziv_kategorije);
                    listaAdapter = new ArrayAdapter<>(AddBeers.this,
                            android.R.layout.simple_spinner_item, lista);
                    listaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner1.setAdapter(listaAdapter);
                    //pitati jel dobro
                    //pozicija = spinner1.getSelectedItemPosition();

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {

        });
        requestQueue.add(jsonObjectRequest);

        binding.floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                pozicija = (String) spinner1.getSelectedItem();
                if (pozicija.equals("Svijetla")){
                    pozicija_int = 1;
                }
                else if (pozicija.equals("Crvena")){
                    pozicija_int = 2;
                }
                else if (pozicija.equals("Tamna")){
                    pozicija_int = 3;
                }
                else if (pozicija.equals("Crna")){
                    pozicija_int = 4;
                }

                naziv_proizvoda = findViewById(R.id.editTextTextPersonName);
                cijena_proizvoda = findViewById(R.id.editTextNumberDecimal);
                okus = findViewById(R.id.editTextTextPersonName3);
                kolicina = findViewById(R.id.editTextTextPersonName4);

                Map<String, String> params = new HashMap<String, String>();
                params.put("naziv_proizvoda", naziv_proizvoda.getText().toString());
                params.put("cijena_proizvoda", cijena_proizvoda.getText().toString());
                params.put("vrsta_proizvoda", okus.getText().toString());
                params.put("kolicina_proizvoda", kolicina.getText().toString());
                params.put("id_kategorija", pozicija_int.toString());

                SlanjePodataka slanjePodataka = new SlanjePodataka(sendUrl);
                slanjePodataka.setParametri(params);
                slanjePodataka.sendData(getApplicationContext(), requestQueue);

                requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
                    @Override
                    public void onRequestFinished(Request<Object> request) {
                        String odgovor = slanjePodataka.getOdgovor();
                        if (odgovor.equals("Succesfully added a beer")) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Successfully added a beer! ", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                });
            }
        });
    }



}