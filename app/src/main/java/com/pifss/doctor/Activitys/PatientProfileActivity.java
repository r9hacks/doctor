package com.pifss.doctor.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pifss.doctor.Adapters.ReportListAdapter;
import com.pifss.doctor.Model.Doctor;
import com.pifss.doctor.Model.MyPatient;
import com.pifss.doctor.Model.Patient;
import com.pifss.doctor.Model.ReportCell;
import com.pifss.doctor.Model.ReportList;
import com.pifss.doctor.R;
import com.pifss.doctor.links;

import java.util.ArrayList;

public class PatientProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);

        toolbar.setTitle("Patient Profile");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



//        EditText medication = (EditText) findViewById(R.id.MedicationEditTextMedication);
//        medication.setKeyListener(null);


        SharedPreferences preference = getSharedPreferences("settings",MODE_PRIVATE);
        String patientProfile = preference.getString(links.PrefPatientProfile,"notfound");
        Patient patient = new Gson().fromJson(patientProfile,Patient.class);


        TextView name = (TextView) findViewById(R.id.textViewName);
        TextView age = (TextView) findViewById(R.id.textViewAge);
        TextView gender = (TextView) findViewById(R.id.textViewGender);
        TextView phone = (TextView) findViewById(R.id.textViewPhone);
        TextView bloodType = (TextView) findViewById(R.id.textViewBloodType);
        TextView asthma = (TextView) findViewById(R.id.textViewAsthma);
        TextView diabities = (TextView) findViewById(R.id.textViewDiabetes);
        TextView allergy = (TextView) findViewById(R.id.textViewAllergies);
        TextView medication = (TextView) findViewById(R.id.textViewMedication);


        name.setText(patient.getFirstName()+" "+patient.getLastName());
        age.setText(patient.getDateOfBirth());
        gender.setText(patient.getGender());
        phone.setText(patient.getPhoneNumber());
        bloodType.setText(patient.getBloodType());
        asthma.setText(patient.getAsthma()+"");
        diabities.setText(patient.getDiabetes()+"");
        allergy.setText(patient.getAllergies());
        medication.setText(patient.getMedications());


        //list

        final ArrayList<ReportList> model=new ArrayList<>();
//String comment, Date date, String heartRate, String bloodPreassure, String fever) {

        model.add(new ReportList("1", "28/1/2017","100BPM","80/120","no"));
        model.add(new ReportList("22222", "27/1/2017","90BPM","88/120","no"));
        model.add(new ReportList("3333", "28/1/2017","100BPM","80/120","no"));
        model.add(new ReportList("44444", "27/1/2017","90BPM","88/120","no"));
        model.add(new ReportList("55555", "28/1/2017","100BPM","80/120","no"));
        model.add(new ReportList("6666", "27/1/2017","90BPM","88/120","no"));


        ListView lv= (ListView) findViewById(R.id.reportListView);

        ReportListAdapter adapter=new ReportListAdapter(PatientProfileActivity.this,model);


        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ReportList patient = model.get(position);

               // Toast.makeText(PatientProfileActivity.this, patient.getComment(), Toast.LENGTH_SHORT).show();


              //here go to report

            }
        });









    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
