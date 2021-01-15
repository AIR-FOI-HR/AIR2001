package com.example.beervana.TastingMenu;


import com.example.modulzamodule.TastingMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoadTastingMenu {
    public ArrayList<TastingMenu> loadTastingMenu(JSONObject odgovor) {
        JSONArray jsonArray = null;
        ArrayList<TastingMenu> tastingMenuArrayList = new ArrayList<TastingMenu>();
        try {
            jsonArray = odgovor.getJSONArray("tastingMenu");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                TastingMenu tastingMenu = new TastingMenu(
                        object.getString("id_degustacijski_meni"),
                        object.getString("naziv_menija"),
                        object.getString("trajanje"),
                        object.getString("opis_menija"),
                        object.getString("id_lokacija")
                );
                tastingMenuArrayList.add(tastingMenu);
            }
        } catch (JSONException e) {
            tastingMenuArrayList = null;
            e.printStackTrace();
        }
        return tastingMenuArrayList;
    }
}
