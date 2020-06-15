package com.gp.cspd.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gp.cspd.Database.AccountLoged;
import com.gp.cspd.MainActivity;
import com.gp.cspd.R;
import com.gp.cspd.signUp.signUp;

public class login_page extends AppCompatActivity implements View.OnClickListener {

    EditText ssn,password;
    CheckBox rememberMe;
    ProgressBar progressBar;
    TextView signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        ssn=findViewById(R.id.edit_text_ssn);
        password=findViewById(R.id.edit_text_password);
        rememberMe=findViewById(R.id.checkbox_rememberme);
        progressBar=findViewById(R.id.progress_circular);
        findViewById(R.id.button_signin).setOnClickListener(this);
        findViewById(R.id.sign_up_text).setOnClickListener(this);

        progressBar.setVisibility(ProgressBar.INVISIBLE);


      //  imagePickerDialog();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_signin:
                progressBar.setVisibility(ProgressBar.VISIBLE);
                String ssnStr=ssn.getText().toString().trim();
                String passwordStr=password.getText().toString().trim();
                login(ssnStr,passwordStr);

              //      startActivity(new Intent(login_page.this, MainActivity.class));
              //      finish();
                break;
            case R.id.sign_up_text:
                startActivity(new Intent(login_page.this, com.gp.cspd.signUp.signUp.class));
                finish();
                break;
        }
    }
    private void login(final String ssnStr, final String passwordStr) {
        DatabaseReference mDatabase;
        String strRef = "users/"+ssnStr+"/account/password";
        mDatabase = FirebaseDatabase.getInstance().getReference(strRef);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String password = (String) dataSnapshot.getValue();

                if (password != null && password.equals(passwordStr)){
                    accountHandler(ssnStr);

                    if (rememberMe.isChecked()){
                        SharedPreferences sharedpreferences = getSharedPreferences("remember",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("ssn", ssnStr);
                        editor.commit();
                    }

                    startActivity(new Intent(login_page.this, MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Invalid account.", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error try again later.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void accountHandler(String ssn){
        AccountLoged loged = AccountLoged.getInstance();
        loged.setSsn(ssn);
    }

    public void imagePickerDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.form_style);
        dialog.setTitle("Image view info");


        dialog.show();
    }


}
