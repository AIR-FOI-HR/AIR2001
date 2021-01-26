package com.example.modulzamodule;

public class Lokacija {
    private String id_lokacija, nazivLokacija,oibLokacija,adresaLokacija,latitudaLokacija, longitudaLokacija;

    public Lokacija(){}
    public Lokacija(String id_lokacija,String nazivLokacija, String oibLokacija, String adresaLokacija, String latitudaLokacija, String longitudaLokacija){
        this.id_lokacija = id_lokacija;
        this.nazivLokacija = nazivLokacija;
        this.oibLokacija = oibLokacija;
        this.adresaLokacija = adresaLokacija;
        this.latitudaLokacija = latitudaLokacija;
        this.longitudaLokacija = longitudaLokacija;
    }

    public String getId_lokacija() {
        return id_lokacija;
    }

    public void setId_lokacija(String id_lokacija) {
        this.id_lokacija = id_lokacija;
    }

    public String getNazivLokacija() {
        return nazivLokacija;
    }

    public void setNazivLokacija(String nazivLokacija) {
        this.nazivLokacija = nazivLokacija;
    }

    public String getOibLokacija() {
        return oibLokacija;
    }

    public void setOibLokacija(String oibLokacija) {
        this.oibLokacija = oibLokacija;
    }

    public String getAdresaLokacija() {
        return adresaLokacija;
    }

    public void setAdresaLokacija(String adresaLokacija) {
        this.adresaLokacija = adresaLokacija;
    }

    public String getLatitudaLokacija() {
        return latitudaLokacija;
    }

    public void setLatitudaLokacija(String latitudaLokacija) {
        this.latitudaLokacija = latitudaLokacija;
    }

    public String getLongitudaLokacija() {
        return longitudaLokacija;
    }

    public void setLongitudaLokacija(String longitudaLokacija) {
        this.longitudaLokacija = longitudaLokacija;
    }
}
