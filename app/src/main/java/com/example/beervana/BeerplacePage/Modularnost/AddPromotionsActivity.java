package com.example.beervana.BeerplacePage.Modularnost;

import android.content.Intent;
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
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modularnost);
        initToolbar();
        view = findViewById(android.R.id.content).getRootView();
        recyclerView = findViewById(R.id.AddPromos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Modules.getInstance();
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
        Intent intent = new Intent(this, LoadModuleFragmentActivity.class).putExtra("position", position);
        startActivity(intent);
    }
}
