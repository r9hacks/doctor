package com.pifss.doctor.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.pifss.doctor.Model.Doctor;
import com.pifss.doctor.Model.MyPatient;
import com.pifss.doctor.R;
import com.pifss.doctor.Adapters.myPatientAdapter;
import com.pifss.doctor.RequestQueueSingleTon;
import com.pifss.doctor.links;

import org.json.JSONArray;

import java.util.ArrayList;

public class MyPatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_patient);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);

        toolbar.setTitle("My Patient");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final ArrayList<MyPatient> model=new ArrayList<>();

        RequestQueue queue= RequestQueueSingleTon.getInstance().getRequestQueue(MyPatientActivity.this);
        SharedPreferences preference = getSharedPreferences("settings",MODE_PRIVATE);
        String doctorProfile = preference.getString(links.PrefDoctorProfile,"notfound");
        Doctor doctor = new Gson().fromJson(doctorProfile,Doctor.class);

        try {

            JSONArray jsonBody = new JSONArray();



            JsonArrayRequest jsonObjRequest = new JsonArrayRequest(Request.Method.GET, links.MyPatient+doctor.getDrId() , jsonBody, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Toast.makeText(MyPatientActivity.this, "my patient req list: "+response.toString(), Toast.LENGTH_SHORT).show();
                    System.out.println("my patient req list: "+response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MyPatientActivity.this, "error my patient req list: "+error.toString(), Toast.LENGTH_SHORT).show();
                    System.out.println("error my patient req list: "+error.toString());
                }
            });

            queue.add(jsonObjRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

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


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
