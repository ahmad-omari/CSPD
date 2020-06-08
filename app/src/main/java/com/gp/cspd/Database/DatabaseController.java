package com.gp.cspd.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gp.cspd.userInformation.User;

public class DatabaseController {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    public DatabaseController(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
    }

    public void addUser(User user){
        myRef = myRef.child(user.getUserAccount().getSsn());

        DatabaseReference myRef1 = myRef.child("mothersNameEnglish");
        myRef1.setValue(user.getMothersNameEnglish());

        DatabaseReference myRef2 = myRef.child("mothersNameArabic");
        myRef2.setValue(user.getMothersNameArabic());

        DatabaseReference myRef3 = myRef.child("gender");
        myRef3.setValue(user.getGender());

        DatabaseReference myRef4 = myRef.child("birthPlace");
        myRef4.setValue(user.getBirthPlace());

        DatabaseReference myRef5 = myRef.child("livingPlace");
        myRef5.setValue(user.getLivingPlace());

        DatabaseReference myRef6 = myRef.child("bloodType");
        myRef6.setValue(user.getBloodType());

        DatabaseReference myRef7 = myRef.child("email");
        myRef7.setValue(user.getEmail());

        DatabaseReference myRef8 = myRef.child("phoneNO");
        myRef8.setValue(user.getPhoneNO());

        StringBuilder sb = new StringBuilder();
        for (int i=0;i<user.getNameArabic4Parts().length;i++)
            sb.append(user.getNameArabic4Parts()[i]+" ");
        DatabaseReference myRef9 = myRef.child("arabicName");
        myRef9.setValue(sb.toString());

        sb = new StringBuilder();
        for (int i=0;i<user.getNameEnglish4Parts().length;i++)
            sb.append(user.getNameEnglish4Parts()[i]+" ");
        DatabaseReference myRef10 = myRef.child("englishName");
        myRef10.setValue(sb.toString());

        DatabaseReference myRef11 = myRef.child("address").child("governorate");
        myRef11.setValue(user.getUserAddress().getGovernorate());

        DatabaseReference myRef12 = myRef.child("address").child("banner");
        myRef12.setValue(user.getUserAddress().getBanner());

        DatabaseReference myRef13 = myRef.child("address").child("neighborhood");
        myRef13.setValue(user.getUserAddress().getNeighborhood());

        DatabaseReference myRef14 = myRef.child("address").child("nearestNeighborhood");
        myRef14.setValue(user.getUserAddress().getNearestNeighborhood());

        DatabaseReference myRef15 = myRef.child("address").child("complex");
        myRef15.setValue(user.getUserAddress().getComplex());

        DatabaseReference myRef16 = myRef.child("address").child("streetName");
        myRef16.setValue(user.getUserAddress().getStreetName());

        DatabaseReference myRef17 = myRef.child("address").child("buildingNO");
        myRef17.setValue(user.getUserAddress().getBuildingNO());

        DatabaseReference myRef18 = myRef.child("account").child("ssn");
        myRef18.setValue(user.getUserAccount().getSsn());

        DatabaseReference myRef19 = myRef.child("account").child("password");
        myRef19.setValue(user.getUserAccount().getPassword());

    }
}
