package com.pifss.doctor.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pifss.doctor.Model.MyPatient;
import com.pifss.doctor.R;
import com.squareup.picasso.Picasso;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Entisar on 3/27/17.
 */

public class myPatientAdapter extends BaseAdapter {


    Activity context;
    LayoutInflater inflater;

    ArrayList<MyPatient> model;


    public myPatientAdapter(Activity context, ArrayList<MyPatient> model) {
        this.model = model;
        this.context = context;

        inflater= (LayoutInflater) context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);


    }


    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view = inflater.inflate(R.layout.list_item_my_patient,null);

        ImageView img= (ImageView) view.findViewById(R.id.imageViewPatient);
        TextView name = (TextView) view.findViewById(R.id.textViewName);
        TextView age = (TextView) view.findViewById(R.id.textViewAge);
        TextView phone= (TextView) view.findViewById(R.id.textViewPhone);
        TextView gender= (TextView) view.findViewById(R.id.textViewGender);




        MyPatient patient = model.get(position);
        // img.setImageResource(Integer.parseInt(patient.getImage()));

        if (!patient.getImage().equals("")){

            Picasso.with(this.context).load(patient.getImage()).into(img);
        }

        name.setText(patient.getName());

        gender.setText(patient.getGender());

        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        Date startDate;
        try {
            startDate = df.parse(patient.getAge());

            String newDateString = df.format(startDate);
            System.out.println(newDateString);

            Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
            calendar.setTime(startDate);   // assigns calendar to given date


            LocalDate birthdate = new LocalDate (calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH));
            LocalDate now = new LocalDate();
            Years patientAge = Years.yearsBetween(birthdate, now);

            age.setText("Age: "+patientAge.getYears());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        phone.setText(patient.getPhoneNum());


//
//
//
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "User clicked Image", Toast.LENGTH_SHORT).show();
//            }
//        });

        return view;

    }
}
