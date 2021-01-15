package com.example.beervana.BeerMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.beervana.R;
import com.example.beervana.SettingsActivity;

import com.example.beervana.databinding.AddBeersActivityBinding;
import com.example.modulzamodule.AddBeersViewModel;
import com.example.webservice.SlanjePodataka;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddBeers extends AppCompatActivity {

    ImageView slika;
    EditText naziv_proizvoda;
    EditText cijena_proizvoda;
    EditText okus;
    EditText kolicina;

    TextView errSlika;
    TextView errNaziv;
    TextView errOkus;
    TextView errCijena;
    TextView errLitra;

    private AddBeersViewModel viewModel;

    Spinner spinner1;
    ArrayList<String> lista = new ArrayList<>();
    ArrayAdapter<String> listaAdapter;
    RequestQueue requestQueue;
    String pozicija;
    Integer pozicija_int;

    Button podnesi;
    String sendUrl = "https://beervana2020.000webhostapp.com/test/addBeers.php";
    SharedPreferences sp;
    String idLokacija;

    AddBeersActivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_beers_activity);
        binding = AddBeersActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        viewModel=new ViewModelProvider(this).get(AddBeersViewModel.class);
        sp = getSharedPreferences("login", MODE_PRIVATE);
        idLokacija = sp.getString("id_lokacija", "Nema Lokacija").split(",")[0];
        naziv_proizvoda=binding.editTextTextPersonName;
        cijena_proizvoda= binding.editTextNumberDecimal;
        okus = binding.editTextTextPersonName3;
        kolicina = binding.editTextTextPersonName4;
        slika = binding.imageView11;
        slika.setImageURI(viewModel.getSlika());
        //final AddBeersViewModel viewModel = new ViewModelProvider(this).get(AddBeersViewModel.class);

        //PostaviGreske();


        binding.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto,1);
            }
        });

        requestQueue = Volley.newRequestQueue(this);
        spinner1 = findViewById(R.id.spinner1);
        String url = "https://beervana2020.000webhostapp.com/test/populateBeerType.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, response -> {
            try {
                JSONArray jsonArray = response.getJSONArray("kategorija");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String naziv_kategorije = jsonObject.optString("naziv_kategorije");
                    lista.add(naziv_kategorije);
                    listaAdapter = new ArrayAdapter<>(AddBeers.this,
                            android.R.layout.simple_spinner_item, lista);
                    listaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner1.setAdapter(listaAdapter);
                    //pozicija = spinner1.getSelectedItemPosition();

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {

        });
        requestQueue.add(jsonObjectRequest);

        binding.floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setNazivPiva(naziv_proizvoda.getText().toString());
                viewModel.setCijenaPiva(Double.parseDouble(cijena_proizvoda.getText().toString()));
                viewModel.setLitraPiva(Double.parseDouble(kolicina.getText().toString()));
                viewModel.setOkusPiva(okus.getText().toString());
                if(viewModel.ProvjeriPodatke()) {
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    pozicija = (String) spinner1.getSelectedItem();
                    if (pozicija.equals("Svijetla")) {
                        pozicija_int = 1;
                    } else if (pozicija.equals("Crvena")) {
                        pozicija_int = 2;
                    } else if (pozicija.equals("Tamna")) {
                        pozicija_int = 3;
                    } else if (pozicija.equals("Crna")) {
                        pozicija_int = 4;
                    }


                    Map<String, String> params = new HashMap<String, String>();
                    params.put("id_lokacija",idLokacija);
                    params.put("naziv_proizvoda", viewModel.getNazivPiva());
                    params.put("cijena_proizvoda", String.valueOf(viewModel.getCijenaPiva()));
                    params.put("vrsta_proizvoda", viewModel.getOkusPiva());
                    params.put("kolicina_proizvoda", String.valueOf(viewModel.getLitraPiva()));
                    params.put("id_kategorija", pozicija_int.toString());
                    params.put("slika",viewModel.getSlikaZaSlanje());

                    SlanjePodataka slanjePodataka = new SlanjePodataka(sendUrl);
                    slanjePodataka.setParametri(params);
                    slanjePodataka.sendData(getApplicationContext(), requestQueue);

                    requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
                        @Override
                        public void onRequestFinished(Request<Object> request) {
                            String odgovor = slanjePodataka.getOdgovor();
                            if (odgovor.equals("Succesfully added a beer")) {
                                Toast toast = Toast.makeText(getApplicationContext(), "Successfully added a beer! ", Toast.LENGTH_LONG);
                                toast.show();

                            }
                        }
                    });
                }
                //PostaviGreske();
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
                    viewModel.setSlikaZaSlanje(image_str);
                } catch (IOException e) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Erorr file not found",Toast.LENGTH_LONG);
                    toast.show();
                    e.printStackTrace();
                }
                viewModel.setSlika(selectedImage);
                slika.setImageURI(viewModel.getSlika());
            }else{
                Toast toast = Toast.makeText(getApplicationContext(),"Error when inserting image",Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }

    private void PostaviGreske() {
        errSlika.setText(viewModel.getErrSlika());
        errCijena.setText(viewModel.getErrCijena());
        errLitra.setText(viewModel.getErrLitra());
        errNaziv.setText(viewModel.getErrNaziv());
        errOkus.setText(viewModel.getErrOkus());

        errSlika.setVisibility(viewModel.getErrSlikaVisibility());
        errOkus.setVisibility(viewModel.getErrUnosokusaPivaVisibility());
        errNaziv.setVisibility(viewModel.getErrUnosNazivaPivaVisibility());
        errLitra.setVisibility(viewModel.getErrUnosLitreVisibility());
        errCijena.setVisibility(viewModel.getErrUnosCijenePivaVisibility());
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