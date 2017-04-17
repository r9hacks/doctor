package com.pifss.doctor.Activitys;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pifss.doctor.Adapters.ReportListAdapter;
import com.pifss.doctor.Model.Doctor;
import com.pifss.doctor.Model.Patient;
import com.pifss.doctor.Model.Report;
import com.pifss.doctor.Model.ReportList;
import com.pifss.doctor.R;
import com.pifss.doctor.RequestQueueSingleTon;
import com.pifss.doctor.links;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PatientProfileActivity extends AppCompatActivity {
    final ArrayList<ReportList> model=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);

        toolbar.setTitle(R.string.PatientProfile);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        EditText medication = (EditText) findViewById(R.id.MedicationEditTextMedication);
//        medication.setKeyListener(null);

        final SharedPreferences preference = getSharedPreferences("settings",MODE_PRIVATE);
        String patientProfile = preference.getString(links.PrefPatientProfile,"notfound");
        final Patient patient = new Gson().fromJson(patientProfile,Patient.class);

        TextView name = (TextView) findViewById(R.id.textViewName);
        TextView age = (TextView) findViewById(R.id.textViewAge);
        TextView gender = (TextView) findViewById(R.id.textViewGender);
        TextView phone = (TextView) findViewById(R.id.textViewPhone);
        TextView bloodType = (TextView) findViewById(R.id.textViewBloodType);
        TextView asthma = (TextView) findViewById(R.id.textViewAsthma);
        TextView diabities = (TextView) findViewById(R.id.textViewDiabetes);
        TextView allergy = (TextView) findViewById(R.id.textViewAllergies);
        TextView medication = (TextView) findViewById(R.id.textViewMedication);

        CircleImageView img = (CircleImageView) findViewById(R.id.imageViewPatient);
        if (!patient.getImageUrl().equals("")){

            Picasso.with(this).load(patient.getImageUrl()).placeholder(R.mipmap.profile_image).into(img);
        }

        name.setText(patient.getFirstName()+" "+patient.getLastName());
        age.setText(patient.getDateOfBirth());

        if ( (patient.getGender().charAt(0) + "" ).equalsIgnoreCase("f") )
        {
            gender.setText("Female");

        }else{
            gender.setText("Male");

        }
        phone.setText(patient.getPhoneNumber());
        bloodType.setText(patient.getBloodType());
        asthma.setText(patient.getAsthma()+"");
        diabities.setText(patient.getDiabetes()+"");
        allergy.setText(patient.getAllergies());
        medication.setText(patient.getMedications());


        //list

//String comment, Date date, String heartRate, String bloodPreassure, String fever) {

//        model.add(new ReportList("1", "28/1/2017","100BPM","80/120","no"));
//        model.add(new ReportList("22222", "27/1/2017","90BPM","88/120","no"));
//        model.add(new ReportList("3333", "28/1/2017","100BPM","80/120","no"));
//        model.add(new ReportList("44444", "27/1/2017","90BPM","88/120","no"));
//        model.add(new ReportList("55555", "28/1/2017","100BPM","80/120","no"));
//        model.add(new ReportList("6666", "27/1/2017","90BPM","88/120","no"));
//

        ListView lv= (ListView) findViewById(R.id.reportListView);

        final ReportListAdapter adapter=new ReportListAdapter(PatientProfileActivity.this,model);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ReportList patient = model.get(position);

               // Toast.makeText(PatientProfileActivity.this, patient.getComment(), Toast.LENGTH_SHORT).show();
              //here go to report

                try {
                    JSONObject jsonReport = patient.getReportObject().getJSONReport();
                    SharedPreferences.Editor editor = preference.edit();
                    editor.putString(links.PrefReport, jsonReport.toString());
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Intent i = new Intent(PatientProfileActivity.this, ReportDetailActivity.class);
                startActivity(i);

            }
        });

        RequestQueue queue= RequestQueueSingleTon.getInstance().getRequestQueue(PatientProfileActivity.this);
        String doctorProfile = preference.getString(links.PrefDoctorProfile,"notfound");
        Doctor doctor = new Gson().fromJson(doctorProfile,Doctor.class);
        final ProgressDialog progressDialog = new ProgressDialog(PatientProfileActivity.this);

        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("patientId",patient.getPatientId());
            jsonBody.put("drId",doctor.getDrId());
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, links.GetPatientReport, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.hide();
                        ArrayList<Report> report = new Gson().fromJson(response, new TypeToken<ArrayList<Report>>(){}.getType());
                        for (Report r:report) {
                            model.add(new ReportList(r.getComments(),r.getTimestamp(),r.getHeartbeatRate(),r.getBloodPressure(),r.getFever(),r));
                            adapter.notifyDataSetChanged();
                        }
                        // Toast.makeText(getActivity(), "report pending list: "+pendingArray.toString(), Toast.LENGTH_SHORT).show();
                        System.out.println("Patient report list: "+response);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.hide();

                    // Toast.makeText(getActivity(), "error report pending list: "+error.toString(), Toast.LENGTH_SHORT).show();
                    System.out.println("error Patient report list: "+error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    try {

                        String jsonString = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        //Allow null
                        if (jsonString == null || jsonString.length() == 0) {
                            return Response.success(null, HttpHeaderParser.parseCacheHeaders(response));
                        }
                        return Response.success(jsonString,
                                HttpHeaderParser.parseCacheHeaders(response));

                    } catch (UnsupportedEncodingException e) {
                        return Response.error(new ParseError(e));
                    }
                }
            };

            progressDialog.setMessage(getResources().getString(R.string.Loading));
            progressDialog.show();
            queue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }



        lv.setEmptyView(findViewById(R.id.emptyElement));


        ImageView phoneImage = (ImageView) findViewById(R.id.PhoneImage);
        phoneImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(PatientProfileActivity.this);

                String call = getResources().getString(R.string.Call);
                String sure = getResources().getString(R.string.Areyousureyouwannacall);


                builder.setTitle( call  +" "+ patient.getFirstName() +" "+patient.getLastName())
                        .setMessage( sure+" "+patient.getFirstName()+" "+patient.getLastName()+" ?")
                        .setIcon(R.mipmap.phonecall)
                        .setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i=new Intent();
                                i.setAction(Intent.ACTION_DIAL);
                                i.setData(Uri.parse("tel:"+patient.getPhoneNumber()));

                                startActivity(i);
                            }
                        }).setNeutralButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });



                Dialog dConfirm=builder.create();
                dConfirm.show();
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
