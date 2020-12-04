package com.example.beervana;
import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PrikazPodatakaOPivovariActivity extends AppCompatActivity
{
    TextView beerName1, beerName2, beerTaste1, beerTaste2, beerLitres2, beerLitres1, eventTitle1, eventTitle2, eventDesc1, eventDesc2,  menuTitle1,  menuTitle2, menuDesc1, menuDesc2, ocjena1, ocjena2, komentar1, komentar2;
    ImageView beerImage1,beerImage2,eventImage1,eventImage2;
    PrikazPodatakaOPivovariAdapter adapter;
    public static ArrayList<PrikazPodatakaOPivovaryViewModel> prikazPodatakaOPivovaryViewModelArrayList = new ArrayList<>();
    String url = "https://beervana2020.000webhostapp.com/test/fetchInfoPivovare.php";
    PrikazPodatakaOPivovaryViewModel pivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_prikaz_podataka_o_pivovari);

        adapter = new PrikazPodatakaOPivovariAdapter(this, prikazPodatakaOPivovaryViewModelArrayList);

        dohvatiPodatke1();



        /*
        //podaci
        beerName1 = findViewById(R.id.textViewBeerTitle1);
        beerName2 = findViewById(R.id.textViewBeerTitle2);
        beerTaste1 = findViewById(R.id.textViewOkus1);
        beerTaste2 = findViewById(R.id.textViewOkus3);
        beerLitres1 = findViewById(R.id.textViewOkus2);
        beerLitres2 = findViewById(R.id.textViewOkus4);
        eventTitle1 = findViewById(R.id.textViewEventTitle1);
        eventTitle2 = findViewById(R.id.textViewEventTitle2);
        eventDesc1 = findViewById(R.id.textViewEventDesc1);
        eventDesc2 = findViewById(R.id.textViewEventDesc2);
        menuTitle1 = findViewById(R.id.textViewTastingMTitle1);
        menuTitle2 = findViewById(R.id.textViewTastingMTitle2);
        menuDesc1 = findViewById(R.id.textViewTastingMDesc1);
        menuDesc2 = findViewById(R.id.textViewTastingMDesc);
        ocjena1 = findViewById(R.id.userReviewTextView);
        ocjena2 = findViewById(R.id.userReviewTextView3);
        komentar1 = findViewById(R.id.commentTextView3);
        komentar2 = findViewById(R.id.commentTextView);

        //slike
        beerImage1 = findViewById(R.id.imageViewBeers1);
        beerImage2 = findViewById(R.id.imageViewBeers2);
        eventImage1 = findViewById(R.id.imageViewEvent1);
        eventImage2 = findViewById(R.id.imageViewEvent2);
        //promoImage1 = findViewById(R.id.imageViewPromo1);
        //promoImage2 = findViewById(R.id.imageViewPromo2);

        beerName1.setText(BeerCatalog.BeerArrayList.get(position).getNaziv_proizvoda_1());
        beerName2.setText(BeerCatalog.BeerArrayList.get(position).getNaziv_proizvoda_2());
        beerTaste1.setText(BeerCatalog.BeerArrayList.get(position).getOkus_1());
        beerTaste2.setText(BeerCatalog.BeerArrayList.get(position).getOkus_2());
        beerLitres1.setText(BeerCatalog.BeerArrayList.get(position).getLitara_1());
        beerLitres2.setText(BeerCatalog.BeerArrayList.get(position).getLitara_2());
        eventTitle1.setText(BeerCatalog.BeerArrayList.get(position).getNaziv_dogadaja_1());
        eventTitle2.setText(BeerCatalog.BeerArrayList.get(position).getNaziv_dogadaja_2());
        eventDesc1.setText(BeerCatalog.BeerArrayList.get(position).getOpis_događaja_1());
        eventDesc2.setText(BeerCatalog.BeerArrayList.get(position).getOpis_događaja_2());
        menuTitle1.setText(BeerCatalog.BeerArrayList.get(position).getNaziv_menija_1());
        menuTitle2.setText(BeerCatalog.BeerArrayList.get(position).getNaziv_menija_2());
        menuDesc1.setText(BeerCatalog.BeerArrayList.get(position).getOpis_menija_1());
        menuDesc2.setText(BeerCatalog.BeerArrayList.get(position).getOpis_menija_2());
        ocjena1.setText(BeerCatalog.BeerArrayList.get(position).getOcjena_1());
        ocjena2.setText(BeerCatalog.BeerArrayList.get(position).getOcjena_2());
        komentar1.setText(BeerCatalog.BeerArrayList.get(position).getKomentar_1());
        komentar2.setText(BeerCatalog.BeerArrayList.get(position).getKomentar_2());

        String imageUri = BeerCatalog.BeerArrayList.get(position).getSlika();
        Picasso.with(this).load(imageUri).into(beerImage);

         */

    }
    public void dohvatiPodatke1(){

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        prikazPodatakaOPivovaryViewModelArrayList.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("piva");

                            if(success.equals("1")){

                                for (int i = 0; i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(1);

                                    String id = object.getString("id_proizvod");
                                    String naziv = object.getString("naziv_proizvoda");
                                    String okus = object.getString("okus");
                                    String litara = object.getString("litara");
                                    String slika = object.getString("slika");

                                    pivo = new PrikazPodatakaOPivovaryViewModel(naziv, "a", okus, "b", litara, "c", "d", "e", "s", "s", "s", "s", "s", "s", "3", "2", "1", "2as" );
                                    prikazPodatakaOPivovaryViewModelArrayList.add(pivo);
                                    adapter.notifyDataSetChanged();
                                }

                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PrikazPodatakaOPivovariActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}

