package com.gp.cspd.passport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gp.cspd.IdCard.damagedReplacementCard;
import com.gp.cspd.IdCard.idCardForms;
import com.gp.cspd.IdCard.renewIdCard;
import com.gp.cspd.MainActivity;
import com.gp.cspd.R;
import com.gp.cspd.birthCertificate.birthForms;

public class passportForms extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passport_forms);

        findViewById(R.id.renew_passport).setOnClickListener(this);
        findViewById(R.id.damaged_allowance_passport).setOnClickListener(this);
        findViewById(R.id.replacement_of_lost_passport).setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(passportForms.this, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.renew_passport:
                startActivity(new Intent(passportForms.this, firstTimePassport.class));
                finish();
                break;
            case R.id.damaged_allowance_passport:
                startActivity(new Intent(passportForms.this, damagedPassport.class));
                finish();
                break;
            case R.id.replacement_of_lost_passport:
                startActivity(new Intent(passportForms.this,replacementOfLostPassport.class));
                finish();
                break;
        }
    }
}
