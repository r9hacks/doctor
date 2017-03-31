package com.pifss.doctor.Activitys;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.pifss.doctor.Model.Doctor;
import com.pifss.doctor.R;
import com.pifss.doctor.RequestQueueSingleTon;
import com.pifss.doctor.links;

import org.json.JSONException;
import org.json.JSONObject;

public class EditDoctorProfileActivity extends AppCompatActivity {

    int Bday = 1;
    int Bmonth = 1;
    int Byear = 1990;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_doctor_profile);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);

        toolbar.setTitle("Edit profile");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        Button btnSaveChange = (Button) findViewById(R.id.btnEdit);
        final EditText txtPassword = (EditText) findViewById(R.id.txtDrPassword);

        SharedPreferences preference = getSharedPreferences("settings",MODE_PRIVATE);
        String doctorProfile = preference.getString(links.PrefDoctorProfile,"notfound");
        final Doctor doctor = new Gson().fromJson(doctorProfile,Doctor.class);

        final EditText txtName = (EditText) findViewById(R.id.txtDrName);
        txtName.setText(doctor.getFirstName());

        final EditText txtMName = (EditText) findViewById(R.id.txtDrMiddleName);
        txtMName.setText(doctor.getMiddleName());

        final EditText txtLName = (EditText) findViewById(R.id.txtDrLastName);
        txtLName.setText(doctor.getLastName());

        final EditText txtEmail = (EditText) findViewById(R.id.txtDrEmail);
        txtEmail.setText(doctor.getEmail());

        final EditText txtPhone = (EditText) findViewById(R.id.txtDrPhone);
        txtPhone.setText(doctor.getPhoneNumber());

        final EditText txtLocation = (EditText) findViewById(R.id.txtLocation);
        txtLocation.setText(doctor.getLocation());

        final EditText txtCivil = (EditText) findViewById(R.id.txtDrCivil);
        txtCivil.setText(doctor.getCivilId());

        final EditText txtDrNationality = (EditText) findViewById(R.id.txtNationality);
        txtDrNationality.setText(doctor.getNationality());

        final EditText txtDrSpecialty = (EditText) findViewById(R.id.txtSpecialty);
        txtDrSpecialty.setText(doctor.getSpecialityId());

        final TextView txtBirthday = (TextView) findViewById(R.id.txtBirthdate);
        //txtBirthday.setEnabled(false);
        txtBirthday.setText(doctor.getBDay()+"-"+doctor.getBMonth()+"-"+doctor.getBYear());

        Bday = doctor.getBDay();
        Bmonth = doctor.getBMonth();
        Byear = doctor.getBYear();

        txtBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog d = new DatePickerDialog(EditDoctorProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtBirthday.setText(dayOfMonth+"-"+month+"-"+year);
                        Bday = dayOfMonth;
                        Bmonth = month;
                        Byear = year;
                    }
                },Byear,Bmonth,Bday);
                d.show();
            }
        });
//        txtBirthday.setText(doctor.getBDay());


        if ( (doctor.getGender().charAt(0)+"").equalsIgnoreCase("f")){
            RadioButton genderRadio = (RadioButton) findViewById(R.id.radioFemale);
            genderRadio.setChecked(true);
        }else{
            RadioButton genderRadio = (RadioButton) findViewById(R.id.radioMale);
            genderRadio.setChecked(true);
        }

        final EditText txtInfo = (EditText) findViewById(R.id.txtBio);
        txtInfo.setText(doctor.getExtraInfo());

        btnSaveChange.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (txtName.getText().toString() == "" || txtEmail.getText().toString() == "" || txtPassword.getText().toString() == "" || txtLocation.getText().toString() == "" || txtCivil.getText().toString() == ""
                    || txtDrNationality.getText().toString() == "" || txtDrSpecialty.getText().toString() == "" || txtInfo.getText().toString() == ""){

                Toast.makeText(EditDoctorProfileActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!(txtPassword.getText().toString()).equals(doctor.getPassword()) ){
               Toast.makeText(EditDoctorProfileActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupGender);

        int radioButtonID = radioGroup.getCheckedRadioButtonId();
            if (radioButtonID == R.id.radioFemale){
                doctor.setGender("f");
            }else{
                doctor.setGender("m");
            }

            doctor.setFirstName(txtName.getText().toString());
            doctor.setMiddleName(txtMName.getText().toString());
            doctor.setLastName(txtLName.getText().toString());
            doctor.setEmail(txtEmail.getText().toString());
            doctor.setPhoneNumber(txtPhone.getText().toString());
            doctor.setLocation(txtLocation.getText().toString());
            doctor.setCivilId(txtCivil.getText().toString());
            doctor.setNationality(txtDrNationality.getText().toString());
            doctor.setSpecialityId(txtDrSpecialty.getText().toString());
            doctor.setExtraInfo(txtInfo.getText().toString());

            doctor.setBDay(Bday);
            doctor.setBMonth(Bmonth);
            doctor.setBYear(Byear);

            final RequestQueue queue= RequestQueueSingleTon.getInstance().getRequestQueue(EditDoctorProfileActivity.this);

            JSONObject jsonBody = null;
            try {
                jsonBody = doctor.getJSONDoctor();
                jsonBody.remove("drId");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String URL =links.Doctor + "/" + doctor.getDrId();

            JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.PUT, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        if (response.getString("errorMsgEn").equalsIgnoreCase("Done")){

                            Toast.makeText(EditDoctorProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();

                            SharedPreferences preference = getSharedPreferences("settings", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preference.edit();
                            editor.putString(links.PrefDoctorProfile, doctor.getJSONDoctor().toString());
                            editor.commit();
                        }else{
                            Toast.makeText(EditDoctorProfileActivity.this, "Error try again later", Toast.LENGTH_SHORT).show();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    System.out.println("response: "+response.toString());

                    //Toast.makeText(EditDoctorProfileActivity.this, "response: "+response.toString(), Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            queue.add(jsonObjRequest);

        }
    });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
