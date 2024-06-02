package com.heroku.java.MODEL;

import java.sql.Date;

public class Volunteer {
    public int id;
    public String name;
    public String email;
    public int phonenum;
    public String icnum;
    public Date birthdate;
    public int age;
    public String username;
    public String password;
    public int vpid;

    // public int programId; // fk 

    
    public Volunteer() {
    }

    

    public Volunteer(int id, String name, String email, int phonenum, String icnum, Date vbirthdate, int age, String username,
            String password, int vpid) {
                
        this.id=id;        
        this.name = name;
        this.email = email;
        this.phonenum = phonenum;
        this.icnum = icnum;
        this.birthdate = vbirthdate;
        this.age = age;
        this.username = username;
        this.password = password;
        this.vpid = vpid;

        
        
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

    public int getVpid() {
        return vpid;
    }



    public void setVpid(int vpid) {
        this.vpid = vpid;
    }

    


    // fk program

    // public int getProgramId() {
    //     return programId;
    // }



    // public void setProgramId(int programId) {
    //     this.programId = programId;
    // }



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



    // public int getVolunteerphonenum() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getVolunteerphonenum'");
    // }



    // public String getVolunteericnum() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getVolunteericnum'");
    // }



    public Date getVolunteerbirthdate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVolunteerbirthdate'");
    }



    public String getVolunteerusername() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVolunteerusername'");
    }



   

   

    

    


    

    
}


