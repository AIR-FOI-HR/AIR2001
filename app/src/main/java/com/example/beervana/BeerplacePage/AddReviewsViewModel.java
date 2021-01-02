package com.example.beervana.BeerplacePage;

import android.view.View;

import androidx.lifecycle.ViewModel;

public class AddReviewsViewModel extends ViewModel {
    private Integer idKorisnik, idLokacija, idProizvod;
    private Double ocjena;
    private String komentar, errOcjena, errKomentar;

    int gone = View.GONE;
    int visible = View.VISIBLE;

    private int errOcjenaVisibility = gone;
    private int errKomentarVisibility = gone;

    ReviewsLogic reviewsLogic = new ReviewsLogic();


    public Integer getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(Integer idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public Integer getIdLokacija() {
        return idLokacija;
    }

    public void setIdLokacija(Integer idLokacija) {
        this.idLokacija = idLokacija;
    }

    public Double getOcjena() {
        return ocjena;
    }

    public void setOcjena(Double ocjena) {
        this.ocjena = ocjena;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public String getErrOcjena() {
        return errOcjena;
    }

    public void setErrOcjena(String errOcjena) {
        this.errOcjena = errOcjena;
    }

    public String getErrKomentar() {
        return errKomentar;
    }

    public void setErrKomentar(String errKomentar) {
        this.errKomentar = errKomentar;
    }

    public int getErrOcjenaVisibility() {
        return errOcjenaVisibility;
    }

    public void setErrOcjenaVisibility(int errOcjenaVisibility) {
        this.errOcjenaVisibility = errOcjenaVisibility;
    }

    public int getErrKomentarVisibility() {
        return errKomentarVisibility;
    }

    public void setErrKomentarVisibility(int errKomentarVisibility) {
        this.errKomentarVisibility = errKomentarVisibility;
    }

    public boolean ProvjeriPodatke(){
        boolean sveUredu = true;

        errKomentarVisibility = gone;
        errOcjenaVisibility = gone;

        errOcjena = ReviewsLogic.ProvjeraUnosaOcjene(ocjena);
        errKomentar = ReviewsLogic.ProvjeraUnosaKomentara(komentar);

        if(!errOcjena.equals("")){
            sveUredu=false;
            errKomentarVisibility = visible;
        }
        if(!errKomentar.equals("")){
            sveUredu=false;
            errOcjenaVisibility = visible;
        }


        return sveUredu;
    }

    public Integer getIdProizvod() {
        return idProizvod;
    }

    public void setIdProizvod(Integer idProizvod) {
        this.idProizvod = idProizvod;
    }
}