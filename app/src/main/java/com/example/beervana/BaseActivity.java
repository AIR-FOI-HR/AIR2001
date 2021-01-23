package com.example.beervana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.beervana.UserData.UserActivity;
import com.example.beervana.UserData.UserDataActivity;

import org.json.JSONException;

public abstract class BaseActivity extends AppCompatActivity {
    EditText pretrazivanje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void initToolbar()
    {
        ImageView mImageView = (ImageView)findViewById(R.id.settings_icon);
        mImageView.setOnClickListener(v -> openActivity3());

        //OVAJ DIO OTKOMENTIRATI KAD SE KREIRAJU AKTIVNOSTI

        ImageView mImageView1 = (ImageView)findViewById(R.id.user_icon);
        mImageView1.setOnClickListener(v -> openActivity4());

        ImageView imageViewPretrazivanje = (ImageView)findViewById(R.id.search_icon);
        imageViewPretrazivanje.setOnClickListener(v -> openActivity5());
        pretrazivanje = (EditText) findViewById(R.id.txtpretrazivanje);

        //

    }


    public void openActivity3(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    //OVAJ DIO OTKOMENTIRATI KAD SE KREIRAJU AKTIVNOSTI

    public void openActivity4(){
        Intent intent = new Intent(this, UserDataActivity.class);
        startActivity(intent);
    }
    public void openActivity5(){
        if(!pretrazivanje.getText().toString().equals("")&&!pretrazivanje.getText().toString().equals(" ")){
            Intent intent = new Intent(this, SearchActivity.class).putExtra("search",pretrazivanje.getText().toString());
            startActivity(intent);
        }else{
            Toast toast = Toast.makeText(getApplicationContext(),"You have to enter something to search.",Toast.LENGTH_LONG);
            toast.show();
        }

    }

    public void blockToolbar(){
        ImageView mImageView = (ImageView)findViewById(R.id.settings_icon);
        mImageView.setEnabled(false);

        ImageView mImageView1 = (ImageView)findViewById(R.id.user_icon);
        mImageView1.setEnabled(false);

        ImageView imageViewPretrazivanje = (ImageView)findViewById(R.id.search_icon);
        imageViewPretrazivanje.setEnabled(false);
        pretrazivanje = (EditText) findViewById(R.id.txtpretrazivanje);
        pretrazivanje.setEnabled(false);
    }
    public void unBlockToolbar(){
        ImageView mImageView = (ImageView)findViewById(R.id.settings_icon);
        mImageView.setEnabled(true);

        ImageView mImageView1 = (ImageView)findViewById(R.id.user_icon);
        mImageView1.setEnabled(true);

        ImageView imageViewPretrazivanje = (ImageView)findViewById(R.id.search_icon);
        imageViewPretrazivanje.setEnabled(true);
        pretrazivanje = (EditText) findViewById(R.id.txtpretrazivanje);
        pretrazivanje.setEnabled(true);
    }


}
