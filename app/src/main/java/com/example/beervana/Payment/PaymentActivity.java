package com.example.beervana.Payment;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.beervana.R;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class PaymentActivity extends BaseActivity {
    private static final int REQUEST_CODE = 1234;
    final String API_GET_TOKEN = "https://beervana2020.000webhostapp.com/Braintree/main.php";
    final String API_CHECK_OUT = "https://beervana2020.000webhostapp.com/Braintree/checkout.php";

    String token, amount;
    HashMap<String, String> paramsHash;
    Button btn_pay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initToolbar();

        btn_pay = (Button) findViewById(R.id.KorisnikPlati);

        new getToken().execute();

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPayment();
            }
        });
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
                String amount = "50";
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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_CHECK_OUT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.toString().contains("Successful")){
                    Toast.makeText(PaymentActivity.this, "Transaction successful!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(PaymentActivity.this, "Transaction unsuccessful!", Toast.LENGTH_SHORT).show();


                }
                Log.d("EDMT_ERROR", response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("EDMT_ERROR", error.toString());
            }
        })
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
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            token = responseBody;
                        }
                    });
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