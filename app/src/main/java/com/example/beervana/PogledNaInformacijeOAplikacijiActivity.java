package com.example.beervana;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.beervana.BeerMenu.AddBeers;
import com.example.beervana.EventMenu.AddEventActivity;
import com.example.beervana.TastingMenu.DodavanjeDegustacijskihMeniaActivity;

public class PogledNaInformacijeOAplikacijiActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pogled_na_informacije_o_aplikaciji);

        TextView broj1 = findViewById(R.id.ContactUsBroj1TextView2);
        broj1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v ){
                String phone = broj1.getText().toString();
                String s = "tel:" + phone;
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(s));
                startActivity(intent);
            }
        });

        TextView broj2 = findViewById(R.id.ContactUsBroj2TextView2);
        broj2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v ){
                String phone = broj2.getText().toString();
                String str = "tel:" + phone;
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(str));
                startActivity(intent);
            }
        });
    }
}