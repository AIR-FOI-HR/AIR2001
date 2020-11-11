package com.example.beervana;

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
}
