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
public class MyPatientFragment extends Fragment {


    public MyPatientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_patient, container, false);
    }

}
