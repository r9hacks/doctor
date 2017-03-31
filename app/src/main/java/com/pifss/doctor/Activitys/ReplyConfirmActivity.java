package com.pifss.doctor.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.pifss.doctor.R;

public class ReplyConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_confirm);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        toolbar.setTitle(R.string.ReplySent);
        toolbar.setTitleTextColor(Color.WHITE);

        TextView message = (TextView) findViewById(R.id.messageTextView);
        message.setText(getIntent().getStringExtra("message"));
        final String goTo = getIntent().getStringExtra("goTo");
        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 5 seconds
                    sleep(3*1000);

                    // After 5 seconds redirect to another intent
                    if (goTo.equalsIgnoreCase("patient")){
                        Intent i=new Intent(ReplyConfirmActivity.this,PatientProfileActivity.class);
                        startActivity(i);
                    }
                    if (goTo.equalsIgnoreCase("report")){
                        Intent i=new Intent(ReplyConfirmActivity.this,HomeReportActivity.class);
                        startActivity(i);
                    }


                    //Remove activity
                    finish();

                } catch (Exception e) {

                }
            }
        };

        // start thread
        background.start();
    }
}
