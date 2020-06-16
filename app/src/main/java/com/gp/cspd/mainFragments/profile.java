package com.gp.cspd.mainFragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.gp.cspd.R;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile extends Fragment {

    View view;
    CircleImageView profilePicture;
    TextView address,phone,ssn,email,arName,momAR,momEN,birthPlace,bloodType,gender,password,enName;

    ImageView edit_ar_name,edit_mom_ar_name,edit_mom_en_name,edit_address,edit_birth_place,edit_tel,edit_blood,edit_ssn,edit_gender,edit_email,edit_password;

    DatabaseReference mDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.activity_profile,container,false);

        profilePicture=view.findViewById(R.id.profile_pic);

        enName = view.findViewById(R.id.en_name);

        address=view.findViewById(R.id.address);
        phone=view.findViewById(R.id.telp);
        ssn=view.findViewById(R.id.ssn);
        email=view.findViewById(R.id.email);
        arName=view.findViewById(R.id.ar_name);
        momAR=view.findViewById(R.id.ar_mom_name);
        momEN=view.findViewById(R.id.en_mom_name);
        birthPlace=view.findViewById(R.id.birth_place);
        bloodType=view.findViewById(R.id.blood);
        gender=view.findViewById(R.id.gender);
        password=view.findViewById(R.id.password);

        edit_ar_name = view.findViewById(R.id.edit_ar_name);
        edit_mom_ar_name = view.findViewById(R.id.edit_ar_mom_name);
        edit_mom_en_name= view.findViewById(R.id.edit_en_mom_name);
        edit_address = view.findViewById(R.id.edit_address);
        edit_birth_place = view.findViewById(R.id.edit_birthplace);
        edit_tel= view.findViewById(R.id.edit_tel);
        edit_blood=view.findViewById(R.id.edit_blood);
        edit_ssn=view.findViewById(R.id.edit_ssn);
        edit_gender=view.findViewById(R.id.edit_gender);
        edit_email=view.findViewById(R.id.edit_email);
        edit_password = view.findViewById(R.id.edit_password);

        edit_ar_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfile(1);
            }
        });
        edit_mom_ar_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LogEditMomNam","yes in button");
                editProfile(2);
            }
        });
        edit_mom_en_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfile(3);
            }
        });
        edit_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfile(4);
            }
        });
        edit_birth_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfile(5);
            }
        });
        edit_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfile(6);
            }
        });
        edit_blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfile(7);
            }
        });
        edit_ssn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfile(8);
            }
        });
        edit_gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfile(9);
            }
        });
        edit_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfile(10);
            }
        });
        edit_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfile(11);
            }
        });

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
        getPasswordDB();


        return view;
    }

    private void editProfile(int num){
        final int choice = num;
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.edit_dialog);

        final EditText editText = dialog.findViewById(R.id.edittxt);
        Button ed = dialog.findViewById(R.id.btn_edit);
        Button canc = dialog.findViewById(R.id.btn_cancel);
        canc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editString = editText.getText().toString();
                Log.d("strVal",editString +" is the value of "+choice);
                if (editString == null || editString.equals("")){
                    Toast.makeText(getContext(),"بيانات غير صحيحه",Toast.LENGTH_LONG).show();
                }else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    AccountLoged al =AccountLoged.getInstance();
                    DatabaseReference myRef = database.getReference("users").child(al.getSsn());
                    switch (choice) {
                        case 1:
                            String [] ar = editString.split("");
                            if (ar.length!=4){
                                Toast.makeText(getContext(),"الرجاء ادخال الاسم من اربع مقاطع",Toast.LENGTH_LONG).show();
                            }else {
                                DatabaseReference myRef9 = myRef.child("arabicName");
                                myRef9.setValue(editString);
                            }
                            break;
                        case 2:
                            Log.d("case 2",editString +" is the value of "+choice);
                            DatabaseReference myRef2 = myRef.child("mothersNameArabic");
                            myRef2.setValue(editString);
                            break;
                        case 3:
                            DatabaseReference myRef1 = myRef.child("mothersNameEnglish");
                            myRef1.setValue(editString);
                            break;
                        case 4:
                            DatabaseReference myRef5 = myRef.child("livingPlace");
                            myRef5.setValue(editString);
                            break;
                        case 5:
                            DatabaseReference myRef4 = myRef.child("birthPlace");
                            myRef4.setValue(editString);
                            break;
                        case 6:
                            DatabaseReference myRef8 = myRef.child("phoneNO");
                            myRef8.setValue(editString);
                            break;
                        case 7:
                            DatabaseReference myRef6 = myRef.child("bloodType");
                            myRef6.setValue(editString);
                            break;
                        case 8:
                            DatabaseReference myRef18 = myRef.child("account").child("ssn");
                            myRef18.setValue(editString);
                            break;
                        case 9:
                            DatabaseReference myRef3 = myRef.child("gender");
                            myRef3.setValue(editString);
                            break;
                        case 10:
                            DatabaseReference myRef7 = myRef.child("email");
                            myRef7.setValue(editString);
                            break;
                        case 11:
                            DatabaseReference myRef19 = myRef.child("account").child("password");
                            myRef19.setValue(editString);
                            break;
                    }
                    dialog.dismiss();
                }

            }
        });
        dialog.show();
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
                                profilePicture.setImageBitmap(bitmap);
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
                    String pass = (String) dataSnapshot.getValue();

                    if (pass != null){
                        enName.setText(pass);
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

    private void getPasswordDB() {
        AccountLoged al = AccountLoged.getInstance();
        String strRef = "users/"+al.getSsn()+"/account/password";
        mDatabase = FirebaseDatabase.getInstance().getReference(strRef);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String pass = (String) dataSnapshot.getValue();

                    if (pass != null){
                        password.setText(pass);
                    }
                }catch (Exception e){
                    Log.d("101","error password");
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
                        gender.setText(gen);
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
                        bloodType.setText(bt);
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
                        momEN.setText(mne);
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
                        momAR.setText(mna);
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
                    String an = (String) dataSnapshot.getValue();

                    if (an != null){
                        arName.setText(an);
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
                        phone.setText(pn);
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

                    StringBuilder sb = new StringBuilder();
                    address.setText("");
                    sb.append(address.getText());
                    if (gov != null){
                        sb.append(gov+", ");
                        address.setText(sb.toString());
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

                    StringBuilder sb = new StringBuilder();
                    sb.append(address.getText());

                    if (gov != null){
                        sb.append(gov+", ");
                        address.setText(sb.toString());
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

                    StringBuilder sb = new StringBuilder();
                    sb.append(address.getText());
                    if (gov != null){
                        sb.append(gov+", ");
                        address.setText(sb.toString());
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

                    StringBuilder sb = new StringBuilder();
                    sb.append(address.getText());
                    if (gov != null){
                        sb.append(gov+", ");
                        address.setText(sb.toString());
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
        ssn.setText(al.getSsn());
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
                        email.setText(em);
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
