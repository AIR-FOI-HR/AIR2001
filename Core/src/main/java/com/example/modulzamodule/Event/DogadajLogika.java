package com.example.modulzamodule.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DogadajLogika {

    public String ProvjeraUnosaSlike(String slika){
        if(slika.equals("")){
            return "Error: you must choose an image";
        }

        return "";
    }

    public String ProvjeraUnosaNazivaDogadjaja(String nazivDogadjaja){

        if(nazivDogadjaja.equals("")){
            return "Error: you have to enter event name.";
        }else if(nazivDogadjaja.length()<3 || nazivDogadjaja.length()>45){
            return "Error: event name must be between 3 and 45 characters long";
        }
        return "";
    }
    public String ProvjeraUnosaOpisaDogadjaja(String opisDogadjaja){

        if(opisDogadjaja.equals("")){
            return "Error: you have to enter event description.";
        }else if(opisDogadjaja.length()<3 || opisDogadjaja.length()>100){
            return "Error: event description must be between 3 and 100 characters long";
        }
        return "";
    }
    public String ProvjeraUpisaDatuma(String datum, String vrijeme){

        if(datum.equals("   / /   ")){
            return "Error: you have to enter a date for event.";
        }else{
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            try {
                Date datumZaProvjeriti = format.parse(datum+" "+vrijeme);
                Date datumDanas = Calendar.getInstance().getTime();
                if(datumZaProvjeriti.before(datumDanas)){
                    return "Error: end date can't be in past.";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
    public String ProvjeraIspravnostiPocetniZavrsnidatum(String pocetniDatum, String zavrsniDatum, String pocetnoVrijeme, String zavrsnoVrijeme){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date datumPocetak = format.parse(pocetniDatum+" "+pocetnoVrijeme);
            Date datumKraj = format.parse(zavrsniDatum+ " "+zavrsnoVrijeme);
            if(datumKraj.before(datumPocetak)){
                return "Error: end date can't be before start date";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }
    public String ProvjeraIspravnostiDatuma(String datum,  String vrijeme){


        return "";
    }

    public String ProvjeraUpisaVremena(String vrijeme){

        if(vrijeme.equals("    :    ")){
            return "Error: you have to enter time for event.";
        }
        return "";
    }
}
