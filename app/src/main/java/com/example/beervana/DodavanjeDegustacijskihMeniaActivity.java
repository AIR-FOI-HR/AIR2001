package com.example.beervana;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.MultiSpinnerListener;
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;
import com.example.beervana.databinding.ActivityDodavanjeDegustacijskihMeniaBinding;
import com.example.webservice.SlanjePodataka;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DodavanjeDegustacijskihMeniaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private static final String TAG = "DodavanjeDegustacijskihMeniaActivity";

    EditText menuName;
    EditText menuDuration;
    ArrayList<String> beerList = new ArrayList<>();
    ArrayList<String> pickedBeers= new ArrayList<>();
    ImageView slika;
    RequestQueue requestQueue;
    MultiSpinnerSearch beerSpin;
    ArrayAdapter<String> listaAdapter;
    final List<KeyPairBoolData> listArray = new ArrayList<>();
    private DodavanjeDegustacijskihMeniaViewModel model;
    Uri imageUri;

    ActivityDodavanjeDegustacijskihMeniaBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodavanje_degustacijskih_menia);
        binding = ActivityDodavanjeDegustacijskihMeniaBinding.inflate((getLayoutInflater()));
        View view = binding.getRoot();
        setContentView(view);
        model = new ViewModelProvider(this).get(DodavanjeDegustacijskihMeniaViewModel.class);

        beerSpin = binding.spinPivi;
        menuName  = binding.tastingMenuName;
        menuDuration = binding.tastingMenuDuration;
        slika = binding.imageTastingMenu;
        slika.setImageURI(model.getSlika());

        menuDuration.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    if(DegustacijskiMeniLogika.menuDuration(menuDuration.getText().toString())) {
                        model.setMenuDuration(menuDuration.getText().toString());
                        binding.durationTextView.setVisibility(View.GONE);
                    }
                    else {
                        binding.durationTextView.setVisibility(View.VISIBLE);

                    }
                }
            }
        });



        menuName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    if(DegustacijskiMeniLogika.menuName(menuName.getText().toString())) {
                        model.setMenuName(menuName.getText().toString());
                        binding.nameTextView.setVisibility(View.GONE);
                    }
                    else{
                        binding.nameTextView.setVisibility(View.VISIBLE);

                    }
                }
            }
        });

        binding.imageTastingMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto,1);
            }
        });

        requestQueue = Volley.newRequestQueue(this);
        beerSpin = findViewById(R.id.spinPivi);
        String url = "https://beervana2020.000webhostapp.com/test/dohvacanjePiva.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, response -> {
            try {
                JSONArray jsonArray = response.getJSONArray("proizvod");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String naziv_kategorije = jsonObject.optString("naziv_proizvoda");

                    beerList.add(naziv_kategorije);
                    listaAdapter = new ArrayAdapter<>(DodavanjeDegustacijskihMeniaActivity.this,
                            android.R.layout.simple_spinner_item, beerList);
                    listaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    beerSpin.setAdapter(listaAdapter);

                        KeyPairBoolData h = new KeyPairBoolData();
                        h.setId(i + 1);
                        h.setName(beerList.get(i));
                        listArray.add(h);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {

        });
        requestQueue.add(jsonObjectRequest);


        beerSpin.setSearchEnabled(true);
        beerSpin.setSearchHint(getString(R.string.odaberite_pivu));
        beerSpin.setEmptyTitle("Not Data Found!");
        beerSpin.setShowSelectAllButton(true);
        beerSpin.setClearText("Close & Clear");
        beerSpin.setItems(listArray, new MultiSpinnerListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onItemsSelected(List<KeyPairBoolData> items) {
                    if(!beerSpin.getSelectedIds().isEmpty()) {
                        pickedBeers = new ArrayList<>();
                        for (int i = 0; i < items.size(); i++) {
                            if (items.get(i).isSelected()) {
                                pickedBeers.add(items.get(i).getName());
                            }
                        }
                        model.setBeers(pickedBeers);
                        binding.spinTextView.setVisibility(View.GONE);
                    }
                    else {
                        model.setBeers(null);
                        binding.spinTextView.setVisibility(View.VISIBLE);
                    }

                }
        });

        binding.acceptTastingMenu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            public void onClick(View v) {

                //TODO promijenit statičke podatke s pravim
                if(model.ProvjeriSvePodatke()){
                    requestQueue= Volley.newRequestQueue(getApplicationContext());
                    Map<String, String> params=new HashMap<String, String>();
                    params.put("id_korisnik","50");
                    params.put("id_lokacija","8");
                    params.put("slika",model.getSlikaZaSlanje());
                    params.put("menuName",model.getMenuName());
                    params.put("beersOnMenu", model.getBeers().toString().substring(1,model.getBeers().toString().length()-1));
                    params.put("duration", model.getMenuDuration());

                    String sendUrl="https://beervana2020.000webhostapp.com/test/addMenu.php";
                    SlanjePodataka slanjePodataka = new SlanjePodataka(sendUrl);
                    slanjePodataka.setParametri(params);
                    slanjePodataka.sendData(getApplicationContext(),requestQueue);
                    requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
                        @Override
                        public void onRequestFinished(Request<Object> request) {
                            String odgovor = slanjePodataka.getOdgovor();
                            Toast toast = Toast.makeText(getApplicationContext(),odgovor,Toast.LENGTH_LONG);
                            toast.show();
                        }
                    });
                }
            }
        });



    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode !=RESULT_CANCELED){
            if (resultCode == RESULT_OK && data != null) {
                Uri selectedImage =  data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 60, stream);
                    byte [] byte_arr = stream.toByteArray();
                    String image_str = Base64.encodeToString(byte_arr, Base64.DEFAULT);
                    model.setSlikaZaSlanje(image_str);
                } catch (IOException e) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Erorr file not found",Toast.LENGTH_LONG);
                    toast.show();
                    e.printStackTrace();
                }
                model.setSlika(selectedImage);
                slika.setImageURI(model.getSlika());
            }else{
                Toast toast = Toast.makeText(getApplicationContext(),"Error when inserting image",Toast.LENGTH_LONG);
                toast.show();
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
}