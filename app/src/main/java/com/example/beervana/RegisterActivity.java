package com.example.beervana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.beervana.databinding.ActivityRegisterBinding;
import com.example.webservice.SlanjePodataka;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.Inet4Address;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String sendUrl="https://beervana2020.000webhostapp.com/test/email.php";
    private RequestQueue requestQueue;
    private  static  final  String TAG=RegisterActivity.class.getSimpleName();
    int success;
    private String TAG_SUCESS="success";
    private String TAG_MESSAGE="message";
    private String tag_json_obj="json_obj_req";
    private EditText ime;
    private EditText  prezime;
    private EditText  brojMobitela;
    private EditText  email;
    private EditText  korisnickoIme;
    private EditText  lozinka;
    private EditText  ponovljenaLozinka;
    private Spinner   uloga;
    private EditText  nazivLokacije;
    private EditText  oibLokacije;
    private EditText  opisLokacije;
    private EditText  grad;
    private EditText  ulica;
    private EditText  kucniBroj;

    private TextView errUnosIme ;
    private TextView errUnosPrezime ;
    private TextView errUnosBrojMobitela ;
    private TextView errUnosEmail ;
    private TextView errUnosKorisnickoIme ;
    private TextView errUnosLozinka ;
    private TextView errUnosPonovljenjaLozinka ;
    private TextView errSpinUloga ;
    private TextView errUnosNazivLokacije ;
    private TextView errUnosOibLokacije ;
    private TextView errUnosOpisLokacije ;
    private TextView errUnosGrad ;
    private TextView errUnosUlica ;
    private TextView errUnosKucniBroj ;
    boolean provjeraPostojeLiPodaci;

    ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        final RegisterActivityViewModel viewModel = new ViewModelProvider(this).get(RegisterActivityViewModel.class);


        final Spinner listaUloga = binding.spinUloga;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ListaUloga, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listaUloga.setAdapter(adapter);
        listaUloga.setOnItemSelectedListener(this);

        ime = binding.unosIme;
        prezime = binding.unosPrezime;
        brojMobitela = binding.unosBrojMobitela;
        email = binding.unosEmail;
        korisnickoIme = binding.unosKorisnickoIme;
        lozinka = binding.unosLozinka;
        ponovljenaLozinka= binding.unosPonovljenaLozinka;
        uloga = binding.spinUloga;
        nazivLokacije = binding.unosNazivLokacije;
        oibLokacije = binding.unosOibLokacije;
        opisLokacije = binding.unosOpisLokacije;
        grad = binding.unosGrad;
        ulica = binding.unosUlica;
        kucniBroj = binding.unosKucniBroj;

        errUnosIme = binding.errUnosIme;
        errUnosPrezime = binding.errUnosPrezime;
        errUnosBrojMobitela = binding.errUnosBrojMobitela;
        errUnosEmail = binding.errUnosEmail;
        errUnosKorisnickoIme = binding.errUnosKorisnickoIme;
        errUnosLozinka = binding.errUnosLozinka;
        errUnosPonovljenjaLozinka = binding.errUnosPonovljenaLozinka;
        errSpinUloga = binding.errSpinUloga;
        errUnosNazivLokacije = binding.errUnosNazivLokacije;
        errUnosOibLokacije = binding.errUnosOibLokacije;
        errUnosOpisLokacije = binding.errUnosOpisLokacije;
        errUnosGrad = binding.errUnosGrad;
        errUnosUlica = binding.errUnosUlica;
        errUnosKucniBroj = binding.errUnosKucniBroj;

        errUnosIme.setText(viewModel.getErrUnosIme());
        errUnosPrezime.setText(viewModel.getErrUnosPrezime());
        errUnosBrojMobitela.setText(viewModel.getErrUnosBrojMobitela());
        errUnosEmail.setText(viewModel.getErrUnosEmail());
        errUnosKorisnickoIme.setText(viewModel.getErrUnosKorisnickoIme());
        errUnosLozinka.setText(viewModel.getErrUnosLozinka());
        errUnosPonovljenjaLozinka.setText(viewModel.getErrUnosPonovljenjaLozinka());
        errSpinUloga.setText(viewModel.getErrSpinUloga());
        errUnosNazivLokacije.setText(viewModel.getErrUnosNazivLokacije());
        errUnosOibLokacije.setText(viewModel.getErrUnosOibLokacije());
        errUnosOpisLokacije.setText(viewModel.getErrUnosOpisLokacije());
        errUnosGrad.setText(viewModel.getErrUnosGrad());
        errUnosUlica.setText(viewModel.getErrUnosUlica());
        errUnosKucniBroj.setText(viewModel.getErrUnosKucniBroj());

        errUnosIme.setVisibility(viewModel.errUnosImeVidljivost);
        errUnosPrezime.setVisibility(viewModel.errUnosPrezimeVidljivost);
        errUnosBrojMobitela.setVisibility(viewModel.errUnosBrojMobitelaVidljivost);
        errUnosEmail.setVisibility(viewModel.errUnosEmailVidljivost);
        errUnosKorisnickoIme.setVisibility(viewModel.errUnosKorisnickoImeVidljivost);
        errUnosLozinka.setVisibility(viewModel.errUnosLozinkaVidljivost);
        errUnosPonovljenjaLozinka.setVisibility(viewModel.errUnosPonovljenjaLozinkaVidljivost);
        errSpinUloga.setVisibility(viewModel.errSpinUlogaVidljivost);
        errUnosNazivLokacije.setVisibility(viewModel.errUnosNazivLokacijeVidljivost);
        errUnosOibLokacije.setVisibility(viewModel.errUnosOibLokacijeVidljivost);
        errUnosOpisLokacije.setVisibility(viewModel.errUnosOpisLokacijeVidljivost);
        errUnosGrad.setVisibility(viewModel.errUnosGradVidljivost);
        errUnosUlica.setVisibility(viewModel.errUnosUlicaVidljivost);
        errUnosKucniBroj.setVisibility(viewModel.errUnosKucniBrojVidljivost);

        //requestQueue= Volley.newRequestQueue(getApplicationContext());

        binding.btnRegistracija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setIme(ime.getText().toString());
                viewModel.setPrezime(prezime.getText().toString());
                viewModel.setBrojMobitela(brojMobitela.getText().toString());
                viewModel.setEmail(email.getText().toString());
                viewModel.setKorisnickoIme(korisnickoIme.getText().toString());
                viewModel.setLozinka(lozinka.getText().toString());
                viewModel.setPonovljenaLozinka(ponovljenaLozinka.getText().toString());
                viewModel.setUloga(listaUloga.getSelectedItem().toString());
                viewModel.setNazivLokacije(nazivLokacije.getText().toString());
                viewModel.setOibLokacije(oibLokacije.getText().toString());
                viewModel.setOpisLokacije(opisLokacije.getText().toString());
                viewModel.setGrad(grad.getText().toString());
                viewModel.setUlica(ulica.getText().toString());
                viewModel.setKucniBroj(kucniBroj.getText().toString());
                if(viewModel.ProvijeriSvePodatke(getApplicationContext())){
                    requestQueue= Volley.newRequestQueue(getApplicationContext());
                    provjeraPostojeLiPodaci = true;
                    Map<String, String> params=new HashMap<String, String>();
                    params.put("ime_korisnika",ime.getText().toString());
                    params.put("prezime_korisnika",prezime.getText().toString());
                    params.put("email_korisnika",email.getText().toString());
                    params.put("korsnicko_ime",korisnickoIme.getText().toString());
                    params.put("lozinka",lozinka.getText().toString());
                    params.put("uloga",uloga.getSelectedItem().toString());
                    params.put("nazivLokacije",nazivLokacije.getText().toString());
                    params.put("OIBLokacije",oibLokacije.getText().toString());
                    params.put("Latituda", viewModel.getKoordinate().split(",")[0] );
                    params.put("Longituda", viewModel.getKoordinate().split(",")[1] );
                    params.put("adresaLokacije",ulica.getText().toString().concat(" ".concat(kucniBroj.getText().toString().concat(", ".concat(grad.getText().toString()))) ));
                    sendUrl="https://beervana2020.000webhostapp.com/test/email.php";
                    SlanjePodataka slanjePodataka = new SlanjePodataka(sendUrl);
                    slanjePodataka.setParametri(params);
                    slanjePodataka.sendData(getApplicationContext(),requestQueue);
                    //sendUrl="https://beervana2020.000webhostapp.com/test/korisnickoImeProvjera.php";
                    //slanjePodataka.setSendUrl(sendUrl);
                    //slanjePodataka.sendData(getApplicationContext(),requestQueue);
                    requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
                        @Override
                        public void onRequestFinished(Request<Object> request) {
                            String odgovor = slanjePodataka.getOdgovor();
                            if(request.getUrl().contains("email.php")){
                                if(odgovor.equals("E-mail already exists")){
                                    viewModel.setErrUnosEmail(odgovor);
                                    viewModel.errUnosEmailVidljivost = View.VISIBLE;
                                    errUnosEmail.setText(viewModel.getErrUnosEmail());
                                    errUnosEmail.setVisibility(viewModel.errUnosEmailVidljivost);
                                    provjeraPostojeLiPodaci=false;
                                }
                                sendUrl="https://beervana2020.000webhostapp.com/test/korisnickoImeProvjera.php";
                                slanjePodataka.setSendUrl(sendUrl);
                                slanjePodataka.sendData(getApplicationContext(),requestQueue);

                            }else if(request.getUrl().contains("korisnickoImeProvjera.php")){
                                if(odgovor.equals("Username already exists")){
                                    viewModel.setErrUnosKorisnickoIme(odgovor);
                                    viewModel.errUnosKorisnickoImeVidljivost = View.VISIBLE;
                                    errUnosKorisnickoIme.setText(viewModel.getErrUnosKorisnickoIme());
                                    errUnosKorisnickoIme.setVisibility(viewModel.errUnosKorisnickoImeVidljivost);
                                    provjeraPostojeLiPodaci=false;
                                }
                                if(viewModel.getUloga().equals("Klijent")){
                                    sendUrl="https://beervana2020.000webhostapp.com/test/LokacijaOIBProvjera.php";
                                    slanjePodataka.setSendUrl(sendUrl);
                                    slanjePodataka.sendData(getApplicationContext(),requestQueue);
                                }else{
                                    if(provjeraPostojeLiPodaci){
                                        sendUrl="https://beervana2020.000webhostapp.com/test/registracijaEmail.php";
                                        slanjePodataka.setSendUrl(sendUrl);
                                        slanjePodataka.sendData(getApplicationContext(),requestQueue);
                                    }
                                }

                            }else if(request.getUrl().contains("LokacijaOIBProvjera.php")){
                                if(odgovor.equals("There already exist a location with this OIB")){
                                    viewModel.setErrUnosOibLokacije(odgovor);
                                    viewModel.errUnosOibLokacijeVidljivost = View.VISIBLE;
                                    errUnosOibLokacije.setText(viewModel.getErrUnosOibLokacije());
                                    errUnosOibLokacije.setVisibility(viewModel.errUnosOibLokacijeVidljivost);
                                    provjeraPostojeLiPodaci=false;
                                }
                                if(provjeraPostojeLiPodaci){
                                    sendUrl="https://beervana2020.000webhostapp.com/test/registracijaEmail.php";
                                    slanjePodataka.setSendUrl(sendUrl);
                                    slanjePodataka.sendData(getApplicationContext(),requestQueue);
                                }

                            }else{
                                if(odgovor.equals("successfully registered")){
                                    openLogin();
                                }
                            }
                        }
                    });
                    //openLogin();
                }else{
                    errUnosIme.setText(viewModel.getErrUnosIme());
                    errUnosPrezime.setText(viewModel.getErrUnosPrezime());
                    errUnosBrojMobitela.setText(viewModel.getErrUnosBrojMobitela());
                    errUnosEmail.setText(viewModel.getErrUnosEmail());
                    errUnosKorisnickoIme.setText(viewModel.getErrUnosKorisnickoIme());
                    errUnosLozinka.setText(viewModel.getErrUnosLozinka());
                    errUnosPonovljenjaLozinka.setText(viewModel.getErrUnosPonovljenjaLozinka());
                    errSpinUloga.setText(viewModel.getErrSpinUloga());
                    errUnosNazivLokacije.setText(viewModel.getErrUnosNazivLokacije());
                    errUnosOibLokacije.setText(viewModel.getErrUnosOibLokacije());
                    errUnosOpisLokacije.setText(viewModel.getErrUnosOpisLokacije());
                    errUnosGrad.setText(viewModel.getErrUnosGrad());
                    errUnosUlica.setText(viewModel.getErrUnosUlica());
                    errUnosKucniBroj.setText(viewModel.getErrUnosKucniBroj());
                }
                errUnosIme.setVisibility(viewModel.errUnosImeVidljivost);
                errUnosPrezime.setVisibility(viewModel.errUnosPrezimeVidljivost);
                errUnosBrojMobitela.setVisibility(viewModel.errUnosBrojMobitelaVidljivost);
                errUnosEmail.setVisibility(viewModel.errUnosEmailVidljivost);
                errUnosKorisnickoIme.setVisibility(viewModel.errUnosKorisnickoImeVidljivost);
                errUnosLozinka.setVisibility(viewModel.errUnosLozinkaVidljivost);
                errUnosPonovljenjaLozinka.setVisibility(viewModel.errUnosPonovljenjaLozinkaVidljivost);
                errSpinUloga.setVisibility(viewModel.errSpinUlogaVidljivost);
                errUnosNazivLokacije.setVisibility(viewModel.errUnosNazivLokacijeVidljivost);
                errUnosOibLokacije.setVisibility(viewModel.errUnosOibLokacijeVidljivost);
                errUnosOpisLokacije.setVisibility(viewModel.errUnosOpisLokacijeVidljivost);
                errUnosGrad.setVisibility(viewModel.errUnosGradVidljivost);
                errUnosUlica.setVisibility(viewModel.errUnosUlicaVidljivost);
                errUnosKucniBroj.setVisibility(viewModel.errUnosKucniBrojVidljivost);
                //ProvijeriSvePodatke();
            }
        });

    }

    public void openLogin(){
        Toast toast = Toast.makeText(getApplicationContext(),"Successfully registered",Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pozicija, long id) {
        if(pozicija==2){
            nazivLokacije.setVisibility(View.VISIBLE);
            oibLokacije.setVisibility(View.VISIBLE);
            opisLokacije.setVisibility(View.VISIBLE);
            grad.setVisibility(View.VISIBLE);
            ulica.setVisibility(View.VISIBLE);
            kucniBroj.setVisibility(View.VISIBLE);
        }else{
            nazivLokacije.setVisibility(View.GONE);
            oibLokacije.setVisibility(View.GONE);
            opisLokacije.setVisibility(View.GONE);
            grad.setVisibility(View.GONE);
            ulica.setVisibility(View.GONE);
            kucniBroj.setVisibility(View.GONE);
            errSpinUloga.setVisibility(View.GONE);
            errUnosNazivLokacije.setVisibility(View.GONE);
            errUnosOibLokacije.setVisibility(View.GONE);
            errUnosOpisLokacije.setVisibility(View.GONE);
            errUnosGrad.setVisibility(View.GONE);
            errUnosUlica.setVisibility(View.GONE);
            errUnosKucniBroj.setVisibility(View.GONE);

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
