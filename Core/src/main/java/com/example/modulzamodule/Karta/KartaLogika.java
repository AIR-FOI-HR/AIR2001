package com.example.modulzamodule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class KartaLogika {
    public ArrayList<KartaModelPodataka> ParsiranjePodatakaZaKartu(JSONObject odgovor){
        ArrayList<KartaModelPodataka> podaciZaPrikaz = new ArrayList<KartaModelPodataka>();
        JSONArray jsonArray = null;
        try {
            jsonArray = odgovor.getJSONArray("lokacije");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                KartaModelPodataka podatak = new KartaModelPodataka();
                String idLokacija = object.getString("id_lokacije");
                String nazivLokacija = object.getString("naziv_lokacije");
                String adresaLokacija = object.getString("adresa_lokacije");
                String latitudaLokacija = object.getString("latituda");
                String longitudaLokacija = object.getString("longituda");
                String ocjenaLokacije = object.getString("ocjena");
                podatak.lokacija =  new Lokacija(idLokacija,nazivLokacija,"",adresaLokacija,latitudaLokacija,longitudaLokacija);
                podatak.setOcjena(ocjenaLokacije);
                podaciZaPrikaz.add(podatak);
            }
        } catch (JSONException e) {
            podaciZaPrikaz = null;
            e.printStackTrace();
        }
        if(podaciZaPrikaz.size() == 0){
            return null;
        }
        return podaciZaPrikaz;
    }
}
