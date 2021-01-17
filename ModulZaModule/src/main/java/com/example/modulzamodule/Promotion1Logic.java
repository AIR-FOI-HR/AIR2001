package com.example.modulzamodule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Promotion1Logic {
    public String ProvjeraUnosaNazivaPromocije(String nazivPromocije){

        if(nazivPromocije.equals("")){
            return "Error: you have to enter promotion name.";
        }else if(nazivPromocije.length()<3 || nazivPromocije.length()>45){
            return "Error: promotion name must be between 3 and 45 characters long";
        }
        return "";
    }
    public String ProvjeraUnosOpisaPromocije(String opisPromocije){

        if(opisPromocije.equals("")){
            return "Error: you have to enter promotion description.";
        }else if(opisPromocije.length()<10 || opisPromocije.length()>45){
            return "Error: promotion description must be between 10 and 45 characters long";
        }
        return "";
    }
    public String ProvijeriPopust(String popust){
        if(popust.equals("")){
            return  "Error: you have to enter a discount.";
        }else if(Integer.parseInt(popust)<0 && Integer.parseInt(popust)>100){
            return "Error: the discount must be between 1 and 99 percent.";
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

    public String ProvjeraUpisaVremena(String vrijeme){

        if(vrijeme.equals("    :    ")){
            return "Error: you have to enter time for event.";
        }
        return "";
    }

}