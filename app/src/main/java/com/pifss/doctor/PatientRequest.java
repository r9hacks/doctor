package com.pifss.doctor;

/**
 * Created by Entisar on 3/27/17.
 */

public class PatientRequest {

    public PatientRequest(String name, int age, String civilId, String gender) {
        this.name = name;
        this.age = age;
        this.civilId = civilId;
        this.gender = gender;
    }

    private String name;
    private int age;
    private String civilId;
    private String gender;

    // String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCivilId() {
        return civilId;
    }

    public void setCivilId(String civilId) {
        this.civilId = civilId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



}
