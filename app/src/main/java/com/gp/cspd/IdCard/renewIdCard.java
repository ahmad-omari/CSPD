package com.gp.cspd.IdCard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.gp.cspd.R;

public class renewIdCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renew_id_card);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(renewIdCard.this, idCardForms.class));
        finish();
    }
}
