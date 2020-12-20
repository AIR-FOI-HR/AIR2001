package com.example.beervana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import com.example.webservice.DohvatPodataka;

import org.json.JSONObject;




public class GlavniIzbornikUser extends AppCompatActivity {
    private GlavniIzbornikUserViewModel viewModel;
    private RequestQueue requestQueue;
    private TextView prikazNajnovijeLokacije;
    String urlNajnovijaPivovara = "https://beervana2020.000webhostapp.com/test/zadnjaPivnica.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glavni_izbornik_user_activity);
        prikazNajnovijeLokacije = (TextView) findViewById(R.id.textViewNajnovijaLokacija);
        viewModel = new ViewModelProvider(this).get(GlavniIzbornikUserViewModel.class);
        if(viewModel.getLokacijaNajnovija()!=null){
            PostaviPodatkeNajnovijaPivovara();
        }
        retrieveData();
    }
    private void retrieveData() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        DohvatPodataka dohvatPodataka = new DohvatPodataka();
        dohvatPodataka.setSendUrl(urlNajnovijaPivovara);
        dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);

        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                if (odgovor != null) {
                    viewModel.ParsiranjeLokacijeZaGlavniIzbornikUser(odgovor);
                    PostaviPodatkeNajnovijaPivovara();
                }
            }
        });
    }

    private void PostaviPodatkeNajnovijaPivovara() {
        ModelPodatakaLokacijaSOcjenom lokacijaNajnovija = new ModelPodatakaLokacijaSOcjenom(viewModel.getLokacijaNajnovija().getLokacija(),viewModel.getLokacijaNajnovija().getOcjena());
        prikazNajnovijeLokacije.setText(lokacijaNajnovija.getLokacija().getNazivLokacija().concat("\nOcjena: ").concat(lokacijaNajnovija.getOcjena()));
    }
}