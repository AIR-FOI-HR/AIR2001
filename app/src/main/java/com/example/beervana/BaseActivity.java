package com.example.beervana;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beervana.GlavniIzbornik.GlavniIzbornikClient;
import com.example.beervana.GlavniIzbornik.GlavniIzbornikUser;
import com.example.beervana.Toolbar.SearchActivity;
import com.example.beervana.Toolbar.SettingsActivity;
import com.example.beervana.UserData.UserDataActivity;

public abstract class BaseActivity extends AppCompatActivity {
    EditText pretrazivanje;
    TextView naslovna;
    private SharedPreferences sp;
    int uloga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("login", MODE_PRIVATE);
        uloga = sp.getInt("id_uloga", 0);


    }

    public void initToolbar() {
        ImageView mImageView = findViewById(R.id.settings_icon);
        mImageView.setOnClickListener(v -> openActivity3());

        //OVAJ DIO OTKOMENTIRATI KAD SE KREIRAJU AKTIVNOSTI

        ImageView mImageView1 = findViewById(R.id.user_icon);
        mImageView1.setOnClickListener(v -> openActivity4());

        ImageView imageViewPretrazivanje = findViewById(R.id.search_icon);
        imageViewPretrazivanje.setOnClickListener(v -> openActivity5());
        pretrazivanje = findViewById(R.id.txtpretrazivanje);

        naslovna = findViewById(R.id.naslovna);
        if(uloga==1) {
            naslovna.setOnClickListener(v -> openNaslovnaKorisnik());
        }
        else{
            naslovna.setOnClickListener(v -> openNaslovnaClient());
        }
        //

    }

    private void openNaslovnaClient() {
        if(this.getClass()!= GlavniIzbornikClient.class) {
            finishAffinity();
            Intent intent = new Intent(this, GlavniIzbornikClient.class);
            startActivity(intent);
        }
    }

    public void openNaslovnaKorisnik() {
        if(this.getClass()!= GlavniIzbornikUser.class) {
            finishAffinity();
            Intent intent = new Intent(this, GlavniIzbornikUser.class);
            startActivity(intent);

        }
    }


    public void openActivity3() {
        if(this.getClass()!= SettingsActivity.class) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
    }


    public void openActivity4() {
        Intent intent = new Intent(this, UserDataActivity.class);
        startActivity(intent);
    }

    public void openActivity5() {
        if (!pretrazivanje.getText().toString().equals("") && !pretrazivanje.getText().toString().equals(" ")) {
            Intent intent = new Intent(this, SearchActivity.class).putExtra("search", pretrazivanje.getText().toString());
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "You have to enter something to search.", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    public void blockToolbar() {
        ImageView mImageView = findViewById(R.id.settings_icon);
        mImageView.setEnabled(false);

        ImageView mImageView1 = findViewById(R.id.user_icon);
        mImageView1.setEnabled(false);

        ImageView imageViewPretrazivanje = findViewById(R.id.search_icon);
        imageViewPretrazivanje.setEnabled(false);
        pretrazivanje = findViewById(R.id.txtpretrazivanje);
        pretrazivanje.setEnabled(false);

        naslovna = findViewById(R.id.naslovna);
        naslovna.setEnabled(false);



    }

    public void unBlockToolbar() {
        ImageView mImageView = findViewById(R.id.settings_icon);
        mImageView.setEnabled(true);

        ImageView mImageView1 = findViewById(R.id.user_icon);
        mImageView1.setEnabled(true);

        ImageView imageViewPretrazivanje = findViewById(R.id.search_icon);
        imageViewPretrazivanje.setEnabled(true);
        pretrazivanje = findViewById(R.id.txtpretrazivanje);
        pretrazivanje.setEnabled(true);

        naslovna = findViewById(R.id.naslovna);
        naslovna.setEnabled(true);
    }


}
