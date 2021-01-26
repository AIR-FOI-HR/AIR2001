package com.example.modulzamodule;

public class Promotion {
    private String id_promocija, id_lokacija, naziv_promocije,opis_promocije, tip_promocije, opis_o_promociji, datum_od, datum_do;


    public Promotion(String id_promocija, String id_lokacija, String naziv_promocije, String opis_promocije, String tip_promocije, String opis_o_promociji, String datum_od, String datum_do) {
        this.id_promocija = id_promocija;
        this.id_lokacija = id_lokacija;
        this.naziv_promocije = naziv_promocije;
        this.opis_promocije = opis_promocije;
        this.tip_promocije = tip_promocije;
        this.opis_o_promociji = opis_o_promociji;
        this.datum_od = datum_od;
        this.datum_do = datum_do;
    }

    public String getId_promocija() {
        return id_promocija;
    }

    public void setId_promocija(String id_promocija) {
        this.id_promocija = id_promocija;
    }

    public String getId_lokacija() {
        return id_lokacija;
    }

    public void setId_lokacija(String id_lokacija) {
        this.id_lokacija = id_lokacija;
    }

    public String getNaziv_promocije() {
        return naziv_promocije;
    }

    public void setNaziv_promocije(String naziv_promocije) {
        this.naziv_promocije = naziv_promocije;
    }

    public String getOpis_promocije() {
        return opis_promocije;
    }

    public void setOpis_promocije(String opis_promocije) {
        this.opis_promocije = opis_promocije;
    }

    public String getTip_promocije() {
        return tip_promocije;
    }

    public void setTip_promocije(String tip_promocije) {
        this.tip_promocije = tip_promocije;
    }

    public String getOpis_o_promociji() {
        return opis_o_promociji;
    }

    public void setOpis_o_promociji(String opis_o_promociji) {
        this.opis_o_promociji = opis_o_promociji;
    }

    public String getDatum_od() {
        return datum_od;
    }

    public void setDatum_od(String datum_od) {
        this.datum_od = datum_od;
    }

    public String getDatum_do() {
        return datum_do;
    }

    public void setDatum_do(String datum_do) {
        this.datum_do = datum_do;
    }
}
