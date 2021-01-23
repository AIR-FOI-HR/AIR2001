package com.example.beervana.TastingMenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beervana.R;
import com.example.modulzamodule.TastingMenu;

import java.util.List;

public class RecyclerTastingMenuAdapter extends RecyclerView.Adapter<RecyclerTastingMenuAdapter.ViewHolder> {

    private final Context context;
    private final List<TastingMenu> arrayListTastingMenu;
    private final onTastingMenuListener onTastingMenuListener;
    private final LayoutInflater layoutInflater;

    public RecyclerTastingMenuAdapter(
            Context context,
            List<TastingMenu> arrayListTastingMenu,
            onTastingMenuListener onTastingMenuListener) {
        this.context = context;
        this.arrayListTastingMenu = arrayListTastingMenu;
        this.onTastingMenuListener = onTastingMenuListener;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {
        View view = layoutInflater.inflate(R.layout.tasting_menu_item, parent, false);
        return new ViewHolder(view, onTastingMenuListener);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {
        TastingMenu tastingMenu = arrayListTastingMenu.get(position);

        holder.tastingMenuName.setText(tastingMenu.getName());
        holder.tastingMenuDescription.setText(tastingMenu.getDescription());
        holder.tastingMenuDuration.setText(tastingMenu.getDuration());
        holder.tastingMenuLocation.setText(tastingMenu.getLocation());
    }

    @Override
    public int getItemCount() {
        return arrayListTastingMenu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tastingMenuName, tastingMenuDescription, tastingMenuDuration, tastingMenuLocation;
        onTastingMenuListener tastingMenuListener;

        public ViewHolder(@NonNull View itemView, onTastingMenuListener tastingMenuListener) {
            super(itemView);
            tastingMenuName = itemView.findViewById(R.id.tastingMenuItemName);
            tastingMenuDuration = itemView.findViewById(R.id.tastingMenuItemDuration);
            tastingMenuDescription = itemView.findViewById(R.id.tastingMenuItemDescription);
            tastingMenuLocation = itemView.findViewById(R.id.tastingMenuItemLocation);
            this.tastingMenuListener = tastingMenuListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            tastingMenuListener.onTastingMenuClick(getAdapterPosition());
        }
    }

    public interface onTastingMenuListener {
        void onTastingMenuClick(int position);
    }
}
