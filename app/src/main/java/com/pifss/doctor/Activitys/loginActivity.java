package com.pifss.doctor.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;

import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
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
import com.pifss.doctor.Connection;
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
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);

        toolbar.setTitle(R.string.Login);
        toolbar.setTitleTextColor(Color.WHITE);

        Button loginButton = (Button) findViewById(R.id.btnLogin);
        Button registerButton = (Button) findViewById(R.id.btnRegister);
        TextView forgetButton = (TextView) findViewById(R.id.textView4);
        ImageView img = (ImageView) findViewById(R.id.imageViewLogo);
        img.requestFocus();

        final EditText email = (EditText) findViewById(R.id.txtDrEmail);
        final EditText password = (EditText) findViewById(R.id.txtDrPassword);


        final RequestQueue queue= RequestQueueSingleTon.getInstance().getRequestQueue(loginActivity.this);

        final ProgressDialog progressDialog = new ProgressDialog(loginActivity.this);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("username",email.getText().toString());
                    jsonBody.put("password",password.getText().toString());


                    JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.POST, links.login, jsonBody, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            progressDialog.hide();
                            try {
                                if ((response.getString("errorMsgEn")).equalsIgnoreCase("Error")) {
                                    //error login
                                    //show message
                                    Toast.makeText(loginActivity.this, R.string.Errorresponse + response.toString(), Toast.LENGTH_SHORT).show();

                                    return;
                                }
                                System.out.println("response: " + response.toString());
                                JSONObject profileJson = response.getJSONObject("items");
                                Doctor doctor = new Gson().fromJson(profileJson.toString(), Doctor.class);

                                    //Toast.makeText(loginActivity.this, "" + doctor.getJSONDoctor().toString(), Toast.LENGTH_SHORT).show();

                                    // save profile to shared refrences
                                    SharedPreferences preference = getSharedPreferences("settings", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preference.edit();
                                    editor.putString(links.PrefDoctorProfile, profileJson.toString());
                                    editor.commit();

                                    Intent i = new Intent(loginActivity.this, HomeReportActivity.class);
                                    startActivity(i);
                                    finish();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    //Show no internet connection message

                    View v1 = (View) findViewById(R.id.activity_login_id);
                    ConnectivityManager cm = (ConnectivityManager) getSystemService(loginActivity.CONNECTIVITY_SERVICE);
                    Connection ch1 = new Connection(cm);
                    if (ch1.haveNetworkConnection() == false){
                        ch1.ShowSnackBar(v1);
                    }else {

                        progressDialog.setMessage("Login...");
                        progressDialog.show();
                        queue.add(jsonObjRequest);
                    }
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

    @Override
    protected void onStart() {
        super.onStart();
        final ProgressDialog progressDialog = new ProgressDialog(loginActivity.this);

        SharedPreferences preference = getSharedPreferences("settings",MODE_PRIVATE);
        String doctorProfile = preference.getString(links.PrefDoctorProfile,"notfound");
        if (!doctorProfile.equalsIgnoreCase("notfound")){

            Doctor doctor = new Gson().fromJson(doctorProfile,Doctor.class);

            final EditText email = (EditText) findViewById(R.id.txtDrEmail);
            final EditText password = (EditText) findViewById(R.id.txtDrPassword);

            email.setText(doctor.getEmail());
            password.setText(doctor.getPassword());

            final RequestQueue queue= RequestQueueSingleTon.getInstance().getRequestQueue(loginActivity.this);

            try {

                JSONObject jsonBody = new JSONObject();
                jsonBody.put("username",email.getText().toString());
                jsonBody.put("password",password.getText().toString());


                JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.POST, links.login, jsonBody, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.hide();
                        try {
                            if ((response.getString("errorMsgEn")).equalsIgnoreCase("Error")) {
                                //error login
                                //show message
                                Toast.makeText(loginActivity.this, "Error response: " + response.toString(), Toast.LENGTH_SHORT).show();

                                return;
                            }
                            System.out.println("response: " + response.toString());
                            JSONObject profileJson = response.getJSONObject("items");
                            Doctor doctor = new Gson().fromJson(profileJson.toString(), Doctor.class);

                            //Toast.makeText(loginActivity.this, "" + doctor.getJSONDoctor().toString(), Toast.LENGTH_SHORT).show();

                            // save profile to shared refrences
                            SharedPreferences preference = getSharedPreferences("settings", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preference.edit();
                            editor.putString(links.PrefDoctorProfile, profileJson.toString());
                            editor.commit();

                            Intent i = new Intent(loginActivity.this, HomeReportActivity.class);
                            startActivity(i);
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                    }
                });

                progressDialog.setMessage(getResources().getString(R.string.Logining));
                progressDialog.show();
                queue.add(jsonObjRequest);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
