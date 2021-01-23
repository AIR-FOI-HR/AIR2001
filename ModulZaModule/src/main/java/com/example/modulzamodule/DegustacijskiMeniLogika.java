package com.example.modulzamodule;

import java.text.SimpleDateFormat;
import java.util.List;

public class DegustacijskiMeniLogika {

    public static boolean menuName(String name)
    {
        if(name != null &&!name.trim().isEmpty() && name.length()>3 && name.length()<45) return true;
        return false;
    }


    public static boolean menuDuration(String duration)
    {
        if(duration.equals("End Date:")){
            return false;
        }
        return true;
    }

    public static boolean checkBeer(List<String> beers)
    {
        if(!beers.isEmpty()) return true;
        return false;
    }

    public static boolean menuDescription(String description) {
        if(description != null && !description.isEmpty() && description.length()>10)
            return true;
        return false;
    }
}
