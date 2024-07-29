package com.heroku.java.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model; 
import jakarta.servlet.http.HttpSession;


import javax.sql.DataSource;

import java.io.IOException;
import java.sql.*;
import java.util.List;

// import com.heroku.java.SERVICES.AdminDAO;
import com.heroku.java.SERVICES.IssueViewDAO;
import com.heroku.java.SERVICES.ProgramDAO; //nama file dao
import com.heroku.java.SERVICES.RegistrationDAO;
// import com.heroku.java.SERVICES.VolunteerEmailDAO;
import com.heroku.java.MODEL.Issue;
import com.heroku.java.MODEL.Program; // nama model/bean
// import com.heroku.java.MODEL.Registration;
import com.heroku.java.MODEL.Volunteer;

@Controller
public class ProgramController {
    private final DataSource dataSource;
    private ProgramDAO programDAO;

    public ProgramController(DataSource dataSource, ProgramDAO programDAO) {
        this.dataSource = dataSource;
        this.programDAO = programDAO;
    }

    // -------------------CREATE PROGRAM FOR ADMIN----------------------------------//
    @GetMapping("/addProgram")
    public String addProgram(HttpSession session) {
    if (session.getAttribute("adminid") != null) {
        // Admin is logged in, proceed to addProgram
        return "addProgram";
    } else {
        // Admin is not logged in, redirect to login page
        return "redirect:/login";
    }
    }

    @PostMapping("/addProgram")
    public String addProgram(HttpSession session, @ModelAttribute("ProgramDetail")Program program,  @RequestParam("pimage") MultipartFile pimage) throws IOException {
        Integer adminid = (Integer) session.getAttribute("adminid");  // Retrieve admin ID from session

        //debug
        System.out.println("Admin id create (program): " + adminid);

        if (adminid == null) {
        return "redirect:/login"; 

    }

        try {
            program.setPimagebyte(pimage.getBytes());
            program.setAdminId(adminid); 
            ProgramDAO programDAO = new ProgramDAO(dataSource);

            programDAO.addProgram(program);
            // programDAO.addProgram(program);
            return "redirect:/viewProgram"; 
                
    } catch (SQLException e) {
        e.printStackTrace();
        return "redirect:/login";
    }
    }

    // -------------------VIEW PROGRAM FOR ADMIN----------------------------------//
    @GetMapping("/viewProgram")
    public String viewProgram(Model model, Program program, HttpSession session) {
        ProgramDAO programDAO = new ProgramDAO(dataSource);

        Integer adminid = (Integer) session.getAttribute("adminid");
        String username = (String) session.getAttribute("username");

        if (adminid == null) {
        return "redirect:/login";  // Redirect to login if admin ID is not found in session
        }
        //debug
        // System.out.println("Admin id in session program (admin profile): " + adminid);
        System.out.println("Admin username view (program) :" + username);

        try{
            List<Program> programlist = programDAO.listProgram();
            model.addAttribute("programs", programlist);
            String role = (String) session.getAttribute("role");
            model.addAttribute("role", role);
        }  catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
        return "viewProgram";    
      
        }

