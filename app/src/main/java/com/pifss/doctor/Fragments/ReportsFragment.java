package com.pifss.doctor.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pifss.doctor.InstaAdapter;
import com.pifss.doctor.Navigation;
import com.pifss.doctor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportsFragment extends Fragment {


    public ReportsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reports, container, false);

        final ViewPager vp= (ViewPager) view.findViewById(R.id.myViewPager);
        final TabLayout tabLayout= (TabLayout) view.findViewById(R.id.mytabLayout);

        InstaAdapter adapter=new InstaAdapter(getFragmentManager());

        vp.setAdapter(adapter);

        tabLayout.setupWithViewPager(vp);

        tabLayout.getTabAt(0).setText("Pending");
        //tabLayout.getTabAt(0).setIcon(R.mipmap.ic_launcher_round);
        tabLayout.getTabAt(1).setText("Replied");


        return view;

    }

}
