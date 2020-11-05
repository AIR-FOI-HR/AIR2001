package com.example.beervana;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beervana.databinding.ActivityAddeventBinding;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddeventBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
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


        binding.btnOdaberiDatum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
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
        listenerZaDatum = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                prikazDatuma.setText(dayOfMonth +"/"+month+"/"+year);
            }
        };

    }


}
