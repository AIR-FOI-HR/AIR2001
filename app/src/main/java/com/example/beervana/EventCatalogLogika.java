package com.example.beervana;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class EventCatalogLogika {
    public ArrayList<ModelPodatakEventCatalog> parsiranjePodatakaEventData(JSONObject odgovor) {
        JSONArray jsonArray = null;
        ArrayList<ModelPodatakEventCatalog> eventDataList = new ArrayList<ModelPodatakEventCatalog>();
        try {
            jsonArray = odgovor.getJSONArray("dogadaj");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String id_dogadaj = object.getString("id_dogadaj");
                String naziv_dogadaja = object.getString("naziv_dogadaja");
                String opis_dogadaja = object.getString("opis_dogadaja");
                String vizual_dogadaja = object.getString("vizual_dogadaja");
                String datum_od = formatiratiDatum(object.getString("datum_od"));
                String datum_do = formatiratiDatum(object.getString("datum_do"));
                String id_korisnik = object.getString("id_korisnik");
                String lokacija_id = object.getString("lokacija_id");
                String naziv_lokacije = object.getString("naziv_lokacije");
                String adresa_lokacije = object.getString("adresa_lokacije");
                Event event = new Event(id_dogadaj,naziv_dogadaja,vizual_dogadaja,id_korisnik,opis_dogadaja,datum_od,datum_do);
                ModelPodatakEventCatalog eventData= new ModelPodatakEventCatalog(event,naziv_lokacije,lokacija_id, adresa_lokacije);
                eventDataList.add(eventData);


            }
        } catch (JSONException e) {
            eventDataList = null;
            e.printStackTrace();
        }

        return eventDataList;

    }

    public  String formatiratiDatum(String zaFormatirati){
        String formatiratiDatum;
        String [] poljeDatum;
        String  vrijeme;
        String dan;
        String [] pomocnoPolje;
        poljeDatum= zaFormatirati.split("-");
        pomocnoPolje = poljeDatum[2].split(" ");
        dan = pomocnoPolje[0];
        pomocnoPolje = pomocnoPolje[1].split(":");
        vrijeme = pomocnoPolje[0]+":"+pomocnoPolje[1];
        formatiratiDatum = dan + "/"+poljeDatum[1]+"/"+poljeDatum[0]+" "+vrijeme;

        return formatiratiDatum;
    }
}
