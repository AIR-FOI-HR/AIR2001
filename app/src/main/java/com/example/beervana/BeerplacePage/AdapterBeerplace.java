package com.example.beervana.BeerplacePage;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beervana.BeerMenu.Beer;
import com.example.beervana.BeerMenu.BeerCatalogActivity;
import com.example.beervana.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterBeerplace extends RecyclerView.Adapter<AdapterBeerplace.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Beer> data = new ArrayList<Beer>();

    AdapterBeerplace(Context context, ArrayList<Beer> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_beerplace_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Beer title = data.get(position);
        holder.textTitle.setText(title.getNaziv_proizvoda());

        String imageUri = title.getSlika();
        Picasso.with(context).load(imageUri).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textTitle, textDescription;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textView4);
            textDescription = itemView.findViewById(R.id.textView8);
            imageView = itemView.findViewById(R.id.imageView6);
        }
    }

}
