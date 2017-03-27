package com.pifss.doctor.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.pifss.doctor.R;

public class PatientProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);
        EditText medication = (EditText) findViewById(R.id.editTextMedication);
        medication.setKeyListener(null);
    }
}
