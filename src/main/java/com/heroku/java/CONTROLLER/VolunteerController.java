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
import java.util.List;

import com.heroku.java.SERVICES.VolunteerDAO;
import com.heroku.java.MODEL.Volunteer;



@Controller
public class VolunteerController  {   
    private VolunteerDAO volunteerDAO;

    @Autowired
    public VolunteerController (VolunteerDAO volunteerDAO){
        this.volunteerDAO=volunteerDAO;
    }
    // private final DataSource dataSource;

    // public volunteerController(DataSource dataSource) {
    //     this.dataSource = dataSource;
    // }
    
    
    //CREATE ACCOUNT
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String addVolunteer(HttpSession session, @ModelAttribute("signup")Volunteer volunteer) {
        try {
            volunteerDAO.addVolunteer(volunteer);
        // VolunteerDAO volunteerDAO = new VolunteerDAO(volunteerDAO);
        // volunteerDAO.addVolunteer(volunteer);

        return "redirect:/login";
    } catch (SQLException e) {
        e.printStackTrace();
        return "error";
    }
    }


    
}
