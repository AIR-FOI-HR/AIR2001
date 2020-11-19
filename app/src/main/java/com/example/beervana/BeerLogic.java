package com.example.beervana;

public class BeerLogic {
    public String ProvjeraUnosaSlike(String slika){
        if(slika.equals("")){
            return "Error: you must choose an image";
        }

        return "";
    }

    public String ProvjeraUnosaNazivaPiva(String nazivPiva){

        if(nazivPiva.equals("")){
            return "Error: you have to enter beer name.";
        }else if(nazivPiva.length()<3 || nazivPiva.length()>45){
            return "Error: beer name must be between 3 and 45 characters long.";
        }
        return "";
    }

    public String ProvjeraUnosaCijenePiva(Double cijenaPiva){

        if(cijenaPiva.equals("")){
            return "Error: you have to enter price of beer.";
        }else if(cijenaPiva<0){
            return "Error: Beer price must be positive. ";
        }
        return "";
    }

    public String ProvjeraUnosaOkusa(String okusPiva){

        if(okusPiva.equals("")){
            return "Error: you have to enter beer taste. ";
        }else if(okusPiva.length()<3 || okusPiva.length()>100){
            return "Error: beer taste must be between 3 and 100 characters long";
        }
        return "";
    }

    public String ProvjeraUnosaLitara(Double litra){

        if(litra.equals("")){
            return "Error: you have to enter beer litres. ";
        }else if(litra<0){
            return "Error: beer litre must be positive";
        }
        return "";
    }









}
