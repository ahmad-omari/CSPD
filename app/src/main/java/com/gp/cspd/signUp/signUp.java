package com.gp.cspd.signUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gp.cspd.R;
import com.gp.cspd.login.login_page;

import java.util.Calendar;

public class signUp extends AppCompatActivity implements View.OnClickListener ,
        AdapterView.OnItemSelectedListener {

    private TextView dob;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private Spinner bloodType ,governorateSP;
    private ImageView authPic;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        dob = findViewById(R.id.date_of_birth);
        bloodType = findViewById(R.id.blood_spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(signUp.this,R.array.blood_type,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodType.setAdapter(arrayAdapter);
        bloodType.setOnItemSelectedListener(this);

        governorateSP = findViewById(R.id.governorate);
        ArrayAdapter<CharSequence> arrayAdapterGov = ArrayAdapter.createFromResource(signUp.this,R.array.governorates,android.R.layout.simple_spinner_item);
        arrayAdapterGov.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        governorateSP.setAdapter(arrayAdapterGov);
        governorateSP.setOnItemSelectedListener(this);

        findViewById(R.id.date_ic).setOnClickListener(this);
        findViewById(R.id.auth_pic_info).setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);
        findViewById(R.id.sign_up_button).setOnClickListener(this);

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                dob.setText(day+"/"+(month+1)+"/"+year);
                dob.setTextColor(Color.DKGRAY);
            }
        };


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.date_ic:

                Calendar calendar= Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(signUp.this,R.style.calenderTheme,dateSetListener,
                        year,month,day);
           //     datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
                break;

            case R.id.auth_pic_info:
                authInfoDialog();
                break;

            case R.id.cancel:
                startActivity(new Intent(signUp.this, login_page.class));
                finish();
                break;

            case R.id.sign_up_button:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.blood_spinner:
                int po = adapterView.getPositionForView(view);
                String bloodTypeString = adapterView.getItemAtPosition(po).toString();
                if (!bloodTypeString.equals("-"))
                    Toast.makeText(getApplicationContext(),bloodTypeString,Toast.LENGTH_LONG).show();
                break;

            case R.id.governorate:
                String governorateString = adapterView.getItemAtPosition(i).toString();
                if (!governorateString.equals("-"))
                    Toast.makeText(getApplicationContext(),governorateString,Toast.LENGTH_LONG).show();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void authInfoDialog(){
        final Dialog dialog = new Dialog(signUp.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.auth_info_dialog);
        dialog.setTitle("Image view info");

        Button button=dialog.findViewById(R.id.ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
