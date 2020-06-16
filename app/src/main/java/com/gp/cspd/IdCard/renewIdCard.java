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
import com.gp.cspd.birthCertificate.birthForms;
import com.gp.cspd.forms.FormDialog;
import com.gp.cspd.forms.FormsRequest;
import com.gp.cspd.forms.RenewIdCardForm;
import com.gp.cspd.signUp.signUp;
import com.gp.cspd.userInformation.UserAccount;

import java.io.IOException;

public class renewIdCard extends AppCompatActivity implements View.OnClickListener {
    private final int REQUEST_IMAGE = 222;
    private Uri uriIMG;
    private Bitmap imgBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renew_id_card);

        uriIMG = null;
        imgBitmap = null;

        findViewById(R.id.cancel).setOnClickListener(this);
        findViewById(R.id.show_my_form).setOnClickListener(this);
        findViewById(R.id.personal_picture).setOnClickListener(this);
        findViewById(R.id.btn_get_renew_form).setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(renewIdCard.this, idCardForms.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancel:
                startActivity(new Intent(renewIdCard.this, MainActivity.class));
                finish();
                break;

            case R.id.show_my_form:
                FormDialog formDialog = new FormDialog(this);
                formDialog.showFormDialog();
                break;

            case R.id.personal_picture:
                uploadImage();
                break;

            case R.id.btn_get_renew_form:
                doSubmit();
                break;
        }
    }

    private void doSubmit() {
        if (imgBitmap == null){
            Toast.makeText(getApplicationContext(),"Please choose image...",Toast.LENGTH_LONG).show();
        }else {
            uploadImages();
            setFormDB("renew");
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
                startActivity(new Intent(renewIdCard.this, MainActivity.class));
                finish();
            }
        });
        dialog.show();
    }

    private void setFormDB(String fName) {
        DatabaseForm databaseForm = new DatabaseForm();
        databaseForm.setOrderName("ID Card");
        databaseForm.setFormName(fName);
        databaseForm.uploadDB();
    }

    private void uploadImage() {
        imagePicker();
    }

    public void imagePickerDialog(Bitmap bitmap){
        final Dialog dialog = new Dialog(renewIdCard.this);
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

    public void imagePicker(){
        Intent objectIntent = new Intent();
        objectIntent.setType("image/");
        objectIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(objectIntent,REQUEST_IMAGE);
    }

    public void uploadImages(){
        AccountLoged account = AccountLoged.getInstance();

        String signatureImageName= account.getSsn()+"renewIdCardPersonalImage."+getExtention(uriIMG);

        DatabaseUserImage signatureUserImage = new DatabaseUserImage(signatureImageName,uriIMG);

    }

    public String getExtention(Uri uri){
        try {
            ContentResolver objectContentResolver = getContentResolver();
            MimeTypeMap objectMimeTypeMap = MimeTypeMap.getSingleton();

            return objectMimeTypeMap.getExtensionFromMimeType(objectContentResolver.getType(uri));
        }catch (Exception e){e.printStackTrace();}

        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
                uriIMG = data.getData();
                Bitmap objectBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriIMG);
                imgBitmap = objectBitmap;
                imagePickerDialog(objectBitmap);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
