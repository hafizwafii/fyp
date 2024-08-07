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

import javax.sql.DataSource;

import com.heroku.java.SERVICES.VolunteerProfileDAO; //nama file dao
import com.heroku.java.MODEL.Volunteer; // nama model/bean

@Controller
public class VolunteerProfileController { //nama controller
    private final VolunteerProfileDAO volunteerProfileDAO; //declare nama dao yang nak pakai
    // private DataSource dataSource;

@Autowired
public VolunteerProfileController(VolunteerProfileDAO volunteerProfileDAO) { //declare je
    this.volunteerProfileDAO = volunteerProfileDAO;
}    

// -------------------VIEW ACCOUNT FOR VOLUNTEER----------------------------------//
@GetMapping("/profilevolunteer")
public String volunteerProfile(@RequestParam(name = "success", required = false) Boolean success, HttpSession session,
        Model model, Volunteer volunteer) {

    Integer vid = (Integer) session.getAttribute("volunteerid");
    String username = (String) session.getAttribute("username");

    // Check if volunteer ID is null
    if (vid == null) {
        return "redirect:/login";  
    }

    // Debug
    System.out.println("Volunteer id in session (volunteer profile): " + vid);
    System.out.println("Volunteer name in session (volunteer profile): " + username);

    try {
        // Fetch volunteer profile using the DAO method
        volunteer = volunteerProfileDAO.VolunteerProfile(vid);
        model.addAttribute("VolunteerProfile", volunteer);
    } catch (SQLException sqe) {
        System.out.println("Error Code = " + sqe.getErrorCode());
        System.out.println("SQL state = " + sqe.getSQLState());
        System.out.println("Message = " + sqe.getMessage());
        System.out.println("printTrace /n");
        sqe.printStackTrace();
    }

    return "profilevolunteer";
}


@PostMapping("/UpdateProfile")
public String updateProfile(HttpSession session, @ModelAttribute("VolunteerProfile") Volunteer volunteer, Model model) {
    int vid = volunteer.getId();
    String vfullname = volunteer.getName();
    System.out.println("Volunteer id in session (volunteer update): " + vid);
    System.out.println("Volunteer name in session (volunteer update): " + vfullname);

    if (vid != 0) {
        try {
            boolean isUpdated = volunteerProfileDAO.updateProfile(volunteer);
            if (isUpdated) {
                session.setAttribute("VolunteerProfile", volunteer);
                return "redirect:/profilevolunteer?profileSuccess=true";
            } else {
                return "redirect:/profilevolunteer?noChanges=true";
            }
        } catch (SQLException sqe) {
            System.out.println("Error Code = " + sqe.getErrorCode());
            System.out.println("SQL state = " + sqe.getSQLState());
            System.out.println("Message = " + sqe.getMessage());
            System.out.println("printTrace /n");
            sqe.printStackTrace();
            return "redirect:/homevolunteer";
        }
    }
    return "redirect:/profilevolunteer?profileSuccess=true";
}
    
}