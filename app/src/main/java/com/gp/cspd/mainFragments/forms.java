package com.gp.cspd.mainFragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.gp.cspd.forms.RecycleViewForm;
import com.gp.cspd.forms.RequestFormDB;
import com.gp.cspd.signUp.signUp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class forms extends Fragment {

    TextView ssnStr=null,arF=null,arS=null,arT=null,arFam=null,enF=null,enS=null,enT=null,enFam=null,
            arMom=null,enMom=null,birthPlace=null,dateOB=null,dola=null,mohafatha=null,hayy=null,streetBuild=null
            ,nearestPl=null,mil=null,gend=null,bloood=null,phon=null;
    ImageView personalImage=null,rel1=null,rel2=null,rel3=null;

    View view;

    DatabaseReference mDatabase;

    private  List<RequestFormDB> dbList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_forms,container,false);


        String strRef = "Forms/order";
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference(strRef);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                StringBuilder strB =new StringBuilder();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    try {
                        RequestFormDB rq = new RequestFormDB();

                        StringBuilder stringBuilder = new StringBuilder();

                        String oN = ds.child("order_name").getValue().toString();
                        String oT = ds.child("order_type").getValue().toString();
                        String stat = ds.child("status").getValue().toString();
                        String ss = ds.child("ssn").getValue().toString();

                        if (oN != null && oT != null && stat != null && ss != null) {
                            AccountLoged al = AccountLoged.getInstance();
                            if (ss.equals(al.getSsn())) {
                                stringBuilder.append(oN + " " + oT);
                                rq.setFormName(stringBuilder.toString());

                                Log.d("Log9876","Added "+stringBuilder.toString());

                                rq.setStatus(stat);

                                if (!strB.toString().contains(oN + " " + oT + " " + stat))
                                    dbList.add(rq);
                                else
                                    strB.append(oN + " " + oT + " " + stat);
                                Log.d("lnnnn", dbList.size() + "");
                                Log.d("333", "workkkkkkkkk" + oN + " " + oT + " " + stat);


                                recyclerView = view.findViewById(R.id.RecycleViewForms);
                                //recyclerView.setHasFixedSize(true);
                                recyclerView.setHasFixedSize(true);
                                // use a linear layout manager
                                final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                                recyclerView.setLayoutManager(layoutManager);

                                // specify an adapter (see also next example)
                                Log.d("lengtttt", dbList.size() + "");
                                mAdapter = new RecycleViewForm(dbList);
                                recyclerView.setAdapter(mAdapter);
                            }

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

        return view;
    }

    private void showFormDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.form_style);

        personalImage = dialog.findViewById(R.id.personal_image);
        rel1 = dialog.findViewById(R.id.related_img_1);
        rel2 = dialog.findViewById(R.id.related_img_2);
        rel3 = dialog.findViewById(R.id.related_img_3);

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
        Button button=dialog.findViewById(R.id.ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
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
