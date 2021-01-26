package com.example.modulzamodule;



import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Model1ViewViewModel  extends ViewModel {
    private String idKorisnik;
    private String idPromocija;
    String nazivPromocije,opisPromocije,proizvodNaziv,popust,datumOd,datumDo,idOdabranaPiva;
    Boolean iskoristeno;
    Promotion1Logic logika = new Promotion1Logic();

    public String getIdOdabranaPiva() {
        return idOdabranaPiva;
    }

    public void setIdOdabranaPiva(String idOdabranaPiva) {
        this.idOdabranaPiva = idOdabranaPiva;
    }

    public String getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(String idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public String getIdPromocija() {
        return idPromocija;
    }

    public void setIdPromocija(String idPromocija) {
        this.idPromocija = idPromocija;
    }

    public String getNazivPromocije() {
        return nazivPromocije;
    }

    public void setNazivPromocije(String nazivPromocije) {
        this.nazivPromocije = nazivPromocije;
    }

    public String getOpisPromocije() {
        return opisPromocije;
    }

    public void setOpisPromocije(String opisPromocije) {
        this.opisPromocije = opisPromocije;
    }

    public String getProizvodNaziv() {
        return proizvodNaziv;
    }

    public void setProizvodNaziv(String proizvodNaziv) {
        this.proizvodNaziv = proizvodNaziv;
    }

    public String getPopust() {
        return popust;
    }

    public void setPopust(String popust) {
        this.popust = popust;
    }

    public String getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(String datumOd) {
        this.datumOd = datumOd;
    }

    public String getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(String datumDo) {
        this.datumDo = datumDo;
    }

    public Boolean getIskoristeno() {
        return iskoristeno;
    }

    public void setIskoristeno(Boolean iskoristeno) {
        this.iskoristeno = iskoristeno;
    }
    public void PostaviModel(JSONArray promocija) throws JSONException {
        for (int i = 0; i < promocija.length(); i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = promocija.getJSONObject(i);
                nazivPromocije=jsonObject.optString("naziv");
                opisPromocije=jsonObject.getString("opis");
                datumOd = logika.formatiratiDatum(jsonObject.getString("datum_od"));
                datumDo = logika.formatiratiDatum(jsonObject.getString("datum_do"));
                String opisPromocije=jsonObject.getString("opis_o_promociji");
                JSONObject promos = new JSONObject(opisPromocije);
                String medukorak = promos.getString("Promocija");
                JSONArray popustJSON = new JSONArray(medukorak);
                JSONObject  popustObj =  popustJSON.getJSONObject(0);
                idOdabranaPiva = popustObj.getString("id_proizvod");
                popust = popustObj.getString("popust");
                String iskoristenostString = jsonObject.getString("id_korisnik");
                if(iskoristenostString.equals("null")){
                    iskoristeno = false;
                }else{
                    iskoristeno = true;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
