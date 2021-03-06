package com.pifss.doctor.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
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
import com.android.volley.toolbox.JsonArrayRequest;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);

        toolbar.setTitle(R.string.Register);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final Button register = (Button) findViewById(R.id.btnRegister);
        final EditText name = (EditText) findViewById(R.id.txtDrName);
        final EditText email = (EditText) findViewById(R.id.txtDrEmail);
        final EditText password = (EditText) findViewById(R.id.txtDrPassword);
        final EditText civilId = (EditText) findViewById(R.id.txtDrCivil);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString() == "" || email.getText().toString() == "" || password.getText().toString() == "" || civilId.getText().toString() == ""){
                   Toast.makeText(RegisterActivity.this, R.string.Pleasefillallfields, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().length() < 8){
                    Toast.makeText(RegisterActivity.this, R.string.Passwordmustbeatleast8character, Toast.LENGTH_SHORT).show();
                    return;
                }

                //validate email
                if (validate(email.getText().toString()) == false){
                    Toast.makeText(RegisterActivity.this, R.string.Pleaseenteravalidemailaddress, Toast.LENGTH_SHORT).show();
                    return;
                }

                // Toast.makeText(RegisterActivity.this, "Connecting...", Toast.LENGTH_SHORT).show();


                Doctor newDoctor = new Doctor(name.getText().toString(),email.getText().toString(),password.getText().toString(),civilId.getText().toString());

                final RequestQueue queue= RequestQueueSingleTon.getInstance().getRequestQueue(RegisterActivity.this);
                final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);

                try {

                    JSONObject jsonBody = newDoctor.getJSONDoctor();
                    jsonBody.remove("drId");
                    System.out.println(jsonBody.toString());

                    JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.POST, links.Doctor, jsonBody, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            progressDialog.hide();
                            System.out.println("response: "+response.toString());
                            try {
                                if (response.getString("errorMsgEn").equalsIgnoreCase("Accepted")){
                                    Toast.makeText(RegisterActivity.this, R.string.Registersuccessfully, Toast.LENGTH_SHORT).show();
                                    email.setText("");
                                    name.setText("");
                                    password.setText("");
                                    civilId.setText("");
                                }else if(response.getInt("errorCode") == 406 ){
                                    Toast.makeText(RegisterActivity.this, R.string.RegisterFailed, Toast.LENGTH_SHORT).show();
                                    Toast.makeText(RegisterActivity.this, R.string.Useanotheremailaddress, Toast.LENGTH_SHORT).show();

                                }else{
                                        Toast.makeText(RegisterActivity.this, R.string.RegisterFailed, Toast.LENGTH_SHORT).show();
                                        Toast.makeText(RegisterActivity.this, R.string.Connectionerror, Toast.LENGTH_SHORT).show();
                                }

                                //Toast.makeText(RegisterActivity.this, "on response: "+response.toString(), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("error: "+error.toString());
                            //show message
                            Toast.makeText(RegisterActivity.this, R.string.RegisterFailed, Toast.LENGTH_SHORT).show();
                            Toast.makeText(RegisterActivity.this, R.string.Useanotheremailaddress, Toast.LENGTH_SHORT).show();

                            progressDialog.hide();

                        }
                    });
// {
//                        @Override
//                        protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
//                            String jsonString = "";
//                            JSONObject object = new JSONObject();
//
//                            try {
//                                System.out.println("statuscode: "+response.statusCode);
//                                if (response.statusCode <200 || response.statusCode >300){
//
//                                    object.put("status",false);
//                                    return Response.success(object,
//                                            HttpHeaderParser.parseCacheHeaders(response));
//                                }
//                                jsonString = new String(response.data, "UTF-8");
//                                System.out.println("jsonString");
//
//                                System.out.println("\""+jsonString+"\"");
//                                if (jsonString.equals("")){
//                                    object.put("status",true);
//                                }else{
//                                    object.put("status",false);
//                                }
//
//                                return Response.success(object,
//                                    HttpHeaderParser.parseCacheHeaders(response));
//
//                            } catch (UnsupportedEncodingException e) {
//                                e.printStackTrace();
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                            return Response.success(object,
//                                    HttpHeaderParser.parseCacheHeaders(response));
//
//                        }
//                    };

                    progressDialog.setMessage(getResources().getString(R.string.Connecting));
                    progressDialog.show();
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
