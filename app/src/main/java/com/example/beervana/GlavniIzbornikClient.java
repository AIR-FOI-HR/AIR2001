package com.example.beervana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GlavniIzbornikClient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gl_izbornik_client);

        Button button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(v -> openActivity2());
    }

    public void openActivity2(){
        Intent intent = new Intent(this, AddBeers.class);
        startActivity(intent);
    }
}