package com.example.beervana;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modulzamodule.ModelPodatakaPivoSOcjenom;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ActivityRecyclerAdaptetPivoSOcjenom extends RecyclerView.Adapter<ActivityRecyclerAdaptetPivoSOcjenom.ViewHolder> {
    private final Context context;
    private LayoutInflater layoutInflater;
    private final List<ModelPodatakaPivoSOcjenom> piva;
    private final onPivaListener onPivaListener;

    public ActivityRecyclerAdaptetPivoSOcjenom(Context context, List<ModelPodatakaPivoSOcjenom> piva, onPivaListener onPivaListener) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.piva = piva;
        this.onPivaListener = onPivaListener;
    }

    @NonNull
    @Override
    public ActivityRecyclerAdaptetPivoSOcjenom.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_beer_view, parent, false);
        return new ActivityRecyclerAdaptetPivoSOcjenom.ViewHolder(view, onPivaListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelPodatakaPivoSOcjenom pive = piva.get(position);
        holder.nazivPiva.setText(pive.getBeer().getNaziv_proizvoda());
        holder.cijenaPiva.setText(pive.getBeer().getCijena_proizvoda());
        holder.okusPiva.setText(pive.getBeer().getCijena_proizvoda());
        String imageUri = pive.getBeer().getSlika();
        Picasso.with(context).load(imageUri).into(holder.slikaPiva);
    }

    @Override
    public int getItemCount() {
        return piva.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nazivPiva, cijenaPiva, okusPiva;
        ImageView slikaPiva;
        onPivaListener pivaListener;

        public ViewHolder(@NonNull View itemView, onPivaListener pivaListener) {
            super(itemView);
            nazivPiva = itemView.findViewById(R.id.txtNazivPivaPretrazivanje);
            cijenaPiva = itemView.findViewById(R.id.txtCijenaPivaPretrazivanje);
            okusPiva = itemView.findViewById(R.id.txtOkusPivaPretrazivanje);
            slikaPiva = itemView.findViewById(R.id.slikaPiva);
            this.pivaListener = pivaListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            pivaListener.onBeerClick(getAdapterPosition());
        }
    }

    public interface onPivaListener {
        void onBeerClick(int position);
    }
}
