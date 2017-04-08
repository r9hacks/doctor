package com.pifss.doctor.Adapters;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.pifss.doctor.Activitys.EditDoctorProfileActivity;
import com.pifss.doctor.Activitys.PatientRequestActivity;
import com.pifss.doctor.Model.PatientRequest;
import com.pifss.doctor.R;
import com.pifss.doctor.RequestQueueSingleTon;
import com.pifss.doctor.links;
import com.squareup.picasso.Picasso;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
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
    public View getView(final int position, View convertView, ViewGroup parent) {



        View view = inflater.inflate(R.layout.list_item_patient_invitation,null);

        ImageView img= (ImageView) view.findViewById(R.id.imageViewPatient);
        TextView name = (TextView) view.findViewById(R.id.textViewName);

        TextView civil = (TextView) view.findViewById(R.id.textViewCivilId);
        TextView gender= (TextView) view.findViewById(R.id.textViewGender);

        TextView txtbday = (TextView) view.findViewById(R.id.txtbday);
        TextView phone = (TextView) view.findViewById(R.id.txtPhoneP);
        TextView bloodType = (TextView) view.findViewById(R.id.txtBloodType);
        TextView nationality = (TextView) view.findViewById(R.id.txtNationalityP);

        final ImageButton accept = (ImageButton) view.findViewById(R.id.imageButtonAccept);
        final ImageButton decline = (ImageButton) view.findViewById(R.id.imageButtonDecline);




        final PatientRequest patient = model.get(position);

        phone.setText(patient.getPatientObject().getPhoneNumber());
        bloodType.setText(patient.getPatientObject().getBloodType());
        nationality.setText(patient.getPatientObject().getNationality());

        // img.setImageResource(Integer.parseInt(patient.getImage()));
        if (!patient.getImage().equals("")){

            Picasso.with(this.context).load(patient.getImage()).into(img);
        }
        name.setText(patient.getName());

        String g = patient.getGender();
        if ( (g.charAt(0) + "").equalsIgnoreCase("f")){
            gender.setText("Female");
        }else{
            gender.setText("Male");
        }

        txtbday.setText(patient.getAge());

        /*

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

            String ageLocalized = context.getString(R.string.Age);
            txtbday.setText(ageLocalized + ": "+patientAge.getYears());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        */



        civil.setText(patient.getCivilId());


        final RequestQueue queue= RequestQueueSingleTon.getInstance().getRequestQueue(context);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(context, "clicked accept", Toast.LENGTH_SHORT).show();

                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("addingTime",patient.getPatientObject().getAddingTime());
                    jsonBody.put("drId",patient.getPatientObject().getDrId());
                    jsonBody.put("linkId",patient.getPatientObject().getLinkId());
                    jsonBody.put("patientId",patient.getPatientObject().getPatientId());
                    jsonBody.put("status",1);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String URL = links.UpdateRequestStatus + patient.getPatientObject().getLinkId();
                System.out.println("body:"+jsonBody.toString());
                System.out.println("link:"+URL);
                JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.PUT, URL, jsonBody, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if (response.getBoolean("status") == true){

                                Toast.makeText(context, "Patient accepted successfully", Toast.LENGTH_SHORT).show();
                                model.remove(position);
                                notifyDataSetChanged();
                            }else{
                                Toast.makeText(context, "Patient accepted failed", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        System.out.println("response: "+response.toString());


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Patient accepted failed", Toast.LENGTH_SHORT).show();

                    }
                }){
                    @Override
                    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                        String jsonString = "";
                        JSONObject object = new JSONObject();

                        try {
                            System.out.println("statuscode: "+response.statusCode);
                            if (response.statusCode <200 || response.statusCode >300){

                                object.put("status",false);
                                return Response.success(object,
                                        HttpHeaderParser.parseCacheHeaders(response));
                            }
                            jsonString = new String(response.data, "UTF-8");
                            System.out.println("jsonString");

                            System.out.println("\""+jsonString+"\"");
                            if (jsonString.equals("")){
                                object.put("status",true);
                            }else{
                                object.put("status",false);
                            }

                            return Response.success(object,
                                    HttpHeaderParser.parseCacheHeaders(response));

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return Response.success(object,
                                HttpHeaderParser.parseCacheHeaders(response));

                    }
                };

                queue.add(jsonObjRequest);

            }
        });


        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(context, "clicked decline", Toast.LENGTH_SHORT).show();
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("addingTime",patient.getPatientObject().getAddingTime());
                    jsonBody.put("drId",patient.getPatientObject().getDrId());
                    jsonBody.put("linkId",patient.getPatientObject().getLinkId());
                    jsonBody.put("patientId",patient.getPatientObject().getPatientId());
                    jsonBody.put("status",-1);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String URL = links.UpdateRequestStatus + patient.getPatientObject().getLinkId();
                System.out.println("body:"+jsonBody.toString());
                System.out.println("link:"+URL);
                JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.PUT, URL, jsonBody, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if (response.getBoolean("status") == true){

                                Toast.makeText(context, "Patient declined successfully", Toast.LENGTH_SHORT).show();
                                model.remove(position);
                                notifyDataSetChanged();
                            }else{
                                Toast.makeText(context, "Patient declined failed", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        System.out.println("response: "+response.toString());


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Patient declined failed", Toast.LENGTH_SHORT).show();

                    }
                }){
                    @Override
                    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                        String jsonString = "";
                        JSONObject object = new JSONObject();

                        try {
                            System.out.println("statuscode: "+response.statusCode);
                            if (response.statusCode <200 || response.statusCode >300){

                                object.put("status",false);
                                return Response.success(object,
                                        HttpHeaderParser.parseCacheHeaders(response));
                            }
                            jsonString = new String(response.data, "UTF-8");
                            System.out.println("jsonString");

                            System.out.println("\""+jsonString+"\"");
                            if (jsonString.equals("")){
                                object.put("status",true);
                            }else{
                                object.put("status",false);
                            }

                            return Response.success(object,
                                    HttpHeaderParser.parseCacheHeaders(response));

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return Response.success(object,
                                HttpHeaderParser.parseCacheHeaders(response));

                    }
                };

                queue.add(jsonObjRequest);
            }
        });




        return view;

    }
}
