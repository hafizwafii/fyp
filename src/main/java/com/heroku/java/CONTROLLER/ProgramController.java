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

import com.heroku.java.SERVICES.AdminDAO;
import com.heroku.java.SERVICES.IssueViewDAO;
import com.heroku.java.SERVICES.ProgramDAO; //nama file dao
import com.heroku.java.SERVICES.RegistrationDAO;
import com.heroku.java.SERVICES.VolunteerEmailDAO;
import com.heroku.java.MODEL.Issue;
import com.heroku.java.MODEL.Program; // nama model/bean
import com.heroku.java.MODEL.Registration;
import com.heroku.java.MODEL.Volunteer;

@Controller
public class ProgramController {
    private final DataSource dataSource;

    public ProgramController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/addProgram")
    public String addProgram(HttpSession session) {
        return "addProgram";
    }

    @PostMapping("/addProgram")
    public String addProgram(HttpSession session, @ModelAttribute("ProgramDetail")Program program,  @RequestParam("pimage") MultipartFile pimage) throws IOException {
        Integer adminId = (Integer) session.getAttribute("adminid");  // Retrieve admin ID from session

        //debug
        System.out.println("Admin id create (program): " + adminId);

        if (adminId == null) {
        return "redirect:/login";  // Redirect to login if admin ID is not found in session

    }

        try {
            program.setPimagebyte(pimage.getBytes());
            program.setAdminId(adminId); 
            ProgramDAO programDAO = new ProgramDAO(dataSource);

            programDAO.addProgram(program);
            // programDAO.addProgram(program);
            return "redirect:/viewProgram"; 
                
    } catch (SQLException e) {
        e.printStackTrace();
        return "/homepage";
    }
    }

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

        // update program
        @GetMapping("/updateProgram")
        public String updateProgram(@RequestParam("programid") int programid, Model model) {
            try {

                System.out.println("programid in controller :"+ programid);
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

    // program volunteer
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
