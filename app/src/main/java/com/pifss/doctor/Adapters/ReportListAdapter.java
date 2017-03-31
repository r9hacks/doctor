package com.pifss.doctor.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pifss.doctor.Model.ReportList;
import com.pifss.doctor.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Entisar on 3/28/17.
 */

public class ReportListAdapter extends BaseAdapter {


    Activity context;
    LayoutInflater inflater;
    ArrayList<ReportList> model;

    public ReportListAdapter(Activity context, ArrayList<ReportList> model) {
        this.context = context;
        this.model = model;
        inflater = (LayoutInflater) context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);

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

        View view = inflater.inflate(R.layout.list_item_patient_reports,null);


        TextView comments = (TextView) view.findViewById(R.id.patientCommentTV);
        TextView date = (TextView) view.findViewById(R.id.DateTV);

        TextView heartRate = (TextView) view.findViewById(R.id.heartRateTV);
        TextView bloodPressure = (TextView) view.findViewById(R.id.bloodPressureTV);
        TextView fever = (TextView) view.findViewById(R.id.feverTV);


        ReportList report = model.get(position);

        //image.setImageResource(m.getImage());
//        Picasso.with(context).invalidate(m.getImage());
//        Picasso.with(this.context).load(m.getImage()).into(image);


        comments.setText(report.getComment());


        DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        Date startDate;
        try {
            startDate = df.parse(report.getDate());

            String newDateString = df.format(startDate);
            System.out.println(newDateString);

            Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
            calendar.setTime(startDate);   // assigns calendar to given date


            date.setText(calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR));

        } catch (ParseException e) {
            e.printStackTrace();
        }



        heartRate.setText(report.getHeartRate());
        bloodPressure.setText(report.getBloodPreassure());
        fever.setText(report.getFever());

        return view;
    }
}
