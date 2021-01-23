package com.example.modulzamodule;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.ViewModel;

public class RegisterActivityViewModel extends ViewModel {
    private String  ime;
    private String  prezime;
    private String  brojMobitela;
    private String  email;
    private String  korisnickoIme;
    private String  lozinka;
    private String  ponovljenaLozinka;
    private String  uloga;
    private String  nazivLokacije;
    private String  oibLokacije;
    private String  opisLokacije;
    private String  grad;
    private String  ulica;
    private String  kucniBroj;
    private String  koordinate;

    String errUnosIme ;
    String errUnosPrezime ;
    String errUnosBrojMobitela ;
    String errUnosEmail ;
    String errUnosKorisnickoIme ;
    String errUnosLozinka ;
    String errUnosPonovljenjaLozinka ;
    String errSpinUloga ;
    String errUnosNazivLokacije;
    String errUnosOibLokacije;
    String errUnosOpisLokacije ;
    String errUnosGrad ;
    String errUnosUlica ;
    String errUnosKucniBroj;

    int gone = View.GONE;
    int visible=View.VISIBLE;

    public int errUnosImeVidljivost = gone ;
    public int errUnosPrezimeVidljivost =gone;
    public int errUnosBrojMobitelaVidljivost =gone;
    public int errUnosEmailVidljivost =gone;
    public int errUnosKorisnickoImeVidljivost =gone;
    public int errUnosLozinkaVidljivost =gone;
    public int errUnosPonovljenjaLozinkaVidljivost =gone;
    public int errSpinUlogaVidljivost =gone;
    public int errUnosNazivLokacijeVidljivost =gone;
    public int errUnosOibLokacijeVidljivost = gone;
    public int errUnosOpisLokacijeVidljivost =gone;
    public int errUnosGradVidljivost =gone;
    public int errUnosUlicaVidljivost =gone;
    public int errUnosKucniBrojVidljivost =gone;

    KorisnikLogika logikaKorisnika = new KorisnikLogika();
    LokacijaLogika logikaLokacija = new LokacijaLogika();

    public String getKoordinate() {
        return koordinate;
    }

