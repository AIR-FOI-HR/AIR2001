package com.example.beervana.BeerMenu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BaseActivity;
import com.example.beervana.BeerMenu.BeerCatalogActivity;
import com.example.beervana.R;
import com.example.beervana.SettingsActivity;
import com.example.webservice.SlanjePodataka;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class PrikazZaPodatkeOPivuActivity extends BaseActivity {

    TextView beerName1, beerPrice, beerTaste,beerLitres;
    ImageView beerImage;
    String beerId;
    int position;
    private ImageView addToFavorites;
    private boolean favorite;
    SlanjePodataka slanjePodataka;
    private String CheckFavoriteBeer = "https://beervana2020.000webhostapp.com/test/isFavoriteBeer.php";;
    private String AddToFavoritesUrl = "https://beervana2020.000webhostapp.com/test/addFavoriteBeer.php";
    private String RemoveFromFavoritesUrl = "https://beervana2020.000webhostapp.com/test/removeFavoriteBeer.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_za_podatke_o_pivu);
        initToolbar();

        beerName1 = findViewById(R.id.beerNameTextView1);
        beerPrice = findViewById(R.id.textView19);
        beerLitres = findViewById(R.id.alcoholPercentageTextView);
        beerImage = findViewById(R.id.beerImage);
        beerTaste = findViewById(R.id.descriptionTextView);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        if(intent.getExtras().getInt("position")!=0){
            beerName1.setText("Beer name: " + BeerCatalogActivity.BeerArrayList.get(position).getNaziv_proizvoda());
            beerPrice.setText("Beer price: " + BeerCatalogActivity.BeerArrayList.get(position).getCijena_proizvoda());
            beerTaste.setText("Beer taste: "+ BeerCatalogActivity.BeerArrayList.get(position).getOkus());
            beerLitres.setText("Beer litres: " + BeerCatalogActivity.BeerArrayList.get(position).getLitara());
            String imageUri = BeerCatalogActivity.BeerArrayList.get(position).getSlika();
            Picasso.with(this).load(imageUri).into(beerImage);
            beerId = BeerCatalogActivity.BeerArrayList.get(position).getId_proizvod();
        }
        else{
            beerName1.setText("Beer name: " + intent.getExtras().getString("naziv_proizvoda"));
            beerPrice.setText("Beer price: " + intent.getExtras().getString("cijena_proizvoda"));
            beerTaste.setText("Beer taste: "+ intent.getExtras().getString("okus"));
            beerLitres.setText("Beer litres: " + intent.getExtras().getString("litara"));
            String imageUri = intent.getExtras().getString("slika_proizvoda");
            Picasso.with(this).load(imageUri).into(beerImage);
            beerId = intent.getExtras().getString("id_proizvod");
        }
        addToFavorites = (ImageView)findViewById(R.id.addToFavorites);
        addToFavorites.setOnClickListener(v->AddToFavoriteLocations());
        CheckIfFavoriteBeer();
    }

    private void CheckIfFavoriteBeer() {
        slanjePodataka = new SlanjePodataka(CheckFavoriteBeer);
        RequestQueue requestQueueCheck = Volley.newRequestQueue(getApplicationContext());
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_proizvod",beerId);
        params.put("id_korisnik","20");
        slanjePodataka.setParametri(params);
        slanjePodataka.sendData(this,requestQueueCheck);

        requestQueueCheck.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                String odgovor = slanjePodataka.getOdgovor();
                if (odgovor.equals("This is a favorite beer")){
                    addToFavorites.setImageResource(R.drawable.removefromfavorites);
                    favorite = true;
                }
                else if(odgovor.equals("This is not a favorite beer")){
                    addToFavorites.setImageResource(R.drawable.addtofavorites);
                    favorite = false;
                }
            }
        });
    }

    private void AddToFavoriteLocations() {
        if(!favorite){
            slanjePodataka = new SlanjePodataka(AddToFavoritesUrl);
        }
        else{
            slanjePodataka = new SlanjePodataka(RemoveFromFavoritesUrl);
        }

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_proizvod",beerId);
        params.put("id_korisnik","20");
        slanjePodataka.setParametri(params);
        slanjePodataka.sendData(this,requestQueue);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                String odgovor = slanjePodataka.getOdgovor();
                if (odgovor.equals("Succesfully added favorite beer")) {
                    favorite = true;
                    addToFavorites.setImageResource(R.drawable.removefromfavorites);
                    Toast toast = Toast.makeText(getApplicationContext(), "Successfully added favorite beer! ", Toast.LENGTH_LONG);
                    toast.show();
                }
                else if (odgovor.equals("Successfully deleted a favorite beer")) {
                    favorite = false;
                    addToFavorites.setImageResource(R.drawable.addtofavorites);
                    Toast toast = Toast.makeText(getApplicationContext(), "Successfully deleted favorite beer! ", Toast.LENGTH_LONG);
                    toast.show();
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), odgovor, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
}
