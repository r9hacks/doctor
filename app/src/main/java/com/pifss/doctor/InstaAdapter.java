package com.pifss.doctor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.pifss.doctor.Fragments.Pending;
import com.pifss.doctor.Fragments.Replied;

/**
 * Created by khaledhosni on 3/15/17.
 */

public class InstaAdapter extends FragmentPagerAdapter {


    public InstaAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new Pending();
            case 1:
                return new Replied();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
