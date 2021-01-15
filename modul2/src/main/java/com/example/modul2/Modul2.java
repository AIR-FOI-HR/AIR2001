package com.example.modul2;

import com.example.basemodule.BaseClassForModules;

public class Modul2 extends BaseClassForModules {
    @Override
    public String getNaslov() {
        return "mod2";
    }

    @Override
    public void setNaslov(String naslov) {

    }

    @Override
    public String getOpis() {
        return "opis2";
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
