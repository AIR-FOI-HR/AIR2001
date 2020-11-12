package com.example.beervana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.beervana.databinding.AddBeersActivityBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AddBeers extends AppCompatActivity {
    AddBeersActivityBinding binding;

    Spinner spinner1;
    ArrayList<String> lista = new ArrayList<>();
    ArrayAdapter<String> listaAdapter;
    RequestQueue requestQueue;

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
                for(int i=0; i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String naziv_kategorije = jsonObject.optString("naziv_kategorije");
                    lista.add(naziv_kategorije);
                    listaAdapter = new ArrayAdapter<>(AddBeers.this,
                            android.R.layout.simple_spinner_item, lista);
                    listaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner1.setAdapter(listaAdapter);
            }

            } catch (JSONException e){
                e.printStackTrace();
            }
        }, error -> {

        });
        requestQueue.add(jsonObjectRequest);
    }

}