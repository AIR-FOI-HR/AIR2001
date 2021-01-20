package com.example.basemodule;

public abstract class BaseClassForModules{
    private String naslov = null;
    private String opis = null;
    private String tip = null;

    public abstract String getNaslov();

    public abstract void setNaslov(String naslov);

    public abstract String getOpis();

    public abstract void setOpis(String opis);

    public abstract void setTip(String tip);

    public abstract String getTip();

    public abstract String AddPromos();
    public abstract String UpdatePromos();
    public abstract String ViewPromos();

}
