package com.gp.cspd.passport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.gp.cspd.R;

public class replacementOfLostPassport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replacement_of_lost_passport);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(replacementOfLostPassport.this, passportForms.class));
        finish();
    }
}
