package com.example.modulzamodule.Users;

import androidx.lifecycle.ViewModel;

import com.example.modulzamodule.Karta.KartaLogika;
import com.example.modulzamodule.Karta.KartaModelPodataka;

import org.json.JSONObject;

import java.util.ArrayList;

public class KartaActivityViewModel extends ViewModel {
    public ArrayList<KartaModelPodataka> podaciZaPrikaz;
    KartaLogika logikaKarte= new KartaLogika();

    public void ParsirajIPohraniPodatke(JSONObject odgovor){
        podaciZaPrikaz = logikaKarte.ParsiranjePodatakaZaKartu(odgovor);
    }

}
