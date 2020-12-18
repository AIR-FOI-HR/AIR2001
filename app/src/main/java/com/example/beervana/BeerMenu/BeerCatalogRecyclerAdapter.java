package com.example.beervana.BeerMenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beervana.AdapterBeerplace;
import com.example.beervana.EventMenu.EventCatalogRecyclerAdapter;
import com.example.beervana.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BeerCatalogRecyclerAdapter extends RecyclerView.Adapter<BeerCatalogRecyclerAdapter.ViewHolder>{
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Beer> data = new ArrayList<Beer>();
    private onPivoListener onPivoListener;

    BeerCatalogRecyclerAdapter(Context context, ArrayList<Beer> data,onPivoListener onPivoListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
        this.onPivoListener =onPivoListener;
    }


    @NonNull
    @Override
    public BeerCatalogRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_list_view, parent, false);
        return new ViewHolder(view,onPivoListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BeerCatalogRecyclerAdapter.ViewHolder holder, int position) {
        Beer title = data.get(position);
        holder.textId.setText(title.getId_proizvod());
        holder.textNaziv.setText(title.getNaziv_proizvoda());
        holder.textPrice.setText(title.getCijena_proizvoda());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textId, textNaziv,textPrice;
        onPivoListener pivoListener;
        public ViewHolder(@NonNull View itemView, onPivoListener pivoListener) {
            super(itemView);
            textId = itemView.findViewById(R.id.txtIdPiva);
            textNaziv = itemView.findViewById(R.id.txtNazivPiva);
            textPrice = itemView.findViewById(R.id.txtPrice);
            this.pivoListener = pivoListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            pivoListener.onBeerClick(getAdapterPosition());
        }
    }
    public interface onPivoListener{
        void onBeerClick(int position);
    }
}
