package com.example.beervana.Payment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.braintreepayments.api.dropin.DropInActivity;
import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.dropin.DropInResult;
import com.braintreepayments.api.interfaces.HttpResponseCallback;
import com.braintreepayments.api.internal.HttpClient;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.example.beervana.BaseActivity;
import com.example.beervana.EventMenu.EventCatalogActivity;
import com.example.beervana.EventMenu.EventCatalogRecyclerAdapter;
import com.example.beervana.GlavniIzbornikClient;
import com.example.webservice.DohvatPodataka;
import com.example.webservice.SlanjePodataka;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beervana.R;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class PaymentActivity extends BaseActivity {
    private static final int REQUEST_CODE = 1234;
    final String API_GET_TOKEN = "https://beervana2020.000webhostapp.com/Braintree/main.php";
    final String API_CHECK_OUT = "https://beervana2020.000webhostapp.com/Braintree/checkout.php";
    private static final String url = "https://beervana2020.000webhostapp.com/test/setPayment.php";
    private static final String url1 = "https://beervana2020.000webhostapp.com/test/getPayment.php";

    String token, amount;
    HashMap<String, String> paramsHash;
    Button btn_pay;
    private String id_korisnik;

    RequestQueue requestQueue, requestQueueDatum;
    DateFormat df;
    String date;
    String stariDatum;
    TextView statusPretplate;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initToolbar();
        blockToolbar();

        sp = getSharedPreferences("login", MODE_PRIVATE);
        id_korisnik = Integer.toString(sp.getInt("id_korisnik",0));

        df = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date result = cal.getTime();
        date = df.format(result);

        //id_korisnik = "50";
        statusPretplate = (TextView) findViewById(R.id.StatusSubskripcija);
        checkStatus(date, id_korisnik);

        btn_pay = (Button) findViewById(R.id.KorisnikPlati);

        new getToken().execute();

        btn_pay.setOnClickListener(v -> submitPayment());
    }

    private void checkStatus(String datum1, String id_korisnik) {
        requestQueueDatum = Volley.newRequestQueue(getApplicationContext());
        PaymentLogika paymentLogika = new PaymentLogika();

        DohvatPodataka dohvatPodataka = new DohvatPodataka();
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_korisnik", id_korisnik);
        dohvatPodataka.setParametri(params);
        dohvatPodataka.setSendUrl(url1);
        dohvatPodataka.retrieveData(getApplicationContext(), requestQueueDatum);

        requestQueueDatum.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                if (odgovor != null) {
                    Payment payment = paymentLogika.parsiranjePodatakaDatuma(odgovor);
                    if(payment == null){
                        stariDatum = "1998-12-02";
                    }
                    else {
                        stariDatum = payment.datum;
                    }

                    ProvjeriDatum(stariDatum, datum1);
                }
            }
        });

    }

    private void ProvjeriDatum(String stariDatum, String datum) {
        LocalDate datumPretplate = LocalDate.parse(datum);
        if(stariDatum.equals("null")){
            stariDatum = "1998-12-02";
        }
        LocalDate stariDatum2 = LocalDate.parse(stariDatum);
        if(stariDatum2.isBefore(datumPretplate)){
            statusPretplate.setText("Inactive");
        } else{
            statusPretplate.setText("Active");
            unBlockToolbar();
        }
    }

    private void submitPayment() {
        DropInRequest dropInRequest = new DropInRequest().clientToken(token);
        startActivityForResult(dropInRequest.getIntent(this),REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                DropInResult result = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);
                PaymentMethodNonce nonce = result.getPaymentMethodNonce();
                String strNonce = nonce.getNonce();
                String amount = "40";
                paramsHash = new HashMap<>();
                paramsHash.put("amount", amount);
                paramsHash.put("nonce", strNonce);

                sendPayments();

            }
            else if (resultCode == RESULT_CANCELED){
                Toast.makeText(this, "User Cancel", Toast.LENGTH_SHORT).show();
            }
            else{
                Exception error = (Exception) data.getSerializableExtra(DropInActivity.EXTRA_ERROR);
                Log.d("EDMT_ERROR", error.toString());
            }
        }
    }

    private void sendPayments() {
        RequestQueue queue = Volley.newRequestQueue(PaymentActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_CHECK_OUT, response -> {

            if(response.toString().contains("Successful")){
                requestQueue = Volley.newRequestQueue(getApplicationContext());

                Map<String, String> params = new HashMap<String, String>();
                params.put("id_korisnik", id_korisnik);

                SlanjePodataka slanjePodataka = new SlanjePodataka(url);
                slanjePodataka.setParametri(params);
                slanjePodataka.sendData(getApplicationContext(), requestQueue);

                requestQueue.addRequestFinishedListener(request -> {
                    String odgovor = slanjePodataka.getOdgovor();
                    if (odgovor.equals("Succesfully updated your payment!")) {
                        //statusPretplate.setText("Active");
                        //unBlockToolbar();
                        Toast toast = Toast.makeText(getApplicationContext(), "Succesfully updated your payment!", Toast.LENGTH_LONG);
                        toast.show();
                        posaljiNaIzbornik();
                    }
                });

                Toast.makeText(PaymentActivity.this, "Transaction successful!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(PaymentActivity.this, "Transaction unsuccessful!", Toast.LENGTH_SHORT).show();


            }
            Log.d("EDMT_ERROR", response.toString());

        }, error -> Log.d("EDMT_ERROR", error.toString()))
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if(paramsHash==null){
                    return null;
                }
                Map<String, String> params = new HashMap<>();
                for(String key:paramsHash.keySet()){
                    params.put(key,paramsHash.get(key));
                }
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(stringRequest);


    }

    private void posaljiNaIzbornik() {
        Intent intent = new Intent(this, GlavniIzbornikClient.class);
        startActivity(intent);
    }

    private class getToken extends AsyncTask {
        ProgressDialog mDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mDialog = new ProgressDialog(PaymentActivity.this, android.R.style.Theme_DeviceDefault_Dialog);
            mDialog.setCancelable(false);
            mDialog.setMessage("Please wait");
            mDialog.show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            HttpClient client = new HttpClient();
            client.get(API_GET_TOKEN, new HttpResponseCallback() {
                @Override
                public void success(String responseBody) {
                    runOnUiThread(() -> token = responseBody);
                }

                @Override
                public void failure(Exception exception) {
                    Log.d("EDMT_ERROR", exception.toString());
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            mDialog.dismiss();
        }
    }
}