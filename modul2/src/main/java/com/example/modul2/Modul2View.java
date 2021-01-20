package com.example.modul2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.modul2.databinding.Modul2ViewPromosFragmentBinding;
import com.example.modulzamodule.Model1ViewViewModel;
import com.example.modulzamodule.Model2ViewViewModel;
import com.example.webservice.DohvatPodataka;
import com.example.webservice.SlanjePodataka;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Modul2View extends Fragment {
    String sendUrl = "https://beervana2020.000webhostapp.com/test/GetPromotionAndIfUsed.php";
    String iskoristiUrl = "https://beervana2020.000webhostapp.com/test/IskoristiPromociju.php";
    Modul2ViewPromosFragmentBinding binding;
    TextView nazivPromocije,opisPromocije,proizvodNaziv,kolicina,gratis,datumOd,datumDo;
    Button iskoristiPromociju;
    RequestQueue requestQueue;
    RequestQueue requestQueueSlanje;
    DohvatPodataka dohvatPodataka;
    SlanjePodataka slanjePodataka;
    Model2ViewViewModel model;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modul2_view_promos_fragment, container, false);
        binding = Modul2ViewPromosFragmentBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        Intent intent = getActivity().getIntent();
        model = new ViewModelProvider(this).get(Model2ViewViewModel.class);
        model.setIdKorisnik(intent.getExtras().getString("id_korisnik"));
        model.setIdPromocija(intent.getExtras().getString("id_promocija"));
        nazivPromocije = binding.txtNazivPromocije;
        opisPromocije = binding.txtOpisPromocije;
        proizvodNaziv = binding.txtProizvod;
        kolicina = binding.txtKolicina;
        gratis = binding.txtGratis;
        datumDo = binding.txtDatumDo;
        datumOd = binding.txtDatumOd;
        iskoristiPromociju = binding.btnIskoristiPopust;
        iskoristiPromociju.setEnabled(false);
        RetriveData();
        iskoristiPromociju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueueSlanje = Volley.newRequestQueue(getActivity());
                slanjePodataka = new SlanjePodataka(iskoristiUrl);
                Map<String, String> params = new HashMap<>();
                params.put("id_korisnik", model.getIdKorisnik());
                params.put("id_promocija", model.getIdPromocija());
                slanjePodataka.setParametri(params);
                slanjePodataka.sendData(getActivity(),requestQueueSlanje);
                requestQueueSlanje.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
                    @Override
                    public void onRequestFinished(Request<Object> request) {
                        String odgovor = slanjePodataka.getOdgovor();
                        Toast toast = Toast.makeText(getActivity(),odgovor,Toast.LENGTH_LONG);
                        toast.show();
                        if(odgovor.equals("Succesfully used a promotion")){
                            iskoristiPromociju.setEnabled(false);
                        }
                    }
                });
            }
        });

        return view;
    }


    private void RetriveData() {
        requestQueue = Volley.newRequestQueue(getActivity());
        dohvatPodataka = new DohvatPodataka();
        Map<String, String> params = new HashMap<>();
        params.put("id_promocija",model.getIdPromocija());
        params.put("id_korisnik", model.getIdKorisnik());
        dohvatPodataka.setParametri(params);
        dohvatPodataka.setSendUrl(sendUrl);
        dohvatPodataka.retrieveData(getActivity(),requestQueue);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>()  {
            @Override
            public void onRequestFinished(Request<Object> request) {
                JSONObject odgovor = dohvatPodataka.getOdgovor();
                if (odgovor != null) {
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = odgovor.getJSONArray("proizvod");
                        JSONObject objekt = jsonArray.getJSONObject(0);
                        model.setProizvodNaziv(objekt.getString("naziv_proizvoda"));
                        proizvodNaziv.setText(model.getProizvodNaziv());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        JSONArray body = odgovor.getJSONArray("promocija");
                        model.PostaviModel(body);
                        PostaviPodatke();
                        dohvatPodataka.setSendUrl("https://beervana2020.000webhostapp.com/test/DohvatiPivoPoIDu.php");
                        Map<String, String> params = new HashMap<>();
                        params.put("id_pivo", model.getIdOdabranaPiva());
                        dohvatPodataka.setParametri(params);
                        dohvatPodataka.retrieveData(getActivity(),requestQueue);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void PostaviPodatke() {
        nazivPromocije.setText(model.getNazivPromocije());
        opisPromocije.setText(model.getOpisPromocije());
        kolicina.setText(model.getKolicina());
        gratis.setText("+".concat(model.getGratis().concat(" Free")));
        datumOd.setText("Od: ".concat(model.getDatumOd()));
        datumDo.setText("Do: ".concat(model.getDatumDo()));
        if(!model.getIskoristeno()){
            iskoristiPromociju.setEnabled(true);
        }
    }
}
