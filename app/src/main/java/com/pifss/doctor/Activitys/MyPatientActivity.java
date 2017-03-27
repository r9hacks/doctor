package com.pifss.doctor.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.pifss.doctor.MyPatient;
import com.pifss.doctor.R;
import com.pifss.doctor.myPatientAdapter;

import java.util.ArrayList;

public class MyPatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_patient);

        final ArrayList<MyPatient> model=new ArrayList<>();


        model.add(new MyPatient("John Smith",22,"1234","female"));
        model.add(new MyPatient("Mshmsh Soso",23,"14321","female"));
        model.add(new MyPatient("John Smith",22,"1234","female"));
        model.add(new MyPatient("Mshmsh Soso",23,"14321","female"));
        model.add(new MyPatient("John Smith",22,"1234","female"));
        model.add(new MyPatient("Mshmsh Soso",23,"14321","female"));


        myPatientAdapter adapter=new myPatientAdapter(MyPatientActivity.this,model);


        ListView lv= (ListView) findViewById(R.id.myPatientListView);

        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MyPatient patient = model.get(position);

                Toast.makeText(MyPatientActivity.this, patient.getName(), Toast.LENGTH_SHORT).show();


                Intent i=new Intent(MyPatientActivity.this,PatientProfileActivity.class);
                startActivity(i);


            }
        });

    }
}
