package com.example.beervana;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.TastingMenu.DodavanjeDegustacijskihMeniaActivity;
import com.example.beervana.databinding.ActivityMainBinding;
import com.example.webservice.DohvatPodataka;
import com.example.webservice.SlanjePodataka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.text.method.LinkMovementMethod;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String sendUrl="https://beervana2020.000webhostapp.com/test/login_pokusaj.php";
    private RequestQueue requestQueue;
    private  static  final  String TAG=MainActivity.class.getSimpleName();
    int success;
    SharedPreferences sp;
    private String TAG_SUCESS="success";
    private String TAG_MESSAGE="message";
    private String tag_json_obj="json_obj_req";

    private EditText korisnickoIme;
    private EditText lozinka;

    private TextView errorKorisnickoIme;
    private TextView errorLozinka;
    private TextView signUp;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        super.onCreate(savedInstanceState);
        setContentView(view);

        final MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        sp = getSharedPreferences("login",MODE_PRIVATE);

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
                requestQueue= Volley.newRequestQueue(getApplicationContext());
                viewModel.setKorisnickoIme(korisnickoIme.getText().toString());
                viewModel.setLozinka(lozinka.getText().toString());
                if (viewModel.ProvjeriPodatke()) {

                    Map<String, String> params=new HashMap<String, String>();
                    params.put("korsnicko_ime",korisnickoIme.getText().toString());
                    params.put("lozinka",lozinka.getText().toString());
                    DohvatPodataka dohvatPodataka = new DohvatPodataka();
                    dohvatPodataka.setSendUrl(sendUrl);
                    dohvatPodataka.setParametri(params);
                    dohvatPodataka.retrieveData(getApplicationContext(),requestQueue);
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
                                if(odgovor.getString("message").equals(" Successfully loged in")){
                                    sp.edit().putBoolean("logged",true).apply();
                                    openMainMenu();
                                    KorisnikLogika korisnikLogika = new KorisnikLogika();
                                    if(odgovor!=null){
                                        User user = korisnikLogika.parsiranjePodatakaKorisnika(odgovor);
                                        SharedPreferences.Editor editor = sp.edit();
                                        editor.putInt("id_korisnik",user.getId_korisnik());
                                        editor.putInt("id_clanstvo",user.getId_clanstvo());
                                        editor.putInt("id_uloga",user.getId_uloga());
                                        editor.apply();

                                    }

                                }
                                else if (odgovor.getString("message").equals("Wrong username or password")){
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
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegistration();
            }
        });

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



}