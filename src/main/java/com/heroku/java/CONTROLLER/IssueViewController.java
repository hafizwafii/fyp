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
    public String viewIssue(Model model, Issue issue, HttpSession session) {
        IssueViewDAO issueViewDAO = new IssueViewDAO(dataSource);

        int adminid = (int) session.getAttribute("adminid");
        String username = (String) session.getAttribute("username");
    
        //debug
        System.out.println("Admin id in session issue (admin profile): " + adminid);
        System.out.println("Admin name in session issue (admin profile): " + username);

        try{
            List<Issue> issuelist = issueViewDAO.listIssue();
            model.addAttribute("issuess", issuelist);
            String role = (String) session.getAttribute("role");
            model.addAttribute("role", role);
        }  catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
        return "viewIssue";    
      
        }

    //updateissue
     @GetMapping("/updateIssue")
    public String updateIssue(@RequestParam("iid") int issueid, Model model) {
        try {
            System.out.println("issueid in controller :"+ issueid);

            IssueViewDAO issueViewDAO = new IssueViewDAO(dataSource);
            Issue issue = issueViewDAO.getIssueById(issueid);
            model.addAttribute("issue", issue);
            return "updateIssue";
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping("/updateIssue")
    public String updateIssue(@ModelAttribute("IssueDetail") Issue issue) {
        try {
            IssueViewDAO issueViewDAO = new IssueViewDAO(dataSource);
            issueViewDAO.updateIssue(issue);
            return "redirect:/viewIssue"; // Redirect to the viewIssue page after updating the issue
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }
    
    //Delete the issue//
    @PostMapping("/deleteIssue")
    public String deleteIssue(@RequestParam("iid") int issueid) {
    try {
        IssueViewDAO issueViewDAO = new IssueViewDAO(dataSource);
        issueViewDAO.deleteIssue(issueid);
        return "redirect:/viewIssue";
    } catch (SQLException e) {
        System.out.println("Error deleting issue: " + e.getMessage());
        return "error";
    }
}

    


        
    
}

