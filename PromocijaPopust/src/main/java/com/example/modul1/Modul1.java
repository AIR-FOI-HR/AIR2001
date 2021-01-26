package com.example.modul1;


import com.example.basemodule.BaseClassForModules;

public class Modul1 extends BaseClassForModules {
    @Override
    public String getNaslov() {
        return "Popust na pivo";
    }

    @Override
    public void setNaslov(String naslov) {

    }

    @Override
    public String getOpis() {
        return "Popust u postocima na pivo.";
    }

    @Override
    public void setOpis(String opis) {

    }

    @Override
    public void setTip(String tip) {

    }

    @Override
    public String getTip() {
        return "PopustNaPivo";
    }

    @Override
    public String AddPromos() {
        return AddModul1Promos.class.getName();
    }

    @Override
    public String UpdatePromos() {
        return AddModul1Promos.class.getName();
    }

    @Override
    public String ViewPromos() {
        return Modul1View.class.getName();
    }
}
