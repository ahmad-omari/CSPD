package com.gp.cspd.IdCard;

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
import com.gp.cspd.MainActivity;
import com.gp.cspd.R;
import com.gp.cspd.forms.FormDialog;
import com.gp.cspd.forms.FormsRequest;
import com.gp.cspd.forms.RenewIdCardForm;
import com.gp.cspd.signUp.signUp;
import com.gp.cspd.userInformation.UserAccount;

import java.io.IOException;

public class damagedReplacementCard extends AppCompatActivity implements View.OnClickListener{
    private final int REQUEST_EXPLORATION=123;
    private final int REQUEST_PERSONAL_PICTURE=124;
    private final int REQUEST_WRITTEN=125;

    private Uri Uri_explon;
    private Uri Uri_personal_pic;
    private Uri Uri_written;

    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Bitmap bitmap3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damaged_replacement);

        bitmap1=null;
        bitmap2=null;
        bitmap3=null;

        findViewById(R.id.cancel).setOnClickListener(this);
        findViewById(R.id.show_my_form).setOnClickListener(this);
        findViewById(R.id.btn_personal_pic).setOnClickListener(this);
        findViewById(R.id.btn_expl).setOnClickListener(this);
        findViewById(R.id.btn_written_pledge).setOnClickListener(this);
        findViewById(R.id.btn_get_renew_form_sub).setOnClickListener(this);

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(damagedReplacementCard.this, idCardForms.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancel:
                startActivity(new Intent(damagedReplacementCard.this, MainActivity.class));
                finish();
                break;

            case R.id.show_my_form:
                FormDialog formDialog = new FormDialog(this);
                formDialog.showFormDialog();
                break;

            case R.id.btn_personal_pic:
                imagePickerPersonal();
                break;
            case R.id.btn_expl:
                imagePickerExplo();
                break;
            case R.id.btn_written_pledge:
                imagePickerWritten();
                break;
            case R.id.btn_get_renew_form_sub:
                doSubmit();
                break;
        }
    }

    private void doSubmit() {
        if (bitmap1 == null || bitmap2 == null ||bitmap3 == null ){
            Toast.makeText(getApplicationContext(),"Please choose image...",Toast.LENGTH_LONG).show();
        }else {
            uploadImages();
            setFormDB("damaged or replacement");
            Toast.makeText(getApplicationContext(),"Submitted successfully",Toast.LENGTH_LONG).show();
        }
    }
    private void setFormDB(String fName) {
        DatabaseForm databaseForm = new DatabaseForm();
        databaseForm.setOrderName("ID Card");
        databaseForm.setFormName(fName);
        databaseForm.uploadDB();
    }

    public void imagePickerDialog(Bitmap bitmap){
        final Dialog dialog = new Dialog(damagedReplacementCard.this);
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void uploadImages(){
        AccountLoged account = AccountLoged.getInstance();

        String signatureImageName= account.getSsn()+"damgedIdCardPersonal."+getExtention(Uri_personal_pic);
        String authImageName= account.getSsn()+"damgedIdCardWritten."+getExtention(Uri_written);
        String profilePicImageName= account.getSsn()+"damgedIdCardExplain."+getExtention(Uri_explon);

        DatabaseUserImage signatureUserImage = new DatabaseUserImage(signatureImageName,Uri_personal_pic);
        DatabaseUserImage authUserImage = new DatabaseUserImage(authImageName,Uri_written);
        DatabaseUserImage profilePicUserImage = new DatabaseUserImage(profilePicImageName,Uri_explon);

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
