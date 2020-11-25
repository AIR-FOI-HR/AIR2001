package com.example.beervana;

public class ModelPodatakEventCatalog {
    Event dogadaj;
    private String nazivLokacije;
    private String idLokacije;
    public ModelPodatakEventCatalog(){}

    public ModelPodatakEventCatalog(Event dogadaj, String nazivLokacije,String idLokacije){
        this.dogadaj = dogadaj;
        this.nazivLokacije = nazivLokacije;
        this.idLokacije = idLokacije;
    }


    public String getNazivLokacije() {
        return nazivLokacije;
    }

    public void setNazivLokacije(String nazivLokacije) {
        this.nazivLokacije = nazivLokacije;
    }

    public String getIdLokacije() {
        return idLokacije;
    }

    public void setIdLokacije(String idLokacije) {
        this.idLokacije = idLokacije;
    }
}
