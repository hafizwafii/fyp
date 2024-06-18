package com.heroku.java.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import javax.sql.DataSource;
import java.sql.SQLException;
import jakarta.servlet.http.HttpSession;

import com.heroku.java.SERVICES.VolunteerDAO;
import com.heroku.java.SERVICES.RegistrationDAO;
import com.heroku.java.SERVICES.ProgramDAO;

import com.heroku.java.MODEL.Registration;
import com.heroku.java.MODEL.Volunteer;
import com.heroku.java.MODEL.Program;

@Controller
public class RegistrationController {
    private final DataSource dataSource;
    private final RegistrationDAO registrationDAO;

    @Autowired
    public RegistrationController(RegistrationDAO registrationDAO, DataSource dataSource) {
        this.dataSource = dataSource;
        this.registrationDAO = registrationDAO;
    }

    @GetMapping("/addRegister")
    public String showRegistration(HttpSession session, @RequestParam("pid") int programid, Model model) {
        try {
            System.out.println("PROGRAM ID in registration :" + programid);
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
            // Update the volunteer's program ID (vpid) after successful registration
            VolunteerDAO volunteerDAO = new VolunteerDAO(dataSource);
            Volunteer volunteer = volunteerDAO.getVolunteerById(volunteerId);
            volunteer.setVpid(programid);
            volunteerDAO.updateVolunteer(volunteer);  // You need to implement this method

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

}
