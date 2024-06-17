package com.heroku.java.MODEL;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class Program {
    public int pid;
    public String pname;
    public String pdesc;
    public String pvenue;
    public String ptime;
    public Date pdate;
    public byte[] pimagebyte;
    public MultipartFile pimage;
    public String imageSrc;
    public int adminId;
    public int volunteerId;

    public Program(){
    }

    public Program(int pid, String pname, String pdesc, String pvenue, String ptime, Date pdate, byte[] pimagebyte,
            MultipartFile pimage, String imageSrc) {
                
        this.pid = pid;
        this.pname = pname;
        this.pdesc = pdesc;
        this.pvenue = pvenue;
        this.ptime = ptime;
        this.pdate = pdate;
        this.pimagebyte = pimagebyte;
        this.pimage = pimage;
        this.imageSrc = imageSrc;
    }

    public Program(int pid, String pname, String pdesc, String pvenue, String ptime, Date pdate, byte[] pimagebyte,
            MultipartFile pimage, String imageSrc, int adminId, int volunteerId) {
                
        this.pid = pid;
        this.pname = pname;
        this.pdesc = pdesc;
        this.pvenue = pvenue;
        this.ptime = ptime;
        this.pdate = pdate;
        this.pimagebyte = pimagebyte;
        this.pimage = pimage;
        this.imageSrc = imageSrc;
        this.adminId = adminId;
        this.volunteerId = volunteerId;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public String getPvenue() {
        return pvenue;
    }

    public void setPvenue(String pvenue) {
        this.pvenue = pvenue;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public byte[] getPimagebyte() {
        return this.pimagebyte;
    }

    public void setPimagebyte(byte[] pimagebyte) {
        this.pimagebyte = pimagebyte;
    }

    public MultipartFile getPimage() {
        return this.pimage;
    }

    public void setPimage(MultipartFile pimage) {
        this.pimage = pimage;
    }

    public String getImageSrc() {
        return this.imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(int volunteerId) {
        this.volunteerId = volunteerId;
    }

    
    
    
}
