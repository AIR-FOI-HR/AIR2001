package com.example.beervana.TastingMenu;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.beervana.R;

import java.util.List;

public class TastingMenuAdapter extends ArrayAdapter<TastingMenu> {

    private Context context;
    private List<TastingMenu> arrayListTastingMenu;

    public TastingMenuAdapter(Context context,List<TastingMenu> arrayListTastingMenu) {
        super(context, R.layout.tasting_menu_item,arrayListTastingMenu);
        this.context = context;
        this.arrayListTastingMenu = arrayListTastingMenu;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasting_menu_item,null,true);

        TextView name = view.findViewById(R.id.tastingMenuItemName);
        TextView description = view.findViewById(R.id.tastingMenuItemDescription);
        TextView duration = view.findViewById(R.id.tastingMenuItemDuration);
        TextView location = view.findViewById(R.id.tastingMenuItemLocation);

        description.setText(arrayListTastingMenu.get(position).getDescription());
        name.setText(arrayListTastingMenu.get(position).getName());
        duration.setText(arrayListTastingMenu.get(position).getDuration());
        location.setText(arrayListTastingMenu.get(position).getLocation());

        return view;
    }


}

