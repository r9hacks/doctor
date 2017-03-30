package com.pifss.doctor.Activitys;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.pifss.doctor.Model.Report;
import com.pifss.doctor.R;
import com.pifss.doctor.links;

public class ReplyReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_report);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        toolbar.setTitle("Reply to Report");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        SharedPreferences preference = getSharedPreferences("settings",MODE_PRIVATE);
        String reportJson = preference.getString(links.PrefReport,"notfound");
        Report report = new Gson().fromJson(reportJson,Report.class);

        TextView comment = (TextView) findViewById(R.id.textViewcommentsContent);
        comment.setText(report.getComments());

        EditText reply = (EditText) findViewById(R.id.editTextReply);
        reply.setText(report.getDrcomment());

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
