package com.example.modulzamodule;

import android.view.View;

import java.util.List;

public class AddPromotion1ViewModel {

    private String unosImePromocije;
    private String unosOpisPromocije;
    private String prikazDatumaOd = "   / /   ";
    private String prikazVremenaOd= "    :    ";
    private String prikazDatumaDo = "   / /   ";
    private String prikazVremenaDo= "    :    ";
    private String errUnosImePromocije;
    private String errOpisaPromocije;
    private String errUnosDatumOd = "";
    private String errUnosVrijemeOd;
    private String errUnosDatumDo = "";
    private String errUnosVrijemeDo;
    private List<String> idOdabranaPiva;
    private String Json;



    int gone = View.GONE;
    int visible=View.VISIBLE;

    private int errUnosImePromocijeVisibility= gone;
    private int errOpisaPromocijeVisibility= gone;
    private int errUnosDatumOdVisibility= gone;
    private int errUnosVrijemeOdVisibility= gone;
    private int errUnosDatumDoVisibility= gone;
    private int errUnosVrijemeDoVisibility= gone;


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

    public List<String> getIdOdabranaPiva() {
        return idOdabranaPiva;
    }

    public void setIdOdabranaPiva(List<String> idOdabranaPiva) {
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

    public String FormirajDatum(String datum, String vrijeme) {
        String konacniDatum;
        String [] poljeDatum;
        poljeDatum= datum.split("/");
        konacniDatum = poljeDatum[2]+"-"+poljeDatum[1]+"-"+poljeDatum[0]+" "+vrijeme;


        return konacniDatum;
    }
}
