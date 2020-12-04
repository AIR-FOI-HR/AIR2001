package com.example.beervana.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.beervana.R;
import com.example.beervana.model.Info;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<Info> mData;
    RequestOptions option;

    public RecyclerViewAdapter(Context mContext, List<Info> mData) {
        this.mContext = mContext;
        this.mData = mData;
        //slika
        option = new RequestOptions().centerCrop();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.info_row_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_naziv_piva.setText(mData.get(position).getNaziv_piva());
        holder.tv_okus.setText(mData.get(position).getOkus());
        holder.tv_litara.setText(mData.get(position).getLitara());
        //Glide koristimo
        Glide.with(mContext).load(mData.get(position).getSlika()).apply(option).into(holder.img_thumbnail);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_naziv_piva;
        TextView tv_okus;
        TextView tv_litara;
        ImageView img_thumbnail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_naziv_piva = itemView.findViewById(R.id.naziv_pivkana);
            tv_okus = itemView.findViewById(R.id.okusi);
            tv_litara = itemView.findViewById(R.id.litara);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);

        }
    }



}
