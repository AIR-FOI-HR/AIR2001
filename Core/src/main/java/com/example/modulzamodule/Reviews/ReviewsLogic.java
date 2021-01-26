package com.example.modulzamodule.Reviews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReviewsLogic {

    public static String ProvjeraUnosaKomentara(String komentar) {
        if (komentar.equals("")) {
            return "Error: you must enter a textual review.";
        }

        return "";
    }

    public static String ProvjeraUnosaOcjene(Double ocjena) {

        if (ocjena.equals("")) {
            return "Error: you must give your rating.";
        }

        return "";
    }



    public static ArrayList<Review> parsiranjePodatakaReviewa(JSONObject jsonObject) {
        JSONArray jsonArray = null;
        ArrayList<Review> reviewsArrayList = new ArrayList<Review>();
        try {
            jsonArray = jsonObject.getJSONArray("recenzija");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String id_recenzija = object.getString("id_recenzija");
                String id_proizvod = object.getString("id_proizvod");
                String id_korisnik = object.getString("id_korisnik");
                String id_lokacija = object.getString("id_lokacija");
                String ocjena = object.getString("ocjena");
                String komentar = object.getString("komentar");
                String datum_i_vrijeme_recenzije = object.getString("datum_i_vrijeme_recenzije");

                Review review = new Review(id_recenzija, id_proizvod, id_korisnik, id_lokacija, ocjena, komentar, datum_i_vrijeme_recenzije);
                reviewsArrayList.add(review);


            }
        } catch (JSONException e) {
            reviewsArrayList = null;
            e.printStackTrace();
        }

        return reviewsArrayList;
    }

    public static ArrayList<Review> parsiranjePodatakaReviewaZaGlavniIzbornikUser(JSONObject jsonObject) {
        JSONArray jsonArray = null;
        ArrayList<Review> reviewsArrayList = new ArrayList<Review>();
        try {
            jsonArray = jsonObject.getJSONArray("body");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String ocjena = object.getString("ocjena");
                String komentar = object.getString("komentar");
                String datum_i_vrijeme_recenzije = object.getString("datum_i_vrijeme_recenzije");

                Review review = new Review("", "", "", "", ocjena, komentar, datum_i_vrijeme_recenzije);
                reviewsArrayList.add(review);


            }
        } catch (JSONException e) {
            reviewsArrayList = null;
            e.printStackTrace();
        }

        return reviewsArrayList;
    }
}
