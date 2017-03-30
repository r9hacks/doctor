package com.pifss.doctor;

/**
 * Created by Entisar on 3/27/17.
 */

public class links {



    public static String BASE = "http://34.196.107.188:8080/DMHealthBackEnd/webresources/ws.";
    public static String Doctor = BASE + "doctor";
    public static String getDoctor = Doctor + "/getDoctor/";

    public static String Patient = BASE + "patient";
    public static String PatientReport = BASE + "patientreport";

    public static String resetPassword = Doctor + "/reset/";
    public static String login = Doctor + "/login/";

    public static String PatientDrLink = BASE + "patientdrlink";
    public static String getLinkPatient = PatientDrLink + "/getLinkedPatient/";

    public static String PendingRequest = "http://34.196.107.188:8081/MhealthWeb/webresources/doctor/pendingdoctor/";
    public static String MyPatient = "http://34.196.107.188:8081/MhealthWeb/webresources/doctor/accepteddoctor/";
public static String GetRepliedReport = "http://34.196.107.188:8081/MhealthWeb/webresources/patientreport/getPatientReport/";

    public static String PrefDoctorProfile = "doctorProfile";


}

