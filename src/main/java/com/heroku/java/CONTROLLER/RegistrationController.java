package com.heroku.java.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.http.HttpSession;
import com.heroku.java.SERVICES.VolunteerDAO;
import com.heroku.java.SERVICES.RegistrationDAO;
import com.heroku.java.SERVICES.EmailService;
import com.heroku.java.SERVICES.ProgramDAO;
import com.heroku.java.MODEL.Registration;
import com.heroku.java.MODEL.Volunteer;
import com.heroku.java.MODEL.Program;

@Controller
public class RegistrationController {
    private final DataSource dataSource;
    private final RegistrationDAO registrationDAO;
    private final EmailService emailService;

    @Autowired
    public RegistrationController(RegistrationDAO registrationDAO, DataSource dataSource, EmailService emailService) {
        this.dataSource = dataSource;
        this.registrationDAO = registrationDAO;
        this.emailService = emailService;
    }

    // -------------------CREATE REGISTRATION FOR VOLUNTEER---------------------------------//
    @GetMapping("/addRegister")
    public String showRegistration(HttpSession session, @RequestParam("pid") int programid, Model model) {
        try {
            ProgramDAO programDAO = new ProgramDAO(dataSource);
            Program programs = programDAO.getProgramById(programid);
            Integer volunteerId = (Integer) session.getAttribute("volunteerid");

            if (volunteerId == null) {
                return "redirect:/login"; // Redirect to login if volunteer ID is not found
            }

            boolean alreadyRegistered = registrationDAO.isAlreadyRegistered(volunteerId, programid);

            if (programs != null) {
                model.addAttribute("programs", programs);
                model.addAttribute("alreadyRegistered", alreadyRegistered);
                model.addAttribute("programid", programid);
                model.addAttribute("volunteerId", volunteerId);
                return "addRegister";
            } else {
                return "homevolunteer"; // Or another error page if program is not found
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "homevolunteer"; // Or another error page
        }
    }
// -------------------EMAIL NOTIFICATION AFTER REGISTRATION----------------------------------//
    @PostMapping("/addRegister")
    public String handleRegistration(HttpSession session, @RequestParam("pid") int programid, Registration registration, Model model) {
        try {
            Integer volunteerId = (Integer) session.getAttribute("volunteerid");

            if (volunteerId == null) {
                return "redirect:/login"; // Redirect to login if volunteer ID is not found
            }

            registration.setVolunteerId(volunteerId);
            registration.setProgramId(programid);

            try {
                registrationDAO.showRegistration(registration);

                // Fetch program and volunteer details
                ProgramDAO programDAO = new ProgramDAO(dataSource);
                Program program = programDAO.getProgramById(programid);
                VolunteerDAO volunteerDAO = new VolunteerDAO(dataSource);
                Volunteer volunteer = volunteerDAO.getVolunteerById(volunteerId);

                // Prepare email content
                String subject = "Registration Confirmation: " + program.getPname();
                String htmlContent = String.format(
                        "<h1>Registration Confirmation</h1>" +
                        "<p>Dear %s,</p>" +
                        "<p>You have successfully registered for the following program:</p>" +
                        "<ul>" +
                        "<li>Program Name: %s</li>" +
                        "<li>Program Description: %s</li>" +
                        "<li>Program Date: %s</li>" +
                        "<li>Registration Date: %s</li>" +
                        "</ul>" +
                        "<p>Thank you for registering!</p>",
                        volunteer.getName(),
                        program.getPname(),
                        program.getPdesc(),
                        program.getPdate(),
                        registration.getRdate().toString()
                );
                System.out.println("email volunteer: " + volunteer.getEmail());
                // Send email
                emailService.sendHtmlEmail(volunteer.getEmail(), subject, htmlContent);
                
                return "redirect:/homevolunteer";
            } catch (SQLException e) {
                model.addAttribute("errorMessage", "You have already registered for this program.");
                model.addAttribute("alreadyRegistered", true);
                return "redirect:/addRegister?pid=" + programid;
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "redirect:/login";
        }
    }

    // -------------------VIEW PROGRAM THAT HAVE BEEN REGISTERED FOR VOLUNTEER----------------------------------//
    @GetMapping("/profileRegistration")
    public String profileRegistration(HttpSession session, Model model) {
    try {
        Integer volunteerId = (Integer) session.getAttribute("volunteerid");

        if (volunteerId == null) {
            return "redirect:/login"; // Redirect to login if volunteer ID is not found
        }

        // Fetch the list of programs the volunteer has registered for
        List<Program> programs = registrationDAO.getProgramsByVolunteerId(volunteerId);
        model.addAttribute("programs", programs);
        
        return "profileRegistration"; // Redirect to profile registration view
    } catch (SQLException e) {
        e.printStackTrace();
        return "homevolunteer"; // Or another error page
    }
}

}

