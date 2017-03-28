package com.pifss.doctor.Model;

import java.util.Date;

/**
 * Created by Entisar on 3/28/17.
 */

public class ReportList {



    private String comment;
    private String date;
    private String heartRate;
    private String bloodPreassure;
    private String fever;

    public ReportList(String comment, String date, String heartRate, String bloodPreassure, String fever) {
        this.comment = comment;
        this.date = date;
        this.heartRate = heartRate;
        this.bloodPreassure = bloodPreassure;
        this.fever = fever;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getBloodPreassure() {
        return bloodPreassure;
    }

    public void setBloodPreassure(String bloodPreassure) {
        this.bloodPreassure = bloodPreassure;
    }

    public String getFever() {
        return fever;
    }

    public void setFever(String fever) {
        this.fever = fever;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
