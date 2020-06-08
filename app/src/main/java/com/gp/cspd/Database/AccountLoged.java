package com.gp.cspd.Database;

public class AccountLoged {
    private static AccountLoged instance=null;
    private String ssn;

    private AccountLoged(){}

    public static AccountLoged getInstance() {
        if (instance == null)
            instance = new AccountLoged();
        return instance;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
