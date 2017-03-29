package com.pifss.doctor;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by PIFSS on 3/29/2017.
 */

public class RequestQueueSingleTon {
    private RequestQueue requestQueue;
    private static final RequestQueueSingleTon ourInstance = new RequestQueueSingleTon();

    public static RequestQueueSingleTon getInstance() {
        return ourInstance;
    }

    private RequestQueueSingleTon() {
    }
    public  RequestQueue getRequestQueue(Context context) {

        if(requestQueue==null)
            requestQueue= Volley.newRequestQueue(context);


        return requestQueue;
    }
}
