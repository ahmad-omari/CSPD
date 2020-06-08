package com.gp.cspd.login;

public class UserVerification {

    public static boolean isValidSSN(String ssn){
        return ssn.length()==10;
    }
    public static boolean isValidPassword(String password){
        return password.length()>6;
    }
}
