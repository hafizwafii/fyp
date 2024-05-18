package com.heroku.java.MODEL;

public class Admin {
    private int adminid;
    private String adminname;
    private String adminemail;
    private int admincontactnum;
    private String adminusername;
    private String adminpassword;

    public Admin(){
    }

    public Admin(int adminid, String adminname, String adminemail, int admincontactnum, String adminusername,
            String adminpassword) {
        this.adminid = adminid;
        this.adminname = adminname;
        this.adminemail = adminemail;
        this.admincontactnum = admincontactnum;
        this.adminusername = adminusername;
        this.adminpassword = adminpassword;
    }

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getAdminemail() {
        return adminemail;
    }

    public void setAdminemail(String adminemail) {
        this.adminemail = adminemail;
    }

    public int getAdmincontactnum() {
        return admincontactnum;
    }

    public void setAdmincontactnum(int admincontactnum) {
        this.admincontactnum = admincontactnum;
    }

    public String getAdminusername() {
        return adminusername;
    }

    public void setAdminusername(String adminusername) {
        this.adminusername = adminusername;
    }

    public String getAdminpassword() {
        return adminpassword;
    }

    public void setAdminpassword(String adminpassword) {
        this.adminpassword = adminpassword;
    }

    
    
}
