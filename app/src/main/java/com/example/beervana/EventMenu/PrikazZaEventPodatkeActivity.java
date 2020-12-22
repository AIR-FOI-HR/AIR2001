package com.example.beervana.EventMenu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beervana.EventMenu.EventCatalogActivity;
import com.example.beervana.R;
import com.example.beervana.SettingsActivity;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

public class PrikazZaEventPodatkeActivity  extends AppCompatActivity {
    ImageView slikaDogadaja;
    TextView nazivDogadaja,nazivLokacije,opisDogadaja,datumOd,datumDo;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_za_event_podatke);

        nazivDogadaja = findViewById(R.id.prikazNazivDogadaja);
        nazivLokacije = findViewById(R.id.prikazNazivLokacije);
        opisDogadaja = findViewById(R.id.prikazOpisDogadaja);
        datumOd = findViewById(R.id.prikazDatumOd);
        datumDo = findViewById(R.id.prikazDatumDo);
        slikaDogadaja = findViewById(R.id.prikazSlikaDogadjaj);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        nazivDogadaja.setText(EventCatalogActivity.eventDataList.get(position).dogadaj.getNazivDogadaj());
        nazivLokacije.setText(EventCatalogActivity.eventDataList.get(position).getNazivLokacije()+", "+EventCatalogActivity.eventDataList.get(position).getAdresaLokacije());
        opisDogadaja.setText(EventCatalogActivity.eventDataList.get(position).dogadaj.getOpisDogadaja());
        datumOd.setText("Start: " + EventCatalogActivity.eventDataList.get(position).dogadaj.getDatumOd());
        datumDo.setText("End: "+ EventCatalogActivity.eventDataList.get(position).dogadaj.getDatumDo());

        String imageUri = EventCatalogActivity.eventDataList.get(position).dogadaj.getSlikaDogadaja();
        Picasso.with(this).load(imageUri).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE).into(slikaDogadaja);

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
