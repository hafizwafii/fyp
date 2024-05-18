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
import java.sql.*;
import java.util.List;

import com.heroku.java.SERVICES.AdminDAO; //nama file dao
import com.heroku.java.MODEL.Admin; // nama model/bean



@Controller
public class AdminController {
    // private final AdminDAO adminDAO; //declare nama dao yang nak pakai
    private final DataSource dataSource;
    

@Autowired
public AdminController(DataSource dataSource) {
    this.dataSource = dataSource;
  
}

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
        return "/homepage";
    }
    }

    @GetMapping("/viewAccount")
    public String viewAccount() {
        return "viewAccount";
    }

}   






