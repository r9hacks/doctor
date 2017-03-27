package com.pifss.doctor.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.pifss.doctor.myPatientAdapter;
import com.pifss.doctor.MyPatient;
import com.pifss.doctor.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPatientFragment extends Fragment {


    public MyPatientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_patient, container, false);


        final ArrayList<MyPatient> model=new ArrayList<>();


        model.add(new MyPatient("John Smith",22,"1234","female"));
        model.add(new MyPatient("Mshmsh Soso",23,"14321","female"));
        model.add(new MyPatient("John Smith",22,"1234","female"));
        model.add(new MyPatient("Mshmsh Soso",23,"14321","female"));
        model.add(new MyPatient("John Smith",22,"1234","female"));
        model.add(new MyPatient("Mshmsh Soso",23,"14321","female"));


        myPatientAdapter adapter=new myPatientAdapter(getActivity(),model);


        ListView lv= (ListView) view.findViewById(R.id.myPatientListView);

        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MyPatient patient = model.get(position);

                Toast.makeText(getActivity(), patient.getName(), Toast.LENGTH_SHORT).show();



            }
        });


        return view;
    }

}
