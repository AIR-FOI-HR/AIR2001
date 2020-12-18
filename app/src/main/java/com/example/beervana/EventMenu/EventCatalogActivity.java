package com.example.beervana.EventMenu;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.R;
import com.example.webservice.DohvatPodataka;
import com.example.webservice.SlanjePodataka;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventCatalogActivity extends AppCompatActivity {
    ListView listView;
    private RequestQueue requestQueue;
    private RequestQueue requestQueueBrisanje;
    EventCatalogAdapter adapter;
    public static ArrayList<ModelPodatakEventCatalog> eventDataList = new ArrayList<>();
    String url = "https://beervana2020.000webhostapp.com/test/DohvacanjeDogadaja.php";
    String urlBrisanje = "https://beervana2020.000webhostapp.com/test/ObrisiDogadaj.php";
    private SharedPreferences sp;
    private String idLokacija;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_catalog);

        sp = getSharedPreferences("login", MODE_PRIVATE);
        idLokacija = sp.getString("id_lokacija","Nema Lokacija").split(",")[0];
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
                                startActivity(new Intent(getApplicationContext(), PrikazZaEventPodatkeActivity.class).putExtra("position",position));
                                break;
                            case 1:
                                startActivity(new Intent(getApplicationContext(), AddEventActivity.class).putExtra("position",position));
                                break;
                            case 2:
                                DeleteEvent(position);
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
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_lokacija",idLokacija);
        dohvatPodataka.setParametri(params);
        dohvatPodataka.setSendUrl(url);
        dohvatPodataka.retrieveData(getApplicationContext(),requestQueue);

        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request){
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                if(odgovor != null){
                    eventDataList.clear();
                    eventDataList.addAll(logikaEventCatalog.parsiranjePodatakaEventData(odgovor));
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }
    private void DeleteEvent(int pozicija){
        requestQueueBrisanje = Volley.newRequestQueue(getApplicationContext());
        SlanjePodataka slanjePodataka = new SlanjePodataka(urlBrisanje);
        Map<String, String> params=new HashMap<String, String>();
        params.put("id_dogadaja", EventCatalogActivity.eventDataList.get(pozicija).dogadaj.getIdDogadaj());
        slanjePodataka.setParametri(params);
        slanjePodataka.sendData(getApplicationContext(),requestQueueBrisanje);
        requestQueueBrisanje.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request){
                String odgovor = slanjePodataka.getOdgovor();
                if (odgovor.equals("Successfully deleted an event")){
                    eventDataList.remove(pozicija);
                    adapter.notifyDataSetChanged();
                }
                Toast toast = Toast.makeText(getApplicationContext(),odgovor,Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }

}
