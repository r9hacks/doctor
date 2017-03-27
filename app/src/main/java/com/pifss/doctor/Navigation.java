package com.pifss.doctor;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialize.color.Material;
import com.pifss.doctor.Fragments.AboutUsFragment;
import com.pifss.doctor.Fragments.DoctorProfileFragment;
import com.pifss.doctor.Fragments.MyPatientFragment;
import com.pifss.doctor.Fragments.PatientRequestFragment;
import com.pifss.doctor.Fragments.ReportsFragment;
import com.pifss.doctor.Fragments.SettingsFragment;

public class Navigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);

        toolbar.setTitle("All Reports");
        toolbar.setTitleTextColor(Color.WHITE);

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("View Reports").withIcon(R.mipmap.medical_report_icon).withBadge("10");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Patient Requests").withIcon(R.mipmap.add_icon).withBadge("3");
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("My Patients").withIcon(R.mipmap.my_patient_icon).withBadge("7");
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("Share").withIcon(R.mipmap.share_icon).withSelectable(false);
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName("Settings").withIcon(R.mipmap.settings_icon);
        PrimaryDrawerItem item7 = new PrimaryDrawerItem().withIdentifier(7).withName("About us").withIcon(R.mipmap.aboutus_icon);
        PrimaryDrawerItem item8 = new PrimaryDrawerItem().withIdentifier(8).withName("Logout").withIcon(R.mipmap.logout_icon);
        DividerDrawerItem d = new DividerDrawerItem();


        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.mipmap.material_bacground)
                .addProfiles(
                        new ProfileDrawerItem().withName("Ahmad AlKandari").withEmail("r9hacks@gmail.com").withIcon(R.mipmap.ic_launcher_round)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        Toast.makeText(Navigation.this, "Profile tapped", Toast.LENGTH_SHORT).show();
                        DoctorProfileFragment fragment = new DoctorProfileFragment();

                        FragmentManager fm = getSupportFragmentManager();
                        fm.beginTransaction()
                                .replace(R.id.content_frame, fragment).commit();
                        toolbar.setTitle("My Profile");
                        return false;
                    }
                })
                .build();

        new DrawerBuilder().withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(item1,item2,item3,d,item5,item6,item7,item8)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Toast.makeText(Navigation.this, "position: "+position+" Identifier: "+drawerItem.getIdentifier(), Toast.LENGTH_SHORT).show();

                Intent i = new Intent();
                i.putExtra("empty","");


                if ( ((int) drawerItem.getIdentifier()) == 1) {

                    ReportsFragment fragment = new ReportsFragment();

                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.content_frame, fragment).commit();
                    toolbar.setTitle("All Reports");
                }else if ( ((int) drawerItem.getIdentifier()) == 2) {
                    PatientRequestFragment fragment = new PatientRequestFragment();

                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.content_frame, fragment).commit();
                    toolbar.setTitle("Patient Request");
                }else if ( ((int) drawerItem.getIdentifier()) == 3) {
                    MyPatientFragment fragment = new MyPatientFragment();

                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.content_frame, fragment).commit();
                    toolbar.setTitle("My Patient");
                }else if ( ((int) drawerItem.getIdentifier()) == 6) {
                    SettingsFragment fragment = new SettingsFragment();

                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.content_frame, fragment).commit();
                    toolbar.setTitle("Settings");
                }else if ( ((int) drawerItem.getIdentifier()) == 7) {
                    AboutUsFragment fragment = new AboutUsFragment();

                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.content_frame, fragment).commit();
                    toolbar.setTitle("About Us");
                }


                return false;
            }
        })
                .withAccountHeader(headerResult)
                .build();

        ReportsFragment fragment = new ReportsFragment();

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.content_frame, fragment).commit();
        toolbar.setTitle("All Reports");


    }

}
