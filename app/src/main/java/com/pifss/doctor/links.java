package com.pifss.doctor;

/**
 * Created by Entisar on 3/27/17.
 */

public class links {



    //public static String BASE = "http://34.196.107.188:8080/DMHealthBackEnd/webresources/ws.";
    public static String BASE = "http://34.196.107.188:8081/MhealthWeb/webresources/";

    public static String Doctor = BASE + "doctor";
    public static String getDoctor = Doctor + "/getDoctor/";

    public static String Patient = BASE + "patient";
    public static String PatientReport = BASE + "patientreport";

    public static String resetPassword = Doctor + "/reset/";
    public static String login = Doctor + "/login/";


    public static String PendingRequest = Doctor + "/pendingdoctor/";
    public static String MyPatient = Doctor + "/accepteddoctor/";
    public static String GetRepliedReport = PatientReport + "/getReports/";
    public static String ReplyReport = PatientReport +"/UpdatePatientReportDRec/";
    public static String GetPatientReport = PatientReport +"/getPatientReport/";


    public static String PrefDoctorProfile = "doctorProfile";
    public static String PrefReport = "PatientReport";

    public static String PrefPatientProfile = "patientProfile";


}

