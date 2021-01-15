package com.example.beervana;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modulzamodule.ModelPodatakaLokacijaSOcjenom;

import java.util.List;

public class SearchActivityRecyclerAdapterPivnica extends RecyclerView.Adapter<SearchActivityRecyclerAdapterPivnica.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<ModelPodatakaLokacijaSOcjenom> lokacije;
    private onLokacijaListener onLokacijaListener;

    SearchActivityRecyclerAdapterPivnica(Context context, List<ModelPodatakaLokacijaSOcjenom> lokacije,onLokacijaListener onLokacijaListener){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.lokacije = lokacije;
        this.onLokacijaListener = onLokacijaListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_location_view,parent,false);
        return new SearchActivityRecyclerAdapterPivnica.ViewHolder(view,onLokacijaListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchActivityRecyclerAdapterPivnica.ViewHolder holder, int position) {
        ModelPodatakaLokacijaSOcjenom lokacija = lokacije.get(position);
        holder.nazivLokacije.setText(lokacija.getLokacija().getNazivLokacija());
        holder.adresaLokacije.setText(lokacija.getLokacija().getAdresaLokacija());
        holder.ocjenaLokacije.setText(lokacija.getOcjena());
    }

    @Override
    public int getItemCount() {
        return lokacije.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView nazivLokacije,ocjenaLokacije,adresaLokacije;
        onLokacijaListener lokacijaListener;
        public ViewHolder(@NonNull View itemView, onLokacijaListener lokacijaListener) {
            super(itemView);
            nazivLokacije = itemView.findViewById(R.id.txtNazivLokacijePretrazivanje);
            ocjenaLokacije = itemView.findViewById(R.id.txtOcjenaLokacijePretrazivanje);
            adresaLokacije = itemView.findViewById(R.id.txtAdresaLokacijePretrazivanje);
            this.lokacijaListener = lokacijaListener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            lokacijaListener.onLocationClick(getAdapterPosition());
        }
    }
    public interface onLokacijaListener{
        void onLocationClick(int position);
    }
}
