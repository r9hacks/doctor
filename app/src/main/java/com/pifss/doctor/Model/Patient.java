package com.pifss.doctor.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class Patient {

    @SerializedName("middleName")
    @Expose
    private String middleName;
    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("emergencyNumber")
    @Expose
    private String emergencyNumber;
    @SerializedName("medications")
    @Expose
    private String medications;
    @SerializedName("drId")
    @Expose
    private Integer drId;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("addingTime")
    @Expose
    private String addingTime;
    @SerializedName("diabetes")
    @Expose
    private Boolean diabetes;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("deleted")
    @Expose
    private Integer deleted;
    @SerializedName("civilId")
    @Expose
    private String civilId;
    @SerializedName("patientId")
    @Expose
    private Integer patientId;
    @SerializedName("bloodType")
    @Expose
    private String bloodType;
    @SerializedName("linkId")
    @Expose
    private Integer linkId;
    @SerializedName("nationality")
    @Expose
    private String nationality;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("asthma")
    @Expose
    private Boolean asthma;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("allergies")
    @Expose
    private String allergies;

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Patient withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Patient withDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Patient withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setEmergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }

    public Patient withEmergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
        return this;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public Patient withMedications(String medications) {
        this.medications = medications;
        return this;
    }

    public Integer getDrId() {
        return drId;
    }

    public void setDrId(Integer drId) {
        this.drId = drId;
    }

    public Patient withDrId(Integer drId) {
        this.drId = drId;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Patient withStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Patient withImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getAddingTime() {
        return addingTime;
    }

    public void setAddingTime(String addingTime) {
        this.addingTime = addingTime;
    }

    public Patient withAddingTime(String addingTime) {
        this.addingTime = addingTime;
        return this;
    }

    public Boolean getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(Boolean diabetes) {
        this.diabetes = diabetes;
    }

    public Patient withDiabetes(Boolean diabetes) {
        this.diabetes = diabetes;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Patient withPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Patient withDeleted(Integer deleted) {
        this.deleted = deleted;
        return this;
    }

    public String getCivilId() {
        return civilId;
    }

    public void setCivilId(String civilId) {
        this.civilId = civilId;
    }

    public Patient withCivilId(String civilId) {
        this.civilId = civilId;
        return this;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Patient withPatientId(Integer patientId) {
        this.patientId = patientId;
        return this;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Patient withBloodType(String bloodType) {
        this.bloodType = bloodType;
        return this;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public Patient withLinkId(Integer linkId) {
        this.linkId = linkId;
        return this;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Patient withNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Patient withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Patient withEmail(String email) {
        this.email = email;
        return this;
    }

    public Boolean getAsthma() {
        return asthma;
    }

    public void setAsthma(Boolean asthma) {
        this.asthma = asthma;
    }

    public Patient withAsthma(Boolean asthma) {
        this.asthma = asthma;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Patient withGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Patient withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public Patient withAllergies(String allergies) {
        this.allergies = allergies;
        return this;
    }


    public JSONObject getJSONPatient() throws JSONException {
        JSONObject jsonPatient = new JSONObject();

        jsonPatient.put("firstName",getFirstName());
        jsonPatient.put("middleName",getMiddleName());
        jsonPatient.put("lastName",getLastName());


        jsonPatient.put("civilId",getCivilId());
        jsonPatient.put("deleted",getDeleted());

        jsonPatient.put("patientId",getPatientId());
        jsonPatient.put("email",getEmail());

        jsonPatient.put("gender",getGender());
        jsonPatient.put("imageUrl",getImageUrl());
        jsonPatient.put("dateOfBirth",getDateOfBirth());


        jsonPatient.put("nationality",getNationality());
        jsonPatient.put("password",getPassword());
        jsonPatient.put("phoneNumber",getPhoneNumber());


        jsonPatient.put("status",getStatus());

        jsonPatient.put("allergies",getAllergies());
        jsonPatient.put("asthma",getAsthma());
        jsonPatient.put("bloodType",getBloodType());
        jsonPatient.put("diabetes",getDiabetes());
        jsonPatient.put("medications",getMedications());
        jsonPatient.put("emergencyNumber",getEmergencyNumber());

        jsonPatient.put("addingTime",getAddingTime());
        jsonPatient.put("linkId",getLinkId());
        jsonPatient.put("drId",getDrId());


        return jsonPatient;
    }





}
