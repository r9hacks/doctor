package com.pifss.doctor.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pifss.doctor.Model.MyBloodRequest;
import com.pifss.doctor.R;

import java.util.ArrayList;

/**
 * Created by PIFSS on 4/11/2017.
 */

public class BloodRequestsAdapter extends BaseAdapter {


    Activity context;
    LayoutInflater inflater;

    ArrayList<MyBloodRequest> model;


    public BloodRequestsAdapter(Activity context, ArrayList<MyBloodRequest> model) {
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

        View view = inflater.inflate(R.layout.list_item_blood_requests,null);

        ImageView img= (ImageView) view.findViewById(R.id.BloodDropImageView);

        TextView quantity= (TextView) view.findViewById(R.id.textViewQuantity);
        TextView status= (TextView) view.findViewById(R.id.textViewStatus);
        TextView reason= (TextView) view.findViewById(R.id.textViewReason);

        final MyBloodRequest myBloodRequest = model.get(position);


        quantity.setText("Quantity:" + myBloodRequest.getQuantity());
        reason.setText(myBloodRequest.getReason());

        if (myBloodRequest.getStatus() == 1){
            status.setText("Available");
        }else if (myBloodRequest.getStatus() == -1){
            status.setText("Not Available");
        }else {
            status.setText("Pending");
        }

        if (myBloodRequest.getBloodType().equalsIgnoreCase("A+")){
            img.setImageResource(R.mipmap.a_plus);
        }else if (myBloodRequest.getBloodType().equalsIgnoreCase("A-")){
            img.setImageResource(R.mipmap.a_minus);
        }else if (myBloodRequest.getBloodType().equalsIgnoreCase("B+")){
            img.setImageResource(R.mipmap.b_plus);
        }else if (myBloodRequest.getBloodType().equalsIgnoreCase("B-")){
            img.setImageResource(R.mipmap.b_minus);
        }else if (myBloodRequest.getBloodType().equalsIgnoreCase("O+")){
            img.setImageResource(R.mipmap.o_plus);
        }else if (myBloodRequest.getBloodType().equalsIgnoreCase("O-")){
            img.setImageResource(R.mipmap.o_minus);
        }else if (myBloodRequest.getBloodType().equalsIgnoreCase("AB+")){
            img.setImageResource(R.mipmap.ab_plus);
        }else{
            img.setImageResource(R.mipmap.ab_minus);
        }

        return view;

    }
}
