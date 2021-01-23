package com.example.beervana.UserData;


public class UserLogic {
    public String ProvjeraUnosaKorisnickogImena(String username) {
        if (username.equals("")) {
            return "Error: you must insert a username first";
        }

        return "";
    }

    public String ProvjeraIspravnostiKorisnickogImena(String old_username, String new_username) {
        if (old_username.equals(new_username)) {
            return "Error: new username must be different from old username";
        }

        return "";
    }

    public String ProvjeraUnosaLozinke(String new_password_1, String new_password_2) {
        if (new_password_1.equals("") | new_password_2.equals("")) {
            return "Error: you must insert a password first";
        }

        return "";
    }

    //TODO
    public String ProvjeraIspravnostiLozinke(String new_password_1_1, String new_password_2_2) {
        if (!(new_password_1_1.equals(new_password_2_2))) {
            return "Error: Please check if passwords are the same";
        }

        return "";
    }

    public String ProvjeraIspravnostiNoveLozinke(String new_password_1_1_1, String new_password_2_2_2, String old_password) {
        if (new_password_1_1_1.equals(old_password) | new_password_2_2_2.equals(old_password)) {
            return "Error: New password cannot be the same as old password";
        }

        return "";
    }

}