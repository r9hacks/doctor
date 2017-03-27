package com.pifss.doctor;

import android.media.Image;

/**
 * Created by Entisar on 3/27/17.
 */

public class MyPatient {


    public MyPatient(String name, int age, String phoneNum, String gender) {
        this.name = name;
        this.age = age;
        this.phoneNum = phoneNum;
        this.gender = gender;

    }



    private String name;
    private int age;
    private String phoneNum;
    private String gender;
   // private String image;

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



}
