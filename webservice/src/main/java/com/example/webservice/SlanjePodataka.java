package com.example.webservice;

import android.content.Context;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class SlanjePodataka {
    private String sendUrl;

    //private static final String TAG = RegisterActivity.class.getSimpleName();
    int success;
    private String TAG_SUCESS = "success";
    private String TAG_MESSAGE = "message";
    private String tag_json_obj = "json_obj_req";
    private Map<String,String> parametri;
    private String odgovor = "";

    public String getSendUrl() {
        return sendUrl;
    }

    public void setSendUrl(String sendUrl) {
        this.sendUrl = sendUrl;
    }

    public String getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(String odgovor) {
        this.odgovor = odgovor;
    }

    public Map<String, String> getParametri() {
        return parametri;
    }

    public void setParametri(Map<String, String> parametri) {
        this.parametri = parametri;
    }

    public SlanjePodataka(String sendUrl) {
        this.sendUrl = sendUrl;
    }

    public void sendData(Context context, RequestQueue requestQueue) {
        //requestQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, sendUrl, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    success = jobj.getInt(TAG_SUCESS);
                    if (success == 1) {
                        //Toast.makeText(context, jobj.getString(TAG_MESSAGE), Toast.LENGTH_SHORT).show();
                        odgovor = jobj.getString(TAG_MESSAGE);
                    } else {
                        //Toast.makeText(RegisterActivity.this, jobj.getString(TAG_MESSAGE), Toast.LENGTH_SHORT).show();
                        odgovor = jobj.getString(TAG_MESSAGE);
                    }
                } catch (JSONException e) {
                    //Toast.makeText(RegisterActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                    odgovor = "Error Occured";
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(RegisterActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                odgovor = error.getMessage();
            }
        }) {
            public Map<String, String> getParams() {
                return parametri;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 1, 1.0f));

        requestQueue.add(request);


    }


}
