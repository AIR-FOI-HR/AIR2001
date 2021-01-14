package com.example.beervana.BeerplacePage.Modularnost;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.basemodule.BaseClassForModules;
import com.example.beervana.BaseActivity;
import com.example.beervana.R;

import java.util.List;

public class LoadModuleFragmentActivity extends BaseActivity {
    List<BaseClassForModules> modulesList = Modules.getModulesList();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_module_fragment);
        initToolbar();

        Modul1 m = new Modul1();
        String fragment = m.AddPromos();
        Class<?> c = null;
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