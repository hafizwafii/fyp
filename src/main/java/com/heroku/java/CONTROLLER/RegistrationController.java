package com.heroku.java.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import javax.sql.DataSource;
import java.sql.SQLException;
import com.heroku.java.SERVICES.RegistrationDAO;
import com.heroku.java.MODEL.Registration;

@Controller
public class RegistrationController {

    private RegistrationDAO registrationDAO;

    @Autowired
    public RegistrationController(RegistrationDAO registrationDAO) {
        this.registrationDAO = registrationDAO;
    }

    // @Autowired
    // public RegistrationController(ProgramDAO programDAO) {
    //     this.registrationDAO = registrationDAO;
    // }

    // @GetMapping("/update-registration")
    // public String showUpdateRegistration(@RequestParam("pid") int programid, Model model) {
    //     try {
    //         Registration registrations = programDAO.getProgramById(programid);
    //         if (registrations != null) {
    //             model.addAttribute("registrations", registrations);
    //             return "update-registration";
    //         } else {
    //             return "homevolunteer"; // Or another error page if registration is not found
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         return "homevolunteer"; // Or another error page
    //     }
    // }
}
