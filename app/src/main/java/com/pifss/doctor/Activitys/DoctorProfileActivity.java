package com.pifss.doctor.Activitys;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pifss.doctor.Model.Doctor;
import com.pifss.doctor.R;
import com.pifss.doctor.links;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class DoctorProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);

        toolbar.setTitle("My profile");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button btnnEdit = (Button) findViewById(R.id.btnEdit);



        btnnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DoctorProfileActivity.this,EditDoctorProfileActivity.class);
                startActivity(i);
            }
        });


        SharedPreferences preference = getSharedPreferences("settings",MODE_PRIVATE);
        String doctorProfile = preference.getString(links.PrefDoctorProfile,"notfound");
        Doctor doctor = new Gson().fromJson(doctorProfile,Doctor.class);

        TextView txtName = (TextView) findViewById(R.id.txtDrNameView);
        txtName.setText(doctor.getFirstName() + " " + doctor.getLastName());


        ImageView img = (ImageView) findViewById(R.id.imageViewLogo);
        Picasso.with(DoctorProfileActivity.this).load(doctor.getImageUrl()).into(img);

        EditText txtEmail =(EditText) findViewById(R.id.txtDrEmail);
        txtEmail.setText(doctor.getEmail());
        txtEmail.setEnabled(false);

        EditText txtCivilID = (EditText) findViewById(R.id.txtDrCivil);
        txtCivilID.setText(doctor.getCivilId());
        txtCivilID.setEnabled(false);

        EditText txtSpecialty = (EditText) findViewById(R.id.txtDrSpeciality);
        txtSpecialty.setText(doctor.getSpecialityId());
        txtSpecialty.setEnabled(false);

        EditText txtLocation = (EditText) findViewById(R.id.txtDrLocation);
        txtLocation.setText(doctor.getLocation());
        txtLocation.setEnabled(false);







    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
