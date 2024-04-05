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

import com.heroku.java.SERVICES.VolunteerProfileDAO; //nama file dao
import com.heroku.java.MODEL.Volunteer; // nama model/bean

@Controller
public class VolunteerProfileController { //nama controller
    private final VolunteerProfileDAO volunteerProfileDAO; //declare nama dao yang nak pakai

@Autowired
public VolunteerProfileController(VolunteerProfileDAO volunteerProfileDAO) { //declare je
    this.volunteerProfileDAO = volunteerProfileDAO;
}    

@GetMapping("/profilevolunteer")
public String volunteerProfile(@RequestParam(name = "success", required = false) Boolean success, HttpSession session,
        Model model, Volunteer volunteer) { //model mana nak pakai

    //setup session dekat page        
    int vid = (int) session.getAttribute("volunteerid");
    String username = (String) session.getAttribute("username");

    //debug
    System.out.println("Volunteer id in session (volunteer profile): " + vid);
    System.out.println("Volunteer name in session (volunteer profile): " + username);

    if (vid != 0) {
        try {
            //dia amik Volunteerprofiledao (yang biru tu function dalam daoprofile) (vid tu dia nak pass pergi dao)
            volunteer = volunteerProfileDAO.VolunteerProfile(vid);
            model.addAttribute("VolunteerProfile", volunteer);
            return "/profilevolunteer";
        } catch (SQLException sqe) {
            System.out.println("Error Code = " + sqe.getErrorCode());
            System.out.println("SQL state = " + sqe.getSQLState());
            System.out.println("Message = " + sqe.getMessage());
            System.out.println("printTrace /n");
            sqe.printStackTrace();
        }
    }

    return "/profilevolunteer";
}

 @PostMapping("UpdateProfile")
    public String updateProfile(HttpSession session, @ModelAttribute("VolunteerProfile") Volunteer volunteer, Model model) {
        int vid = (int) session.getAttribute("vid");
        String vfullname = (String) session.getAttribute("vname");
        System.out.println("Volunteer id in session (volunteer update): " + vid);
        System.out.println("Volunteer name in session (volunteer update): " + vfullname);

        if (vid != 0) {
            try {
                volunteer.setId(vid);
                volunteer = volunteerProfileDAO.UpdateProfile(volunteer);
                return "redirect:/profilevolunteer?profileSuccess=true";
            } catch (SQLException sqe) {
                System.out.println("Error Code = " + sqe.getErrorCode());
                System.out.println("SQL state = " + sqe.getSQLState());
                System.out.println("Message = " + sqe.getMessage());
                System.out.println("printTrace /n");
                sqe.printStackTrace();
                return "redirect:/homevolunteer";
            }
        }
        return "/profilevolunteer?profileSuccess=true";
    }

    
}