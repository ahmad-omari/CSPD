package com.gp.cspd.birthCertificate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gp.cspd.Database.DatabaseForm;
import com.gp.cspd.MainActivity;
import com.gp.cspd.R;
import com.gp.cspd.forms.FormDialog;

public class birthForms extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birth_forms);

        findViewById(R.id.show_my_form).setOnClickListener(this);
        findViewById(R.id.first_time_birth_certificate).setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(birthForms.this, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.show_my_form:
                FormDialog formDialog = new FormDialog(this);
                formDialog.showFormDialog();
                break;

            case R.id.first_time_birth_certificate:
                uploadForm();
                startActivity(new Intent(birthForms.this, MainActivity.class));
                finish();
                break;
        }
    }

    private void uploadForm() {
        DatabaseForm databaseForm = new DatabaseForm();
        databaseForm.setOrderName("Birth Certificate");
        databaseForm.setFormName("first time");
        databaseForm.uploadDB();
    }
}
