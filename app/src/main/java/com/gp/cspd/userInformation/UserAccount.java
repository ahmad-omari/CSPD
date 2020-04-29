package com.gp.cspd.userInformation;

public class UserAccount {
    private final String ssn;
    private final String password;

    public UserAccount(String ssn, String password) {
        this.ssn = ssn;
        this.password = password;
    }

    public String getSsn() {
        return ssn;
    }

    public String getPassword() {
        return password;
    }
}
