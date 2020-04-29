package com.gp.cspd.passport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.gp.cspd.IdCard.idCardForms;
import com.gp.cspd.IdCard.renewIdCard;
import com.gp.cspd.R;

public class firstTimePassport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_passport);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(firstTimePassport.this, passportForms.class));
        finish();
    }
}
