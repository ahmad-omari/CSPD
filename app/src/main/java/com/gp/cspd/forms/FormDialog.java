package com.gp.cspd.forms;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.gp.cspd.Database.AccountLoged;
import com.gp.cspd.MainActivity;
import com.gp.cspd.R;
import com.gp.cspd.mainFragments.profile;

import java.io.File;
import java.io.IOException;

public class FormDialog {
    TextView ssnStr=null,arF=null,arS=null,arT=null,arFam=null,enF=null,enS=null,enT=null,enFam=null,
            arMom=null,enMom=null,birthPlace=null,dateOB=null,dola=null,mohafatha=null,hayy=null,streetBuild=null
            ,nearestPl=null,mil=null,gend=null,bloood=null,phon=null;

    ImageView personalImage=null,rel1=null,rel2=null,rel3=null,rel4=null,rel5=null;

    View view;

    DatabaseReference mDatabase;

    Context context;

    private String ord,typ;

    public FormDialog(Context context) {
        this.context = context;
    }

    public String getOrd() {
        return ord;
    }

    public void setOrd(String ord) {
        this.ord = ord;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public void showFormDialog() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.form_style);


        personalImage = dialog.findViewById(R.id.personal_image);
        rel1 = dialog.findViewById(R.id.related_img_1);
        rel2 = dialog.findViewById(R.id.related_img_2);
        rel3 = dialog.findViewById(R.id.related_img_3);
        rel4 = dialog.findViewById(R.id.related_img_4);
        rel5 = dialog.findViewById(R.id.related_img_5);

        ssnStr = dialog.findViewById(R.id.ssn_number);

        arF = dialog.findViewById(R.id.f_ar_name_fst);
        arS = dialog.findViewById(R.id.f_ar_name_sec);
        arT = dialog.findViewById(R.id.f_ar_name_third);
        arFam = dialog.findViewById(R.id.f_ar_name_family);

        enF = dialog.findViewById(R.id.f_en_name_fst);
        enS = dialog.findViewById(R.id.f_en_name_sec);
        enT = dialog.findViewById(R.id.f_en_name_third);
        enFam = dialog.findViewById(R.id.f_en_name_family);

        arMom = dialog.findViewById(R.id.f_momar);
        enMom = dialog.findViewById(R.id.f_momen);

        gend = dialog.findViewById(R.id.f_gen);
        bloood = dialog.findViewById(R.id.f_blood);
        phon = dialog.findViewById(R.id.f_phone);

        birthPlace = dialog.findViewById(R.id.f_prirth_place);
        dateOB = dialog.findViewById(R.id.f_dob);

        dola = dialog.findViewById(R.id.f_country);

        mohafatha = dialog.findViewById(R.id.f_moha);
        hayy = dialog.findViewById(R.id.f_hay);
        streetBuild = dialog.findViewById(R.id.f_strname);
        nearestPl = dialog.findViewById(R.id.f_nearest);
        mil = dialog.findViewById(R.id.f_email);


        fillForm();
        fillImages();

