
package com.pifss.doctor.Model;


import org.json.JSONException;
import org.json.JSONObject;

public class Patient {

    private String firstName;
    private String middleName;
    private String lastName;

    private String gender;
    private String civilId;
    private String dateOfBirth;
    private String nationality;
    private String phoneNumber;
    private String emergencyNumber;


    private String bloodType;
    private String allergies;
    private Boolean asthma;
    private Boolean diabetes;

    private String imageUrl;

    private String email;
    private String password;
    private Integer deleted;
    private Integer patientId;
    private Boolean status;

    private String medications;








    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public Boolean getAsthma() {
        return asthma;
    }

    public void setAsthma(Boolean asthma) {
        this.asthma = asthma;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getCivilId() {
        return civilId;
    }

    public void setCivilId(String civilId) {
        this.civilId = civilId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Boolean getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(Boolean diabetes) {
        this.diabetes = diabetes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setEmergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
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


        return jsonPatient;
    }





}
