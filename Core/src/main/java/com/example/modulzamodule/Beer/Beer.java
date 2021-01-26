package com.example.modulzamodule.Beer;

public class Beer {
    private String id_proizvod,id_kategorija,naziv_proizvoda,cijena_proizvoda, okus,litara,slika;

    public Beer() {
    }

    public Beer(String naziv_proizvoda, String cijena_proizvoda, String okus)
    {
        this.naziv_proizvoda = naziv_proizvoda;
        this.cijena_proizvoda = cijena_proizvoda;
        this.okus = okus;
    }

    public Beer(String id_proizvod, String id_kategorija, String naziv_proizvoda, String cijena_proizvoda, String okus, String litara, String slika) {
        this.id_proizvod = id_proizvod;
        this.id_kategorija = id_kategorija;
        this.naziv_proizvoda = naziv_proizvoda;
        this.cijena_proizvoda = cijena_proizvoda;
        this.okus = okus;
        this.litara = litara;
        this.slika = slika;
    }

    public String getId_proizvod() {
        return id_proizvod;
    }

    public void setId_proizvod(String id_proizvod) {
        this.id_proizvod = id_proizvod;
    }

    public String getId_kategorija() {
        return id_kategorija;
    }

    public void setId_kategorija(String id_kategorija) {
        this.id_kategorija = id_kategorija;
    }

    public String getNaziv_proizvoda() {
        return naziv_proizvoda;
    }

    public void setNaziv_proizvoda(String naziv_proizvoda) {
        this.naziv_proizvoda = naziv_proizvoda;
    }

    public String getCijena_proizvoda() {
        return cijena_proizvoda;
    }

    public void setCijena_proizvoda(String cijena_proizvoda) {
        this.cijena_proizvoda = cijena_proizvoda;
    }

    public String getOkus() {
        return okus;
    }

    public void setOkus(String okus) {
        this.okus = okus;
    }

    public String getLitara() {
        return litara;
    }

    public void setLitara(String litara) {
        this.litara = litara;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }
}
