package com.pifss.doctor;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Entisar on 3/27/17.
 */

public class RequestAdapter extends BaseAdapter {

    Activity context;
    LayoutInflater inflater;

    ArrayList<PatientRequest> model;


    public RequestAdapter(Activity context, ArrayList<PatientRequest> model) {
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



        View view = inflater.inflate(R.layout.list_item_patient_invitation,null);

        ImageView img= (ImageView) view.findViewById(R.id.imageViewPatient);
        TextView name = (TextView) view.findViewById(R.id.textViewName);
        TextView age = (TextView) view.findViewById(R.id.textViewAge);
        TextView civil = (TextView) view.findViewById(R.id.textViewCivilId);
        TextView gender= (TextView) view.findViewById(R.id.textViewGender);




        PatientRequest patient = model.get(position);
        // img.setImageResource(Integer.parseInt(patient.getImage()));

        name.setText(patient.getName());

        gender.setText(patient.getGender());

        age.setText(patient.getAge()+"");
        civil.setText(patient.getCivilId());





        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "User clicked Image", Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }
}
