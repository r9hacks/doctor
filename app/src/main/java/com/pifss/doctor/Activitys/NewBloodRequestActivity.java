package com.pifss.doctor.Activitys;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

import java.util.ArrayList;

public class NewBloodRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_request);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);

        toolbar.setTitle(R.string.NewBloodRequests);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final EditText editTextReason = (EditText) findViewById(R.id.editTextReason);
        final EditText editTextQuantity = (EditText) findViewById(R.id.editText);

        Button buttonSendReply = (Button) findViewById(R.id.buttonSendReply);

        buttonSendReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String reason = editTextReason.getText().toString();
                int quantity;

                try {
                   quantity = Integer.parseInt(editTextQuantity.getText().toString());
                }catch (Exception e){
                    quantity = 0;
                }
                if (reason.length()<=0){
                    Toast.makeText(NewBloodRequestActivity.this, R.string.Reasoncantbeempty, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (quantity <=0 || quantity > 20){
                    Toast.makeText(NewBloodRequestActivity.this, R.string.Quantitymustbebetween1and20, Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences preference = getSharedPreferences("settings",MODE_PRIVATE);
                String doctorProfile = preference.getString(links.PrefDoctorProfile,"notfound");
                final Doctor doctor = new Gson().fromJson(doctorProfile,Doctor.class);
                final ProgressDialog progressDialog = new ProgressDialog(NewBloodRequestActivity.this);
                final RequestQueue queue= RequestQueueSingleTon.getInstance().getRequestQueue(NewBloodRequestActivity.this);

                try {

                    JSONObject jsonBody = new JSONObject();
                    if (selectedBloodTypeIndex == 0){
                        jsonBody.put("bloodType","A+");
                    }else if (selectedBloodTypeIndex  == 1){
                        jsonBody.put("bloodType","A-");
                    }else if (selectedBloodTypeIndex  == 2){
                        jsonBody.put("bloodType","B+");
                    }else if (selectedBloodTypeIndex  == 3){
                        jsonBody.put("bloodType","B-");
                    }else if (selectedBloodTypeIndex  == 4){
                        jsonBody.put("bloodType","O+");
                    }else if (selectedBloodTypeIndex  == 5){
                        jsonBody.put("bloodType","O-");
                    }else if (selectedBloodTypeIndex  == 6){
                        jsonBody.put("bloodType","AB+");
                    }else{
                        jsonBody.put("bloodType","AB-");
                    }

                    jsonBody.put("drId",doctor.getDrId());
                    jsonBody.put("quantity",quantity);
                    jsonBody.put("reason",reason);
                    jsonBody.put("status",0);

                    System.out.println(jsonBody.toString());

                    JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.POST, links.BloodRequests, jsonBody, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            progressDialog.hide();
                            System.out.println("response: "+response.toString());
                            try {
                                if (response.getString("errorMsgEn").equalsIgnoreCase("Accepted")){
                                    Toast.makeText(NewBloodRequestActivity.this, R.string.Requestsent, Toast.LENGTH_SHORT).show();
                                    finish();
                                }else if(response.getString("errorMsgEn").equalsIgnoreCase("Not Created+\nUser already exist") ){
                                    Toast.makeText(NewBloodRequestActivity.this, R.string.RequestFailed, Toast.LENGTH_SHORT).show();

                                }else{
                                    Toast.makeText(NewBloodRequestActivity.this, R.string.RegisterFailed, Toast.LENGTH_SHORT).show();
                                    Toast.makeText(NewBloodRequestActivity.this, R.string.Connectionerror, Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(NewBloodRequestActivity.this, R.string.RegisterFailed, Toast.LENGTH_SHORT).show();
                            Toast.makeText(NewBloodRequestActivity.this, R.string.Connectionerror, Toast.LENGTH_SHORT).show();

                            progressDialog.hide();

                        }
                    });

                    progressDialog.setMessage(getResources().getString(R.string.Connecting));
                    progressDialog.show();
                    queue.add(jsonObjRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


        ImageButton aPlusButton = (ImageButton) findViewById(R.id.aPlusButton);
        ImageButton aMinusButton = (ImageButton) findViewById(R.id.aMinusButton);
        ImageButton bPlusButton = (ImageButton) findViewById(R.id.bPlusButton);
        ImageButton bMinusButton = (ImageButton) findViewById(R.id.bMinusButton);
        ImageButton oPlusButton = (ImageButton) findViewById(R.id.oPlusButton);
        ImageButton oMinusButton = (ImageButton) findViewById(R.id.oMinusButton);
        ImageButton abPlusButton = (ImageButton) findViewById(R.id.abPlusButton);
        ImageButton abMinusButton = (ImageButton) findViewById(R.id.abMinusButton);

        myImageButtons.add(aPlusButton);
        myImageButtons.add(aMinusButton);
        myImageButtons.add(bPlusButton);
        myImageButtons.add(bMinusButton);
        myImageButtons.add(oPlusButton);
        myImageButtons.add(oMinusButton);
        myImageButtons.add(abPlusButton);
        myImageButtons.add(abMinusButton);

        aPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBloodTypeImageButton(0);
            }
        });
        aMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBloodTypeImageButton(1);
            }
        });
        bPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBloodTypeImageButton(2);
            }
        });
        bMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBloodTypeImageButton(3);
            }
        });
        oPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBloodTypeImageButton(4);
            }
        });
        oMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBloodTypeImageButton(5);
            }
        });
        abPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBloodTypeImageButton(6);
            }
        });
        abMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBloodTypeImageButton(7);
            }
        });
        selectedBloodTypeImageButton(0);
    }

    ArrayList<ImageButton> myImageButtons = new ArrayList<>();
    int selectedBloodTypeIndex = 0;
    public void selectedBloodTypeImageButton(int position) {
        selectedBloodTypeIndex = position;
        for (int i = 0; i < myImageButtons.size(); i++) {

            ImageButton button = myImageButtons.get(i);

            if (i == position) {
                button.setAlpha(1.0f);
            } else {
                button.setAlpha(0.4f);
            }

        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
