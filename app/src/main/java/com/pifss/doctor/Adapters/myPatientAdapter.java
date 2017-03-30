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

import java.util.ArrayList;

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

        name.setText(patient.getName());

        gender.setText(patient.getGender());

        age.setText(patient.getAge()+"");
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
