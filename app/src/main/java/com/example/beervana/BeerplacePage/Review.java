package com.example.beervana.BeerplacePage;

public class Review {

    private String id_recenzija, id_proizvod, id_korisnik, id_lokacija, ocjena, komentar, datum_i_vrijeme_recenzije;

    public Review(String id_recenzija, String id_proizvod, String id_korisnik, String id_lokacija, String ocjena, String komentar, String datum_i_vrijeme_recenzije) {
        this.id_recenzija = id_recenzija;
        this.id_proizvod = id_proizvod;
        this.id_korisnik = id_korisnik;
        this.id_lokacija = id_lokacija;
        this.ocjena = ocjena;
        this.komentar = komentar;
        this.datum_i_vrijeme_recenzije = datum_i_vrijeme_recenzije;
    }

    public String getId_recenzija() {
        return id_recenzija;
    }

    public void setId_recenzija(String id_recenzija) {
        this.id_recenzija = id_recenzija;
    }

    public String getId_proizvod() {
        return id_proizvod;
    }

    public void setId_proizvod(String id_proizvod) {
        this.id_proizvod = id_proizvod;
    }

    public String getId_korisnik() {
        return id_korisnik;
    }

    public void setId_korisnik(String id_korisnik) {
        this.id_korisnik = id_korisnik;
    }

    public String getId_lokacija() {
        return id_lokacija;
    }

    public void setId_lokacija(String id_lokacija) {
        this.id_lokacija = id_lokacija;
    }

    public String getOcjena() {
        return ocjena;
    }

    public void setOcjena(String ocjena) {
        this.ocjena = ocjena;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public String getDatum_i_vrijeme_recenzije() {
        return datum_i_vrijeme_recenzije;
    }

    public void setDatum_i_vrijeme_recenzije(String datum_i_vrijeme_recenzije) {
        this.datum_i_vrijeme_recenzije = datum_i_vrijeme_recenzije;
    }
}
