package com.example.beervana.Toolbar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.example.beervana.BaseActivity;
import com.example.beervana.R;

public class AboutUsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pogled_na_informacije_o_aplikaciji);
        initToolbar();

        TextView broj1 = findViewById(R.id.ContactUsBroj1TextView2);
        broj1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = broj1.getText().toString();
                String s = "tel:" + phone;
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(s));

                if (ActivityCompat.checkSelfPermission(AboutUsActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                startActivity(intent);
            }
        });

        TextView broj2 = findViewById(R.id.ContactUsBroj2TextView2);
        broj2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = broj2.getText().toString();
                String str = "tel:" + phone;
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(str));

                if (ActivityCompat.checkSelfPermission(AboutUsActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                startActivity(intent);
            }
        });


    }

}