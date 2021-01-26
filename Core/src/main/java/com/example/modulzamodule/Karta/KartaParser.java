package com.example.modulzamodule;

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
                String adresa = object.getString("adresa");

                koordinate.add(latitudaLokacija);
                koordinate.add(longitudaLokacija);
                koordinate.add(adresa);

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
