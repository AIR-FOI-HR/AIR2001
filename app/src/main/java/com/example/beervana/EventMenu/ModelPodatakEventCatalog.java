package com.example.beervana.EventMenu;

import com.example.beervana.EventMenu.Event;

public class ModelPodatakEventCatalog {
    Event dogadaj;
    private String nazivLokacije;
    private String idLokacije;
    private String adresaLokacije;
    public ModelPodatakEventCatalog(){}

    public ModelPodatakEventCatalog(Event dogadaj, String nazivLokacije,String idLokacije, String adresaLokacije){
        this.dogadaj = dogadaj;
        this.nazivLokacije = nazivLokacije;
        this.idLokacije = idLokacije;
        this.adresaLokacije = adresaLokacije;

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

    public String getAdresaLokacije() {
        return adresaLokacije;
    }

    public void setAdresaLokacije(String adresaLokacije) {
        this.adresaLokacije = adresaLokacije;
    }
}
