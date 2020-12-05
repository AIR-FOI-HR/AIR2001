package com.example.beervana.TastingMenu;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.beervana.Beer;
import com.example.beervana.R;

import java.util.List;

public class TastingMenuContentAdapter extends ArrayAdapter<Beer> {

    private Context context;
    private List<Beer> arrayListTastingMenu;

    public TastingMenuContentAdapter(Context context,List<Beer> arrayListTastingMenu) {
        super(context, R.layout.tasting_menu_beer_item,arrayListTastingMenu);
        this.context = context;
        this.arrayListTastingMenu = arrayListTastingMenu;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasting_menu_beer_item,null,true);

        TextView name = view.findViewById(R.id.tastingBeerName);
        TextView description = view.findViewById(R.id.tastingBeerDescription);
        TextView price = view.findViewById(R.id.tastingBeerPrice);

        description.setText(arrayListTastingMenu.get(position).getOkus());
        name.setText(arrayListTastingMenu.get(position).getNaziv_proizvoda());
        price.setText(arrayListTastingMenu.get(position).getCijena_proizvoda());

        return view;
    }


}

