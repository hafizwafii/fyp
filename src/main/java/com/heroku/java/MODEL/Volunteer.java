package com.heroku.java.MODEL;

import java.sql.Date;

public class Volunteer {
    private int id;
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

    

    public Volunteer(int id, String name, String email, int phonenum, String icnum, Date birthdate, int age, String username,
            String password) {
        this.id=id;        
        this.name = name;
        this.email = email;
        this.phonenum = phonenum;
        this.icnum = icnum;
        this.birthdate = birthdate;
        this.age = age;
        this.username = username;
        this.password = password;
        
    }



    public Volunteer(int id, String name, String username, String password) {
        //TODO Auto-generated constructor stub
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



    // public String getVolunteerpassword() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getVolunteerpassword'");
    // }



    // public String getVolunteername() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getVolunteername'");
    // }



    // public String getVolunteeremail() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getVolunteeremail'");
    // }



    public int getVolunteerphonenum() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVolunteerphonenum'");
    }



    public String getVolunteericnum() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVolunteericnum'");
    }



    public Date getVolunteerbirthdate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVolunteerbirthdate'");
    }



    public String getVolunteerusername() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVolunteerusername'");
    }

   

    

    


    

    
}


