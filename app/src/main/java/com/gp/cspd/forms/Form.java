package com.gp.cspd.forms;

public abstract class Form {
    private final String formName;

    protected Form(String formName) {
        this.formName = formName;
    }

    public String getFormName() {
        return formName;
    }
}
