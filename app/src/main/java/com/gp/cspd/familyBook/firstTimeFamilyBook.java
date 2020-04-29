package com.gp.cspd.familyBook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.gp.cspd.MainActivity;
import com.gp.cspd.R;

public class firstTimeFamilyBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_family_book);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(firstTimeFamilyBook.this, familyBookForms.class));
        finish();
    }
}
