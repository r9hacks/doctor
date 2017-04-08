package com.pifss.doctor.Model;

/**
 * Created by PIFSS on 3/27/2017.
 */

public class ReportCell {

    private String name;
    private String date;
    private String comment;
    private String imageURL;
    private String gender;
    private String heartRate;
    private String bloodPreassure;
    private String fever;
    private Report reportObject;


    public ReportCell(String name, String date, String comment, String imageURL, String gender, String heartRate,String fever, String bloodPreassure,Report reportObject) {
        this.name = name;
        this.date = date;
        this.comment = comment;
        this.imageURL = imageURL;
        this.gender = gender;
        this.heartRate = heartRate;
        this.bloodPreassure = bloodPreassure;
        this.fever =fever;
        this.reportObject = reportObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Report getReportObject() {
        return reportObject;
    }

    public void setReportObject(Report reportObject) {
        this.reportObject = reportObject;
    }
}
