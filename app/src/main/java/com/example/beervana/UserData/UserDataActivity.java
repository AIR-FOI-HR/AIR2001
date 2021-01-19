package com.example.beervana.UserData;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.beervana.BaseActivity;
import com.example.beervana.BeerplacePage.ReviewsActivity;
import com.example.beervana.R;

public class UserDataActivity extends BaseActivity {
    private String id_korisnik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_data_activity);
        initToolbar();

        ImageView mImageView = (ImageView) findViewById(R.id.imageVieww14);
        mImageView.setOnClickListener(v -> openActivityUserSettings());

        ImageView mImageView1 = (ImageView) findViewById(R.id.imageVieww15);
        mImageView1.setOnClickListener(v -> openActivityUserStatistics());

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        //id_korisnik = extras.getString("id_korisnik", "20");
        id_korisnik = "20";
    }

    private void openActivityUserStatistics() {
        Intent intent = new Intent(this, UserActivity.class).putExtra("id_korisnik", id_korisnik);
        startActivity(intent);
    }

    private void openActivityUserSettings() {
        Intent intent = new Intent(this, UserActivity.class).putExtra("id_korisnik", id_korisnik);
        startActivity(intent);
    }
}