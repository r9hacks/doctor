package com.pifss.doctor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pifss.doctor.ReportCell;
import com.pifss.doctor.R;
import com.pifss.doctor.ReportAdapter;

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