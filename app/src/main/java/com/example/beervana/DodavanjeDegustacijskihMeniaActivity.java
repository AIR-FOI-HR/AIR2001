package com.example.beervana;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.MultiSpinnerListener;
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;
import com.example.webservice.SlanjePodataka;
import com.example.beervana.databinding.ActivityDodavanjeDegustacijskihMeniaBinding;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DodavanjeDegustacijskihMeniaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private static final String TAG = "DodavanjeDegustacijskihMeniaActivity";




    ActivityDodavanjeDegustacijskihMeniaBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dodavanje_degustacijskih_menia);
        MultiSpinnerSearch multiSelectSpinnerWithSearch = findViewById(R.id.spinPivi);
        final List<String> list = Arrays.asList(getResources().getStringArray(R.array.ListaPivi));

        final List<KeyPairBoolData> listArray = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            KeyPairBoolData h = new KeyPairBoolData();
            h.setId(i + 1);
            h.setName(list.get(i));
            listArray.add(h);
        }

        multiSelectSpinnerWithSearch.setSearchEnabled(true);
        multiSelectSpinnerWithSearch.setSearchHint("Select your beer");
        multiSelectSpinnerWithSearch.setEmptyTitle("Not Data Found!");
        multiSelectSpinnerWithSearch.setShowSelectAllButton(true);
        multiSelectSpinnerWithSearch.setClearText("Close & Clear");
        multiSelectSpinnerWithSearch.setItems(listArray, new MultiSpinnerListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onItemsSelected(List<KeyPairBoolData> items) {
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).isSelected()) {
                        Log.i(TAG, i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());
                    }
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}