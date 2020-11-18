package com.example.beervana;

import java.util.List;

public class DegustacijskiMeniLogika {

    public static boolean imagePicked(String image)
    {
        if(image != null && !image.trim().isEmpty()) return true;
        return false;
    }

    public static boolean menuName(String name)
    {
        if(name != null &&!name.trim().isEmpty() && name.length()>3 && name.length()<45) return true;
        return false;
    }


    public static boolean menuDuration(String duration)
    {
        if(duration!=null  && !duration.trim().isEmpty() && duration.matches("[0-9]+")) return true;
        return false;
    }

    public static boolean checkBeer(List<String> beers)
    {
        if(!beers.isEmpty()) return true;
        return false;
    }

}
