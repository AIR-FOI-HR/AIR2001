package com.example.modulzamodule;

import android.view.View;

import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private String korisnickoIme;
    private String lozinka;

    String errorKorisnickoIme;
    String errorLozinka;

    int gone = View.GONE;
    int visible=View.VISIBLE;

    public int errorKorisnickoImeVidljivost= gone;
    public int errorLozinkaVidljivost = gone;

    KorisnikLogika logikaKorisnika = new KorisnikLogika();
    LokacijaLogika logikaLokacija = new LokacijaLogika();

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

    public String getErrorKorisnickoIme() {
        return errorKorisnickoIme;
    }

    public void setErrorKorisnickoIme(String errorKorisnickoIme) {
        this.errorKorisnickoIme = errorKorisnickoIme;
    }

    public String getErrorLozinka() {
        return errorLozinka;
    }

    public void setErrorLozinka(String errorLozinka) {
        this.errorLozinka = errorLozinka;
    }

    public Boolean ProvjeriPodatke(){
        boolean sveUredu =true;

        errorKorisnickoIme = logikaKorisnika.ProvjeraUnosaKorisnickogImena(korisnickoIme);
        errorLozinka = logikaKorisnika.ProvjeraUnosaLozinke(lozinka);

        if(!errorKorisnickoIme.equals("")) {
            sveUredu=false;
            errorKorisnickoImeVidljivost = visible;
        }else{
            errorKorisnickoIme=logikaKorisnika.ProvijeriPostojiLiKorisnickoIme(korisnickoIme);
            if(!errorKorisnickoIme.equals("")){
                sveUredu=false;
                errorKorisnickoImeVidljivost= visible;
            }else{
                errorKorisnickoImeVidljivost = gone;
            }

        }

        if(!errorLozinka.equals("")) {
            sveUredu=false;
            errorLozinkaVidljivost = visible;
        }else{
            errorLozinkaVidljivost = gone;
        }
        return sveUredu;
    }


}
