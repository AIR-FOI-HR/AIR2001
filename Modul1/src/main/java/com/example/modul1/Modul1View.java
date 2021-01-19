package com.example.modul1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.modul1.databinding.Modul1ViewPromosFragmentBinding;

public class Modul1View extends Fragment {
    String sendUrl = "https://beervana2020.000webhostapp.com/test/GetPromotionInfo.php";
    String iskoristiUrl = "";
    Modul1ViewPromosFragmentBinding binding;
    TextView nazivPromocije,opisPromocije,proizvodNaziv,popust;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modul1_view_promos_fragment, container, false);
        view = binding.getRoot();


        return view;
    }
}
