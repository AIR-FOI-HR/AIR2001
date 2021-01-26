package com.example.beervana.GlavniIzbornik;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BaseActivity;
import com.example.beervana.BeerplacePage.BeerplaceHomepageActivityNew;
import com.example.beervana.BeerplacePage.ReviewsActivity;
import com.example.beervana.Karta.KartaActivity;
import com.example.beervana.R;
import com.example.modulzamodule.GlavniIzbornik.GlavniIzbornikUserViewModel;
import com.example.modulzamodule.Lokacija.ModelPodatakaLokacijaSOcjenom;
import com.example.modulzamodule.Beer.ModelPodatakaPivoSOcjenom;
import com.example.webservice.DohvatPodataka;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GlavniIzbornikUser extends BaseActivity {
    private GlavniIzbornikUserViewModel viewModel;
    private RequestQueue requestQueue;
    private TextView prikazNajnovijeLokacije, prikazNazivaNajboljeLokacije, prikazOcjeneNajboljeLokacije, prikazNajblizeLokacije1, prikazNajblizeLokacije2,
            udaljenost1, udaljenost2, recenzijaTekst, recenzijaDatum, recenzijaOcjena, omiljenaLokacijaPrva, omiljenaLokacijaDruga, omiljenoPivoPrvo, omiljenoPivoDrugo;
    String urlNajnovijaPivovara = "https://beervana2020.000webhostapp.com/test/zadnjaPivnica.php";
    String urlNajboljaPivoavara = "https://beervana2020.000webhostapp.com/test/NajboljaPivnicaMjeseca.php";
    String urlNajblizeLokacije = "https://beervana2020.000webhostapp.com/test/NajblizePivnice.php";
    String urlRecenzije = "https://beervana2020.000webhostapp.com/test/getReviewByUser.php";
    String urlNajdrazeLokacije = "https://beervana2020.000webhostapp.com/test/MojeLokacijeGlavniIzbornik.php";
    String urlNajdrazaPiva = "https://beervana2020.000webhostapp.com/test/MojaPivaGlavniIzbornik.php";
    Button idiNaNajnovijuPivovaru, idiNaNajboljuPivovaru, prikaziNajblizeLokacije, recenzijaGumb, prikaziNajdrazeLokacije, prikaziNajdrazaPiva;
    ImageView omiljenoPivo1, omiljenoPivo2;
    private SharedPreferences sp;
    private float KorisnikLongituda;
    private float KorisnikLatituda;
    private int idKorisnika;
    String odg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glavni_izbornik_user_activity);
        initToolbar();
        prikazNajnovijeLokacije = findViewById(R.id.textViewNajnovijaLokacija);
        prikazNazivaNajboljeLokacije = findViewById(R.id.textView8);
        prikazOcjeneNajboljeLokacije = findViewById(R.id.textView15);
        prikazNajblizeLokacije1 = findViewById(R.id.textView17);
        prikazNajblizeLokacije2 = findViewById(R.id.textView20);
        udaljenost1 = findViewById(R.id.textView18);
        udaljenost2 = findViewById(R.id.textView21);
        omiljenaLokacijaPrva = findViewById(R.id.textView25);
        omiljenaLokacijaDruga = findViewById(R.id.textView26);

        omiljenoPivoPrvo = findViewById(R.id.textView27);
        omiljenoPivoDrugo = findViewById(R.id.textView29);
        omiljenoPivo1 = findViewById(R.id.imageView27);
        omiljenoPivo2 = findViewById(R.id.imageView28);


        idiNaNajnovijuPivovaru = findViewById(R.id.button4);
        idiNaNajboljuPivovaru = findViewById(R.id.button9);
        prikaziNajblizeLokacije = findViewById(R.id.button10);
        prikaziNajdrazeLokacije = findViewById(R.id.button12);
        prikaziNajdrazaPiva = findViewById(R.id.button13);

        recenzijaTekst = findViewById(R.id.textView24);
        recenzijaDatum = findViewById(R.id.textView34);
        recenzijaOcjena = findViewById(R.id.textView35);
        recenzijaGumb = findViewById(R.id.button11);

        viewModel = new ViewModelProvider(this).get(GlavniIzbornikUserViewModel.class);
        sp = getSharedPreferences("login", MODE_PRIVATE);
        KorisnikLongituda = sp.getFloat("Longitude", (float) 0.0);
        KorisnikLatituda = sp.getFloat("Latitude", (float) 0.0);
        idKorisnika = sp.getInt("id_korisnik", 0);
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
        if (viewModel.getNajdrazeLokacije() != null) {
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

        prikaziNajdrazaPiva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ViewMyFavouriteBeersActivity.class));
            }
        });

        idiNaNajboljuPivovaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewModel.getLokacijaMjeseca() !=null) {
                    startActivity(new Intent(getApplicationContext(), BeerplaceHomepageActivityNew.class).putExtra("id_lokacija", viewModel.getLokacijaMjeseca().getLokacija().getId_lokacija())
                            .putExtra("naziv_lokacije", viewModel.getLokacijaMjeseca().getLokacija().getNazivLokacija())
                            .putExtra("ocjena_lokacije", viewModel.getLokacijaMjeseca().getOcjena()));
                }
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
                startActivity(new Intent(getApplicationContext(), ReviewsActivity.class).putExtra("id_korisnika", String.valueOf(idKorisnika)));
            }
        });
        PostaviSve();

    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveData();
        PostaviSve();
    }

    private void PostaviSve() {
    }

    private void retrieveData() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        DohvatPodataka dohvatPodataka = new DohvatPodataka();
        dohvatPodataka.setSendUrl(urlNajnovijaPivovara);
        dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                Map<String, String> params;
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                try {
                    if (odgovor != null) {
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
                        } else if (odgovor.getString("message").equals("Locations successfully loaded")) {
                            viewModel.ParsiranjeLokacijeZaGlavniIzbornikUserZaLokacijeUBlizini(odgovor);
                            PostaviPodatkeNajblizihPivovara();
                            params = new HashMap<String, String>();
                            params.put("id_korisnik", Integer.toString(idKorisnika));
                            dohvatPodataka.setParametri(params);
                            dohvatPodataka.setSendUrl(urlRecenzije);
                            dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);
                        } else if (odgovor.getString("message").equals("Succesfully retrived reviews")) {
                            viewModel.ParsiranjeRecenzijeZaGlavniIzbornikUser(odgovor);
                            PostaviPodatkeRecenzija();
                            params = new HashMap<String, String>();
                            params.put("id_korisnik", Integer.toString(idKorisnika));
                            dohvatPodataka.setParametri(params);
                            dohvatPodataka.setSendUrl(urlNajdrazeLokacije);
                            dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);
                        } else if (odgovor.getString("message").equals("Successfully retrieved my locations")) {
                            viewModel.ParsiranjeNajdrazihLokacija(odgovor);
                            postaviPodatkeNajdrazihLokacija();
                            params = new HashMap<>();
                            params.put("id_korisnik", Integer.toString(idKorisnika));
                            dohvatPodataka.setParametri(params);
                            dohvatPodataka.setSendUrl(urlNajdrazaPiva);
                            dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);
                        } else if (odgovor.getString("message").equals("Successfully retrieved my beers")) {
                            viewModel.ParsiranjeNajdrazihPiva(odgovor);
                            PostaviPodatkeNajdrazihPiva();
                        } else if (odgovor.getString("message").equals("Couldn't retrieve location")) {
                            params = new HashMap<String, String>();
                            params.put("Latituda", Float.toString(KorisnikLatituda));
                            params.put("Longituda", Float.toString(KorisnikLongituda));
                            dohvatPodataka.setParametri(params);
                            dohvatPodataka.setSendUrl(urlNajblizeLokacije);
                            dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void postaviPodatkeNajdrazihLokacija() {

        if (viewModel.getNajdrazeLokacije() != null && viewModel.getNajdrazeLokacije().size() != 0) {
            ModelPodatakaLokacijaSOcjenom modelPodatakaLokacijaSOcjenom = viewModel.getNajdrazeLokacije().get(0);
            omiljenaLokacijaPrva.setText("Naziv lokacije: ".concat(modelPodatakaLokacijaSOcjenom.getLokacija().getNazivLokacija()).concat("\n Adresa lokacije: ")
                    .concat(modelPodatakaLokacijaSOcjenom.getLokacija().getAdresaLokacija()).concat("\n Ocjena lokacije: ")
                    .concat(modelPodatakaLokacijaSOcjenom.getOcjena()));
            if (viewModel.getNajdrazeLokacije().size() > 1) {
                ModelPodatakaLokacijaSOcjenom modelPodatakaLokacijaSOcjenom1 = viewModel.getNajdrazeLokacije().get(1);
                omiljenaLokacijaDruga.setText("Naziv lokacije: ".concat(modelPodatakaLokacijaSOcjenom1.getLokacija().getNazivLokacija()).concat("\n Adresa lokacije: ")
                        .concat(modelPodatakaLokacijaSOcjenom1.getLokacija().getAdresaLokacija()).concat("\n Ocjena lokacije: ")
                        .concat(modelPodatakaLokacijaSOcjenom1.getOcjena()));
            } else {
                omiljenaLokacijaDruga.setVisibility(View.GONE);
            }


        } else {
            prikaziNajdrazeLokacije.setVisibility(View.GONE);
            omiljenaLokacijaPrva.setText("Currently you don't have any favourite locations added");
            omiljenaLokacijaDruga.setVisibility(View.GONE);
        }
    }

    private void PostaviPodatkeRecenzija() {
        if (viewModel.getRecenzijeMoje() != null && viewModel.getRecenzijeMoje().size() != 0) {
            recenzijaTekst.setText(viewModel.getRecenzijeMoje().get(0).getKomentar());
            recenzijaOcjena.setText(viewModel.getRecenzijeMoje().get(0).getOcjena());
            recenzijaDatum.setText(viewModel.getRecenzijeMoje().get(0).getDatum_i_vrijeme_recenzije());
        } else {
            recenzijaTekst.setVisibility(View.GONE);
            recenzijaOcjena.setVisibility(View.GONE);
            recenzijaDatum.setVisibility(View.GONE);
        }
    }

    private void PostaviPodatkeNajblizihPivovara() {
        ArrayList<ModelPodatakaLokacijaSOcjenom> najblizeLokacije = viewModel.getLokacijeUBlizini();
        if (najblizeLokacije == null) {
            prikazNajblizeLokacije2.setVisibility(View.GONE);
            udaljenost2.setVisibility(View.GONE);
            prikazNajblizeLokacije1.setVisibility(View.GONE);
            udaljenost1.setVisibility(View.GONE);
        } else if (najblizeLokacije.size() == 2) {
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
        prikazNazivaNajboljeLokacije.setVisibility(View.VISIBLE);
        prikazOcjeneNajboljeLokacije.setVisibility(View.VISIBLE);
        findViewById(R.id.imageView17).setVisibility(View.VISIBLE);
        findViewById(R.id.textView14).setVisibility(View.VISIBLE);
        findViewById(R.id.textView14).setVisibility(View.VISIBLE);
    }

    private void PostaviPodatkeNajnovijaPivovara() {
        ModelPodatakaLokacijaSOcjenom lokacijaNajnovija = new ModelPodatakaLokacijaSOcjenom(viewModel.getLokacijaNajnovija().getLokacija(), viewModel.getLokacijaNajnovija().getOcjena());
        prikazNajnovijeLokacije.setText(lokacijaNajnovija.getLokacija().getNazivLokacija().concat("\nOcjena: ").concat(lokacijaNajnovija.getOcjena()));
    }

    private void PostaviPodatkeNajdrazihPiva() {
        if (viewModel.getNajdrazaPiva() != null && viewModel.getNajdrazaPiva().size() != 0) {
            ModelPodatakaPivoSOcjenom najdrazaPiva = viewModel.getNajdrazaPiva().get(0);
            omiljenoPivoPrvo.setText("Ime piva: ".concat(najdrazaPiva.getBeer().getNaziv_proizvoda()).concat("\n Okus piva: ")
                    .concat(najdrazaPiva.getBeer().getOkus()).concat("\n Količina: ").concat(najdrazaPiva.getBeer().getLitara() + " l")
                    .concat("\n Cijena: ").concat(najdrazaPiva.getBeer().getCijena_proizvoda())
                    .concat("\n Ocjena: ").concat(najdrazaPiva.getOcjena().substring(0,4))
                    .concat("\n Lokacija: ").concat(najdrazaPiva.getNaziv_lokacije()));

            String imageUri = najdrazaPiva.getBeer().getSlika();
            Picasso.with(this.getApplicationContext()).load(imageUri).into(omiljenoPivo1);
            if (viewModel.getNajdrazaPiva().size() > 1) {
                ModelPodatakaPivoSOcjenom najdrazaPiva1 = viewModel.getNajdrazaPiva().get(0);
                omiljenoPivoPrvo.setText("Ime piva: ".concat(najdrazaPiva1.getBeer().getNaziv_proizvoda()).concat("\n Okus piva: ")
                        .concat(najdrazaPiva1.getBeer().getOkus()).concat("\n Količina: ").concat(najdrazaPiva1.getBeer().getLitara())
                        .concat("Cijena: ").concat(najdrazaPiva1.getBeer().getCijena_proizvoda()));

                String imageUri1 = najdrazaPiva1.getBeer().getSlika();
                Picasso.with(this.getApplicationContext()).load(imageUri1).into(omiljenoPivo2);
            } else {
                omiljenoPivoDrugo.setVisibility(View.GONE);
                omiljenoPivo2.setVisibility(View.GONE);
            }

        } else {
            prikaziNajdrazaPiva.setEnabled(false);
            prikaziNajdrazaPiva.setVisibility(View.GONE);
            omiljenoPivoPrvo.setText("Currrently you don't have any favourite beers added");
            omiljenoPivo2.setVisibility(View.GONE);
            omiljenoPivo1.setVisibility(View.GONE);
            omiljenoPivoDrugo.setVisibility(View.GONE);
        }

    }

}