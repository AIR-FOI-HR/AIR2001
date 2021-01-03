package com.example.beervana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import com.example.beervana.BeerplacePage.BeerplaceHomepageActivityNew;
import com.example.beervana.BeerplacePage.Review;
import com.example.beervana.BeerplacePage.ReviewsActivity;
import com.example.beervana.BeerplacePage.ReviewsLogic;
import com.example.beervana.EventMenu.ModelPodatakEventCatalog;
import com.example.beervana.EventMenu.PrikazZaEventPodatkeActivity;
import com.example.beervana.Karta.KartaActivity;
import com.example.webservice.DohvatPodataka;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GlavniIzbornikUser extends BaseActivity {
    private GlavniIzbornikUserViewModel viewModel;
    private RequestQueue requestQueue;
    private TextView prikazNajnovijeLokacije, prikazNazivaNajboljeLokacije, prikazOcjeneNajboljeLokacije, prikazNajblizeLokacije1, prikazNajblizeLokacije2,
            udaljenost1, udaljenost2, recenzijaTekst, recenzijaDatum, recenzijaOcjena, omiljenaLokacijaPrva, omiljenaLokacijaDruga;
    String urlNajnovijaPivovara = "https://beervana2020.000webhostapp.com/test/zadnjaPivnica.php";
    String urlNajboljaPivoavara = "https://beervana2020.000webhostapp.com/test/NajboljaPivnicaMjeseca.php";
    String urlNajblizeLokacije = "https://beervana2020.000webhostapp.com/test/NajblizePivnice.php";
    String urlRecenzije = "https://beervana2020.000webhostapp.com/test/getReviewByUser.php";
    String urlNajdrazeLokacije= "https://beervana2020.000webhostapp.com/test/MojeLokacijeGlavniIzbornik.php";
    Button idiNaNajnovijuPivovaru, idiNaNajboljuPivovaru, prikaziNajblizeLokacije, recenzijaGumb, prikaziNajdrazeLokacije;
    private SharedPreferences sp;
    private float KorisnikLongituda;
    private float KorisnikLatituda;
    private int idKorisnika;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glavni_izbornik_user_activity);
        initToolbar();
        prikazNajnovijeLokacije = (TextView) findViewById(R.id.textViewNajnovijaLokacija);
        prikazNazivaNajboljeLokacije = (TextView) findViewById(R.id.textView8);
        prikazOcjeneNajboljeLokacije = (TextView) findViewById(R.id.textView15);
        prikazNajblizeLokacije1 = (TextView) findViewById(R.id.textView17);
        prikazNajblizeLokacije2 = (TextView) findViewById(R.id.textView20);
        udaljenost1 = (TextView) findViewById(R.id.textView18);
        udaljenost2 = (TextView) findViewById(R.id.textView21);
        omiljenaLokacijaPrva = (TextView) findViewById(R.id.textView25);
        omiljenaLokacijaDruga = (TextView) findViewById(R.id.textView26);

        idiNaNajnovijuPivovaru = (Button) findViewById(R.id.button4);
        idiNaNajboljuPivovaru = (Button) findViewById(R.id.button9);
        prikaziNajblizeLokacije = (Button) findViewById(R.id.button10);
        prikaziNajdrazeLokacije = (Button) findViewById(R.id.button12);

        recenzijaTekst = (TextView) findViewById(R.id.textView24);
        recenzijaDatum = (TextView) findViewById(R.id.textView34);
        recenzijaOcjena = (TextView) findViewById(R.id.textView35);
        recenzijaGumb = (Button) findViewById(R.id.button11);

        viewModel = new ViewModelProvider(this).get(GlavniIzbornikUserViewModel.class);
        sp = getSharedPreferences("login", MODE_PRIVATE);
        KorisnikLongituda = sp.getFloat("Longitude", (float) 0.0);
        KorisnikLatituda = sp.getFloat("Latitude", (float) 0.0);
        idKorisnika = sp.getInt("id_korisnik", 20);
        if (viewModel.getLokacijaNajnovija() != null) {
            PostaviPodatkeNajnovijaPivovara();
        }
        if (viewModel.getLokacijaMjeseca() != null) {
            PostaviPodatkePivovaraMjeseca();
        }
        if (viewModel.getLokacijeUBlizini() != null) {
            PostaviPodatkeNajblizihPivovara();
        }
        if (viewModel.getRecenzijeMoje() != null) {
            PostaviPodatkeRecenzija();
        }
        if(viewModel.getNajdrazeLokacije()!=null){
            postaviPodatkeNajdrazihLokacija();
        }
        retrieveData();
        idiNaNajnovijuPivovaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BeerplaceHomepageActivityNew.class).putExtra("id_lokacija", viewModel.getLokacijaNajnovija().getLokacija().getId_lokacija())
                        .putExtra("naziv_lokacije", viewModel.getLokacijaNajnovija().getLokacija().getNazivLokacija())
                        .putExtra("ocjena_lokacije", viewModel.getLokacijaNajnovija().getOcjena()));
            }
        });

        prikaziNajdrazeLokacije.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ViewMyFavoriteLocationsActivity.class));
            }
        });

        idiNaNajboljuPivovaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BeerplaceHomepageActivityNew.class).putExtra("id_lokacija", viewModel.getLokacijaMjeseca().getLokacija().getId_lokacija())
                        .putExtra("naziv_lokacije", viewModel.getLokacijaMjeseca().getLokacija().getNazivLokacija())
                        .putExtra("ocjena_lokacije", viewModel.getLokacijaMjeseca().getOcjena()));
            }
        });

        prikaziNajblizeLokacije.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), KartaActivity.class));
            }
        });

        recenzijaGumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ReviewsActivity.class).putExtra("id_korisnika", idKorisnika));
            }
        });

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
                try {
                    if (odgovor.getString("message").equals("Successfully retrieved location")) {
                        viewModel.ParsiranjeLokacijeZaGlavniIzbornikUser(odgovor, 1);
                        if (viewModel.getLokacijaNajnovija() != null) {
                            PostaviPodatkeNajnovijaPivovara();
                        }
                        dohvatPodataka.setSendUrl(urlNajboljaPivoavara);
                        dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);

                    } else if (odgovor.getString("message").equals("Successfully retrieved best location")) {
                        viewModel.ParsiranjeLokacijeZaGlavniIzbornikUser(odgovor, 2);
                        if (viewModel.getLokacijaMjeseca() != null) {
                            PostaviPodatkePivovaraMjeseca();
                        }
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Latituda", Float.toString(KorisnikLatituda));
                        params.put("Longituda", Float.toString(KorisnikLongituda));
                        dohvatPodataka.setParametri(params);
                        dohvatPodataka.setSendUrl(urlNajblizeLokacije);
                        dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);
                    } else if (odgovor.getString("message").equals("Locations successfully loaded")) {
                        viewModel.ParsiranjeLokacijeZaGlavniIzbornikUserZaLokacijeUBlizini(odgovor);
                        PostaviPodatkeNajblizihPivovara();
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("id_korisnik", Integer.toString(idKorisnika));
                        dohvatPodataka.setParametri(params);
                        dohvatPodataka.setSendUrl(urlRecenzije);
                        dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);
                    } else if (odgovor.getString("message").equals("Succesfully retrived reviews")) {
                        viewModel.ParsiranjeRecenzijeZaGlavniIzbornikUser(odgovor);
                        PostaviPodatkeRecenzija();
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("id_korisnik", Integer.toString(idKorisnika));
                        dohvatPodataka.setParametri(params);
                        dohvatPodataka.setSendUrl(urlNajdrazeLokacije);
                        dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);

                    } else if (odgovor.getString("message").equals("Successfully retrieved my locations")){
                        viewModel.ParsiranjeNajdrazihLokacija(odgovor);
                        postaviPodatkeNajdrazihLokacija();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void postaviPodatkeNajdrazihLokacija() {

        if(viewModel.getNajdrazeLokacije() != null && viewModel.getNajdrazeLokacije().size() != 0){
            ModelPodatakaLokacijaSOcjenom modelPodatakaLokacijaSOcjenom = viewModel.getNajdrazeLokacije().get(0);
            omiljenaLokacijaPrva.setText("Naziv lokacije: ".concat(modelPodatakaLokacijaSOcjenom.getLokacija().getNazivLokacija()).concat("\n Adresa lokacije: ")
                    .concat(modelPodatakaLokacijaSOcjenom.getLokacija().getAdresaLokacija()).concat("\n Ocjena lokacije: ")
                    .concat(modelPodatakaLokacijaSOcjenom.getOcjena()));
            if(viewModel.getNajdrazeLokacije().size()>1){
                ModelPodatakaLokacijaSOcjenom modelPodatakaLokacijaSOcjenom1 = viewModel.getNajdrazeLokacije().get(1);
                omiljenaLokacijaDruga.setText("Naziv lokacije: ".concat(modelPodatakaLokacijaSOcjenom1.getLokacija().getNazivLokacija()).concat("\n Adresa lokacije: ")
                        .concat(modelPodatakaLokacijaSOcjenom1.getLokacija().getAdresaLokacija()).concat("\n Ocjena lokacije: ")
                        .concat(modelPodatakaLokacijaSOcjenom1.getOcjena()));
            }
            else{
                omiljenaLokacijaDruga.setVisibility(View.GONE);
            }


        }
        else{
            prikaziNajdrazeLokacije.setVisibility(View.GONE);
            omiljenaLokacijaPrva.setText("Currently you don't have any favourite locations added");
            omiljenaLokacijaDruga.setVisibility(View.GONE);
        }
    }

    private void PostaviPodatkeRecenzija() {
        if(viewModel.getRecenzijeMoje() != null && viewModel.getRecenzijeMoje().size() != 0){
            recenzijaTekst.setText(viewModel.getRecenzijeMoje().get(0).getKomentar());
            recenzijaOcjena.setText(viewModel.getRecenzijeMoje().get(0).getOcjena());
            recenzijaDatum.setText(viewModel.getRecenzijeMoje().get(0).getDatum_i_vrijeme_recenzije());
        }else{
            recenzijaTekst.setVisibility(View.GONE);
            recenzijaOcjena.setVisibility(View.GONE);
            recenzijaDatum.setVisibility(View.GONE);
        }
    }

    private void PostaviPodatkeNajblizihPivovara() {
        ArrayList<ModelPodatakaLokacijaSOcjenom> najblizeLokacije = viewModel.getLokacijeUBlizini();
        if(najblizeLokacije==null){
            prikazNajblizeLokacije2.setVisibility(View.GONE);
            udaljenost2.setVisibility(View.GONE);
            prikazNajblizeLokacije1.setVisibility(View.GONE);
            udaljenost1.setVisibility(View.GONE);
        }
        else if (najblizeLokacije.size() == 2) {
            prikazNajblizeLokacije1.setText(najblizeLokacije.get(0).getLokacija().getNazivLokacija());
            prikazNajblizeLokacije2.setText(najblizeLokacije.get(1).getLokacija().getNazivLokacija());
            udaljenost1.setText(najblizeLokacije.get(0).getUdaljenost());
            udaljenost2.setText(najblizeLokacije.get(1).getUdaljenost());
        } else if (najblizeLokacije.size() == 1) {
            prikazNajblizeLokacije1.setText(najblizeLokacije.get(0).getLokacija().getNazivLokacija());
            udaljenost1.setText(najblizeLokacije.get(0).getUdaljenost());
            prikazNajblizeLokacije2.setVisibility(View.GONE);
            udaljenost2.setVisibility(View.GONE);
        } else {
            prikazNajblizeLokacije2.setVisibility(View.GONE);
            udaljenost2.setVisibility(View.GONE);
            prikazNajblizeLokacije1.setVisibility(View.GONE);
            udaljenost1.setVisibility(View.GONE);
        }
    }

    private void PostaviPodatkePivovaraMjeseca() {
        ModelPodatakaLokacijaSOcjenom lokacijaNajbolja = new ModelPodatakaLokacijaSOcjenom(viewModel.getLokacijaMjeseca().getLokacija(), viewModel.getLokacijaMjeseca().getOcjena());
        prikazNazivaNajboljeLokacije.setText(lokacijaNajbolja.getLokacija().getNazivLokacija());
        prikazOcjeneNajboljeLokacije.setText(lokacijaNajbolja.getOcjena());
    }

    private void PostaviPodatkeNajnovijaPivovara() {
        ModelPodatakaLokacijaSOcjenom lokacijaNajnovija = new ModelPodatakaLokacijaSOcjenom(viewModel.getLokacijaNajnovija().getLokacija(), viewModel.getLokacijaNajnovija().getOcjena());
        prikazNajnovijeLokacije.setText(lokacijaNajnovija.getLokacija().getNazivLokacija().concat("\nOcjena: ").concat(lokacijaNajnovija.getOcjena()));
    }


}