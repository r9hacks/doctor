package com.pifss.doctor;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by trn24 on 4/12/17.
 */

public class Connection {
    ConnectivityManager cm;

    public Connection(ConnectivityManager cm) {
        this.cm = cm;
    }

    public void ShowSnackBar (View v){

        if (!haveNetworkConnection()){

            Snackbar snackbarConnection = Snackbar.make(v,"No Internet Connection", Snackbar.LENGTH_LONG);
            snackbarConnection.show();

        }

    }
    public boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;


        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {

            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if(ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if(ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;

    }
}
