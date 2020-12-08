package com.example.beervana.Karta;

import androidx.lifecycle.ViewModel;

import org.json.JSONObject;

import java.util.ArrayList;

public class KartaActivityViewModel extends ViewModel {
    ArrayList<KartaModelPodataka> podaciZaPrikaz;
    KartaLogika logikaKarte= new KartaLogika();

    public void ParsirajIPohraniPodatke(JSONObject odgovor){
        podaciZaPrikaz = logikaKarte.ParsiranjePodatakaZaKartu(odgovor);
    }

}
