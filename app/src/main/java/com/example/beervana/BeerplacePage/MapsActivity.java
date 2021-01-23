package com.example.beervana.BeerplacePage;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.R;
import com.example.beervana.SettingsActivity;
import com.example.modulzamodule.KartaParser;
import com.example.webservice.DohvatPodataka;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private ArrayList<String> koordinate;
    GoogleMap map;
    private String idLokacija;
    private RequestQueue requestQueue;
    private String url = "https://beervana2020.000webhostapp.com/test/lokacijaNaMapi.php";
    boolean mapaSpremna = false;
    boolean podaciSpremni = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null){
            idLokacija = extras.getString("id_lokacija");
            retrieveData();
        }
        //OVAJ DIO DODATI ZA TOOLBAR
        ImageView yImageView = (ImageView)findViewById(R.id.settings_icon);
        yImageView.setOnClickListener(v -> openActivity3());

        //OVAJ DIO OTKOMENTIRATI KAD SE KREIRAJU AKTIVNOSTI
        /*
        ImageView mImageView = (ImageView)findViewById(R.id.user_icon);
        mImageView.setOnClickListener(v -> openActivity4());

        ImageView mImageView = (ImageView)findViewById(R.id.search_icon);
        mImageView.setOnClickListener(v -> openActivity5());
        */

        //
    }

    private void retrieveData() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        KartaParser kartaParser = new KartaParser();
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
                    koordinate = kartaParser.ParsiranjePodatakaZaKartu(odgovor);
                    podaciSpremni = true;

                    prikaziLokaciju();
                }
            }
        });

    }

    public void prikaziLokaciju(){
        if (mapaSpremna && podaciSpremni){
            LatLng Beerplace = new LatLng(Double.parseDouble(koordinate.get(0)), Double.parseDouble(koordinate.get(1)));
            map.addMarker(new MarkerOptions().position(Beerplace).title("Beerplace"));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(Beerplace,16.0F));

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        mapaSpremna = true;

        prikaziLokaciju();

    }
    //I OVAJ DIO DODATI ZA TOOLBAR
    public void openActivity3(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    //OVAJ DIO OTKOMENTIRATI KAD SE KREIRAJU AKTIVNOSTI
    /*
    public void openActivity4(){
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }
    public void openActivity5(){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
     */

    //KRAJ
}