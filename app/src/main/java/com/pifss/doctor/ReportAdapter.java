package com.pifss.doctor;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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

        //image.setImageResource(m.getImage());
//        Picasso.with(context).invalidate(m.getImage());
//        Picasso.with(this.context).load(m.getImage()).into(image);

        name.setText(report.getName());
        comments.setText(report.getComment());
        date.setText(report.getDate());
        heartRate.setText(report.getHeartRate());
        bloodPressure.setText(report.getBloodPreassure());
        gender.setText(report.getGender());

        return v;
    }
}
