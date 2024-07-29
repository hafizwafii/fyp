package com.heroku.java.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.ui.Model; 
import jakarta.servlet.http.HttpSession; 
import java.sql.SQLException; 
import javax.sql.DataSource;

// import java.security.Principal;
// import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.heroku.java.SERVICES.AdminDAO; //nama file dao
// import com.heroku.java.SERVICES.IssueViewDAO;
// import com.heroku.java.SERVICES.VolunteerDAO;

import com.heroku.java.MODEL.Admin; // nama model/bean
// import com.heroku.java.MODEL.Issue;
import com.heroku.java.MODEL.Volunteer;

@Controller
public class AdminController {
    // private final AdminDAO adminDAO; //declare nama dao yang nak pakai
    private final DataSource dataSource;
    

@Autowired
public AdminController(DataSource dataSource) {
    this.dataSource = dataSource;
  
}

// -------------------CREATE ACCOUNT FOR NORMAL ADMIN----------------------------------// 
@GetMapping("/addAccount")
    public String addAccount () {
        return "addAccount";
    }

 @PostMapping("/addAccount")
    public String addAccount(HttpSession session, @ModelAttribute("AdminDetail")Admin admin) {
        try {
            AdminDAO adminDAO = new AdminDAO(dataSource);
            adminDAO.addAdmin(admin);
            return "redirect:/viewAccount"; 
            // issueCreateDAO.addIssue(issue);
        
    } catch (SQLException e) {
        e.printStackTrace();
        return "/";
    }
    }

    // -------------------VIEW ACCOUNT FOR NORMAL ADMIN AND ITSELF----------------------------------//
    @GetMapping("/viewAccount")
    public String viewAccount(HttpSession session, Model model) {
    AdminDAO adminDAO = new AdminDAO(dataSource);
    String adminusername = (String) session.getAttribute("username");  // Ensure you're using the correct session attribute name
    try {
        Admin currentAdmin = adminDAO.findAdminByUsername(adminusername);

        if (currentAdmin == null) {
            return "login";
        }

        String role = currentAdmin.getRole();
        List<Admin> adminlist;

        if ("Super Admin".equals(role)) {
            // Super Admin can only see Normal Admins, not other Super Admins
            adminlist = adminDAO.listNormalAdmins();
        } else {
            // Normal Admin sees only other Normal Admins
            // adminlist = adminDAO.listAdminsByRole(role);
            adminlist = new ArrayList<>();
            adminlist.add(currentAdmin);
        }

        model.addAttribute("admins", adminlist);
        model.addAttribute("role", role);  // Add the role to the model

        return "viewAccount";

    } catch (SQLException e) {
        e.printStackTrace();
        return "error";
    }
}
  
// -------------------VIEW ALL VOLUNTEER----------------------------------//
@GetMapping("/viewVolunteer")
public String listVolunteer(Model model, HttpSession session) {
Integer adminId = (Integer) session.getAttribute("adminid");  // Retrieve admin ID from session

// Check if admin ID is null
if (adminId == null) {
    return "redirect:/login";  // Redirect to login if admin ID is not found in session
}
    try {
        AdminDAO adminDAO = new AdminDAO(dataSource);
        List<Volunteer> volunteers = adminDAO.getAllVolunteers();
        model.addAttribute("volunteers", volunteers);
        return "viewVolunteer";
    } catch (SQLException e) {
        System.out.println("Error retrieving volunteers: " + e.getMessage());
        return "redirect:/login";
    }
}

// -------------------UPDATE ACCOUNT FOR NORMAL ADMIN----------------------------------//
@GetMapping("/updateAccount")
public String updateAccount(@RequestParam("adminid") int adminid, Model model, HttpSession session) {
    if (session.getAttribute("adminid") != null) {
        // Admin is logged in, proceed with updating the account
        try {
            System.out.println("adminid in controller :" + adminid);

            AdminDAO adminDAO = new AdminDAO(dataSource);
            Admin admin = adminDAO.getAdminById(adminid);
            model.addAttribute("admin", admin);
            return "updateAccount";
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
    } else {
        // Admin is not logged in, redirect to login page
        return "redirect:/login";
    }
}

@PostMapping("/updateAccount")
public String updateAccount(@ModelAttribute("AdminDetail") Admin admin) {
        try {
            AdminDAO adminDAO = new AdminDAO(dataSource);
            adminDAO.updateAccount(admin);
            return "redirect:/viewAccount"; // Redirect to the viewIssue page after updating the issue
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
}

// -------------------DELETE ACCOUNT FOR NORMAL ADMIN----------------------------------//
@PostMapping("/deleteAccount")
public String deleteAccount(@RequestParam("adminid") int adminid) {
    try {
        AdminDAO adminDAO = new AdminDAO(dataSource);
        adminDAO.deleteAccount(adminid);
        return "redirect:/viewAccount";
    } catch (SQLException e) {
        System.out.println("Error deleting issue: " + e.getMessage());
        return "error";
    }
}

}


    






