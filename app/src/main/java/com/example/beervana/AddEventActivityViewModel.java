package com.example.beervana;

import android.net.Uri;
import android.view.View;

import androidx.lifecycle.ViewModel;


public class AddEventActivityViewModel extends ViewModel {
    private Uri slika;
    private String slikaZaSlanje = "";
    private String unosImedogadjaja;
    private String unosOpisaDogadaja;
    private String prikazDatumaOd = "   / /   ";
    private String prikazVremenaOd= "    :    ";
    private String prikazDatumaDo = "   / /   ";
    private String prikazVremenaDo= "    :    ";
    private String errSlika;
    private String errUnosImeDogadjaja;
    private String errOpisaDogadjaja;
    private String errUnosDatumOd = "";
    private String errUnosVrijemeOd;
    private String errUnosDatumDo = "";
    private String errUnosVrijemeDo;

    int gone = View.GONE;
    int visible=View.VISIBLE;

    private int errSlikaVisibility = gone;
    private int errUnosImeDogadjajaVisibility= gone;
    private int errOpisaDogadjajaVisibility= gone;
    private int errUnosDatumOdVisibility= gone;
    private int errUnosVrijemeOdVisibility= gone;
    private int errUnosDatumDoVisibility= gone;
    private int errUnosVrijemeDoVisibility= gone;

    DogadajLogika logikaDogadjaj = new DogadajLogika();


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

    public String getPrikazDatumaOd() {
        return prikazDatumaOd;
    }

    public void setPrikazDatumaOd(String prikazDatumaOd) {
        this.prikazDatumaOd = prikazDatumaOd;
    }

    public String getPrikazVremenaOd() {
        return prikazVremenaOd;
    }

    public void setPrikazVremenaOd(String prikazVremenaOd) {
        this.prikazVremenaOd = prikazVremenaOd;
    }

    public String getPrikazDatumaDo() {
        return prikazDatumaDo;
    }

    public void setPrikazDatumaDo(String prikazDatumaDo) {
        this.prikazDatumaDo = prikazDatumaDo;
    }

    public String getPrikazVremenaDo() {
        return prikazVremenaDo;
    }

    public void setPrikazVremenaDo(String prikazVremenaDo) {
        this.prikazVremenaDo = prikazVremenaDo;
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

    public String getErrUnosDatumOd() {
        return errUnosDatumOd;
    }

    public void setErrUnosDatumOd(String errUnosDatumOd) {
        this.errUnosDatumOd = errUnosDatumOd;
    }

    public String getErrUnosVrijemeOd() {
        return errUnosVrijemeOd;
    }

    public void setErrUnosVrijemeOd(String errUnosVrijemeOd) {
        this.errUnosVrijemeOd = errUnosVrijemeOd;
    }

    public String getErrUnosDatumDo() {
        return errUnosDatumDo;
    }

    public void setErrUnosDatumDo(String errUnosDatumDo) {
        this.errUnosDatumDo = errUnosDatumDo;
    }

    public String getErrUnosVrijemeDo() {
        return errUnosVrijemeDo;
    }

    public void setErrUnosVrijemeDo(String errUnosVrijemeDo) {
        this.errUnosVrijemeDo = errUnosVrijemeDo;
    }

    public int getErrSlikaVisibility() {
        return errSlikaVisibility;
    }

    public int getErrUnosImeDogadjajaVisibility() {
        return errUnosImeDogadjajaVisibility;
    }

    public int getErrOpisaDogadjajaVisibility() {
        return errOpisaDogadjajaVisibility;
    }

    public int getErrUnosDatumOdVisibility() {
        return errUnosDatumOdVisibility;
    }

    public int getErrUnosVrijemeOdVisibility() {
        return errUnosVrijemeOdVisibility;
    }

    public int getErrUnosDatumDoVisibility() {
        return errUnosDatumDoVisibility;
    }

    public int getErrUnosVrijemeDoVisibility() {
        return errUnosVrijemeDoVisibility;
    }

    public boolean ProvijeriSvePodatke(){
        boolean sveUredu = true;
        errSlikaVisibility = gone;
        errUnosImeDogadjajaVisibility = gone;
        errOpisaDogadjajaVisibility = gone;
        errUnosDatumOdVisibility = gone;
        errUnosVrijemeOdVisibility = gone;
        errUnosDatumDoVisibility = gone;
        errUnosVrijemeDoVisibility = gone;

        errSlika = logikaDogadjaj.ProvjeraUnosaSlike(slikaZaSlanje);
        errUnosImeDogadjaja = logikaDogadjaj.ProvjeraUnosaNazivaDogadjaja(unosImedogadjaja);
        errOpisaDogadjaja = logikaDogadjaj.ProvjeraUnosaOpisaDogadjaja(unosOpisaDogadaja);
        errUnosVrijemeOd = logikaDogadjaj.ProvjeraUpisaVremena(prikazVremenaOd);
        errUnosVrijemeDo = logikaDogadjaj.ProvjeraUpisaVremena(prikazVremenaDo);

        if(!errSlika.equals("")){
              sveUredu=false;
              errSlikaVisibility = visible;
        }
        if(!errUnosImeDogadjaja.equals("")){
            sveUredu=false;
            errUnosImeDogadjajaVisibility = visible;
        }
        if(!errOpisaDogadjaja.equals("")){
            sveUredu=false;
            errOpisaDogadjajaVisibility = visible;
        }
        if(!errUnosVrijemeOd.equals("")){
            sveUredu=false;
            errUnosVrijemeOdVisibility = visible;
        }else{
            errUnosDatumOd  = logikaDogadjaj.ProvjeraUpisaDatuma(prikazDatumaOd,prikazVremenaOd);
        }
        if(!errUnosDatumOd.equals("")){
            sveUredu=false;
            errUnosDatumOdVisibility = visible;
        }

        if(!errUnosVrijemeDo.equals("")){
            sveUredu=false;
            errUnosVrijemeDoVisibility = visible;
        }else{
            errUnosDatumDo  = logikaDogadjaj.ProvjeraUpisaDatuma(prikazDatumaDo,prikazVremenaDo);
        }
        if(errUnosDatumOd.equals("") && errUnosVrijemeOd.equals("") &&
                errUnosDatumDo.equals("") && errUnosVrijemeDo.equals("")){
            errUnosDatumDo=
                    logikaDogadjaj.ProvjeraIspravnostiPocetniZavrsnidatum
                            (prikazDatumaOd,prikazDatumaDo,prikazVremenaOd,prikazVremenaDo);
        }
        if(!errUnosDatumDo.equals("")){
            sveUredu=false;
            errUnosDatumDoVisibility = visible;
        }

        return sveUredu;
    }

    public String FormirajDatum(String datum,String vrijeme) {
        String konacniDatum;
        String [] poljeDatum;
        poljeDatum= datum.split("/");
        konacniDatum = poljeDatum[2]+"-"+poljeDatum[1]+"-"+poljeDatum[0]+" "+vrijeme;


        return konacniDatum;
    }
}
