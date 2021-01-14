package com.example.beervana.BeerplacePage.Modularnost;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beervana.BaseActivity;
import com.example.beervana.MainActivity;
import com.example.beervana.R;
import com.example.beervana.TastingMenu.DodavanjeDegustacijskihMeniaActivity;
import com.example.beervana.TastingMenu.RecyclerTastingMenuAdapter;
import com.example.beervana.TastingMenu.TastingMenuActivity;
import com.example.beervana.TastingMenu.TastingMenuDetailsActivity;

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
