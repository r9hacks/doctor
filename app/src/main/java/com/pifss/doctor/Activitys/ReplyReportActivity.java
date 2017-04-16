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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.pifss.doctor.Model.Doctor;
import com.pifss.doctor.Model.Report;
import com.pifss.doctor.R;
import com.pifss.doctor.RequestQueueSingleTon;
import com.pifss.doctor.links;

import org.json.JSONException;
import org.json.JSONObject;

public class ReplyReportActivity extends AppCompatActivity {
    int tag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_report);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        toolbar.setTitle(R.string.ReplytoReport);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        SharedPreferences preference = getSharedPreferences("settings",MODE_PRIVATE);
        String reportJson = preference.getString(links.PrefReport,"notfound");
        final Report report = new Gson().fromJson(reportJson,Report.class);

        final TextView comment = (TextView) findViewById(R.id.textViewcommentsContent);
        comment.setText(report.getComments());

        final EditText reply = (EditText) findViewById(R.id.editTextReply);
        reply.setText(report.getDrcomment());

        final Button sendButton = (Button) findViewById(R.id.buttonSendReply);
        final RequestQueue queue= RequestQueueSingleTon.getInstance().getRequestQueue(ReplyReportActivity.this);

        if (comment.getText().toString() != "") {

            reply.setEnabled(false);
            sendButton.setText(R.string.Editreply);
            tag = 1;

        }else{

            sendButton.setText(R.string.SendReply);
            tag = 0;

            return;
        }


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getIntent().getStringExtra("message")
                //UpdatePatientReportDRec

                if (tag == 1) {

                    reply.setEnabled(true);
                    sendButton.setText(R.string.SendReply);
                    tag = 0;
                } else if(tag == 0) {
                    reply.setEnabled(false);
                    sendButton.setText(R.string.Editreply);
                    tag = 1;

                    final ProgressDialog progressDialog = new ProgressDialog(ReplyReportActivity.this);

                    try {

                        JSONObject jsonBody = new JSONObject();
                        jsonBody.put("reportId",report.getReportId());
                        jsonBody.put("drcomment",reply.getText().toString());


                        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.POST, links.ReplyReport, jsonBody, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                progressDialog.hide();
                                try {
                                    Intent i = new Intent(ReplyReportActivity.this, ReplyConfirmActivity.class);
                                    // Toast.makeText(ReplyReportActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                                    if (response.getBoolean("status") == true){
                                        i.putExtra("message","Your Reply sent successfully");
                                        //i.putExtra("message",R.string.YourReplysentsuccessfully);

                                    }else{
                                        i.putExtra("message","Your Reply sent Failed");

                                    }
                                    i.putExtra("goTo","report");
                                    startActivity(i);

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

                        progressDialog.setMessage(getResources().getString(R.string.Connecting));
                        progressDialog.show();
                        queue.add(jsonObjRequest);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return;
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
