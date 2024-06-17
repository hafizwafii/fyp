package com.heroku.java.MODEL;

import java.sql.Date;

public class Registration {
    public int rid;
    public Date rdate;
    public int volunteerId;
    public int programId;

 public Registration(){
    }

public Registration(int rid, Date rdate, int volunteerId, int programId) {
    this.rid = rid;
    this.rdate = rdate;
    this.volunteerId = volunteerId;
    this.programId = programId;
}

public int getRid() {
    return rid;
}

public void setRid(int rid) {
    this.rid = rid;
}

public Date getRdate() {
    return rdate;
}

public void setRdate(Date rdate) {
    this.rdate = rdate;
}

public int getVolunteerId() {
    return volunteerId;
}

public void setVolunteerId(int volunteerId) {
    this.volunteerId = volunteerId;
}

public int getProgramId() {
    return programId;
}

public void setProgramId(int programId) {
    this.programId = programId;
}   

}

    


   