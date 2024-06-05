package com.heroku.java.MODEL;

public class Admin {
    private int adminid;
    private String adminname;
    private String adminemail;
    private String adminusername;
    private String adminpassword;
    private String role;

    public Admin(){
    }

    public Admin(int adminid, String adminname, String adminemail, String adminusername,
            String adminpassword, String role) {
        this.adminid = adminid;
        this.adminname = adminname;
        this.adminemail = adminemail;
        this.adminusername = adminusername;
        this.adminpassword = adminpassword;
        this.role = role;
    }

    public Admin(int adminid, String adminname, String adminusername, String adminpassword,String role) {
        this.adminid = adminid;
        this.adminname = adminname;
        this.adminusername = adminusername;
        this.adminpassword = adminpassword;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    

    
    
}