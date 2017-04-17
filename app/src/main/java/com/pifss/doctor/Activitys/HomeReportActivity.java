package com.pifss.doctor.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.pifss.doctor.Adapters.ReportFragmentAdapter;
import com.pifss.doctor.Model.Doctor;
import com.pifss.doctor.R;
import com.pifss.doctor.links;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class HomeReportActivity extends AppCompatActivity {
    AccountHeader headerResult;
    DrawerBuilder drab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);

        toolbar.setTitle(R.string.AllReports);
        toolbar.setTitleTextColor(Color.WHITE);

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.ViewReports).withIcon(R.mipmap.viewreports);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.PatientRequests).withIcon(R.mipmap.add_patient);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName(R.string.MyPatients).withIcon(R.mipmap.my_patient_icon);
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName(R.string.Share).withIcon(R.mipmap.share_icon).withSelectable(false);
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName(R.string.Settings).withIcon(R.mipmap.settings_icon);
        PrimaryDrawerItem item7 = new PrimaryDrawerItem().withIdentifier(7).withName(R.string.AboutusAct).withIcon(R.mipmap.aboutus_icon);
        PrimaryDrawerItem item8 = new PrimaryDrawerItem().withIdentifier(8).withName(R.string.Logout).withIcon(R.mipmap.logout_icon);
        DividerDrawerItem d = new DividerDrawerItem();

        PrimaryDrawerItem item9 = new PrimaryDrawerItem().withIdentifier(9).withName(R.string.BloodRequests).withIcon(R.mipmap.blood_donation);


        SharedPreferences preference = getSharedPreferences("settings",MODE_PRIVATE);
        String doctorProfile = preference.getString(links.PrefDoctorProfile,"notfound");
        Doctor doctor = new Gson().fromJson(doctorProfile,Doctor.class);



        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.mipmap.material_bacground_a)
                .addProfiles(
                        new ProfileDrawerItem().withName(doctor.getFirstName()+" "+doctor.getMiddleName()+" "+doctor.getLastName()).withEmail(doctor.getEmail())
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                      //  Toast.makeText(HomeReportActivity.this, "Profile tapped", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(HomeReportActivity.this,DoctorProfileActivity.class);
                        startActivity(i);

                        return false;
                    }
                })
                .build();

       // headerResult.getActiveProfile().
       // Toast.makeText(this, "doctor.getImageUrl()"+doctor.getImageUrl(), Toast.LENGTH_SHORT).show();

        drab = new DrawerBuilder();
        drab.withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(item1,item2,item3,item9,d,item5,item6,item7,item8)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //Toast.makeText(HomeReportActivity.this, "position: "+position+" Identifier: "+drawerItem.getIdentifier(), Toast.LENGTH_SHORT).show();

                        Intent i;
                        switch (position){
//                    case 1:
//                        i = new Intent(HomeReportActivity.this,HomeReportActivity.class);
//                        startActivity(i);
//                        break;
                            case 2:
                                i = new Intent(HomeReportActivity.this,PatientRequestActivity.class);
                                startActivity(i);
                                break;
                            case 3:
                                i = new Intent(HomeReportActivity.this,MyPatientActivity.class);
                                startActivity(i);
                                break;
                            case 4:
                                i = new Intent(HomeReportActivity.this,MyBloodRequestsActivity.class);
                                startActivity(i);
                                break;
                            case 6:

                                try {
                                     i = new Intent(Intent.ACTION_SEND);
                                    i.setType("text/plain");
                                    i.putExtra(Intent.EXTRA_SUBJECT, "MHealth");
                                    String sAux = "\n"+R.string.Recommend+"\n\n";
                                    sAux = sAux + "here i put the link of the application in google play \n\n";
                                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                                    startActivity(Intent.createChooser(i, "choose one"));
                                } catch(Exception e) {
                                    //e.toString();
                                }
                                break;
                            case 7:
                                i = new Intent(HomeReportActivity.this,SettingsActivity.class);
                                startActivity(i);
                                break;
                            case 8:
                                i = new Intent(HomeReportActivity.this,AboutUsActivity.class);
                                startActivity(i);
                                break;
                            case 9:
                                i = new Intent(HomeReportActivity.this,loginActivity.class);

                                SharedPreferences preference = getSharedPreferences("settings", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preference.edit();
                                editor.remove(links.PrefDoctorProfile);
                                editor.commit();

                                startActivity(i);
                                finish();
                                break;

                        }

                        return false;
                    }
                })
                .withAccountHeader(headerResult)
                .build();

        final ViewPager vp= (ViewPager) findViewById(R.id.myViewPager);
        final TabLayout tabLayout= (TabLayout) findViewById(R.id.mytabLayout);

        ReportFragmentAdapter adapter=new ReportFragmentAdapter(getSupportFragmentManager());

        vp.setAdapter(adapter);

        tabLayout.setupWithViewPager(vp);

        tabLayout.getTabAt(0).setText(R.string.Pending);
        //tabLayout.getTabAt(0).setIcon(R.mipmap.ic_launcher_round);
        tabLayout.getTabAt(1).setText(R.string.Replied);


    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences preference = getSharedPreferences("settings",MODE_PRIVATE);
        String doctorProfile = preference.getString(links.PrefDoctorProfile,"notfound");
        final Doctor doctor = new Gson().fromJson(doctorProfile,Doctor.class);

        Picasso.with(HomeReportActivity.this).load(doctor.getImageUrl()).placeholder(R.mipmap.profile_image).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                //Toast.makeText(HomeReportActivity.this, "Load image", Toast.LENGTH_SHORT).show();
                headerResult.getActiveProfile().withIcon(bitmap);
                System.out.println("load image profile");

                PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.ViewReports).withIcon(R.mipmap.viewreports);
                PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.PatientRequests).withIcon(R.mipmap.add_patient);
                PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName(R.string.MyPatients).withIcon(R.mipmap.my_patient_icon);
                PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName(R.string.Share).withIcon(R.mipmap.share_icon).withSelectable(false);
                PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName(R.string.Settings).withIcon(R.mipmap.settings_icon);
                PrimaryDrawerItem item7 = new PrimaryDrawerItem().withIdentifier(7).withName(R.string.AboutusAct).withIcon(R.mipmap.aboutus_icon);
                PrimaryDrawerItem item8 = new PrimaryDrawerItem().withIdentifier(8).withName(R.string.Logout).withIcon(R.mipmap.logout_icon);
                DividerDrawerItem d = new DividerDrawerItem();
                PrimaryDrawerItem item9 = new PrimaryDrawerItem().withIdentifier(9).withName(R.string.BloodRequests).withIcon(R.mipmap.blood_donation);

                headerResult = new AccountHeaderBuilder()
                        .withActivity(HomeReportActivity.this)
                        .withHeaderBackground(R.mipmap.material_bacground_a)
                        .addProfiles(
                                new ProfileDrawerItem().withName(doctor.getFirstName()+" "+doctor.getMiddleName()+" "+doctor.getLastName()).withEmail(doctor.getEmail()).withIcon(bitmap)
                        )
                        .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                            @Override
                            public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                             //   Toast.makeText(HomeReportActivity.this, "Profile tapped", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(HomeReportActivity.this,DoctorProfileActivity.class);
                                startActivity(i);

                                return false;
                            }
                        })
                        .build();

                // headerResult.getActiveProfile().
                final Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);

                drab = new DrawerBuilder();
                drab.withActivity(HomeReportActivity.this)
                        .withToolbar(toolbar)
                        .addDrawerItems(item1,item2,item3,item9,d,item5,item6,item7,item8)
                        .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                            @Override
                            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                //Toast.makeText(HomeReportActivity.this, "position: "+position+" Identifier: "+drawerItem.getIdentifier(), Toast.LENGTH_SHORT).show();

                                Intent i;
                                switch (position){
//                    case 1:
//                        i = new Intent(HomeReportActivity.this,HomeReportActivity.class);
//                        startActivity(i);
//                        break;
                                    case 2:
                                        i = new Intent(HomeReportActivity.this,PatientRequestActivity.class);
                                        startActivity(i);
                                        break;
                                    case 3:
                                        i = new Intent(HomeReportActivity.this,MyPatientActivity.class);
                                        startActivity(i);
                                        break;
                                    case 4:
                                        i = new Intent(HomeReportActivity.this,MyBloodRequestsActivity.class);
                                        startActivity(i);
                                        break;
                                    case 6:

                                        try {
                                            i = new Intent(Intent.ACTION_SEND);
                                            i.setType("text/plain");
                                            i.putExtra(Intent.EXTRA_SUBJECT, "MHealth");
                                            String sAux = "\n"+R.string.Recommend+"\n\n";
                                            sAux = sAux + "here i put the link of the application in google play \n\n";
                                            i.putExtra(Intent.EXTRA_TEXT, sAux);
                                            startActivity(Intent.createChooser(i,"choose one"));
                                        } catch(Exception e) {
                                            //e.toString();
                                        }
                                        break;
                                    case 7:
                                        i = new Intent(HomeReportActivity.this,SettingsActivity.class);
                                        startActivity(i);
                                        break;
                                    case 8:
                                        i = new Intent(HomeReportActivity.this,AboutUsActivity.class);
                                        startActivity(i);
                                        break;
                                    case 9:
                                        i = new Intent(HomeReportActivity.this,loginActivity.class);

                                        SharedPreferences preference = getSharedPreferences("settings", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = preference.edit();
                                        editor.remove(links.PrefDoctorProfile);
                                        editor.commit();

                                        startActivity(i);
                                        finish();
                                        break;

                                }

                                return false;
                            }
                        })
                        .withAccountHeader(headerResult)
                        .build();

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

               // Toast.makeText(HomeReportActivity.this, "onBitmapFailed", Toast.LENGTH_SHORT).show();
                System.out.println("onBitmapFailed");
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
               // Toast.makeText(HomeReportActivity.this, "onPrepareLoad", Toast.LENGTH_SHORT).show();
                System.out.println("onPrepareLoad");
            }
        });
    }
}
