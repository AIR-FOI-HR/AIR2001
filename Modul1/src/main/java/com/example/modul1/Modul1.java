package com.example.modul1;


import com.example.basemodule.BaseClassForModules;

public class Modul1 extends BaseClassForModules {
    @Override
    public String getNaslov() {
        return "mod1";
    }

    @Override
    public void setNaslov(String naslov) {

    }

    @Override
    public String getOpis() {
        return "opis1";
    }

    @Override
    public void setOpis(String opis) {

    }

    @Override
    public String AddPromos() {
        return AddModul1Promos.class.getName();
    }

    @Override
    public void UpdatePromos() {

    }

    @Override
    public void ViewPromos() {

    }
}
