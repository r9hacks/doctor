package com.pifss.doctor.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.pifss.doctor.Adapters.ReportListAdapter;
import com.pifss.doctor.Model.MyPatient;
import com.pifss.doctor.Model.ReportCell;
import com.pifss.doctor.Model.ReportList;
import com.pifss.doctor.R;

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



        EditText medication = (EditText) findViewById(R.id.MedicationEditTextMedication);
        medication.setKeyListener(null);






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

                Toast.makeText(PatientProfileActivity.this, patient.getComment(), Toast.LENGTH_SHORT).show();


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
