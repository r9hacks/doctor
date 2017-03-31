package com.pifss.doctor.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pifss.doctor.R;
import com.pifss.doctor.Model.ReportCell;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by PIFSS on 3/27/2017.
 */

public class ReportAdapter extends BaseAdapter {

    Activity context;
    LayoutInflater inflater;
    ArrayList<ReportCell> model;


    public ReportAdapter(Activity context, ArrayList<ReportCell> model) {
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
        View v = inflater.inflate(R.layout.list_item_report,null);
        ImageView image = (ImageView) v.findViewById(R.id.imageView);
        TextView name = (TextView) v.findViewById(R.id.textViewName);

        TextView comments = (TextView) v.findViewById(R.id.textViewComments);
        TextView date = (TextView) v.findViewById(R.id.textDate);

        TextView heartRate = (TextView) v.findViewById(R.id.heartRate);
        TextView bloodPressure = (TextView) v.findViewById(R.id.bloodPressure);
        TextView gender = (TextView) v.findViewById(R.id.gender);

        ReportCell report = model.get(position);

      //  image.setImageResource(m.getImage());
//        Picasso.with(context).invalidate(m.getImage());
        if (!report.getImageURL().equals("")){

            Picasso.with(this.context).load(report.getImageURL()).into(image);
        }

        name.setText(report.getName());
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

        String g = report.getGender();
        if ( (g.charAt(0) + "").equalsIgnoreCase("f")){
            gender.setText("Female");
        }else{
            gender.setText("Male");
        }

        return v;
    }
}
