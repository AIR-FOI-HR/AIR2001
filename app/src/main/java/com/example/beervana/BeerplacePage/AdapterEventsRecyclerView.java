package com.example.beervana.BeerplacePage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beervana.EventMenu.ModelPodatakEventCatalog;
import com.example.beervana.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterEventsRecyclerView extends RecyclerView.Adapter<AdapterEventsRecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<ModelPodatakEventCatalog> events;

    public AdapterEventsRecyclerView(Context context, List<ModelPodatakEventCatalog> events) {
        this.layoutInflater = LayoutInflater.from(context);
        this.events = events;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_beerplace_view,parent,false);
        return  new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelPodatakEventCatalog title = events.get(position);
        holder.textTitle.setText(title.dogadaj.getNazivDogadaj());
        String imageUri = title.dogadaj.getSlikaDogadaja();
        Picasso.with(context).load(imageUri).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return events.size();
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
