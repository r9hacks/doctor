package com.pifss.doctor.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.pifss.doctor.Model.Doctor;
import com.pifss.doctor.R;
import com.pifss.doctor.RequestQueueSingleTon;
import com.pifss.doctor.links;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);

        toolbar.setTitle("Register");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button register = (Button) findViewById(R.id.btnRegister);
        final EditText name = (EditText) findViewById(R.id.txtDrName);
        final EditText email = (EditText) findViewById(R.id.txtDrEmail);
        final EditText password = (EditText) findViewById(R.id.txtDrPassword);
        final EditText civilId = (EditText) findViewById(R.id.txtDrCivil);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString() == "" || email.getText().toString() == "" || password.getText().toString() == "" || civilId.getText().toString() == ""){
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().length() < 8){
                    Toast.makeText(RegisterActivity.this, "Password must be at least 8 character", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(RegisterActivity.this, "Connecting...", Toast.LENGTH_SHORT).show();

                Doctor newDoctor = new Doctor(name.getText().toString(),email.getText().toString(),password.getText().toString(),civilId.getText().toString());

                final RequestQueue queue= RequestQueueSingleTon.getInstance().getRequestQueue(RegisterActivity.this);

                try {

                    JSONObject jsonBody = newDoctor.getJSONDoctor();
                    jsonBody.remove("drId");

                    JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.POST, links.Doctor, jsonBody, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("error: "+error.toString());
                            //show message
                            Toast.makeText(RegisterActivity.this, "error response: "+error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    queue.add(jsonObjRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
