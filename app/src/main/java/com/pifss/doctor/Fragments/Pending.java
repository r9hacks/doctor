package com.pifss.doctor.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pifss.doctor.Activitys.ReportDetailActivity;
import com.pifss.doctor.Adapters.ReportAdapter;
import com.pifss.doctor.Model.ReportCell;
import com.pifss.doctor.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Pending extends Fragment {


    public Pending() {
        // Required empty public constructor
    }

    ArrayList<ReportCell> model = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending, container, false);

        fillModel();

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
