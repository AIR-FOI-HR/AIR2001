package com.example.beervana.BeerplacePage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BaseActivity;
import com.example.beervana.BeerMenu.Beer;
import com.example.beervana.BeerMenu.BeerLogic;
import com.example.beervana.EventMenu.AddEventActivity;
import com.example.beervana.EventMenu.PrikazZaEventPodatkeActivity;
import com.example.beervana.R;
import com.example.beervana.SettingsActivity;
import com.example.webservice.DohvatPodataka;
import com.example.webservice.SlanjePodataka;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReviewsActivity extends BaseActivity implements AdapterReview.onRecenzijaListener {

    private static final String url = "https://beervana2020.000webhostapp.com/test/fetchReviews.php";
    RecyclerView recyclerView;
    public ArrayList<Review> reviews;
    private RequestQueue requestQueue;
    private String id_lokacija;
    private String id_korisnik;
    private boolean is_user= false;
    View view;
    AdapterReview adapterReview;
    private String sendUrl = "https://beervana2020.000webhostapp.com/test/deleteReview.php";
    private RequestQueue requestQueueBrisanje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        initToolbar();

        view = findViewById(android.R.id.content).getRootView();
        reviews = new ArrayList<Review>();
        recyclerView = findViewById(R.id.reviewRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras.containsKey("id_lokacija")){
            id_lokacija = extras.getString("id_lokacija");
        } else if (extras.containsKey("id_korisnika")){
            id_korisnik = extras.getString("id_korisnika");
            is_user = true;
        }
        loadReviews();


    }

    private void loadReviews() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        ReviewsLogic reviewsLogic = new ReviewsLogic();
        DohvatPodataka dohvatPodataka = new DohvatPodataka();
        Map<String, String> params = new HashMap<String, String>();
        if(is_user){
            params.put("id_korisnik", id_korisnik);
            String urlKorisnik = "https://beervana2020.000webhostapp.com/test/fetchMyReviews.php";
            dohvatPodataka.setSendUrl(urlKorisnik);
        }
        else{
            params.put("id_lokacija", id_lokacija);
            dohvatPodataka.setSendUrl(url);
        }

        dohvatPodataka.setParametri(params);
        dohvatPodataka.retrieveData(getApplicationContext(),requestQueue);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request){
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                if(odgovor != null){
                    reviews.clear();
                    reviews.addAll(ReviewsLogic.parsiranjePodatakaReviewa(odgovor));
                    adapterReview = new AdapterReview(ReviewsActivity.this, reviews, ReviewsActivity.this);
                    recyclerView.setAdapter(adapterReview);
                    //adapter.notifyDataSetChanged();
                }
            }
        });
    }


    @Override
    public void onReviewClick(int position) {
        if(is_user){
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

            CharSequence[] dijalogStavke = {"Edit data ", "Delete data"};
            builder.setTitle("Komentar: ".concat(reviews.get(position).getDatum_i_vrijeme_recenzije()));
            builder.setItems(dijalogStavke, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int odabir) {
                    switch (odabir) {
                        case 0:
                            if(reviews.get(position).getId_lokacija()!=null){
                                startActivity(new Intent(getApplicationContext(), AddReviewsActivity.class).putExtra("id_lokacija", reviews.get(position).getId_lokacija())
                                        .putExtra("komentar", reviews.get(position).getKomentar())
                                        .putExtra("ocjena",reviews.get(position).getOcjena()).putExtra("id_recenzija",reviews.get(position).getId_recenzija()));
                            }else{
                                startActivity(new Intent(getApplicationContext(), AddReviewsActivity.class).putExtra("id_proizvod", reviews.get(position).getId_proizvod())
                                        .putExtra("komentar", reviews.get(position).getKomentar())
                                        .putExtra("ocjena",reviews.get(position).getOcjena()).putExtra("id_recenzija",reviews.get(position).getId_recenzija()));
                            }
                            break;
                        case 1:
                            DeleteReview(reviews.get(position).getId_recenzija(),position);
                            break;

                    }
                }
            });
            builder.create().show();
        }
    }

    private void DeleteReview(String idRecenzija, int pozicija) {
        requestQueueBrisanje = Volley.newRequestQueue(getApplicationContext());
        Map<String, String > params = new HashMap<String, String>();
        params.put("id_recenzija",idRecenzija);
        SlanjePodataka slanjePodataka = new SlanjePodataka(sendUrl);
        slanjePodataka.setParametri(params);
        slanjePodataka.sendData(getApplicationContext(), requestQueueBrisanje);
        requestQueueBrisanje.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                String odgovor = slanjePodataka.getOdgovor();
                if (odgovor.equals("Succesfully deleted your review!")) {
                    reviews.remove(pozicija);
                    adapterReview.notifyDataSetChanged();
                    Toast toast = Toast.makeText(getApplicationContext(), "Succesfully deleted your review!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
}