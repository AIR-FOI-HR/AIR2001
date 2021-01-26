package com.example.beervana.Statistika;

import androidx.lifecycle.ViewModel;

import com.example.modulzamodule.Reviews.Review;
import com.jjoe64.graphview.series.DataPoint;

import org.json.JSONObject;

import java.util.ArrayList;

public class StatistikaViewModel extends ViewModel {
    DataPoint[] podaci;
    ArrayList<DataPoint> podaciArray = new ArrayList<>();
    StatistikaLogika logika = new StatistikaLogika();
    ArrayList<Review> reviewLocation = new ArrayList<>();
    ArrayList<Review> reviewBeer = new ArrayList<>();

    public ArrayList<Review> getReviewLocation() {
        return reviewLocation;
    }

    public void setReviewLocation(ArrayList<Review> reviewLocation) {
        this.reviewLocation = reviewLocation;
    }

    public ArrayList<Review> getReviewBeer() {
        return reviewBeer;
    }

    public void setReviewBeer(ArrayList<Review> reviewBeer) {
        this.reviewBeer = reviewBeer;
    }

    public void PostaviPodatke(JSONObject object) {
        podaciArray.clear();
        podaciArray.addAll(logika.parsiranjePodatakaStatistika(object));
        podaci = new DataPoint[podaciArray.size()];
        for (int i = 0; i < podaciArray.size(); i++) {
            podaci[i] = podaciArray.get(i);
        }
    }

    public DataPoint[] getPodaci() {
        return podaci;
    }

    public void setPodaci(DataPoint[] podaci) {
        this.podaci = podaci;
    }
}
