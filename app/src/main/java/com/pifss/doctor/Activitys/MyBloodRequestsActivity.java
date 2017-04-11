package com.pifss.doctor.Activitys;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pifss.doctor.Adapters.BloodRequestsAdapter;
import com.pifss.doctor.Adapters.myPatientAdapter;
import com.pifss.doctor.Model.Doctor;
import com.pifss.doctor.Model.MyBloodRequest;
import com.pifss.doctor.Model.MyPatient;
import com.pifss.doctor.Model.Patient;
import com.pifss.doctor.R;
import com.pifss.doctor.RequestQueueSingleTon;
import com.pifss.doctor.links;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyBloodRequestsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_blood_requests);

        //myBloodRequestListView
        final Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);

        toolbar.setTitle("My Blood Requests");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        final ListView lv= (ListView) findViewById(R.id.myBloodRequestListView);

        final ArrayList<MyBloodRequest> model=new ArrayList<>();

        RequestQueue queue= RequestQueueSingleTon.getInstance().getRequestQueue(MyBloodRequestsActivity.this);
        final SharedPreferences preference = getSharedPreferences("settings",MODE_PRIVATE);
        String doctorProfile = preference.getString(links.PrefDoctorProfile,"notfound");
        Doctor doctor = new Gson().fromJson(doctorProfile,Doctor.class);

        try {

            JSONArray jsonBody = new JSONArray();

            final ProgressDialog progressDialog = new ProgressDialog(MyBloodRequestsActivity.this);

            JsonArrayRequest jsonObjRequest = new JsonArrayRequest(Request.Method.GET, links.GetDoctorRequests+doctor.getDrId() , jsonBody, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    progressDialog.hide();
                    //      Toast.makeText(MyPatientActivity.this, "my patient req list: "+response.toString(), Toast.LENGTH_SHORT).show();
                    System.out.println("my blood req list: "+response.toString());

                    for (int i = 0 ; i < response.length(); i++) {
                        try {

                        JSONObject bloodRequestsJson = response.getJSONObject(i);

                        String bloodType = bloodRequestsJson.getString("bloodType");
                        int drId = bloodRequestsJson.getInt("drId");
                        int quantity = bloodRequestsJson.getInt("quantity");
                        String reason = bloodRequestsJson.getString("reason");
                        int requestsId = bloodRequestsJson.getInt("requestsId");
                        int status = bloodRequestsJson.getInt("status");
                        String timestamp = bloodRequestsJson.getString("timestamp");

                        //String name, String BDay, String phoneNum, String gender, String image, String BloodType
                        MyBloodRequest myBloodRequest =  new MyBloodRequest(bloodType,drId,quantity,reason,requestsId,status,timestamp);

                        model.add(myBloodRequest);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    BloodRequestsAdapter adapter=new BloodRequestsAdapter(MyBloodRequestsActivity.this,model);

                    lv.setAdapter(adapter);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //      Toast.makeText(MyPatientActivity.this, "error my patient req list: "+error.toString(), Toast.LENGTH_SHORT).show();
                    System.out.println("error my blood req list: "+error.toString());
                    progressDialog.hide();

                }
            });

            progressDialog.setMessage("Loading...");
            progressDialog.show();
            queue.add(jsonObjRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        lv.setEmptyView(findViewById(R.id.emptyElement));

    }
}
