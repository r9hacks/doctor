
package com.pifss.doctor.Model;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class Doctor {

    private Integer bDay;
    private Integer bMonth;
    private Integer bYear;
    private String civilId;
    private String cvUrl;
    private Integer deleted;
    private Integer drId;
    private String email;
    private String extraInfo;
    private String firstName;
    private String gender;
    private String imageUrl;
    private String lastName;
    private String location;
    private String middleName;
    private String nationality;
    private String password;
    private String phoneNumber;
    private String specialityId;
    private Boolean status;

    public Integer getBDay() {
        return bDay;
    }

    public void setBDay(Integer bDay) {
        this.bDay = bDay;
    }

    public Integer getBMonth() {
        return bMonth;
    }

    public void setBMonth(Integer bMonth) {
        this.bMonth = bMonth;
    }

    public Integer getBYear() {
        return bYear;
    }

    public void setBYear(Integer bYear) {
        this.bYear = bYear;
    }

    public String getCivilId() {
        return civilId;
    }

    public void setCivilId(String civilId) {
        this.civilId = civilId;
    }

    public String getCvUrl() {
        return cvUrl;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getDrId() {
        return drId;
    }

    public void setDrId(Integer drId) {
        this.drId = drId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(String specialityId) {
        this.specialityId = specialityId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Doctor(){}

    public Doctor(String firstName, String email, String password, String civilId){
        this.setFirstName(firstName);
        this.setEmail(email);
        this.setPassword(password);
        this.setCivilId(civilId);

        Calendar calender = Calendar.getInstance();
        this.setBDay(calender.get(Calendar.DAY_OF_MONTH));
        this.setBMonth(calender.get(Calendar.MONTH));
        this.setBYear(calender.get(Calendar.YEAR));
        this.setCvUrl("");
        this.setDeleted(0);
        this.setExtraInfo("");
        this.setGender("Male");
        this.setStatus(true);
        this.setSpecialityId("");
        this.setPhoneNumber("");
        this.setNationality("");
        this.setLocation("");
        this.setMiddleName("");
        this.setLastName("");
        this.setImageUrl("");
    }
    public JSONObject getJSONDoctor() throws JSONException {
        JSONObject jsonDoctor = new JSONObject();

        jsonDoctor.put("firstName",getFirstName());
        jsonDoctor.put("middleName",getMiddleName());
        jsonDoctor.put("lastName",getLastName());

        jsonDoctor.put("BDay",getBDay());
        jsonDoctor.put("BMonth",getBMonth());
        jsonDoctor.put("BYear",getBYear());

        jsonDoctor.put("civilId",getCivilId());
        jsonDoctor.put("cvUrl",getCvUrl());
        jsonDoctor.put("deleted",getDeleted());

        jsonDoctor.put("drId",getDrId());
        jsonDoctor.put("email",getEmail());
        jsonDoctor.put("extraInfo",getExtraInfo());

        jsonDoctor.put("gender",getGender());
        jsonDoctor.put("imageUrl",getImageUrl());
        jsonDoctor.put("location",getLocation());

        jsonDoctor.put("nationality",getNationality());
        jsonDoctor.put("password",getPassword());
        jsonDoctor.put("phoneNumber",getPhoneNumber());

        jsonDoctor.put("specialityId",getSpecialityId());
        jsonDoctor.put("status",getStatus());

        return jsonDoctor;
    }
}
