package com.example.beervana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.beervana.BeerMenu.BeerCatalogActivity;
import com.example.beervana.EventMenu.EventCatalogActivity;
import com.example.beervana.TastingMenu.TastingMenuActivity;

public class GlavniIzbornikClient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gl_izbornik_client);

        Button button = (Button) findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
        Button buttonBeerCatalog = (Button) findViewById(R.id.button2);
        buttonBeerCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivityBeerCatalog();
            }
        });
        Button buttonEventCatalog = (Button) findViewById(R.id.button6);
        buttonEventCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivityEventCatalog();
            }
        });
        Button buttonTastingMeni = (Button) findViewById(R.id.button3);
        buttonTastingMeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivityTastingMeni();
            }
        });
    }

    public void openActivity2(){
        Intent intent = new Intent(this, IzbornikZaDodavanjeActivity.class);
        startActivity(intent);
    }
    public void OpenActivityBeerCatalog(){
        Intent intent = new Intent(this, BeerCatalogActivity.class);
        startActivity(intent);
    }
    public void OpenActivityEventCatalog(){
        Intent intent = new Intent(this, EventCatalogActivity.class);
        startActivity(intent);
    }
    public void OpenActivityTastingMeni(){
        Intent intent = new Intent(this, TastingMenuActivity.class);
        startActivity(intent);
    }
}