package com.example.beervana.Statistika;

import com.jjoe64.graphview.series.DataPoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class StatistikaLogika {
    public ArrayList<DataPoint> parsiranjePodatakaStatistika(JSONObject odgovor) {
        JSONArray jsonArray = null;
        ArrayList<DataPoint> pointovi = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH) + 1;
        try {
            jsonArray = odgovor.getJSONArray("statistika");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String prvi = object.getString("prvi_mjesec");
                String drugi = object.getString("drugi_mjesec");
                String treci = object.getString("treci_mjesec");
                DataPoint point1 = null;
                DataPoint point2 = null;
                DataPoint point3 = null;
                int prviM = month - 1;
                int drugiM = month - 2;
                int treciM = month - 3;
                if (prviM <= 0) {
                    prviM += 12;
                    drugiM += 12;
                    treciM += 12;
                } else if (drugiM <= 0) {
                    drugiM += 12;
                    treciM += 12;
                } else if (treciM <= 0) {
                    treciM += 12;
                }
                if (prvi.equals("null")) {
                    point1 = new DataPoint(prviM, 0);
                } else {
                    point1 = new DataPoint(prviM, Double.valueOf(prvi));
                }
                if (drugi.equals("null")) {
                    point2 = new DataPoint(drugiM, 0);
                } else {
                    point2 = new DataPoint(drugiM, Double.valueOf(drugi));
                }
                if (treci.equals("null")) {
                    point3 = new DataPoint(treciM, 0);
                } else {
                    point3 = new DataPoint(treciM, Double.valueOf(treci));
                }
                if (prviM > drugiM) {
                    pointovi.add(point3);
                    pointovi.add(point2);
                    pointovi.add(point1);
                } else if (drugiM > treciM) {
                    pointovi.add(point1);
                    pointovi.add(point3);
                    pointovi.add(point2);
                } else {
                    pointovi.add(point2);
                    pointovi.add(point1);
                    pointovi.add(point3);
                }

            }
        } catch (JSONException e) {
            pointovi = null;
            e.printStackTrace();
        }
        return pointovi;
    }
}
