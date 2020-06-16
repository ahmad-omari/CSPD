package com.gp.cspd.passport;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.gp.cspd.Database.AccountLoged;
import com.gp.cspd.Database.DatabaseForm;
import com.gp.cspd.Database.DatabaseUserImage;
import com.gp.cspd.IdCard.damagedReplacementCard;
import com.gp.cspd.MainActivity;
import com.gp.cspd.R;
import com.gp.cspd.birthCertificate.birthForms;
import com.gp.cspd.forms.FormDialog;

import java.io.IOException;

public class replacementOfLostPassport extends AppCompatActivity implements View.OnClickListener{
    private final int REQUEST_EXPLORATION=123;
    private final int REQUEST_PERSONAL_PICTURE=124;
    private final int REQUEST_WRITTEN=125;
    private final int REQUEST_LOST=126;

    private Uri Uri_explon;
    private Uri Uri_personal_pic;
    private Uri Uri_written;
    private Uri Uri_lost;

    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Bitmap bitmap3;
    private Bitmap bitmap4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replacement_of_lost_passport);

        bitmap1=null;
        bitmap2=null;
        bitmap3=null;
        bitmap4=null;

        findViewById(R.id.cancel).setOnClickListener(this);
        findViewById(R.id.show_my_form).setOnClickListener(this);
        findViewById(R.id.btn_note_service).setOnClickListener(this);
        findViewById(R.id.btn_acceptance_book).setOnClickListener(this);
        findViewById(R.id.btn_security_acceptance).setOnClickListener(this);
        findViewById(R.id.btn_security_lost).setOnClickListener(this);
        findViewById(R.id.btn_get_renew_form).setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(replacementOfLostPassport.this, passportForms.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancel:
                startActivity(new Intent(replacementOfLostPassport.this, MainActivity.class));
                finish();
                break;
            case R.id.show_my_form:
                FormDialog formDialog = new FormDialog(this);
                formDialog.showFormDialog();
                break;

            case R.id.btn_note_service:
                imagePickerPersonal();
                break;
            case R.id.btn_acceptance_book:
                imagePickerExplo();
                break;
            case R.id.btn_security_acceptance:
                imagePickerWritten();
                break;
            case R.id.btn_security_lost:
                imagePickerFour();
                break;
            case R.id.btn_get_renew_form:
                doSubmit();
                break;
        }
    }

    private void doSubmit() {
        if (bitmap1 == null || bitmap2 == null ||bitmap3 == null || bitmap4==null ){
            Toast.makeText(getApplicationContext(),"Please choose image...",Toast.LENGTH_LONG).show();
        }else {
            uploadImages();
            setFormDB("lost");
            showSuccessDialog();
        }
    }
    private void showSuccessDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.accepted_form_dialog);
        Button btn = dialog.findViewById(R.id.done);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(replacementOfLostPassport.this, MainActivity.class));
                finish();
            }
        });
        dialog.show();
    }
    private void setFormDB(String fName) {
        DatabaseForm databaseForm = new DatabaseForm();
        databaseForm.setOrderName("Passport");
        databaseForm.setFormName(fName);
        databaseForm.uploadDB();
    }

    public void imagePickerDialog(Bitmap bitmap){
        final Dialog dialog = new Dialog(replacementOfLostPassport.this);
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

    public void imagePickerPersonal(){
        Intent objectIntent = new Intent();
        objectIntent.setType("image/");
        objectIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(objectIntent,REQUEST_PERSONAL_PICTURE);
    }

    public void imagePickerExplo(){
        Intent objectIntent = new Intent();
        objectIntent.setType("image/");
        objectIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(objectIntent,REQUEST_EXPLORATION);
    }

    public void imagePickerWritten(){
        Intent objectIntent = new Intent();
        objectIntent.setType("image/");
        objectIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(objectIntent,REQUEST_WRITTEN);
    }

    public void imagePickerFour(){
        Intent objectIntent = new Intent();
        objectIntent.setType("image/");
        objectIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(objectIntent,REQUEST_LOST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_PERSONAL_PICTURE && resultCode == RESULT_OK && data != null && data.getData() != null) {
                Uri_personal_pic = data.getData();
                Bitmap objectBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri_personal_pic);
                bitmap1=objectBitmap;
                imagePickerDialog(objectBitmap);

            }

            if (requestCode == REQUEST_EXPLORATION && resultCode == RESULT_OK && data != null && data.getData() != null) {
                Uri_explon = data.getData();
                Bitmap objectBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri_explon);
                bitmap2=objectBitmap;
                imagePickerDialog(objectBitmap);
            }

            if (requestCode == REQUEST_WRITTEN && resultCode == RESULT_OK && data != null && data.getData() != null) {
                Uri_written = data.getData();
                Bitmap objectBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri_written);
                bitmap3=objectBitmap;
                imagePickerDialog(objectBitmap);
            }

            if (requestCode == REQUEST_LOST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                Uri_lost = data.getData();
                Bitmap objectBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri_lost);
                bitmap4=objectBitmap;
                imagePickerDialog(objectBitmap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void uploadImages(){
        AccountLoged account = AccountLoged.getInstance();

        String signatureImageName= account.getSsn()+"notePassport."+getExtention(Uri_personal_pic);
        String authImageName= account.getSsn()+"bookPassport."+getExtention(Uri_written);
        String profilePicImageName= account.getSsn()+"secAcceptPassport."+getExtention(Uri_explon);
        String lost= account.getSsn()+"lostImgPassport."+getExtention(Uri_explon);

        DatabaseUserImage signatureUserImage = new DatabaseUserImage(signatureImageName,Uri_personal_pic);
        DatabaseUserImage authUserImage = new DatabaseUserImage(authImageName,Uri_written);
        DatabaseUserImage profilePicUserImage = new DatabaseUserImage(profilePicImageName,Uri_explon);
        DatabaseUserImage lostUsImg = new DatabaseUserImage(lost,Uri_lost);

    }

    public String getExtention(Uri uri){
        try {
            ContentResolver objectContentResolver = getContentResolver();
            MimeTypeMap objectMimeTypeMap = MimeTypeMap.getSingleton();

            return objectMimeTypeMap.getExtensionFromMimeType(objectContentResolver.getType(uri));
        }catch (Exception e){e.printStackTrace();}

        return null;
    }
}
