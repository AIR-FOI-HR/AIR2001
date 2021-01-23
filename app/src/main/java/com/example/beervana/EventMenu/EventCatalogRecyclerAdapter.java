package com.example.beervana.EventMenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beervana.R;
import com.example.modulzamodule.ModelPodatakEventCatalog;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventCatalogRecyclerAdapter extends RecyclerView.Adapter<EventCatalogRecyclerAdapter.ViewHolder> {

    private final Context context;
    private final LayoutInflater layoutInflater;
    private final List<ModelPodatakEventCatalog> events;
    private final onDogadajListener onDogadajListener;

    public EventCatalogRecyclerAdapter(Context context, List<ModelPodatakEventCatalog> events, onDogadajListener onDogadajListener) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.events = events;
        this.onDogadajListener = onDogadajListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.event_catalog_list_view, parent, false);
        return new ViewHolder(view, onDogadajListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EventCatalogRecyclerAdapter.ViewHolder holder, int position) {
        ModelPodatakEventCatalog title = events.get(position);
        holder.txtNazivDogadaja.setText(title.dogadaj.getNazivDogadaj());
        holder.txtNazivLokacije.setText(title.getNazivLokacije());
        holder.txtDatumOd.setText(title.dogadaj.getDatumOd());
        holder.txtDatumDo.setText(title.dogadaj.getDatumDo());
        String imageUri = title.dogadaj.getSlikaDogadaja();
        Picasso.with(context).load(imageUri).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtNazivLokacije, txtNazivDogadaja, txtDatumOd, txtDatumDo;
        ImageView imageView;
        onDogadajListener dogadajListener;

        public ViewHolder(@NonNull View itemView, onDogadajListener dogadajListener) {
            super(itemView);
            txtNazivLokacije = itemView.findViewById(R.id.txtNazivLokacije);
            txtNazivDogadaja = itemView.findViewById(R.id.txtNazivDogadaja);
            txtDatumOd = itemView.findViewById(R.id.txtDatumOd);
            txtDatumDo = itemView.findViewById(R.id.txtDatumDo);
            imageView = itemView.findViewById(R.id.imgSlikaPrikazDogadaja);
            this.dogadajListener = dogadajListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            dogadajListener.onEventClick(getAdapterPosition());
        }
    }

    public interface onDogadajListener {
        void onEventClick(int position);
    }


}
