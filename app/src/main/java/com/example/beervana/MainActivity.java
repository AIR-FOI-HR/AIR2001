package com.example.beervana;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.TastingMenu.DodavanjeDegustacijskihMeniaActivity;
import com.example.beervana.databinding.ActivityMainBinding;
import com.example.webservice.DohvatPodataka;
import com.example.webservice.SlanjePodataka;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import androidx.lifecycle.ViewModelProvider;

import android.text.method.LinkMovementMethod;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private String sendUrl = "https://beervana2020.000webhostapp.com/test/login_pokusaj.php";
    private Boolean gotLocation = false;
    protected LocationManager locationManager;
    FusedLocationProviderClient fusedLocationProviderClient;
    private RequestQueue requestQueue;
    private static final String TAG = MainActivity.class.getSimpleName();
    int success;
    private SharedPreferences.Editor editor;
    private SharedPreferences sp;
    private String TAG_SUCESS = "success";
    private String TAG_MESSAGE = "message";
    private String tag_json_obj = "json_obj_req";

    private EditText korisnickoIme;
    private EditText lozinka;

    private TextView errorKorisnickoIme;
    private TextView errorLozinka;
    private TextView signUp;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        super.onCreate(savedInstanceState);
        setContentView(view);

        // initialization fusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //When permission granted
           // getLocation();
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,0,0,this);
        }
         else {
            //When permission denied
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);

        }
        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //When permission granted
            //getLocation();
        }
        final MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        sp = getSharedPreferences("login", MODE_PRIVATE);

        //if(sp.getBoolean("logged",false)){
        //  openMainMenu();
        //}

        korisnickoIme = binding.etUsername;
        lozinka = binding.etPassword;

        errorKorisnickoIme = binding.errUnosKorisnickoIme;
        errorLozinka = binding.errUnosLozinka;

        errorKorisnickoIme.setText(viewModel.getErrorKorisnickoIme());
        errorLozinka.setText(viewModel.getErrorLozinka());

        errorKorisnickoIme.setVisibility(viewModel.errorKorisnickoImeVidljivost);
        errorLozinka.setVisibility(viewModel.errorLozinkaVidljivost);

        signUp = binding.btnSignUp;
        signUp.setMovementMethod(LinkMovementMethod.getInstance());


        binding.btnUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestQueue = Volley.newRequestQueue(getApplicationContext());
                viewModel.setKorisnickoIme(korisnickoIme.getText().toString());
                viewModel.setLozinka(lozinka.getText().toString());
                if (viewModel.ProvjeriPodatke()) {

                    Map<String, String> params = new HashMap<String, String>();
                    params.put("korsnicko_ime", korisnickoIme.getText().toString());
                    params.put("lozinka", lozinka.getText().toString());
                    DohvatPodataka dohvatPodataka = new DohvatPodataka();
                    dohvatPodataka.setSendUrl(sendUrl);
                    dohvatPodataka.setParametri(params);
                    dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);
                    //requestQueue = slanjePodataka.sendData(getApplicationContext());

                    requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
                        @Override
                        public void onRequestFinished(Request<Object> request) {

                            JSONObject odgovor = dohvatPodataka.getOdgovor();
                            //Toast noviToast = Toast.makeText(getApplicationContext(),odgovor.toString(),Toast.LENGTH_LONG);
                            //noviToast.show();
                            //System.out.println(odgovor.toString());
                            try {
                                //System.out.println(odgovor.getString("message"));
                                if (odgovor.getString("message").equals(" Successfully loged in")) {
                                    sp.edit().putBoolean("logged", true).apply();
                                    openMainMenu();
                                    KorisnikLogika korisnikLogika = new KorisnikLogika();
                                    if (odgovor != null) {
                                        User user = korisnikLogika.parsiranjePodatakaKorisnika(odgovor);
                                        editor = sp.edit();
                                        editor.putInt("id_korisnik", user.getId_korisnik());
                                        editor.putInt("id_clanstvo", user.getId_clanstvo());
                                        editor.putInt("id_uloga", user.getId_uloga());
                                        editor.putString("id_lokacija", user.getId_lokacija());
                                        editor.apply();
                                        //String a = sp.getString("id_lokacija", "");
                                    }

                                } else if (odgovor.getString("message").equals("Wrong username or password")) {
                                    viewModel.setErrorLozinka(odgovor.getString("message"));
                                    viewModel.errorLozinkaVidljivost = View.VISIBLE;
                                    errorLozinka.setText(viewModel.getErrorLozinka());
                                    errorLozinka.setVisibility(viewModel.errorLozinkaVidljivost);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });


                } else {
                    errorKorisnickoIme.setText(viewModel.getErrorKorisnickoIme());
                    errorLozinka.setText(viewModel.getErrorLozinka());
                }
                errorKorisnickoIme.setVisibility(viewModel.errorKorisnickoImeVidljivost);
                errorLozinka.setVisibility(viewModel.errorLozinkaVidljivost);
            }
        });
        binding.btnSignUp.setOnClickListener(v -> openRegistration());

    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if(location!=null){
                        Geocoder geocoder = new Geocoder(MainActivity.this,
                                Locale.getDefault());
                        try {
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                            editor.putFloat("Latitude",(float)addresses.get(0).getLatitude());
                            editor.putFloat("Longitude",(float)addresses.get(0).getLongitude());
                            editor.putString("Country",addresses.get(0).getCountryName());
                            editor.putString("City",addresses.get(0).getLocality());
                            editor.putString("Address",addresses.get(0).getAddressLine(0));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

    }

    public void openRegistration(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    public void openMainMenu(){
        Intent intent = new Intent(this,GlavniIzbornikClient.class);
        startActivity(intent);
    }

    public void openDegMenu()
    {
        Intent intent = new Intent(this, DodavanjeDegustacijskihMeniaActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        if (location.getAccuracy() < 1000 && !gotLocation && location!=null) {
            gotLocation = true;
            double latitude, longitude;
            Geocoder geocoder = new Geocoder(MainActivity.this,
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