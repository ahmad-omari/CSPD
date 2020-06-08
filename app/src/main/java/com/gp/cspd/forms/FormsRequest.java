package com.gp.cspd.forms;

import java.util.ArrayList;
import java.util.List;

public class FormsRequest {
    private static FormsRequest instance=null;
    private static List<Form> formList;

    private FormsRequest(){}

    public static FormsRequest getInstance() {
        if (instance == null) {
            instance = new FormsRequest();
            formList = new ArrayList<>();
        }
        return instance;
    }

    public static List<Form> getFormList() {
        return formList;
    }

    public void addForm(Form form) {
        this.formList.add(form);
    }
}
