package com.gp.cspd.IdCard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gp.cspd.MainActivity;
import com.gp.cspd.R;
import com.gp.cspd.birthCertificate.birthForms;

public class idCardForms extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_card_forms);

        findViewById(R.id.renew_id_card).setOnClickListener(this);
        findViewById(R.id.damaged_allowance_card).setOnClickListener(this);
        findViewById(R.id.replacement_of_lost_card).setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(idCardForms.this, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.renew_id_card:
                startActivity(new Intent(idCardForms.this,renewIdCard.class));
                finish();
                break;
            case R.id.damaged_allowance_card:
                startActivity(new Intent(idCardForms.this,damagedReplacementCard.class));
                finish();
                break;
            case R.id.replacement_of_lost_card:
                startActivity(new Intent(idCardForms.this,damagedReplacementCard.class));
                finish();
                break;
        }
    }
}
