package com.example.beervana.BeerplacePage.Modularnost;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewStub;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.basemodule.BaseClassForModules;

import com.example.beervana.BaseActivity;
import com.example.beervana.GlavniIzbornikClient;
import com.example.beervana.R;

import java.util.List;

public class LoadModuleFragmentActivity extends BaseActivity {
    List<BaseClassForModules> modulesList;
    int pozicija;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_module_fragment);
        initToolbar();
        //TODO remove line under
        Modules.getInstance();
        modulesList = Modules.getModulesList();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        pozicija = extras.getInt("position");

        BaseClassForModules m = null;
        try {
            m = Modules.getModulesList().get(0).getClass().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


        String fragment =   m.ViewPromos();
        Class<? > c = null;
        try {
            c = Class.forName(fragment);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.staticFragment, (Class<? extends Fragment>) c, null, "tag")
                .setReorderingAllowed(true)
                .commit();

    }
}
