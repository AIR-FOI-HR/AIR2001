package com.example.beervana.BeerplacePage.Modularnost;

import androidx.fragment.app.Fragment;

import com.example.beervana.BaseActivity;

public abstract class BaseClassForModules{
    private String naslov = null;
    private String opis = null;

    public abstract String getNaslov();

    public abstract void setNaslov(String naslov);

    public abstract String getOpis();

    public abstract void setOpis(String opis);

    public abstract String AddPromos();
    public abstract void UpdatePromos();
    public abstract void ViewPromos();

}
