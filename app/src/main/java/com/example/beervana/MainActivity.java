package com.example.beervana;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.beervana.databinding.ActivityMainBinding;
import com.example.webservice.SlanjePodataka;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private String sendUrl="https://beervana2020.000webhostapp.com/test/login.php";
    private RequestQueue requestQueue;
    private  static  final  String TAG=MainActivity.class.getSimpleName();
    int success;
    private String TAG_SUCESS="success";
    private String TAG_MESSAGE="message";
    private String tag_json_obj="json_obj_req";

    private EditText korisnickoIme;
    private EditText lozinka;

    private TextView errorKorisnickoIme;
    private TextView errorLozinka;
    private TextView signUp;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        super.onCreate(savedInstanceState);
        setContentView(view);

        final MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        korisnickoIme = binding.etUsername;
        lozinka = binding.etPassword;

        errorKorisnickoIme = binding.errUnosKorisnickoIme;
        errorLozinka = binding.errUnosLozinka;

        errorKorisnickoIme.setText(viewModel.getErrorKorisnickoIme());
        errorLozinka.setText(viewModel.getErrorLozinka());

        errorKorisnickoIme.setVisibility(viewModel.errorKorisnickoImeVidljivost);
        errorLozinka.setVisibility(viewModel.errorLozinkaVidljivost);

        signUp = binding.btnSignUp;
        signUp.setMovementMethod(LinkMovementMethod.getInstance());

        requestQueue= Volley.newRequestQueue(getApplicationContext());

        binding.btnUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setKorisnickoIme(korisnickoIme.getText().toString());
                viewModel.setLozinka(lozinka.getText().toString());
                if (viewModel.ProvjeriPodatke()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Uspješna prijava! ", Toast.LENGTH_LONG);
                    toast.show();
                    Map<String, String> params=new HashMap<String, String>();
                    params.put("korsnicko_ime",korisnickoIme.getText().toString());
                    params.put("lozinka",lozinka.getText().toString());
                    SlanjePodataka slanjePodataka = new SlanjePodataka(sendUrl);
                    slanjePodataka.setParametri(params);
                    slanjePodataka.sendData(getApplicationContext(),requestQueue);
                    //requestQueue = slanjePodataka.sendData(getApplicationContext());
                    requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
                        @Override
                        public void onRequestFinished(Request<Object> request) {
                            String odgovor = slanjePodataka.getOdgovor();
                            Toast noviToast = Toast.makeText(getApplicationContext(),odgovor,Toast.LENGTH_LONG);
                            noviToast.show();
                            if(odgovor.equals(" Successfully loged in")){
                                openMainMenu();
                            }
                        }
                    });




                } else {
                    errorKorisnickoIme.setText(viewModel.getErrorKorisnickoIme());
                    errorLozinka.setText(viewModel.getErrorLozinka());
                }
                errorKorisnickoIme.setVisibility(viewModel.errorKorisnickoImeVidljivost);
                errorLozinka.setVisibility(viewModel.errorLozinkaVidljivost);
            }
        });
      binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              openRegistration();
          }
      });

    }

    public void openRegistration(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    public void openMainMenu(){
        Intent intent = new Intent(this,GlavniIzbornikClient.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private  void sendData(){
        StringRequest request=new StringRequest(Request.Method.POST, sendUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //Log.e ( "response", "" + response );
                    JSONObject jobj = new JSONObject(response);
                    success = jobj.getInt(TAG_SUCESS);
                    if (success == 1) {
                        Toast.makeText(MainActivity.this, jobj.getString(TAG_MESSAGE), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, jobj.getString(TAG_MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    //Log.e ( "response", "" + response );
                    Toast.makeText(MainActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            public Map<String,String> getParams(){
                Map<String, String> params=new HashMap<String, String>();
                params.put("korsnicko_ime",korisnickoIme.getText().toString());
                params.put("lozinka",lozinka.getText().toString());
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(10000,1,1.0f));
        requestQueue.add(request);

    }

}