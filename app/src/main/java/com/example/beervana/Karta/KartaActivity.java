package com.example.beervana.Karta;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.EventMenu.EventCatalogLogika;
import com.example.beervana.R;
import com.example.webservice.DohvatPodataka;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class KartaActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private RequestQueue requestQueue;
    private String url = "https://beervana2020.000webhostapp.com/test/GetLocationsForMap.php";
    KartaActivityViewModel viewModel;
    boolean kartaSpremna = false;
    boolean podaciSpremni = false;
    private SharedPreferences sp;
    private float KorisnikLongituda;
    private float KorisnikLatituda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karta);
        sp = getSharedPreferences("login", MODE_PRIVATE);
        KorisnikLongituda = sp.getFloat("Longitude", (float)0.0);
        KorisnikLatituda = sp.getFloat("Latitude", (float)0.0);
        viewModel = new ViewModelProvider(this).get(KartaActivityViewModel.class);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        retrieveData();
    }

    private void retrieveData() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        EventCatalogLogika logikaEventCatalog = new EventCatalogLogika();
        DohvatPodataka dohvatPodataka = new DohvatPodataka();
        //TODO Promijenit sa lokacijom korisnika
        String latituda = Float.toString(KorisnikLatituda);
        String longituda = Float.toString(KorisnikLongituda);
        Map<String, String> params = new HashMap<String, String>();
        params.put("Latituda",latituda);
        params.put("Longituda",longituda);
        dohvatPodataka.setParametri(params);
        dohvatPodataka.setSendUrl(url);
        dohvatPodataka.retrieveData(getApplicationContext(),requestQueue);

        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                if(odgovor != null){
                    viewModel.ParsirajIPohraniPodatke(odgovor);
                    podaciSpremni = true;
                    PrikaziPodatke();
                }else{
                    viewModel.podaciZaPrikaz = null;
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        kartaSpremna = true;


        LatLng korisnik = new LatLng(KorisnikLatituda, KorisnikLongituda);
        BitmapDescriptor ikona = BitmapDescriptorFactory.fromResource(R.drawable.ikonica);
        mMap.addMarker(new MarkerOptions().position(korisnik).title("Korisnik").snippet("Pozicija Korisnika").icon(ikona));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(korisnik,16.0F));
        googleMap.setOnInfoWindowClickListener(this);
        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            // Use default InfoWindow frame
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            // Defines the contents of the InfoWindow
            @Override
            public View getInfoContents(Marker marker) {
                if(marker.getTitle().equals("Korisnik")){
                    return null;
                }
                // Getting view from the layout file info_window_layout
                View infoWindow = getLayoutInflater().inflate(R.layout.info_window_karta, null);

                KartaModelPodataka podatak = new KartaModelPodataka();
                for(KartaModelPodataka clan : viewModel.podaciZaPrikaz){
                    if(clan.getIdMarker().equals(marker.getId())){
                        podatak = clan;
                        break;
                    }
                }
                TextView nazivLokacije  = (TextView) infoWindow.findViewById(R.id.txtInfoWindowNazivLokacije);
                TextView adresaLokacije = (TextView) infoWindow.findViewById(R.id.txtInfoWindowAdresalokacije);
                TextView ocijenaLokacije  = (TextView) infoWindow.findViewById(R.id.txtInfoWindowOcjenaLokacije);

                nazivLokacije.setText(podatak.lokacija.getNazivLokacija());
                adresaLokacije.setText(podatak.lokacija.getAdresaLokacija());
                if(!podatak.getOcjena().equals("null")){
                    ocijenaLokacije.setText(podatak.getOcjena());
                }else{
                    ocijenaLokacije.setText("-");
                }

                return infoWindow;

            }
        });
        PrikaziPodatke();
    }

    private void PrikaziPodatke() {
        if(kartaSpremna && podaciSpremni){
            if(viewModel.podaciZaPrikaz != null){
                for(KartaModelPodataka podatak : viewModel.podaciZaPrikaz){
                    LatLng latLng = new LatLng(Double.parseDouble(podatak.lokacija.getLatitudaLokacija()),Double.parseDouble(podatak.lokacija.getLongitudaLokacija()));
                    Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).title(podatak.lokacija.getId_lokacija()));
                    podatak.setIdMarker(marker.getId());
                }
            }else{
                Toast.makeText(this, "There aren't any locations in your area",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Info window clicked"+marker.getTitle(),
                Toast.LENGTH_SHORT).show();
    }
}
