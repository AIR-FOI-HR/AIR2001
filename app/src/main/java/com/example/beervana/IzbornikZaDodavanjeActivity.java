package com.example.beervana;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.beervana.BeerMenu.AddBeers;
import com.example.beervana.EventMenu.AddEventActivity;
import com.example.beervana.TastingMenu.DodavanjeDegustacijskihMeniaActivity;

public class IzbornikZaDodavanjeActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izbornik_za_dodavanje);
        initToolbar();

        Button button = (Button) findViewById(R.id.actionAddBeer);
        button.setOnClickListener(v -> openAddBeers());

        Button button1 = (Button) findViewById(R.id.actionAddEvent);
        button1.setOnClickListener(v -> openAddEvents());
        /*
        Button button3 = (Button) findViewById(R.id.actionAddPromo);
        button3.setOnClickListener(v -> openAddPromo());
         */

        Button button4 = (Button) findViewById(R.id.actionAddTastingMenu);
        button4.setOnClickListener(v -> openAddTastingMenu());


    }

    public void openAddBeers(){
        Intent intent = new Intent(this, AddBeers.class);
        startActivity(intent);
    }



    public void openAddEvents(){
        Intent intent = new Intent(this, AddEventActivity.class);
        startActivity(intent);
    }

    /*
    public void openAddPromo(){
        Intent intent = new Intent(this, AddPromoActivity.class);
        startActivity(intent);
    }*/


    public void openAddTastingMenu(){
        Intent intent = new Intent(this, DodavanjeDegustacijskihMeniaActivity.class);
        startActivity(intent);
    }

}
