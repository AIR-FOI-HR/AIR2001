package com.example.beervana.activites;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.beervana.R;
import com.example.beervana.adapters.RecyclerViewAdapter;
import com.example.beervana.model.Info;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Test extends AppCompatActivity {

    private final String JSON_URL = "https://beervana2020.000webhostapp.com/test/fetchInfoPivovare.php";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Info> lstInfo;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m);

        lstInfo = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewid);
        jsonrequest();

    }

    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

                for(int i = 0; i< response.length(); i++){

                    try {
                        jsonObject = response.getJSONObject(i);
                        Info info = new Info();
                        info.setId_proizvod(jsonObject.getInt("id_proizvod"));
                        info.setNaziv_piva(jsonObject.getString("naziv_proizvoda"));
                        info.setOkus(jsonObject.getString("okus"));
                        info.setLitara(jsonObject.getString("litara"));
                        info.setSlika(jsonObject.getString("slika"));

                        lstInfo.add(info);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                setuprecyclerview(lstInfo);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(Test.this);
        requestQueue.add(request);

    }

    private void setuprecyclerview(List<Info> lstInfo) {

        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this, lstInfo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);

    }


}

