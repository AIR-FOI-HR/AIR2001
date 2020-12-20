package com.example.beervana;

public class ModelPodatakaLokacijaSOcjenom {
    private Lokacija lokacija;
    private String ocjena;

    ModelPodatakaLokacijaSOcjenom(Lokacija lokacija, String ocjena){
        this.lokacija = lokacija;
        this.ocjena = ocjena;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public String getOcjena() {
        return ocjena;
    }
}
