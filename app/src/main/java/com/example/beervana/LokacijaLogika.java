package com.example.beervana;

import java.util.regex.Pattern;

public class LokacijaLogika {
    public String ProvijeriUnosNazivaLokacije(String nazivLokacije){
        if(nazivLokacije.equals("")) {
            return "Greška: Morate unijeti naziv lokacije.";
        }
        return "";
    }

    public String ProvijeritiOIBLokacije(String oibLokacije){
        if(oibLokacije.length()==11){
            if(!SamoBrojevi(oibLokacije)){
                return "Greška: OIB lokacije smije sadržavati samo brojeve.";

            }
        }else if (oibLokacije.equals("")){
            return "Greška: Morate unijeti OIB lokacije.";
        }else{
            return "Greška: OIB mora biti duljine 11 znakova.";
        }
        return  "";
    }
    public String ProvijeritiUnosOpisaLokacije(String opisLokacije){
        if(opisLokacije.equals("")) {
            return "Greška: Morate unijeti opis lokacije.";
        }
        return "";
    }
    public String ProvijeritiUnosGrada(String grad){
        if(grad.length()>2 && grad.length()<31){
            if(!SlovaHrkIRazmaci(grad)){
                return "Greška: Grad smije sadržavati samo slova i razmake";

            }
        }else if (grad.equals("")){
            return "Greška: Morate unijeti grad.";
        }else{
            return "Greška: Grad mora biti duljine od 3 do 30 znakova";
        }
        return "";
    }
    public String ProvijeritiUnosUlice(String ulica){
        if(ulica.length()>6 && ulica.length()<41){
            if(!SlovaHrkIRazmaci(ulica)){
                return "Greška: Ulica smije zadržavati samo slova i razmake";
            }
        }else if (ulica.equals("")){
            return "Greška: Morate unijeti ulicu.";
        }else{
            return "Greška: Ulica mora biti duljine od 7 do 40 znakova";
        }
        return "";
    }
    public String ProvijeriUnosKucniBroj(String kucniBroj){
        if(kucniBroj.length()>0 && kucniBroj.length()<6){
            if(!SlovaIBrojevi(kucniBroj)){
                return "Greška: Kućni broj smije sadržavati samo slova i brojeve";
            }
        }else if(kucniBroj.equals("")){
            return "Greška: Morate unijeti kućni broj.";
        }else{
            return "Greška: Kućni broj mora biti duljine od 1 do 5 znakova";
        }
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
