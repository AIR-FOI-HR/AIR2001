package com.example.modulzamodule;

import android.view.View;

import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AddPromotion1ViewModel extends ViewModel {

    private String unosImePromocije;
    private String unosOpisPromocije;
    private String prikazDatumaOd = "   / /   ";
    private String prikazVremenaOd = "    :    ";
    private String prikazDatumaDo = "   / /   ";
    private String prikazVremenaDo = "    :    ";
    private String unosPopust;
    private String errUnosImePromocije;
    private String errOpisaPromocije;
    private String errUnosDatumOd = "";
    private String errUnosVrijemeOd;
    private String errUnosDatumDo = "";
    private String errUnosVrijemeDo;
    private String errUnosProizvoda;
    private String errUnosPopusta = "";
    private String idOdabranaPiva = "";
    private String Json;
    boolean Azuriranje = false;

    private String staroUnosImePromocije,staroUnosOpisPromocije,
            staroPrikazDatumaOd,staroPrikazVremenaOd,staroPrikazDatumaDo,
            staroPrikazVremenaDo,staroUnosPopust,staroIdOdabranaPiva,idPromocija;

    int gone = View.GONE;
    int visible = View.VISIBLE;

    private int errUnosImePromocijeVisibility = gone;
    private int errOpisaPromocijeVisibility = gone;
    private int errUnosDatumOdVisibility = gone;
    private int errUnosVrijemeOdVisibility = gone;
    private int errUnosDatumDoVisibility = gone;
    private int errUnosVrijemeDoVisibility = gone;
    private int errUnosProizvodaVisibility = gone;
    private int errUnosPopustaVisibility = gone;
    Promotion1Logic logika = new Promotion1Logic();

    public String getIdPromocija() {
        return idPromocija;
    }

    public void setIdPromocija(String idPromocija) {
        this.idPromocija = idPromocija;
    }

    public boolean isAzuriranje() {
        return Azuriranje;
    }

    public void setAzuriranje(boolean azuriranje) {
        Azuriranje = azuriranje;
    }

    public String getUnosPopust() {
        return unosPopust;
    }

    public void setUnosPopust(String unosPopust) {
        this.unosPopust = unosPopust;
    }

    public String getErrUnosProizvoda() {
        return errUnosProizvoda;
    }

    public void setErrUnosProizvoda(String errUnosProizvoda) {
        this.errUnosProizvoda = errUnosProizvoda;
    }

    public String getErrUnosPopusta() {
        return errUnosPopusta;
    }

    public void setErrUnosPopusta(String errUnosPopusta) {
        this.errUnosPopusta = errUnosPopusta;
    }

    public int getErrUnosProizvodaVisibility() {
        return errUnosProizvodaVisibility;
    }

    public void setErrUnosProizvodaVisibility(int errUnosProizvodaVisibility) {
        this.errUnosProizvodaVisibility = errUnosProizvodaVisibility;
    }

    public int getErrUnosPopustaVisibility() {
        return errUnosPopustaVisibility;
    }

    public void setErrUnosPopustaVisibility(int errUnosPopustaVisibility) {
        this.errUnosPopustaVisibility = errUnosPopustaVisibility;
    }

    public String getUnosImePromocije() {
        return unosImePromocije;
    }

    public void setUnosImePromocije(String unosImePromocije) {
        this.unosImePromocije = unosImePromocije;
    }

    public String getUnosOpisPromocije() {
        return unosOpisPromocije;
    }

    public void setUnosOpisPromocije(String unosOpisPromocije) {
        this.unosOpisPromocije = unosOpisPromocije;
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

    public String getErrUnosImePromocije() {
        return errUnosImePromocije;
    }

    public void setErrUnosImePromocije(String errUnosImePromocije) {
        this.errUnosImePromocije = errUnosImePromocije;
    }

    public String getErrOpisaPromocije() {
        return errOpisaPromocije;
    }

    public void setErrOpisaPromocije(String errOpisaPromocije) {
        this.errOpisaPromocije = errOpisaPromocije;
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

    public String getIdOdabranaPiva() {
        return idOdabranaPiva;
    }

    public void setIdOdabranaPiva(String idOdabranaPiva) {
        this.idOdabranaPiva = idOdabranaPiva;
    }

    public String getJson() {
        return Json;
    }

    public void setJson(String json) {
        Json = json;
    }

    public int getGone() {
        return gone;
    }

    public void setGone(int gone) {
        this.gone = gone;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public int getErrUnosImePromocijeVisibility() {
        return errUnosImePromocijeVisibility;
    }

    public void setErrUnosImePromocijeVisibility(int errUnosImePromocijeVisibility) {
        this.errUnosImePromocijeVisibility = errUnosImePromocijeVisibility;
    }

    public int getErrOpisaPromocijeVisibility() {
        return errOpisaPromocijeVisibility;
    }

    public void setErrOpisaPromocijeVisibility(int errOpisaPromocijeVisibility) {
        this.errOpisaPromocijeVisibility = errOpisaPromocijeVisibility;
    }

    public int getErrUnosDatumOdVisibility() {
        return errUnosDatumOdVisibility;
    }

    public void setErrUnosDatumOdVisibility(int errUnosDatumOdVisibility) {
        this.errUnosDatumOdVisibility = errUnosDatumOdVisibility;
    }

    public int getErrUnosVrijemeOdVisibility() {
        return errUnosVrijemeOdVisibility;
    }

    public void setErrUnosVrijemeOdVisibility(int errUnosVrijemeOdVisibility) {
        this.errUnosVrijemeOdVisibility = errUnosVrijemeOdVisibility;
    }

    public int getErrUnosDatumDoVisibility() {
        return errUnosDatumDoVisibility;
    }

    public void setErrUnosDatumDoVisibility(int errUnosDatumDoVisibility) {
        this.errUnosDatumDoVisibility = errUnosDatumDoVisibility;
    }

    public int getErrUnosVrijemeDoVisibility() {
        return errUnosVrijemeDoVisibility;
    }

    public void setErrUnosVrijemeDoVisibility(int errUnosVrijemeDoVisibility) {
        this.errUnosVrijemeDoVisibility = errUnosVrijemeDoVisibility;
    }

    public boolean ProvijeriPodatke() {
        boolean sveUredu = true;
        errUnosImePromocijeVisibility = gone;
        errOpisaPromocijeVisibility = gone;
        errUnosDatumOdVisibility = gone;
        errUnosVrijemeOdVisibility = gone;
        errUnosDatumDoVisibility = gone;
        errUnosVrijemeDoVisibility = gone;
        errUnosProizvodaVisibility = gone;
        errUnosPopustaVisibility = gone;

        errUnosImePromocije = logika.ProvjeraUnosaNazivaPromocije(unosImePromocije);
        errOpisaPromocije = logika.ProvjeraUnosOpisaPromocije(unosOpisPromocije);
        errUnosPopusta = logika.ProvijeriPopust(unosPopust);
        errUnosVrijemeOd = logika.ProvjeraUpisaVremena(prikazVremenaOd);
        errUnosVrijemeDo = logika.ProvjeraUpisaVremena(prikazVremenaDo);
        if(!errUnosImePromocije.equals("")){
            sveUredu=false;
            errUnosImePromocijeVisibility = visible;
        }
        if(!errOpisaPromocije.equals("")){
            sveUredu=false;
            errOpisaPromocijeVisibility = visible;
        }

        if(!errUnosVrijemeDo.equals("")){
            sveUredu=false;
            errUnosVrijemeDoVisibility = visible;
        }else{
            errUnosDatumDo  = logika.ProvjeraUpisaDatuma(prikazDatumaDo,prikazVremenaDo);
        }
        if(!errUnosVrijemeOd.equals("")){
            sveUredu=false;
            errUnosVrijemeOdVisibility = visible;
        }else{
            errUnosDatumOd  = logika.ProvjeraUpisaDatuma(prikazDatumaOd,prikazVremenaOd);
        }
        if(!errUnosDatumOd.equals("")){
            sveUredu=false;
            errUnosDatumOdVisibility = visible;
        }
        if(!errUnosDatumDo.equals("")){
            sveUredu=false;
            errUnosDatumDoVisibility = visible;
        }
        if(errUnosDatumOd.equals("") && errUnosVrijemeOd.equals("") &&
                errUnosDatumDo.equals("") && errUnosVrijemeDo.equals("")){
            errUnosDatumDo=
                    logika.ProvjeraIspravnostiPocetniZavrsnidatum
                            (prikazDatumaOd,prikazDatumaDo,prikazVremenaOd,prikazVremenaDo);
        }
        if(idOdabranaPiva.equals("")){
            errUnosProizvoda = "Error: you have to select a product.";
            errUnosProizvodaVisibility = visible;
            sveUredu = false;
        }
        if(!errUnosPopusta.equals("")){
            errUnosPopustaVisibility = visible;
            sveUredu = false;
        }
        return sveUredu;
    }

    public String FormirajDatum(String datum, String vrijeme) {
        String konacniDatum;
        String[] poljeDatum;
        poljeDatum = datum.split("/");
        konacniDatum = poljeDatum[2] + "-" + poljeDatum[1] + "-" + poljeDatum[0] + " " + vrijeme;


        return konacniDatum;
    }
    public String KreirajJSONZaSlati(){
        JSONObject promocija = new JSONObject();
        try {
            promocija.put("id_proizvod", idOdabranaPiva);
            promocija.put("popust", unosPopust);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(promocija);

        JSONObject promocijaObj = new JSONObject();
        try {
            promocijaObj.put("Promocija", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return promocijaObj.toString();
    }
    public void PostaviModel(JSONArray promocija) throws JSONException {
        for (int i = 0; i < promocija.length(); i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = promocija.getJSONObject(i);
                unosImePromocije=jsonObject.optString("naziv");
                staroUnosImePromocije = unosImePromocije;
                unosOpisPromocije=jsonObject.getString("opis");
                staroUnosOpisPromocije = unosOpisPromocije;
                prikazDatumaOd = logika.formatiratiDatum(jsonObject.getString("datum_od")).split(" ")[0];
                staroPrikazDatumaOd = prikazDatumaOd;
                prikazVremenaOd = logika.formatiratiDatum(jsonObject.getString("datum_od")).split(" ")[1];
                staroPrikazVremenaOd = prikazVremenaOd;
                prikazDatumaDo = logika.formatiratiDatum(jsonObject.getString("datum_do")).split(" ")[0];
                staroPrikazDatumaDo = prikazDatumaDo;
                prikazVremenaDo = logika.formatiratiDatum(jsonObject.getString("datum_do")).split(" ")[1];
                staroPrikazVremenaDo = prikazVremenaDo;
                String opisPromocije=jsonObject.getString("opis_o_promociji");
                JSONObject promos = new JSONObject(opisPromocije);
                String medukorak = promos.getString("Promocija");
                JSONArray popust = new JSONArray(medukorak);
                JSONObject  popustObj =  popust.getJSONObject(0);
                idOdabranaPiva = popustObj.getString("id_proizvod");
                staroIdOdabranaPiva = idOdabranaPiva;
                unosPopust = popustObj.getString("popust");
                staroUnosPopust = unosPopust;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public Boolean DosloDoPromijene(){
        if(!staroUnosImePromocije.equals(unosImePromocije)){
            return true;
        }
        if(!staroUnosOpisPromocije.equals(unosOpisPromocije)){
            return true;
        }
        if(!staroIdOdabranaPiva.equals(idOdabranaPiva)){
            return true;
        }
        if(!staroUnosPopust.equals((unosPopust))){
            return true;
        }
        if(!staroPrikazDatumaOd.equals(prikazDatumaOd)){
            return true;
        }
        if(!staroPrikazDatumaDo.equals(prikazDatumaDo)){
            return true;
        }
        if(!staroPrikazVremenaOd.equals(prikazVremenaOd)){
            return true;
        }
        if(!staroPrikazVremenaDo.equals(prikazVremenaDo)){
            return true;
        }
        return false;
    }
}
