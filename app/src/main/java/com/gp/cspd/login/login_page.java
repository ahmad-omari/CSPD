package com.gp.cspd.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

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


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_signin:
                String ssnStr=ssn.getText().toString().trim();
                String passwordStr=password.getText().toString().trim();
                login(ssnStr,passwordStr);
                progressBar.setVisibility(ProgressBar.VISIBLE);
                startActivity(new Intent(login_page.this, MainActivity.class));
                finish();
                break;
            case R.id.sign_up_text:
                startActivity(new Intent(login_page.this, com.gp.cspd.signUp.signUp.class));
                finish();
                break;
        }
    }
    private static void login(String ssnStr,String passwordStr){
        if (UserVerification.isValidSSN(ssnStr) && UserVerification.isValidPassword(passwordStr)){

        }
    }

}
