package com.example.beervana.Statistika;

import androidx.lifecycle.ViewModel;

import com.jjoe64.graphview.series.DataPoint;

import org.json.JSONObject;

import java.util.ArrayList;

public class StatistikaViewModel extends ViewModel {
    DataPoint[] podaci;
    ArrayList<DataPoint> podaciArray = new ArrayList<>();
    StatistikaLogika logika = new StatistikaLogika();
    public void PostaviPodatke(JSONObject object){
        podaciArray.clear();
        podaciArray.addAll(logika.parsiranjePodatakaStatistika(object));
        podaci = new DataPoint[podaciArray.size()];
        for(int i = 0;i<podaciArray.size();i++){
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
