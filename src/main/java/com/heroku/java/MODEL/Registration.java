package com.heroku.java.MODEL;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class Registration {
    public int rid;
    public int vid;
    public int programid;
    public byte[] programimagebyte;
    public MultipartFile programimage;
    public String imageSrc;
    public String programname;
    public String programdesc;
    public String programvenue;
    public String programtime;
    public String programdate;
    public Date rdate;


    public Registration(int rid, int vid, int programid, byte[] programimagebyte, MultipartFile programimage,
            String imageSrc, String programname, String programdesc, String programvenue, String programtime,
            String programdate, Date rdate) {
                
        this.rid = rid;
        this.vid = vid;
        this.programid = programid;
        this.programimagebyte = programimagebyte;
        this.programimage = programimage;
        this.imageSrc = imageSrc;
        this.programname = programname;
        this.programdesc = programdesc;
        this.programvenue = programvenue;
        this.programtime = programtime;
        this.programdate = programdate;
        this.rdate = rdate;
    }


    public Registration(int rid, int vid, int programid,String programname, String programdesc, String programvenue, String programtime,
            String programdate, Date rdate) {
                
        this.rid = rid;
        this.vid = vid;
        this.programid = programid;
        this.programname = programname;
        this.programdesc = programdesc;
        this.programvenue = programvenue;
        this.programtime = programtime;
        this.programdate = programdate;
        this.rdate = rdate;
    }


    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public int getProgramid() {
        return programid;
    }

    public void setProgramid(int programid) {
        this.programid = programid;
    }

    public byte[] getProgramimagebyte() {
        return programimagebyte;
    }

    public void setProgramimagebyte(byte[] programimagebyte) {
        this.programimagebyte = programimagebyte;
    }

    public MultipartFile getProgramimage() {
        return programimage;
    }

    public void setProgramimage(MultipartFile programimage) {
        this.programimage = programimage;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getProgramname() {
        return programname;
    }

    public void setProgramname(String programname) {
        this.programname = programname;
    }

    public String getProgramdesc() {
        return programdesc;
    }

    public void setProgramdesc(String programdesc) {
        this.programdesc = programdesc;
    }

    public String getProgramvenue() {
        return programvenue;
    }

    public void setProgramvenue(String programvenue) {
        this.programvenue = programvenue;
    }

    public String getProgramtime() {
        return programtime;
    }

    public void setProgramtime(String programtime) {
        this.programtime = programtime;
    }

    public String getProgramdate() {
        return programdate;
    }

    public void setProgramdate(String programdate) {
        this.programdate = programdate;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

}
