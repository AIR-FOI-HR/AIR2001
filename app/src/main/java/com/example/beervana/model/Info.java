package com.example.beervana.model;

public class Info {

    private Integer id_proizvod;
    private String naziv_piva;
    private String okus;
    private String litara;
    private String slika;

    public Info(){

    }

    public Info(Integer id_proizvod, String naziv_piva, String okus, String litara, String slika) {
        this.id_proizvod = id_proizvod;
        this.naziv_piva = naziv_piva;
        this.okus = okus;
        this.litara = litara;
        this.slika = slika;
    }

    public Integer getId_proizvod() {
        return id_proizvod;
    }

    public String getNaziv_piva() {
        return naziv_piva;
    }

    public String getOkus() {
        return okus;
    }

    public String getLitara() {
        return litara;
    }

    public String getSlika() {
        return slika;
    }

    public void setId_proizvod(Integer id_proizvod) {
        this.id_proizvod = id_proizvod;
    }

    public void setNaziv_piva(String naziv_piva) {
        this.naziv_piva = naziv_piva;
    }

    public void setOkus(String okus) {
        this.okus = okus;
    }

    public void setLitara(String litara) {
        this.litara = litara;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }
}
