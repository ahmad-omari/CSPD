package com.gp.cspd.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseForm {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String formName;
    private String orderName;

    public DatabaseForm(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Forms");
        formName=null;
        orderName=null;
    }

    public void setFormName(String formName){
        this.formName = formName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getFormName() {
        return formName;
    }

    public String getOrderName() {
        return orderName;
    }

    public void uploadDB(){
        if (formName != null && orderName != null){
            AccountLoged loged = AccountLoged.getInstance();
            DatabaseReference reference = myRef.child("order");
            String key=reference.push().getKey();
            reference.child(key).child("ssn").setValue(loged.getSsn());
            reference.child(key).child("order_type").setValue(orderName);
            reference.child(key).child("order_name").setValue(formName);
            reference.child(key).child("status").setValue("in process");
            reference.child(key).child("ssn_id").setValue(key);



            /*
            DatabaseReference refe = reference.child("name");
            refe.setValue("ID Card");

            DatabaseReference reference1 = reference.child("type");
            reference1.setValue("Renew");

            DatabaseReference reference2 = reference.child("status");
            reference2.setValue("In Process");

             */

           // DatabaseReference reference = myRef.child("order").child("ID Card");
           // DatabaseReference myRef2 = myRef.child(formName).child(loged.getSsn()).child("status");
           // myRef2.setValue("In Process");

        }
    }
}
/*
ID CARD
        ---- renew
        SSN + renewIdCardPersonalImage .ext

       --- damage / replace
        SSN + damgedIdCardPersonal .ext
        SSN + damgedIdCardWritten .ext
        SSN + damgedIdCardExplain .ext


Passport
        --- first/renew
        SSN + firstPassportNote .ext
        SSN + firstPassportBook .ext
        SSN + firstPassportForeign .ext

 */