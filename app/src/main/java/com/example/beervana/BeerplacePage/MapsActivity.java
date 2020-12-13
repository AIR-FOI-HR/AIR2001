package com.example.beervana.BeerplacePage;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.beervana.BeerMenu.Beer;
import com.example.beervana.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        LatLng Beerplace = new LatLng(19.169257, 73.341601);
        map.addMarker(new MarkerOptions().position(Beerplace).title("Beerplace"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Beerplace));
    }
}