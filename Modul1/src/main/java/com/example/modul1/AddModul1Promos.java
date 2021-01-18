package com.example.modul1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.SingleSpinnerListener;
import com.androidbuts.multispinnerfilter.SingleSpinnerSearch;
import com.example.modul1.databinding.Modul1AddPromoFragmentBinding;
import com.example.modulzamodule.AddPromotion1ViewModel;
import com.example.modulzamodule.TastingMenuViewModel;
import com.example.webservice.DohvatPodataka;
import com.example.webservice.SlanjePodataka;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


public class AddModul1Promos extends Fragment {
    int position;
    private DatePickerDialog.OnDateSetListener listenerZaDatumOd;
    private TimePickerDialog.OnTimeSetListener listenerZaVrijemeOd;
    private DatePickerDialog.OnDateSetListener listenerZaDatumDo;
    private TimePickerDialog.OnTimeSetListener listenerZaVrijemeDo;
    private Calendar calendar = Calendar.getInstance();
    Button potvrdi, odDatumPostavi, doDatumPostavi, odVrijemePostavi, doVrijemePostavi;
    SingleSpinnerSearch beerSpin;
    EditText opisPromocije,nazivPromocije,unosPopustPromocija;
    TextView odDatum, doDatum, odVrijeme, doVrijeme, errorOpisPromocije,
            errorNazivPromocije, errorOdDatum, errorDoDatum,
            errorOdVrijeme, errorDoVrijeme,errorOdabirProizvoda, errorUnosPopusta;
    Modul1AddPromoFragmentBinding binding;
    ArrayList<String> pickedBeers = new ArrayList<>();
    //TODO oi, check your default mate.
    private String idLokacija = "";
    private RequestQueue requestQueue;
    private ArrayList<String> beerList = new ArrayList<>();
    private ArrayAdapter<String> listaAdapter;
    final List<KeyPairBoolData> listArray =  new ArrayList<>();
    AddPromotion1ViewModel model;
    private String sendUrl = "https://beervana2020.000webhostapp.com/test/dodajPromocijuV1.php";
    private RequestQueue requestQueueSlanje;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modul1_add_promo_fragment,container,false);
        Intent intent = getActivity().getIntent();
        position = intent.getExtras().getInt("position");
        idLokacija = intent.getExtras().getString("id_lokacija");
        super.onCreate(savedInstanceState);
        binding = Modul1AddPromoFragmentBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        potvrdi = binding.btnDodajPromociju;
        odDatumPostavi = binding.btnOdaberiDatumOd;
        doDatumPostavi = binding.btnOdaberiDatumDo;
        odVrijemePostavi = binding.btnOdaberiVrijemeOd;
        doVrijemePostavi = binding.btnOdaberiVrijemeDo;
        opisPromocije = binding.unosOpisPromocije;
        nazivPromocije = binding.nazivPromocije;
        odDatum = binding.prikazDatumaOd;
        doDatum = binding.prikazDatumaDo;
        odVrijeme = binding.prikazVremenaOd;
        doVrijeme = binding.prikazVremenaDo;
        unosPopustPromocija = binding.unosPopustPromocija;

        errorOpisPromocije = binding.errUnosOpisPromocije;
        errorNazivPromocije = binding.errNazivPromocije;
        errorOdDatum = binding.errUnosDatumOd;
        errorOdVrijeme = binding.errUnosVrijemeOd;
        errorDoDatum = binding.errUnosDatumDo;
        errorDoVrijeme = binding.errUnosVrijemeDo;
        errorOdabirProizvoda = binding.errUnosProizvoda;
        errorUnosPopusta = binding.errUnosPopusta;

        beerSpin = binding.odabirPiva;

        model = new ViewModelProvider(this).get(AddPromotion1ViewModel.class);

        retrieveData();
        odDatum.setText(model.getPrikazDatumaOd());
        odVrijeme.setText(model.getPrikazVremenaOd());
        doDatum.setText(model.getPrikazDatumaDo());
        doVrijeme.setText(model.getPrikazVremenaDo());
        PostaviGreske();
        beerSpin.setSearchEnabled(true);
        beerSpin.setSearchHint("Choose a beer");
        beerSpin.setEmptyTitle("No Data Found!");
        beerSpin.setItems(listArray, new SingleSpinnerListener() {
            @Override
            public void onItemsSelected(KeyPairBoolData selectedItem) {
                model.setIdOdabranaPiva(String.valueOf(selectedItem.getId()));
            }

            @Override
            public void onClear() {

            }
        });
        odDatumPostavi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PokreniDijalogZaDatum(listenerZaDatumOd);
            }
        });

        odVrijemePostavi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PokreniDijalogZaVrijeme(listenerZaVrijemeOd);
            }
        });
        doDatumPostavi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PokreniDijalogZaDatum(listenerZaDatumDo);
            }
        });

        doVrijemePostavi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PokreniDijalogZaVrijeme(listenerZaVrijemeDo);
            }
        });
        listenerZaDatumOd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                model.setPrikazDatumaOd(dayOfMonth +"/"+month+"/"+year);
                odDatum.setText(model.getPrikazDatumaOd());
            }
        };
        listenerZaVrijemeOd = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                model.setPrikazVremenaOd(hourOfDay+":"+minute);
                odVrijeme.setText(model.getPrikazVremenaOd());
            }
        };
        listenerZaDatumDo = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                model.setPrikazDatumaDo(dayOfMonth +"/"+month+"/"+year);
                doDatum.setText(model.getPrikazDatumaDo());
            }
        };
        listenerZaVrijemeDo = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                model.setPrikazVremenaDo(hourOfDay+":"+minute);
                doVrijeme.setText(model.getPrikazVremenaDo());
            }
        };
        potvrdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setUnosOpisPromocije(opisPromocije.getText().toString());
                model.setUnosImePromocije(nazivPromocije.getText().toString());
                model.setUnosPopust(unosPopustPromocija.getText().toString());
                if(model.ProvijeriPodatke()){
                    Map<String, String> params=new HashMap<String, String>();
                    params.put("tip_promocije","PopustNaPivo");
                    params.put("id_lokacije",idLokacija);
                    params.put("naziv_promocije",model.getUnosImePromocije());
                    params.put("opis_promocije",model.getUnosOpisPromocije());
                    params.put("datum_pocetak",model.FormirajDatum(model.getPrikazDatumaOd(),model.getPrikazVremenaOd()));
                    params.put("datum_kraj",model.FormirajDatum(model.getPrikazDatumaDo(),model.getPrikazVremenaDo()));
                    params.put("json_string",model.KreirajJSONZaSlati());
                    requestQueueSlanje= Volley.newRequestQueue(getActivity());
                    SlanjePodataka slanjePodataka = new SlanjePodataka(sendUrl);
                    slanjePodataka.setParametri(params);
                    slanjePodataka.sendData(getActivity(),requestQueueSlanje);
                    requestQueueSlanje.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
                        @Override
                        public void onRequestFinished(Request<Object> request) {
                            String odgovor = slanjePodataka.getOdgovor();
                            Toast toast = Toast.makeText(getActivity(),odgovor,Toast.LENGTH_LONG);
                            toast.show();
                            if(odgovor.equals("Succesfully updated a promotion.")){

                            }
                            if(odgovor.equals("Succesfully added a promotion.")){
                                Toast.makeText(getActivity(),"Succesfully added a promotion.",Toast.LENGTH_LONG);
                                openMenuClient();
                            }
                        }
                    });
                }else{
                    PostaviGreske();
                }
            }
        });
        return view;
    }

    private void openMenuClient() {
            getActivity().onBackPressed();
    }

    private void PostaviGreske() {
        errorNazivPromocije.setText(model.getErrUnosImePromocije());
        errorOpisPromocije.setText(model.getErrOpisaPromocije());
        errorOdDatum.setText(model.getErrUnosDatumOd());
        errorOdVrijeme.setText(model.getErrUnosVrijemeOd());
        errorDoDatum.setText(model.getErrUnosDatumDo());
        errorDoVrijeme.setText(model.getErrUnosVrijemeDo());
        errorOdabirProizvoda.setText(model.getErrUnosProizvoda());
        errorUnosPopusta.setText(model.getErrUnosPopusta());

        errorNazivPromocije.setVisibility(model.getErrUnosImePromocijeVisibility());
        errorOpisPromocije.setVisibility(model.getErrOpisaPromocijeVisibility());
        errorOdDatum.setVisibility(model.getErrUnosDatumOdVisibility());
        errorOdVrijeme.setVisibility(model.getErrUnosVrijemeOdVisibility());
        errorDoDatum.setVisibility(model.getErrUnosDatumDoVisibility());
        errorDoVrijeme.setVisibility(model.getErrUnosVrijemeDoVisibility());
        errorOdabirProizvoda.setVisibility(model.getErrUnosProizvodaVisibility());
        errorUnosPopusta.setVisibility(model.getErrUnosPopustaVisibility());
    }

    private void retrieveData() {
        DohvatPodataka dohvatPodataka = new DohvatPodataka();
        String url;
        Map<String, String> params = new HashMap<>();
        url = "https://beervana2020.000webhostapp.com/test/dohvacanjePiva.php";
        params.put("id_lokacija", idLokacija);
        requestQueue = Volley.newRequestQueue(getActivity());
        dohvatPodataka.setParametri(params);
        dohvatPodataka.setSendUrl(url);
        dohvatPodataka.retrieveData(getActivity(), requestQueue);
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
                }
            }
        });
    }

    private void setBeers(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
                String naziv_proizvoda = jsonObject.optString("naziv_proizvoda");
                String id_proizvoda = jsonObject.optString("id_proizvod");
                beerList.add(id_proizvoda);
                listaAdapter = new ArrayAdapter<>(getActivity(),
                        android.R.layout.simple_spinner_item, beerList);
                listaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                beerSpin.setAdapter(listaAdapter);

                KeyPairBoolData h = new KeyPairBoolData();
                h.setId(Long.parseLong(id_proizvoda));
                h.setName(naziv_proizvoda);
                listArray.add(h);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void PokreniDijalogZaDatum(DatePickerDialog.OnDateSetListener listener){
        int godina = calendar.get(Calendar.YEAR);
        int mjesec = calendar.get(Calendar.MONTH);
        int dan = calendar.get(calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog
                (getActivity(), R.style.Theme_AppCompat_Light_Dialog_MinWidth,
                        listener,godina,mjesec,dan);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
        dialog.show();
    }

    private void PokreniDijalogZaVrijeme(TimePickerDialog.OnTimeSetListener listener){
        int sat = calendar.get(Calendar.HOUR_OF_DAY);
        int minuta = calendar.get(Calendar.MINUTE);

        TimePickerDialog dialog = new TimePickerDialog(getActivity(),R.style.Theme_AppCompat_DayNight_Dialog_MinWidth
                ,listener,sat,minuta,true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.LTGRAY)));
        dialog.show();
    }

}
