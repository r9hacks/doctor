package com.pifss.doctor.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
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
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pifss.doctor.Activitys.ReportDetailActivity;
import com.pifss.doctor.Adapters.ReportAdapter;
import com.pifss.doctor.Model.Doctor;
import com.pifss.doctor.Model.Report;
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
public class Pending extends Fragment {


    public Pending() {
        // Required empty public constructor
    }

    ArrayList<ReportCell> model = new ArrayList<>();
    ReportAdapter adapter;
    ListView myList;
    View view;
    String feverString = "All";
    String pressureString = "All";
    String rateString = "All";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_pending, container, false);

        RequestQueue queue = RequestQueueSingleTon.getInstance().getRequestQueue(getActivity());
        SharedPreferences preference = getActivity().getSharedPreferences("settings", getActivity().MODE_PRIVATE);
        String doctorProfile = preference.getString(links.PrefDoctorProfile, "notfound");
        Doctor doctor = new Gson().fromJson(doctorProfile, Doctor.class);

        adapter = new ReportAdapter(getActivity(), model);
        myList = (ListView) view.findViewById(R.id.pendingListView);
        myList.setAdapter(adapter);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ReportCell rCell = model.get(position);
                try {
                    JSONObject jsonReport = rCell.getReportObject().getJSONReport();
                    SharedPreferences preference = getActivity().getSharedPreferences("settings", getActivity().MODE_PRIVATE);
                    SharedPreferences.Editor editor = preference.edit();
                    editor.putString(links.PrefReport, jsonReport.toString());
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Intent i = new Intent(getActivity(), ReportDetailActivity.class);
                startActivity(i);
            }
        });

        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("time", 0);
            jsonBody.put("drId", doctor.getDrId());
            final String requestBody = jsonBody.toString();
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());

            StringRequest stringRequest = new StringRequest(Request.Method.POST, links.GetRepliedReport, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.hide();
                    try {

                        JSONArray pendingArray = new JSONArray();
                        JSONArray pendingArrayTemp = new JSONArray(response);
                        for (int i = 0; i < pendingArrayTemp.length(); i++) {

                            JSONObject report = pendingArrayTemp.getJSONObject(i);

                            if (report.has("drcomment")) {
                                String drcomment = report.getString("drcomment");

                                if (drcomment.equalsIgnoreCase("")) {
                                    pendingArray.put(report);
                                }
                            }
                        }


                        ArrayList<Report> report = new Gson().fromJson(pendingArray.toString(), new TypeToken<ArrayList<Report>>() {
                        }.getType());
                        for (Report r : report) {
                            model.add(new ReportCell(r.getName(), r.getTimestamp(), r.getComments(), r.getImg(), r.getGender(), r.getHeartbeatRate(), r.getBloodPressure(), r.getFever(), r));
                            adapter.notifyDataSetChanged();
                        }


                        // Toast.makeText(getActivity(), "report pending list: "+pendingArray.toString(), Toast.LENGTH_SHORT).show();
                        System.out.println("report pending list: " + pendingArray.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.hide();

                    // Toast.makeText(getActivity(), "error report pending list: "+error.toString(), Toast.LENGTH_SHORT).show();
                    System.out.println("error report pending list: " + error.toString());
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

            progressDialog.setMessage("Loading...");
            progressDialog.show();
            queue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        myList.setEmptyView(view.findViewById(R.id.emptyElement));


        //Spinner code
        Spinner spinHeart = (Spinner) view.findViewById(R.id.spinnerHeart);
        final String[] rate = { getResources().getString(R.string.All), getResources().getString(R.string.High), getResources().getString(R.string.Low), getResources().getString( R.string.Moderate),};

        Spinner spinBlood = (Spinner) view.findViewById(R.id.spinnerBlood);
        final String[] bloodPressure = {getResources().getString(R.string.All), getResources().getString(R.string.High), getResources().getString(R.string.Low), getResources().getString( R.string.Moderate),};

        Spinner spinFever = (Spinner) view.findViewById(R.id.spinnerFever);
        final String[] feverArray = {getResources().getString(R.string.All), getResources().getString(R.string.Yes), getResources().getString(R.string.No),};

        spinHeart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //    Toast.makeText(getActivity(), "clicked: "+rate[position], Toast.LENGTH_SHORT).show();

                if (position == 0)
                {
                    rateString = "All";
                }
                else if (position == 1)
                {
                    rateString = "High";
                }
                else if (position == 2)
                {
                    rateString = "Low";
                }else if (position == 3)
                {
                    rateString = "Moderate";
                }

                initAdapterWithFilter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //          Toast.makeText(getActivity(), "nothing selected ", Toast.LENGTH_SHORT).show();

            }
        });
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter heartAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, rate);
        heartAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinHeart.setAdapter(heartAdapter);


        //blood click

        spinBlood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //   Toast.makeText(getActivity(), "clicked: "+bloodPressure[position], Toast.LENGTH_SHORT).show();


                if (position == 0)
                {
                    pressureString = "All";

                }
                else if (position == 1)
                {
                    pressureString =  "High";
                           // "High";
                }
                else if (position == 2)
                {
                    pressureString = "Low";
                }else if (position == 3)
                {
                    pressureString = "Moderate";
                }

                initAdapterWithFilter();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter bloodAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, bloodPressure);
        bloodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinBlood.setAdapter(bloodAdapter);


        //fever click


        spinFever.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (position == 0)
                {
                    feverString = "All";
                }
                else if (position == 1)
                {
                    feverString = "Yes";
                }
                else if (position == 2)
                {
                    feverString = "No";
                }

                initAdapterWithFilter();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter feverAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, feverArray);
        feverAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinFever.setAdapter(feverAdapter);


        return view;
    }


    private void initAdapterWithFilter() {

        if (model == null || model.size() <= 0) {
            return;
        }

        final ArrayList<ReportCell> ChosenModel = new ArrayList<>();

        for (int i = 0; i < model.size(); i++) {
            ReportCell reportChosen = model.get(i);
            String rateHeart = reportChosen.getHeartRate().toLowerCase();
            String bloodPressure = reportChosen.getBloodPreassure().toLowerCase();
            final String fever = reportChosen.getFever().toLowerCase();


            if (rateString.equalsIgnoreCase("All") && pressureString.equalsIgnoreCase("All") && feverString.equalsIgnoreCase("All"))

            {
                ChosenModel.add(reportChosen);
            }

            // 1 first
            else if (!rateString.equalsIgnoreCase("All") && pressureString.equalsIgnoreCase("All") && feverString.equalsIgnoreCase("All")) {
                if (rateHeart.contains(rateString.toLowerCase())) {
                    ChosenModel.add(reportChosen);
                }
            }


            // 1 second
            else if (rateString.equalsIgnoreCase("All") && !pressureString.equalsIgnoreCase("All") && feverString.equalsIgnoreCase("All")) {
                if (bloodPressure.contains(pressureString.toLowerCase())) {
                    ChosenModel.add(reportChosen);
                }
            }


            // 1 third
            else if (rateString.equalsIgnoreCase("All") && pressureString.equalsIgnoreCase("All") && !feverString.equalsIgnoreCase("All")) {
                if (fever.contains(feverString.toLowerCase())) ;
                {
                    ChosenModel.add(reportChosen);
                }
            }


            // 2 first second
            else if (!rateString.equalsIgnoreCase("All") && !pressureString.equalsIgnoreCase("All") && feverString.equalsIgnoreCase("All")) {
                if (rateHeart.contains(rateString.toLowerCase()) && bloodPressure.contains(pressureString.toLowerCase())) {
                    ChosenModel.add(reportChosen);
                }
            }


            // 2 second third
            else if (rateString.equalsIgnoreCase("All") && !pressureString.equalsIgnoreCase("All") && !feverString.equalsIgnoreCase("All")) {
                if (bloodPressure.contains(pressureString.toLowerCase()) && fever.contains(feverString.toLowerCase())) {
                    ChosenModel.add(reportChosen);
                }
            }


            // 2 first third
            else if (!rateString.equalsIgnoreCase("All") && pressureString.equalsIgnoreCase("All") && !feverString.equalsIgnoreCase("All")) {
                if (rateHeart.contains(rateString.toLowerCase()) && fever.contains(feverString.toLowerCase())) {
                    ChosenModel.add(reportChosen);
                }
            }


            // 3 all
            else if (!rateString.equalsIgnoreCase("All") && !pressureString.equalsIgnoreCase("All") && !feverString.equalsIgnoreCase("All")) {
                if (rateHeart.contains(rateString.toLowerCase()) && bloodPressure.contains(pressureString.toLowerCase()) && fever.contains(feverString.toLowerCase())) {
                    ChosenModel.add(reportChosen);
                }
            }


        }




        myList = (ListView) view.findViewById(R.id.pendingListView);

        adapter = new ReportAdapter(getActivity(), ChosenModel);

        myList.setAdapter(adapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ReportCell rCell = ChosenModel.get(position);

                JSONObject jsonReport = null;
                try {
                    jsonReport = rCell.getReportObject().getJSONReport();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
//shared pref

                SharedPreferences preference = getActivity().getSharedPreferences("settings", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = preference.edit();
                editor.putString(links.PrefReport, jsonReport.toString());
                editor.commit();

                Intent i = new Intent(getActivity(), ReportDetailActivity.class);
                startActivity(i);


            }
        });
    }

}





