package com.example.beervana.BeerMenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.beervana.R;
import com.example.modulzamodule.Beer;

import java.util.List;

public class BeerCatalogAdapter extends ArrayAdapter<Beer> {

    Context context;
    List<Beer> arrayListBeer;


    public BeerCatalogAdapter(@NonNull Context context, List<Beer> arrayListBeer) {
        super(context, R.layout.custom_list_view, arrayListBeer);

        this.context = context;
        this.arrayListBeer = arrayListBeer;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_view, null, true);

        TextView tvID = view.findViewById(R.id.txtIdPiva);
        TextView tvName = view.findViewById(R.id.txtNazivPiva);
        TextView tvPrice = view.findViewById(R.id.txtPrice);

        tvID.setText(arrayListBeer.get(position).getId_proizvod());
        tvName.setText(arrayListBeer.get(position).getNaziv_proizvoda());
        tvPrice.setText(arrayListBeer.get(position).getCijena_proizvoda());

        return view;
    }

}
