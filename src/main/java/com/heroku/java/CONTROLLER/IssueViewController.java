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
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

import com.heroku.java.SERVICES.IssueViewDAO; 
import com.heroku.java.MODEL.Issue; 

@Controller
public class IssueViewController { 
    private final IssueViewDAO issueViewDAO; 
    private final DataSource dataSource;

    @Autowired
    public IssueViewController(IssueViewDAO issueViewDAO, DataSource dataSource) { 
        this.issueViewDAO = issueViewDAO;
        this.dataSource = dataSource;
    }
    
    @GetMapping("/viewIssue")
    public String viewIssue(Model model, Issue issue) {
        IssueViewDAO issueViewDAO = new IssueViewDAO(dataSource);
        try{
            List<Issue> issuelist = issueViewDAO.listIssue();
            model.addAttribute("issuess", issuelist);
        }  catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
        return "viewIssue";    
      
        }

    //updateissue
    @GetMapping("/updateIssue")
    public String updateIssue(@RequestParam("iid")int issueid, Model model) {
      try {
        IssueViewDAO issueViewDAO = new IssueViewDAO(dataSource);
        Issue issue = issueViewDAO.getIssueById(issueid);
        model.addAttribute("updateIssue", issue);
        return "updateIssue";
      } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("damn error bro");
        return "error";
    }
    }

    @PostMapping("/updateIssue")
    public String updateIssue(@ModelAttribute("IssueDetail") Issue issue) {
        try {
            IssueViewDAO issueViewDAO = new IssueViewDAO(dataSource);
            issueViewDAO.updateIssue(issue);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Damn error BRO!");
        }
        return "redirect:/updateIssue"; // Replace with the appropriate redirect URL after updating the staff details
    }

    // @PostMapping("/deleteStaff")
    // public String deleteStaff(@RequestParam("id") int employeeId) {
    //     try {
    //         EmployeeDAO employeeDAO = new EmployeeDAO(dataSource);
    //         employeeDAO.deleteEmployee(employeeId);
    //         return "redirect:/listStaff";
    //     } catch (SQLException e) {
    //         System.out.println("Error deleting employee: " + e.getMessage());
    //         // Handle the exception or display an error message to the user
    //         // You can redirect to an error page or display a meaningful message
    //         return "error";
    //     }
    // }


        
    }

