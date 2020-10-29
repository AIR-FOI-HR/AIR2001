package com.example.beervana;

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


import com.example.beervana.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
                if(viewModel.ProvijeriSvePodatke()){
                    Toast toast = Toast.makeText(getApplicationContext(),"Uspješna Registracija",Toast.LENGTH_LONG);
                    toast.show();
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
