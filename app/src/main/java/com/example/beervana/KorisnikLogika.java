package com.example.beervana;

import java.util.regex.Pattern;

public class KorisnikLogika {
    public String ProvjeraUnosaIme(String ime){

        if(ime.length()>1 && ime.length()<46){
            if(!SlovaHrkIRazmaci(ime)){
                return "Greška: Ime mora sadržavati samo slova i razmake.";
            }
        }else if(ime.equals("")){
            return "Greška: Morate unijeti ime.";
        }else{
            return "Greška: Korisnički ime mora biti duljine od 2 do 45 znakova.";
        }
        return "";
    }
    public String ProvjeraUnosaPrezimena(String prezime){
        if(prezime.length()>1 && prezime.length()<46){
            if(!SlovaHrkIRazmaci(prezime)){
                return "Greška: Prezime mora sadržavati samo slova i razmake.";
            }
        }else if (prezime.equals("")){
            return "Greška: Morate unijeti prezime.";
        }else{
            return "Greška: prezime mora biti duljine od 2 do 45 znakova.";
        }
        return "";
    }
    public String ProvjeraUnosaBrojaMobitela(String brojMobitela){
        if(brojMobitela.length()>9 && brojMobitela.length()<46){
            if(!SamoBrojevi(brojMobitela)){
                return "Greška: Broj mobitela mora sadržavati samo brojeve.";
            }
        }else if(brojMobitela.equals("")){
            return "Greška: Morate unijeti broj mobitela.";
        }else{
            return "Greška: Broj mobitela mora biti duljine od 10 do 45 znakova.";
        }
        return "";
    }
    public String ProvjeraUnosaEmail(String email){
        if(email.length()>6 && email.length()<46){
            if(!EmailProvjera(email)){
                return "Greška: Email mora biti u pravilnom formatu.";
            }
        }else if (email.equals("")){
            return "Greška: Morate unijeti Email.";
        }else{
            return "Greška: Email mora biti duljine od 7 do 45 znakova.";
        }
        return "";
    }
    public String ProvjeraUnosaKorisnickogImena(String korisnickoIme){
        if(korisnickoIme.length()>1 && korisnickoIme.length()<26){
            if(!SlovaIBrojevi(korisnickoIme)){
                return "Greška: Korisničko ime mora sadržavati samo slova (bez čćžšđ) i brojeve.";

            }
        }else if (korisnickoIme.equals("")){
            return "Greška: Morate unijeti korisničko ime.";
        }else{
            return "Greška: Korisničko ime mora biti duljine od 2 do 25 znakova.";
        }
        return  "";
    }
    public String ProvjeraUnosaLozinke(String lozinka){
        if(lozinka.length()>5 && lozinka.length()<21){
            if(!SlovaIBrojevi(lozinka)){
                return "Greška: Lozinka smije sadržavati samo slova i brojeve.";

            }
        }else if (lozinka.equals("")){
            return "Greška: Morate unijeti lozinku.";
        }else{
            return "Greška: Lozinka mora biti duljine od 6 do 20 znakova";
        }
        return "";
    }
    public String ProvjeraUnosaPonovljeneLozinke(String ponovljenaLozinka, String lozinka){
        if(!ponovljenaLozinka.equals("")){
            if(!ponovljenaLozinka.equals(lozinka)){
                return "Greška: Morate ponoviti istu lozinku.";
            }
        }else{
            return "Greška: Morate unijeti ponovljenu lozinku.";
        }
        return "";
    }
    public String ProvijeriPostojiLiKorisnickoIme(String korisnickoIme){
        if(korisnickoIme.equals("default")){
            return "Greška: korisničko ime je zauzeto.";
        }


        return "";
    }
    public String ProvijeriPostojiLiEmail(String email){
        if(email.equals("default@default.com")){
            return "Greška: Korisnički račun s ovim emailom već postoji.";
        }
        return"";
    }
    public String KreirajKorisnika(){
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
