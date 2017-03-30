package com.pifss.doctor.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.pifss.doctor.Activitys.MyPatientActivity;
import com.pifss.doctor.Activitys.ReportDetailActivity;
import com.pifss.doctor.Adapters.ReportAdapter;
import com.pifss.doctor.Model.Doctor;
import com.pifss.doctor.Model.ReportCell;
import com.pifss.doctor.R;
import com.pifss.doctor.RequestQueueSingleTon;
import com.pifss.doctor.links;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Replied extends Fragment {


    public Replied() {
        // Required empty public constructor
    }
    ArrayList<ReportCell> model = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_replied, container, false);
        fillModel();

        RequestQueue queue= RequestQueueSingleTon.getInstance().getRequestQueue(getActivity());
        SharedPreferences preference = getActivity().getSharedPreferences("settings",getActivity().MODE_PRIVATE);
        String doctorProfile = preference.getString(links.PrefDoctorProfile,"notfound");
        Doctor doctor = new Gson().fromJson(doctorProfile,Doctor.class);



        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("time",0);
            jsonBody.put("drId",doctor.getDrId());
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, links.GetRepliedReport, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        JSONArray repliedArray = new JSONArray();
                        JSONArray repliedArrayTemp = new JSONArray(response);
                    for (int i = 0 ; i< repliedArrayTemp.length();i++){

                        JSONObject report = repliedArrayTemp.getJSONObject(i);

                        if (report.has("drcomment")){
                            String drcomment = null;

                                drcomment = report.getString("drcomment");

                            if (!drcomment.equalsIgnoreCase("")){
                                repliedArray.put(report);
                            }
                        }

                    }

                    Toast.makeText(getActivity(), "report replied list: "+repliedArray.toString(), Toast.LENGTH_SHORT).show();
                    System.out.println("report replied list: "+repliedArray.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getActivity(), "error report replied list: "+error.toString(), Toast.LENGTH_SHORT).show();
                    System.out.println("error report replied list: "+error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    try {
                        String jsonString = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        //Allow null
                        if (jsonString == null || jsonString.length() == 0) {
                            return Response.success(null, HttpHeaderParser.parseCacheHeaders(response));
                        }
                        return Response.success(jsonString,
                                HttpHeaderParser.parseCacheHeaders(response));

                    } catch (UnsupportedEncodingException e) {
                        return Response.error(new ParseError(e));
                    }
                }
            };

            queue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final ReportAdapter adapter = new ReportAdapter(getActivity(),model);

        ListView myList = (ListView) view.findViewById(R.id.listView);

        myList.setAdapter(adapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), ReportDetailActivity.class);
                startActivity(i);
            }
        });

        return view;
    }

    void fillModel(){
        model.add(new ReportCell("Sophie ALSaffar","Today","i dont feel good today ha ha ha","","Female","80","120/80"));
        model.add(new ReportCell("Sophie ALSaffar","Today","i dont feel good today ha ha ha","","Female","80","120/80"));
        model.add(new ReportCell("Sophie ALSaffar","Today","i dont feel good today ha ha ha","","Female","80","120/80"));
        model.add(new ReportCell("Sophie ALSaffar","Today","i dont feel good today ha ha ha","","Female","80","120/80"));
        model.add(new ReportCell("Sophie ALSaffar","Today","i dont feel good today ha ha ha","","Female","80","120/80"));
        model.add(new ReportCell("Sophie ALSaffar","Today","i dont feel good today ha ha ha","","Female","80","120/80"));
        model.add(new ReportCell("Sophie ALSaffar","Today","i dont feel good today ha ha ha","","Female","80","120/80"));
        model.add(new ReportCell("Sophie ALSaffar","Today","i dont feel good today ha ha ha","","Female","80","120/80"));
    }
}
