package com.example.modul1;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.androidbuts.multispinnerfilter.SingleSpinnerSearch;
import com.example.modul1.databinding.Modul1AddPromoFragmentBinding;
import com.example.webservice.DohvatPodataka;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class AddModul1Promos extends Fragment {

    Button potvrdi, odDatumPostavi, doDatumPostavi, odVrijemePostavi, doVrijemePostavi;
    SingleSpinnerSearch beerSpin;
    EditText opisPromocije,nazivPromocije;
    TextView odDatum, doDatum, odVrijeme, doVrijeme, errorOpisPromocije, errorNazivPromocije, errorOdDatum, errorDoDatum, errorOdVrijeme, errorDoVrijeme;
    Modul1AddPromoFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modul1_add_promo_fragment,container,false);
        binding = Modul1AddPromoFragmentBinding.inflate(getLayoutInflater());
        potvrdi = binding.btnDodajPromociju;
        odDatumPostavi = binding.btnOdaberiDatumOd;
        doDatumPostavi = binding.btnOdaberiDatumOd;
        odVrijemePostavi = binding.btnOdaberiVrijemeOd;
        doVrijemePostavi = binding.btnOdaberiVrijemeDo;
        opisPromocije = binding.unosOpisPromocije;
        nazivPromocije = binding.nazivPromocije;
        odDatum = binding.prikazDatumaOd;
        doDatum = binding.prikazDatumaDo;
        odVrijeme = binding.prikazVremenaOd;
        doVrijeme = binding.prikazVremenaDo;

        errorOpisPromocije = binding.errUnosOpisPromocije;
        errorNazivPromocije = binding.errNazivPromocije;
        errorOdDatum = binding.errUnosDatumOd;
        errorOdVrijeme = binding.errUnosVrijemeOd;
        errorDoDatum = binding.errUnosDatumDo;
        errorDoVrijeme = binding.errUnosVrijemeDo;

        beerSpin = binding.odabirPiva;

        retrieveData();





        return view;
    }

    private void retrieveData() {
        DohvatPodataka dohvatPodataka = new DohvatPodataka();
        String url;
        Map<String, String> params = new HashMap<>();
        if (getIntent().getExtras() == null) {
            url = "https://beervana2020.000webhostapp.com/test/dohvacanjePiva.php";
            params.put("id_lokacija", idLokacija);
        } else {
            url = "https://beervana2020.000webhostapp.com/test/GetTastingMenuInfo.php";
            Intent intet = getIntent();
            Bundle extra = intet.getExtras();
            params.put("id_menu", extra.getString("menuId"));
        }
        dohvatPodataka.setParametri(params);
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


}
