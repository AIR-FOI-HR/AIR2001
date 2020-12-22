package com.example.beervana;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beervana.BeerMenu.AddBeers;
import com.example.beervana.EventMenu.AddEventActivity;
import com.example.beervana.TastingMenu.DodavanjeDegustacijskihMeniaActivity;

public class AboutUsActivity extends AppCompatActivity{

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

        //OVAJ DIO DODATI ZA TOOLBAR
        ImageView mImageView = (ImageView)findViewById(R.id.settings_icon);
        mImageView.setOnClickListener(v -> openActivity3());

        //OVAJ DIO OTKOMENTIRATI KAD SE KREIRAJU AKTIVNOSTI
        /*
        ImageView mImageView = (ImageView)findViewById(R.id.user_icon);
        mImageView.setOnClickListener(v -> openActivity4());

        ImageView mImageView = (ImageView)findViewById(R.id.search_icon);
        mImageView.setOnClickListener(v -> openActivity5());
        */

        //
    }
    //I OVAJ DIO DODATI ZA TOOLBAR
    public void openActivity3(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    //OVAJ DIO OTKOMENTIRATI KAD SE KREIRAJU AKTIVNOSTI
    /*
    public void openActivity4(){
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }
    public void openActivity5(){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
     */

    //KRAJ
}