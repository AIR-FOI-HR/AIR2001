package com.example.beervana;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modulzamodule.Beer;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchActivityRecyclerAdapterPivo extends RecyclerView.Adapter<SearchActivityRecyclerAdapterPivo.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Beer> pive;
    private onPivaListener onPivaListener;
    public SearchActivityRecyclerAdapterPivo(Context context, List<Beer> pive, onPivaListener onPivaListener) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.pive = pive;
        this.onPivaListener = onPivaListener;
    }
    @NonNull
    @Override
    public SearchActivityRecyclerAdapterPivo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_beer_view,parent,false);
        return new ViewHolder(view,onPivaListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchActivityRecyclerAdapterPivo.ViewHolder holder, int position) {
        Beer piva = pive.get(position);
        holder.nazivPiva.setText(piva.getNaziv_proizvoda());
        holder.cijenaPiva.setText(piva.getCijena_proizvoda());
        holder.okusPiva.setText(piva.getOkus());
        String imageUri = piva.getSlika();
        Picasso.with(context).load(imageUri).into(holder.slikaPiva);
    }

    @Override
    public int getItemCount() {
        return pive.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nazivPiva,cijenaPiva,okusPiva;
        ImageView slikaPiva;
        onPivaListener pivaListener;
        public ViewHolder(@NonNull View itemView,onPivaListener pivaListener) {
            super(itemView);
            nazivPiva=itemView.findViewById(R.id.txtNazivPivaPretrazivanje);
            cijenaPiva=itemView.findViewById(R.id.txtCijenaPivaPretrazivanje);
            okusPiva=itemView.findViewById(R.id.txtOkusPivaPretrazivanje);
            slikaPiva= itemView.findViewById(R.id.slikaPiva);
            this.pivaListener = pivaListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            pivaListener.onBeerClick(getAdapterPosition());
        }
    }
    public interface onPivaListener{
        void onBeerClick(int position);
    }
}
