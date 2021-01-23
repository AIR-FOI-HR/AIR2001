package com.example.beervana.EventMenu;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.beervana.BaseActivity;
import com.example.beervana.GlavniIzbornikClient;
import com.example.beervana.R;
import com.example.beervana.databinding.ActivityAddeventBinding;
import com.example.modulzamodule.AddEventActivityViewModel;
import com.example.webservice.SlanjePodataka;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class AddEventActivity extends BaseActivity {
    private String sendUrl;
    private RequestQueue requestQueue;
    private ActivityAddeventBinding binding;
    private ImageView slikaDogadjaj;
    private EditText unosImedogadjaja;
    private EditText unosOpisaDogadaja;
    private TextView errSlika;
    private TextView errUnosImeDogadjaja;
    private TextView errOpisaDogadjaja;
    private TextView errUnosDatumOd;
    private TextView errUnosVrijemeOd;
    private TextView errUnosDatumDo;
    private TextView errUnosVrijemeDo;
    private TextView prikazDatumaOd;
    private TextView prikazVremenaOd;
    private TextView prikazDatumaDo;
    private TextView prikazVremenaDo;

    private DatePickerDialog.OnDateSetListener listenerZaDatumOd;
    private TimePickerDialog.OnTimeSetListener listenerZaVrijemeOd;
    private DatePickerDialog.OnDateSetListener listenerZaDatumDo;
    private TimePickerDialog.OnTimeSetListener listenerZaVrijemeDo;
    private final Calendar calendar = Calendar.getInstance();
    private AddEventActivityViewModel viewModel;
    int position;
    private String idKorisnik;
    private String idLokacija;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddeventBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initToolbar();

        sp = getSharedPreferences("login", MODE_PRIVATE);
        idLokacija = sp.getString("id_lokacija", "Nema Lokacija").split(",")[0];
        idKorisnik = Integer.toString(sp.getInt("id_korisnik", 0));
        //final AddEventActivityViewModel viewModel = new ViewModelProvider(this).get(AddEventActivityViewModel.class);
        viewModel = new ViewModelProvider(this).get(AddEventActivityViewModel.class);

        prikazDatumaOd = binding.prikazDatumaOd;
        prikazVremenaOd = binding.prikazVremenaOd;
        prikazDatumaDo = binding.prikazDatumaDo;
        prikazVremenaDo = binding.prikazVremenaDo;
        slikaDogadjaj = binding.slikaDogadjaj;
        unosImedogadjaja = binding.unosImeDogadaja;
        unosOpisaDogadaja = binding.unosOpisDogadaja;
        errSlika = binding.errSlika;
        errUnosImeDogadjaja = binding.errUnosImeDogadaja;
        errOpisaDogadjaja = binding.errUnosOpisdogadaja;
        errUnosDatumOd = binding.errUnosDatumOd;
        errUnosVrijemeOd = binding.errUnosVrijemeOd;
        errUnosDatumDo = binding.errUnosDatumDo;
        errUnosVrijemeDo = binding.errUnosVrijemeDo;

        if (!viewModel.isAzuriraj()) {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            if (extras != null) {
                position = extras.getInt("position");
                viewModel.setAzuriraj(true);
                PostaviPodatkeUViewModel(position);
                unosImedogadjaja.setText(viewModel.getUnosImedogadjaja());
                unosOpisaDogadaja.setText(viewModel.getUnosOpisaDogadaja());

            }
        }


        prikazDatumaOd.setText(viewModel.getPrikazDatumaOd());
        prikazVremenaOd.setText(viewModel.getPrikazVremenaOd());
        prikazDatumaDo.setText(viewModel.getPrikazDatumaDo());
        prikazVremenaDo.setText(viewModel.getPrikazVremenaDo());
        Picasso.with(this).load(viewModel.getSlika()).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(slikaDogadjaj);


        PostaviGreske();

        binding.btnDodajSliku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);
            }
        });

        binding.btnOdaberiDatumOd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PokreniDijalogZaDatum(listenerZaDatumOd);
            }
        });

        binding.btnOdaberiVrijemeOd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PokreniDijalogZaVrijeme(listenerZaVrijemeOd);
            }
        });
        binding.btnOdaberiDatumDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PokreniDijalogZaDatum(listenerZaDatumDo);
            }
        });

        binding.btnOdaberiVrijemeDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PokreniDijalogZaVrijeme(listenerZaVrijemeDo);
            }
        });


        listenerZaDatumOd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                viewModel.setPrikazDatumaOd(dayOfMonth + "/" + month + "/" + year);
                prikazDatumaOd.setText(viewModel.getPrikazDatumaOd());
            }
        };
        listenerZaVrijemeOd = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                viewModel.setPrikazVremenaOd(hourOfDay + ":" + minute);
                prikazVremenaOd.setText(viewModel.getPrikazVremenaOd());
            }
        };
        listenerZaDatumDo = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                viewModel.setPrikazDatumaDo(dayOfMonth + "/" + month + "/" + year);
                prikazDatumaDo.setText(viewModel.getPrikazDatumaDo());
            }
        };
        listenerZaVrijemeDo = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                viewModel.setPrikazVremenaDo(hourOfDay + ":" + minute);
                prikazVremenaDo.setText(viewModel.getPrikazVremenaDo());
            }
        };

        binding.btnDodajDogadaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setUnosImedogadjaja(unosImedogadjaja.getText().toString());
                viewModel.setUnosOpisaDogadaja(unosOpisaDogadaja.getText().toString());
                //TODO promijenit statičke podatke s pravim
                if (viewModel.ProvijeriSvePodatke()) {
                    boolean mozeSlanje = false;
                    Map<String, String> params = new HashMap<String, String>();
                    if (viewModel.isAzuriraj()) {
                        if (viewModel.DosloDoPromjene()) {
                            mozeSlanje = true;
                            params.put("id_dogadaj", viewModel.getId_dogadaj());
                            params.put("id_lokacija", idLokacija);
                            params.put("naziv_dogadjaja", viewModel.getUnosImedogadjaja());
                            params.put("opis_dogadjaja", viewModel.getUnosOpisaDogadaja());
                            params.put("datum_pocetak", viewModel.FormirajDatum(viewModel.getPrikazDatumaOd(), viewModel.getPrikazVremenaOd()));
                            params.put("datum_kraj", viewModel.FormirajDatum(viewModel.getPrikazDatumaDo(), viewModel.getPrikazVremenaDo()));
                            if (viewModel.getSlikaZaSlanje() != "") {
                                params.put("slika", viewModel.getSlikaZaSlanje());
                            }
                            sendUrl = "https://beervana2020.000webhostapp.com/test/AzurirajDogadaj.php";
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "You didn't make any changes", Toast.LENGTH_LONG);
                            toast.show();
                            openBeerCatalog();
                        }

                    } else {
                        mozeSlanje = true;
                        params.put("id_korisnik", idKorisnik);
                        params.put("id_lokacija", idLokacija);
                        params.put("slika", viewModel.getSlikaZaSlanje());
                        params.put("naziv_dogadjaja", viewModel.getUnosImedogadjaja());
                        params.put("opis_dogadjaja", viewModel.getUnosOpisaDogadaja());
                        params.put("datum_pocetak", viewModel.FormirajDatum(viewModel.getPrikazDatumaOd(), viewModel.getPrikazVremenaOd()));
                        params.put("datum_kraj", viewModel.FormirajDatum(viewModel.getPrikazDatumaDo(), viewModel.getPrikazVremenaDo()));
                        sendUrl = "https://beervana2020.000webhostapp.com/test/dodajDogadjajV2.php";
                    }
                    if (mozeSlanje) {
                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                        SlanjePodataka slanjePodataka = new SlanjePodataka(sendUrl);
                        slanjePodataka.setParametri(params);
                        slanjePodataka.sendData(getApplicationContext(), requestQueue);
                        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
                            @Override
                            public void onRequestFinished(Request<Object> request) {
                                String odgovor = slanjePodataka.getOdgovor();
                                Toast toast = Toast.makeText(getApplicationContext(), odgovor, Toast.LENGTH_LONG);
                                toast.show();
                                if (odgovor.equals("Succesfully updated an event.")) {
                                    openBeerCatalog();
                                }
                                if (odgovor.equals("Succesfully added an event")) {
                                    openMenuClient();
                                }
                            }
                        });
                    }

                }
                PostaviGreske();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            if (resultCode == RESULT_OK && data != null) {
                Uri selectedImage = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 60, stream);
                    byte[] byte_arr = stream.toByteArray();
                    String image_str = Base64.encodeToString(byte_arr, Base64.DEFAULT);
                    viewModel.setSlikaZaSlanje(image_str);
                } catch (IOException e) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Erorr file not found", Toast.LENGTH_LONG);
                    toast.show();
                    e.printStackTrace();
                }
                viewModel.setSlika(selectedImage);
                Picasso.with(this).load(viewModel.getSlika()).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(slikaDogadjaj);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Error when inserting image", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }

    private void PostaviGreske() {
        errSlika.setText(viewModel.getErrSlika());
        errUnosImeDogadjaja.setText(viewModel.getErrUnosImeDogadjaja());
        errOpisaDogadjaja.setText(viewModel.getErrOpisaDogadjaja());
        errUnosDatumOd.setText(viewModel.getErrUnosDatumOd());
        errUnosVrijemeOd.setText(viewModel.getErrUnosVrijemeOd());
        errUnosDatumDo.setText(viewModel.getErrUnosDatumDo());
        errUnosVrijemeDo.setText(viewModel.getErrUnosVrijemeDo());

        errSlika.setVisibility(viewModel.getErrSlikaVisibility());
        errUnosImeDogadjaja.setVisibility(viewModel.getErrUnosImeDogadjajaVisibility());
        errOpisaDogadjaja.setVisibility(viewModel.getErrOpisaDogadjajaVisibility());
        errUnosDatumOd.setVisibility(viewModel.getErrUnosDatumOdVisibility());
        errUnosVrijemeOd.setVisibility(viewModel.getErrUnosVrijemeOdVisibility());
        errUnosDatumDo.setVisibility(viewModel.getErrUnosDatumDoVisibility());
        errUnosVrijemeDo.setVisibility(viewModel.getErrUnosVrijemeDoVisibility());
    }

    private void PokreniDijalogZaDatum(DatePickerDialog.OnDateSetListener listener) {
        int godina = calendar.get(Calendar.YEAR);
        int mjesec = calendar.get(Calendar.MONTH);
        int dan = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog
                (AddEventActivity.this, R.style.Theme_AppCompat_Light_Dialog_MinWidth,
                        listener, godina, mjesec, dan);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
        dialog.show();
    }

    private void PokreniDijalogZaVrijeme(TimePickerDialog.OnTimeSetListener listener) {
        int sat = calendar.get(Calendar.HOUR_OF_DAY);
        int minuta = calendar.get(Calendar.MINUTE);

        TimePickerDialog dialog = new TimePickerDialog(AddEventActivity.this, R.style.Theme_AppCompat_DayNight_Dialog_MinWidth
                , listener, sat, minuta, true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.LTGRAY)));
        dialog.show();
    }

    private void PostaviPodatkeUViewModel(int position) {
        viewModel.podaciPrijeAzuriranja = EventCatalogActivity.eventDataList.get(position);
        viewModel.setId_dogadaj(EventCatalogActivity.eventDataList.get(position).dogadaj.getIdDogadaj());
        viewModel.setUnosOpisaDogadaja(EventCatalogActivity.eventDataList.get(position).dogadaj.getOpisDogadaja());
        viewModel.setUnosImedogadjaja(EventCatalogActivity.eventDataList.get(position).dogadaj.getNazivDogadaj());
        viewModel.setPrikazDatumaOd(EventCatalogActivity.eventDataList.get(position).dogadaj.getDatumOd().split(" ")[0]);
        viewModel.setPrikazDatumaDo(EventCatalogActivity.eventDataList.get(position).dogadaj.getDatumDo().split(" ")[0]);
        viewModel.setPrikazVremenaOd(EventCatalogActivity.eventDataList.get(position).dogadaj.getDatumOd().split(" ")[1]);
        viewModel.setPrikazVremenaDo(EventCatalogActivity.eventDataList.get(position).dogadaj.getDatumDo().split(" ")[1]);
        Uri imageUri = Uri.parse(EventCatalogActivity.eventDataList.get(position).dogadaj.getSlikaDogadaja());
        viewModel.setSlika(imageUri);
    }

    public void openBeerCatalog() {
        Intent intent = new Intent(this, EventCatalogActivity.class);
        startActivity(intent);
    }

    public void openMenuClient() {
        Intent intent = new Intent(this, GlavniIzbornikClient.class);
        startActivity(intent);
    }

}
