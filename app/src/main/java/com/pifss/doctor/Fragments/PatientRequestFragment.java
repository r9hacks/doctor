package com.pifss.doctor.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pifss.doctor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatientRequestFragment extends Fragment {


    public PatientRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_patient_request, container, false);






        return view;
    }

}
