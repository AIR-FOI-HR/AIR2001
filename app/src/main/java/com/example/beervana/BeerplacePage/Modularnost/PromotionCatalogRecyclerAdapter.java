package com.example.beervana.BeerplacePage.Modularnost;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beervana.R;
import com.example.modulzamodule.Promotion;

import java.util.ArrayList;

public class PromotionCatalogRecyclerAdapter extends RecyclerView.Adapter<PromotionCatalogRecyclerAdapter.ViewHolder> {
    private final Context context;
    private final LayoutInflater layoutInflater;
    private final ArrayList<Promotion> promotions;
    private final PromotionCatalogRecyclerAdapter.onAddPromosListener onAddPromosListener;

    public PromotionCatalogRecyclerAdapter(Context context, ArrayList<Promotion> promotions, PromotionCatalogRecyclerAdapter.onAddPromosListener onAddPromosListener) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.promotions = promotions;
        this.onAddPromosListener = onAddPromosListener;
    }

    @NonNull
    @Override
    public PromotionCatalogRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.promotion_item, parent, false);
        return new PromotionCatalogRecyclerAdapter.ViewHolder(view, onAddPromosListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PromotionCatalogRecyclerAdapter.ViewHolder holder, int position) {
        Promotion promotion = promotions.get(position);
        holder.txtNazivPromocije.setText(promotion.getNaziv_promocije());
        holder.txtOpisPromocije.setText(promotion.getOpis_promocije());
        holder.txtDatumDo.setText(promotion.getDatum_do());
        holder.txtTipPromocije.setText(promotion.getTip_promocije());

    }

    @Override
    public int getItemCount() {
        return promotions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtNazivPromocije, txtOpisPromocije, txtDatumOd, txtDatumDo, txtTipPromocije;
        onAddPromosListener promocijeListener;

        public ViewHolder(@NonNull View itemView, PromotionCatalogRecyclerAdapter.onAddPromosListener onAddPromoListener) {
            super(itemView);
            txtNazivPromocije = itemView.findViewById(R.id.promotionItemName);
            txtOpisPromocije = itemView.findViewById(R.id.promotionItemDescription);
            txtDatumDo = itemView.findViewById(R.id.promotionItemDuration);
            txtTipPromocije = itemView.findViewById(R.id.promotionItemType);
            this.promocijeListener = onAddPromoListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onAddPromosListener.onPromotionClick(getAdapterPosition());
        }
    }

    interface onAddPromosListener {
        void onPromotionClick(int position);
    }

}
