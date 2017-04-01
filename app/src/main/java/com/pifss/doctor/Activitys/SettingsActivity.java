package com.pifss.doctor.Activitys;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pifss.doctor.R;
import com.pifss.doctor.links;

import java.util.Locale;


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
                Configuration config = getResources().getConfiguration();

                    config.locale = new Locale("en");

                getResources().updateConfiguration(config,getResources().getDisplayMetrics());

                SharedPreferences preference = getSharedPreferences("settings", MODE_PRIVATE);
                SharedPreferences.Editor editor = preference.edit();
                editor.putString(links.PrefLanguage, "en");
                editor.commit();

                Intent i = new Intent(SettingsActivity.this,SplashActivity.class);
                finish();
                startActivity(i);
            }
        });

        Button arabic = (Button) findViewById(R.id.buttonArabic);

        arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            //    Toast.makeText(SettingsActivity.this, R.string.LangButton, Toast.LENGTH_SHORT).show();
                Configuration config = getResources().getConfiguration();

                    config.locale = new Locale("ar");


                    getResources().updateConfiguration(config,getResources().getDisplayMetrics());

                SharedPreferences preference = getSharedPreferences("settings", MODE_PRIVATE);
                SharedPreferences.Editor editor = preference.edit();
                editor.putString(links.PrefLanguage, "ar");
                editor.commit();

                Intent i = new Intent(SettingsActivity.this,SplashActivity.class);
                finish();
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
