package com.pifss.doctor.Model;

/**
 * Created by Entisar on 3/27/17.
 */

public class MyPatient {


    public MyPatient(String name, String age, String phoneNum, String gender, String image) {
        this.name = name;
        this.age = age;
        this.phoneNum = phoneNum;
        this.gender = gender;
        this.image = image;

    }



    private String name;
    private String age;
    private String phoneNum;
    private String gender;
    private String image;
    private Patient patient;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
