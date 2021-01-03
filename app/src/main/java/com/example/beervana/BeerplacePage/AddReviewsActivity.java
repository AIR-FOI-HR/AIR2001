package com.example.beervana.BeerplacePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BaseActivity;
import com.example.beervana.R;
import com.example.beervana.databinding.AddReviewsActivityBinding;
import com.example.webservice.SlanjePodataka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddReviewsActivity extends BaseActivity {
    private String idLokacija;
    private String idProizvod;
    EditText komentar;
    RatingBar ocjena;
    TextView errOcjena, errKomentar;
    boolean isLokacija = true;
    private AddReviewsViewModel viewModel;

    ArrayList<String> lista = new ArrayList<>();
    ArrayAdapter<String> listaAdapter;
    RequestQueue requestQueue;

    String sendUrl = "https://beervana2020.000webhostapp.com/test/addReviews.php";

    AddReviewsActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reviews_activity);
        initToolbar();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras.containsKey("id_lokacija")){
            idLokacija = extras.getString("id_lokacija");
        } else{
            idProizvod = extras.getString("id_proizvod");
            isLokacija = false;
        }
        requestQueue = Volley.newRequestQueue(this);

        binding = AddReviewsActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        viewModel = new ViewModelProvider(this).get(AddReviewsViewModel.class);

        komentar = binding.recenzija;
        ocjena = binding.ratingBar;

        binding.ratingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //viewModel.setIdKorisnik(Integer.valueOf(idKorisnik.toString()));
                if(isLokacija)
                    viewModel.setIdLokacija(Integer.valueOf(idLokacija));
                else
                    viewModel.setIdProizvod(Integer.valueOf(idProizvod));
                viewModel.setOcjena(Double.valueOf(ocjena.getRating()));
                viewModel.setKomentar(komentar.getText().toString());
                if(viewModel.ProvjeriPodatke()) {
                    Map<String, String > params = new HashMap<String, String>();
                    /*
                    params.put("id_korisnik", String.valueOf(viewModel.getIdKorisnik()));
                    params.put("id_lokacija", String.valueOf(viewModel.getIdLokacija()));
                    params.put("ocjena", String.valueOf(viewModel.getOcjena()));
                    params.put("komentar", viewModel.getKomentar());
                    */
                    params.put("id_korisnik", "20");
                    if(isLokacija)
                        params.put("id_lokacija", String.valueOf(viewModel.getIdLokacija()));
                    else
                        params.put("id_proizvod", String.valueOf(viewModel.getIdProizvod()));
                    params.put("ocjena", String.valueOf(viewModel.getOcjena()));
                    params.put("komentar", viewModel.getKomentar());

                    SlanjePodataka slanjePodataka = new SlanjePodataka(sendUrl);
                    slanjePodataka.setParametri(params);
                    slanjePodataka.sendData(getApplicationContext(), requestQueue);

                    requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
                        @Override
                        public void onRequestFinished(Request<Object> request) {
                            String odgovor = slanjePodataka.getOdgovor();
                            if (odgovor.equals("Succesfully added your review!")) {
                                Toast toast = Toast.makeText(getApplicationContext(), "Succesfully added your review!", Toast.LENGTH_LONG);
                                toast.show();

                            }
                        }
                    });
                }
            }
        });
        
    }
}