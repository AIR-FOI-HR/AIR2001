package com.example.beervana.BeerplacePage;

import com.example.beervana.Karta.KartaModelPodataka;
import com.example.beervana.Lokacija;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class KartaParser {
    public ArrayList<String> ParsiranjePodatakaZaKartu(JSONObject odgovor){
        ArrayList<String> koordinate = new ArrayList<>();
        JSONArray jsonArray = null;
        try {
            jsonArray = odgovor.getJSONArray("koordinate");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                String latitudaLokacija = object.getString("latituda");
                String longitudaLokacija = object.getString("longituda");

                koordinate.add(latitudaLokacija);
                koordinate.add(longitudaLokacija);

            }
        } catch (JSONException e) {
            koordinate = null;
            e.printStackTrace();
        }
        if(koordinate.size() == 0){
            return null;
        }
        return koordinate;
    }
}
