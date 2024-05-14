package com.heroku.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.heroku.java.MODEL.Issue;
import com.heroku.java.MODEL.Program;
import com.heroku.java.SERVICES.IssueViewDAO;
import com.heroku.java.SERVICES.ProgramDAO;

import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@Controller
public class GettingStartedApplication {
    private final DataSource dataSource;

    @Autowired
    public GettingStartedApplication(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    

    @GetMapping("/homepage")
    public String homepage(Model model, Program program, Issue issue) {
        ProgramDAO programDAO = new ProgramDAO(dataSource);
        IssueViewDAO issueViewDAO = new IssueViewDAO(dataSource);
        try{
            List<Program> programlist = programDAO.listProgram();
            model.addAttribute("programs", programlist);
            List<Issue> issuelist = issueViewDAO.listIssue();
            model.addAttribute("issuess", issuelist);
        }  catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
        return "homepage";
    }

    @GetMapping("/homevolunteer")
    public String homevolunteer(Model model, Program program, Issue issue) {
        ProgramDAO programDAO = new ProgramDAO(dataSource);
        IssueViewDAO issueViewDAO = new IssueViewDAO(dataSource);
        try{
            List<Program> programlist = programDAO.listProgram();
            model.addAttribute("programs", programlist);
            List<Issue> issuelist = issueViewDAO.listIssue();
            model.addAttribute("issuess", issuelist);
        }  catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
        return "homevolunteer";
    }

    @GetMapping("/registrationform")
    public String registrationform() {
        return "registrationform";
    }


    // @GetMapping("/homepage")
    // public String homepage(Model model, Issue issue) {
    //     IssueViewDAO issueViewDAO = new IssueViewDAO(dataSource);
    //     try{
    //         List<Issue> issuelist = issueViewDAO.listIssue();
    //         model.addAttribute("issuess", issuelist);
    //     }  catch (SQLException e) {
    //         e.printStackTrace();
    //         return "error";
    //     }
    //     return "homepage";
    // }


    // @GetMapping("/homevolunteer")
    // public String homevolunteer() {
    //     return "homevolunteer";
    // }

    @GetMapping("/homepageadmin")
    public String homepageadmin() {
        return "homepageadmin";
    }

    

    // @GetMapping("/addProgram")
    // public String addProgram() {
    //     return "addProgram";
    // }



    // @GetMapping("/viewIssue")
    // public String viewIssue() {
    //     return "viewIssue";
    // }

    // @GetMapping("/addIssue")
    // public String addIssue() {
    //     return "addIssue";
    // }
    

    // @GetMapping("/profilevolunteer")
    // public String profilevolunteer() {
    //     return "profilevolunteer";
    // }

    // @GetMapping("/login") 
    // public String login(HttpSession session) { 
    //         return "login"; 
    // }
    // @PostMapping("/login")
    // public String login(HttpSession session, @RequestParam("username") String username,
    //                 @RequestParam("password") String password, Model model) {

    // @GetMapping("/database")
    // String database(Map<String, Object> model) {
    //     try (Connection connection = dataSource.getConnection()) {
    //         final var statement = connection.createStatement();
    //         statement.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
    //         statement.executeUpdate("INSERT INTO ticks VALUES (now())");

    //         final var resultSet = statement.executeQuery("SELECT tick FROM ticks");
    //         final var output = new ArrayList<>();
    //         while (resultSet.next()) {
    //             output.add("Read from DB: " + resultSet.getTimestamp("tick"));
    //         }

    //         model.put("records", output);
    //         return "database";

    //     } catch (Throwable t) {
    //         model.put("message", t.getMessage());
    //         return "error";
    //     }
    // }

    public static void main(String[] args) {
        SpringApplication.run(GettingStartedApplication.class, args);
    }
}
