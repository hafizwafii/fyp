package com.heroku.java.MODEL;

import java.sql.Date;

public class Issue {
    private String iname;
    private String idesc;
    private Date idate;
    private String iremark;
    
    public Issue(){
    }

    public Issue(String iname, String idesc, Date idate, String iremark) {
        this.iname = iname;
        this.idesc = idesc;
        this.idate = idate;
        this.iremark = iremark;
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getIdesc() {
        return idesc;
    }

    public void setIdesc(String idesc) {
        this.idesc = idesc;
    }

    public Date getIdate() {
        return idate;
    }

    public void setIdate(Date idate) {
        this.idate = idate;
    }

    public String getIremark() {
        return iremark;
    }

    public void setIremark(String iremark) {
        this.iremark = iremark;
    }

    
    
    
}
