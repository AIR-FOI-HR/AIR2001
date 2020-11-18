package com.example.beervana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BeerCatalog extends AppCompatActivity {

    ListView listView;
    MyAdapter adapter;
    public static ArrayList<Beer> BeerArrayList = new ArrayList<>();
    String url = "https://beervana2020.000webhostapp.com/test/dohvacanjePiva.php";
    Beer beer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_catalog);

        listView = findViewById(R.id.ListViewBeer);
        adapter = new MyAdapter(this,BeerArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),PrikazZaPodatkeOPivuActivity.class).putExtra("position",position));
            }
        });
        retrieveData();
    }

    private void retrieveData() {
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        BeerArrayList.clear();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("proizvod");

                            if(success.equals("1")){

                                for(int i = 0; i<jsonArray.length();i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id_proizvod = object.getString("id_proizvod");
                                    String id_kategorija = object.getString("id_kategorija");
                                    String naziv_proizvoda = object.getString("naziv_proizvoda");
                                    String cijena_proizvoda = object.getString("cijena_proizvoda");
                                    String okus = object.getString("okus");
                                    String litara = object.getString("litara");
                                    String slika = object.getString("slika");

                                    beer = new Beer(id_proizvod,id_kategorija,naziv_proizvoda,cijena_proizvoda,okus,litara,slika);
                                    BeerArrayList.add(beer);
                                    adapter.notifyDataSetChanged();


                                }
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BeerCatalog.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 1, 1.0f));
        requestQueue.add(request);

    }


}