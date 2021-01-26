package com.example.modulzamodule;

public class Event {
    private String idDogadaj,nazivDogadaj, slikaDogadaja, idKorisnika, opisDogadaja, datumOd,datumDo;

    public Event(){}

    public Event(String idDogadaj,String nazivDogadaj,String slikaDogadaja,String idKorisnika,String opisDogadaja,String datumOd, String datumDo){
        this.idDogadaj = idDogadaj;
        this.nazivDogadaj = nazivDogadaj;
        this.slikaDogadaja = slikaDogadaja;
        this. idKorisnika = idKorisnika;
        this.opisDogadaja =opisDogadaja;
        this.datumOd =datumOd;
        this.datumDo = datumDo;
    }

    public String getIdDogadaj() {
        return idDogadaj;
    }

    public void setIdDogadaj(String idDogadaj) {
        this.idDogadaj = idDogadaj;
    }

    public String getNazivDogadaj() {
        return nazivDogadaj;
    }

    public void setNazivDogadaj(String nazivDogadaj) {
        this.nazivDogadaj = nazivDogadaj;
    }

    public String getSlikaDogadaja() {
        return slikaDogadaja;
    }

    public void setSlikaDogadaja(String slikaDogadaja) {
        this.slikaDogadaja = slikaDogadaja;
    }

    public String getIdKorisnika() {
        return idKorisnika;
    }

    public void setIdKorisnika(String idKorisnika) {
        this.idKorisnika = idKorisnika;
    }

    public String getOpisDogadaja() {
        return opisDogadaja;
    }

    public void setOpisDogadaja(String opisDogadaja) {
        this.opisDogadaja = opisDogadaja;
    }

    public String getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(String datumOd) {
        this.datumOd = datumOd;
    }

    public String getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(String datumDo) {
        this.datumDo = datumDo;
    }
}
