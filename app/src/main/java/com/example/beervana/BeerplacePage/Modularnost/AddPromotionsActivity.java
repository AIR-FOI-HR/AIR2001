package com.example.beervana.BeerplacePage.Modularnost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basemodule.BaseClassForModules;
import com.example.beervana.BaseActivity;
import com.example.beervana.R;

import java.util.List;

public class AddPromotionsActivity extends BaseActivity implements AddPromotionsRecylcerAdapter.onAddPromosListener{
    RecyclerView recyclerView;
    AddPromotionsRecylcerAdapter adapter;
    List<BaseClassForModules> modulesList;
    SharedPreferences sp;
    View view;
    String idLokacija;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modularnost);
        initToolbar();
        view = findViewById(android.R.id.content).getRootView();
        recyclerView = findViewById(R.id.AddPromos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //TODO makunti instanciranje
        Modules.getInstance();
        sp = getSharedPreferences("login", MODE_PRIVATE);
        idLokacija = sp.getString("id_lokacija", "Nema Lokacija").split(",")[0];
        modulesList = Modules.getModulesList();
        loadPromos();

    }

    private void loadPromos() {
        adapter = new AddPromotionsRecylcerAdapter(AddPromotionsActivity.this, modulesList,AddPromotionsActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onPromotionClick(int position) {
        Intent intent = new Intent(this, LoadModuleFragmentActivity.class).putExtra("position", position).putExtra("id_promocija","0")
                .putExtra("id_lokacija",idLokacija);
        startActivity(intent);
    }
}
