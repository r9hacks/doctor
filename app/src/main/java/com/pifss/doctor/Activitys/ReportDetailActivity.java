package com.pifss.doctor.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.pifss.doctor.Model.Doctor;
import com.pifss.doctor.Model.Report;
import com.pifss.doctor.R;
import com.pifss.doctor.links;

public class ReportDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);

        toolbar.setTitle(R.string.ReportDetail);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton3);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReportDetailActivity.this,ReplyReportActivity.class);
                startActivity(i);
            }
        });


        SharedPreferences preference = getSharedPreferences("settings",MODE_PRIVATE);
        String reportJson = preference.getString(links.PrefReport,"notfound");
        Report report = new Gson().fromJson(reportJson,Report.class);

        TextView blood = (TextView) findViewById(R.id.textViewbloodContent);
        TextView coughing = (TextView) findViewById(R.id.textViewcoughingContent);
        TextView dizzness = (TextView) findViewById(R.id.textViewdizznessContent);
        TextView fever = (TextView) findViewById(R.id.textViewfeverContent);
        TextView headache = (TextView) findViewById(R.id.textViewheadacheContent);
        TextView heartRate = (TextView) findViewById(R.id.textViewheartRateContent);
        TextView nouseous = (TextView) findViewById(R.id.textViewanouseouContent);
        TextView painLocationod = (TextView) findViewById(R.id.textViewpainLocationContent);
        TextView sugarLevel = (TextView) findViewById(R.id.textViewsugarContent);
        TextView comment = (TextView) findViewById(R.id.textViewcommentsContent);


        blood.setText(report.getBloodPressure());
        coughing.setText(report.getCoughing());
        dizzness.setText(report.getDizziness());
        fever.setText(report.getFever());
        headache.setText(report.getHeadache());
        heartRate.setText(report.getHeartbeatRate());
        nouseous.setText(report.getNauseous());
        sugarLevel.setText(report.getSugarLevel());
        comment.setText(report.getComments());
        if (report.getPain()){
            painLocationod.setText(report.getPainlocation());
        }else{
            painLocationod.setText(R.string.NOPain);
        }

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
