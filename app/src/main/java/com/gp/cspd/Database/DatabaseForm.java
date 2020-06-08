package com.gp.cspd.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseForm {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String formName;

    public DatabaseForm(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Forms");
        formName=null;
    }

    public void setFormName(String formName){
        this.formName = formName;
    }

    public void uploadDB(){
        if (formName != null){
            AccountLoged loged = AccountLoged.getInstance();
            DatabaseReference reference = myRef.child(loged.getSsn()).child("order");

            DatabaseReference refe = reference.child("name");
            refe.setValue("ID Card");

            DatabaseReference reference1 = reference.child("type");
            reference1.setValue("Renew");

            DatabaseReference reference2 = reference.child("status");
            reference2.setValue("In Process");
           // DatabaseReference reference = myRef.child("order").child("ID Card");
           // DatabaseReference myRef2 = myRef.child(formName).child(loged.getSsn()).child("status");
           // myRef2.setValue("In Process");

        }
    }
}
