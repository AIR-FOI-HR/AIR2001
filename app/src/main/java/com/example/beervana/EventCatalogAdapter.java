package com.example.beervana;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class EventCatalogAdapter extends ArrayAdapter<ModelPodatakEventCatalog> {
    Context context;
    List<ModelPodatakEventCatalog> arrayListDataEvent;


    public EventCatalogAdapter(@NonNull Context context, List<ModelPodatakEventCatalog> arrayListDataEvent) {
        super(context,R.layout.event_catalog_list_view,arrayListDataEvent);

        this.context=context;
        this.arrayListDataEvent = arrayListDataEvent;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_catalog_list_view,null,true);

        ImageView slika = view.findViewById(R.id.imgSlikaPrikazDogadaja);
        TextView lokacija = view.findViewById(R.id.txtNazivLokacije);
        TextView nazivDogadaj = view.findViewById(R.id.txtNazivDogadaja);
        TextView datumOd = view.findViewById(R.id.txtDatumOd);
        TextView datumDo = view.findViewById(R.id.txtDatumDo);

        String imageUri = arrayListDataEvent.get(position).dogadaj.getSlikaDogadaja();
        Picasso.with(context).load(imageUri).into(slika);
        lokacija.setText(arrayListDataEvent.get(position).getNazivLokacije());
        nazivDogadaj.setText(arrayListDataEvent.get(position).dogadaj.getNazivDogadaj());
        datumOd.setText(arrayListDataEvent.get(position).dogadaj.getDatumOd());
        datumDo.setText(arrayListDataEvent.get(position).dogadaj.getDatumDo());


        return view;
    }
}
