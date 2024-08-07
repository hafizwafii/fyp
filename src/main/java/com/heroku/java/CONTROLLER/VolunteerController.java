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

import javax.sql.DataSource;

import com.heroku.java.SERVICES.VolunteerDAO;
import com.heroku.java.MODEL.Volunteer;

@Controller
public class VolunteerController  {   
private VolunteerDAO volunteerDAO;
private DataSource dataSource;

@Autowired
public VolunteerController (VolunteerDAO volunteerDAO){
this.volunteerDAO=volunteerDAO;
}

// -------------------CREATE ACCOUNT FOR VOLUNTEER----------------------------------//
@GetMapping("/signup")
public String signup() {
return "signup";
}

@PostMapping("/signup")
public String addVolunteer(HttpSession session, @ModelAttribute("signup")Volunteer volunteer) {
    try {
        volunteerDAO.addVolunteer(volunteer);
        
        return "redirect:/login";
    } catch (SQLException e) {
        e.printStackTrace();
        return "error";
    }
}

// -------------------SEARCH VOLUNTEER BY NAME FOR ADMIN----------------------------------//
@GetMapping("/searchVolunteer")
public String searchVolunteer(@RequestParam("vname") String volunteerName, Model model) {
    try{
        List<Volunteer> volunteers = volunteerDAO.searchVolunteersByName(volunteerName);
        model.addAttribute("volunteers", volunteers); 
    }catch (SQLException e) {
                   
    e.printStackTrace(); // You may want to log the exception instead
    model.addAttribute("error", "An error occurred during the search: " + e.getMessage());
    }
    return "viewVolunteer"; // Replace with the name of your results view
    }   
}