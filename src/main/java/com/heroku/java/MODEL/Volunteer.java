package com.heroku.java.MODEL;

import java.sql.Date;

public class Volunteer {
    public int id;
    public String name;
    public String email;
    public int phonenum;

    // public String formattedPhonenum; // Store formatted phone number

    public String icnum;
    public Date birthdate;
    public int age;
    public String username;
    public String password;

    public boolean isRegistered;

    // public int vpid;

    
    
    public Volunteer() {
    }

    

    public Volunteer(int id, String name, String email, int phonenum, String icnum, Date vbirthdate, int age, String username,
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

    public int getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(int phonenum) {
        this.phonenum = phonenum;
    }

    public String getFormattedPhonenum() {
        return "0" + phonenum; // Add a leading zero to the phone number
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

    // public Date getVolunteerbirthdate() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getVolunteerbirthdate'");
    // }

    public String getVolunteerusername() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVolunteerusername'");
    }

    
}


