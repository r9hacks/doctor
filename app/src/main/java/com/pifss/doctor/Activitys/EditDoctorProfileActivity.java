package com.pifss.doctor.Activitys;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.google.gson.Gson;
import com.pifss.doctor.Model.Doctor;
import com.pifss.doctor.R;
import com.pifss.doctor.links;

public class EditDoctorProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_doctor_profile);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);

        toolbar.setTitle("Edit profile");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        SharedPreferences preference = getSharedPreferences("settings",MODE_PRIVATE);
        String doctorProfile = preference.getString(links.PrefDoctorProfile,"notfound");
        Doctor doctor = new Gson().fromJson(doctorProfile,Doctor.class);

        EditText txtName = (EditText) findViewById(R.id.txtDrName);
        txtName.setText(doctor.getFirstName() + " " + doctor.getLastName());
doctor.setFirstName(txtName.getText().toString());
        EditText txtEmail = (EditText) findViewById(R.id.txtDrEmail);
        txtEmail.setText(doctor.getEmail());

        EditText txtLocation = (EditText) findViewById(R.id.txtLocation);
        txtLocation.setText(doctor.getLocation());

        EditText txtCivil = (EditText) findViewById(R.id.txtDrCivil);
        txtCivil.setText(doctor.getCivilId());

        EditText txtDrNationality = (EditText) findViewById(R.id.txtNationality);
        txtDrNationality.setText(doctor.getNationality());

        EditText txtDrSpecialty = (EditText) findViewById(R.id.txtSpecialty);
        txtDrSpecialty.setText(doctor.getSpecialityId());

//        EditText txtBirthday = (EditText) findViewById(R.id.txtBirthdate);
//        txtBirthday.setText(doctor.getBDay());

        EditText txtInfo = (EditText) findViewById(R.id.txtBio);
        txtInfo.setText(doctor.getExtraInfo());



    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
