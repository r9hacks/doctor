package com.pifss.doctor.Model;


import org.json.JSONException;
import org.json.JSONObject;

public class Report {

    private String coughing;
    private String sugarLevel;
    private Integer drId;
    private String img ="";
    private String drcomment;
    private Boolean pain;
    private String fever;
    private Integer reportId;
    private String dizziness;
    private String timestamp;
    private String painlocation;
    private Integer patientId;
    private String nauseous;
    private String headache;
    private String bloodPressure;
    private String name;
    private String gender ="f";
    private String comments;
    private String heartbeatRate;

    public String getCoughing() {
        return coughing;
    }

    public void setCoughing(String coughing) {
        this.coughing = coughing;
    }

    public Report withCoughing(String coughing) {
        this.coughing = coughing;
        return this;
    }

    public String getSugarLevel() {
        return sugarLevel;
    }

    public void setSugarLevel(String sugarLevel) {
        this.sugarLevel = sugarLevel;
    }

    public Report withSugarLevel(String sugarLevel) {
        this.sugarLevel = sugarLevel;
        return this;
    }

    public Integer getDrId() {
        return drId;
    }

    public void setDrId(Integer drId) {
        this.drId = drId;
    }

    public Report withDrId(Integer drId) {
        this.drId = drId;
        return this;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Report withImg(String img) {
        this.img = img;
        return this;
    }

    public String getDrcomment() {
        return drcomment;
    }

    public void setDrcomment(String drcomment) {
        this.drcomment = drcomment;
    }

    public Report withDrcomment(String drcomment) {
        this.drcomment = drcomment;
        return this;
    }

    public Boolean getPain() {
        return pain;
    }

    public void setPain(Boolean pain) {
        this.pain = pain;
    }

    public Report withPain(Boolean pain) {
        this.pain = pain;
        return this;
    }

    public String getFever() {
        return fever;
    }

    public void setFever(String fever) {
        this.fever = fever;
    }

    public Report withFever(String fever) {
        this.fever = fever;
        return this;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Report withReportId(Integer reportId) {
        this.reportId = reportId;
        return this;
    }

    public String getDizziness() {
        return dizziness;
    }

    public void setDizziness(String dizziness) {
        this.dizziness = dizziness;
    }

    public Report withDizziness(String dizziness) {
        this.dizziness = dizziness;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Report withTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getPainlocation() {
        return painlocation;
    }

    public void setPainlocation(String painlocation) {
        this.painlocation = painlocation;
    }

    public Report withPainlocation(String painlocation) {
        this.painlocation = painlocation;
        return this;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Report withPatientId(Integer patientId) {
        this.patientId = patientId;
        return this;
    }

    public String getNauseous() {
        return nauseous;
    }

    public void setNauseous(String nauseous) {
        this.nauseous = nauseous;
    }

    public Report withNauseous(String nauseous) {
        this.nauseous = nauseous;
        return this;
    }

    public String getHeadache() {
        return headache;
    }

    public void setHeadache(String headache) {
        this.headache = headache;
    }

    public Report withHeadache(String headache) {
        this.headache = headache;
        return this;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public Report withBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Report withName(String name) {
        this.name = name;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Report withGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Report withComments(String comments) {
        this.comments = comments;
        return this;
    }

    public String getHeartbeatRate() {
        return heartbeatRate;
    }

    public void setHeartbeatRate(String heartbeatRate) {
        this.heartbeatRate = heartbeatRate;
    }

    public Report withHeartbeatRate(String heartbeatRate) {
        this.heartbeatRate = heartbeatRate;
        return this;
    }

    public JSONObject getJSONReport() throws JSONException {
        JSONObject jsonReport = new JSONObject();

        jsonReport.put("coughing",getCoughing());
        jsonReport.put("sugarLevel",getSugarLevel());
        jsonReport.put("drId",getDrId());

        jsonReport.put("img",getImg());
        jsonReport.put("drcomment",getDrcomment());
        jsonReport.put("pain",getPain());

        jsonReport.put("fever",getFever());
        jsonReport.put("reportId",getReportId());
        jsonReport.put("dizziness",getDizziness());

        jsonReport.put("timestamp",getTimestamp());
        jsonReport.put("painlocation",getPain());
        jsonReport.put("patientId",getPatientId());

        jsonReport.put("nauseous",getNauseous());
        jsonReport.put("headache",getHeadache());
        jsonReport.put("bloodPressure",getBloodPressure());

        jsonReport.put("name",getName());
        jsonReport.put("gender",getGender());
        jsonReport.put("comments",getComments());

        jsonReport.put("heartbeatRate",getHeartbeatRate());

        return jsonReport;
    }
}