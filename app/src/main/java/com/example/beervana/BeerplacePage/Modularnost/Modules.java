package com.example.beervana.BeerplacePage.Modularnost;

import com.example.basemodule.BaseClassForModules;
import com.example.modul1.Modul1;
import com.example.modul2.Modul2;
import java.util.ArrayList;
import java.util.List;



public class Modules {
    static List<BaseClassForModules> modulesList = new ArrayList<>();
    private static Modules INSTANCE = null;

    private Modules() {
    }

    public static Modules getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Modules();
            setModules();
        }

        return (INSTANCE);
    }

    private static void setModules() {
        modulesList.add(new Modul1());
        modulesList.add(new Modul2());
    }

    public static List<BaseClassForModules> getModulesList() {
        return modulesList;
    }

}
