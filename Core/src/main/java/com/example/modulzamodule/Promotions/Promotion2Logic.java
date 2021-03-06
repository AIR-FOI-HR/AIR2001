package com.example.modulzamodule.Promotions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Promotion2Logic {
    public String ProvjeraUnosaNazivaPromocije(String nazivPromocije) {

        if (nazivPromocije.equals("")) {
            return "Error: you have to enter promotion name.";
        } else if (nazivPromocije.length() < 3 || nazivPromocije.length() > 45) {
            return "Error: promotion name must be between 3 and 45 characters long";
        }
        return "";
    }

    public String ProvjeraUnosOpisaPromocije(String opisPromocije) {

        if (opisPromocije.equals("")) {
            return "Error: you have to enter promotion description.";
        } else if (opisPromocije.length() < 10 || opisPromocije.length() > 45) {
            return "Error: promotion description must be between 10 and 45 characters long";
        }
        return "";
    }
    public String ProvjeraUnosaLozinke(String lozinka) {

        if (lozinka.equals("")) {
            return "Error: you have enter a password.";
        } else if (lozinka.length()<6 || lozinka.length()>30) {
            return "Error: password must be between 6 and 30 characters long";
        }
        return "";
    }
    public String ProvijeriKolicinu(String kolicina) {
        if (kolicina.equals("")) {
            return "Error: you have to enter the amount of products.";
        } else if (Integer.parseInt(kolicina) < 2 || Integer.parseInt(kolicina) > 10) {
            return "Error: the amount must be between 2 and 10.";
        }
        return "";
    }
    public String ProvijeriGratis(String gratis,String kolicina) {
        if (gratis.equals("")) {
            return "Error: you have to enter the amount of free products.";
        } else if (Integer.parseInt(gratis) > Integer.parseInt(kolicina)) {
            return "Error: the amount of free produts must be lower than amount of products";
        }
        return "";
    }

    public String ProvjeraIspravnostiPocetniZavrsnidatum(String pocetniDatum, String zavrsniDatum, String pocetnoVrijeme, String zavrsnoVrijeme) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date datumPocetak = format.parse(pocetniDatum + " " + pocetnoVrijeme);
            Date datumKraj = format.parse(zavrsniDatum + " " + zavrsnoVrijeme);
            if (datumKraj.before(datumPocetak)) {
                return "Error: end date can't be before start date";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String ProvjeraUpisaDatuma(String datum, String vrijeme) {

        if (datum.equals("   / /   ")) {
            return "Error: you have to enter a date for event.";
        } else {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            try {
                Date datumZaProvjeriti = format.parse(datum + " " + vrijeme);
                Date datumDanas = Calendar.getInstance().getTime();
                if (datumZaProvjeriti.before(datumDanas)) {
                    return "Error: end date can't be in past.";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public String ProvjeraUpisaVremena(String vrijeme) {

        if (vrijeme.equals("    :    ")) {
            return "Error: you have to enter time for event.";
        }
        return "";
    }

    public ArrayList<Promotion> parsiranjePodatakaPromotion(JSONObject odgovor) {
        JSONArray jsonArray = null;
        ArrayList<Promotion> promotionDataList = new ArrayList<>();
        try {
            jsonArray = odgovor.getJSONArray("promocija");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String id_promocija = object.getString("id_promocija");
                String naziv = object.getString("naziv");
                String opis = object.getString("opis");
                String id_lokacija = object.getString("id_lokacija");
                String datum_od = formatiratiDatum(object.getString("datum_od"));
                String datum_do = formatiratiDatum(object.getString("datum_do"));
                String tip = object.getString("tip");
                String opis_o_promociji = object.getString("opis_o_promociji");
                Promotion promotion = new Promotion(id_promocija, id_lokacija, naziv, opis, tip, opis_o_promociji, datum_od, datum_do);
                promotionDataList.add(promotion);


            }
        } catch (JSONException e) {
            promotionDataList = null;
            e.printStackTrace();
        }
        return promotionDataList;
    }

    public String formatiratiDatum(String zaFormatirati) {
        String formatiratiDatum;
        String[] poljeDatum;
        String vrijeme;
        String dan;
        String[] pomocnoPolje;
        poljeDatum = zaFormatirati.split("-");
        pomocnoPolje = poljeDatum[2].split(" ");
        dan = pomocnoPolje[0];
        pomocnoPolje = pomocnoPolje[1].split(":");
        vrijeme = pomocnoPolje[0] + ":" + pomocnoPolje[1];
        formatiratiDatum = dan + "/" + poljeDatum[1] + "/" + poljeDatum[0] + " " + vrijeme;

        return formatiratiDatum;
    }


}