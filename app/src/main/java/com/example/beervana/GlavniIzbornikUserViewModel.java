package com.example.beervana;

import androidx.lifecycle.ViewModel;

import com.example.beervana.BeerMenu.Beer;
import com.example.beervana.BeerplacePage.Review;
import com.example.beervana.BeerplacePage.ReviewsLogic;

import org.json.JSONObject;

import java.util.ArrayList;

public class GlavniIzbornikUserViewModel extends ViewModel {
    private  ModelPodatakaLokacijaSOcjenom lokacijaNajnovija = null;
    private ModelPodatakaLokacijaSOcjenom lokacijaMjeseca = null;
    private ArrayList<ModelPodatakaLokacijaSOcjenom> lokacijeUBlizini = null;
    private ArrayList<ModelPodatakaLokacijaSOcjenom> lokacijeMoje = null;
    private ArrayList<ModelPodatakaLokacijaSOcjenom> odgovorParsiranja;
    private ArrayList<Beer> piveMoje = null;
    private ArrayList<Review> recenzijeMoje = null;
    private ArrayList<ModelPodatakaLokacijaSOcjenom> najdrazeLokacije = null;
    private LokacijaLogika lokacijaLogika = new LokacijaLogika();
    private ReviewsLogic RecenzijaLogika = new ReviewsLogic();
    private ArrayList<Review> odgovorParsiranjaRecenzija;



    public void ParsiranjeLokacijeZaGlavniIzbornikUser(JSONObject odgovor, int broj){
        odgovorParsiranja = lokacijaLogika.ParsiranjeLokacijeZaGlavniIzbornikUser(odgovor);
        if (odgovorParsiranja != null) {
            switch (broj) {
                case (1):
                    lokacijaNajnovija = odgovorParsiranja.get(0);
                    break;
                case (2):
                    lokacijaMjeseca = odgovorParsiranja.get(0);
                    break;
            }
        }
    }

    public void ParsiranjeLokacijeZaGlavniIzbornikUserZaLokacijeUBlizini(JSONObject odgovor){
        odgovorParsiranja = lokacijaLogika.ParsiranjeNajblizeLokacijeZaKorisnika(odgovor);
        if(odgovorParsiranja!=null){
            lokacijeUBlizini = odgovorParsiranja;
        }
    }

    public void ParsiranjeRecenzijeZaGlavniIzbornikUser(JSONObject odgovor){
        odgovorParsiranjaRecenzija = RecenzijaLogika.parsiranjePodatakaReviewaZaGlavniIzbornikUser(odgovor);
        if(odgovorParsiranjaRecenzija!= null){
            recenzijeMoje = odgovorParsiranjaRecenzija;
        }
    }

    public void ParsiranjeNajdrazihLokacija(JSONObject odgovor){
        odgovorParsiranja = lokacijaLogika.ParsiranjeLokacijeZaPretrazivanje(odgovor);
        if(odgovorParsiranja!= null){
            najdrazeLokacije = odgovorParsiranja;
        }
    }
    public ArrayList<ModelPodatakaLokacijaSOcjenom> getNajdrazeLokacije() {
        return najdrazeLokacije;
    }

    public ModelPodatakaLokacijaSOcjenom getLokacijaNajnovija() {
        return lokacijaNajnovija;
    }

    public void setLokacijaNajnovija(ModelPodatakaLokacijaSOcjenom lokacijaNajnovija) {
        this.lokacijaNajnovija = lokacijaNajnovija;
    }

    public ModelPodatakaLokacijaSOcjenom getLokacijaMjeseca() {
        return lokacijaMjeseca;
    }

    public void setLokacijaMjeseca(ModelPodatakaLokacijaSOcjenom lokacijaMjeseca) {
        this.lokacijaMjeseca = lokacijaMjeseca;
    }

    public ArrayList<ModelPodatakaLokacijaSOcjenom> getLokacijeUBlizini() {
        return lokacijeUBlizini;
    }

    public void setLokacijeUBlizini(ArrayList<ModelPodatakaLokacijaSOcjenom> lokacijeUBlizini) {
        this.lokacijeUBlizini = lokacijeUBlizini;
    }

    public ArrayList<ModelPodatakaLokacijaSOcjenom> getLokacijeMoje() {
        return lokacijeMoje;
    }

    public void setLokacijeMoje(ArrayList<ModelPodatakaLokacijaSOcjenom> lokacijeMoje) {
        this.lokacijeMoje = lokacijeMoje;
    }

    public ArrayList<Beer> getPiveMoje() {
        return piveMoje;
    }

    public void setPiveMoje(ArrayList<Beer> piveMoje) {
        this.piveMoje = piveMoje;
    }

    public ArrayList<Review> getRecenzijeMoje() {
        return recenzijeMoje;
    }

    public void setRecenzijeMoje(ArrayList<Review> recenzijeMoje) {
        this.recenzijeMoje = recenzijeMoje;
    }
    public void setNajdrazeLokacije(ArrayList<ModelPodatakaLokacijaSOcjenom> najdrazeLokacije) {
        this.najdrazeLokacije = najdrazeLokacije;
    }
}