        Button modyf = dialog.findViewById(R.id.mofy);
        modyf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context.getApplicationContext(), MainActivity.class));
            }
        });

        Button button=dialog.findViewById(R.id.ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    private void fillImages() {
  /*
        String strRef = "Forms/order";
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference(strRef);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                StringBuilder strB =new StringBuilder();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    try {
                        String oN = ds.child("order_name").getValue().toString();
                        String oT = ds.child("order_type").getValue().toString();
                        String stat = ds.child("status").getValue().toString();

                        if (oN != null && oT != null && stat != null) {
                            myPicForm(oN,oT);
                        }

                    }  catch (Exception e) {
                        Log.d("334", e.getMessage());
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/
        if (ord!= null && typ!=null)
            myPicForm(ord,typ);
    }

    private void fillForm() {
        getProfilePic();

        getEnName();

        getAdressDB();
        getPhoneDB();
        getSSNDB();
        getEmailDB();
        getArNameDB();
        getMomArDB();
        getMomEnDB();
        getBirthPlaceDB();
        getBloodTypeDB();
        getGenderDB();
    }

    private void getProfilePic() {
        AccountLoged al = AccountLoged.getInstance();
        String strRef = "UserImageFolder/"+al.getSsn()+"profilePicture.jpg";
        StorageReference reference = FirebaseStorage.getInstance().getReference().child(strRef);
        try {
            final File file = File.createTempFile(al.getSsn()+"profilePicture","jpg");
            reference.getFile(file)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Log.e("LOG1","Success retival image");
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            if (bitmap != null){
                                personalImage.setImageBitmap(bitmap);
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("LOG2","Filed retival image");
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void myPicForm(String order, String type){
        Log.d("6123","function invoked "+order+" "+type);
        AccountLoged loged = AccountLoged.getInstance();
        if (type.equals("Password")){

            if (order.equals("renew")){
                getPictureByName(loged.getSsn()+"firstPassportNote.jpg",1);
                getPictureByName(loged.getSsn()+"firstPassportBook.jpg",2);
                getPictureByName(loged.getSsn()+"firstPassportForeign.jpg",3);
            }else if (order.equals("damaged")){
                getPictureByName(loged.getSsn()+"notePassport.jpg",1);
                getPictureByName(loged.getSsn()+"bookPassport.jpg",2);
                getPictureByName(loged.getSsn()+"damagedPassport.jpg",3);
                getPictureByName(loged.getSsn()+"personalImgPassport.jpg",4);
            }else if (order.equals("lost")){
                getPictureByName(loged.getSsn()+"notePassport.jpg",1);
                getPictureByName(loged.getSsn()+"bookPassport.jpg",2);
                getPictureByName(loged.getSsn()+"secAcceptPassport.jpg",3);
                getPictureByName(loged.getSsn()+"lostImgPassport.jpg",4);
            }

        }else if (type.equals("Family Book")){

            if (order.equals("first time")){
                getPictureByName(loged.getSsn()+"marridFamilyBook.jpg",1);
                getPictureByName(loged.getSsn()+"picFamilyBook.jpg",2);
                getPictureByName(loged.getSsn()+"imgFamilyBook.jpg",3);
                getPictureByName(loged.getSsn()+"passportImgFamilyBook.jpg",4);
                getPictureByName(loged.getSsn()+"idBothFamilyBook.jpg",5);
            }

        }else if (type.equals("ID Card")){

            if (order.equals("renew")){
                getPictureByName(loged.getSsn()+"renewIdCardPersonalImage.jpg",1);
                Log.d("LogRen","in function");
            }else if (order.equals("damaged or replacement")){
                getPictureByName(loged.getSsn()+"damgedIdCardPersonal.jpg",1);
                getPictureByName(loged.getSsn()+"damgedIdCardWritten.jpg",2);
                getPictureByName(loged.getSsn()+"damgedIdCardExplain.jpg",3);
            }
        }
    }

    private void getPictureByName(String picName, final int picIndex) {

        Log.d("Log21","names "+picName);

        AccountLoged al = AccountLoged.getInstance();
        String strRef = "UserImageFolder/"+picName;
        StorageReference reference = FirebaseStorage.getInstance().getReference().child(strRef);
        try {
            final File file = File.createTempFile(al.getSsn()+"profilePicture","jpg");
            reference.getFile(file)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Log.e("LOG1","Success retival image");
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            if (bitmap != null){
                                Log.e("Log22","bitmap not null");
                                switch (picIndex){
                                    case 1:
                                        rel1.setImageBitmap(bitmap);
                                        break;
                                    case 2:
                                        rel2.setImageBitmap(bitmap);
                                        break;
                                    case 3:
                                        rel3.setImageBitmap(bitmap);
                                        break;
                                    case 4:
                                        rel4.setImageBitmap(bitmap);
                                        break;
                                    case 5:
                                        rel5.setImageBitmap(bitmap);
                                        break;
                                }
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("LOG2","Filed retival image");
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getEnName() {
        AccountLoged al = AccountLoged.getInstance();
        String strRef = "users/"+al.getSsn()+"/englishName";
        mDatabase = FirebaseDatabase.getInstance().getReference(strRef);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String enN = (String) dataSnapshot.getValue();

                    if (enN != null){
                        String [] ar = enN.split("\\s");
                        if (ar.length == 4){
                            enF.setText(ar[0]);
                            enS.setText(ar[1]);
                            enT.setText(ar[2]);
                            enFam.setText(ar[3]);
                        }
                    }
                }catch (Exception e){
                    Log.d("99","error english name");
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(getApplicationContext(), "Error try again later.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getGenderDB() {
        AccountLoged al = AccountLoged.getInstance();
        String strRef = "users/"+al.getSsn()+"/gender";
        mDatabase = FirebaseDatabase.getInstance().getReference(strRef);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String gen = (String) dataSnapshot.getValue();

                    if (gen != null){
                        gend.setText(gen);
                    }
                }catch (Exception e){
                    Log.d("102","error gender");
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(getApplicationContext(), "Error try again later.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getBloodTypeDB() {
        AccountLoged al = AccountLoged.getInstance();
        String strRef = "users/"+al.getSsn()+"/bloodType";
        mDatabase = FirebaseDatabase.getInstance().getReference(strRef);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String bt = (String) dataSnapshot.getValue();

                    if (bt != null){
                        bloood.setText(bt);
                    }
                }catch (Exception e){
                    Log.d("103","error blood type");
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(getApplicationContext(), "Error try again later.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getBirthPlaceDB() {
        AccountLoged al = AccountLoged.getInstance();
        String strRef = "users/"+al.getSsn()+"/birthPlace";
        mDatabase = FirebaseDatabase.getInstance().getReference(strRef);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String bp = (String) dataSnapshot.getValue();

                    if (bp != null){

                        birthPlace.setText(bp);
                    }
                }catch (Exception e){
                    Log.d("104","error birth type");
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(getApplicationContext(), "Error try again later.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getMomEnDB() {
        AccountLoged al = AccountLoged.getInstance();
        String strRef = "users/"+al.getSsn()+"/mothersNameEnglish";
        mDatabase = FirebaseDatabase.getInstance().getReference(strRef);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String mne = (String) dataSnapshot.getValue();
                    if (mne != null){
                        enMom.setText(mne);
                    }
                }catch (Exception e){
                    Log.d("105","error mothers name english");
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(getApplicationContext(), "Error try again later.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getMomArDB() {
        AccountLoged al = AccountLoged.getInstance();
        String strRef = "users/"+al.getSsn()+"/mothersNameArabic";
        mDatabase = FirebaseDatabase.getInstance().getReference(strRef);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String mna = (String) dataSnapshot.getValue();

                    if (mna != null){
                        arMom.setText(mna);
                    }
                }catch (Exception e){
                    Log.d("106","error mothers name arabic");
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(getApplicationContext(), "Error try again later.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getArNameDB() {
        AccountLoged al = AccountLoged.getInstance();
        String strRef = "users/"+al.getSsn()+"/arabicName";
        mDatabase = FirebaseDatabase.getInstance().getReference(strRef);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String arN = (String) dataSnapshot.getValue();

                    if (arN != null){
                        String [] ar = arN.split("\\s");
                        if (ar.length == 4){
                            arF.setText(ar[0]);
                            arS.setText(ar[1]);
                            arT.setText(ar[2]);
                            arFam.setText(ar[3]);
                        }
                    }
                }catch (Exception e){
                    Log.d("107","error name arabic");
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(getApplicationContext(), "Error try again later.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getPhoneDB() {
        AccountLoged al = AccountLoged.getInstance();
        String strRef = "users/"+al.getSsn()+"/phoneNO";
        mDatabase = FirebaseDatabase.getInstance().getReference(strRef);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String pn = String.valueOf(dataSnapshot.getValue()) ;

                    if (pn != null){
                        phon.setText(pn+"");
                    }
                }catch (Exception e){
                    Log.d("108","error phone number");
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(getApplicationContext(), "Error try again later.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getAdressDB() {

        AccountLoged al = AccountLoged.getInstance();
        String strRef = "users/"+al.getSsn()+"/address/governorate";
        mDatabase = FirebaseDatabase.getInstance().getReference(strRef);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String gov = (String) dataSnapshot.getValue();

                    if (gov != null){
                        dola.setText("Jordan");
                        mohafatha.setText(gov);
                    }
                }catch (Exception e){
                    Log.d("110","error phone number");
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(getApplicationContext(), "Error try again later.", Toast.LENGTH_LONG).show();
            }
        });

        strRef = "users/"+al.getSsn()+"/address/neighborhood";
        mDatabase = FirebaseDatabase.getInstance().getReference(strRef);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String gov = (String) dataSnapshot.getValue();


                    if (gov != null){
                        nearestPl.setText(gov);
                        hayy.setText(gov);
                    }
                }catch (Exception e){
                    Log.d("111","error phone number");
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(getApplicationContext(), "Error try again later.", Toast.LENGTH_LONG).show();
            }
        });

        strRef = "users/"+al.getSsn()+"/address/streetName";
        mDatabase = FirebaseDatabase.getInstance().getReference(strRef);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String gov = (String) dataSnapshot.getValue();

                    if (gov != null){
                        streetBuild.setText(gov);
                    }
                }catch (Exception e){
                    Log.d("112","error phone number");
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(getApplicationContext(), "Error try again later.", Toast.LENGTH_LONG).show();
            }
        });

        strRef = "users/"+al.getSsn()+"/address/buildingNO";
        mDatabase = FirebaseDatabase.getInstance().getReference(strRef);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String gov = String.valueOf(dataSnapshot.getValue());

                    if (gov != null){
                        streetBuild.append("/"+gov);
                    }
                }catch (Exception e){
                    Log.d("113","error phone number");
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(getApplicationContext(), "Error try again later.", Toast.LENGTH_LONG).show();
            }
        });


    }

    private void getSSNDB() {
        AccountLoged al = AccountLoged.getInstance();
        ssnStr.setText(al.getSsn()+"");
    }

    private void getEmailDB() {
        AccountLoged al = AccountLoged.getInstance();
        String strRef = "users/"+al.getSsn()+"/email";
        mDatabase = FirebaseDatabase.getInstance().getReference(strRef);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String em = (String) dataSnapshot.getValue();

                    if (em != null){
                        mil.setText(em);
                    }
                }catch (Exception e){
                    Log.d("109","error email");
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(getApplicationContext(), "Error try again later.", Toast.LENGTH_LONG).show();
            }
        });
    }


}
