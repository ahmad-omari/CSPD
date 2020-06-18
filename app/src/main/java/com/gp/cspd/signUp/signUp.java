package com.gp.cspd.signUp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gp.cspd.Database.DatabaseController;
import com.gp.cspd.Database.DatabaseUserImage;
import com.gp.cspd.MainActivity;
import com.gp.cspd.R;
import com.gp.cspd.deathCertificate.deathForms;
import com.gp.cspd.login.login_page;
import com.gp.cspd.userInformation.Address;
import com.gp.cspd.userInformation.User;
import com.gp.cspd.userInformation.UserAccount;

import java.io.IOException;
import java.util.Calendar;

public class signUp extends AppCompatActivity implements View.OnClickListener ,
        AdapterView.OnItemSelectedListener {

    private final int REQUEST_SIGNATURE=101;
    private final int REQUEST_PROFILE_PICTURE=102;
    private final int REQUEST_AUTH_IMAGE=103;

    private Uri Uri_signature;
    private Uri Uri_profile_pic;
    private Uri Uri_auth_pic;

    private TextView dob;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private String dateOfBirth,bloodTypeSTR,governorateSTR,gender;

    private Spinner bloodType ,governorateSP;

    private EditText en_name_1,en_name_2,en_name_3,en_name_4
            ,ar_name_1,ar_name_2,ar_name_3,ar_name_4
            ,en_mother_name,ar_mother_name
            ,birthPlace
            ,current_place
            ,phoneNO
            ,ssnET
            ,lewa,hay,neighbor_complex,streetName,BuildingNO,nearestNeighbor
            ,emailET,passwordET,confirmPasswordET;


    private RadioButton male,female;

    private User userAccountInformation;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        en_name_1 = findViewById(R.id.en_name_1);
        en_name_2 = findViewById(R.id.en_name_2);
        en_name_3 = findViewById(R.id.en_name_3);
        en_name_4 = findViewById(R.id.en_name_4);
        ar_name_1 = findViewById(R.id.ar_name_1);
        ar_name_2 = findViewById(R.id.ar_name_2);
        ar_name_3 = findViewById(R.id.ar_name_3);
        ar_name_4 = findViewById(R.id.ar_name_4);
        en_mother_name = findViewById(R.id.en__mom_name_);
        ar_mother_name = findViewById(R.id.ar_mom_name);
        birthPlace = findViewById(R.id.ar_birth_place);
        current_place = findViewById(R.id.current_place);
        phoneNO = findViewById(R.id.phone_NO);
        lewa = findViewById(R.id.lewa);
        hay = findViewById(R.id.alhay);
        neighbor_complex = findViewById(R.id.tajamo_sokani);
        streetName = findViewById(R.id.st_name);
        BuildingNO = findViewById(R.id.bilding_no);
        nearestNeighbor = findViewById(R.id.nearest_place);
        emailET = findViewById(R.id.email);
        passwordET = findViewById(R.id.password);
        confirmPasswordET = findViewById(R.id.password_conf);
        ssnET = findViewById(R.id.ssn);

        male = findViewById(R.id.male_rb);
        female = findViewById(R.id.female_rb);


        male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                gender = "Male";
                if (female.isChecked())
                    female.setChecked(false);
            }
        });

        female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                gender = "Female";
                if (male.isChecked())
                    male.setChecked(false);
            }
        });



        dob = findViewById(R.id.date_of_birth);
        bloodType = findViewById(R.id.blood_spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(signUp.this,R.array.blood_type,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodType.setAdapter(arrayAdapter);
        bloodType.setOnItemSelectedListener(this);

        governorateSP = findViewById(R.id.governorate);
        ArrayAdapter<CharSequence> arrayAdapterGov = ArrayAdapter.createFromResource(signUp.this,R.array.governorates,android.R.layout.simple_spinner_item);
        arrayAdapterGov.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        governorateSP.setAdapter(arrayAdapterGov);
        governorateSP.setOnItemSelectedListener(this);

        findViewById(R.id.date_ic).setOnClickListener(this);
        findViewById(R.id.auth_pic_info).setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);
        findViewById(R.id.sign_up_button).setOnClickListener(this);

        findViewById(R.id.signature).setOnClickListener(this);
        findViewById(R.id.profile_picture).setOnClickListener(this);
        findViewById(R.id.auth_picture).setOnClickListener(this);

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                dob.setText(day+"/"+(month+1)+"/"+year);
                dob.setTextColor(Color.DKGRAY);
                dateOfBirth = day+"/"+(month+1)+"/"+year;
            }
        };


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.date_ic:

                    Calendar calendar= Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(signUp.this,R.style.calenderTheme,dateSetListener,
                        year,month,day);
           //     datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    datePickerDialog.show();
                break;

            case R.id.auth_pic_info:
                    authInfoDialog();
                break;

            case R.id.cancel:
                    startActivity(new Intent(signUp.this, login_page.class));
                    finish();
                break;

            case R.id.sign_up_button:
                    signUp();
                break;

            case R.id.signature:
                    imagePickerSignature();
                break;

            case R.id.profile_picture:
                    imagePickerProfilePic();
                break;

            case R.id.auth_picture:
                    imagePickerAuthIMG();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.blood_spinner:
                int po = adapterView.getPositionForView(view);
                String bloodTypeString = adapterView.getItemAtPosition(po).toString();

                bloodTypeSTR = bloodTypeString;
                if (!bloodTypeString.equals("-"))
                    Toast.makeText(getApplicationContext(),bloodTypeString,Toast.LENGTH_LONG).show();
                break;

            case R.id.governorate:

                String governorateString = adapterView.getItemAtPosition(i).toString();

                governorateSTR = governorateString;

                if (!governorateString.equals("-"))
                    Toast.makeText(getApplicationContext(),governorateString,Toast.LENGTH_LONG).show();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void authInfoDialog(){
        final Dialog dialog = new Dialog(signUp.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.auth_info_dialog);
        dialog.setTitle("Image view info");

        Button button=dialog.findViewById(R.id.ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public void imagePickerDialog(Bitmap bitmap){
        final Dialog dialog = new Dialog(signUp.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.auth_info_dialog);
        dialog.setTitle("Image view info");

        Button button=dialog.findViewById(R.id.ok);
        ImageView imageView = dialog.findViewById(R.id.auth_pic_i);
        imageView.setImageBitmap(bitmap);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public void imagePickerSignature(){
        Intent objectIntent = new Intent();
        objectIntent.setType("image/");
        objectIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(objectIntent,REQUEST_SIGNATURE);
    }

    public void imagePickerProfilePic(){
        Intent objectIntent = new Intent();
        objectIntent.setType("image/");
        objectIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(objectIntent,REQUEST_PROFILE_PICTURE);
    }

    public void imagePickerAuthIMG(){
        Intent objectIntent = new Intent();
        objectIntent.setType("image/");
        objectIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(objectIntent,REQUEST_AUTH_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_SIGNATURE && resultCode == RESULT_OK && data != null && data.getData() != null) {
                Uri_signature = data.getData();
                Bitmap objectBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri_signature);
                imagePickerDialog(objectBitmap);

            }

            if (requestCode == REQUEST_PROFILE_PICTURE && resultCode == RESULT_OK && data != null && data.getData() != null) {
                Uri_profile_pic = data.getData();
                Bitmap objectBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri_profile_pic);
                imagePickerDialog(objectBitmap);
            }

            if (requestCode == REQUEST_AUTH_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
                Uri_auth_pic = data.getData();
                Bitmap objectBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri_auth_pic);
                imagePickerDialog(objectBitmap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadImages(){
        if (Uri_auth_pic!=null && Uri_signature!=null && Uri_profile_pic!=null && userAccountInformation!=null) {
            UserAccount account = userAccountInformation.getUserAccount();

            String signatureImageName = account.getSsn() + "signature." + getExtention(Uri_signature);
            String authImageName = account.getSsn() + "Authentication." + getExtention(Uri_auth_pic);
            String profilePicImageName = account.getSsn() + "profilePicture." + getExtention(Uri_profile_pic);

            DatabaseUserImage signatureUserImage = new DatabaseUserImage(signatureImageName, Uri_signature);
            DatabaseUserImage authUserImage = new DatabaseUserImage(authImageName, Uri_auth_pic);
            DatabaseUserImage profilePicUserImage = new DatabaseUserImage(profilePicImageName, Uri_profile_pic);
        }else
            Toast.makeText(getApplicationContext(),"الرجاء ادخال جميع الصور المطلوبه",Toast.LENGTH_LONG).show();

    }

    public String getExtention(Uri uri){
        try {
            ContentResolver objectContentResolver = getContentResolver();
            MimeTypeMap objectMimeTypeMap = MimeTypeMap.getSingleton();

            return objectMimeTypeMap.getExtensionFromMimeType(objectContentResolver.getType(uri));
        }catch (Exception e){e.printStackTrace();}

        return null;
    }

    public void signUp(){
        fillData();
       // uploadUserInfo();
        uploadImages();
        if (Uri_auth_pic!=null && Uri_signature!=null && Uri_profile_pic!=null && ssnET!=null &&
                 passwordET!=null && confirmPasswordET!=null &&
        ar_name_1!=null && ar_name_2!=null && ar_name_3!=null && ar_name_4!=null &&
                en_name_1!=null && en_name_2!=null && en_name_3!=null && en_name_4!=null && (
                en_name_1.getText().toString().length()>2  && en_name_2.getText().toString().length()>2 && en_name_3.getText().toString().length()>2 && en_name_4.getText().toString().length()>2
                && emailET!=null && emailET.getText().toString().length()<5 && ssnET.getText().toString().length()==10
                        && passwordET.getText().toString().length()>6
                        &&confirmPasswordET.getText().toString().length()>6 &&
        ar_name_1.getText().toString().length()>2  && ar_name_2.getText().toString().length()>2 && ar_name_3.getText().toString().length()>2 && ar_name_4.getText().toString().length()>2
        )
        ) {
            showSuccessDialog();

        }else{if (!passwordET.getText().toString().equals(confirmPasswordET.getText().toString())) {
            confirmPasswordET.setError("غير مطابق");
        }

            if (ssnET==null || ssnET.length()!=10)
                ssnET.setError("الرجاء ادخال عشر خانات");
             if (passwordET==null || passwordET.length()<6)
                passwordET.setError("الرجاء ادخال ست خانات على الاقل");

             if (confirmPasswordET==null || confirmPasswordET.length()<6)
                confirmPasswordET.setError("الرجاء ادخال ست خانات على الاقل");

             if (ar_name_1==null || ar_name_1.length()<2)
                ar_name_1.setError("");

             if (ar_name_2==null || ar_name_2.length()<2)
                ar_name_2.setError("");

             if (ar_name_3==null || ar_name_3.length()<2)
                ar_name_3.setError("");

             if (ar_name_4==null || ar_name_4.length()<2)
                ar_name_4.setError("");

             if (en_name_1==null || en_name_1.length()<2)
                en_name_1.setError("");

             if (en_name_2==null || en_name_2.length()<2)
                en_name_2.setError("");

             if (en_name_3==null || en_name_3.length()<2)
                en_name_3.setError("");

             if (en_name_4==null || en_name_4.length()<2)
                en_name_4.setError("");
             if (emailET==null || emailET.getText().toString().length()<5)
                emailET.setError("");
        }
    }

    private void showSuccessDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.success_account);
        Button btn = dialog.findViewById(R.id.done);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signUp.this, login_page.class));
                finish();
            }
        });
        dialog.show();

    }

    private void fillData() {
        String en_name_1STR = en_name_1.getText().toString().trim();
        String en_name_2STR = en_name_2.getText().toString().trim();
        String en_name_3STR = en_name_3.getText().toString().trim();
        String en_name_4STR = en_name_4.getText().toString().trim();
        String ar_name_1STR = ar_name_1.getText().toString().trim();
        String ar_name_2STR = ar_name_2.getText().toString().trim();
        String ar_name_3STR = ar_name_3.getText().toString().trim();
        String ar_name_4STR = ar_name_4.getText().toString().trim();
        String en_mother_nameSTR = en_mother_name.getText().toString().trim();
        String ar_mother_nameSTR = ar_mother_name.getText().toString().trim();
        String birthPlaceSTR = birthPlace.getText().toString().trim();
        String current_placeSTR = current_place.getText().toString().trim();
        String phoneNOSTR = phoneNO.getText().toString().trim();
        String lewaSTR = lewa.getText().toString().trim();
        String haySTR = hay.getText().toString().trim();
        String neighbor_complexSTR = neighbor_complex.getText().toString().trim();
        String streetNameSTR = streetName.getText().toString().trim();
        String BuildingNOSTR = BuildingNO.getText().toString().trim();
        String nearestNeighborSTR = nearestNeighbor.getText().toString().trim();
        String emailETSTR = emailET.getText().toString().trim();
        String passwordETSTR = passwordET.getText().toString().trim();
        String confirmPasswordETSTR = confirmPasswordET.getText().toString().trim();
        String ssnSTR = ssnET.getText().toString().trim();

        if (passwordETSTR.equals(confirmPasswordETSTR) && ssnSTR.length()==10){
            userAccountInformation = new User(ssnSTR , passwordETSTR);

            userAccountInformation.setNameEnglish4Parts(en_name_1STR , en_name_2STR ,en_name_3STR, en_name_4STR);
            userAccountInformation.setNameArabic4Parts(ar_name_1STR, ar_name_2STR, ar_name_3STR, ar_name_4STR);

            userAccountInformation.setMothersNameArabic(ar_mother_nameSTR);
            userAccountInformation.setMothersNameEnglish(en_mother_nameSTR);
            userAccountInformation.setBirthPlace(birthPlaceSTR);
            userAccountInformation.setLivingPlace(current_placeSTR);
            int phoneNoInt=0,buildingNoInt=0;
            try {
                phoneNoInt = Integer.parseInt(phoneNOSTR);
                buildingNoInt = Integer.parseInt(BuildingNOSTR);
            }catch (Exception e){
                e.printStackTrace();
            }

            userAccountInformation.setPhoneNO(phoneNoInt);
            userAccountInformation.setEmail(emailETSTR);
            userAccountInformation.setDob(dateOfBirth);
            userAccountInformation.setBloodType(bloodTypeSTR);
            userAccountInformation.setGender(gender);

            Address address = new Address();
            if (lewaSTR!=null && haySTR!=null && nearestNeighborSTR!=null && neighbor_complexSTR!=null && streetNameSTR!=null
            && buildingNoInt!=0 && governorateSTR!=null) {
                address.setBanner(lewaSTR);
                address.setNeighborhood(haySTR);
                address.setNearestNeighborhood(nearestNeighborSTR);
                address.setComplex(neighbor_complexSTR);
                address.setStreetName(streetNameSTR);
                address.setBuildingNO(buildingNoInt);
                address.setGovernorate(governorateSTR);
                userAccountInformation.setUserAddress(address);
                uploadUserInfo();
            }

        }

    }

    public void uploadUserInfo(){
        DatabaseController uploadUserInfo = new DatabaseController();

        String lewaSTR = lewa.getText().toString().trim();
        String haySTR = hay.getText().toString().trim();
        String neighbor_complexSTR = neighbor_complex.getText().toString().trim();
        String streetNameSTR = streetName.getText().toString().trim();
        String BuildingNOSTR = BuildingNO.getText().toString().trim();
        String nearestNeighborSTR = nearestNeighbor.getText().toString().trim();
        int buildingNoInt=0;
        try {
            buildingNoInt = Integer.parseInt(BuildingNOSTR);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (lewaSTR!=null && haySTR!=null && nearestNeighborSTR!=null && neighbor_complexSTR!=null && streetNameSTR!=null
                && buildingNoInt!=0 && governorateSTR!=null) {
            uploadUserInfo.addUser(userAccountInformation);
        }
    }
}
