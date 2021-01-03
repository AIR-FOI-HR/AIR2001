package com.example.beervana;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.example.beervana.Karta.KartaModelPodataka;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class LokacijaLogika {
    public String ProvijeriUnosNazivaLokacije(String nazivLokacije){
        if(nazivLokacije.equals("")) {
            return "Error: You have to enter a location name.";
        }
        return "";
    }

    public String ProvijeritiOIBLokacije(String oibLokacije){
        if(oibLokacije.length()==11){
            if(!SamoBrojevi(oibLokacije)){
                return "Error: Location OIB can only contain numbers.";

            }
        }else if (oibLokacije.equals("")){
            return "Error: You have to enter a Location OIB.";
        }else{
            return "Error: OIB must be 11 characters long.";
        }
        return  "";
    }
    public String ProvijeritiUnosOpisaLokacije(String opisLokacije){
        if(opisLokacije.equals("")) {
            return "Error: You have to enter a location description.";
        }
        return "";
    }
    public String ProvijeritiUnosGrada(String grad){
        if(grad.length()>2 && grad.length()<31){
            if(!SlovaHrkIRazmaci(grad)){
                return "Error: City can only contain letters and spaces.";

            }
        }else if (grad.equals("")){
            return "Error: You have to enter a city.";
        }else{
            return "Error: The city must be between 3 and 30 characters.";
        }
        return "";
    }
    public String ProvijeritiUnosUlice(String ulica){
        if(ulica.length()>6 && ulica.length()<41){
            if(!SlovaHrkIRazmaci(ulica)){
                return "Error: Street can only contain letters and spaces.";
            }
        }else if (ulica.equals("")){
            return "Error: You have to enter a street.";
        }else{
            return "Error: The street must be between 7 and 40 characters.";
        }
        return "";
    }
    public String ProvijeriUnosKucniBroj(String kucniBroj){
        if(kucniBroj.length()>0 && kucniBroj.length()<6){
            if(!SlovaIBrojevi(kucniBroj)){
                return "Error: House number can only contain letters and numbers.";
            }
        }else if(kucniBroj.equals("")){
            return "Error: You have to enter a house number.";
        }else{
            return "Error: The house number must be between 1 and 5 characters.";
        }
        return "";
    }
    public String ProvijeriPostojiLiOIBlokacije(String oibLokacije){
        if(oibLokacije.equals("99999999999")){
            return "Greška: OIB lokacije već postoji.";
        }
        return "";
    }
    public String KreirajLokaciju(){
        return "";
    }
    public boolean SamoSlovaHrv(String zaProvjeriti){
        return Pattern.matches("^[a-zA-ZčČćĆžŽđĐšŠ]+$",zaProvjeriti);
    }
    public boolean SamoSlova(String zaProvijeriti){
        return Pattern.matches("^[a-zA-Z]+$",zaProvijeriti);
    }
    public boolean EmailProvjera(String zaProvijeriti){
        return Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",zaProvijeriti);
    }
    public boolean SlovaIBrojevi(String zaProvijeriti){
        return Pattern.matches("^[a-zA-Z0-9]+$",zaProvijeriti);
    }
    public boolean SamoBrojevi(String zaProvijeriti){
        return Pattern.matches("^[0-9]+$",zaProvijeriti);
    }
    public boolean SlovaHrkIRazmaci(String zaProvijeriti){
        return Pattern.matches("^([a-zA-ZčČćĆžŽđĐšŠ]+\\s)*[a-zA-ZčČćĆžŽđĐšŠ]+$",zaProvijeriti);
    }

    public String provjeraIDohvatLokacije(String strAddress, Context context){

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        String p1 = null;
        try {
            address = coder.getFromLocationName(strAddress,5);
            if (address==null ||address.size()==0) {
                return null;
            }

            Address location=address.get(0);
            p1 = Objects.toString((double) (location.getLatitude() )) +','+ Objects.toString((double)(location.getLongitude()));
            return p1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p1;
    }
    public ArrayList<ModelPodatakaLokacijaSOcjenom> ParsiranjeLokacijeZaGlavniIzbornikUser(JSONObject odgovor){
        ArrayList<ModelPodatakaLokacijaSOcjenom> podaciZaPrikaz = new ArrayList<ModelPodatakaLokacijaSOcjenom>();
        JSONArray jsonArray = null;
        try {
            jsonArray = odgovor.getJSONArray("body");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String idLokacija = object.getString("id_lokacija");
                String nazivLokacija = object.getString("naziv_lokacije");
                String ocjenaLokacije = object.optString("ocjena");
                if(ocjenaLokacije.equals("null")){
                    ocjenaLokacije= "-";
                }
                Lokacija lokacija =  new Lokacija(idLokacija,nazivLokacija,"","","","");
                ModelPodatakaLokacijaSOcjenom podatak = new ModelPodatakaLokacijaSOcjenom(lokacija,ocjenaLokacije);
                podaciZaPrikaz.add(podatak);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        if(podaciZaPrikaz.size() == 0){
            return null;
        }
        return podaciZaPrikaz;
    }

    public ArrayList<ModelPodatakaLokacijaSOcjenom> ParsiranjeNajblizeLokacijeZaKorisnika(JSONObject odgovor){
        ArrayList<ModelPodatakaLokacijaSOcjenom> podaciZaPrikaz = new ArrayList<ModelPodatakaLokacijaSOcjenom>();
        JSONArray jsonArray = null;
        try {
            jsonArray = odgovor.getJSONArray("body");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String idLokacija = object.getString("id_lokacija");
                String nazivLokacija = object.getString("naziv_lokacije");
                String ocjenaLokacije = object.optString("ocjena");
                String udaljenost = object.optString("udaljenost");
                if(ocjenaLokacije.equals("null")){
                    ocjenaLokacije= "-";
                }
                Lokacija lokacija =  new Lokacija(idLokacija,nazivLokacija,"","","","");
                ModelPodatakaLokacijaSOcjenom podatak = new ModelPodatakaLokacijaSOcjenom(lokacija,ocjenaLokacije);
                podatak.setUdaljenost(udaljenost);
                podaciZaPrikaz.add(podatak);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return podaciZaPrikaz;
    }
    public ArrayList<ModelPodatakaLokacijaSOcjenom> ParsiranjeLokacijeZaPretrazivanje(JSONObject odgovor){
        ArrayList<ModelPodatakaLokacijaSOcjenom> podaciZaPrikaz = new ArrayList<ModelPodatakaLokacijaSOcjenom>();
        JSONArray jsonArray = null;
        try {
            jsonArray = odgovor.getJSONArray("body");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String idLokacija = object.getString("id_lokacija");
                if(idLokacija=="null") break;
                String nazivLokacija = object.getString("naziv_lokacije");
                String ocjenaLokacije = object.optString("ocjena");
                String adresaLokacije = object.optString("adresa_lokacije");
                if(ocjenaLokacije.equals("null")){
                    ocjenaLokacije= "-";
                }
                Lokacija lokacija =  new Lokacija(idLokacija,nazivLokacija,"",adresaLokacije,"","");
                ModelPodatakaLokacijaSOcjenom podatak = new ModelPodatakaLokacijaSOcjenom(lokacija,ocjenaLokacije);
                podaciZaPrikaz.add(podatak);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        if(podaciZaPrikaz.size() == 0){
            return null;
        }
        return podaciZaPrikaz;
    }


}
