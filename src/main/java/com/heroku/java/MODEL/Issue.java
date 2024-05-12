package com.heroku.java.MODEL;

import java.sql.Date;

public class Issue {
    private int iid;
    private String iname;
    private String idesc;
    private Date idate;
    private String iremark;
    
    public Issue(){
    }

    public Issue(int iid, String iname, String idesc, Date idate, String iremark) {
        this.iid=iid;
        this.iname = iname;
        this.idesc = idesc;
        this.idate = idate;
        this.iremark = iremark;
    }

    public int getIid(){
        return iid;
    }

    public void setIid(int iid){
        this.iid=iid;
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
