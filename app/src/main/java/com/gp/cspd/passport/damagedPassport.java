package com.gp.cspd.passport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.gp.cspd.R;

public class damagedPassport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damaged_passport);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(damagedPassport.this, passportForms.class));
        finish();
    }
}
