package com.example.beervana;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.beervana.GlavniIzbornik.GlavniIzbornikClient;
import com.example.beervana.GlavniIzbornik.GlavniIzbornikUser;
import com.example.beervana.Toolbar.SearchActivity;
import com.example.beervana.Toolbar.SettingsActivity;
import com.example.beervana.UserData.UserDataActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public abstract class BaseActivity extends AppCompatActivity implements LocationListener {
    EditText pretrazivanje;
    TextView naslovna;
    private SharedPreferences sp;
    int uloga;
    private Boolean gotLocation = false;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("login", MODE_PRIVATE);
        uloga = sp.getInt("id_uloga", 0);


    }

    public void initToolbar() {
        ImageView mImageView = findViewById(R.id.settings_icon);
        mImageView.setOnClickListener(v -> openActivity3());

        //OVAJ DIO OTKOMENTIRATI KAD SE KREIRAJU AKTIVNOSTI

        ImageView mImageView1 = findViewById(R.id.user_icon);
        mImageView1.setOnClickListener(v -> openActivity4());

        ImageView imageViewPretrazivanje = findViewById(R.id.search_icon);
        imageViewPretrazivanje.setOnClickListener(v -> openActivity5());
        pretrazivanje = findViewById(R.id.txtpretrazivanje);

        naslovna = findViewById(R.id.naslovna);
        if(uloga==1) {
            naslovna.setOnClickListener(v -> openNaslovnaKorisnik());
        }
        else{
            naslovna.setOnClickListener(v -> openNaslovnaClient());
        }
        //

    }

    private void openNaslovnaClient() {
        if(this.getClass()!= GlavniIzbornikClient.class) {
            finishAffinity();
            Intent intent = new Intent(this, GlavniIzbornikClient.class);
            startActivity(intent);
        }
    }

    public void openNaslovnaKorisnik() {
        if(this.getClass()!= GlavniIzbornikUser.class) {
            finishAffinity();
            Intent intent = new Intent(this, GlavniIzbornikUser.class);
            startActivity(intent);

        }
    }


    public void openActivity3() {
        if(this.getClass()!= SettingsActivity.class) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
    }


    public void openActivity4() {
        Intent intent = new Intent(this, UserDataActivity.class);
        startActivity(intent);
    }

    public void openActivity5() {
        if (!pretrazivanje.getText().toString().equals("") && !pretrazivanje.getText().toString().equals(" ")) {
            Intent intent = new Intent(this, SearchActivity.class).putExtra("search", pretrazivanje.getText().toString());
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "You have to enter something to search.", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    public void blockToolbar() {
        ImageView mImageView = findViewById(R.id.settings_icon);
        mImageView.setEnabled(false);

        ImageView mImageView1 = findViewById(R.id.user_icon);
        mImageView1.setEnabled(false);

        ImageView imageViewPretrazivanje = findViewById(R.id.search_icon);
        imageViewPretrazivanje.setEnabled(false);
        pretrazivanje = findViewById(R.id.txtpretrazivanje);
        pretrazivanje.setEnabled(false);

        naslovna = findViewById(R.id.naslovna);
        naslovna.setEnabled(false);



    }

    public void unBlockToolbar() {
        ImageView mImageView = findViewById(R.id.settings_icon);
        mImageView.setEnabled(true);

        ImageView mImageView1 = findViewById(R.id.user_icon);
        mImageView1.setEnabled(true);

        ImageView imageViewPretrazivanje = findViewById(R.id.search_icon);
        imageViewPretrazivanje.setEnabled(true);
        pretrazivanje = findViewById(R.id.txtpretrazivanje);
        pretrazivanje.setEnabled(true);

        naslovna = findViewById(R.id.naslovna);
        naslovna.setEnabled(true);
    }
    @Override
    public void onLocationChanged(@NonNull Location location) {
        if (location.getAccuracy() < 1000 && !gotLocation && location != null) {
            gotLocation = true;
            double latitude, longitude;
            Geocoder geocoder = new Geocoder(this,
                    Locale.getDefault());
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            try {
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                editor = sp.edit();
                editor.putFloat("Latitude", (float) addresses.get(0).getLatitude());
                editor.putFloat("Longitude", (float) addresses.get(0).getLongitude());
                editor.putString("Country", addresses.get(0).getCountryName());
                editor.putString("City", addresses.get(0).getLocality());
                editor.putString("Address", addresses.get(0).getAddressLine(0));
                editor.apply();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
