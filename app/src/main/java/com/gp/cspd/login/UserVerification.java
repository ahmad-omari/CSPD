package com.gp.cspd.login;

public class UserVerification {

    public static boolean isValidSSN(String ssn){
        return ssn.matches("[0-9]+") && (ssn.length()==14);
    }
    public static boolean isValidPassword(String password){
        return password.length()>6;
    }
}
