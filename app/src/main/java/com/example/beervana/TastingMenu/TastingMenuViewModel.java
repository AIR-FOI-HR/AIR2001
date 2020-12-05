package com.example.beervana.TastingMenu;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class TastingMenuViewModel extends ViewModel {

    private String slikaZaSlanje="";
    private Uri slika;
    private String menuName;
    private String menuDuration;
    private ArrayList<String> beers;
    private String menuDescription;
    private String location;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public String getSlikaZaSlanje() {
        return slikaZaSlanje;
    }

    public void setSlikaZaSlanje(String slikaZaSlanje) {
        this.slikaZaSlanje = slikaZaSlanje;
    }

    public Uri getSlika() {
        return slika;
    }

    public void setSlika(Uri slika) {
        this.slika = slika;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDuration() {
        return menuDuration;
    }

    public void setMenuDuration(String menuDuration) {
        this.menuDuration = menuDuration;
    }

    public ArrayList<String> getBeers() {
        return beers;
    }

    public void setBeers(ArrayList<String> beers) {
        this.beers = beers;
    }

    public boolean ProvjeriSvePodatke()
    {
        if(DegustacijskiMeniLogika.menuDuration(this.menuDuration)
            && DegustacijskiMeniLogika.menuName(this.menuName)
            && DegustacijskiMeniLogika.checkBeer(this.beers)
        )
                return true;
        return false;
    }

}