    public void setKoordinate(String koordinate) {
        this.koordinate = koordinate;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrojMobitela() {
        return brojMobitela;
    }

    public void setBrojMobitela(String brojMobitela) {
        this.brojMobitela = brojMobitela;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getPonovljenaLozinka() {
        return ponovljenaLozinka;
    }

    public void setPonovljenaLozinka(String ponovljenaLozinka) {
        this.ponovljenaLozinka = ponovljenaLozinka;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    public String getNazivLokacije() {
        return nazivLokacije;
    }

    public void setNazivLokacije(String nazivLokacije) {
        this.nazivLokacije = nazivLokacije;
    }

    public String getOibLokacije() {
        return oibLokacije;
    }

    public void setOibLokacije(String oibLokacije) {
        this.oibLokacije = oibLokacije;
    }

    public String getOpisLokacije() {
        return opisLokacije;
    }

    public void setOpisLokacije(String opisLokacije) {
        this.opisLokacije = opisLokacije;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getKucniBroj() {
        return kucniBroj;
    }

    public void setKucniBroj(String kucniBroj) {
        this.kucniBroj = kucniBroj;
    }

    public void setErrUnosEmail(String errUnosEmail) {
        this.errUnosEmail = errUnosEmail;
    }

    public void setErrUnosKorisnickoIme(String errUnosKorisnickoIme) {
        this.errUnosKorisnickoIme = errUnosKorisnickoIme;
    }

    public void setErrUnosOibLokacije(String errUnosOibLokacije) {
        this.errUnosOibLokacije = errUnosOibLokacije;
    }

    public String getErrUnosIme() {
        return errUnosIme;
    }

    public String getErrUnosPrezime() {
        return errUnosPrezime;
    }

    public String getErrUnosBrojMobitela() {
        return errUnosBrojMobitela;
    }

    public String getErrUnosEmail() {
        return errUnosEmail;
    }

    public String getErrUnosKorisnickoIme() {
        return errUnosKorisnickoIme;
    }

    public String getErrUnosLozinka() {
        return errUnosLozinka;
    }

    public String getErrUnosPonovljenjaLozinka() {
        return errUnosPonovljenjaLozinka;
    }

    public String getErrSpinUloga() {
        return errSpinUloga;
    }

    public String getErrUnosNazivLokacije() {
        return errUnosNazivLokacije;
    }

    public String getErrUnosOibLokacije() {
        return errUnosOibLokacije;
    }

    public String getErrUnosOpisLokacije() {
        return errUnosOpisLokacije;
    }

    public String getErrUnosGrad() {
        return errUnosGrad;
    }

    public String getErrUnosUlica() {
        return errUnosUlica;
    }

    public String getErrUnosKucniBroj() {
        return errUnosKucniBroj;
    }

    public Boolean ProvijeriSvePodatke(Context context){
        boolean sveUredu =true;

        errUnosIme=logikaKorisnika.ProvjeraUnosaIme(ime);
        errUnosPrezime=logikaKorisnika.ProvjeraUnosaPrezimena(prezime);
        errUnosBrojMobitela=logikaKorisnika.ProvjeraUnosaBrojaMobitela(brojMobitela);
        errUnosEmail=logikaKorisnika.ProvjeraUnosaEmail(email);
        errUnosKorisnickoIme=logikaKorisnika.ProvjeraUnosaKorisnickogImena(korisnickoIme);
        errUnosLozinka=logikaKorisnika.ProvjeraUnosaLozinke(lozinka);
        errUnosPonovljenjaLozinka=logikaKorisnika.ProvjeraUnosaPonovljeneLozinke(ponovljenaLozinka,lozinka);

        errSpinUloga = "";
        errSpinUlogaVidljivost = gone;
        errUnosNazivLokacijeVidljivost = gone;
        errUnosOibLokacijeVidljivost = gone;
        errUnosOpisLokacijeVidljivost = gone;
        errUnosGradVidljivost = gone;
        errUnosUlicaVidljivost = gone;
        errUnosKucniBrojVidljivost = gone;

        if(uloga.equals("Odaberite Ulogu")){
            errSpinUloga="Greška: Trebate odabrati ulogu.";
        }else if(uloga.equals("Klijent")){
            errUnosNazivLokacije=logikaLokacija.ProvijeriUnosNazivaLokacije(nazivLokacije);
            errUnosOibLokacije = logikaLokacija.ProvijeritiOIBLokacije(oibLokacije);
            errUnosOpisLokacije=logikaLokacija.ProvijeritiUnosOpisaLokacije(opisLokacije);
            errUnosGrad=logikaLokacija.ProvijeritiUnosGrada(grad);
            errUnosUlica=logikaLokacija.ProvijeritiUnosUlice(ulica);
            errUnosKucniBroj=logikaLokacija.ProvijeriUnosKucniBroj(kucniBroj);
            if(!errUnosNazivLokacije.equals("")) {
                sveUredu=false;
                errUnosNazivLokacijeVidljivost = visible;
            }
            if(!errUnosOibLokacije.equals("")) {
                sveUredu=false;
                errUnosOibLokacijeVidljivost = visible;
            }
            if(!errUnosOpisLokacije.equals("")) {
                sveUredu=false;
                errUnosOpisLokacijeVidljivost = visible;
            }
            if(!errUnosGrad.equals("")) {
                sveUredu=false;
                errUnosGradVidljivost = visible;
            }
            if(!errUnosUlica.equals("")) {
                sveUredu=false;
                errUnosUlicaVidljivost = visible;
            }
            if(!errUnosKucniBroj.equals("")) {
                sveUredu=false;
                errUnosKucniBrojVidljivost = visible;
            }
        }
        if(!errUnosIme.equals("")) {
            sveUredu=false;
            errUnosImeVidljivost = visible;
        }else{
            errUnosImeVidljivost = gone;
        }
        if(!errUnosPrezime.equals("")) {
            sveUredu=false;
            errUnosPrezimeVidljivost = visible;
        }else{
            errUnosPrezimeVidljivost = gone;
        }
        if(!errUnosBrojMobitela.equals("")) {
            sveUredu=false;
            errUnosBrojMobitelaVidljivost = visible;
        }else{
            errUnosBrojMobitelaVidljivost = gone;
        }
        if(!errUnosEmail.equals("")) {
            sveUredu=false;
            errUnosEmailVidljivost = visible;
        }else{
            errUnosEmailVidljivost = gone;
        }
        if(!errUnosKorisnickoIme.equals("")) {
            sveUredu=false;
            errUnosKorisnickoImeVidljivost = visible;
        }else{
            errUnosKorisnickoImeVidljivost = gone;
        }
        if(!errUnosLozinka.equals("")) {
            sveUredu=false;
            errUnosLozinkaVidljivost = visible;
        }else{
            errUnosLozinkaVidljivost = gone;
        }
        if(!errUnosPonovljenjaLozinka.equals("")) {
            sveUredu=false;
            errUnosPonovljenjaLozinkaVidljivost = visible;
        }else{
            errUnosPonovljenjaLozinkaVidljivost = gone;
        }
        if(!errSpinUloga.equals("")) {
            sveUredu=false;
            errSpinUlogaVidljivost = visible;
        }
        if(errUnosGrad.equals("") && errUnosUlica.equals("") && errUnosKucniBroj.equals("")){
            koordinate = logikaLokacija.provjeraIDohvatLokacije(ulica.concat(" ".concat(kucniBroj.concat(", ".concat(grad)))),context);
            if(koordinate==null){
                sveUredu=false;
                errUnosGrad="Error: Check your address, we couldn't find the coordinates.";
                errUnosGradVidljivost=visible;
            }
        }

        return sveUredu;
    }
}
