package com.example.beervana.Payment;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PaymentLogika {
    private Payment payment;

    public Payment parsiranjePodatakaDatuma(JSONObject odgovor) {

        JSONArray jsonArray = null;

        try {
            jsonArray = odgovor.getJSONArray("body");
            JSONObject object = jsonArray.getJSONObject(0);
            String datum = object.getString("zadnja_uplata");
            payment = new Payment(datum);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return payment;

    }
}
