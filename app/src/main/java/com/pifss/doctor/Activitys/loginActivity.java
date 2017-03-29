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
import android.widget.ImageView;
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

public class loginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);

        toolbar.setTitle("Login");
        toolbar.setTitleTextColor(Color.WHITE);

        Button loginButton = (Button) findViewById(R.id.btnLogin);
        Button registerButton = (Button) findViewById(R.id.btnRegister);
        TextView forgetButton = (TextView) findViewById(R.id.textView4);
        ImageView img = (ImageView) findViewById(R.id.imageViewLogo);
        img.requestFocus();

        final EditText email = (EditText) findViewById(R.id.txtDrEmail);
        final EditText password = (EditText) findViewById(R.id.txtDrPassword);


        final RequestQueue queue= RequestQueueSingleTon.getInstance().getRequestQueue(loginActivity.this);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("email",email.getText().toString());
                    jsonBody.put("password",password.getText().toString());


                    JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.POST, links.login, jsonBody, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println("response: "+response.toString());

                            Doctor doctor = new Gson().fromJson(response.toString(),Doctor.class);

                            if (doctor.getStatus() == true){
                                Toast.makeText(loginActivity.this, "response: "+response.toString(), Toast.LENGTH_SHORT).show();

                                // save profile to shared refrences
                                SharedPreferences preference = getSharedPreferences("settings",MODE_PRIVATE);
                                SharedPreferences.Editor editor = preference.edit();
                                editor.putString(links.PrefDoctorProfile,response.toString());
                                editor.commit();

                                Intent i = new Intent(loginActivity.this,HomeReportActivity.class);
                                startActivity(i);
                                finish();
                            }else{
                                //error login
                                //show message
                                Toast.makeText(loginActivity.this, "Error response: "+response.toString(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    queue.add(jsonObjRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(loginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

        forgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(loginActivity.this,ForgetPasswordActivity.class);
                startActivity(i);
            }
        });
    }
}
