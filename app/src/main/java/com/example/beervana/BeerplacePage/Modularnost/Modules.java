package com.example.beervana.BeerplacePage.Modularnost;

import java.util.ArrayList;
import java.util.List;

public class Modules {
    static List<BaseClassForModules> modulesList = new ArrayList<>();
    private static Modules INSTANCE = null;

    // other instance variables can be here

    private Modules() {};

    public static Modules getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Modules();
        }
        setModules();
        return(INSTANCE);
    }

    private static void setModules() {
        modulesList.add(new Modul1());
        modulesList.add(new Modul2());
    }
    public static List<BaseClassForModules> getModulesList(){
        return modulesList;
    }

}
