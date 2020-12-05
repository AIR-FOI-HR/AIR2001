package com.example.beervana.BeerMenu;

import android.net.Uri;
import android.view.View;

import androidx.lifecycle.ViewModel;

public class AddBeersViewModel extends ViewModel {
    private Uri slika;
    private String slikaZaSlanje = "";
    private String nazivPiva;
    private double cijenaPiva;
    private String okusPiva;
    private double litraPiva;
    private String errSlika;
    private String errNaziv;
    private String errCijena;
    private String errOkus;
    private String errLitra;

    int gone = View.GONE;
    int visible = View.VISIBLE;

    private int errSlikaVisibility = gone;
    private int errUnosNazivaPivaVisibility= gone;
    private int errUnosCijenePivaVisibility = gone;
    private int errUnosokusaPivaVisibility = gone;
    private int errUnosLitreVisibility = gone;

    BeerLogic beerLogic = new BeerLogic();

    public String getErrSlika() {
        return errSlika;
    }

    public void setErrSlika(String errSlika) {
        this.errSlika = errSlika;
    }

    public String getErrNaziv() {
        return errNaziv;
    }

    public void setErrNaziv(String errNaziv) {
        this.errNaziv = errNaziv;
    }

    public String getErrCijena() {
        return errCijena;
    }

    public void setErrCijena(String errCijena) {
        this.errCijena = errCijena;
    }

    public String getErrOkus() {
        return errOkus;
    }

    public void setErrOkus(String errOkus) {
        this.errOkus = errOkus;
    }

    public String getErrLitra() {
        return errLitra;
    }

    public void setErrLitra(String errLitra) {
        this.errLitra = errLitra;
    }


    public Uri getSlika() {
        return slika;
    }

    public void setSlika(Uri slika) {
        this.slika = slika;
    }

    public String getSlikaZaSlanje() {
        return slikaZaSlanje;
    }

    public void setSlikaZaSlanje(String slikaZaSlanje) {
        this.slikaZaSlanje = slikaZaSlanje;
    }

    public String getNazivPiva() {
        return nazivPiva;
    }

    public void setNazivPiva(String nazivPiva) {
        this.nazivPiva = nazivPiva;
    }

    public double getCijenaPiva() {
        return cijenaPiva;
    }

    public void setCijenaPiva(double cijenaPiva) {
        this.cijenaPiva = cijenaPiva;
    }

    public String getOkusPiva() {
        return okusPiva;
    }

    public void setOkusPiva(String okusPiva) {
        this.okusPiva = okusPiva;
    }

    public double getLitraPiva() {
        return litraPiva;
    }

    public void setLitraPiva(double litraPiva) {
        this.litraPiva = litraPiva;
    }

    public int getErrSlikaVisibility() {
        return errSlikaVisibility;
    }

    public void setErrSlikaVisibility(int errSlikaVisibility) {
        this.errSlikaVisibility = errSlikaVisibility;
    }

    public int getErrUnosNazivaPivaVisibility() {
        return errUnosNazivaPivaVisibility;
    }

    public void setErrUnosNazivaPivaVisibility(int errUnosNazivaPiva) {
        this.errUnosNazivaPivaVisibility = errUnosNazivaPiva;
    }

    public int getErrUnosCijenePivaVisibility() {
        return errUnosCijenePivaVisibility;
    }

    public void setErrUnosCijenePivaVisibility(int errUnosCijenePiva) {
        this.errUnosCijenePivaVisibility = errUnosCijenePiva;
    }

    public int getErrUnosokusaPivaVisibility() {
        return errUnosokusaPivaVisibility;
    }

    public void setErrUnosokusaPivaVisibility(int errUnosokusaPiva) {
        this.errUnosokusaPivaVisibility = errUnosokusaPiva;
    }

    public int getErrUnosLitreVisibility() {
        return errUnosLitreVisibility;
    }

    public void setErrUnosLitreVisibility(int errUnosLitre) {
        this.errUnosLitreVisibility = errUnosLitre;
    }

    public boolean ProvjeriPodatke(){
        boolean sveUredu = true;
        errSlikaVisibility = gone;
        errUnosNazivaPivaVisibility= gone;
        errUnosCijenePivaVisibility = gone;
        errUnosokusaPivaVisibility = gone;
        errUnosLitreVisibility = gone;

        errSlika = beerLogic.ProvjeraUnosaSlike(slikaZaSlanje);
        errCijena = beerLogic.ProvjeraUnosaCijenePiva(cijenaPiva);
        errLitra = beerLogic.ProvjeraUnosaLitara(litraPiva);
        errNaziv = beerLogic.ProvjeraUnosaNazivaPiva(nazivPiva);
        errOkus = beerLogic.ProvjeraUnosaOkusa(okusPiva);

        if(!errSlika.equals("")){
            sveUredu=false;
            errSlikaVisibility = visible;
        }
        if(!errCijena.equals("")){
            sveUredu=false;
            errUnosCijenePivaVisibility = visible;
        }
        if(!errOkus.equals("")){
            sveUredu=false;
            errUnosokusaPivaVisibility = visible;
        }
        if(!errSlika.equals("")){
            sveUredu=false;
            errSlikaVisibility = visible;
        }
        if(!errNaziv.equals("")){
            sveUredu=false;
            errUnosNazivaPivaVisibility = visible;
        }
        if(!errLitra.equals("")){
            sveUredu=false;
            errUnosLitreVisibility = visible;
        }

        return sveUredu;
    }
}