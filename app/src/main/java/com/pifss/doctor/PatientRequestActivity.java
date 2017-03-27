package com.pifss.doctor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PatientRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_request);

        final ArrayList<PatientRequest> model=new ArrayList<>();

        model.add(new PatientRequest("John Smith",22,"1234","female"));
        model.add(new PatientRequest("Mshmsh Soso",23,"14321","female"));
        model.add(new PatientRequest("John Smith",22,"1234","female"));
        model.add(new PatientRequest("Mshmsh Soso",23,"14321","female"));
        model.add(new PatientRequest("John Smith",22,"1234","female"));
        model.add(new PatientRequest("Mshmsh Soso",23,"14321","female"));


        RequestAdapter adapter=new RequestAdapter(PatientRequestActivity.this,model);


        ListView lv= (ListView) findViewById(R.id.invitationListView);

        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                PatientRequest request = model.get(position);

                Toast.makeText(PatientRequestActivity.this, request.getName(), Toast.LENGTH_SHORT).show();



            }
        });

    }
}
