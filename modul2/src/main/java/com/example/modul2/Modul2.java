package com.example.modul2;

import com.example.basemodule.BaseClassForModules;

public class Modul2 extends BaseClassForModules {
    @Override
    public String getNaslov() {
        return "Free beer";
    }

    @Override
    public void setNaslov(String naslov) {

    }

    @Override
    public String getOpis() {
        return "Promotion where user has to buy an amount of beers to get one or more free.";
    }

    @Override
    public void setOpis(String opis) {

    }

    @Override
    public void setTip(String tip) {

    }

    @Override
    public String getTip() {
        return "GratisPivo";
    }

    @Override
    public String AddPromos() {
        return AddModul2Promos.class.getName();
    }

    @Override
    public String UpdatePromos() {
        return AddModul2Promos.class.getName();
    }

    @Override
    public String ViewPromos() {
        return "";
    }
}
