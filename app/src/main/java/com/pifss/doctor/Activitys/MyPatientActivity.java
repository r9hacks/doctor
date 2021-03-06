package com.pifss.doctor.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
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
import com.google.gson.reflect.TypeToken;
import com.pifss.doctor.Model.Doctor;
import com.pifss.doctor.Model.MyPatient;
import com.pifss.doctor.Model.Patient;
import com.pifss.doctor.R;
import com.pifss.doctor.Adapters.myPatientAdapter;
import com.pifss.doctor.RequestQueueSingleTon;
import com.pifss.doctor.links;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyPatientActivity extends AppCompatActivity {



    ArrayList<MyPatient> model;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_patient);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);

        toolbar.setTitle(R.string.MyPatients);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        lv= (ListView) findViewById(R.id.myPatientListView);

        final SearchView searchView = (SearchView) findViewById(R.id.searchView);


        model=new ArrayList<>();

        RequestQueue queue= RequestQueueSingleTon.getInstance().getRequestQueue(MyPatientActivity.this);
        final SharedPreferences preference = getSharedPreferences("settings",MODE_PRIVATE);
        String doctorProfile = preference.getString(links.PrefDoctorProfile,"notfound");
        Doctor doctor = new Gson().fromJson(doctorProfile,Doctor.class);

        try {

            JSONArray jsonBody = new JSONArray();

            final ProgressDialog progressDialog = new ProgressDialog(MyPatientActivity.this);

            JsonArrayRequest jsonObjRequest = new JsonArrayRequest(Request.Method.GET, links.MyPatient+doctor.getDrId() , jsonBody, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    progressDialog.hide();
              //      Toast.makeText(MyPatientActivity.this, "my patient req list: "+response.toString(), Toast.LENGTH_SHORT).show();
                    System.out.println("my patient req list: "+response.toString());

                    ArrayList<Patient> patients = new ArrayList<>();


                    patients = new Gson().fromJson(response.toString(),new TypeToken<ArrayList<Patient>>(){}.getType());

                    for (int i = 0 ; i < patients.size(); i++) {


                        String gender = patients.get(i).getGender();
                        if(( gender.charAt(0)+"").equalsIgnoreCase("f") )
                        {
                            gender="Female";

                        }

                        else
                        {
                            gender="Male";
                        }
                                                                                                                                                    //String name, String BDay, String phoneNum, String gender, String image, String BloodType
                        MyPatient myPatient =  new MyPatient(patients.get(i).getFirstName()+" "+patients.get(i).getLastName(), patients.get(i).getDateOfBirth(), patients.get(i).getPhoneNumber(), gender ,patients.get(i).getImageUrl(), patients.get(i).getBloodType());
                        myPatient.setPatient(patients.get(i));
                        model.add(myPatient);


                    }

                    myPatientAdapter adapter=new myPatientAdapter(MyPatientActivity.this,model);

                    lv.setAdapter(adapter);

                    }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
              //      Toast.makeText(MyPatientActivity.this, "error my patient req list: "+error.toString(), Toast.LENGTH_SHORT).show();
                    System.out.println("error my patient req list: "+error.toString());
                    progressDialog.hide();

                }
            });

            progressDialog.setMessage(getResources().getString(R.string.Loading));
            progressDialog.show();
            queue.add(jsonObjRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MyPatient patient = model.get(position);


               // Toast.makeText(MyPatientActivity.this, "clicked "+patient.getName(), Toast.LENGTH_SHORT).show();
                JSONObject object= null;
                try {
                    object = patient.getPatient().getJSONPatient();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
//shared pref
                SharedPreferences preference = getSharedPreferences("settings", MODE_PRIVATE);
                SharedPreferences.Editor editor = preference.edit();
                editor.putString(links.PrefPatientProfile, object.toString());
                editor.commit();


                Intent i=new Intent(MyPatientActivity.this,PatientProfileActivity.class);
                startActivity(i);



            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                initAdapterWithFilter(newText);


                return false;
            }
        });


        lv.setEmptyView(findViewById(R.id.emptyElement));
    }



    private void initAdapterWithFilter(String filter) {
        if (model == null || model.size() <= 0) {
            return;
        }

        final ArrayList<MyPatient> ChosenModel = new ArrayList<>();

        for (int i = 0; i < model.size(); i++) {
            MyPatient myPatient = model.get(i);
            String fullName = myPatient.getName();
            if ( fullName.toLowerCase().contains(filter) ) {
                ChosenModel.add(myPatient);
            }
        }


        ListView lv = (ListView) findViewById(R.id.myPatientListView);

        //Activity context, ArrayList<MyPatient> model
        myPatientAdapter adapter = new myPatientAdapter(this, ChosenModel);


        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MyPatient patient = ChosenModel.get(position);


                // Toast.makeText(MyPatientActivity.this, "clicked "+patient.getName(), Toast.LENGTH_SHORT).show();

                JSONObject object= null;
                try {
                    object = patient.getPatient().getJSONPatient();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
//shared pref
                SharedPreferences preference = getSharedPreferences("settings", MODE_PRIVATE);
                SharedPreferences.Editor editor = preference.edit();
                editor.putString(links.PrefPatientProfile, object.toString());
                editor.commit();


                Intent i =new Intent(MyPatientActivity.this,PatientProfileActivity.class);
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
