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

import com.heroku.java.SERVICES.IssueCreateDAO; //nama file dao
import com.heroku.java.MODEL.Issue; // nama model/bean

@Controller
public class IssueCreateController { //nama controller
    // private final IssueCreateDAO issueCreateDAO; //declare nama dao yang nak pakai
    private final DataSource dataSource;


@Autowired
public IssueCreateController(DataSource dataSource) {
    this.dataSource = dataSource;

// public IssueCreateController(IssueCreateDAO issueCreateDAO) { //declare je
    // this.issueCreateDAO = issueCreateDAO;
    
}

@GetMapping("/addIssue")
    public String addIssue() {
        return "addIssue";
    }

@PostMapping("/addIssue")
    public String addIssue(HttpSession session, @ModelAttribute("IssueDetail")Issue issue) {
        try {
            IssueCreateDAO issueCreateDAO = new IssueCreateDAO(dataSource);
            issueCreateDAO.addIssue(issue);
            return "redirect:/viewIssue"; 
            // issueCreateDAO.addIssue(issue);
        

        
    } catch (SQLException e) {
        e.printStackTrace();
        return "/homepage";
    }
    }

}