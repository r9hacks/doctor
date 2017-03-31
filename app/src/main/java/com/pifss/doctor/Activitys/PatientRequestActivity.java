package com.pifss.doctor.Activitys;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pifss.doctor.Adapters.myPatientAdapter;
import com.pifss.doctor.Model.Doctor;
import com.pifss.doctor.Model.Patient;
import com.pifss.doctor.Model.PatientRequest;
import com.pifss.doctor.R;
import com.pifss.doctor.Adapters.RequestAdapter;
import com.pifss.doctor.RequestQueueSingleTon;
import com.pifss.doctor.links;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PatientRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_request);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);

        toolbar.setTitle("Request");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final ArrayList<PatientRequest> model=new ArrayList<>();

        final ListView lv= (ListView) findViewById(R.id.invitationListView);


        RequestQueue queue= RequestQueueSingleTon.getInstance().getRequestQueue(PatientRequestActivity.this);
        SharedPreferences preference = getSharedPreferences("settings",MODE_PRIVATE);
        String doctorProfile = preference.getString(links.PrefDoctorProfile,"notfound");
        Doctor doctor = new Gson().fromJson(doctorProfile,Doctor.class);

        try {

            JSONArray jsonBody = new JSONArray();



            JsonArrayRequest jsonObjRequest = new JsonArrayRequest(Request.Method.GET, links.PendingRequest+doctor.getDrId() , jsonBody, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                   // Toast.makeText(PatientRequestActivity.this, "get patient req list: "+response.toString(), Toast.LENGTH_SHORT).show();
                    System.out.println("get patient req list: "+response.toString());

                    ArrayList<Patient> patients = new ArrayList<>();


                    patients = new Gson().fromJson(response.toString(),new TypeToken<ArrayList<Patient>>(){}.getType());

                    for (int i = 0 ; i < patients.size(); i++) {


                        PatientRequest request =  new PatientRequest(patients.get(i).getFirstName()+" "+patients.get(i).getLastName(), patients.get(i).getDateOfBirth(), patients.get(i).getCivilId(), patients.get(i).getGender(), patients.get(i).getImageUrl());
                                model.add(request);


                    }
                    RequestAdapter adapter=new RequestAdapter(PatientRequestActivity.this, model);

                    lv.setAdapter(adapter);


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                   // Toast.makeText(PatientRequestActivity.this, "error get patient req list: "+error.toString(), Toast.LENGTH_SHORT).show();
                    System.out.println("error get patient req list: "+error.toString());
                }
            });

                    queue.add(jsonObjRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }




        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                PatientRequest request = model.get(position);
               // Toast.makeText(PatientRequestActivity.this, request.getName(), Toast.LENGTH_SHORT).show();
                


            }
        });
        lv.setEmptyView(findViewById(R.id.emptyElement));
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
