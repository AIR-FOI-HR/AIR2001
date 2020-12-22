package com.example.beervana.BeerMenu;

import com.example.beervana.BeerMenu.Beer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BeerLogic {
    public String ProvjeraUnosaSlike(String slika) {
        if (slika.equals("")) {
            return "Error: you must choose an image";
        }

        return "";
    }

    public String ProvjeraUnosaNazivaPiva(String nazivPiva) {

        if (nazivPiva.equals("")) {
            return "Error: you have to enter beer name.";
        } else if (nazivPiva.length() < 3 || nazivPiva.length() > 45) {
            return "Error: beer name must be between 3 and 45 characters long.";
        }
        return "";
    }

    public String ProvjeraUnosaCijenePiva(Double cijenaPiva) {

        if (cijenaPiva.equals("")) {
            return "Error: you have to enter price of beer.";
        } else if (cijenaPiva < 0) {
            return "Error: Beer price must be positive. ";
        }
        return "";
    }

    public String ProvjeraUnosaOkusa(String okusPiva) {

        if (okusPiva.equals("")) {
            return "Error: you have to enter beer taste. ";
        } else if (okusPiva.length() < 3 || okusPiva.length() > 100) {
            return "Error: beer taste must be between 3 and 100 characters long";
        }
        return "";
    }

    public String ProvjeraUnosaLitara(Double litra) {

        if (litra.equals("")) {
            return "Error: you have to enter beer litres. ";
        } else if (litra < 0) {
            return "Error: beer litre must be positive";
        }
        return "";
    }

    public ArrayList<Beer> parsiranjePodatakaPiva(JSONObject jsonObject) {
        JSONArray jsonArray = null;
        ArrayList<Beer> beerArrayList = new ArrayList<Beer>();
        try {
            jsonArray = jsonObject.getJSONArray("proizvod");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String id_proizvod = object.getString("id_proizvod");
                String id_kategorija = object.getString("id_kategorija");
                String naziv_proizvoda = object.getString("naziv_proizvoda");
                String cijena_proizvoda = object.getString("cijena_proizvoda");
                String okus = object.getString("okus");
                String litara = object.getString("litara");
                String slika = object.getString("slika");

                Beer beer = new Beer(id_proizvod, id_kategorija, naziv_proizvoda, cijena_proizvoda, okus, litara, slika);
                beerArrayList.add(beer);


            }
        } catch (JSONException e) {
            beerArrayList = null;
            e.printStackTrace();
        }

        return beerArrayList;
    }

    public ArrayList<Beer> parsiranjePodatakaPivaZaDegustacijskiMeni(JSONObject jsonObject) {
        JSONArray jsonArray = null;
        ArrayList<Beer> beerArrayList = new ArrayList<Beer>();
        try {
            jsonArray = jsonObject.getJSONArray("proizvod");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                String naziv_proizvoda = object.getString("naziv_proizvoda");
                String cijena_proizvoda = object.getString("cijena_proizvoda");
                String okus = object.getString("okus");

                Beer beer = new Beer(naziv_proizvoda, cijena_proizvoda, okus);
                beerArrayList.add(beer);
            }
        } catch (JSONException e) {
            beerArrayList = null;
            e.printStackTrace();
        }

        return beerArrayList;
    }
    public ArrayList<Beer> parsiranjePivaZaPretrazivanje(JSONObject jsonObject) {
        JSONArray jsonArray = null;
        ArrayList<Beer> beerArrayList = new ArrayList<Beer>();
        try {
            jsonArray = jsonObject.getJSONArray("body");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String id_proizvod = object.getString("id_proizvod");
                String naziv_proizvoda = object.getString("naziv_proizvoda");
                String cijena_proizvoda = object.getString("cijena_proizvoda");
                String okus = object.getString("okus");
                String litara = object.getString("litara");
                String slika = object.getString("slika");

                Beer beer = new Beer(id_proizvod, "", naziv_proizvoda, cijena_proizvoda, okus, litara, slika);
                beerArrayList.add(beer);


            }
        } catch (JSONException e) {
            beerArrayList = null;
            e.printStackTrace();
        }

        return beerArrayList;
    }
}
