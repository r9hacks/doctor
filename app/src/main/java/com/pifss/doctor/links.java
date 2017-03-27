package com.pifss.doctor;

/**
 * Created by Entisar on 3/27/17.
 */

public class links {


    static String BASE = "http://34.196.107.188:8080/DMHealthBackEnd/webresources/ws.";
    static String Doctor = BASE + "doctor";
    static String getDoctor = Doctor + "/getDoctor/";

    static String Patient = BASE + "patient";
    static String PatientReport = BASE + "patientreport";

    static String resetPassword = Doctor + "/reset/";
    static String login = Doctor + "/login/";

    static String PatientDrLink = BASE + "patientdrlink";
    static String getLinkPatient = PatientDrLink + "/getLinkedPatient/";




}

