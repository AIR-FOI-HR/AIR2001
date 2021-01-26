package com.example.modulzamodule.Users;

public class User {
    private int id_korisnik, id_uloga, id_clanstvo ;
    private String ime,prezime, adresa, email, telefon, korisnicko_ime,slika_korisnika,id_lokacija;

    public User(int id_korisnik,int id_uloga, int id_clanstvo, String id_lokacija){
        this.id_korisnik = id_korisnik;
        this.id_uloga = id_uloga;
        this.id_clanstvo = id_clanstvo;
        this.id_lokacija = id_lokacija;
    }

    public User(int id_korisnik, int id_uloga, int id_clanstvo, String ime, String prezime, String adresa, String email, String telefon, String korisnicko_ime, String slika_korisnika) {
        this.id_korisnik = id_korisnik;
        this.id_uloga = id_uloga;
        this.id_clanstvo = id_clanstvo;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.email = email;
        this.telefon = telefon;
        this.korisnicko_ime = korisnicko_ime;
        this.slika_korisnika = slika_korisnika;
    }

    public String getId_lokacija() {
        return id_lokacija;
    }

    public void setId_lokacija(String id_lokacija) {
        this.id_lokacija = id_lokacija;
    }

    public int getId_korisnik() {
        return id_korisnik;
    }

    public void setId_korisnik(int id_korisnik) {
        this.id_korisnik = id_korisnik;
    }

    public int getId_uloga() {
        return id_uloga;
    }

    public void setId_uloga(int id_uloga) {
        this.id_uloga = id_uloga;
    }

    public int getId_clanstvo() {
        return id_clanstvo;
    }

    public void setId_clanstvo(int id_clanstvo) {
        this.id_clanstvo = id_clanstvo;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getKorisnicko_ime() {
        return korisnicko_ime;
    }

    public void setKorisnicko_ime(String korisnicko_ime) {
        this.korisnicko_ime = korisnicko_ime;
    }

    public String getSlika_korisnika() {
        return slika_korisnika;
    }

    public void setSlika_korisnika(String slika_korisnika) {
        this.slika_korisnika = slika_korisnika;
    }
}

