package com.pifss.doctor.Model;

/**
 * Created by Entisar on 3/27/17.
 */

public class PatientRequest {



    private String name;
    private String age;
    private String civilId;

    public PatientRequest(String name, String age, String civilId, String gender, String image) {
        this.name = name;
        this.age = age;
        this.civilId = civilId;
        this.gender = gender;
        this.image = image;
    }

    private String gender;
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
