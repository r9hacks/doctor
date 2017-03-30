package com.pifss.doctor.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class Doctor {

    @SerializedName("BDay")
    @Expose
    private Integer bDay;
    @SerializedName("BMonth")
    @Expose
    private Integer bMonth;
    @SerializedName("BYear")
    @Expose
    private Integer bYear;
    @SerializedName("civilId")
    @Expose
    private String civilId;
    @SerializedName("cvUrl")
    @Expose
    private String cvUrl;
    @SerializedName("deleted")
    @Expose
    private Integer deleted;
    @SerializedName("drId")
    @Expose
    private Integer drId;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("extraInfo")
    @Expose
    private String extraInfo;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("middleName")
    @Expose
    private String middleName;
    @SerializedName("nationality")
    @Expose
    private String nationality;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("specialityId")
    @Expose
    private String specialityId;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public Integer getBDay() {
        return bDay;
    }

    public void setBDay(Integer bDay) {
        this.bDay = bDay;
    }

    public Doctor withBDay(Integer bDay) {
        this.bDay = bDay;
        return this;
    }

    public Integer getBMonth() {
        return bMonth;
    }

    public void setBMonth(Integer bMonth) {
        this.bMonth = bMonth;
    }

    public Doctor withBMonth(Integer bMonth) {
        this.bMonth = bMonth;
        return this;
    }

    public Integer getBYear() {
        return bYear;
    }

    public void setBYear(Integer bYear) {
        this.bYear = bYear;
    }

    public Doctor withBYear(Integer bYear) {
        this.bYear = bYear;
        return this;
    }

    public String getCivilId() {
        return civilId;
    }

    public void setCivilId(String civilId) {
        this.civilId = civilId;
    }

    public Doctor withCivilId(String civilId) {
        this.civilId = civilId;
        return this;
    }

    public String getCvUrl() {
        return cvUrl;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
    }

    public Doctor withCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
        return this;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Doctor withDeleted(Integer deleted) {
        this.deleted = deleted;
        return this;
    }

    public Integer getDrId() {
        return drId;
    }

    public void setDrId(Integer drId) {
        this.drId = drId;
    }

    public Doctor withDrId(Integer drId) {
        this.drId = drId;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Doctor withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public Doctor withExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Doctor withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Doctor withGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Doctor withImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Doctor withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Doctor withLocation(String location) {
        this.location = location;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Doctor withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Doctor withNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Doctor withPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Doctor withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(String specialityId) {
        this.specialityId = specialityId;
    }

    public Doctor withSpecialityId(String specialityId) {
        this.specialityId = specialityId;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Doctor withStatus(Boolean status) {
        this.status = status;
        return this;
    }
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
