package com.example.beervana;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class IzbornikZaDodavanjeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izbornik_za_dodavanje);

        Button button = (Button) findViewById(R.id.actionAddBeer);
        button.setOnClickListener(v -> openAddBeers());
    }

    public void openAddBeers(){
        Intent intent = new Intent(this, AddBeers.class);
        startActivity(intent);
    }

}
