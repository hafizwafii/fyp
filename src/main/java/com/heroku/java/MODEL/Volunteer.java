package com.heroku.java.MODEL;

import java.sql.Date;

public class Volunteer {
    private String name;
    private String email;
    private int phonenum;
    private String icnum;
    private Date birthdate;
    private int age;
    private String username;
    private String password;

    
    public Volunteer() {
    }

    public Volunteer(String name, String email, int phonenum, String icnum, Date birthdate, int age, String username,
            String password) {
        this.name = name;
        this.email = email;
        this.phonenum = phonenum;
        this.icnum = icnum;
        this.birthdate = birthdate;
        this.age = age;
        this.username = username;
        this.password = password;
        
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

    

    


    

    
}


