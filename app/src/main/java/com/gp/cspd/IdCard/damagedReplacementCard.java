package com.gp.cspd.IdCard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.gp.cspd.MainActivity;
import com.gp.cspd.R;

public class damagedReplacementCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damaged_replacement);
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(damagedReplacementCard.this, idCardForms.class));
        finish();
    }
}
