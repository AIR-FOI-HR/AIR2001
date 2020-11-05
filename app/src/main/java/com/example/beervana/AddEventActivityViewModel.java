package com.example.beervana;

import android.net.Uri;
import android.view.View;


public class AddEventActivityViewModel {
    private Uri slika;
    private String unosImedogadjaja;
    private String unosOpisaDogadaja;
    private String prikazDatuma;
    private String prikazVremena;
    private String errSlika;
    private String errUnosImeDogadjaja;
    private String errOpisaDogadjaja;
    private String errUnosDatum;
    private String errUnosVrijeme;

    private int errSlikaVisibility;
    private int errUnosImeDogadjajaVisibility;
    private int errOpisaDogadjajaVisibility;
    private int errUnosDatumVisibility;
    private int errUnosVrijemeVisibility;

    int gone = View.GONE;
    int visible=View.VISIBLE;

    public Uri getSlika() {
        return slika;
    }

    public void setSlika(Uri slika) {
        this.slika = slika;
    }

    public String getUnosImedogadjaja() {
        return unosImedogadjaja;
    }

    public void setUnosImedogadjaja(String unosImedogadjaja) {
        this.unosImedogadjaja = unosImedogadjaja;
    }

    public String getUnosOpisaDogadaja() {
        return unosOpisaDogadaja;
    }

    public void setUnosOpisaDogadaja(String unosOpisaDogadaja) {
        this.unosOpisaDogadaja = unosOpisaDogadaja;
    }

    public String getPrikazDatuma() {
        return prikazDatuma;
    }

    public void setPrikazDatuma(String prikazDatuma) {
        this.prikazDatuma = prikazDatuma;
    }

    public String getPrikazVremena() {
        return prikazVremena;
    }

    public void setPrikazVremena(String prikazVremena) {
        this.prikazVremena = prikazVremena;
    }

    public String getErrSlika() {
        return errSlika;
    }

    public void setErrSlika(String errSlika) {
        this.errSlika = errSlika;
    }

    public String getErrUnosImeDogadjaja() {
        return errUnosImeDogadjaja;
    }

    public void setErrUnosImeDogadjaja(String errUnosImeDogadjaja) {
        this.errUnosImeDogadjaja = errUnosImeDogadjaja;
    }

    public String getErrOpisaDogadjaja() {
        return errOpisaDogadjaja;
    }

    public void setErrOpisaDogadjaja(String errOpisaDogadjaja) {
        this.errOpisaDogadjaja = errOpisaDogadjaja;
    }

    public String getErrUnosDatum() {
        return errUnosDatum;
    }

    public void setErrUnosDatum(String errUnosDatum) {
        this.errUnosDatum = errUnosDatum;
    }

    public String getErrUnosVrijeme() {
        return errUnosVrijeme;
    }

    public void setErrUnosVrijeme(String errUnosVrijeme) {
        this.errUnosVrijeme = errUnosVrijeme;
    }



}
