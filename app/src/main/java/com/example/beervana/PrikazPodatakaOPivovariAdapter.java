package com.example.beervana;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PrikazPodatakaOPivovariAdapter extends ArrayAdapter<PrikazPodatakaOPivovaryViewModel> {

    Context context;
    List<PrikazPodatakaOPivovaryViewModel> arrayPrikaz;


    public PrikazPodatakaOPivovariAdapter(@NonNull Context context, List<PrikazPodatakaOPivovaryViewModel> arrayPrikaz) {
        super(context, R.layout.activity_prikaz_podataka_o_pivovari , arrayPrikaz);

        this.context = context;
        this.arrayPrikaz = arrayPrikaz;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_prikaz_podataka_o_pivovari, null, true);
        TextView beerName1 = view.findViewById(R.id.textViewBeerTitle1);
        TextView beerName2 = view.findViewById(R.id.textViewBeerTitle2);
        TextView beerTaste1 = view.findViewById(R.id.textViewOkus1);
        TextView beerTaste2 = view.findViewById(R.id.textViewOkus3);
        TextView beerLitres1 = view.findViewById(R.id.textViewOkus2);
        TextView beerLitres2 = view.findViewById(R.id.textViewOkus4);
        TextView eventTitle1 = view.findViewById(R.id.textViewEventTitle1);
        TextView eventTitle2 = view.findViewById(R.id.textViewEventTitle2);
        TextView eventDesc1 = view.findViewById(R.id.textViewEventDesc1);
        TextView eventDesc2 = view.findViewById(R.id.textViewEventDesc2);
        TextView menuTitle1 = view.findViewById(R.id.textViewTastingMTitle1);
        TextView menuTitle2 = view.findViewById(R.id.textViewTastingMTitle2);
        TextView menuDesc1 = view.findViewById(R.id.textViewTastingMDesc1);
        TextView menuDesc2 = view.findViewById(R.id.textViewTastingMDesc);
        TextView ocjena1 = view.findViewById(R.id.userReviewTextView);
        TextView ocjena2 = view.findViewById(R.id.userReviewTextView3);
        TextView komentar1 = view.findViewById(R.id.commentTextView3);
        TextView komentar2 = view.findViewById(R.id.commentTextView);

        //slike
        ImageView beerImage1 = view.findViewById(R.id.imageViewBeers1);
        ImageView beerImage2 = view.findViewById(R.id.imageViewBeers2);
        ImageView eventImage1 = view.findViewById(R.id.imageViewEvent1);
        ImageView eventImage2 = view.findViewById(R.id.imageViewEvent2);

        beerName1.setText(arrayPrikaz.get(position).getNaziv_proizvoda_1());

        return view;
    }
}
