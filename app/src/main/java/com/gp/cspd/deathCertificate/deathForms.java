package com.gp.cspd.deathCertificate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.gp.cspd.MainActivity;
import com.gp.cspd.R;
import com.gp.cspd.birthCertificate.birthForms;

public class deathForms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death_forms);
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(deathForms.this, MainActivity.class));
        finish();
    }
}
