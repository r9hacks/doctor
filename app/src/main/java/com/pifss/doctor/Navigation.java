package com.pifss.doctor;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialize.color.Material;

public class Navigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);

        toolbar.setTitle("Reports");
        toolbar.setTitleTextColor(Color.WHITE);

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("View Reports").withIcon(R.mipmap.medical_report_icon).withBadge("10");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(1).withName("Patient Requests").withIcon(R.mipmap.add_icon).withBadge("3");
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(1).withName("My Patients").withIcon(R.mipmap.my_patient_icon).withBadge("7");
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(1).withName("My Profile").withIcon(R.mipmap.doctor_profile_icon);
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(1).withName("Share").withIcon(R.mipmap.share_icon);
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(1).withName("Settings").withIcon(R.mipmap.settings_icon);
        PrimaryDrawerItem item7 = new PrimaryDrawerItem().withIdentifier(1).withName("About us").withIcon(R.mipmap.aboutus_icon);
        PrimaryDrawerItem item8 = new PrimaryDrawerItem().withIdentifier(1).withName("Logout").withIcon(R.mipmap.logout_icon);
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
                        return false;
                    }
                })
                .build();

        new DrawerBuilder().withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(item1,item2,item3,item4,d,item5,item6,item7,item8)
                .withAccountHeader(headerResult)
                .build();

        ViewPager vp= (ViewPager) findViewById(R.id.myViewPager);

        InstaAdapter adapter=new InstaAdapter(getSupportFragmentManager());

        vp.setAdapter(adapter);

        TabLayout tabLayout= (TabLayout) findViewById(R.id.mytabLayout);
        tabLayout.setupWithViewPager(vp);

        tabLayout.getTabAt(0).setText("Pending");
        //tabLayout.getTabAt(0).setIcon(R.mipmap.ic_launcher_round);
        tabLayout.getTabAt(1).setText("Replied");


    }
}
