package com.gp.cspd.deathCertificate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.gp.cspd.Database.DatabaseForm;
import com.gp.cspd.MainActivity;
import com.gp.cspd.R;
import com.gp.cspd.birthCertificate.birthForms;
import com.gp.cspd.forms.FormDialog;

public class deathForms extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death_forms);

        findViewById(R.id.show_my_form).setOnClickListener(this);
        findViewById(R.id.first_time_death_certificate).setOnClickListener(this);
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(deathForms.this, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.show_my_form:
                FormDialog formDialog = new FormDialog(this);
                formDialog.showFormDialog();
                break;

            case R.id.first_time_death_certificate:
                uploadForm();
                showSuccessDialog();
                break;
        }
    }

    private void showSuccessDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.accepted_form_dialog);
        Button btn = dialog.findViewById(R.id.done);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(deathForms.this, MainActivity.class));
                finish();
            }
        });
        dialog.show();
    }

    private void uploadForm() {
        DatabaseForm databaseForm = new DatabaseForm();
        databaseForm.setOrderName("Death Certificate");
        databaseForm.setFormName("first time");
        databaseForm.uploadDB();
    }

}
