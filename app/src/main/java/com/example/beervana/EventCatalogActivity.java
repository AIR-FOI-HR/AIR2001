package com.example.beervana;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.webservice.DohvatPodataka;

import org.json.JSONObject;

import java.util.ArrayList;

public class EventCatalogActivity extends AppCompatActivity {
    ListView listView;
    private RequestQueue requestQueue;
    EventCatalogAdapter adapter;
    public static ArrayList<ModelPodatakEventCatalog> eventDataList = new ArrayList<>();
    String url = "https://beervana2020.000webhostapp.com/test/DohvacanjeDogadaja.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_catalog);

        listView = findViewById(R.id.ListViewEvent);
        adapter = new EventCatalogAdapter(this,eventDataList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                CharSequence[] dijalogStavke = {"View data","Edit data ","Delete data"};
                builder.setTitle(eventDataList.get(position).dogadaj.getNazivDogadaj());
                builder.setItems(dijalogStavke, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int odabir) {
                        switch (odabir){
                            case 0:
                                startActivity(new Intent(getApplicationContext(),PrikazZaEventPodatkeActivity.class).putExtra("position",position));
                                break;
                            case 1:
                                break;
                            case 2:
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        retrieveData();
    }

    private void retrieveData() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        EventCatalogLogika logikaEventCatalog = new EventCatalogLogika();
        DohvatPodataka dohvatPodataka = new DohvatPodataka();
        dohvatPodataka.setSendUrl(url);
        dohvatPodataka.retrieveData(getApplicationContext(),requestQueue);

        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request){
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                if(odgovor != null){
                    eventDataList.addAll(logikaEventCatalog.parsiranjePodatakaEventData(odgovor));
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }

}
