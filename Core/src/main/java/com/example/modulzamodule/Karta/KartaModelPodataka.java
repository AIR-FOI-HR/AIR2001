package com.example.modulzamodule.Karta;

import com.example.modulzamodule.Lokacija.Lokacija;

public class KartaModelPodataka {
    public Lokacija lokacija;
    private String ocjena;
    private String idMarker;

    public String getOcjena() {
        return ocjena;
    }

    public void setOcjena(String ocjena) {
        this.ocjena = ocjena;
    }

    public String getIdMarker() {
        return idMarker;
    }

    public void setIdMarker(String idMarker) {
        this.idMarker = idMarker;
    }
}
