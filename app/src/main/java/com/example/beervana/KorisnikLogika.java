package com.example.beervana;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class KorisnikLogika {
    public String ProvjeraUnosaIme(String ime){

        if(ime.length()>1 && ime.length()<46){
            if(!SlovaHrkIRazmaci(ime)){
                return "Error: Name can only contain letters and spaces.";
            }
        }else if(ime.equals("")){
            return "Error: You have to enter a name.";
        }else{
            return "Error: The name must be between 2 and 45 characters.";
        }
        return "";
    }
    public String ProvjeraUnosaPrezimena(String prezime){
        if(prezime.length()>1 && prezime.length()<46){
            if(!SlovaHrkIRazmaci(prezime)){
                return "Error: Surname can only contain letters and spaces.";
            }
        }else if (prezime.equals("")){
            return "Error: You have to enter a surname.";
        }else{
            return "Error: The surname must be between 2 and 45 characters.";
        }
        return "";
    }
    public String ProvjeraUnosaBrojaMobitela(String brojMobitela){
        if(brojMobitela.length()>9 && brojMobitela.length()<46){
            if(!SamoBrojevi(brojMobitela)){
                return "Error: Phone number can only contain numbers.";
            }
        }else if(brojMobitela.equals("")){
            return "Error: You have to enter a phone number.";
        }else{
            return "Error: The phone number must be between 10 and 45 characters.";
        }
        return "";
    }
    public String ProvjeraUnosaEmail(String email){
        if(email.length()>6 && email.length()<46){
            if(!EmailProvjera(email)){
                return "Error: Email has to be in a correct format.";
            }
        }else if (email.equals("")){
            return "Error: You have to enter an email.";
        }else{
            return "Error: The email must be between 7 and 45 characters.";
        }
        return "";
    }
    public String ProvjeraUnosaKorisnickogImena(String korisnickoIme){
        if(korisnickoIme.length()>1 && korisnickoIme.length()<26){
            if(!SlovaIBrojevi(korisnickoIme)){
                return "Error: User name can only contain letters (excluding čćšđž).";

            }
        }else if (korisnickoIme.equals("")){
            return "Error: You have to enter a user name.";
        }else{
            return "Error: The user name must be between 2 and 25 characters.";
        }
        return  "";
    }
    public String ProvjeraUnosaLozinke(String lozinka){
        if(lozinka.length()>5 && lozinka.length()<21){
            if(!SlovaIBrojevi(lozinka)){
                return "Error: password can only contain letters and numbers.";

            }
        }else if (lozinka.equals("")){
            return "Error: You have to enter a password.";
        }else{
            return "Error: The password must be between 6 and 20 characters.";
        }
        return "";
    }
    public String ProvjeraUnosaPonovljeneLozinke(String ponovljenaLozinka, String lozinka){
        if(!ponovljenaLozinka.equals("")){
            if(!ponovljenaLozinka.equals(lozinka)){
                return "Error: you have to repeat the same password";
            }
        }else{
            return "Error: You have to enter a repeated password.";
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

    public User parsiranjePodatakaKorisnika(JSONObject jsonObject) {
        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray("body");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                int id_korisnik = Integer.parseInt(object.getString("id_korisnik"));
                int id_uloga = Integer.parseInt(object.getString("id_uloga"));
                int id_clanstvo = Integer.parseInt(object.getString("id_clanstvo"));
                String id_lokacija = object.getString("id_lokacija");
                /*String ime = object.getString("ime_korisnika");
                String prezime = object.getString("prezime_korisnika");
                String adresa = object.getString("adresa_korisnika");
                String email = object.getString("email");
                String telefon = object.getString("telefon_korisnika");
                String korisnicko_ime = object.getString("korsnicko_ime");
                String slika = object.getString("slika_korisnika"); */

                User user = new User(id_korisnik, id_uloga, id_clanstvo,id_lokacija);
                return user;


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
