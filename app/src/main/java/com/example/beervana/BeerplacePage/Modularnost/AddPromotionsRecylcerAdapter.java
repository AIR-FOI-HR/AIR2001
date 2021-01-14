package com.example.beervana.BeerplacePage.Modularnost;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basemodule.BaseClassForModules;
import com.example.beervana.R;

import java.util.List;

public class AddPromotionsRecylcerAdapter extends RecyclerView.Adapter<AddPromotionsRecylcerAdapter.ViewHolder >{
    private Context context;
    private LayoutInflater layoutInflater;
    private List<BaseClassForModules> modules;
    private AddPromotionsRecylcerAdapter.onAddPromosListener onAddPromosListener;

    public AddPromotionsRecylcerAdapter(Context context, List<BaseClassForModules> modules, AddPromotionsRecylcerAdapter.onAddPromosListener onAddPromosListener){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.modules = modules;
        this.onAddPromosListener = onAddPromosListener;
    }
    @NonNull
    @Override
    public AddPromotionsRecylcerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.modul_list_item,parent,false);
        return new AddPromotionsRecylcerAdapter.ViewHolder(view,onAddPromosListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AddPromotionsRecylcerAdapter.ViewHolder holder, int position) {
        BaseClassForModules module = modules.get(position);

        holder.txtNazivModula.setText(module.getNaslov());
        holder.txtOpisMoudla.setText(module.getOpis());
    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtNazivModula,txtOpisMoudla;
        onAddPromosListener promocijeListener;
        public ViewHolder(@NonNull View itemView, onAddPromosListener onAddPromoListener) {
            super(itemView);
            txtNazivModula = itemView.findViewById(R.id.naslov);
            txtOpisMoudla = itemView.findViewById(R.id.opis);
            this.promocijeListener = onAddPromoListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onAddPromosListener.onPromotionClick(getAdapterPosition());
        }
    }
    interface onAddPromosListener{
        void onPromotionClick(int position);
    }
}
