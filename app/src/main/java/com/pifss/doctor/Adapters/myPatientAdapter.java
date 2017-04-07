package com.pifss.doctor.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
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
    public View getView(int position, View convertView, final ViewGroup parent) {


        View view = inflater.inflate(R.layout.list_item_my_patient,null);

        ImageView img= (ImageView) view.findViewById(R.id.imageViewPatient);
        TextView name = (TextView) view.findViewById(R.id.textViewName);
        TextView age = (TextView) view.findViewById(R.id.textViewAge);
        TextView phone= (TextView) view.findViewById(R.id.textViewPhone);
        TextView gender= (TextView) view.findViewById(R.id.textViewGender);
        TextView BDay= (TextView) view.findViewById(R.id.BDay);
        TextView BloodType= (TextView) view.findViewById(R.id.BloodType);




        final MyPatient patient = model.get(position);
        // img.setImageResource(Integer.parseInt(patient.getImage()));

        if (!patient.getImage().equals("")){

            Picasso.with(this.context).load(patient.getImage()).into(img);
        }

        name.setText(patient.getName());

        BDay.setText(patient.getBDay());

        BloodType.setText(patient.getBloodType());
        gender.setText(patient.getGender());

//
//        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
//        Date startDate;
//        try {
//            startDate = df.parse(patient.getAge());
//
//            String newDateString = df.format(startDate);
//            System.out.println(newDateString);
//
//            Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
//            calendar.setTime(startDate);   // assigns calendar to given date
//
//
//            LocalDate birthdate = new LocalDate (calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH));
//            LocalDate now = new LocalDate();
//            Years patientAge = Years.yearsBetween(birthdate, now);
//
//            age.setText("Age: "+patientAge.getYears());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


        phone.setText(patient.getPhoneNum());

        ImageView phoneImage = (ImageView) view.findViewById(R.id.PhoneImage);
        phoneImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);

                builder.setTitle("Call "+patient.getName())
                        .setMessage("Are you sure you wanna call "+patient.getName()+" ?")
                        .setIcon(R.mipmap.phonecall)
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i=new Intent();
                                i.setAction(Intent.ACTION_DIAL);
                                i.setData(Uri.parse("tel:"+patient.getPhoneNum()));

                                context.startActivity(i);
                            }
                        }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });



                Dialog dConfirm=builder.create();
                dConfirm.show();
            }
        });
        return view;

    }
}
