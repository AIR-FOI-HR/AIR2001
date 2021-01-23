package com.example.beervana.BeerplacePage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.beervana.R;
import com.example.modulzamodule.Review;

import java.util.ArrayList;

public class AdapterReview extends RecyclerView.Adapter<AdapterReview.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Review> data = new ArrayList<Review>();
    private onRecenzijaListener onRecenzijaListener;
    AdapterReview(Context context, ArrayList<Review> data, onRecenzijaListener onRecenzijaListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
        this.onRecenzijaListener = onRecenzijaListener;
    }

    @NonNull
    @Override
    public AdapterReview.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_beerplace_view, parent, false);
        return new AdapterReview.ViewHolder(view, onRecenzijaListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterReview.ViewHolder holder, int position) {

        Review title = data.get(position);
        holder.komentar.setText(title.getKomentar());
        holder.ocjena.setText(title.getOcjena());
        holder.datum.setText(title.getDatum_i_vrijeme_recenzije());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        onRecenzijaListener recenzijaListener;
        TextView komentar, ocjena, datum;
        public ViewHolder(@NonNull View itemView,onRecenzijaListener recListener) {
            super(itemView);
            komentar = itemView.findViewById(R.id.komentar);
            ocjena = itemView.findViewById(R.id.ocjena);
            datum = itemView.findViewById(R.id.datum);
            this.recenzijaListener = recListener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            recenzijaListener.onReviewClick(getAdapterPosition());
        }
    }
    public interface onRecenzijaListener{
        void onReviewClick(int position);
    }


}