package com.gp.cspd.birthCertificate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.gp.cspd.MainActivity;
import com.gp.cspd.R;

public class birthForms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birth_forms);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(birthForms.this, MainActivity.class));
        finish();
    }
}
