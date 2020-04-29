package com.gp.cspd.familyBook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gp.cspd.MainActivity;
import com.gp.cspd.R;
import com.gp.cspd.birthCertificate.birthForms;

public class familyBookForms extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_book_forms);

        findViewById(R.id.first_time_family_book).setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(familyBookForms.this, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.first_time_family_book:
                startActivity(new Intent(familyBookForms.this, firstTimeFamilyBook.class));
                finish();
                break;
        }
    }
}
