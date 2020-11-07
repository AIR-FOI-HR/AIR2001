package com.example.beervana;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.beervana.databinding.ActivityAddeventBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;


public class AddEventActivity extends AppCompatActivity {
    private ActivityAddeventBinding binding;
    private ImageView slikaDogadjaj;
    private EditText unosImedogadjaja;
    private EditText unosOpisaDogadaja;
    private TextView errSlika;
    private TextView errUnosImeDogadjaja;
    private TextView errOpisaDogadjaja;
    private TextView errUnosDatum;
    private TextView errUnosVrijeme;
    private TextView prikazDatuma;
    private TextView prikazVremena;

    private DatePickerDialog.OnDateSetListener listenerZaDatum;
    private TimePickerDialog.OnTimeSetListener listenerZaVrijeme;
    private Calendar calendar = Calendar.getInstance();
    private AddEventActivityViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddeventBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //final AddEventActivityViewModel viewModel = new ViewModelProvider(this).get(AddEventActivityViewModel.class);
        viewModel = new ViewModelProvider(this).get(AddEventActivityViewModel.class);

        prikazDatuma = binding.prikazDatuma;
        prikazVremena = binding.prikazVremena;
        slikaDogadjaj = binding.slikaDogadjaj;
        unosImedogadjaja = binding.unosImeDogadaja;
        unosOpisaDogadaja = binding.unosOpisDogadaja;
        errSlika = binding.errSlika;
        errUnosImeDogadjaja = binding.errUnosImeDogadaja;
        errOpisaDogadjaja = binding.errUnosOpisdogadaja;
        errUnosDatum = binding.errUnosDatum;
        errUnosVrijeme = binding.errUnosVrijeme;

        prikazDatuma.setText(viewModel.getPrikazDatuma());
        prikazVremena.setText(viewModel.getPrikazVremena());
        slikaDogadjaj.setImageURI(viewModel.getSlika());
        PostaviGreske();

        binding.btnDodajSliku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);
            }
        });

        binding.btnOdaberiDatum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int godina = calendar.get(Calendar.YEAR);
                int mjesec = calendar.get(Calendar.MONTH);
                int dan = calendar.get(calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog
                        (AddEventActivity.this,R.style.Theme_AppCompat_Light_Dialog_MinWidth,
                                listenerZaDatum,godina,mjesec,dan);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
                dialog.show();
            }
        });

        binding.btnOdaberiVrijeme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sat = calendar.get(Calendar.HOUR_OF_DAY);
                int minuta = calendar.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(AddEventActivity.this,R.style.Theme_AppCompat_DayNight_Dialog_MinWidth
                        ,listenerZaVrijeme,sat,minuta,true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.LTGRAY)));
                dialog.show();
            }
        });

        listenerZaDatum = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                viewModel.setPrikazDatuma(dayOfMonth +"/"+month+"/"+year);
                prikazDatuma.setText(viewModel.getPrikazDatuma());
            }
        };
        listenerZaVrijeme = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                viewModel.setPrikazVremena(hourOfDay+":"+minute);
                prikazVremena.setText(viewModel.getPrikazVremena());
            }
        };

        binding.btnDodajDogadaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setUnosImedogadjaja(unosImedogadjaja.getText().toString());
                viewModel.setUnosOpisaDogadaja(unosOpisaDogadaja.getText().toString());
                if(viewModel.ProvijeriSvePodatke()){

                }
                PostaviGreske();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_CANCELED){
            if (resultCode == RESULT_OK && data != null) {
                Uri selectedImage =  data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
                    byte [] byte_arr = stream.toByteArray();
                    String image_str = Base64.encodeToString(byte_arr, Base64.DEFAULT);
                    viewModel.setSlikaZaSlanje(image_str);
                } catch (IOException e) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Erorr file not found",Toast.LENGTH_LONG);
                    toast.show();
                    e.printStackTrace();
                }
                viewModel.setSlika(selectedImage);
                slikaDogadjaj.setImageURI(viewModel.getSlika());
            }else{
                Toast toast = Toast.makeText(getApplicationContext(),"Error when inserting image",Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }

    private void PostaviGreske(){
        errSlika.setText(viewModel.getErrSlika());
        errUnosImeDogadjaja.setText(viewModel.getErrUnosImeDogadjaja());
        errOpisaDogadjaja.setText(viewModel.getErrOpisaDogadjaja());
        errUnosDatum.setText(viewModel.getErrUnosDatum());
        errUnosVrijeme.setText(viewModel.getErrUnosVrijeme());

        errSlika.setVisibility(viewModel.getErrSlikaVisibility());
        errUnosImeDogadjaja.setVisibility(viewModel.getErrUnosImeDogadjajaVisibility());
        errOpisaDogadjaja.setVisibility(viewModel.getErrOpisaDogadjajaVisibility());
        errUnosDatum.setVisibility(viewModel.getErrUnosDatumVisibility());
        errUnosVrijeme.setVisibility(viewModel.getErrUnosVrijemeVisibility());
    }

}
