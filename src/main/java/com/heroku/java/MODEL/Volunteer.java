package com.heroku.java.MODEL;

import java.sql.Date;

public class Volunteer {
    public int id;
    public String name;
    public String email;
    public String phonenum;

    public String icnum;
    public Date birthdate;
    public int age;
    public String username;
    public String password;

    public boolean isRegistered;
    
    public Volunteer() {
    }

    

    public Volunteer(int id, String name, String email, String phonenum, String icnum, Date vbirthdate, int age, String username,
            String password) {
                
        this.id=id;        
        this.name = name;
        this.email = email;
        this.phonenum = phonenum;
        this.icnum = icnum;
        this.birthdate = vbirthdate;
        this.age = age;
        this.username = username;
        this.password = password;
        // this.formattedPhonenum= formattedPhonenum;
        // this.vpid = vpid;

        
        
    }



    public Volunteer(int id, String name, String username, String password) {
        this.id=id;
        this.name=name;
        this.username=username;
        this.password=password;
    }



    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getIcnum() {
        return icnum;
    }

    public void setIcnum(String icnum) {
        this.icnum = icnum;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    
}


