package com.example.modulzamodule.Beer;

import com.example.modulzamodule.Beer.Beer;

public class ModelPodatakaPivoSOcjenom {
    private Beer beer;
    private String ocjena;
    private String id_lokacije;
    private String naziv_lokacije;

    public ModelPodatakaPivoSOcjenom(Beer beer, String ocjena, String id_lokacije, String naziv_lokacije) {
        this.beer = beer;
        this.ocjena = ocjena;
        this.id_lokacije = id_lokacije;
        this.naziv_lokacije = naziv_lokacije;
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public String getOcjena() {
        return ocjena;
    }

    public void setOcjena(String ocjena) {
        this.ocjena = ocjena;
    }

    public String getId_lokacije() {
        return id_lokacije;
    }

    public void setId_lokacije(String id_lokacije) {
        this.id_lokacije = id_lokacije;
    }

    public String getNaziv_lokacije() {
        return naziv_lokacije;
    }

    public void setNaziv_lokacije(String naziv_lokacije) {
        this.naziv_lokacije = naziv_lokacije;
    }
}
