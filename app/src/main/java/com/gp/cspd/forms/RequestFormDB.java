package com.gp.cspd.forms;

public class RequestFormDB {
    private String formName;
    private String status;

    public RequestFormDB(){
        formName=null;
        status=null;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