        // -------------------VIEW PAST AND UPCOMING PROGRAM FOR ADMIN----------------------------------//
        @GetMapping("/viewUpcomingProgram")
        public String viewUpcomingPrograms(Model model, HttpSession session) {
        Integer adminid = (Integer) session.getAttribute("adminid");
        String username = (String) session.getAttribute("username");

        if (adminid == null) {
            return "redirect:/login";  // Redirect to login if admin ID is not found in session
        }

        System.out.println("Admin username view (upcoming programs): " + username);

        try {
            List<Program> programList = programDAO.listUpcomingPrograms();
            model.addAttribute("programs", programList);
            String role = (String) session.getAttribute("role");
            model.addAttribute("role", role);
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
        return "viewUpcomingProgram";
    }

    @GetMapping("/viewPastProgram")
    public String viewPastPrograms(Model model, HttpSession session) {
        Integer adminid = (Integer) session.getAttribute("adminid");
        String username = (String) session.getAttribute("username");

        if (adminid == null) {
            return "redirect:/login";  // Redirect to login if admin ID is not found in session
        }

        System.out.println("Admin username view (past programs): " + username);

        try {
            List<Program> programList = programDAO.listPastPrograms();
            model.addAttribute("programs", programList);
            String role = (String) session.getAttribute("role");
            model.addAttribute("role", role);
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
        return "viewPastProgram";
    }


        // -------------------UPDATE PROGRAM FOR ADMIN----------------------------------//
        @GetMapping("/updateProgram")
        public String updateProgram(@RequestParam("programid") int programid, Model model, HttpSession session) {
        if (session.getAttribute("adminid") != null) {
        // Admin is logged in, proceed with updating the program
        try {
            System.out.println("programid in controller :" + programid);
            ProgramDAO programDAO = new ProgramDAO(dataSource);
            Program program = programDAO.getProgramById(programid);

            if (program != null) {
                model.addAttribute("programs", program);
            }

            return "updateProgram";
        } catch (SQLException sqe) {
            System.out.println("Error Code = " + sqe.getErrorCode());
            System.out.println("SQL state = " + sqe.getSQLState());
            System.out.println("Message = " + sqe.getMessage());
            System.out.println("printTrace /n");
            sqe.printStackTrace();
            return "redirect:/viewProgram";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("E message : " + e.getMessage());
            return "error";
        }
        } else {
        // Admin is not logged in, redirect to login page
        return "redirect:/login";
        }
    }   

        @PostMapping("/updateProgram")
        public String updateProgram(@ModelAttribute("ProgramDetail") Program program, @RequestParam("pimage") MultipartFile pimage) {
        ProgramDAO programDAO = new ProgramDAO(dataSource);
        boolean success = programDAO.updateProgram(program, pimage);
        if (success) {
        return "redirect:/viewProgram";
    }   else {
        return "error";
    }
    }   

// -------------------DELETE PROGRAM FOR ADMIN----------------------------------//
    @PostMapping("/deleteProgram")
    public String deleteProgram(@RequestParam("pid") int programid) {
    try {
        ProgramDAO programDAO = new ProgramDAO(dataSource);
        if (programDAO.hasRegistrations(programid)) {
            // If there are registrations, do not delete the program and return an appropriate message
            return "redirect:/viewProgram?error= Sorry! It cannot be deleted because program has registrations";
        } else {
            programDAO.deleteProgram(programid);
            return "redirect:/viewProgram?success=This program has been successfully deleted";
        }
    } catch (SQLException e) {
        System.out.println("Error deleting program: " + e.getMessage());
        return "error";
    }
}

// -------------------HOME PAGE AFTER LOGIN FOR VOLUNTEER----------------------------------//
    @GetMapping("/homevolunteer")
    public String homevolunteer(Model model) {
        ProgramDAO programDAO = new ProgramDAO(dataSource);
        IssueViewDAO issueViewDAO = new IssueViewDAO(dataSource);
        try {
            List<Program> programlist = programDAO.listProgram();
            model.addAttribute("programs", programlist);
            List<Issue> issuelist = issueViewDAO.listIssue();
            model.addAttribute("issuess", issuelist);
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
        return "homevolunteer";
    }

   // -------------------VIEW VOLUNTEER THAT JOINED THE PROGRAM----------------------------------//
    @GetMapping("/viewProgramVolunteer")
    public String viewProgramVolunteer(@RequestParam("programid") int programId, Model model) {
        try {
            ProgramDAO programDAO = new ProgramDAO(dataSource);
            RegistrationDAO registrationDAO = new RegistrationDAO(dataSource);
            
            Program program = programDAO.getProgramById(programId);
            List<Volunteer> volunteers = registrationDAO.getVolunteersByProgramId(programId);
            
            model.addAttribute("programName", program.getPname());
            model.addAttribute("volunteers", volunteers);
            
            return "viewProgramVolunteer";
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }    
}
