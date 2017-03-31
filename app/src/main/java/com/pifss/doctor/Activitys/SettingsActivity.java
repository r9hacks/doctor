package com.pifss.doctor.Activitys;


import android.graphics.Color;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pifss.doctor.R;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);

        toolbar.setTitle(R.string.Settings);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        EditText txtLogo =(EditText) findViewById(R.id.txtChooseLang);
        txtLogo.setEnabled(false);



        Button english = (Button) findViewById(R.id.buttonEnglish);

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(SettingsActivity.this, R.string.LangButton, Toast.LENGTH_SHORT).show();

            }
        });

        Button arabic = (Button) findViewById(R.id.buttonArabic);

        arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            //    Toast.makeText(SettingsActivity.this, R.string.LangButton, Toast.LENGTH_SHORT).show();

            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
