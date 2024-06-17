package com.heroku.java.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import javax.sql.DataSource;
import java.sql.SQLException;

import com.heroku.java.SERVICES.RegistrationDAO;
import com.heroku.java.SERVICES.VolunteerDAO;

import jakarta.servlet.http.HttpSession;

import com.heroku.java.SERVICES.ProgramDAO;

import com.heroku.java.MODEL.Registration;
import com.heroku.java.MODEL.Volunteer;
import com.heroku.java.MODEL.Program;

@Controller
public class RegistrationController {
    private final DataSource dataSource;
    private RegistrationDAO registrationDAO;

    @Autowired
    public RegistrationController(RegistrationDAO registrationDAO, DataSource dataSource) {
        this.dataSource = dataSource;
        this.registrationDAO = registrationDAO;
    }

    @PostMapping("/addRegister")
    public String showRegistration(HttpSession session, @RequestParam("pid") int programid, Registration registration) {
    try {
        Integer volunteerId = (Integer) session.getAttribute("vid");
        // Integer programId = (Integer) session.getAttribute("programid");

        //debug
        System.out.println("VOLUNTEER ID in registration: " + volunteerId);

        // Check if volunteerId is valid
        if (volunteerId != null) {
            return "redirect:/login"; // Redirect to login if volunteer ID is not found
        }

        // Set the volunteerId and programId in the registration object
        registration.setVolunteerId(volunteerId);
        registration.setProgramId(programid);

        // Call RegistrationDAO method to save the registration
        registrationDAO.showRegistration(registration);

        return "redirect:/homevolunteer";
    } catch (NumberFormatException e) {
        e.printStackTrace();
        return "redirect:/login";
    } catch (SQLException e) {
        e.printStackTrace();
        return "redirect:/login";
    }
}


}
    


