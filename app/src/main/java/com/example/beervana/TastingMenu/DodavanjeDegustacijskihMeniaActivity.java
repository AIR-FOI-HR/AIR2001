package com.example.beervana.TastingMenu;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;
import com.example.beervana.AddEventActivity;
import com.example.beervana.R;
import com.example.beervana.databinding.ActivityDodavanjeDegustacijskihMeniaBinding;
import com.example.webservice.DohvatPodataka;
import com.example.webservice.SlanjePodataka;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DodavanjeDegustacijskihMeniaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "DodavanjeDegustacijskihMeniaActivity";

    EditText menuName;
    TextView menuDuration;
    EditText menuDescription;
    ArrayList<String> beerList = new ArrayList<>();
    ArrayList<String> pickedBeers = new ArrayList<>();
    RequestQueue requestQueue;
    MultiSpinnerSearch beerSpin;
    ArrayAdapter<String> listaAdapter;
    final List<KeyPairBoolData> listArray = new ArrayList<>();
    private TastingMenuViewModel model;
    private Calendar calendar = Calendar.getInstance();
    ActivityDodavanjeDegustacijskihMeniaBinding binding;
    private DatePickerDialog.OnDateSetListener listenerZaDatumOd;
    String sendUrl = "https://beervana2020.000webhostapp.com/test/addMenu.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodavanje_degustacijskih_menia);
        binding = ActivityDodavanjeDegustacijskihMeniaBinding.inflate((getLayoutInflater()));
        View view = binding.getRoot();
        setContentView(view);
        model = new ViewModelProvider(this).get(TastingMenuViewModel.class);

        menuDescription = binding.tastingMenuDescription;
        beerSpin = binding.spinPivi;
        menuName = binding.tastingMenuName;
        menuDuration = binding.tastingMenuDuration;

        menuDescription.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (DegustacijskiMeniLogika.menuDescription(menuDescription.getText().toString())) {
                    model.setMenuDescription(menuDescription.getText().toString());
                    binding.descriptionTextView.setVisibility(View.GONE);
                } else {
                    binding.descriptionTextView.setVisibility(View.VISIBLE);
                }
            }
        });

        menuDuration.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (DegustacijskiMeniLogika.menuDuration(menuDuration.getText().toString())) {
                    model.setMenuDuration(menuDuration.getText().toString());
                    binding.durationTextView.setVisibility(View.GONE);
                } else {
                    binding.durationTextView.setVisibility(View.VISIBLE);
                }
            }
        });


        menuName.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (DegustacijskiMeniLogika.menuName(menuName.getText().toString())) {
                    model.setMenuName(menuName.getText().toString());
                    binding.nameTextView.setVisibility(View.GONE);
                } else {
                    binding.nameTextView.setVisibility(View.VISIBLE);

                }
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        beerSpin = findViewById(R.id.spinPivi);

        retriveData();


        beerSpin.setSearchEnabled(true);
        beerSpin.setSearchHint(getString(R.string.odaberite_pivu));
        beerSpin.setEmptyTitle("Not Data Found!");
        beerSpin.setShowSelectAllButton(true);
        beerSpin.setClearText("Close & Clear");
        beerSpin.setItems(listArray, items -> {
            if (!beerSpin.getSelectedIds().isEmpty()) {
                pickedBeers = new ArrayList<>();
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).isSelected()) {
                        pickedBeers.add(items.get(i).getName());
                    }
                }
                model.setBeers(pickedBeers);
                binding.spinTextView.setVisibility(View.GONE);
            } else {
                model.setBeers(null);
                binding.spinTextView.setVisibility(View.VISIBLE);
            }

        });

        binding.tastingMenuDurationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PokreniDijalogZaDatum(listenerZaDatumOd);
            }
        });
        listenerZaDatumOd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                model.setMenuDuration(year + "-" + month + "-" + dayOfMonth);
                menuDuration.setText(model.getMenuDuration());
            }
        };

        binding.acceptTastingMenu.setOnClickListener(v -> {

            //TODO promijenit statičke podatke s pravim
            if (model.ProvjeriSvePodatke()) {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_korisnik", "50");
                params.put("id_lokacija", "8");
                params.put("menuName", model.getMenuName());
                params.put("beersOnMenu", model.getBeers().toString().substring(1, model.getBeers().toString().length() - 1));
                params.put("duration", model.getMenuDuration());
                params.put("description", model.getMenuDescription());
                params.put("oldName",  getIntent().getExtras().toString());
                SlanjePodataka slanjePodataka = new SlanjePodataka(sendUrl);
                slanjePodataka.setParametri(params);
                slanjePodataka.sendData(getApplicationContext(), requestQueue);
                requestQueue.addRequestFinishedListener(request -> {
                    String odgovor = slanjePodataka.getOdgovor();
                    Toast toast = Toast.makeText(getApplicationContext(), odgovor, Toast.LENGTH_LONG);
                    toast.show();
                });
            }
        });


    }

    private void retriveData() {
        DohvatPodataka dohvatPodataka = new DohvatPodataka();
        String url;
        if (getIntent().getExtras() == null) {
            url = "https://beervana2020.000webhostapp.com/test/dohvacanjePiva.php";
        } else {
            url = "https://beervana2020.000webhostapp.com/test/GetTastingMenuInfo.php";
            Map<String, String> params = new HashMap<String, String>();
            Intent intet = getIntent();
            Bundle extra = intet.getExtras();
            params.put("tastingMenuName", extra.getString("com.example.beervana.TastingMenu.MESSAGE"));
            dohvatPodataka.setParametri(params);
        }
        dohvatPodataka.setSendUrl(url);
        dohvatPodataka.retrieveData(getApplicationContext(), requestQueue);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                if (odgovor != null) {
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = odgovor.getJSONArray("proizvod");
                        setBeers(jsonArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        System.out.println(odgovor);
                        JSONArray body = odgovor.getJSONArray("meni");
                        setView(body);
                        sendUrl = "https://beervana2020.000webhostapp.com/test/UpdateTastingMenu.php";
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void setView(JSONArray tastingMenu) throws JSONException {
        for (int i = 0; i < tastingMenu.length(); i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = tastingMenu.getJSONObject(i);
                menuName.setText(jsonObject.optString("tastingMenuName"));
                menuDescription.setText(jsonObject.getString("description"));
                menuDuration.setText(jsonObject.getString("duration"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void setBeers(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
                String naziv_proizvoda = jsonObject.optString("naziv_proizvoda");

                beerList.add(naziv_proizvoda);
                listaAdapter = new ArrayAdapter<>(DodavanjeDegustacijskihMeniaActivity.this,
                        android.R.layout.simple_spinner_item, beerList);
                listaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                beerSpin.setAdapter(listaAdapter);

                KeyPairBoolData h = new KeyPairBoolData();
                h.setId(i + 1);
                h.setName(beerList.get(i));
                listArray.add(h);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (view != null && view instanceof EditText) {
                Rect r = new Rect();
                view.getGlobalVisibleRect(r);
                int rawX = (int)ev.getRawX();
                int rawY = (int)ev.getRawY();
                if (!r.contains(rawX, rawY)) {
                    view.clearFocus();
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void PokreniDijalogZaDatum(DatePickerDialog.OnDateSetListener listener){
        int godina = calendar.get(Calendar.YEAR);
        int mjesec = calendar.get(Calendar.MONTH);
        int dan = calendar.get(calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog
                (DodavanjeDegustacijskihMeniaActivity.this,R.style.Theme_AppCompat_Light_Dialog_MinWidth,
                        listener,godina,mjesec,dan);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
        dialog.show();
    }
}