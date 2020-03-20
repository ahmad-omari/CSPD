package com.gp.cspd.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.gp.cspd.MainActivity;
import com.gp.cspd.R;

public class login_page extends AppCompatActivity implements View.OnClickListener {

    EditText ssn,password;
    CheckBox rememberMe;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        ssn=findViewById(R.id.edit_text_ssn);
        password=findViewById(R.id.edit_text_password);
        rememberMe=findViewById(R.id.checkbox_rememberme);
        progressBar=findViewById(R.id.progress_circular);
        findViewById(R.id.button_signin).setOnClickListener(this);

        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_signin:
                progressBar.setVisibility(ProgressBar.VISIBLE);
                startActivity(new Intent(login_page.this, MainActivity.class));
                finish();
                break;
        }
    }

}
