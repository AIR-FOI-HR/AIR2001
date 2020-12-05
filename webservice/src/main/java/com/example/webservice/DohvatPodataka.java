package com.example.webservice;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class DohvatPodataka {
    private String sendUrl;
    private JSONObject odgovor = null;
    private Map<String,String> parametri = null;

    public void setParametri(Map<String, String> parametri) {
        this.parametri = parametri;
    }


    public JSONObject getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(JSONObject odgovor) {
        this.odgovor = odgovor;
    }

    public String getSendUrl() {
        return sendUrl;
    }

    public void setSendUrl(String sendUrl) {
        this.sendUrl = sendUrl;
    }

    public void retrieveData(Context context, RequestQueue requestQueue){
        StringRequest request = new StringRequest(Request.Method.POST, sendUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            //JSONArray jsonArray = jsonObject.getJSONArray("proizvod");

                            if(success.equals("1")){
                                odgovor = jsonObject;

                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            public Map<String, String> getParams() {
                return parametri;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 1, 1.0f));
        requestQueue.add(request);

    }
}
